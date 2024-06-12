package modelo;

public class Config {
    
    private String url;
    private String usuario;
    private String contrasena;
    private String driver;


    public Config() {
        this.url = "jdbc:mysql://localhost:3306/Inventario";
        this.usuario = "root";
        this.contrasena = "password";
        this.driver = "com.mysql.cj.jdbc.Driver";
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }
    
    
    
}
