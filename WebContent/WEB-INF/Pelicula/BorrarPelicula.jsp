<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.LinkedList"%>
<%@page import="entities.Pelicula"%>
<%@page import="entities.Persona" %>
<%@page import="logic.LogicCalificacion"%>
<%@page import="entities.Calificacion"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<%@ include file="/Estilo.jsp" %>
	<title>BORRAR PELICULA</title>
	<%
	Integer isEmpleado = 0;
	Persona per = (Persona)request.getSession().getAttribute("usuario");
		
	if ( !(per==null)){
		isEmpleado = per.getHabilitado();
		} else { isEmpleado = 0;} 	 
	LinkedList<Pelicula> lp=(LinkedList<Pelicula>)request.getAttribute("peliculas");
	Pelicula peliculaEncontrada = (Pelicula)request.getAttribute("peliculaEncontrada");
	LogicCalificacion lc = new LogicCalificacion();
	%>
</head>
<body>
<div class="fondo">
	<jsp:include page="/BarraMenu.jsp" />
	<%if ( request.getAttribute("encontrada")==null ) { %>
		<br>
		<br>
		<%if ( request.getAttribute("Noencontrada")!=null ) { %>
        	<div class="alert alert-warning">No se encontro la pelicula seleccionada</div>
        <% } %>
        <% if (request.getAttribute("borrada")!=null) {%>
        	<div class="alert alert-success">¡Pelicula borrada con exito!</div>
    	<% }%>
		<h2>Seleccione una pelicula</h2>
		<form class="addPelicula" action="BuscarPelicula" method="get" >
			<div>
        		<label>Peliculas</label>
        		<select name="elegirpelicula" required="required">
        			<% for (Pelicula pel: lp){ %>
            			<option value="<%= pel.getCodigo() %>"><%= pel.getNombre() %></option>
            		<% } %>
        		</select>
    		</div>
    		<br>
   		 	<button class="btn btn-lg btn-primary btn-block" type="submit" >BUSCAR</button>
    	</form>
    <%} %>
  
    <%if ( request.getAttribute("encontrada")!=null ) {%>
    	<br>
		<br>
    	<br>
		<div class="pelicula">
			<div class="infopelicula">
				<img class="infopelicula" id="fotopelicula" src="<%=peliculaEncontrada.getPortada()%>"/>
				<p class="nombrepelicula" id="nombrepelicula" ><%= peliculaEncontrada.getNombre() %></p>
                <p class="sinopsis" id="sinopsis"><%= peliculaEncontrada.getSinopsis() %></p>
                <p class="infopelicula"> <b>Director :</b> <%= peliculaEncontrada.getDirector() %></p>
                <%
                 	Calificacion c = new Calificacion();
                    c.setCodigo_calificacion(peliculaEncontrada.getCodigo_calificacion());
                    Calificacion calif = lc.buscar(c);
                 %>
                <p class="infopelicula"> <b>Calificacion :</b> <%= calif.getDescripcion() %></p>
                <p class="infopelicula"> <b>Duracion :</b> <%= peliculaEncontrada.getDuracion() %></p>
                <form action="BorraPelicula" method="post">
                    <input type="hidden" name="codigoPel" value="<%=peliculaEncontrada.getCodigo() %>"/>
                    <br>
                    <button id="botonBorrar" class="buttonBorrar" >Borrar pelicula</button>
                </form>
            </div>
        </div>
    <%}%>
</div>
</body>
</html>