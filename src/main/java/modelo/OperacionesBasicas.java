package modelo;

import java.util.ArrayList;


public interface OperacionesBasicas {
    
    public boolean insertar(Object obj); 
    public boolean modificar (Object obj);
    public boolean eliminar (Object obj);
    public ArrayList<?>  seleccionar ();
    
    
}
