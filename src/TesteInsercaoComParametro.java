import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TesteInsercaoComParametro {
	
	public static void main(String[] args) throws SQLException {
		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection conn = connectionFactory.createConnection();
		
		String nome = "Mouse";
		String descricao = "Razer Abyssus";
		
		/* O prepareStatement é um método que consegue tratar o comando SQL de forma a evitar
		 * erros do usuário, assim como evitar SQL injections.
		 * A consulta neste método é compilada no banco de dados */
		PreparedStatement stm = conn.prepareStatement(
				"INSERT INTO PRODUTO(nome, descricao) VALUES(?, ?)",
				Statement.RETURN_GENERATED_KEYS);
		
		stm.setString(1, nome);
		stm.setString(2, descricao);
		
		stm.execute();
		
		ResultSet rs = stm.getGeneratedKeys();
		
		while(rs.next()) {
			Integer id = rs.getInt(1);
			System.out.println("id= " + id + " adicionado");
		}
		
		conn.close();
	}

}
