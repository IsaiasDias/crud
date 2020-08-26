package com.br.crud;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;
	private PostagemDAO postagemDAO;

	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");

		userDAO = new UserDAO(jdbcURL, jdbcUsername, jdbcPassword);
		postagemDAO = new PostagemDAO(jdbcURL, jdbcUsername, jdbcPassword);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		HttpSession sessao = request.getSession();

		RequestDispatcher dispatcher = null;
		try {
			switch (action) {

			case "/entrar":
				if (sessao.getAttribute("idUsuario") == null) {
					insertUser(request, response, sessao);
					listPost(request, response, sessao);
				}
				response.sendRedirect("posts");
				break;
			case "/postar":
				if (sessao.getAttribute("idUsuario") != null) {
					insertPost(request, response, sessao);
				} else {
					response.sendRedirect("/crud");
				}
				break;
			case "/posts":
				if (sessao.getAttribute("idUsuario") != null) {
					listPost(request, response, sessao);
					dispatcher = request.getRequestDispatcher("postagens.jsp");
					dispatcher.forward(request, response);
				} else {
					response.sendRedirect("/crud");
				}
				break;
			case "/novoPost":
				if (sessao.getAttribute("idUsuario") != null) {
					dispatcher = request.getRequestDispatcher("MainMenu.jsp");
					dispatcher.forward(request, response);
				} else {
					response.sendRedirect("/crud");
				}
				break;
			case "/sair":
				sessao.removeAttribute("idUsuario");
				response.sendRedirect("/crud");
				break;
			case "/delete":
				if (sessao.getAttribute("idUsuario") != null) {
					deletePost(request, response, sessao);
				}
				response.sendRedirect("posts");
				break;
			case "/edit":
				if (sessao.getAttribute("idUsuario") != null) {
					editPost(request, response, sessao);
				}
				response.sendRedirect("posts");
				break;
			default:
				if (sessao.getAttribute("idUsuario") == null) {
					dispatcher = request.getRequestDispatcher("cadastro.jsp");
					dispatcher.forward(request, response);
				} else {
					response.sendRedirect("posts");
				}
				break;
			}

		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void insertUser(HttpServletRequest request, HttpServletResponse response, HttpSession sessao)
			throws SQLException, IOException {
		String login = request.getParameter("Login");
		String senha = request.getParameter("Senha");

		User newUser = new User(login, senha);
		Integer idUsuario = userDAO.insertUser(newUser);
		sessao.setAttribute("idUsuario", idUsuario);
	}

	private void insertPost(HttpServletRequest request, HttpServletResponse response, HttpSession sessao)
			throws SQLException, IOException {
		Integer idUsuario = (Integer) sessao.getAttribute("idUsuario");
		String conteudo = request.getParameter("conteudo");

		if (!conteudo.equals("")) {
			Postagem newPostagem = new Postagem(idUsuario, conteudo);
			postagemDAO.insertPost(newPostagem);
			response.sendRedirect("posts");
		} else {
			response.sendRedirect("novoPost");
		}
	}

	private void listPost(HttpServletRequest request, HttpServletResponse response, HttpSession sessao)
			throws SQLException, IOException, ServletException {
		List<PostDTO> listPost = postagemDAO.listAllPosts();
		request.setAttribute("listPost", listPost);
		request.setAttribute("idUsuarioLogado", sessao.getAttribute("idUsuario"));
	}

	private void deletePost(HttpServletRequest request, HttpServletResponse response, HttpSession sessao)
			throws SQLException, IOException {
		Integer idPost = Integer.parseInt(request.getParameter("id"));
		Integer idUsuarioPost = Integer.parseInt(request.getParameter("idUsuario"));
		Integer idUsuarioLogado = (Integer) sessao.getAttribute("idUsuario");

		if (idUsuarioLogado == idUsuarioPost) {
			Postagem post = new Postagem();
			post.setId(idPost);
			post.setIdUsuario(idUsuarioPost);
			postagemDAO.deletePost(post);
		}
	}

	private void editPost(HttpServletRequest request, HttpServletResponse response, HttpSession sessao)
			throws SQLException, IOException {
		Integer idPost = Integer.parseInt(request.getParameter("id"));
		Integer idUsuarioPost = Integer.parseInt(request.getParameter("idUsuario"));
		Integer idUsuarioLogado = (Integer) sessao.getAttribute("idUsuario");
		String conteudo = request.getParameter("conteudo");

		if (idUsuarioLogado == idUsuarioPost) {
			Postagem post = new Postagem(idPost, idUsuarioLogado, conteudo);
			postagemDAO.editPost(post);
		}
	}

}
