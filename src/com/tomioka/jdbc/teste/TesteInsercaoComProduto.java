package com.tomioka.jdbc.teste;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.tomioka.jdbc.ConnectionFactory;
import com.tomioka.jdbc.modelo.Produto;

public class TesteInsercaoComProduto {

	public static void main(String[] args) throws SQLException {

		try (Connection conn = new ConnectionFactory().createConnection()) {
			conn.setAutoCommit(false);

			String sql = "INSERT INTO PRODUTO(nome, descricao) VALUES(?, ?)";

			try (PreparedStatement pstm = conn.prepareStatement(sql, 
					Statement.RETURN_GENERATED_KEYS)) {

				Produto prod = new Produto("Leitor livro", "Kindle Amazon");

				pstm.setString(1, prod.getNome());
				pstm.setString(2, prod.getDescricao());

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

	}

}
