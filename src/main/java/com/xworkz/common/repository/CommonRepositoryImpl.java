package com.xworkz.common.repository;

import com.xworkz.common.entity.RegisterEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

@Repository
public class CommonRepositoryImpl implements  CommonRepository{
    @Override
    public boolean saveRegisterDetails(RegisterEntity entity) {
        System.out.println("Invoking saveRegisterDetails ");
        boolean isSaved=false;
        if(entity!=null) {
            EntityManager manager = Persistence.createEntityManagerFactory("common-module").createEntityManager();
            manager.getTransaction().begin();
            manager.persist(entity);
            manager.getTransaction().commit();
            System.out.println("Data saved to DB :"+entity.getUserName());
            isSaved=true;
        }
        return isSaved;
    }
}
