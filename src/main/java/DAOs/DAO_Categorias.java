package DAOs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.Categorias;
import modelo.Config;
import modelo.OperacionesBasicas;

public class DAO_Categorias implements OperacionesBasicas{
    
        Config bd = new Config();
    
        Categorias categoria = new Categorias();
        
        public boolean insertar(Object obj) {
        try {
            this.categoria = (Categorias) obj;

            Connection con = null;
            PreparedStatement pst = null;
            String sql = "INSERT INTO Categorias (categoria_id, nombre_categoria, descripcion) VALUES (?, ?, ?)";

            Class.forName(this.bd.getDriver());
            con = DriverManager.getConnection(this.bd.getUrl(), this.bd.getUsuario(), this.bd.getContrasena());

            pst = con.prepareStatement(sql);

            pst.setInt(1, this.categoria.getCategoria_id());
            pst.setString(2, this.categoria.getNombre_categoria());
            pst.setString(3, this.categoria.getDescripcion());

            int filasAfectadas = pst.executeUpdate();

            con.close();
            pst.close();

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
            pst.setString(2, this.categoria.getNombre_categoria());
            pst.setInt(3, this.categoria.getCategoria_id());

            int filasAfectadas = pst.executeUpdate();

            con.close();
            pst.close();

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

            return filasAfectadas > 0;

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar categoría: " + ex.getMessage());
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
        
        try{
            Class.forName(this.bd.getDriver());
            con = DriverManager.getConnection(this.bd.getUrl(), this.bd.getUsuario(), this.bd.getContrasena());

            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            
            while (rs.next()) {
                Object [] fila = new Object[3];
                for (int i=0; i<3;i++){
                    fila[i] = rs.getObject(i+1);
                }
                
                datos.add(fila);
                
            }
            
            con.close();
            pst.close();
            rs.close();             

            
            
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
            
        }
        return datos;
    }

    
}
