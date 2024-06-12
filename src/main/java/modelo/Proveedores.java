package modelo;


public class Proveedores {
    
    private int proveedor_id;
    private String nombre_proveedor;

    public Proveedores(int proveedor_id, String nombre_proveedor) {
        this.proveedor_id = proveedor_id;
        this.nombre_proveedor = nombre_proveedor;
    }

    public Proveedores() {
    }

    public int getProveedor_id() {
        return proveedor_id;
    }

    public void setProveedor_id(int proveedor_id) {
        this.proveedor_id = proveedor_id;
    }

    public String getNombre_proveedor() {
        return nombre_proveedor;
    }

    public void setNombre_proveedor(String nombre_proveedor) {
        this.nombre_proveedor = nombre_proveedor;
    }
    
    
}
