package br.com.pedhped.jpa.testes;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.pedhped.jpa.modelo.Conta;

public class CriaConta {

	public static void main(String args[]) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("contas");
		EntityManager em = emf.createEntityManager();
		
		/*Inst�ncia da classe Conta.java*/
		Conta conta = new Conta();
		conta.setTitular("Pedro Henrique");
		conta.setNumero(12345);
		conta.setAgencia(4236);		
		
		/*Todo comando executado na JPA obrigat�riamente deve estar dentro de uma transa��o*/
		em.getTransaction().begin();
		
		/*Inserindo dados no banco*/
		em.persist(conta);
		
		/*Executa todas as transa��es de uma vez, caso alguma d� erro, 
		 * tudo volta ao estado anterior(Rollback). 
		 rollback � uma opera��o que retorna o banco de dados a algum estado anterior
		 * */
		em.getTransaction().commit();
	}

}
