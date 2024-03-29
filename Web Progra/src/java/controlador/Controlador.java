package controlador;

import DAO.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Producto;
import modelo.User;

/**
 * Class that is responsible for window navigation
 *
 * @author Kevin Trejos
 */
@WebServlet(name = "Controlador", urlPatterns = {"/Controlador"})
public class Controlador extends HttpServlet {

    /**
     * Open the Register view
     */
    public String registrarse = "vistas/Registrarse.jsp";

    /**
     * Open the Enter view
     */
    public String ingresar = "vistas/Ingresar.jsp";

    /**
     * Open the view to add
     */
    public String añadir = "vistas/añadir.jsp";

    /**
     * Open the list view
     */
    public String lista = "vistas/lista.jsp";

    /**
     * Open the profile view
     */
    public String perfil = "vistas/perfil.jsp";

    /**
     * Open the barter view
     */
    public String trueque = "vistas/trueque.jsp";

    /**
     * Open the menu view
     */
    public String menu = "vistas/Menu.jsp";

    /**
     * Open the index view
     */
    public String inicio = "index.jsp";

    /**
     * Open the product features view
     */
    public String caracteristicas = "vistas/productoCaracteristicas.jsp";

    /**
     * Open the barter view
     */
    public String validarTrueque = "vistas/hacerTrueque.jsp";

    /**
     * Open the notifications view
     */
    public String notificaciones = "vistas/Notifications.jsp";

    /**
     * Open the list of products view
     */
    public String listaProductos = "vistas/ListaProductos.jsp";

    public boolean seguir = false;
    private static boolean emailValid = false;
    String producto1S = null;
    String producto2S = null;

    public User user = new User();

    public UserDAO dao;

    /**
     * Constructor
     *
     * @throws IOException
     */
    public Controlador() throws IOException {
        this.dao = new UserDAO();
    }

