
package shop.dao;

public class DetalleVenta {
    
    private int codigoVenta;
    private int codigoArticulo;
    private int cantidad;
    private int descuento;
    private Articulo articulo;
    private Venta venta;

    public DetalleVenta(int codigoVenta, int codigoArticulo, int cantidad, int descuento, Articulo articulo, Venta venta) {
        this.codigoVenta = codigoVenta;
        this.codigoArticulo = codigoArticulo;
        this.cantidad = cantidad;
        this.descuento = descuento;
        this.articulo = articulo;
        this.venta = venta;
    }

    public DetalleVenta() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getCodigoVenta() {
        return codigoVenta;
    }

    public void setCodigoVenta(int codigoVenta) {
        this.codigoVenta = codigoVenta;
    }

    public int getCodigoArticulo() {
        return codigoArticulo;
    }

    public void setCodigoArticulo(int codigoArticulo) {
        this.codigoArticulo = codigoArticulo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getDescuento() {
        return descuento;
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }
    
    
}
