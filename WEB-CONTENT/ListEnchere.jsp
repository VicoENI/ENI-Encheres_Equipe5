<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="fragments/header.html" %>
<div class="container">
    <div class="row">
        <div class="col-6">
            <%@ include file="fragments/link.html" %>
        </div>
        <div class="col-6">
            <a href="">S'inscrire - Se connecter</a>
        </div>
    </div>
    <div class="row">
        <div class="col-12">
            <h1>Liste des enchères</h1>
        </div>
    </div>
    <div class="row">
        <form action="" method="post">
            <div class="col-9">
                <label for="articleName">
                    <strong>
                        Filtres :
                    </strong>
                </label>
                <input type="text" name="articleName" id="articleName" placeholder="Le nom de l'article contient">
                <label for="categorie">
                    Catégorie :
                </label>
                <select name="categorie" id="categorie">
                    <option value="all">Toutes</option>
                    <c:forEach items="${categories}" var="option">
                        <option value="<%= option.getId() %>"><%= option.getLibelle() %></option>
                    </c:forEach>
                </select>
            </div>
            <div class="col-3">
                <input type="submit" value="Rechercher">
            </div>
        </form>
    </div>
    <div class="row">
        <% for (ArticleVendu article : articles) { %>
            <div class="col-6">
                <div class="card mb-3" style="max-width: 540px;">
                    <div class="row g-0">
                        <div class="col-md-4">
                            <img src="..." class="img-fluid rounded-start" alt="...">
                        </div>
                        <div class="col-md-8">
                            <div class="card-body">
                                <h5 class="card-title"><% article.getNomArticle() %></h5>
                                <p class="card-text">Prix : <% article.getPrixVente() %></p>
                                <p class="card-text">Fin de l'enchère : <% article.getDateFinEnchere() %></p>
                                <br>
                                <p class="card-text">Vendeur : <% article.getUtilisateur().getPseudo() %></p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        <% } %>
    </div>
</div>
<%@ include file="fragments/footer.html" %>