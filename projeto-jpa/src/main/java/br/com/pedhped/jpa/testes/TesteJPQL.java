package br.com.pedhped.jpa.testes;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import javax.persistence.Query;

import br.com.pedhped.jpa.modelo.Conta;
import br.com.pedhped.jpa.modelo.Movimentacao;

/*
 	A Jakarta Persistence Query Language � uma linguagem de consulta orientada 
 	a objetos independente de plataforma definida como parte da especifica��o Jakarta Persistence. 
 	JPQL � usado para fazer consultas em entidades armazenadas em um banco de dados relacional
 
 */

public class TesteJPQL {

	public static void main(String[] args) {
		
		
				
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("contas");
		EntityManager em = emf.createEntityManager();
		
		/*
		 * A consulta abaixo n�o usa tabela e colunas como refer�ncia, mas sim objetos e atributos.
		 * 
		 * Movimenta��o = Movimentacao.java
		 * conta.id = :pConta (p = parametro e os dois pontos indica a JPA que isto � um par�metro, que ser� indicado em setParameter()
		 * 
		 * */
		
		/*Cria um objeto conta e seta o Id que deseja buscar no banco, este objeto ser� usado como parametro*/
		Conta conta = new Conta();
		conta.setId(1L);	 /*o 1 � o id na tabela no banco de dados*/
		
		String jpql = "select m from Movimentacao m where m.conta = :pConta";
		
		Query query = (Query) em.createQuery(jpql);
		query.setParameter("pConta", conta);
		
		List<Movimentacao> resultList = query.getResultList();
		
		System.out.println();
		
		for (Movimentacao movimentacao : resultList) {
			System.out.println("Descricao: "+ movimentacao.getDescricao());
			System.out.println("Tipo: "+movimentacao.getTipoMovimentacao());
		}
	}

}
