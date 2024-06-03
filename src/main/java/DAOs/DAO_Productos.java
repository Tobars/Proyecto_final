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
            this.producto = (Productos) obj;

            Connection con = null;
            PreparedStatement pst = null;
            String sql = "INSERT INTO Productos (producto_id, nombre_producto, precio, inStock, foto, categoria_id) VALUES (?, ?, ?, ?, ?, ?)";

            Class.forName(this.bd.getDriver());
            con = DriverManager.getConnection(this.bd.getUrl(), this.bd.getUsuario(), this.bd.getContrasena());

            pst = con.prepareStatement(sql);

            pst.setInt(1, this.producto.getProducto_id());
            pst.setString(2, this.producto.getNombre_producto());
            pst.setFloat(3, this.producto.getPrecio());
            pst.setInt(4, this.producto.getInStock());
            pst.setString(5, this.producto.getFoto());
            pst.setInt(6, this.producto.getCategoria_id());

            int filasAfectadas = pst.executeUpdate();

            con.close();
            pst.close();

            return filasAfectadas > 0;

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al insertar producto: " + ex.getMessage());
            return false;
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
    
    public ArrayList<Object[]> filtrarProductos(String nombre, int categoriaID) {
        ArrayList<Object[]> datosFiltrados = new ArrayList<>();

        Connection con;
        PreparedStatement pst;
        ResultSet rs;
        String sql = "SELECT * FROM Productos WHERE nombre_producto LIKE ? AND categoria_id = ?";

        try {
            Class.forName(this.bd.getDriver());
            con = DriverManager.getConnection(this.bd.getUrl(), this.bd.getUsuario(), this.bd.getContrasena());

            pst = con.prepareStatement(sql);

            // Configurar los parámetros de la consulta SQL
            pst.setString(1, "%" + nombre + "%"); // Utilizamos "%" para buscar coincidencias parciales en el nombre
            pst.setInt(2, categoriaID);

            rs = pst.executeQuery();

            while (rs.next()) {
                Object[] fila = new Object[6];
                for (int i = 0; i < 6; i++) {
                    fila[i] = rs.getObject(i + 1);
                }

                datosFiltrados.add(fila);
            }

            con.close();
            pst.close();
            rs.close();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return datosFiltrados;
    }

}
