package com.br.crud;

public class PostDTO {

	private Integer id;
	private Integer idUsuario;
	private String login;
	private String conteudo;

	public PostDTO(Integer id, Integer idUsuario, String login, String conteudo) {
		super();
		this.id = id;
		this.idUsuario = idUsuario;
		this.login = login;
		this.conteudo = conteudo;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
