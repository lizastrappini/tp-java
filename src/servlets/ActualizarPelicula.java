package servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Pelicula;
import logic.LogicPelicula;

/**
 * Servlet implementation class ActualizarPelicula
 */
@WebServlet({ "/ActualizarPelicula", "/actualizarPelicula", "/actualizarpelicula", "/Actualizarpelicula" })
public class ActualizarPelicula extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ActualizarPelicula() {
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
		

		if( request.getParameter("bandera")==null ) {
		int codigo = Integer.parseInt(request.getParameter("codigo"));
		
		Pelicula peli = new Pelicula();
		LogicPelicula lp = new LogicPelicula();
		
		
		peli = lp.buscarPorCodigo(codigo);


		request.setAttribute("pelicula", peli);
		request.getRequestDispatcher("WEB-INF/Pelicula/Edit.jsp").forward(request, response);
		}
		
		if( !(request.getParameter("bandera")==null)) {
			if (request.getParameter("bandera").toString().equals("cambio") ) {
				
				String pelianterior = request.getParameter("pelianterior");
				
				Pelicula pelicambiada = new Pelicula();
				LogicPelicula lp = new LogicPelicula();
				
				
				Integer codigo = Integer.parseInt(request.getParameter("codigo"));
				
				String nombre = request.getParameter("nombre").toString();
				
				String genero = request.getParameter("genero").toString();
				
				String calificacion = request.getParameter("edad").toString();

				String sinopsis = request.getParameter("sinopsis").toString();

				String director = request.getParameter("director").toString();

				double duracion = Double.parseDouble(request.getParameter("duracion"));

				String portada = request.getParameter("portada").toString();
				
				String fecha1=request.getParameter("estreno");
				
				Date fecha;
				try {
					fecha = new SimpleDateFormat("yyyy-MM-dd").parse(fecha1);
					pelicambiada.setFecha_estreno(fecha);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  
				
			
				
				pelicambiada.setCodigo(codigo);
				pelicambiada.setNombre(nombre);
				pelicambiada.setGenero(genero);
				pelicambiada.setCalificacion(calificacion);
				pelicambiada.setSinopsis(sinopsis);
				pelicambiada.setDirector(director);
				pelicambiada.setDuracion(duracion);
				pelicambiada.setPortada(portada);
		
				
				if ( !(pelianterior.equals(pelicambiada.toString())) ) {
					
					lp.modificar(pelicambiada);
					String modificada = "modificada";
					request.setAttribute("modificada", modificada);
					request.getRequestDispatcher("Empleados.jsp").forward(request, response);
				
				} else if ( pelianterior.equals(pelicambiada.toString()) ){ 
					String nocambio = "nocambio";
					request.setAttribute("nocambio", nocambio);
					request.getRequestDispatcher("Empleados.jsp").forward(request, response);

				}
				
				
			}
			}
				
	}

}
