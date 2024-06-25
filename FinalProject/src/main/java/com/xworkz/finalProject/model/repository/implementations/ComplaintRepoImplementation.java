package com.xworkz.finalProject.model.repository.implementations;

import com.xworkz.finalProject.dto.ComplaintDTO;
import com.xworkz.finalProject.model.repository.interfaces.ComplaintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.Collections;
import java.util.List;

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
        EntityTransaction entityTransaction= entityManager.getTransaction();
        try {
            entityTransaction.begin();
            entityManager.persist(complaintDTO);
            entityTransaction.commit();
            return true;
        }catch (Exception e){
            System.out.println(e);
            entityTransaction.rollback();
        }finally {
            entityManager.close();
        }
        return false;
    }



    @Override
    public List<ComplaintDTO> findByUserIdAndStatus(int userId, String status) {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        try {
            Query query= entityManager.createQuery("select c FROM ComplaintDTO c WHERE c.userId = :userId AND c.status = :status", ComplaintDTO.class);
            query.setParameter("userId", userId);
            query.setParameter("status", status);
            List<ComplaintDTO> list=   query.getResultList();
            return list;
        } finally {
            entityManager.close();
        }
    }

}


