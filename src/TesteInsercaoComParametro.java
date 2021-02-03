import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TesteInsercaoComParametro {

	public static void main(String[] args) throws SQLException {
		ConnectionFactory connectionFactory = new ConnectionFactory();
		/*
		 * O try(recurso) possibilita o fechamento (close()) autom�tico da conex�o ou do
		 * statement, j� que estas interfaces herdam o AutoClosable
		 */
		try (Connection conn = connectionFactory.createConnection()) {

			/*
			 * Aplica��o do conceito de transa��o, no qual, a transa��o ser� efetuada
			 * somente se, tudo o que estava na transa��o foi realizado com sucesso. Link
			 * para consulta:
			 * https://pt.stackoverflow.com/questions/203857/o-que-%c3%a9-acid-em-banco-de-
			 * dados AutoCommit em false n�o realiza o commit da transa��o automaticamente
			 */
			conn.setAutoCommit(false);

			/*
			 * O prepareStatement � um m�todo que consegue tratar o comando SQL de forma a
			 * evitar erros do usu�rio, assim como evitar SQL injections. A consulta neste
			 * m�todo � compilada no banco de dados
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

		/* for�ando erro
		if (nome.equals("Teclado")) {
			throw new RuntimeException("N�o foi possivel adicionar o produto.");
		}
		*/

		pstm.execute();
		
		/*
		 * ResultSet � uma interface que tamb�m possui o recurso de AutoClosable
		 */
		try (ResultSet rs = pstm.getGeneratedKeys()) {

			while (rs.next()) {
				Integer id = rs.getInt(1);
				System.out.println("id= " + id + " adicionado");
			}
		}
	}
}
