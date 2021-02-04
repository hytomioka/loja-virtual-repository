package com.tomioka.jdbc;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/* 
 * Classe destinada a cria��o de uma conexao
 * Link c3p0: https://www.mchange.com/projects/c3p0/
 * Atualiza��o (03-02-21):
 * Implementa��o de uma Pool de conex�es (c3p0), juntamente com o DataSource.
 * Uma pool de conex�es possibilita a abertura de multiplas conex�es com o banco de dados,
 * no qual cada conex�o criada poder� ser reaproveitada no momento que estiver aberta.
 * O conjunto de conex�es pode ter tamanho fixo ou din�mico.
 */
public class ConnectionFactory {

	public DataSource dataSource;

	public ConnectionFactory() {
		ComboPooledDataSource cpds = new ComboPooledDataSource();
		cpds.setJdbcUrl("jdbc:mysql://localhost/loja_virtual?useTimezone=true&serverTimezone=UTC");
		cpds.setUser("root");
		cpds.setPassword("21122002");
		
		// recomenda��o de MinPoolSize <= 3
		cpds.setMinPoolSize(3);
		cpds.setMaxPoolSize(10);
		

		this.dataSource = cpds;
	}

	public Connection createConnection() throws SQLException {
		return this.dataSource.getConnection();
	}

}
