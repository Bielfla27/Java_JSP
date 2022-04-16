package Servelet;

import java.io.IOException;

import dao.DAOLoginRepository;
import dao.DAOUsuarioRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelLogin;

@WebServlet(urlPatterns = {"/principal/ServeletLogin" , "/ServletLogin"})
public class ServeletLogin extends HttpServlet {
	
	private DAOLoginRepository daoLoginRepository = new DAOLoginRepository();
	private DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();
	
	private static final long serialVersionUID = 1L;

 
    public ServeletLogin() {
    }

	//Passa parametros pela url
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");
		
		if( acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("logout")) {
			request.getSession().invalidate(); //Invalida a sessão 
			RequestDispatcher redirecionar =  request.getRequestDispatcher("index.jsp");
			redirecionar.forward(request, response);
		}else {
			doPost(request, response);
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login =	request.getParameter("login");
		String senha = 	request.getParameter("senha");
		String url = 	request.getParameter("url");
	
		try {
			if( login != null && !login.isEmpty() && senha != null && !senha.isEmpty()) {
				ModelLogin modellogin =  new ModelLogin();
				modellogin.setLogin(login);
				modellogin.setSenha(senha);		
				
				if(daoLoginRepository.validarAutentucacao(modellogin)) {
				 
					modellogin = daoUsuarioRepository.consultaUsuarioLogado(login);
					request.getSession().setAttribute("usuario", modellogin.getLogin());
					request.getSession().setAttribute("perfil", modellogin.getPerfil());
					
					if(url == null || url.equals("null")) {
						url = "principal/Principal.jsp";
						 
					}
			
					RequestDispatcher redirecionar  = request.getRequestDispatcher(url);
					redirecionar.forward(request, response);	
				}else {
					RequestDispatcher redirecionar  = request.getRequestDispatcher("/index.jsp");
					request.setAttribute("msg", "Infome o login e senha corretamente!");
					redirecionar.forward(request, response);
				}
					
			}else {
				RequestDispatcher redirecionar  = request.getRequestDispatcher("index.jsp");
				request.setAttribute("msg", "Infome o login e senha corretamente!");
				redirecionar.forward(request, response);
			}
		
		}catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher redirecionar  = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
		}
	}

}
