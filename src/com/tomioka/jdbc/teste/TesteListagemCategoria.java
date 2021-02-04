package com.tomioka.jdbc.teste;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tomioka.jdbc.ConnectionFactory;
import com.tomioka.jdbc.dao.CategoriaDAO;
import com.tomioka.jdbc.modelo.Categoria;

public class TesteListagemCategoria {

	public static void main(String[] args) throws SQLException {
		
		try (Connection conn = new ConnectionFactory().createConnection()) {
			
			CategoriaDAO catDto = new CategoriaDAO(conn);
			List<Categoria> listaCategoria = catDto.list();
			
			listaCategoria.stream().forEach(lc -> System.out.println(lc));
		}
		
		
	}

}
