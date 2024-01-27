package com.pranv.cruddemp.dao;

import com.pranv.cruddemp.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDAOImpl implements StudentDAO{

    //define entity manager
    private EntityManager theEntityManager;

    //inject entity manager using constructor injection
    @Autowired
    public StudentDAOImpl(EntityManager entityManager){
        theEntityManager = entityManager;
    }

    //define save method
    @Override
    @Transactional
    public void save(Student student) {
        theEntityManager.persist(student);
    }
}
