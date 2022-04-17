package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.SingleConnectionBanco;
import model.ModelLogin;

public class DAOUsuarioRepository {
	
	private Connection connection;

	public DAOUsuarioRepository() {
		connection = SingleConnectionBanco.getConnection();
	}
	
	public ModelLogin gravarUsuario(ModelLogin modelo, Long userLogado) throws SQLException {
		
		if(modelo.isNovo()) { //Grava um novo
			
		   String  sql =  "INSERT INTO public.model_login(login, senha,  nome, email,usuario_id,perfil,sexo)VALUES (?, ?, ?, ?, ?,?,?);";
		   
		   PreparedStatement preparedSql = connection.prepareStatement(sql);
		   
		   preparedSql.setString(1, modelo.getLogin());
		   preparedSql.setString(2, modelo.getSenha());
		   preparedSql.setString(3, modelo.getNome());
		   preparedSql.setString(4, modelo.getEmail());
		   preparedSql.setLong(5, userLogado);
		   preparedSql.setString(6,modelo.getPerfil());
		   preparedSql.setString(7, modelo.getSexo());
		   preparedSql.execute();
		   
		   connection.commit();
		   
		   if(modelo.getFotouser() != null && !modelo.getFotouser().isEmpty() ) {
			   sql = "update model_login set fotouser =?, extensaofotouser=? where login =?";
			   preparedSql = connection.prepareStatement(sql);
			   preparedSql.setString(1, modelo.getFotouser());
			   preparedSql.setString(2, modelo.getExtensaofotoUser());
			   preparedSql.setString(3, modelo.getLogin());
			   preparedSql.execute();
			   connection.commit();
		   }
		}else { //Atualizar dados
			
			String sql = "UPDATE public.model_login SET login=?, senha=?,  nome=?, email=?, perfil=?, sexo=? WHERE id = '"+ modelo.getId() + "';";

			PreparedStatement preparedSql = connection.prepareStatement(sql);

			preparedSql.setString(1, modelo.getLogin());
			preparedSql.setString(2, modelo.getSenha());
			preparedSql.setString(3, modelo.getNome());
			preparedSql.setString(4, modelo.getEmail());
			preparedSql.setString(5, modelo.getPerfil());
			preparedSql.setString(6, modelo.getSexo());
			preparedSql.executeUpdate();
			
			connection.commit();
			
			   if(modelo.getFotouser() != null && !modelo.getFotouser().isEmpty()) {
				   sql = "update model_login set fotouser =?, extensaofotouser=? where id =?";
				   preparedSql = connection.prepareStatement(sql);
				   preparedSql.setString(1, modelo.getFotouser());
				   preparedSql.setString(2, modelo.getExtensaofotoUser());
				   preparedSql.setLong(3, modelo.getId());
				   preparedSql.execute();
				   connection.commit();
			   }
		
		}
	   
	   return this.consultaUsuari(modelo.getLogin(), userLogado);
	   
	}
	
	public List<ModelLogin> consultaUsuarioList(String nome, Long userLogado) throws SQLException{
		
		List<ModelLogin> retorno = new ArrayList<ModelLogin>();
		
		String sql = "SELECT * from model_login where upper(nome) like upper(?) and useradmin is false and usuario_id = " + userLogado;
		
		PreparedStatement preparedSql = connection.prepareStatement(sql);
		preparedSql.setString(1, "%" + nome + "%");
		
		ResultSet resultado = preparedSql.executeQuery();
		
		while(resultado.next()) { // percorrer as linha de resultado do sql
			ModelLogin modelLogin = new ModelLogin();
			
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setNome(resultado.getString("nome"));
			modelLogin.setPerfil(resultado.getString("perfil"));
			modelLogin.setSexo(resultado.getString("sexo"));
			retorno.add(modelLogin);
		}
		return retorno;		
	}
	
	public List<ModelLogin> consultaUsuarioList(Long userLogado) throws SQLException{
		
		List<ModelLogin> retorno = new ArrayList<ModelLogin>();
		
		String sql = "SELECT * from model_login where useradmin is false and usuario_id = " + userLogado;
		
		PreparedStatement preparedSql = connection.prepareStatement(sql);
		
		
		ResultSet resultado = preparedSql.executeQuery();
		
		while(resultado.next()) { // percorrer as linha de resultado do sql
			ModelLogin modelLogin = new ModelLogin();
			
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setNome(resultado.getString("nome"));
			modelLogin.setPerfil(resultado.getString("perfil"));
			modelLogin.setSexo(resultado.getString("sexo"));
			retorno.add(modelLogin);
		}
		return retorno;		
	}
	
