package org.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * @author jhkim
 * @since $DATE
 *
 */
public class Main {
	public static void main(String[] args) {

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("hello");
		EntityManager entityManager = factory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		try{
			Member member = new Member();
			member.setId(4L);
			member.setName("test");

			entityManager.persist(member);//jpa 저장
			transaction.commit();

			Member findMember = entityManager.find(Member.class, 1L);
			System.out.println("findMember.getId() = " + findMember.getId());
			System.out.println("findMember.getName() = " + findMember.getName());

			findMember.setName("hello jpa");
			transaction.commit();
			System.out.println("findMember.getName() = " + findMember.getName());
		} catch (Exception e) {
			transaction.rollback();
		} finally {
			entityManager.close();
			factory.close();
		}
	}
}