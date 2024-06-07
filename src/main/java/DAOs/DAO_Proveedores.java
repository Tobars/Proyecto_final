package DAOs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.Config;
import modelo.OperacionesBasicas;
import modelo.Proveedores;

public class DAO_Proveedores implements OperacionesBasicas {
    
    Config bd = new Config();
    
    public boolean insertar(Object obj) {
        Proveedores proveedor = (Proveedores) obj;
        try {
            Connection con = null;
            PreparedStatement pst = null;
            ResultSet rs = null;

            // Obtener el ID máximo de proveedor actual en la base de datos
            int maxProveedorID = 0;
            String sqlMaxID = "SELECT MAX(proveedor_id) FROM Proveedores";

            Class.forName(bd.getDriver());
            con = DriverManager.getConnection(bd.getUrl(), bd.getUsuario(), bd.getContrasena());

            pst = con.prepareStatement(sqlMaxID);
            rs = pst.executeQuery();

            if (rs.next()) {
                maxProveedorID = rs.getInt(1);
            }

            // Generar un nuevo ID único mayor que el máximo actual
            int nuevoProveedorID = maxProveedorID + 1;

            // Preparar la consulta SQL para insertar el nuevo proveedor
            String sql = "INSERT INTO Proveedores (proveedor_id, nombre_proveedor) VALUES (?, ?)";
            pst = con.prepareStatement(sql);

            // Asignar los valores al PreparedStatement
            pst.setInt(1, nuevoProveedorID);
            pst.setString(2, proveedor.getNombre_proveedor());

            // Ejecutar la consulta y verificar si se insertó correctamente
            int filasAfectadas = pst.executeUpdate();

            // Cerrar recursos
            con.close();
            pst.close();
            rs.close();

            return filasAfectadas > 0;

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al insertar proveedor: " + ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean modificar(Object obj) {
        Proveedores proveedor = (Proveedores) obj;
        try {
            Connection con = null;
            PreparedStatement pst = null;
            String sql = "UPDATE Proveedores SET nombre_proveedor = ? WHERE proveedor_id = ?";

            Class.forName(bd.getDriver());
            con = DriverManager.getConnection(bd.getUrl(), bd.getUsuario(), bd.getContrasena());

            pst = con.prepareStatement(sql);

            pst.setString(1, proveedor.getNombre_proveedor());
            pst.setInt(2, proveedor.getProveedor_id());

            int filasAfectadas = pst.executeUpdate();

            con.close();
            pst.close();

            return filasAfectadas > 0;

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al modificar proveedor: " + ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminar(Object obj) {
        Proveedores proveedor = (Proveedores) obj;
        try {
            Connection con = null;
            PreparedStatement pst = null;
            String sql = "DELETE FROM Proveedores WHERE proveedor_id = ?";

            Class.forName(bd.getDriver());
            con = DriverManager.getConnection(bd.getUrl(), bd.getUsuario(), bd.getContrasena());

            pst = con.prepareStatement(sql);
            pst.setInt(1, proveedor.getProveedor_id());

            int filasAfectadas = pst.executeUpdate();

            con.close();
            pst.close();

            return filasAfectadas > 0;

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar proveedor: " + ex.getMessage());
            return false;
        }
    }

    @Override
    public ArrayList<Object[]> seleccionar() {
        ArrayList<Object[]> datos = new ArrayList<>();
        try {
            Connection con;
            PreparedStatement pst;
            ResultSet rs;
            String sql = "SELECT * FROM Proveedores";

            Class.forName(bd.getDriver());
            con = DriverManager.getConnection(bd.getUrl(), bd.getUsuario(), bd.getContrasena());

            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                Object[] fila = new Object[2];
                fila[0] = rs.getInt("proveedor_id");
                fila[1] = rs.getString("nombre_proveedor");
                datos.add(fila);
            }

            con.close();
            pst.close();
            rs.close();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al seleccionar proveedores: " + ex.getMessage());
        }
        return datos;
    }
}