	public ModelLogin consultaUsuarioLogado(String login) throws SQLException {
		
		 ModelLogin modelo = new ModelLogin();
		 String sql = "select * from model_login where upper(login) = upper('"+login+"')";
		
		 PreparedStatement preparedSql = connection.prepareStatement(sql);
	
		 ResultSet resultado = preparedSql.executeQuery();
		 
		 while(resultado.next())/*se tiver resultado*/ {
			 modelo.setId(resultado.getLong("id"));
			 modelo.setEmail(resultado.getString("email"));
			 modelo.setNome(resultado.getString("nome"));
			 modelo.setLogin(resultado.getString("login"));
			 modelo.setSenha(resultado.getString("senha"));
			 modelo.setUseradmin(resultado.getBoolean("useradmin"));
			 modelo.setPerfil(resultado.getString("perfil"));
			 modelo.setSexo(resultado.getString("sexo"));

		 }
		 
		 return modelo; 
	}
	
	public ModelLogin consultaUsuari(String login) throws SQLException {
		
		 ModelLogin modelo = new ModelLogin();
		 String sql = "select * from model_login where upper(login) = upper('"+login+"') and useradmin is false";
		
		 PreparedStatement preparedSql = connection.prepareStatement(sql);
	
		 ResultSet resultado = preparedSql.executeQuery();
		 
		 while(resultado.next())/*se tiver resultado*/ {
			 modelo.setId(resultado.getLong("id"));
			 modelo.setEmail(resultado.getString("email"));
			 modelo.setNome(resultado.getString("nome"));
			 modelo.setLogin(resultado.getString("login"));
			 modelo.setSenha(resultado.getString("senha"));
			 modelo.setUseradmin(resultado.getBoolean("useradmin"));
			 modelo.setPerfil(resultado.getString("perfil"));
			 modelo.setSexo(resultado.getString("sexo"));

		 }
		 
		 return modelo; 
		 
	}
 	
	public ModelLogin consultaUsuari(String login, Long userLogado) throws SQLException {
		
		 ModelLogin modelo = new ModelLogin();
		 String sql = "select * from model_login where upper(login) = upper('"+login+"') and useradmin is false and usuario_id = " + userLogado;
		
		 PreparedStatement preparedSql = connection.prepareStatement(sql);
	
		 ResultSet resultado = preparedSql.executeQuery();
		 
		 while(resultado.next())/*se tiver resultado*/ {
			 modelo.setId(resultado.getLong("id"));
			 modelo.setEmail(resultado.getString("email"));
			 modelo.setNome(resultado.getString("nome"));
			 modelo.setLogin(resultado.getString("login"));
			 modelo.setSenha(resultado.getString("senha"));
			 modelo.setPerfil(resultado.getString("perfil"));
			 modelo.setSexo(resultado.getString("sexo"));

		 }
		 
		 return modelo; 
		 
	}
	
	public ModelLogin consultaUsuarioId(String id, Long userLogado) throws SQLException {
		
		 ModelLogin modelo = new ModelLogin();
		 String sql = "select * from model_login where id = ? and useradmin is false and usuario_id = ?;";
		
		 PreparedStatement preparedSql = connection.prepareStatement(sql);
		 preparedSql.setLong(1, Long.parseLong(id));
		 preparedSql.setLong(2, userLogado);
	
		 ResultSet resultado = preparedSql.executeQuery();
		 
		 while(resultado.next())/*se tiver resultado*/ {
			 modelo.setId(resultado.getLong("id"));
			 modelo.setEmail(resultado.getString("email"));
			 modelo.setNome(resultado.getString("nome"));
			 modelo.setLogin(resultado.getString("login"));
			 modelo.setSenha(resultado.getString("senha"));
			 modelo.setPerfil(resultado.getString("perfil"));
			 modelo.setSexo(resultado.getString("sexo"));

		 }
		 
		 return modelo; 
		 
	}
	
	public boolean validarLogin(String login) throws Exception{
		String sql = "select count(1) > 0 as existe from model_login where upper(login) = upper('"+login+"')";
		
		 PreparedStatement preparedSql = connection.prepareStatement(sql);
	
		 ResultSet resultado = preparedSql.executeQuery();
		 
		 resultado.next();
		 return resultado.getBoolean("existe");
		 

	}
	
	public void delatarUser(String id) throws SQLException {
		String sql =  "DELETE FROM public.model_login WHERE id = ? and useradmin is false;;";
		
		PreparedStatement preparedSql = connection.prepareStatement(sql);
		preparedSql.setLong(1, Long.parseLong(id));
		preparedSql.executeUpdate();
		
		connection.commit();
	}
	

}
