package modelo;

public class Tipos_procesos {
    
    private int proceso_id;
    private String nombre_proceso;
    private String descripcion;

    public Tipos_procesos(int proceso_id, String nombre_proceso, String descripcion) {
        this.proceso_id = proceso_id;
        this.nombre_proceso = nombre_proceso;
        this.descripcion = descripcion;
    }

    public Tipos_procesos() {
    }

    public int getProceso_id() {
        return proceso_id;
    }

    public void setProceso_id(int proceso_id) {
        this.proceso_id = proceso_id;
    }

    public String getNombre_proceso() {
        return nombre_proceso;
    }

    public void setNombre_proceso(String nombre_proceso) {
        this.nombre_proceso = nombre_proceso;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
    
}
