package DAOs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.Config;
import modelo.OperacionesBasicas;
import modelo.Productos;

/**
 *
 * @author Tobar
 */
public class DAO_Productos implements OperacionesBasicas {

    Config bd = new Config();

    Productos producto = new Productos();

    @Override
    public boolean insertar(Object obj) {
        try {
            Connection con = DriverManager.getConnection(this.bd.getUrl(), this.bd.getUsuario(), this.bd.getContrasena());
            String maxIdQuery = "SELECT MAX(producto_id) FROM tblProductos";
            String sql = "INSERT INTO tblProductos (producto_id, nombre_producto, precio, inStock, foto, categoria_id) VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement pstMaxId = con.prepareStatement(maxIdQuery);
            ResultSet rsMaxId = pstMaxId.executeQuery();
            int maxId = 0;
            if (rsMaxId.next()) {
                maxId = rsMaxId.getInt(1);
            }
            int nuevoId = maxId + 1;

            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, nuevoId);
            pst.setString(2, producto.getNombre_producto());
            pst.setFloat(3, producto.getPrecio());
            pst.setInt(4, producto.getInStock());
            pst.setString(5, producto.getFoto());
            pst.setInt(6, producto.getCategoria_id());

            int filasAfectadas = pst.executeUpdate();

            // Registrar el detalle del proceso si la inserción fue exitosa
            if (filasAfectadas > 0) {
                registrarDetalleProceso(nuevoId, 1); // 1 para Agregar Producto
            }

            con.close();
            pst.close();

            return filasAfectadas > 0;

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al insertar producto: " + ex.getMessage());
            return false;
        }
    }
    
    public void registrarDetalleProceso(int productoId, int accion) {
        try {
            Connection con = DriverManager.getConnection(this.bd.getUrl(), this.bd.getUsuario(), this.bd.getContrasena());
            PreparedStatement pst = con.prepareStatement("INSERT INTO Detalles_Procesos (producto_id, proceso_id) VALUES (?, ?)");

            pst.setInt(1, productoId);
            pst.setInt(2, accion);

            pst.executeUpdate();

            con.close();
            pst.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al registrar detalles del proceso: " + e.getMessage());
        }
    }

    @Override
    public boolean modificar(Object obj) {
        try {
            this.producto = (Productos) obj;
            Connection con = null;
            PreparedStatement pst = null;
            String sql = "UPDATE Productos SET nombre_producto = ?, precio = ?, inStock = ?, foto = ?, categoria_id = ? WHERE producto_id = ?";

            Class.forName(this.bd.getDriver());
            con = DriverManager.getConnection(this.bd.getUrl(), this.bd.getUsuario(), this.bd.getContrasena());
            pst = con.prepareStatement(sql);

            pst.setString(1, this.producto.getNombre_producto());
            pst.setFloat(2, this.producto.getPrecio());
            pst.setInt(3, this.producto.getInStock());
            pst.setString(4, this.producto.getFoto());
            pst.setInt(5, this.producto.getCategoria_id());
            pst.setInt(6, this.producto.getProducto_id());

            int filasAfectadas = pst.executeUpdate();
            
            // Registrar detalle del proceso
            registrarDetalleProceso(this.producto.getProducto_id(), 3); // 3 para Modificar Producto

            return filasAfectadas > 0;

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al modificar producto: " + ex.getMessage());
            return false;
        }
    }

    public boolean existeProducto(int productoID) {
        try {
            Connection con = DriverManager.getConnection(this.bd.getUrl(), this.bd.getUsuario(), this.bd.getContrasena());
            String sql = "SELECT * FROM Productos WHERE producto_id=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, productoID);
            ResultSet rs = pst.executeQuery();
            boolean existe = rs.next();
            rs.close();
            pst.close();
            con.close();
            return existe;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al verificar la existencia del producto: " + ex.getMessage());
            return false;
        }
    }


    public boolean eliminar(int productoID) {
        try {
            Connection con = null;
            PreparedStatement pst = null;
            String sql = "DELETE FROM Productos WHERE producto_id = ?";

            Class.forName(this.bd.getDriver());
            con = DriverManager.getConnection(this.bd.getUrl(), this.bd.getUsuario(), this.bd.getContrasena());

            pst = con.prepareStatement(sql);
            pst.setInt(1, productoID);

            int filasAfectadas = pst.executeUpdate();
            
            // Registrar detalle del proceso
            registrarDetalleProceso(productoID, 2); // 2 para Borrar Producto

            con.close();
            pst.close();

            return filasAfectadas > 0;

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar producto: " + ex.getMessage());
            return false;
        }
    }



    @Override
    public ArrayList<Object[]> seleccionar() {
        ArrayList<Object[]> datos = new ArrayList<>();

        Connection con;
        PreparedStatement pst;
        ResultSet rs;
        String sql = "SELECT * FROM Productos";

        try {
            Class.forName(this.bd.getDriver());
            con = DriverManager.getConnection(this.bd.getUrl(), this.bd.getUsuario(), this.bd.getContrasena());

            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                Object[] fila = new Object[6];
                for (int i = 0; i < 6; i++) {
                    fila[i] = rs.getObject(i + 1);
                }

                datos.add(fila);

            }

            con.close();
            pst.close();
            rs.close();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());

        }
        return datos;
    }

    @Override
    public boolean eliminar(Object obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public boolean eliminarProductosEnCategoria(int categoriaID) {
        try {
            Connection con = DriverManager.getConnection(this.bd.getUrl(), this.bd.getUsuario(), this.bd.getContrasena());
            String sql = "DELETE FROM Productos WHERE categoria_id=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, categoriaID);
            int filasAfectadas = pst.executeUpdate();
            pst.close();
            con.close();
            return filasAfectadas > 0; // Devuelve true si se eliminaron productos, false si no se eliminaron
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar productos de la categoría: " + ex.getMessage());
            return false;
        }
    }

    
    public boolean productosEnCategoria(int categoriaID) {
        try {
            Connection con = DriverManager.getConnection(this.bd.getUrl(), this.bd.getUsuario(), this.bd.getContrasena());
            String sql = "SELECT COUNT(*) FROM Productos WHERE categoria_id=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, categoriaID);
            ResultSet rs = pst.executeQuery();
            rs.next(); // Mover el cursor al primer resultado
            int count = rs.getInt(1); // Obtener el valor del primer resultado (el recuento de productos)
            rs.close();
            pst.close();
            con.close();
            return count > 0; // Devolver true si hay productos en la categoría, false si no los hay
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al verificar la existencia de productos en la categoría: " + ex.getMessage());
            return false;
        }
    }
    
    public ArrayList<Object[]> filtrarProductos(String nombre, int productoID, int categoriaID) {
        ArrayList<Object[]> datosFiltrados = new ArrayList<>();

        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            // Construir la consulta SQL base
            String sql = "SELECT * FROM Productos WHERE 1=1";

            // Añadir condiciones a la consulta según los campos proporcionados
            if (!nombre.isEmpty()) {
                sql += " AND nombre_producto LIKE ?";
            }
            if (productoID > 0) {
                sql += " AND producto_id = ?";
            }
            if (categoriaID > 0) {
                sql += " AND categoria_id = ?";
            }

            // Establecer la conexión y preparar la consulta
            Class.forName(this.bd.getDriver());
            con = DriverManager.getConnection(this.bd.getUrl(), this.bd.getUsuario(), this.bd.getContrasena());
            pst = con.prepareStatement(sql);

            // Configurar los parámetros de la consulta según los valores proporcionados
            int paramIndex = 1; // Índice para los parámetros en la consulta preparada
            if (!nombre.isEmpty()) {
                pst.setString(paramIndex++, "%" + nombre + "%"); // Utilizamos "%" para buscar coincidencias parciales en el nombre
            }
            if (productoID > 0) {
                pst.setInt(paramIndex++, productoID);
            }
            if (categoriaID > 0) {
                pst.setInt(paramIndex++, categoriaID);
            }

            // Ejecutar la consulta y obtener los resultados
            rs = pst.executeQuery();

            // Procesar los resultados y agregarlos a la lista de datos filtrados
            while (rs.next()) {
                Object[] fila = new Object[6];
                for (int i = 0; i < 6; i++) {
                    fila[i] = rs.getObject(i + 1);
                }
                datosFiltrados.add(fila);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al filtrar productos: " + ex.getMessage());
        } finally {
            try {
                // Cerrar los recursos utilizados
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al cerrar la conexión: " + ex.getMessage());
            }
        }
        return datosFiltrados;
    }

    

}
