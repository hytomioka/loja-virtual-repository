import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TesteListagem {

	public static void main(String[] args) throws SQLException {
		// database determinada na URL
		
		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection conn = connectionFactory.createConnection();
		
		/* Query a partir do execute
		// criar um statement a partir de um comando SQL
		Statement stm = conn.createStatement();
		
		// excutar um statement
		stm.execute("SELECT * FROM PRODUTO");
		*/
		
		/* Query a partir do preparedStatement */
		PreparedStatement pstm = conn.prepareStatement("SELECT * FROM PRODUTO");
		pstm.execute();
		
		// retorna a execucao e armazena as linhas numa lista
		ResultSet rs = pstm.getResultSet();

		while (rs.next()) {
			Integer id = rs.getInt("id");
			String nome = rs.getString("nome");
			String descricao = rs.getString("descricao");
			System.out.print(id);
			System.out.print(" - " + nome + " - ");
			System.out.println(descricao);
		}
		
		conn.close();

	}

}
