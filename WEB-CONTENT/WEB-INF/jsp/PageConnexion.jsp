<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="fragments/header.html" %>
<div class="container">
			<div class="row">
		        <div class="col-6">
		            <%@ include file="fragments/link.html" %>
		        </div>
			 </div>
            <div class="row">
                <div class="col-6">
                    <a href="">S'inscrire - Se connecter</a>
                </div>
            </div>
            <div class="col-6">
                <form>
                <div class="mb-3">
                    <label for="exampleInputpseudo" class="form-label">Identifiant</label>
                    <input type="text" class="form-control" id="pseudo">
                </div>
                <div class="mb-3">
                    <label for="exampleInputPassword1" class="form-label">Mot de passe</label>
                    <input type="password" class="form-control" id="exampleInputPassword1">
                </div>
                <div class="mb-3 form-check">
                    <input type="checkbox" class="form-check-input" id="exampleCheck1">
                    <label class="form-check-label" for="exampleCheck1">Se souvenir de moi</label>
                </div>
                <button type="submit" class="btn btn-primary">Connexion</button>
                </form>
            </div>
            <div class="row">
                <div class="d-grid gap-2 col-6 mx-auto">
                <button class="btn btn-primary" type="button">Créer un compte  </button>
                </div>
            </div>
            
        </div>