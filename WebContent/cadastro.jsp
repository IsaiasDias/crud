<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title>Crud Application</title>
</head>
<body>
	<center>
		<h1>Cadastro/Login</h1>
	</center>
	<div align="center">

		<form action="entrar" method="post">

			<table border="1" cellpadding="5">
				<tr>
					<th>Login:</th>
					<td><input type="text" name="Login" size="45" maxlength="80" required /></td>
				</tr>
				<tr>
					<th>Senha:</th>
					<td><input type="password" name="Senha" size="45" maxlength="8" required />
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit"
						value="Entrar" /></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
