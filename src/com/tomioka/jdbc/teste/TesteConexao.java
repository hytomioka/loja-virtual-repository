package com.tomioka.jdbc.teste;
import java.sql.Connection;
import java.sql.SQLException;

import com.tomioka.jdbc.ConnectionFactory;

public class TesteConexao {

	public static void main(String[] args) throws SQLException {
		
		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection conn = connectionFactory.createConnection();
		
		System.out.println("Fechando conexao.");
		
		conn.close();
		
	}

}