    /**
     * get
     *
     * @return
     */
    public static boolean isEmailValid() {
        return emailValid;
    }

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Controlador</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Controlador at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    private boolean verifyEmail(String email) {
        if (email.contains("@")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method that does the whole validation and navigation process
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String acceso = "";
        String accion = request.getParameter("accion");
        System.out.println("La accion es: " + accion);
        if (accion.equalsIgnoreCase("registrarse")) {
            request.setAttribute("idUsado", "false");
            request.setAttribute("email", "true");
            request.setAttribute("vacio", "false");
            acceso = registrarse;

        } else if (accion.equalsIgnoreCase("ingresar")) {
            request.setAttribute("ingreso", "true");
            acceso = ingresar;
        } else if (accion.equalsIgnoreCase("anadir")) {
            request.setAttribute("productoCorrecto", "false");
            acceso = añadir;
        } else if (accion.equalsIgnoreCase("introducir")) {
            //metodo de meter la vara saica
            Producto producto;
            String corta = request.getParameter("txtDescripcionCorta");
            String detallada = request.getParameter("txtDescripcionDetallada");
            String precioS = request.getParameter("txtPrecio");
            int precio = 0;
            if (!precioS.equals("")) {
                precio = Integer.parseInt(precioS);
            }
            String imagen = request.getParameter("txtImagen");
            String categoria = request.getParameter("categoria");
            if (!corta.equals("") && !detallada.equals("") && !imagen.equals("") && precio > 0) {
                request.setAttribute("productoCorrecto", "false");
                ArrayList<String> imagenes = new ArrayList<>();
                imagenes.add(imagen);
                producto = new Producto(imagenes, corta, detallada, categoria, precio, 0);
                dao.insertarProducto(producto, user.getId());
                acceso = menu;
            } else {
                request.setAttribute("productoCorrecto", "true");
                acceso = añadir;
            }
        } else if (accion.equalsIgnoreCase("listar")) {
            request.setAttribute("id", user.getId());
            acceso = lista;
        } else if (accion.equalsIgnoreCase("registrar")) {
            seguir = true;
            String id = request.getParameter("txtId");
            String nombre = request.getParameter("txtName");
            String apellidos = request.getParameter("txtSecondName");
            String correo = request.getParameter("txtEmail");
            String provincia = request.getParameter("txtProvincia");
            String canton = request.getParameter("txtCanton");
            String distrito = request.getParameter("txtDistrito");
            String password = request.getParameter("txtPassword");
            boolean isEmpty = true;
            boolean emailVerify = false;
            if (!nombre.equals("") && !id.equals("") && !apellidos.equals("") && !correo.equals("") && !provincia.equals("") && !canton.equals("") && !distrito.equals("") && !password.equals("")) {
                isEmpty = false;
                if (verifyEmail(correo)) {
                    User userID = null;
                    userID = dao.searchUser(id);
                    emailVerify = true;
                    if (userID == null) {
                        user.setCanton(canton);
                        user.setDistrito(distrito);
                        user.setEmail(correo);
                        user.setId(id);
                        user.setName(nombre);
                        user.setPassword(password);
                        user.setProvincia(provincia);
                        user.setSecondName(apellidos);
                        dao.registrarse(user);
                        acceso = menu;
                    } else {
                        request.setAttribute("idUsado", "true");
                        request.setAttribute("email", "true");
                        request.setAttribute("vacio", "false");
                        acceso = registrarse;
                    }
                } else {
                    emailVerify = false;
                    request.setAttribute("idUsado", "false");
                    request.setAttribute("email", "false");
                    request.setAttribute("vacio", "false");
                    acceso = registrarse;
                }
            } else {
                isEmpty = true;
                request.setAttribute("idUsado", "false");
                request.setAttribute("email", "false");
                request.setAttribute("vacio", "true");
                acceso = registrarse;
            }
        } else if (accion.equalsIgnoreCase("INGRESO")) {
            String email = request.getParameter("txtCorreo");
            String password = request.getParameter("txtPassword");
            User userAux;
            if ((userAux = dao.signIn(email, password)) != null) {
                request.setAttribute("ingreso", "true");
                user.setId(userAux.getId());
                ArrayList<Producto> list = userAux.getProductoIntercambiar();
                if (list != null) {
                    Iterator<Producto> it = list.iterator();
                    boolean notify = false;
                    boolean primeraVez = false;
                    boolean rechazado = false;
                    while (it.hasNext()) {
                        Producto next = it.next();
                        if (next.getEstadoTrueque() == 1) {
                            notify = true;
                        }
                    }
                    ArrayList<Producto> listProducts = userAux.getListaProductos();
                    if (listProducts != null) {
                        Iterator<Producto> iterador = listProducts.iterator();
                        while (iterador.hasNext()) {
                            Producto next = iterador.next();
                            if (next.isAceptadoPrimeraVez()) {
                                primeraVez = true;
                            }
                            if (next.isRechazado()) {
                                rechazado = true;
                            }
                        }
                    } else {
                        acceso = menu;
                    }
                    if (userAux.getProductoIntercambiar().size() > 0 && notify || primeraVez || rechazado) {
                        if (primeraVez) {
                            request.setAttribute("id", userAux.getId());
                            request.setAttribute("aceptado", "true");
                            request.setAttribute("rechazado", "false");
                            acceso = notificaciones;
                        } else if (rechazado) {
                            request.setAttribute("id", userAux.getId());
                            request.setAttribute("aceptado", "false");
                            request.setAttribute("rechazado", "true");
                            acceso = notificaciones;
                        } else {
                            request.setAttribute("id", userAux.getId());
                            request.setAttribute("aceptado", "false");
                            request.setAttribute("rechazado", "false");
                            acceso = notificaciones;
                        }
                    } else {
                        acceso = menu;
                    }
                } else {
                    acceso = menu;
                }
            } else {
                request.setAttribute("ingreso", "false");
                acceso = ingresar;
            }
        } else if (accion.equalsIgnoreCase("perfil")) {
            request.setAttribute("id", user.getId());
            acceso = perfil;
        } else if (accion.equalsIgnoreCase("inicio")) {
            acceso = inicio;
        } else if (accion.equalsIgnoreCase("trueque")) {
            //datos
            request.setAttribute("productoPrimero", request.getParameter("consecutivoPrimero"));
            request.setAttribute("id", user.getId());
            acceso = trueque;
        } else if (accion.equalsIgnoreCase("caracteristicas")) {
            //datos
            request.setAttribute("productoID", request.getParameter("consecutivo"));
            acceso = caracteristicas;
        } else if (accion.equalsIgnoreCase("validarTrueque")) {
            producto1S = request.getParameter("consecutivoPrimero");
            producto2S = request.getParameter("consecutivoSegundo");
            request.setAttribute("productoPrimero", producto1S);
            request.setAttribute("productoSegundo", producto2S);
            request.setAttribute("precioIncorrecto", "false");
            acceso = validarTrueque;
        } else if (accion.equalsIgnoreCase("notification")) {
            request.setAttribute("id", user.getId());
            request.setAttribute("aceptado", "false");
            request.setAttribute("rechazado", "false");
            acceso = notificaciones;
        } else if (accion.equalsIgnoreCase("menu")) {
            acceso = menu;

        } else if (accion.equalsIgnoreCase("aceptacion")) {
            int intProduct1 = Integer.parseInt(request.getParameter("producto1"));
            int intProduct2 = Integer.parseInt(request.getParameter("producto2"));
            Producto producto1 = dao.searchProduct(intProduct1);
            Producto producto2 = dao.searchProduct(intProduct2);
            if (producto1.getPrecio() >= producto2.getPrecio()) {
                if ((producto1.getPrecio() - producto2.getPrecio()) > 1000) {
                    request.setAttribute("productoPrimero", producto1S);
                    request.setAttribute("productoSegundo", producto2S);
                    request.setAttribute("precioIncorrecto", "true");
                    acceso = validarTrueque;
                } else {
                    //si se puede hacer
                    request.setAttribute("productoPrimero", producto1S);
                    request.setAttribute("productoSegundo", producto2S);
                    request.setAttribute("precioIncorrecto", "false");
                    dao.insertarSolicitud(producto2, producto1, user.getId());
                    acceso = menu;
                }
            } else if (producto1.getPrecio() < producto2.getPrecio()) {
                if ((producto2.getPrecio() - producto1.getPrecio()) > 1000) {
                    //no se puede hacer
                    request.setAttribute("productoPrimero", producto1S);
                    request.setAttribute("productoSegundo", producto2S);
                    request.setAttribute("precioIncorrecto", "true");
                    acceso = validarTrueque;
                } else {
                    //si se puede hacer
                    request.setAttribute("productoPrimero", producto1S);
                    request.setAttribute("productoSegundo", producto2S);
                    request.setAttribute("precioIncorrecto", "false");
                    dao.insertarSolicitud(producto2, producto1, user.getId());
                    acceso = menu;
                }
            }
        } else if (accion.equalsIgnoreCase("aceptarTrueque")) {
            int product = Integer.parseInt(request.getParameter("prodIntercambiar"));
            Producto producto1 = dao.searchProduct(product);
            dao.accept(producto1, user.getId());
            acceso = menu;
        } else if (accion.equalsIgnoreCase("rechazarTrueque")) {
            int intProduct1 = Integer.parseInt(request.getParameter("consecutivoBorrar"));
            Producto producto1 = dao.searchProduct(intProduct1);
            dao.rechazar(producto1, user.getId());
            acceso = menu;
        } else if (accion.equalsIgnoreCase("listaProductos")) {
            request.setAttribute("id", user.getId());
            acceso = listaProductos;
        } else if (accion.equalsIgnoreCase("aceptadoPrimera")) {
            String id = request.getParameter("id");
            dao.cambiarAceptadoFalso(id);
            acceso = menu;
        } else if (accion.equalsIgnoreCase("rechazoPrimero")) {
            String id = request.getParameter("id");
            dao.cambiarRechazadoFalso(id);
            acceso = menu;
        }

        RequestDispatcher vista = request.getRequestDispatcher(acceso);
        vista.forward(request, response);
    }

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     *
     * @return
     */
    public String getServletInfo() {
        return "Short description";
    }
}
