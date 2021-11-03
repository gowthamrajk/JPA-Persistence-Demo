package com.gowthamrajk.client;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import org.hibernate.query.Query;
import com.gowthamrajk.jpapersistence.Doctor;

public class DoctorDashboard {

	public static void main(String[] args) {

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("doctorDatabase");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		Doctor doctor1 = new Doctor(101, "doctor1", "doctor1@gmail.com", "ENT", 10);
		Doctor doctor2 = new Doctor(102, "doctor2", "doctor2@gmail.com", "CARDIOLOGY", 15);
		Doctor doctor3 = new Doctor(103, "doctor3", "doctor3@gmail.com", "GYNACOLOGY", 8);

		
		entityManager.persist(doctor1);
		entityManager.persist(doctor2);
		entityManager.persist(doctor3);
		
		TypedQuery<Doctor> query = (TypedQuery)entityManager.createNamedQuery("orderByExperience");
		System.out.println(query.getResultList() + "\n");
		
		
		Doctor doctorObj = (Doctor)entityManager.find(Doctor.class, 103);		
		System.out.println(doctorObj + "\n");
		
		
		doctor2.setDoctorName("doctor2Updated");
		query = (TypedQuery)entityManager.createNamedQuery("getAllDoctors");
		System.out.println(query.getResultList() + "\n");
		
		
		entityManager.remove(doctor3);
		query = (TypedQuery)entityManager.createQuery("from Doctor");
		System.out.println(query.getResultList() + "\n");
		
		transaction.commit();
		entityManager.close();

	}
}
