package com.tomioka.jdbc.teste;

import java.sql.Connection;
import java.sql.SQLException;

import com.tomioka.jdbc.ConnectionFactory;
import com.tomioka.jdbc.dao.ProdutoDAO;
import com.tomioka.jdbc.modelo.Produto;

public class TesteInsercaoComProduto {

	public static void main(String[] args) throws SQLException {

		try (Connection conn = new ConnectionFactory().createConnection()) {
			ProdutoDAO prodDao = new ProdutoDAO(conn);
			Produto prod = new Produto("Controle", "Controle Sony PS4");
			prodDao.save(prod);
		}

	}
}