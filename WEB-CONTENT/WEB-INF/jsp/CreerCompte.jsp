<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="fragments/header.html" %>
<div class="container">
    <div class="row">
        <div class="col-6">
            <%@ include file="fragments/link.html" %>
        </div>
	 </div>
      <form class="row g-3">
      <h2>Mon profil</h2>
	  <div class="col-md-6">
	    <label for="inputPseudo" class="form-label">Pseudo</label>
	    <input type="text" class="form-control" id="inputPseudo">
	  </div>
	  <div class="col-md-6">
	    <label for="inputNom" class="form-label">Nom</label>
	    <input type="text" class="form-control" id="inputNom">
	  </div>
	  <div class="col-md-6">
	    <label for="inputPenom" class="form-label">Prénom</label>
	    <input type="text" class="form-control" id="inputPrenom">
	  </div>
	  <div class="col-md-6">
	    <label for="inputEmail4" class="form-label">Email</label>
	    <input type="email" class="form-control" id="inputEmail4">
	  </div>
	  <div class="col-md-6">
	    <label for="inputTelephone" class="form-label">Téléphone</label>
	    <input type="text" class="form-control" id="inputTelephone">
	  </div>
	  <div class="col-md-6">
	    <label for="inputRue" class="form-label">Rue</label>
	    <input type="text" class="form-control" id="inputRue">
	  </div>
	  <div class="col-md-6">
	    <label for="inputEmail4" class="form-label">Code Postal</label>
	    <input type="number" class="form-control" id="inputCodePostal">
	  </div>
	  <div class="col-md-6">
	    <label for="inputVille" class="form-label">Ville</label>
	    <input type="text" class="form-control" id="inputVille">
	  </div>
	  <div class="col-md-6">
	    <label for="inputPassword4" class="form-label">Nouveau mot de passe</label>
	    <input type="password" class="form-control" id="inputPassword4">
	  </div>
	  <div class="col-md-6">
	    <label for="inputPassword4" class="form-label"></label>
	    <input type="password" class="form-control" id="inputPassword4">
	  </div>
	  <div class="col-12">
	    <button type="submit" class="btn btn-primary">Créer</button>
	    <button type="submit" class="btn btn-primary">Annuler</button>
	  </div>
	</form>
</div>