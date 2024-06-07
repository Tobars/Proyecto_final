
package DAOs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.Config;
import modelo.Detalles_procesos;
import modelo.OperacionesBasicas;
import modelo.Productos;

public class DAO_Detalles_procesos implements OperacionesBasicas{
    
    Config bd = new Config();

    Detalles_procesos detalles_procesos = new Detalles_procesos();


    @Override
    public boolean insertar(Object obj) {
        try {
            Detalles_procesos detalleProceso = (Detalles_procesos) obj;

            Connection con = null;
            PreparedStatement pst = null;

            String sql = "INSERT INTO Detalles_Procesos (producto_id, proceso_id) VALUES (?, ?)";

            Class.forName(this.bd.getDriver());
            con = DriverManager.getConnection(this.bd.getUrl(), this.bd.getUsuario(), this.bd.getContrasena());

            pst = con.prepareStatement(sql);

            pst.setInt(1, detalleProceso.getProducto_id());
            pst.setInt(2, detalleProceso.getProceso_id());

            int filasAfectadas = pst.executeUpdate();

            con.close();
            pst.close();

            return filasAfectadas > 0;

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al insertar detalle de proceso: " + ex.getMessage());
            return false;
        }
    }


    @Override
    public boolean modificar(Object obj) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean eliminar(Object obj) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Object[]> seleccionar() {
        ArrayList<Object[]> detallesProcesos = new ArrayList<>();
        try {
            Connection con = null;
            PreparedStatement pst = null;
            ResultSet rs = null;

            String sql = "SELECT * FROM Detalles_Procesos";

            Class.forName(this.bd.getDriver());
            con = DriverManager.getConnection(this.bd.getUrl(), this.bd.getUsuario(), this.bd.getContrasena());

            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                Object[] fila = {
                    rs.getInt("producto_id"),
                    rs.getInt("proceso_id")
                };
                detallesProcesos.add(fila);
            }

            con.close();
            pst.close();
            rs.close();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al seleccionar detalles de procesos: " + ex.getMessage());
        }
        return detallesProcesos;
    }
    
}
