package com.tomioka.jdbc.teste;
import java.sql.SQLException;

import com.tomioka.jdbc.ConnectionFactory;

public class TestePoolConexao {

	public static void main(String[] args) throws SQLException {
		
		ConnectionFactory cf = new ConnectionFactory();
		
		for (int  i=0; i<15; i++) {
			cf.createConnection();
			System.out.println("criando conexao de numero: "+ i);
		}

	}

}
