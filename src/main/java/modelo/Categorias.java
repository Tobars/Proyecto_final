package modelo;

public class Categorias {
    
    private int categoria_id;
    private String nombre_categoria;
    private String descripcion;

    public Categorias() {
        
    }

    public Categorias(int categoria_id, String nombre_categoria, String descripcion) {
        this.categoria_id = categoria_id;
        this.nombre_categoria = nombre_categoria;
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCategoria_id() {
        return categoria_id;
    }

    public void setCategoria_id(int categoria_id) {
        this.categoria_id = categoria_id;
    }

    public String getNombre_categoria() {
        return nombre_categoria;
    }

    public void setNombre_categoria(String nombre_categoria) {
        this.nombre_categoria = nombre_categoria;
    }
    
    
    
    
    
}
