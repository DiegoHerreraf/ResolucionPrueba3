
package shop.services;

import shop.dao.DetalleVenta;
import shop.dao.Articulo;
import shop.dao.ArticuloDB;
import shop.dao.Venta;
import shop.dao.VentaDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Diego
 */
@WebServlet(name = "ServletController", urlPatterns = {"/ServletController"})
public class ServletController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
               String accion = request.getParameter("accion");
        if (accion.equals("ModificarArticulo")) {
            actualizarArticulo(request, response);
        } else if (accion.equals("AnadirCarrito")) {
            añadirCarrito(request, response);
        } else if (accion.equals("RegistrarVenta")) {
            registrarVenta(request, response);
        }else if (accion.equals("Cancelarcompra")){
            cancelarcompra(request, response);
        }
     
    }
     protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     }

    private void actualizarArticulo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Articulo a = new Articulo();
        a.setCodigoArticulo(Integer.parseInt(request.getParameter("txtCodigo")));
        a.setNombre(request.getParameter("txtNombre"));
        a.setPrecio(Integer.parseInt(request.getParameter("txtPrecio")));
        a.setImagen(a.getImagen());
        boolean rpta = ArticuloDB.actualizarArticulo(a);
        if (rpta) {
            response.sendRedirect("/ResolucionPrueba3/Pages/index.jsp");
        } else {
            response.sendRedirect("/ResolucionPrueba3/Pages/modificar.jsp");
        }
    }
    private void añadirCarrito(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Obtenemos la sesion actual
        HttpSession sesion = request.getSession();
        ArrayList<DetalleVenta> carrito;
        //Si no existe la sesion creamos al carrito
        if (sesion.getAttribute("carrito") == null) {
            carrito = new ArrayList<DetalleVenta>();
        } else {
            carrito = (ArrayList<DetalleVenta>) sesion.getAttribute("carrito");
        }
        Articulo a = ArticuloDB.obtenerArticulo(Integer.parseInt(request.getParameter("txtCodigo")));
        DetalleVenta d = new DetalleVenta();
        d.setCodigoArticulo(Integer.parseInt(request.getParameter("txtCodigo")));
        d.setArticulo(a);
        d.setCantidad(Integer.parseInt(request.getParameter("txtCantidad")));
        int subTotal = a.getPrecio() * d.getCantidad();
        if (subTotal > 25000) {
            d.setDescuento(3000);
        } else if(subTotal >35000){
            d.setDescuento(5000);
        } else{
            d.setDescuento(0);
        }
        //Sirva para saber si tenemos agregado el producto al carrito de compras
        int indice = -1;
        //recorremos todo el carrito de compras
        for (int i = 0; i < carrito.size(); i++) {
            DetalleVenta det = carrito.get(i);
            if (det.getCodigoArticulo() == a.getCodigoArticulo()) {
                indice = i;
                break;
            }
        }
        if (indice == -1) {
            //Si es -1 es porque voy a registrar
            carrito.add(d);
        } else {
            carrito.set(indice, d);
        }
        sesion.setAttribute("carrito", carrito);
        //Redireccionamos al formulario
        response.sendRedirect("/ResolucionPrueba3/Pages/RegistrarVenta.jsp");
    }
     private void registrarVenta(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesion = request.getSession();
        Venta v=new Venta();
        v.setCliente(request.getParameter("txtCliente"));
        ArrayList<DetalleVenta> detalle  = (ArrayList<DetalleVenta>) sesion.getAttribute("carrito");
        boolean rpta=VentaDB.insertarVenta(v, detalle);
        if (rpta) {
            response.sendRedirect("/ResolucionPrueba3/Pages/VerVenta.jsp");
             sesion.invalidate();
        } else {
            response.sendRedirect("/ResolucionPrueba3/Pages/RegistrarVenta.jsp.jsp");
        }
    }
     public void cancelarcompra(HttpServletRequest request, HttpServletResponse response)
             throws ServletException, IOException {
                HttpSession sesion = request.getSession();
                sesion.invalidate();
                response.sendRedirect("/ResolucionPrueba3/Pages/index.jsp");
     }
     @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}