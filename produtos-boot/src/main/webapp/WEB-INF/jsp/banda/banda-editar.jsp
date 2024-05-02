<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true" %>

<!DOCTYPE html>
<html>

<head>

    <title>Banda - Editar</title>
    
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

        <!-- Call to Action Well -->
        <div class="row">
            <div class="col-lg-12">
                <div class="well bg-novo">
					
					<h2 class="title-font mb-3">Banda</h2>
					
					<form:form modelAttribute="BandaModel" action="${contextPath}/bandas/${BandaModel.id}" method="put">
					
						<spring:hasBindErrors name="BandaModel">
							<div class="alert alert-danger" role="alert">
								<form:errors path="*" class="has-error" />
							</div>
						</spring:hasBindErrors>
					
						<div class="form-group">
							<label class="control-label text-font" for="nome">Nome:</label>
							<form:input type="text" path="nome" id="nome" class="form-control" maxlength="50" size="50" />
							<font color="red"><form:errors path="nome"/></font><br/>
                        </div>
					
						
							<div class="form-group">
							<label class="control-label text-font" for="genero">Gênero:</label>
							<form:input type="text" path="genero" id="genero" class="form-control" maxlength="50" size="50" />
							<font color="red"><form:errors path="genero"/></font><br/>
                        </div>
			
						
							<div class="form-group">
							<label class="control-label text-font" for="nome">Ano de Formação:</label>
							<form:input type="text" path="anoFormacao" id="anoFormacao" class="form-control" maxlength="50" size="50" />
							<font color="red"><form:errors path="anoFormacao"/></font><br/>
                        </div>
			
						
						<a class="btn btn-remove btn-lg" href="${contextPath}/bandas">Cancelar</a>
						<button type="submit" class="btn btn-purple btn-lg">Gravar</button>
                            
                        <br>
                        <br>
					</form:form>
					
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