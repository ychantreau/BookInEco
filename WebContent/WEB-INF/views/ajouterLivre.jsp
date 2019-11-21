<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<base
	href="<%=request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
					+ request.getContextPath() + "/"%>" />
<meta charset="UTF-8">
<title>Ajout de livre</title>
</head>
<body>
<p>
Vous êtes connecté en tant que : ${sessionScope.util.pseudo}
</p>
<p>
Ajout d'un livre:
</p>
<form method="post" action="">
<label for="titre">Titre :</label>
<input type="text" id="titre" name="titre"/>
<br>
<label for="auteur">Auteur :</label>
<input type="text" id="auteur" name="auteur"/>
<br>
<label for="description">Description :</label>
<textarea id="description" name="description" ></textarea>
<br>
<label for="format">Format :</label>
<input type="text" id="format" name="format"/>
</form>

</body>
</html>