package br.com.pedhped.jpa.testes;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.pedhped.jpa.modelo.Conta;
import br.com.pedhped.jpa.modelo.Movimentacao;

/*
 * Já quando trabalhamos com JPA, há uma linguagem chamada Java Persistence Query Language ou JPQL, 
 * a qual é uma query de mais alto nível que nos permitirá escrevê-la 
 * usando o nome de nossos objetos, classes e atributos, e não mais por meio dos nomes de tabelas e colunas.
 	JPQL é usado para fazer consultas em entidades armazenadas em um banco de dados relacional
 
 */

public class TesteJPQL {

	public static void main(String[] args) {
		
						
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("contas");
		EntityManager em = emf.createEntityManager();
		
		/*
		 * A consulta abaixo não usa tabela e colunas como referência, mas sim objetos e atributos.
		 * 
		 * Movimentação = Movimentacao.java
		 * conta.id = :pConta (p = parametro e os dois pontos indica a JPA que isto é um parâmetro, que será indicado em setParameter()
		 * 
		 * */
		
		/*Cria um objeto conta e seta o Id que deseja buscar no banco, este objeto será usado como parametro*/
		Conta conta = new Conta();
		conta.setId(1L);	 /*o 1 é o id na tabela no banco de dados*/
		
		//String jpql = "select m from Movimentacao m where m.conta = :pConta";
		
		/*usando order by*/
		String jpql = "select m from Movimentacao m where m.conta = :pConta order by m.valor desc";
		
		/*TypedQuery: Define um tipo específico de query*/
		TypedQuery<Movimentacao> query = em.createQuery(jpql, Movimentacao.class);
		query.setParameter("pConta", conta);
		
		
		List<Movimentacao> resultList = query.getResultList();
		
		System.out.println();
		
		for (Movimentacao movimentacao : resultList) {
			System.out.println("Descricao: "+ movimentacao.getDescricao());
			System.out.println("Tipo: "+movimentacao.getTipoMovimentacao());
		}
	}

}











