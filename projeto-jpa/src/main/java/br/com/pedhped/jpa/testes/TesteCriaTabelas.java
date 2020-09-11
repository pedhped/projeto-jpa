package br.com.pedhped.jpa.testes;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TesteCriaTabelas {

	public static void main(String[] args) {
		
		
		/*Chamando a JPA para criar a tabela de banco de dados contas gerado apartir da classe "Contas.java" */
		
		/* o valor dentro de "createEntityManagerFactory()" vem do arquivo persistence.xml, na linha onde consta: <persistence-unit name="contas">*/
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("contas");
		
		/*Usando a Fábrica. */
		EntityManager createEntityManager = emf.createEntityManager();
			
		/*Fechando a fábrica*/
		emf.close();
		
		
	}

}
