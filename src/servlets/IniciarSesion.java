package servlets;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import entities.Persona;
import logic.Login;


/**
 * Servlet implementation class IniciarSesion
 */
@WebServlet({ "/IniciarSesion", "/iniciarSesion", "/iniciarsesion", "/Iniciarsesion" })
public class IniciarSesion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IniciarSesion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Persona per = new Persona();
		Login ctrl = new Login();
		String nombre=null;
		//String email = request.getParameter("email");
		//String password = request.getParameter("contraseña");
		
		//validar email y password
		
		per.setEmail(request.getParameter("email"));
		per.setContraseña(request.getParameter("contraseña"));
		
		per=ctrl.validate(per);
		//LinkedList<Persona> personas = ctrl.getAll();
		nombre = ctrl.buscarNombre(per);

		request.setAttribute("nombre", nombre);
		//request.getSession().setAttribute("usuario", per);
		//request.setAttribute("listaPersonas", personas);
		//request.setAttribute("usuario", per);
		request.getRequestDispatcher("WEB-INF/CliManagement.jsp").forward(request, response);
		
		
	}

}
