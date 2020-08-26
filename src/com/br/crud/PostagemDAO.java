package com.br.crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PostagemDAO {

	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;

	public PostagemDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
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

	public boolean insertPost(Postagem post) throws SQLException {

		String sql = "insert into postagem (idUsuario ,conteudo) VALUES(?,?)";
		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, post.getIdUsuario());
		statement.setString(2, post.getConteudo());

		boolean rowInserted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowInserted;
	}

	public List<PostDTO> listAllPosts() throws SQLException {
		List<PostDTO> listPost = new ArrayList<>();

		String sql = "SELECT u.login, p.conteudo, p.idUsuario, p.id FROM usuario u inner JOIN postagem p on u.id = p.idUsuario";

		connect();

		Statement statement = jdbcConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		while (resultSet.next()) {
			Integer idPost = resultSet.getInt("id");
			Integer idUsuario = resultSet.getInt("idUsuario");
			String login = resultSet.getString("login");
			String conteudo = resultSet.getString("conteudo");

			PostDTO post = new PostDTO(idPost, idUsuario, login, conteudo);
			listPost.add(post);
		}

		resultSet.close();
		statement.close();

		disconnect();

		return listPost;
	}

	public void deletePost(Postagem post) throws SQLException {
		String sql = "DELETE FROM postagem where id = ?";

		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, post.getId());

		statement.executeUpdate();
		statement.close();
		disconnect();
	}

	public void editPost(Postagem post) throws SQLException {
		String sql = "UPDATE postagem SET conteudo = ? where id=?";
		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, post.getConteudo());
		statement.setInt(2, post.getId());
		statement.executeUpdate();
		statement.close();
		disconnect();
	}

}
