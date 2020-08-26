<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Main Menu</title>
</head>
<body>
	<center>
		<h1>Main Menu</h1>
	</center>

	<div align="center">

		<form action="postar" method="post">

			<div align="center">
				<label>Conteúdo</label> <input type="text" name="conteudo"
					size="100" required />
			</div>
			</br> <input type="submit" value="Postar" />

		</form>

		<h2>
			<a href="posts">Ver Postagens</a><br>
			<a href="sair">Sair</a>
		</h2>

	</div>

</body>
</html>