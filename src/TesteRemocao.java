import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TesteRemocao {

	public static void main(String[] args) throws SQLException {
		
		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection conn = connectionFactory.createConnection();
		
		Statement stm = conn.createStatement();
		
		stm.execute("DELETE FROM PRODUTO WHERE ID > 2");
		Integer numLinhasRemovidas = stm.getUpdateCount();
		
		System.out.println("Numero total de linhas removidas: " + numLinhasRemovidas);

	}

}
