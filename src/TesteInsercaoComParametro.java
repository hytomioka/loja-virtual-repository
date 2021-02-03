import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TesteInsercaoComParametro {
	
	public static void main(String[] args) throws SQLException {
		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection conn = connectionFactory.createConnection();
		
		/* AutoCommit em false n�o realiza o commit das queries automaticamente */
		conn.setAutoCommit(false);
		
		/* O prepareStatement � um m�todo que consegue tratar o comando SQL de forma a evitar
		 * erros do usu�rio, assim como evitar SQL injections.
		 * A consulta neste m�todo � compilada no banco de dados */
		PreparedStatement pstm = conn.prepareStatement(
				"INSERT INTO PRODUTO(nome, descricao) VALUES(?, ?)",
				Statement.RETURN_GENERATED_KEYS);
		
		addVariable("Mouse", "Razer Abyssus", pstm);
		addVariable("Teclado", "Logitech", pstm);
		
		conn.close();
	}

	private static void addVariable(String nome, String descricao, PreparedStatement pstm)
			throws SQLException {
		pstm.setString(1, nome);
		pstm.setString(2, descricao);
		
		/* for�ando erro
		if (nome.equals("Teclado")) {
			throw new RuntimeException();
		}
		*/
		
		pstm.execute();
		
		ResultSet rs = pstm.getGeneratedKeys();
		
		while(rs.next()) {
			Integer id = rs.getInt(1);
			System.out.println("id= " + id + " adicionado");
		}
	}
}
