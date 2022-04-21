package Servelet;

import java.io.IOException;

import java.util.List;

import org.apache.tomcat.jakartaee.commons.compress.utils.IOUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.DAOUsuarioRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import model.ModelLogin;

@MultipartConfig
@WebServlet ( urlPatterns = {"/ServletUsuarioController"})
public class ServeletUsuarioControler extends ServeletGenericUtil {
	private static final long serialVersionUID = 1L;

	private DAOUsuarioRepository daoUsuario = new DAOUsuarioRepository();

	public ServeletUsuarioControler() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String acao = request.getParameter("acao");
			
			if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletar")) {
				String idUser = request.getParameter("id");
				daoUsuario.delatarUser(idUser);
				
				List<ModelLogin> modelLogins = daoUsuario.consultaUsuarioList(super.getUserlogado(request));
				request.setAttribute("modelLogins", modelLogins);
				
				request.setAttribute("msg", "Excluido com sucesso!");
				request.setAttribute("totalPagina", daoUsuario.totalPagina(this.getUserlogado(request)));
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
				
			}else if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletarajax")) {
				String idUser = request.getParameter("id");
				daoUsuario.delatarUser(idUser);
				response.getWriter().write("Excluido com sucesso!");
				
			}else if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarUser")) {
				String nomeBusca = request.getParameter("nomeBusca");
				
				List<ModelLogin> dadosJsonUser = daoUsuario.consultaUsuarioList(nomeBusca,super.getUserlogado(request)); 
				
				ObjectMapper mapper = new ObjectMapper();
				String json = mapper.writeValueAsString(dadosJsonUser);
				
				response.getWriter().write(json);	
			}else if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarEditar")) {
				
				String id = request.getParameter("id");
				
				ModelLogin modelLogin = daoUsuario.consultaUsuarioId(id,super.getUserlogado(request)); 
				
				List<ModelLogin> modelLogins = daoUsuario.consultaUsuarioList(super.getUserlogado(request));
				request.setAttribute("modelLogins", modelLogins);

				request.setAttribute("msg", "Usuário em edição");
				request.setAttribute("modelLogin", modelLogin);
				request.setAttribute("totalPagina", daoUsuario.totalPagina(this.getUserlogado(request)));
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
				
			}else if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("listarUser")) {
				
				List<ModelLogin> modelLogins = daoUsuario.consultaUsuarioList(super.getUserlogado(request));
				
				request.setAttribute("msg", "Usuário Carregados");
				request.setAttribute("modelLogins", modelLogins);
				request.setAttribute("totalPagina", daoUsuario.totalPagina(this.getUserlogado(request)));
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
				
				
			}else if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("downloadFoto")) {
				String idUser = request.getParameter("id");
				ModelLogin modelLogin = daoUsuario.consultaUsuarioId(idUser, super.getUserlogado(request));
				if(modelLogin.getFotouser() != null && !modelLogin.getFotouser().isEmpty()) {
					 response.setHeader("Content-Disposition", "attachment;filename=arquivo." + modelLogin.getExtensaofotoUser());
					 response.getOutputStream().write(new Base64().decodeBase64(modelLogin.getFotouser().split("\\,")[1]));
				}
				
				
			}else if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("paginar")) {
				
				Integer offset = Integer.parseInt(request.getParameter("pagina"));
				
				List<ModelLogin> modelLogins = daoUsuario.consultaUsuarioListPaginado(this.getUserlogado(request), offset);
				request.setAttribute("modelLogins", modelLogins);
				request.setAttribute("totalPagina", daoUsuario.totalPagina(this.getUserlogado(request)));
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response); 
				
			}else {
				List<ModelLogin> modelLogins = daoUsuario.consultaUsuarioList(super.getUserlogado(request));
				request.setAttribute("modelLogins", modelLogins);
				request.setAttribute("totalPagina", daoUsuario.totalPagina(this.getUserlogado(request)));
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
			}
			
		
		 }catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String msg = "OperaÃ§Ã£o realizada com sucesso";
			String id = request.getParameter("id");
			String nome = request.getParameter("nome");
			String email = request.getParameter("email");
			String login = request.getParameter("login");
			String senha = request.getParameter("senha");
			String perfil = request.getParameter("perfil");
			String sexo = request.getParameter("sexo");
			String cep = request.getParameter("cep");
			String logradouro = request.getParameter("logradouro");
			String bairro = request.getParameter("bairro");
			String localidade = request.getParameter("localidade");
			String uf = request.getParameter("uf");
			String numero = request.getParameter("numero");

			ModelLogin modelLogin = new ModelLogin();
			modelLogin.setId(id != null && !id.isEmpty() ? Long.parseLong(id) : null);
			modelLogin.setNome(nome);
			modelLogin.setEmail(email);
			modelLogin.setLogin(login);
			modelLogin.setSenha(senha);
			modelLogin.setPerfil(perfil);
			modelLogin.setSexo(sexo);
			modelLogin.setCep(cep);
			modelLogin.setLogradouro(logradouro);
			modelLogin.setBairro(bairro);
			modelLogin.setLocalidade(localidade);
			modelLogin.setUf(uf);
			modelLogin.setNumero(numero);
			
			if(ServletFileUpload.isMultipartContent(request)){
				Part part = request.getPart("fileFoto");//Pega foto da tela
				
				if(part.getSize() > 0) {
					byte[] foto = IOUtils.toByteArray(part.getInputStream()); //Converte imagem em byte
					String imagemBAse64 = "data:/" + part.getContentType().split("\\/")[1] + ";base64," +  new Base64().encodeBase64String(foto);
					
					modelLogin.setFotouser(imagemBAse64);
					modelLogin.setExtensaofotoUser(part.getContentType().split("\\/")[1]);
				}
			}
			
			if (daoUsuario.validarLogin(modelLogin.getLogin()) && modelLogin.getId() == null) {
				msg = "JÃ¡ existe usuÃ¡rio com o mesmo login, informe outro login";
			} else {
				if (!modelLogin.isNovo()) {
					msg = "Atualizado com sucesso!";
				}
				modelLogin = daoUsuario.gravarUsuario(modelLogin,super.getUserlogado(request));
			}
			
			List<ModelLogin> modelLogins = daoUsuario.consultaUsuarioList(super.getUserlogado(request));
			request.setAttribute("modelLogins", modelLogins);
			request.setAttribute("msg", msg);
			request.setAttribute("modelLogin", modelLogin);
			request.setAttribute("totalPagina", daoUsuario.totalPagina(this.getUserlogado(request)));
			request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
		}
	}

}
