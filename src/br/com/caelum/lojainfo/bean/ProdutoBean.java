package br.com.caelum.lojainfo.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import br.com.caelum.lojainfo.dao.DAO;
import br.com.caelum.lojainfo.modelo.Produto;

@ManagedBean
@ViewScoped
public class ProdutoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Produto produto = new Produto();

	private Integer produtoId;


	public Produto getProduto() {
		return produto;
	}

	public Integer getProdutoId() {
		return produtoId;
	}

	public void setProdutoId(Integer produtoId) {
		this.produtoId = produtoId;
	}

	public List<Produto> getProdutos() {
		return new DAO<Produto>(Produto.class).listaTodos();
	}
	
	public void gravar() {
		System.out.println("Gravando produto " + this.produto.getNome());


		if (this.produto.getId() == null) {
			new DAO<Produto>(Produto.class).adiciona(this.produto);
		} else {
			new DAO<Produto>(Produto.class).atualiza(this.produto);
		}

		this.produto = new Produto();
	}

	public void carregar(Produto produto) {
		System.out.println("Carregando produto " + produto.getNome());
		this.produto = produto;
	}

	public void remover(Produto produto) {
		System.out.println("Removendo produto " + produto.getNome());
		new DAO<Produto>(Produto.class).remove(produto);
	}

	public void comecaComDigitoUm(FacesContext fc, UIComponent component,
			Object value) throws ValidatorException {

		String valor = value.toString();
		if (!valor.startsWith("1")) {
			throw new ValidatorException(new FacesMessage(
					"ISBN deveria começar com 1"));
		}

	}

	public void carregarProdutoPelaId() {
		this.produto = new DAO<Produto>(Produto.class).buscaPorId(produtoId);
	}

}
