<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
    	<meta name="viewport" content="width=device-width, initial-scale=1">
   	    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
		<title>Insert title here</title>
		
		<style type="text/css"> 
		
			form {
				position: absolute;
				top: 20%; 
				left: 33%; 
				right: 33%;
			}
			
			h2{text-align: center; }
		
		</style>
		
	</head>
	<body>
		
		<input type="hidden" value = <%= request.getParameter("url")%> name="url">
		<form action="<%= request.getContextPath() %>/ServeletLogin" method="post" class="row g-3 needs-validation" novalidate>
		<h2> Bem vindo ao jsp</h2>
			<div class="md-3">
				<label class="form-label">Login</label>
				<input name = "login" type="text" class="form-control" required="required">	
				<div class="invalid-feedback">
						Login em branco.
		     	</div>
			</div>
			
			<div class="md-3">
				<label class="form-label">Senha</label>
				<input name = "senha" type="password" class="form-control" required="required">	
				<div class="invalid-feedback">
						Senha em branco.
		     	</div>
			</div>
			 <div class="col-12">
				<input type= "submit" value="Acessar" class="btn btn-primary" style=" width: 100%;"> 
			</div>
			
			<h4 style="color: #d00000; text-align: center;">${msg}</h4>
		</form>
		
		
	 <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
		
	<script type="text/javascript">
	//Fetch all the forms we want to apply custom Bootstrap validation styles to
	  var forms = document.querySelectorAll('.needs-validation')

	  // Loop over them and prevent submission
	  Array.prototype.slice.call(forms)
	    .forEach(function (form) {
	      form.addEventListener('submit', function (event) {
	        if (!form.checkValidity()) {
	          event.preventDefault()
	          event.stopPropagation()
	        }

	        form.classList.add('was-validated')
	      }, false)
	    })
	</script>
		
	</body>
</html>