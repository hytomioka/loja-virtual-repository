import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TesteRemocao {

	public static void main(String[] args) throws SQLException {

		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection conn = connectionFactory.createConnection();

		/*
		 * Statement stm = conn.createStatement();
		 * 
		 * stm.execute("DELETE FROM PRODUTO WHERE ID > 2");
		 */

		try (PreparedStatement pstm = conn.prepareStatement("DELETE FROM PRODUTO WHERE ID > ?")) {

			pstm.setInt(1, 2);
			pstm.execute();

			Integer numLinhasRemovidas = pstm.getUpdateCount();

			pstm.close();

			System.out.println("Numero total de linhas removidas: " + numLinhasRemovidas);

		}
	}
}
