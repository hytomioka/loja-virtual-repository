package com.tomioka.jdbc.modelo;

import java.util.List;

public class Categoria {

	private Integer id;
	private String nome;
	private List<Produto> prodCategoria;
	
	public Categoria(Integer id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "Categoria [id=" + id + ", nome=" + nome + "]";
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
