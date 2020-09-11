package br.com.pedhped.jpa.testes;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.pedhped.jpa.modelo.Cliente;
import br.com.pedhped.jpa.modelo.Conta;

public class TestaRelacionamentoClienteConta {

	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("contas");
		EntityManager em = emf.createEntityManager();
		
		Conta conta = new Conta();
		conta.setId(1L);
		
		Cliente cliente = new Cliente();
		cliente.setNome("Selena Goncalves");
		cliente.setEndereco("Quadra 06 Cojunto 9, casa 10");
		cliente.setProfissao("Analista");
		cliente.setConta(conta);
						
		em.getTransaction().begin();
		
		em.persist(cliente);

		em.getTransaction().commit();
		
		em.close();

	}

}
