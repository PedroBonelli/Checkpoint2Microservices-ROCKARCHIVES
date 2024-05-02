<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>

<head>

    <title>Album - Detalhe</title>
    
    <c:set value="${pageContext.request.contextPath}" var="contextPath"/>
    <link rel="stylesheet" href="${contextPath}/css/base.css">
    <link href="${contextPath}/css/bootstrap.css" rel="stylesheet">
    <link href="${contextPath}/css/small-business.css" rel="stylesheet">

</head>

<body>
    
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container-fluid">
			
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li class="links-font"><a href="${contextPath}/bandas">ROCK ARCHIVES</a>
					</li>
					<li class="links-font"><a href="${contextPath}/bandas">Bandas</a>
					</li>
					<li class="links-font"><a href="${contextPath}/integrantes">Integrantes</a>
					</li>
					<li class="links-font"><a href="${contextPath}/albums">Albums</a>
					</li>
				</ul>
			</div>
		</div>
	</nav>

    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="well bg-novo">
					
					<h2 class="title-font mb-3">Album - Editar</h2>
					
						<div class="form-group">
							<label class="control-label text-font" for="nome">Nome:</label>
							<b class="text-font">${album.nome}</b>
                        </div>
					
						
						<div class="form-group">
							<label class="control-label text-font" for="nome">Ano de Lançamento:</label>
							<b class="text-font">${album.anoLancamento}</b>
                        </div>
					
						
						<div class="form-group">
							<label class="control-label text-font" for="nome">Gênero:</label>
							<b class="text-font">${album.genero}</b>
                        </div>
						
						
						<div class="form-group">
							<label class="control-label text-font" for="nome">Gravadora:</label>
							<b class="text-font">${album.gravadora}</b>
                        </div>
						
						
						<div class="form-group">
							<label class="control-label text-font" for="nome">Banda:</label>
							<b class="text-font">${album.banda.nome}</b>
                        </div>
						
						
						<a class="btn btn-purple btn-lg" href="${contextPath}/albums">Voltar</a>
                            
                        <br>
                        <br>
					
                </div>
            </div>
        </div>
    </div>

    <!-- jQuery -->
    <script src="${contextPath}/js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="${contextPath}/js/bootstrap.min.js"></script>

</body>
</html>