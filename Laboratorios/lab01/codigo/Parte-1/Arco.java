
/**
 * Write a description of class Arcos here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Arco
{
    // instance variables - replace the example below with your own
    private int idNodo;
    private double distancia;
    private String nombre;
    
    public Arco() {
    }

    public Arco(int idNodo, double distancia, String nombre) {
        this.idNodo = idNodo;
        this.distancia = distancia;
        this.nombre = nombre;
    }

    public int getIdNodo() {
        return idNodo;
    }

    public void setIdNodo(int idNodo) {
        this.idNodo = idNodo;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}
