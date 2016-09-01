package br.com.caelum.lojainfo.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.caelum.lojainfo.dao.UsuarioDao;
import br.com.caelum.lojainfo.modelo.Usuario;

@ManagedBean
@ViewScoped
public class LoginBean {

	private Usuario usuario = new Usuario();

	public Usuario getUsuario() {
		return usuario;
	}

	public String efetuaLogin() {
		System.out.println("Fazendo login do usuário " + this.usuario.getEmail());
		boolean existe = new UsuarioDao().existe(this.usuario);
		FacesContext context = FacesContext.getCurrentInstance();
		if (existe) {
			context.getExternalContext().getSessionMap().put("usuarioLogado", this.usuario);
			return "produto?faces-redirect=true";
		}
		context.getExternalContext().getFlash().setKeepMessages(true);
		context.addMessage(null, new FacesMessage("Usuario não encontrado"));
		return "login?faces-redirect=true";
	}
	
	public String deslogar(){
		System.out.println("Deslogando o Usuario " + this.usuario.getEmail());
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getSessionMap().remove("usuarioLogado");
		return "login?faces-redirect=true";
	}

}
