package com.tomioka.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tomioka.jdbc.modelo.Categoria;
import com.tomioka.jdbc.modelo.Produto;

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
			System.out.println("ROLLBACK executado.");
		}
		System.out.println("Query de listagem de Categoria executada.");
		return listaCategoria;
	}
	
	public List<Produto> search(Categoria cat) throws SQLException {
		List<Produto> listaProdPorCat = new ArrayList<>();
		conn.setAutoCommit(false);
		
		String sql = "SELECT * FROM PRODUTO WHERE categoria_id = ?";
		try (PreparedStatement pstm = conn.prepareStatement(sql)) {
			
			pstm.setInt(1, cat.getId());
			pstm.execute();
			
			try (ResultSet rs = pstm.getResultSet()) {
				while (rs.next()) {
					Produto p = new Produto(
							rs.getInt("id"), rs.getString("nome"),
							rs.getString("descricao"), rs.getInt("categoria_id"));
					listaProdPorCat.add(p);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			System.out.println("ROLLBACK executado.");
		}
		System.out.println("Query de busca de Produtos por Categoria executada");
		return listaProdPorCat;
	}
	
	public List<Categoria> listCatProd() throws SQLException {
		
		Categoria avoidRepeat = null;
		List<Categoria> listCat = new ArrayList<>();
		
		conn.setAutoCommit(false);
		String sql = "SELECT C.ID, C.NOME, P.ID, P.NOME, P.DESCRICAO FROM CATEGORIA C "
				+ "INNER JOIN PRODUTO P "
				+ "ON C.ID = P.CATEGORIA_ID";
		
		try (PreparedStatement pstm = conn.prepareStatement(sql)) {
			pstm.execute();
			conn.commit();
			
			try (ResultSet rs = pstm.getResultSet()) {
				
				while (rs.next()) {
					Categoria cat = new Categoria(rs.getInt(1), rs.getString(2));
					if (avoidRepeat == null || !cat.getNome().equals(avoidRepeat.getNome())) {
						avoidRepeat = cat;
						listCat.add(cat);
					}
					Produto prod = new Produto(rs.getInt(3), rs.getString(4), rs.getString(5));
					avoidRepeat.add(prod);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			System.out.println("ROLLBACK executado.");
		}
		return listCat;
	}
	
}

