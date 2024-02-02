package com.pranv.cruddemp.dao;

import com.pranv.cruddemp.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO {

    //define entity manager
    private EntityManager theEntityManager;

    //inject entity manager using constructor injection
    @Autowired
    public StudentDAOImpl(EntityManager entityManager) {
        theEntityManager = entityManager;
    }

    //define save method
    @Override
    @Transactional
    public void save(Student student) {
        theEntityManager.persist(student);
    }

    @Override
    public Student findById(Integer id) {
        return theEntityManager.find(Student.class, id);
    }

    @Override
    public List<Student> findAll() {
        TypedQuery<Student> query = theEntityManager.createQuery("FROM Student order by lastName",Student.class);

        List<Student> studentList = query.getResultList();

        return  studentList;
    }

    @Override
    public List<Student> findByLastName(String lastName){

        TypedQuery<Student> query = theEntityManager.createQuery("FROM Student WHERE lastName = :theData",Student.class);

        query.setParameter("theData",lastName);

        return query.getResultList();

    }

    @Override
    @Transactional
    public void update(Student student) {
        theEntityManager.merge(student);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        Student student = findById(id);
        theEntityManager.remove(student);
    }

    @Override
    @Transactional
    public int deleteAll() {
        int rowsDeleted = theEntityManager.createQuery("DELETE FROM Student").executeUpdate();
        return rowsDeleted;
    }

}