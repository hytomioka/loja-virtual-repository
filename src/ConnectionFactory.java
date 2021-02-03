import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/* Classe destinada a criação de uma conexao*/
public class ConnectionFactory {
	
	public Connection createConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://localhost/loja_virtual?useTimezone=true&serverTimezone=UTC",
				"root", "21122002");
	}

}
