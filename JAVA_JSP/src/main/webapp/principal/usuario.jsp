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
															enctype="multipar/form-data"
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
																	<img alt="Imagem User" src="data:image/jpeg;base64,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" width="70px" > 
																</div>
																<input type="file" accept="image/*" class="form-control-file" style="margin-top:15px; margin-left:5px;" >
																
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
	</script>

</body>

</html>
