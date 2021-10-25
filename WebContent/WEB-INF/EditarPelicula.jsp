<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.time.*"%>
    <%@page import="java.time.format.*"%>
<%@page import="entities.Pelicula"%>
<%@page import="logic.LogicPelicula"%>
<%@page import="java.util.LinkedList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>---EDIT PELICULAS---</title>
        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <!-- Font Awesome icons (free version)-->
        <script src="https://use.fontawesome.com/releases/v5.15.3/js/all.js" ></script>
        <!-- Google fonts-->
        <link href="https://fonts.googleapis.com/css?family=Catamaran:100,200,300,400,500,600,700,800,900" rel="stylesheet" />
        <link href="https://fonts.googleapis.com/css?family=Lato:100,100i,300,300i,400,400i,700,700i,900,900i" rel="stylesheet" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="style/css/styles.css" rel="stylesheet" />
        <link rel="preconnect" href="https://fonts.googleapis.com">
		<link rel="preconnect" href="https://fonts.gstatic.com" >
		<link href="https://fonts.googleapis.com/css2?family=Bebas+Neue&display=swap" rel="stylesheet">

<%  
	
	String bandera1 = "";
	
	 if ( !(request.getAttribute("bandera1")==(null)) ){
		 bandera1 = request.getAttribute("bandera1").toString();
	}
	 
		 LinkedList<Pelicula> lp=(LinkedList<Pelicula>)request.getAttribute("peliculas");
	
	 
    %>
</head>
<body>
<div>
	<%
	if ( request.getAttribute("bandera1")==(null) ){
	%>
	
	
	
	
	<h2>Ingrese nombre de la pelicula</h2>
	<label for="inputNombre" >Nombre de la pelicula</label>
	<form class="addPelicula" action="BuscarPelicula" method="post" >
    <input id="inputNombre" name="nombre" class="form-control" placeholder="nombre de la pelicula" required type="text">
   
    
    <button class="btn btn-lg btn-primary btn-block" >BUSCAR</button>

    </form>
    
    
    
    
	<%} %>
	
	
	
	 <% if (!(request.getAttribute("bandera1")==(null)) && bandera1.equals("encontrada")){  %>
	
		<h2> Datos de la pelicula: </h2>
		
			<% for (Pelicula pel : lp){ %>
			
			<div class="peliculas">
							<p class="infopelicula" id="nombrepelicula"><%= pel.getNombre() %></p>
                            <p class="infopelicula" id="sinopsis"><%= pel.getSinopsis() %></p>
                            <p class="infopelicula"> <b>Director :</b> <%= pel.getDirector() %></p>
                            <p class="infopelicula"> <b>Calificacion :</b> <%= pel.getCalificacion() %></p>
                            <p class="infopelicula"> <b>Duracion :</b> <%= pel.getDuracion() %></p>
                            <button class="pelicula" id="editar">  Editar   </button>
              </div>
	<%}}%> 
	
	

</div>
</body>
</html>