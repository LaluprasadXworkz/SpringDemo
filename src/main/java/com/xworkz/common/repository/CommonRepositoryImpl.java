package com.xworkz.common.repository;

import com.xworkz.common.entity.RegisterEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Repository
@Slf4j
public class CommonRepositoryImpl implements CommonRepository {

    @Autowired
    private EntityManagerFactory emf;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public boolean saveRegisterDetails(RegisterEntity entity) {
        log.info("Invoking saveRegisterDetails");
        try {
            EntityManager manager = emf.createEntityManager();
            manager.getTransaction().begin();
            manager.persist(entity);
            manager.getTransaction().commit();
            log.info("Data saved to DB: {}", entity.getUserName());
            return true;
        } catch (Exception e) {
            log.error("Error while saving register details", e);
            return false;
        }
    }

    @Override
    public RegisterEntity getRegisterByEmail(String email) {
        EntityManager manager = emf.createEntityManager();
        try {
            return (RegisterEntity) manager.createNamedQuery("checkEmail").setParameter("email", email).getSingleResult();
        } catch (NoResultException e) {
            log.warn("No user found with email: {}", email);
            return null;
        } catch (Exception e) {
            log.error("Error fetching user by email", e);
            return null;
        } finally {
            manager.close();
        }
    }

    @Override
    public RegisterEntity getRegisterByPhoneNumber(Long mobileNumber) {
        EntityManager manager = emf.createEntityManager();
        try {
            return (RegisterEntity) manager.createNamedQuery("ValidateRegisterByPhoneNumber").setParameter("mobileNumber", mobileNumber).getSingleResult();
        } catch (NoResultException e) {
            log.warn("No user found with phone number: {}", mobileNumber);
            return null;
        } catch (Exception e) {
            log.error("Error fetching user by phone number", e);
            return null;
        } finally {
            manager.close();
        }
    }

    @Override
    public void updateOtpByEmail(String newOtp, String email) {
        log.info("updateOtpByEmail: {}, {}", newOtp, email);
        EntityManager manager = emf.createEntityManager();
        try {
            manager.getTransaction().begin();
            manager.createNamedQuery("setOtpByEmail").setParameter("newOtp", newOtp).setParameter("email", email).executeUpdate();
            manager.getTransaction().commit();
            log.info("OTP updated successfully for email: {}", email);
        } catch (Exception e) {
            log.error("Error updating OTP", e);
            if (manager.getTransaction().isActive()) {
                manager.getTransaction().rollback();
            }
        } finally {
            manager.close();
        }
    }

    @Override
    public RegisterEntity getRegisterById(Integer id) {
        EntityManager manager = emf.createEntityManager();
        try {
            RegisterEntity entity = manager.find(RegisterEntity.class, id);
            log.info("getRegisterById Repo: {}", entity);
            return entity;
        } catch (Exception e) {
            log.error("Error fetching user by ID", e);
            return null;
        } finally {
            manager.close();
        }
    }

    @Override
    public boolean updateRegister(RegisterEntity entity) {
        log.info("updateRegister Repo: {}", entity);
        EntityManager manager = emf.createEntityManager();
        try {
            manager.getTransaction().begin();
            manager.createNamedQuery("updateRegisterByImageName").setParameter("userName", entity.getUserName()).setParameter("phoneNumber", entity.getPhoneNumber()).setParameter("imageName", entity.getImageName()).setParameter("registerId", entity.getRegisterId()).executeUpdate();
            manager.getTransaction().commit();
            log.info("Data updated successfully for email: {}", entity.getEmail());
            return true;
        } catch (Exception e) {
            log.error("Error updating register", e);
            if (manager.getTransaction().isActive()) {
                manager.getTransaction().rollback();
            }
            return false;
        } finally {
            manager.close();
        }
    }
}
