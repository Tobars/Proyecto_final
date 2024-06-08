package DAOs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.Categorias;
import modelo.Config;
import modelo.OperacionesBasicas;

public class DAO_Categorias implements OperacionesBasicas {

    Config bd = new Config();
    Categorias categoria = new Categorias();

    
    public void registrarDetalleProceso(int categoriaId, int accion) {
        try {
            Connection con = DriverManager.getConnection(this.bd.getUrl(),this.bd.getUsuario(), this.bd.getContrasena());
            PreparedStatement pst = con.prepareStatement("INSERT INTO Detalles_Procesos (categoria_id, proceso_id) VALUES (?, ?)");

            pst.setInt(1, categoriaId);
            pst.setInt(2, accion);

            pst.executeUpdate();

            con.close();
            pst.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al registrar detalles del proceso: " + e.getMessage());
        }
    }
    @Override
    public boolean insertar(Object obj) {
        try {
            this.categoria = (Categorias) obj;

            Connection con = null;
            PreparedStatement pst = null;
            ResultSet rs = null;

            // Consulta para obtener el maximo categoria_id actual
            String maxIdQuery = "SELECT MAX(categoria_id) FROM Categorias";

            // Consulta SQL para insertar una nueva categoria
            String sql = "INSERT INTO Categorias (categoria_id, nombre_categoria, descripcion) VALUES (?, ?, ?)";

            Class.forName(this.bd.getDriver());
            con = DriverManager.getConnection(this.bd.getUrl(), this.bd.getUsuario(), this.bd.getContrasena());

            // Obtener el maximo categoria_id actual
            pst = con.prepareStatement(maxIdQuery);
            rs = pst.executeQuery();
            int maxId = 0;
            if (rs.next()) {
                maxId = rs.getInt(1);
            }

            // Incrementar el maximo categoria_id para asignarlo a la nueva categoría
            int nuevoId = maxId + 1;

            // Preparar la consulta para insertar la nueva categoria
            pst = con.prepareStatement(sql);

            pst.setInt(1, nuevoId);
            pst.setString(2, this.categoria.getNombre_categoria());
            pst.setString(3, this.categoria.getDescripcion());

            int filasAfectadas = pst.executeUpdate();

            con.close();
            pst.close();

            // Registrar el detalle del proceso
            registrarDetalleProceso(nuevoId, 7); // 7 para Agregar Categoría

            return filasAfectadas > 0;

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al insertar categoría: " + ex.getMessage());
            return false;
        }
    
    }

    @Override
    public boolean modificar(Object obj) {
        try {
            this.categoria = (Categorias) obj;

            Connection con = null;
            PreparedStatement pst = null;
            String sql = "UPDATE Categorias SET nombre_categoria = ?, descripcion = ? WHERE categoria_id = ?";

            Class.forName(this.bd.getDriver());
            con = DriverManager.getConnection(this.bd.getUrl(), this.bd.getUsuario(), this.bd.getContrasena());

            pst = con.prepareStatement(sql);

            pst.setString(1, this.categoria.getNombre_categoria());
            pst.setString(2, this.categoria.getDescripcion());
            pst.setInt(3, this.categoria.getCategoria_id());

            int filasAfectadas = pst.executeUpdate();

            con.close();
            pst.close();

            // Registrar el detalle del proceso
            registrarDetalleProceso(this.categoria.getCategoria_id(), 9); // 9 para Modificar Categoría

            return filasAfectadas > 0;

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al modificar categoría: " + ex.getMessage());
            return false;
        }
    }
    


