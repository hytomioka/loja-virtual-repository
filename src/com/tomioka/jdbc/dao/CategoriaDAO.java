package com.tomioka.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tomioka.jdbc.modelo.Categoria;

public class CategoriaDAO {
	
	private Connection conn;
	
	public CategoriaDAO(Connection conn) {
		this.conn = conn;
	}
	
	public List<Categoria> list() throws SQLException {

		List<Categoria> listaCategoria = new ArrayList<>();
		conn.setAutoCommit(false);
		String sql = "SELECT * FROM CATEGORIA";
		
		try (PreparedStatement pstm = conn.prepareStatement(sql)) {
			
			pstm.execute();
			conn.commit();
			
			try (ResultSet rs = pstm.getResultSet()) {
				
				while (rs.next()) {
					Categoria cat = new Categoria(
							rs.getInt("id"), rs.getString("nome"));
					listaCategoria.add(cat);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			System.out.println("ROLLBACK executado");
		}
		return listaCategoria;
	}
}

