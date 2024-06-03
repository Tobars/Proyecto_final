
package modelo;

public class Productos {

    private int producto_id;
    private String nombre_producto;
    private float precio;
    private int inStock;
    private String foto;
    private int categoria_id;

    public Productos(int producto_id, String nombre_producto, float precio, int inStock, String foto, int categoria_id) {
        this.producto_id = producto_id;
        this.nombre_producto = nombre_producto;
        this.precio = precio;
        this.inStock = inStock;
        this.foto = foto;
        this.categoria_id = categoria_id;
    }

    public Productos() {
    }

    public int getProducto_id() {
        return producto_id;
    }

    public void setProducto_id(int producto_id) {
        this.producto_id = producto_id;
    }

    public String getNombre_producto() {
        return nombre_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getInStock() {
        return inStock;
    }

    public void setInStock(int inStock) {
        this.inStock = inStock;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public int getCategoria_id() {
        return categoria_id;
    }

    public void setCategoria_id(int categoria_id) {
        this.categoria_id = categoria_id;
    }
    
    
    
    
}

