package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Entrada;
import logic.LogicEntrada;

/**
 * Servlet implementation class BorrarEntrada
 */
@WebServlet("/BorrarEntrada")
public class BorrarEntrada extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BorrarEntrada() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		LogicEntrada le = new LogicEntrada();
			
		Integer idEntrada = Integer.parseInt(request.getParameter("identrada"));
		Entrada entradaEncontrada = le.buscarPorId(idEntrada);
		le.borrar(entradaEncontrada);
		request.setAttribute("borrada", "borrada");
		request.getRequestDispatcher("MiCuenta.jsp").forward(request, response);
	}
}

