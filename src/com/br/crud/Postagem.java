package com.br.crud;

public class Postagem {

	private Integer id;
	private String conteudo;
	private Integer idUsuario;

	public Postagem(Integer idUsuario, String conteudo) {
		super();
		this.conteudo = conteudo;
		this.idUsuario = idUsuario;
	}

	public Postagem(Integer id, Integer idUsuario, String conteudo) {
		super();
		this.id = id;
		this.conteudo = conteudo;
		this.idUsuario = idUsuario;
	}

	public Postagem() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

}
