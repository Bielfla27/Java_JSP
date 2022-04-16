package filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import connection.SingleConnectionBanco;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@WebFilter(urlPatterns = {"/principal/*"})
public class FilterAutenticacao implements Filter {
	
	private static Connection connection; 
   
    public FilterAutenticacao() {
    }

	public void destroy() {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
						throws IOException, ServletException {
		try {
			HttpServletRequest req = (HttpServletRequest) request;
			HttpSession session = req.getSession();
			
			String usuarioLogado = (String) session.getAttribute("usuario");
			String urlParaAutenticar = req.getServletPath();
			
			if(usuarioLogado == null && !urlParaAutenticar.equalsIgnoreCase("/principal/ServeletLogin") ) {
				RequestDispatcher redirecionar =  request.getRequestDispatcher("/index.jsp?url=" +urlParaAutenticar);
				request.setAttribute("msg", "Por favor realize o login!");
				redirecionar.forward(request, response);
				return; //PARA A EXECUÇÃO E REDIRECIONA PARA O LOGIN
			}else{
				chain.doFilter(request, response);			
			}
			connection.commit(); //deu tudo certo , então salvo as alterações
			
		}catch (Exception e) {
			e.printStackTrace();
			
			RequestDispatcher redirecionar  = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);

			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}


	public void init(FilterConfig fConfig) throws ServletException {
		connection = SingleConnectionBanco.getConnection();
	}

}
