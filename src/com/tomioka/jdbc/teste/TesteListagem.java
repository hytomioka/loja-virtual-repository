package com.tomioka.jdbc.teste;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.tomioka.jdbc.ConnectionFactory;
import com.tomioka.jdbc.dao.ProdutoDAO;
import com.tomioka.jdbc.modelo.Produto;

public class TesteListagem {

	public static void main(String[] args) throws SQLException {
		// database determinada na URL

		ConnectionFactory connectionFactory = new ConnectionFactory();
		try (Connection conn = connectionFactory.createConnection()) {
			
			ProdutoDAO prodDao = new ProdutoDAO(conn);
			List<Produto> listaProduto = prodDao.list();
			
//			Iterator<Produto> it = listaProduto.iterator();
//			while (it.hasNext()) {
//				Produto p = it.next();
//				System.out.println(p);
//			}
			
			// Iteração da lista sem utilizar o Iterator
			listaProduto.stream().forEach(lp -> System.out.println(lp));
			

			/*
			 * Query a partir do execute
			 * Criar um statement a partir de um comando SQL
			 * Statement stm = conn.createStatement();
			 * 
			 * Excutar um statement stm.execute("SELECT * FROM PRODUTO");
			 */

			/* Query a partir do preparedStatement */
//			try (PreparedStatement pstm = conn.prepareStatement("SELECT * FROM PRODUTO")) {
//				pstm.execute();
//
//				// retorna a execucao e armazena as linhas numa lista
//				try (ResultSet rs = pstm.getResultSet()) {
//
//					while (rs.next()) {
//						Integer id = rs.getInt("id");
//						String nome = rs.getString("nome");
//						String descricao = rs.getString("descricao");
//						System.out.print(id);
//						System.out.print(" - " + nome + " - ");
//						System.out.println(descricao);
//					}
//				}
//			}
		}
	}
}
