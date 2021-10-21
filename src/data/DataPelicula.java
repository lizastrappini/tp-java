package data;

import java.sql.*;
import java.util.LinkedList;



import entities.Pelicula;

public class DataPelicula {

		public void cargarPelicula(Pelicula nuevaPeli) {
		PreparedStatement stmt=null;
		ResultSet rs = null;
		
		try {						
			stmt = DbConnector.getInstancia().getConn().prepareStatement("insert into pelicula"
					+ "(nombre, director, genero, calificacion, duracion, sinopsis, portada) values (?,?,?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			
			stmt.setString(1, nuevaPeli.getNombre());
			stmt.setString(2, nuevaPeli.getDirector());
			stmt.setString(3, nuevaPeli.getGenero());
			stmt.setString(4, nuevaPeli.getCalificacion());
			stmt.setDouble(5, nuevaPeli.getDuracion());
			stmt.setString(6, nuevaPeli.getSinopsis());
			stmt.setString(7, nuevaPeli.getPortada());
			
			stmt.executeUpdate();
			
			rs = stmt.getGeneratedKeys();
			
			if(rs != null && rs.next()) {
				nuevaPeli.setCodigo(rs.getInt(1));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			try {
				if(rs!=null) {rs.close(); }
				if(stmt!=null) {stmt.close();}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
	}

	public LinkedList<Pelicula>listarPeliculas(){
		Statement stmt= null;
		ResultSet rs= null;
		LinkedList<Pelicula>peliculas= new LinkedList<>();
		try {
			stmt= DbConnector.getInstancia().getConn().createStatement();
			rs= stmt.executeQuery("Select codigo, nombre, director, genero,calificacion, duracion, sinopsis, portada from pelicula");
			if (rs !=null) {
				while (rs.next()) {
					Pelicula p = new Pelicula();
					p.setCodigo(rs.getInt("codigo"));
					p.setNombre(rs.getString("nombre"));
					p.setDirector(rs.getString("director"));
					p.setGenero(rs.getString("genero"));
					p.setCalificacion(rs.getString("calificacion"));
					p.setDuracion(rs.getDouble("duracion"));
					p.setSinopsis(rs.getString("sinopsis"));
					p.setPortada(rs.getString("portada"));
					
					peliculas.add(p);
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
			DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return peliculas;
	}
	public Pelicula buscarPelicula (Pelicula p) {
		ResultSet rs=null;
		PreparedStatement stmt=null;
		Pelicula peli = null;
		
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select codigo,nombre,director,genero,calificacion,duracion,sinopsis from pelicula WHERE UPPER(nombre) = UPPER(?) and UPPER(director) = UPPER(?) "
					);
			//stmt = DbConnector.getInstancia().getConn().prepareStatement("select * from pelicula where nombre = ?");
			stmt.setString(1, p.getNombre());
			stmt.setString(1, p.getDirector());
			rs = stmt.executeQuery();
			
			if(rs!=null && rs.next()) {
				peli = new Pelicula();
				peli.setCodigo(rs.getInt("codigo"));
				peli.setNombre(rs.getString("nombre"));
				peli.setDirector(rs.getString("director"));
				peli.setGenero(rs.getString("genero"));
				peli.setCalificacion(rs.getString("calificacion"));
				peli.setDuracion(rs.getDouble("duracion"));
				peli.setSinopsis(rs.getString("sinopsis"));
			}
			
		}  catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
			DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
				
			}
		}
		
		return peli;
		
	}

	public void modificar(Pelicula peli) {
		PreparedStatement stmt=null;
		try {
			stmt= DbConnector.getInstancia().getConn().prepareStatement("update pelicula "
					+ "set nombre=?, director=?, genero=?, calificacion=?, duracion=?, sinopsis=? where codigo=?");
		
			stmt.setString(1, peli.getNombre());
			stmt.setString(2, peli.getDirector());
			stmt.setString(3, peli.getGenero());
			stmt.setString(4, peli.getCalificacion());
			stmt.setDouble(5, peli.getDuracion());
			stmt.setString(6, peli.getSinopsis());
			stmt.setInt(7, peli.getCodigo());
			stmt.executeUpdate();	
		} catch (SQLException e) {
			
		}finally {
			try {
				if(stmt!=null) {stmt.close();}
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}	
	public void borrarPelicula(Pelicula borraPeli) {
		PreparedStatement stmt=null;
		
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement("delete from pelicula where codigo = ?");
			stmt.setInt(1, borraPeli.getCodigo());
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(stmt!=null)stmt.close();
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public LinkedList<Pelicula> buscarPorGenero (String genero) {
		ResultSet rs=null;
		PreparedStatement stmt=null;
		LinkedList<Pelicula>peliculas= new LinkedList<>();
		
		try {
		
			stmt = DbConnector.getInstancia().getConn().prepareStatement("select * from pelicula where genero = ? ");   
			stmt.setString(1, genero);
			
			rs = stmt.executeQuery();
			
			if(rs!=null ) {
				while (rs.next()) {
					Pelicula p = new Pelicula();
					p.setCodigo(rs.getInt("codigo"));
					p.setNombre(rs.getString("nombre"));
					p.setDirector(rs.getString("director"));
					p.setGenero(rs.getString("genero"));
					p.setCalificacion(rs.getString("calificacion"));
					p.setDuracion(rs.getDouble("duracion"));
					p.setSinopsis(rs.getString("sinopsis"));
					p.setPortada(rs.getString("portada"));
					
					
					peliculas.add(p);
				}}
			
		}  catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
			DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return peliculas; 
	}
	
	public LinkedList<Pelicula> buscarPorEdad (String edad) {
		ResultSet rs=null;
		PreparedStatement stmt=null;
		LinkedList<Pelicula>peliculas= new LinkedList<>();
		
		try {
		
			stmt = DbConnector.getInstancia().getConn().prepareStatement("select * from pelicula where calificacion = ? ");   
			stmt.setString(1, edad);
			
			rs = stmt.executeQuery();
			
			if(rs!=null ) {
				while (rs.next()) {
					Pelicula p = new Pelicula();
					p.setCodigo(rs.getInt("codigo"));
					p.setNombre(rs.getString("nombre"));
					p.setDirector(rs.getString("director"));
					p.setGenero(rs.getString("genero"));
					p.setCalificacion(rs.getString("calificacion"));
					p.setDuracion(rs.getDouble("duracion"));
					p.setSinopsis(rs.getString("sinopsis"));
					p.setPortada(rs.getString("portada"));
					
					
					peliculas.add(p);
				}}
			
		}  catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
			DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return peliculas; 
	}
	
	public LinkedList<Pelicula> listarEstrenos () {
		ResultSet rs=null;
		PreparedStatement stmt=null;
		LinkedList<Pelicula>peliculas= new LinkedList<>();
		
		try {
		
			stmt = DbConnector.getInstancia().getConn().prepareStatement("select * from pelicula where month(fecha_estreno) = month(current_date()) ");   
			rs = stmt.executeQuery();
			
			if(rs!=null ) {
				while (rs.next()) {
					Pelicula p = new Pelicula();
					p.setCodigo(rs.getInt("codigo"));
					p.setNombre(rs.getString("nombre"));
					p.setDirector(rs.getString("director"));
					p.setGenero(rs.getString("genero"));
					p.setCalificacion(rs.getString("calificacion"));
					p.setDuracion(rs.getDouble("duracion"));
					p.setSinopsis(rs.getString("sinopsis"));
					p.setPortada(rs.getString("portada"));
					
					
					peliculas.add(p);
				}}
			
		}  catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
			DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return peliculas; 
	}
	
	
	public int buscarCodigo (Pelicula p) {
		int cod=0;
		ResultSet rs=null;
		PreparedStatement stmt=null;
		
		
		try {
			stmt=DbConnector.getInstancia().getConn().prepareStatement(
					"select codigo from pelicula where nombre=? and director=?"
					);
			//stmt = DbConnector.getInstancia().getConn().prepareStatement("select * from pelicula where nombre = ?");
			stmt.setString(1, p.getNombre());
			stmt.setString(1, p.getDirector());
			
			rs = stmt.executeQuery();
			
			if(rs!=null /*&& rs.next()*/) {
				cod = (rs.getInt("codigo"));
				
			}
			
		}  catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
			DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
				
			}
		}
		
		return cod;
		
	}
	
	
	public Pelicula buscarPorNombre (String nombre) {
		ResultSet rs=null;
		PreparedStatement stmt=null;
		//LinkedList<Pelicula>peliculas= new LinkedList<>();
		Pelicula p = new Pelicula();
		
		try {
		
			stmt = DbConnector.getInstancia().getConn().prepareStatement("select * from pelicula where nombre = ? ");   
			stmt.setString(1, nombre);
			
			rs = stmt.executeQuery();
			
			if(rs!=null ) {
				while (rs.next()) {
					
					p.setCodigo(rs.getInt("codigo"));
					p.setNombre(rs.getString("nombre"));
					p.setDirector(rs.getString("director"));
					p.setGenero(rs.getString("genero"));
					p.setCalificacion(rs.getString("calificacion"));
					p.setDuracion(rs.getDouble("duracion"));
					p.setSinopsis(rs.getString("sinopsis"));
					p.setPortada(rs.getString("portada"));
					
					
					
				}}
			
		}  catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) {rs.close();}
				if(stmt!=null) {stmt.close();}
			DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return p; 
	}
	
	
	
	
}
