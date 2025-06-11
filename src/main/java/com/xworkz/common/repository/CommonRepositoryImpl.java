package com.xworkz.common.repository;

import com.xworkz.common.entity.RegisterEntity;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;


@Repository
public class CommonRepositoryImpl implements  CommonRepository{

    @Autowired
    EntityManagerFactory emf;

    @Override
    public boolean saveRegisterDetails(RegisterEntity entity) {
        System.out.println("Invoking saveRegisterDetails ");
        boolean isSaved=false;
        if(entity!=null) {
            EntityManager manager=emf.createEntityManager();
            manager.getTransaction().begin();
            manager.persist(entity);
            manager.getTransaction().commit();
            System.out.println("Data saved to DB :"+entity.getUserName());
            isSaved=true;
        }
        return isSaved;
    }

    @Override
    public RegisterEntity getRegisterByEmail(String email) {
      return (RegisterEntity) emf.createEntityManager().createNamedQuery("checkEmail").setParameter("email",email).getSingleResult();

    }


}
