package Servelet;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;

import connection.SingleConnectionBanco;
import dao.DAOUsuarioRepository;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class ServeletGenericUtil extends HttpServlet implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();
	
	
	public Long getUserlogado(HttpServletRequest request) throws SQLException {
		
		HttpSession session = request.getSession();
		
		String usuarioLogado = (String) session.getAttribute("usuario");
		
		return daoUsuarioRepository.consultaUsuarioLogado(usuarioLogado).getId();
		
	}

}
