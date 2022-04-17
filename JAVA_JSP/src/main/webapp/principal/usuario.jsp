<%@page import="model.ModelLogin"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html lang="en">

<jsp:include page="head.jsp"></jsp:include>

<body>
	<jsp:include page="theme-loader.jsp"></jsp:include>

	<div id="pcoded" class="pcoded">
		<div class="pcoded-overlay-box"></div>
		<div class="pcoded-container navbar-wrapper">
			<jsp:include page="navbar.jsp"></jsp:include>

			<div class="pcoded-main-container">
				<div class="pcoded-wrapper">

					<jsp:include page="navbarmenu.jsp"></jsp:include>

					<div class="pcoded-content">
						<!-- Page-header start -->

						<jsp:include page="page-head.jsp"></jsp:include>

						<!-- Page-header end -->

						<div class="pcoded-inner-content">
							<!-- Main-body start -->
							<div class="main-body">
								<div class="page-wrapper">
									<!-- Page-body start -->
									<div class="page-body">


										<div class="row">
											<div class="col-sm-12">
												<!-- Basic Form Inputs card start -->
												<div class="card">

													<div class="card-block">
														<h4 class="sub-title">Cadastro De Usuário</h4>

														<form class="form-material"
															enctype="multipart/form-data"
															action="<%=request.getContextPath()%>/ServeletUsuarioControler"
															method="post" id="formUser">

															<input type="hidden" name="acao" id="acao" value="">

															<div class="form-group form-default form-static-label">
																<input type="text" name="id" id="id"
																	class="form-control" readonly="readonly"
																	value="${modelLogin.id }"> <span
																	class="form-bar"></span> <label class="float-label">ID:</label>
															</div>
															
															<div class="form-group form-default input-group mb-4">
																
																<div class="input-group-prepend">
																	<img alt="Imagem User" id="fotobase64" src="" width="70px" > 
																</div>
																<input type="file" id="fileFoto" name="fileFoto" accept="image/*" onchange="vizualizarImg('fotobase64', 'fileFoto');" class="form-control-file" style="margin-top:15px; margin-left:5px;" >
																
															</div>
															
															<div class="form-group form-default form-static-label">
																<input type="text" name="nome" id="nome"
																	class="form-control" required="required"
																	value="${modelLogin.nome }"> <span
																	class="form-bar"></span> <label class="float-label">Nome:</label>
															</div>
															<div class="form-group form-default form-static-label">
																<input type="email" name="email" id="email"
																	class="form-control" required="required"
																	value="${modelLogin.email }"> <span
																	class="form-bar"></span> <label class="float-label">E-mail:</label>
															</div>
															
															<div class="form-group form-default form-static-label">
																<select class="form-control form-control-lg"
																	name="perfil">

																	<option disabled="disabled" selected="selected">Selecione
																		o Perfil</option>
																	<option value="administrador"
																		<%ModelLogin modelLogin = (ModelLogin) request.getAttribute("modelLogin");

																		if (modelLogin != null && modelLogin.getPerfil().equals("administrador")) {
																			out.print(" ");
																			out.print("selected=\"selected\"");
																			out.print(" ");
																		}%>>Admin</option>

																	<option value="secretaria"
																		<%modelLogin = (ModelLogin) request.getAttribute("modelLogin");

																		if (modelLogin != null && modelLogin.getPerfil().equals("secretaria")) {
																			out.print(" ");
																			out.print("selected=\"selected\"");
																			out.print(" ");
																		
																		}%>>Secretária</option>

																	<option value="auxiliar"
																		<%modelLogin = (ModelLogin) request.getAttribute("modelLogin");
																		
																		if (modelLogin != null && modelLogin.getPerfil().equals("auxiliar")) {
																			out.print(" ");
																			out.print("selected=\"selected\"");
																			out.print(" ");
																		}%>>Auxiliar</option>
																</select> <span class="form-bar"></span> <label
																	class="float-label">Perfil:</label>
															</div>

															<div class="form-group form-default form-static-label">
																<input type="text" name="login" id="login"
																	class="form-control" required="required"
																	value="${modelLogin.login }"> <span
																	class="form-bar"></span> <label class="float-label">Login:</label>
															</div>
															<div class="form-group form-default form-static-label">
																<input type="password" name="senha" id="senha"
																	class="form-control" required="required"
																	value="${modelLogin.senha }"> <span
																	class="form-bar"></span> <label class="float-label">Senha:</label>
															</div>

															<div class="form-group form-default form-static-label">
																<input type="radio" name="sexo" checked="checked"
																	value="MASCULINO" <%
																	modelLogin = (ModelLogin) request.getAttribute("modelLogin");     
																	if (modelLogin != null && modelLogin.getSexo().equals("MASCULINO")) {
																		out.print(" ");
																		out.print("checked=\"checked\"");
																		out.print(" ");
																	}
																	%>
																	> Masculino</> 
																	<input
																	type="radio" name="sexo" style="margin-left: 10px;"
																	value="FEMENINO"<%
																	modelLogin = (ModelLogin) request.getAttribute("modelLogin");     
																	if (modelLogin != null && modelLogin.getSexo().equals("FEMENINO")) {
																		out.print(" ");
																		out.print("checked=\"checked\"");
																		out.print(" ");
																	}
																	%>>Femenino</>
															</div>


															<button type="button"
																class="btn waves-effect waves-light btn-primary"
																onclick="limparForm()">
																<i class="icofont icofont-user-alt-3"></i>Novo
															</button>
															<button class="btn waves-effect waves-light btn-success">
																<i class="icofont icofont-user-alt-3"></i>Salvar/Atualizar
															</button>
															<button type="button"
																class="btn waves-effect waves-light btn-info"
																onclick="deleteComAjax();">
																<i class="icofont icofont-user-alt-3"></i>Excluir
															</button>
															<button type="button" class="btn btn-secondary"
																data-toggle="modal" data-target="#exampleModalUsuario">
																Pesquisar</button>

														</form>
													</div>
												</div>
											</div>
										</div>
										<span id="msg">${msg}</span>

										<div style="height: 300px; overflow: scroll;">
											<table class="table" id="tabelaresultadosview">
												<thead>
													<tr>
														<th scope="col">ID</th>
														<th scope="col">Nome</th>
														<th scope="col">Ver</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items='${modelLogins}' var='ml'>
														<tr>
															<td><c:out value="${ml.id}"></c:out></td>
															<td><c:out value="${ml.nome}"></c:out></td>
															<td><a class="btn btn-success"
																href="<%= request.getContextPath() %>/ServletUsuarioController?acao=buscarEditar&id=${ml.id}">Ver</a></td>
														</tr>
													</c:forEach>
												</tbody>

											</table>
										</div>

									</div>
									<!-- Page-body end -->
								</div>
								<div id="styleSelector"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="javascript.jsp"></jsp:include>

	<!-- Modal -->
	<div class="modal fade" id="exampleModalUsuario" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Pesquisa de
						Usuario</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="input-group mb-3">
						<input type="text" class="form-control" placeholder="Nome"
							aria-label="nome" aria-describedby="basic-addon2" id="nomeBusca">
						<div class="input-group-append">
							<button class="btn btn-success" onclick="buscarUsuario();"
								type="button">Buscar</button>
						</div>
					</div>
					<div style="height: 300px; overflow: scroll;">
						<table class="table" id="tabelaresultados">
							<thead>
								<tr>
									<th scope="col">ID</th>
									<th scope="col">Nome</th>
									<th scope="col">Ver</th>
								</tr>
							</thead>
							<tbody>

							</tbody>
						</table>
					</div>
					<span id="totalresultados"></span>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Fechar</button>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		function buscarUsuario() {

			var nomeBusca = document.getElementById("nomeBusca").value;

			if (nomeBusca != null && nomeBusca != '' && nomeBusca.trim() != '') { //validando que tem que ter valor pra buscar no banco

				var urlAction = document.getElementById("formUser").action;

				$
						.ajax(
								{

									method : "get",
									url : urlAction,
									data : "nomeBusca=" + nomeBusca
											+ '&acao=buscarUser',
									success : function(response) {
										var json = JSON.parse(response);

										$('#tabelaresultados > tbody > tr')
												.remove();

										for (var i = 0; i < json.length; i++) {
											$('#tabelaresultados > tbody')
													.append(
															'<tr> <td>'
																	+ json[i].id
																	+ '</td>  <td>'
																	+ json[i].nome
																	+ '</td> <td><button onclick="verEditar('
																	+ json[i].id
																	+ ')" type="button" class="btn btn-info">Ver</button></td> </tr>');
										}

										document
												.getElementById("totalresultados").textContent = 'Resultados: '
												+ json.length;

									}

								}).fail(
								function(xhr, status, errorThrown) {
									alert('Erro ao buscar usuário por nome: '
											+ xhr.responseText);
								});

			}

		}

		function verEditar(id) {
			var urlAction = document.getElementById("formUser").action;

			window.location.href = urlAction + '?acao=buscarEditar&id=' + id;
		}

		function deleteComAjax() {
			if (confirm("Tem certeza que deseja excluir?")) {
				var urlAction = document.getElementById("formUser").action;
				var idUser = document.getElementById("id").value;

				$.ajax({

					method : "get",
					url : urlAction,
					data : "id=" + idUser + '&acao=deletarajax',
					success : function(response) {

						limparForm();
						document.getElementById('msg').textContent = response;
					}

				}).fail(
						function(xhr, status, errorThrown) {
							alert('Erro ao deletar usuário por id: '
									+ xhr.responseText);
						});
			}
		}

		function criarDelete() {
			if (confirm('Deseja realmente excluir os dados?')) {
				document.getElementById("formUser").method = 'get';
				document.getElementById("acao").value = 'deletar';
				document.getElementById("formUser").submit();
			}
		}

		function limparForm() {
			var elementos = document.getElementById("formUser").elements; //Retorna os elementos html dentro do forme

			for (p = 0; p < elementos.length; p++) {
				elementos[p].value = '';
			}
		}
		
		
		function vizualizarImg(fotobase64, fileFoto) {
			var preview = document.getElementById(fotobase64); //Campo img html
			var fileUser = document.getElementById(fileFoto).files[0]; //imagem que usuario quer colocar
			var reader = new FileReader();
			
			reader.onloadend = function() {
				preview.src = reader.result; //carrega a foto
			};
			
			if(fileUser){
				reader.readAsDataURL(fileUser);
			}else{
				preview.src= ''; 
			}
			
			
		}
	</script>

</body>

</html>
