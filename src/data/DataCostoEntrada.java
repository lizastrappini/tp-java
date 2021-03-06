package data;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import entities.CostoEntrada;

public class DataCostoEntrada {
	
	public void cargarCostoEntrada(CostoEntrada nuevoCostoEntrada) {
		PreparedStatement stmt=null;
		ResultSet rs = null;
		
		try {						
			stmt = DbConnector.getInstancia().getConn().prepareStatement("insert into costo_entrada"
					+ "(fecha_desde, costo) values (current_date(),?)");
			
			stmt.setDouble(1, nuevoCostoEntrada.getCosto());
			
			stmt.executeUpdate();
			
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
	
	public LinkedList<CostoEntrada>listarCostosEntradas(){
		Statement stmt= null;
		ResultSet rs= null;
		LinkedList<CostoEntrada>costos_entradas= new LinkedList<>();
		try {
			stmt= DbConnector.getInstancia().getConn().createStatement();
			rs= stmt.executeQuery("Select fecha_desde, costo from costo_entrada");
			
			if (rs !=null) {
				while (rs.next()) {
					CostoEntrada ce = new CostoEntrada();
					ce.setFecha_desde(rs.getObject("fecha_desde",Date.class));
					ce.setCosto(rs.getDouble("costo"));
					costos_entradas.add(ce);
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
		return costos_entradas;
	}
	
	public CostoEntrada buscarCostoActual() {
		ResultSet rs = null;
		PreparedStatement stmt=null;
		CostoEntrada costo = null;
		
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement("call tp_java_cine.obtener_costo_actual();");
			rs = stmt.executeQuery();
			
			if(rs != null && rs.next()) {
				costo = new CostoEntrada();
				costo.setFecha_desde(rs.getObject("u.ultima", Date.class));
				costo.setCosto(rs.getDouble("ce.costo"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (rs!= null ) {rs.close();}
				if(stmt != null ) {stmt.close();}
			DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return costo;

	}
	
}
