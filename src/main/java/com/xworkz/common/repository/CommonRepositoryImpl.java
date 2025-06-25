package com.xworkz.common.repository;

import com.xworkz.common.entity.RegisterEntity;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;


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
        try {
            return (RegisterEntity) emf.createEntityManager().createNamedQuery("checkEmail").
                    setParameter("email", email).
                    getSingleResult();
        }catch (Exception e){
            return  null;
        }
    }

    @Override
    public RegisterEntity getRegisterByPhoneNumber(Long mobileNumber) {
        try{
            return (RegisterEntity) emf.createEntityManager().createNamedQuery("ValidateRegisterByPhoneNumber")
                    .setParameter("mobileNumber",mobileNumber).
                    getSingleResult();
        }catch (Exception e){
            return null;
        }

    }


}
