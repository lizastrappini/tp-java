<%@page import="entities.Pelicula"%>
<%@page import="entities.Funcion"%>
<%@page import="java.util.LinkedList"%>
<%@page import="java.time.LocalDate" %>
<%@page import="java.time.Month" %>
<%@page import="java.util.Locale" %>
<%@page import="entities.Persona" %>
<%@page import="entities.Calificacion" %>
<%@page import="logic.LogicCalificacion" %>
<%@page import="logic.LogicFuncion" %>
<%@page import="java.time.format.TextStyle" %>
<%@page import="java.time.ZoneId" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/Estilo.jsp" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <title>---CINE---</title>
        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <link href="style/css/styles.css" rel="stylesheet" />
        <script src="Javascript/SweetAlert.js"></script>
		<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
		<script src="Javascript/Script.js"></script>
		<% 
		LogicFuncion lf = new LogicFuncion();
		LinkedList <Funcion> listafun= lf.listar();
		
		Month mes = LocalDate.now(ZoneId.of("America/Argentina/Buenos_Aires")).getMonth(); //obtengo el mes
		String nombre = mes.getDisplayName(TextStyle.FULL, new Locale("es", "ES")).toUpperCase();  // convierto a mayusculas
		LinkedList<Pelicula> lp=(LinkedList<Pelicula>)request.getAttribute("listapeliculas");
		LogicCalificacion lc = new LogicCalificacion();
	
		%>
   </head>

<body>

<div class="fondo" >
	<jsp:include page="/BarraMenu.jsp" />
		
	<br>
	<br>
	<br>
<!-- titulo si seleccionamos estrenos -->

<%if( !(request.getAttribute("estrenos")==null) ){%> 

	<% if ( lp==null || lp.isEmpty()){ %>
	<script type="text/javascript">
	Swal.fire({
		  imageUrl: 'https://images.emojiterra.com/twitter/v13.1/512px/2639.png',
		  imageHeight: 100,
		  text: 'No hay estrenos este mes',
		  timer: 2000,
		  timerProgressBar: true,
		  allowOutsideClick: false,
		  didOpen: () => {
			    Swal.showLoading()
			    const b = Swal.getHtmlContainer().querySelector('b')
			    timerInterval = setInterval(() => {
			      b.textContent = Swal.getTimerLeft()
			    }, 100)
			  },
		})
		setTimeout( function() { window.location.href = "index.jsp"; }, 2000 );
	</script>
	
	<%} else {%>
	<h1 class="titulo_estrenos"> ESTRENOS DEL MES DE <%=nombre %></h1>
	
	<% }} %>

<% if ( !(lp.isEmpty()) ) { for (Pelicula pel : lp){ %>
	  <!-- Contenido peliculas-->
        <div class="pelicula">
          					<img class="infopelicula" id="fotopelicula" src="<%=pel.getPortada()%>" alt="Not Found"  />
                            <h2 class="infopelicula" id="nombrepelicula"><%=pel.getNombre() %></h2>
                            <p class="infopelicula" id="sinopsis"><%=pel.getSinopsis() %></p>
                            <p class="infopelicula"> <b>Director :</b> <%=pel.getDirector() %></p>
                            <% Calificacion c = new Calificacion();
                            c=  lc.buscarPorCodigo(pel.getCodigo_calificacion());%>
                            <p class="infopelicula"> <b>Calificacion : </b><%=c.getDescripcion()%></p>
                            <p class="infopelicula"> <b>Duracion : </b><%=(int)pel.getDuracion() %> min</p> 
                           
                            <form class="infopelicula" action="Funciones" method="post">
                        <!-- ACA SE ELIGE LA FECHA EN LA QUE QUIERO CONSULTAR LAS FUNCIONES --> 
                        	 
						     <label> Elegir Fecha:</label>
						    		<select name="elegirFecha" required="required">
						        		<% for (Funcion f: listafun){ %>
						        			<% if (f.getCodigo_pelicula() == pel.getCodigo()){ %>
						        				<%String fecha= (f.getFecha_hora().toString().split("T")[0]); %>
						        					<option value="<%= f.getFecha_hora() %>" selected="selected"><%=fecha %></option>
						            		<% } %>
						            	<% } %>
						    		</select> 	
                            <input type="hidden" name="codigo" value="<%= pel.getCodigo() %>">
                            <button class="buttonClass" id="">  Comprar   </button>
                            </form>
                            
                            <form class="infopelicula" action="MostrarComentarios" method="get">
                            <input type="hidden" name="codigo" value="<%= pel.getCodigo() %>">
                            <button class="buttonClass" id="">  Ver Detalles   </button>
                            </form>
                            
                            
                            
        </div>
        
	<%} }%>
	<a class="boton_volver" href="Peliculas.jsp">VOLVER </a>
	<% if (lp.isEmpty() && request.getAttribute("estrenos")==null){ %>
	<script type="text/javascript">
	Swal.fire({
		  imageUrl: 'https://images.emojiterra.com/twitter/v13.1/512px/2639.png',
		  imageHeight: 100,
		  text: 'No hay peliculas que coincidan con la busqueda',
		  timer: 2000,
		  timerProgressBar: true,
		  allowOutsideClick: false,
		  didOpen: () => {
			    Swal.showLoading()
			    const b = Swal.getHtmlContainer().querySelector('b')
			    timerInterval = setInterval(() => {
			      b.textContent = Swal.getTimerLeft()
			    }, 100)
			  },
		})
		setTimeout( function() { window.location.href = "Peliculas.jsp"; }, 2000 );
		</script>

	<%} %>
<br>
<br>
</div>	


</body>
</html>