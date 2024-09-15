package com.xworkz.finalProject.model.repository.implementations;

import com.xworkz.finalProject.dto.ComplaintDTO;
import com.xworkz.finalProject.model.repository.interfaces.ComplaintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

@Repository
public class ComplaintRepoImplementation implements ComplaintRepository {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public ComplaintRepoImplementation(){
        System.out.println("No argument constructor for ComplaintRepoImplementation");
    }


    @Override
    public boolean saveTheComplaints(ComplaintDTO complaintDTO) {
        EntityManager entityManager= entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction=entityManager.getTransaction();
        try {
            entityTransaction.begin();
            entityManager.persist(complaintDTO);
            entityTransaction.rollback();
            return  true;

        }catch (Exception e){
            System.out.println(e);
            entityTransaction.commit();
        }finally {
            entityManager.close();
        }

        return false;
    }
}
