package br.com.pedhped.jpa.testes;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.pedhped.jpa.modelo.Conta;

public class AtualizaSaldoConta {

	public static void main(String[] args) {
		
		/*Chamando a JPA para criar a tabela de banco de dados contas gerado apartir da classe "Contas.java" */
		
		/* o valor dentro de "createEntityManagerFactory()" vem do arquivo persistence.xml, na linha onde consta: <persistence-unit name="contas">*/
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("contas");
		/*Usando a F�brica. */
		EntityManager em = emf.createEntityManager();
		
		/*Recuperando um registro do banco de dados: onde find() recebe a class e o "id" com o tipo "L"(Long) do registro*/
		Conta contaPedro = em.find(Conta.class,1L);
		
		System.out.printf("Conta do Pedro --> %s %n",contaPedro.getTitular());
		
		/*Iniciando uma transa��o*/
		em.getTransaction().begin();
		
		/*OBS: veja que contaPedro n�o foi relacionada ao hibernate e mesmo assim o saldo foi atualizado na tabela do banco de dados.
		 * Explica��o por que isso � poss�vel: 
		 * 
		 * Quando fazemos um find() no EntityManager, a JPA e o Hibernate buscar�o 
		 * no banco e criar�o um objeto tipo Conta para ser devolvido, representando o registro buscado no database.
			Essa Conta devolvida ainda mant�m uma refer�ncia, ent�o a JPA ainda a 
			conhece mesmo ap�s a devolu��o. Sendo assim, costuma-se dizer que 
			esta entidade Conta se encontra no estado Managed, ou seja, gerenciado pela JPA.
		 * */
		contaPedro.setSaldo(5000000.00);
		
		/*Enviando todas as transa��es*/
		em.getTransaction().commit();
		
		emf.close();

	}

}
