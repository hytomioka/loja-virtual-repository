import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TesteInsercaoComParametro {

	public static void main(String[] args) throws SQLException {
		ConnectionFactory connectionFactory = new ConnectionFactory();
		/*
		 * O try(recurso) possibilita o fechamento (close()) automático da conexão ou do
		 * statement, já que estas interfaces herdam o AutoClosable
		 */
		try (Connection conn = connectionFactory.createConnection()) {

			/*
			 * Aplicação do conceito de transação, no qual, a transação será efetuada
			 * somente se, tudo o que estava na transação foi realizado com sucesso. Link
			 * para consulta:
			 * https://pt.stackoverflow.com/questions/203857/o-que-%c3%a9-acid-em-banco-de-
			 * dados AutoCommit em false não realiza o commit da transação automaticamente
			 */
			conn.setAutoCommit(false);

			/*
			 * O prepareStatement é um método que consegue tratar o comando SQL de forma a
			 * evitar erros do usuário, assim como evitar SQL injections. A consulta neste
			 * método é compilada no banco de dados
			 */
			try (PreparedStatement pstm = conn.prepareStatement("INSERT INTO PRODUTO(nome, descricao) VALUES(?, ?)",
					Statement.RETURN_GENERATED_KEYS)) {

				addVariable("Mouse", "Razer Abyssus", pstm);
				addVariable("Teclado", "Logitech", pstm);

				conn.commit();
			} catch (Exception e) {
				e.printStackTrace();
				/* ROLLBACK volta o estado original do banco de dados */
				System.out.println("ROLLBACK executado.");
				conn.rollback();
			}
		}
	}

	private static void addVariable(String nome, String descricao, PreparedStatement pstm) throws SQLException {
		pstm.setString(1, nome);
		pstm.setString(2, descricao);

//		/* forçando erro
		if (nome.equals("Teclado")) {
			throw new RuntimeException("Não foi possivel adicionar o produto.");
		}
//		*/

		pstm.execute();
		
		/*
		 * ResultSet é uma interface que também possui o recurso de AutoClosable
		 */
		try (ResultSet rs = pstm.getGeneratedKeys()) {

			while (rs.next()) {
				Integer id = rs.getInt(1);
				System.out.println("id= " + id + " adicionado");
			}
		}
	}
}
