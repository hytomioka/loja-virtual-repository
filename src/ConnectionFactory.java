import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/* 
 * Classe destinada a criação de uma conexao
 * Atualização (03-02-21):
 * Implementação de uma Pool de conexões (c3p0), juntamente com o DataSource.
 * Uma pool de conexões possibilita o controle de multiplas conexões com o banco de dados.
 */
public class ConnectionFactory {

	public DataSource dataSource;

	public ConnectionFactory() {
		ComboPooledDataSource cpds = new ComboPooledDataSource();
		cpds.setJdbcUrl("jdbc:mysql://localhost/loja_virtual?useTimezone=true&serverTimezone=UTC");
		cpds.setUser("root");
		cpds.setPassword("21122002");

		this.dataSource = cpds;
	}

	public Connection createConnection() throws SQLException {
		return this.dataSource.getConnection();
	}

}
