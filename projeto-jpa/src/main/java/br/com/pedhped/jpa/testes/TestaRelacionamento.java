package br.com.pedhped.jpa.testes;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.pedhped.jpa.modelo.Conta;
import br.com.pedhped.jpa.modelo.Movimentacao;
import br.com.pedhped.jpa.modelo.TipoMovimentacao;

public class TestaRelacionamento {

	public static void main(String[] args) {
		
		Conta c1 = new Conta();
		c1.setAgencia(124563);
		c1.setNumero(9786322);
		c1.setSaldo(300.0);
		c1.setTitular("Pâmela Maria");
		
		Movimentacao mov1 = new Movimentacao();
		mov1.setDate(LocalDateTime.now());
		mov1.setDescricao("Churrascaria");
		mov1.setValor(new BigDecimal(200.0));
		mov1.setTipoMovimentacao(TipoMovimentacao.ENTRADA);
		mov1.setConta(c1);
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("contas");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
		/*em.persist(c1) --> Antes de persistir a mov1, c1 deve ser persistido virando managed, 
		 * para que tenha um "id" para referenciar na mov1*/
		em.persist(c1);
		em.persist(mov1);
		em.getTransaction().commit();
		
		em.close();
	}

}
