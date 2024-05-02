<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>

<head>

    <title>Albums - Listagem</title>
    
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

    <div class="container-fluid">
        <div class="row">
            <div class="col-md-12">
				
				<h1 class="title-font pb-5">Albums</h1>
				
			
				
					<a class="create btn-lg btn-default text-font btn-novo" href="${contextPath}/albums/form?page=album-novo">Novo Album</a>
	
					<span class="alert"></span>
			
				
				<c:if test="${not empty messages}">
					<h3 class="alert alert-danger title-font py-4">${messages}</h3>
				</c:if>
				
				<table class="table table-size">
					<thead>
						<tr>
							<th data-field="name" class="table-header-font">Nome</th>
							<th data-field="name" class="table-header-font">Ano de Lançamento</th>
							<th data-field="name" class="table-header-font">Estilo</th>
							<th data-field="name" class="table-header-font">Gravadora</th>
							<th data-field="name" class="table-header-font">Banda</th>
							<th class="actions table-header-font" width="300"></th>
						</tr>
					</thead>
					<tbody  class="table-group-divider">
					
					<c:forEach items="${albums}" var="album">
						<tr>
							<td class="text-font">${album.nome}</td>
							
							<td class="text-font">${album.anoLancamento}</td>
							
							<td class="text-font">${album.genero}</td>
							
							<td class="text-font">${album.gravadora}</td>
							
							<td class="text-font">${album.banda.nome}</td>
							
							<td class="actions">
							
								<form:form action="${contextPath}/albums/${album.id}" method="delete">
								
									<a class="btn btn-purple btn-md " href="${contextPath}/albums/${album.id}">Detalhes</a>
									<a class="btn btn-purple btn-md " href="${contextPath}/albums/form?page=album-editar&id=${album.id}">Editar</a>
									<input type="submit" value="Excluir" class="btn btn-remove btn-md">
								</form:form>
								
							</td>
						</tr>
					</c:forEach>
                    </tbody>
				</table>
            </div>
        </div>
    </div>

    <!-- jQuery -->
    <script src="${js}/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="${contextPath}/js/bootstrap.min.js"></script>

</body>
</html>