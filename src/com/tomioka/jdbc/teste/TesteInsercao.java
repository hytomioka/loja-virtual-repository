package com.tomioka.jdbc.teste;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.tomioka.jdbc.ConnectionFactory;

public class TesteInsercao {

	public static void main(String[] args) throws SQLException {

		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection conn = connectionFactory.createConnection();

		Statement stm = conn.createStatement();

		// execute retorna false, j� que esta query n�o retorna uma ResultSet
		stm.execute("INSERT INTO PRODUTO(NOME, DESCRICAO) VALUES('celular', 'iPhone')",
				Statement.RETURN_GENERATED_KEYS);

		ResultSet rs = stm.getGeneratedKeys();
		while (rs.next()) {
			Integer id = rs.getInt(1);
			System.out.println("novo id criado: " + id);
		}

	}

}
