package br.com.caelum.lojainfo.dao;

import javax.persistence.EntityManager;

import br.com.caelum.lojainfo.modelo.Produto;
import br.com.caelum.lojainfo.modelo.Usuario;

public class PopulaBanco2 {

	public static void main(String[] args) {

		EntityManager em = new JPAUtil().getEntityManager();

		em.getTransaction().begin();

		Produto a = geraProduto("roteador", "internacional", 175, 7);
		em.persist(a);

		Produto a1 = geraProduto("roteador", "nacional", 75, 17);
		em.persist(a1);
		
		Produto a2 = geraProduto("repetidor", "internacional", 105, 5);
		em.persist(a2);
		
		Produto a3 = geraProduto("notebook", "internacional", 5000, 7);
		em.persist(a3);
		
		Usuario u = new Usuario();
		u.setEmail("diego@email.com");
		u.setSenha("Abc1234");
		em.persist(u);
		
		em.getTransaction().commit();
		em.close();

	}

	private static Produto geraProduto(String nome, String descricao, double preco, int qtd) {
		Produto p = new Produto();
		p.setNome(nome);
		p.setDescricao(descricao);
		p.setPreco(preco);
		p.setQtdEstoque(qtd);
		return p;
	}

	
}
