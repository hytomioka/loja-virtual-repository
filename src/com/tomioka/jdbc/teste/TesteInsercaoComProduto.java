package com.tomioka.jdbc.teste;

import java.sql.Connection;
import java.sql.SQLException;

import com.tomioka.jdbc.ConnectionFactory;
import com.tomioka.jdbc.dao.ProdutoDAO;
import com.tomioka.jdbc.modelo.Produto;

/*
 * Lembrete:
 * Query de update de PRODUTO no MySQL command line:
 * UPDATE PRODUTO SET "COLUMN" = ? WHERE ID = ?  
 * 
 */

public class TesteInsercaoComProduto {

	public static void main(String[] args) throws SQLException {

		try (Connection conn = new ConnectionFactory().createConnection()) {
			ProdutoDAO prodDao = new ProdutoDAO(conn);
			Produto prod = new Produto("Fogão", "Brastemp");
			prod.setCategoriaId(2);
			prodDao.save(prod);
		}

	}
}