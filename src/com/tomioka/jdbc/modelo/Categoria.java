package com.tomioka.jdbc.modelo;

import java.util.ArrayList;
import java.util.List;

public class Categoria {

	private Integer id;
	private String nome;
	private List<Produto> prodCategoria = new ArrayList<Produto>();
	
	public Categoria(Integer id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "Categoria [id=" + id + ", nome=" + nome + "]";
	}
	
	public void add(Produto prod) {
		prodCategoria.add(prod);
	}

	public List<Produto> getProdCategoria() {
		return prodCategoria;
	}

	public void setProdCategoria(List<Produto> prodCategoria) {
		this.prodCategoria = prodCategoria;
	}

	public int getId() {
		return id;
	}

	public String getNome() {
		// TODO Auto-generated method stub
		return this.nome;
	}
	
}
