import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TesteListagem {

	public static void main(String[] args) throws SQLException {
		// database determinada na URL
		
		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection conn = connectionFactory.createConnection();
		
		// criar um statement a partir de um comando SQL
		Statement stm = conn.createStatement();
		
		// excutar um statement
		stm.execute("SELECT * FROM PRODUTO");
		
		// retorna a execucao e armazena as linhas numa lista
		ResultSet rs = stm.getResultSet();

		while (rs.next()) {
			Integer id = rs.getInt("id");
			System.out.print(id);
			String nome = rs.getString("nome");
			System.out.print(" - " + nome + " - ");
			String descricao = rs.getString("descricao");
			System.out.println(descricao);
		}
		
		conn.close();

	}

}
