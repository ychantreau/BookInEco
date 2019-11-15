<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <title>Accueil</title>
    </head>
    <body>
        Affichage Livres

        <table>
            <th>Titre</th>
            <th>Auteur</th>
            <th>Description</th>
                <c:forEach items="${ livres }" var="livre" >
                <tr>
                    <td><c:out value="${ livre.titre }" /></td>
                    <td><c:out value="${ livre.auteur }" /></td>
                    <td><c:out value="${ livre.description }" /></td>
                </tr>

            </c:forEach>
        </table>
        <p>${test}</p>
        <p>Autre test <%= request.getAttribute("test")%></p>
    </body>
</html>