package com.tomioka.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tomioka.jdbc.modelo.Produto;

/*
 * Classe do tipo Data Access Object da camada de persistência.
 * Cria um objeto de acesso aos dados presentes no MySQL.
 * Informações detalhadas: 
 * https://pt.stackoverflow.com/questions/113840/como-funciona-o-padr%C3%A3o-dao
 */

public class ProdutoDAO {

	private Connection conn;

	public ProdutoDAO(Connection conn) {
		this.conn = conn;
	}

	public void save(Produto prod) throws SQLException {

		conn.setAutoCommit(false);
		String sql = "INSERT INTO PRODUTO(nome, descricao, categoria_id) VALUES(?, ?, ?)";

		try (PreparedStatement pstm = conn.prepareStatement(sql, 
				Statement.RETURN_GENERATED_KEYS)) {

			pstm.setString(1, prod.getNome());
			pstm.setString(2, prod.getDescricao());
			pstm.setInt(3, prod.getCategoriaId());

			pstm.execute();

			conn.commit();

			try (ResultSet rs = pstm.getGeneratedKeys()) {
				while (rs.next()) {
					prod.setId(rs.getInt(1));
				}
			}
			System.out.println(prod);
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			System.out.println("ROLLBACK executado.");
		}
	}
	
	public List<Produto> list() throws SQLException {
		
		List<Produto> listProduto = new ArrayList<>();
		
		conn.setAutoCommit(false);
		String sql = "SELECT * FROM PRODUTO";
		try (PreparedStatement pstm = conn.prepareStatement(sql)) {
			pstm.execute();
			conn.commit();
			
			try (ResultSet rs = pstm.getResultSet()) {
				
				while (rs.next()) {
					Produto prod = new Produto(rs.getInt("id"), rs.getString("nome"),
							rs.getString("descricao"), rs.getInt("categoria_id"));
					listProduto.add(prod);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			System.out.println("ROLLBACK executado.");
		}
		return listProduto;
	}

}
