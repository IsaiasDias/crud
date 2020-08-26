package com.br.crud;

public class User {

	private Integer id;
	private String Login;
	private String senha;

	public User() {

	}

	public User(String login, String senha) {
		super();
		this.Login = login;
		this.senha = senha;
	}

	public String getLogin() {
		return Login;
	}

	public void setLogin(String login) {
		Login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
