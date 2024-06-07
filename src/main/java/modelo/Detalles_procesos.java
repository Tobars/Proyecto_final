package modelo;

public class Detalles_procesos {
    
    private int detalle_proceso;
    private int producto_id;
    private int proceso_id;
   
    public Detalles_procesos() {  
    }

    public int getProceso_id() {
        return proceso_id;
    }

    public void setProceso_id(int proceso_id) {
        this.proceso_id = proceso_id;
    }

    public int getDetalle_proceso() {
        return detalle_proceso;
    }

    public void setDetalle_proceso(int detalle_proceso) {
        this.detalle_proceso = detalle_proceso;
    }

    public int getProducto_id() {
        return producto_id;
    }

    public void setProducto_id(int producto_id) {
        this.producto_id = producto_id;
    }
    
    
    
    
}
