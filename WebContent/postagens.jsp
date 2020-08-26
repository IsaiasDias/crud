<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Postagens</title>
</head>
<body>

	<h3>
		<a href="novoPost">Realizar novo post</a><br> <a href="sair">Sair</a>
	</h3>

	<table align="center" border="1" cellpadding="5">
		<caption>
			<h2>Postagens</h2>
		</caption>
		<tr>
			<th>Id</th>
			<th>Login</th>
			<th>Conteúdo</th>
			<th>Ações</th>
		</tr>


		<c:forEach var="post" items="${listPost}">
			<form action="edit" method="post">
			<tr>
				<td><c:out value="${post.idUsuario}" /></td>
				<td><c:out value="${post.login}" /></td>
				<c:if test="${idUsuarioLogado == post.idUsuario}">
					<td><input type="text" name="conteudo" size="45"
						value="<c:out value='${post.conteudo}' />" /></td>
				</c:if>
				<c:if test="${idUsuarioLogado != post.idUsuario}">
					<td><c:out value="${post.conteudo}" /></td>
				</c:if>

				<td><c:if test="${idUsuarioLogado == post.idUsuario}">
						<input type="hidden" name="id"
							value="<c:out value='${post.id}' />" />
						<input type="hidden" name="idUsuario"
							value="<c:out value='${post.idUsuario}' />" />

						<input type="submit" value="Alterar" />   
						
                        	&nbsp;&nbsp;&nbsp;&nbsp;
                        	<a
							href="delete?id=<c:out value='${post.id}'/>&idUsuario=<c:out value='${post.idUsuario}' />">Excluir</a>
					</c:if></td>
			</tr>
			</form>
		</c:forEach>

	</table>



</body>
</html>