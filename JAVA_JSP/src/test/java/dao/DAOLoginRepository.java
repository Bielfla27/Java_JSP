package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.SingleConnectionBanco;
import model.ModelLogin;

public class DAOLoginRepository {
	
	private Connection connection;
	
	public DAOLoginRepository() {
		connection = SingleConnectionBanco.getConnection();
	}
	
	public boolean validarAutentucacao(ModelLogin model) throws SQLException {
	
		//Se quiser aceitar caractear maiusculo eu faço um  upper(login) = upper(?) ande upper(senha) = upper(?)
		String sql = "select * from model_login where login = ? and senha = ?" ;
		
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, model.getLogin());
		statement.setString(2, model.getSenha());
		
		ResultSet resultado = statement.executeQuery();
		
		if(resultado.next()) {
			return true; //autenticado
		}
		
		return false; // não autenticado
	}
	

}
