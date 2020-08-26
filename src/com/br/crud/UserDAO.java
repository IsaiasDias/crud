package com.br.crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;

	public UserDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
		this.jdbcURL = jdbcURL;
		this.jdbcUsername = jdbcUsername;
		this.jdbcPassword = jdbcPassword;
	}

	protected void connect() throws SQLException {
		if (jdbcConnection == null || jdbcConnection.isClosed()) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				throw new SQLException(e);
			}
			jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		}
	}

	protected void disconnect() throws SQLException {
		if (jdbcConnection != null && !jdbcConnection.isClosed()) {
			jdbcConnection.close();
		}
	}

	public Integer insertUser(User user) throws SQLException {
		User buscaUsuario = findUser(user);
		if (buscaUsuario.getId() == null) {
			String sql = "INSERT INTO usuario (login, senha) VALUES(?,?)";
			connect();

			String[] returnId = { "BATCHID" };

			PreparedStatement statement = jdbcConnection.prepareStatement(sql, returnId);
			statement.setString(1, user.getLogin());
			statement.setString(2, user.getSenha());
			statement.executeUpdate();
			Integer idUsuario = -1;
			try (ResultSet rs = statement.getGeneratedKeys()) {
				if (rs.next()) {
					idUsuario = rs.getInt(1);
				}
				rs.close();
			}
			statement.close();
			disconnect();

			return idUsuario;
		}
		return buscaUsuario.getId();
	}

	public User findUser(User user) throws SQLException {
		User buscaUsuario = new User();
		String sql = "select * from usuario where login=? and senha=?";
		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, user.getLogin());
		statement.setString(2, user.getSenha());

		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) {
			buscaUsuario.setId(resultSet.getInt("id"));
			buscaUsuario.setLogin(resultSet.getString("login"));
			buscaUsuario.setSenha(resultSet.getString("senha"));
		}

		resultSet.close();
		statement.close();

		disconnect();
		return buscaUsuario;

	}
}
