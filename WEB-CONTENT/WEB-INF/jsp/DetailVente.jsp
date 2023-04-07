<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="fragments/header.html" %>
<div class="container">
    <div class="row">
        <div class="col-6">
            <%@ include file="fragments/link.html" %>
        </div>
    </div>
    <div class="row">
        <div class="col-12">
            <h1>Détails vente</h1>
        </div>
    </div>
    <div>
        <input type="text" name="articleName" id="articleName" placeholder="Le nom de l'article contient">
    </div>
    <div>
        <label for="categorie">
            Catégorie :
        </label>
        <p class="card-text">Prix : <% article.getPrixVente() %></p>
        <p class="card-text">Fin de l'enchère : <% article.getDateFinEnchere() %></p>
        <br>
        <p class="card-text">Vendeur : <% article.getUtilisateur().getPseudo() %></p>
    </div>

    <div class="col-3">
        <input type="submit" class="btn btn-warning" value="Enchérir">
    </div>

</div>
<%@ include file="fragments/footer.html" %>