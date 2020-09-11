package br.com.pedhped.jpa.testes;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.pedhped.jpa.modelo.Conta;

public class TestandoEstados {

	public static void main(String[] args) {
		
		//Estado Transient
		Conta conta = new Conta();
		conta.setTitular("Alfredo Simas");
		conta.setAgencia(45666);
		conta.setNumero(23564);
		/*ATÉ AQUI SEM JPA*/
		
		/*TRANSFORMANDO EM MANAGED COM JPA*/
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("contas");
		EntityManager em = emf.createEntityManager();
		
		
		em.getTransaction().begin();

		// Transient -> Managed
		em.persist(conta);
		
		//Managed -> Removed
		//Removida com o contexto do JPA, remove tambem do banco de dados
		em.remove(conta);		
		
		em.getTransaction().commit();
		
	}

}
