package servlets.funcion;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import entities.Funcion;
import entities.Pelicula;
import logic.LogicFuncion;
import logic.LogicPelicula;


/**
 * Servlet implementation class Funciones
 */
@WebServlet("/Funciones")
public class Funciones extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Funciones() {
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
		
		LogicFuncion lf= new LogicFuncion();
		Funcion fun = new Funcion();
		Pelicula pel = new Pelicula();
		LogicPelicula lp = new LogicPelicula();
		Integer cod=Integer.parseInt(request.getParameter("codigo"));
		fun.setCodigo_pelicula(cod);  
		pel = lp.buscarPorCodigo(cod);
		
		if(request.getParameter("elegirFecha")!=(null)) {
		String fechaTraida=request.getParameter("elegirFecha").toString();
		LocalDateTime fecha= LocalDateTime.parse(fechaTraida);
		                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
		
		/* RECORRE LAS FUNCIONES QUE SE TRAJO CON EL CODIGO DE LA PELICULA PARA SELECCIONAR LAS DEL DIA*/
		LinkedList<Funcion> funciones= lf.buscarFuncionPorfecha(fun,fecha);		
		
		request.setAttribute("pel", pel);
		request.setAttribute("listafunciones", funciones);
		request.getRequestDispatcher("WEB-INF/Funcion/FunManagement.jsp").forward(request, response);
		
	} else {

		LinkedList<Funcion> funciones = lf.buscarFuncionesPorPeli(fun);
		
		request.setAttribute("pel", pel);
		request.setAttribute("listafunciones", funciones);
		request.getRequestDispatcher("WEB-INF/Funcion/FunManagement.jsp").forward(request, response);
	}
	}
		
		
}