    @Override
    public boolean eliminar(Object obj) {
        try {
            this.categoria = (Categorias) obj;

            // Verificar si hay productos asociados a esta categoría
            DAO_Productos daoProductos = new DAO_Productos();
            if (daoProductos.productosEnCategoria(this.categoria.getCategoria_id())) {
                // Si hay productos asociados, mostrar un mensaje de advertencia 
                int option = JOptionPane.showConfirmDialog(null, "Esta categoria contiene productos. Desea eliminar los productos y la categoria?", "Advertencia", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    // Si el usuario confirma, eliminar todos los productos asociados a la categoria
                    boolean eliminado = daoProductos.eliminarProductosEnCategoria(this.categoria.getCategoria_id());
                    if (!eliminado) {
                        // Si no se pueden eliminar los productos, mostrar un mensaje y salir
                        JOptionPane.showMessageDialog(null, "No se pudieron eliminar los productos asociados. No se puede eliminar la categoria.");
                        return false;
                    }
                } else {
                    // Si el usuario no confirma, salir sin eliminar la categoría
                    return false;
                }
            }

            // Si no hay productos asociados o si se eliminaron con exito, proceder con la eliminacion de la categoria
            Connection con = null;
            PreparedStatement pst = null;
            String sql = "DELETE FROM Categorias WHERE categoria_id = ?";

            Class.forName(this.bd.getDriver());
            con = DriverManager.getConnection(this.bd.getUrl(), this.bd.getUsuario(), this.bd.getContrasena());

            pst = con.prepareStatement(sql);
            pst.setInt(1, this.categoria.getCategoria_id());

            int filasAfectadas = pst.executeUpdate();

            con.close();
            pst.close();

            // Registrar el detalle del proceso
            registrarDetalleProceso(this.categoria.getCategoria_id(), 8); // 8 para Borrar Categoria

            return filasAfectadas > 0;

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar la categoría: " + ex.getMessage());
            return false;
        }

    }

    @Override
    public ArrayList<Object[]> seleccionar() {
        ArrayList<Object[]> datos = new ArrayList<>();

        Connection con;
        PreparedStatement pst;
        ResultSet rs;
        String sql = "SELECT * FROM Categorias";

        try {
            Class.forName(this.bd.getDriver());
            con = DriverManager.getConnection(this.bd.getUrl(), this.bd.getUsuario(), this.bd.getContrasena());

            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                Object[] fila = new Object[3];
                for (int i = 0; i < 3; i++) {
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
    public int obtenerIdCategoriaPorNombre(String nombreCategoria) {
        int categoriaID = 0; //ID de categoria como 0 por defecto

        try {
            Connection con = null;
            PreparedStatement pst = null;
            ResultSet rs = null;

            // Consulta SQL para obtener el ID de la categoría a partir de su nombre
            String sql = "SELECT categoria_id FROM Categorias WHERE nombre_categoria = ?";

            Class.forName(this.bd.getDriver());
            con = DriverManager.getConnection(this.bd.getUrl(), this.bd.getUsuario(), this.bd.getContrasena());

            pst = con.prepareStatement(sql);
            pst.setString(1, nombreCategoria);
            rs = pst.executeQuery();

            // Si se encuentra una categoria con el nombre especificado, asignamos su ID a categoriaID
            if (rs.next()) {
                categoriaID = rs.getInt("categoria_id");
            }

            con.close();
            pst.close();
            rs.close();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al obtener el ID de la categoría: " + ex.getMessage());
        }

        return categoriaID;
    }
    
    public ArrayList<Categorias> obtenerCategorias() {
        ArrayList<Categorias> categorias = new ArrayList<>();

        try {
            Connection con = null;
            PreparedStatement pst = null;
            ResultSet rs = null;

            // Consulta SQL para seleccionar todas las categoias
            String sql = "SELECT * FROM Categorias";

            Class.forName(this.bd.getDriver());
            con = DriverManager.getConnection(this.bd.getUrl(), this.bd.getUsuario(), this.bd.getContrasena());

            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();

            // Iterar sobre los resultados y crear objetos Categoria
            while (rs.next()) {
                Categorias categoria = new Categorias();
                categoria.setCategoria_id(rs.getInt("categoria_id"));
                categoria.setNombre_categoria(rs.getString("nombre_categoria"));
                categoria.setDescripcion(rs.getString("descripcion"));

                // Agregar el objeto Categoria a la lista
                categorias.add(categoria);
            }

            con.close();
            pst.close();
            rs.close();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al obtener categorias: " + ex.getMessage());
        }

        return categorias;
    }
}
