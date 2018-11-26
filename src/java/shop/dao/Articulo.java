
package shop.dao;

public class Articulo {
    
    private int codigoArticulo;
    private String nombre;
    private int precio;
    private String imagen;

    public Articulo() {
    }

    
    public Articulo(int codigoArticulo, String nombre, int precio, String imagen) {
        this.codigoArticulo = codigoArticulo;
        this.nombre = nombre;
        this.precio = precio;
        this.imagen = imagen;
    }

    public int getCodigoArticulo() {
        return codigoArticulo;
    }

    public void setCodigoArticulo(int codigoArticulo) {
        this.codigoArticulo = codigoArticulo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    
}
