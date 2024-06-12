package modelo;

public class Detalles_productos {
   
    private int detalle_productoi_id;
    private int producto_id;
    private int proveedor_id;

    public Detalles_productos() {
    }

    public int getDetalle_productoi_id() {
        return detalle_productoi_id;
    }

    public void setDetalle_productoi_id(int detalle_productoi_id) {
        this.detalle_productoi_id = detalle_productoi_id;
    }

    public int getProducto_id() {
        return producto_id;
    }

    public void setProducto_id(int producto_id) {
        this.producto_id = producto_id;
    }

    public int getProveedor_id() {
        return proveedor_id;
    }

    public void setProveedor_id(int proveedor_id) {
        this.proveedor_id = proveedor_id;
    }
    
    
}
