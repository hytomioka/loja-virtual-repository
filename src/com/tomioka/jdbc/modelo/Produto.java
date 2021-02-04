package com.tomioka.jdbc.modelo;

public class Produto {

	private Integer id;
	private String nome;
	private String descricao;
	private Integer categoriaId;

	public Produto() {
	}
	
	public Produto(String nome, String descricao) {
		this.nome = nome;
		this.descricao = descricao;
	}
	
	public Produto(Integer id, String nome, String descricao, Integer categoriaId) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.categoriaId = categoriaId;
	}

	public String getNome() {
		return nome;
	}

	public Integer getId() {
		return id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Produto [id=" + id + ", nome=" + nome + ", descricao=" + descricao + ", categoriaId=" + categoriaId
				+ "]";
	}

}
