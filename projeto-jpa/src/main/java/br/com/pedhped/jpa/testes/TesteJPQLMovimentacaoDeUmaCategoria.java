package br.com.pedhped.jpa.testes;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.pedhped.jpa.modelo.Categoria;
import br.com.pedhped.jpa.modelo.Conta;
import br.com.pedhped.jpa.modelo.Movimentacao;

/*
 * Já quando trabalhamos com JPA, há uma linguagem chamada Java Persistence Query Language ou JPQL, 
 * a qual é uma query de mais alto nível que nos permitirá escrevê-la 
 * usando o nome de nossos objetos, classes e atributos, e não mais por meio dos nomes de tabelas e colunas.
 	JPQL é usado para fazer consultas em entidades armazenadas em um banco de dados relacional
 
 */

public class TesteJPQLMovimentacaoDeUmaCategoria {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("contas");
		EntityManager em = emf.createEntityManager();

		Categoria categoria = new Categoria();
		categoria.setId(3L);

		String jpql = "select m from Movimentacao m join m.categorias c where c = :pCategoria";

		/* TypedQuery: Define um tipo específico de query */
		TypedQuery<Movimentacao> query = em.createQuery(jpql, Movimentacao.class);
		query.setParameter("pCategoria", categoria);

		List<Movimentacao> resultList = query.getResultList();

		System.out.println("\n\n\n----------------LISTANDO--------------------");

		for (Movimentacao movimentacao : resultList) {
			System.out.println("Categorias: " + movimentacao.getCategorias());
			System.out.println("Tipo: " + movimentacao.getTipoMovimentacao());
			System.out.println("Descricao: " + movimentacao.getDescricao());
			System.out.println("Tipo: " + movimentacao.getTipoMovimentacao());
		}
	}

}
