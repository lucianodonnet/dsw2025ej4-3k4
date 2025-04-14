
package domain;


public class Pais {
    private String nombre;
    private String ISO;
    
    public Pais(){}
    public Pais(String nombre,String ISO) {
        this.nombre = nombre;
        this.ISO=ISO;
    }

    @Override
    public String toString() {
        return nombre;
    }

    public String getNombre() {
        return nombre;
    }
   
    
    
}
