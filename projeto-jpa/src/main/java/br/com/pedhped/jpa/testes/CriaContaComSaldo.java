package br.com.pedhped.jpa.testes;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.pedhped.jpa.modelo.Conta;

public class CriaContaComSaldo {

	public static void main(String args[]) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("contas");
		EntityManager em = emf.createEntityManager();

		/* Instância da classe Conta.java */
		Conta conta = new Conta();
		conta.setTitular("Elena");
		conta.setNumero(12567);
		conta.setAgencia(4487);
		conta.setSaldo(1000.00);

		/*
		 * Todo comando executado na JPA obrigatóriamente deve estar dentro de uma
		 * transação
		 */
		em.getTransaction().begin();

		/* Inserindo dados no banco */
		em.persist(conta);

		/*
		 * Essa linha realiza um update no registro da tabela no banco apesar do
		 * registro acabar de ter sido criado, pois quando transformamos
		 * (em.persist(conta)) a "conta" em manager O JPA automaticamente SETA um "id"
		 * pra conta o que possibilita o UPDATE no registro do banco de dados
		 */
		conta.setSaldo(4500000.00);

		/*
		 * Executa todas as transações de uma vez, caso alguma dê erro, tudo volta ao
		 * estado anterior(Rollback). rollback é uma operação que retorna o banco de
		 * dados a algum estado anterior
		 */
		em.getTransaction().commit();

		em.close();

		System.out.printf("ID da conta da Elena: %d", conta.getId());

		/*
		 * Esta atualização abaixo não vai surtir efeito no banco de dados, pois após o
		 * close() a conta deixa de ser manager e se torna Detached(ou seja independente
		 */
		conta.setSaldo(500.00);

		/* COMO TRANSFORMAR UM DETACHED EM MANAGER PARA QUE A ATUALIZAÇÃO FUNCIONE */
		EntityManager em2 = emf.createEntityManager();
		em2.getTransaction().begin();
		
		conta.setSaldo(800.00);

		em2.merge(conta);
		em2.getTransaction().commit();

	}

}
