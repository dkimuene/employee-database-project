package com.example.cruddemo.dao;

import com.example.cruddemo.entity.Employee;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO{

    // define field for entitymanager
    private EntityManager entityManager;

    // set up constructor injection
    @Autowired
    public EmployeeDAOHibernateImpl(EntityManager theEntityManager){
        this.entityManager = theEntityManager;
    }

    @Override
    public List<Employee> findAll() {

        // get the current hibernate session
        Session currrentSession = entityManager.unwrap(Session.class);

        //create a query
        Query<Employee> theQuery =
                currrentSession.createQuery("from Employee", Employee.class);

        // execute query and get result list
        List<Employee> employees = theQuery.getResultList();

        // return the result
        return employees;
    }

    @Override
    public Employee findById(int theId) {

        //get the current hibernate session
        Session currrentSession = entityManager.unwrap(Session.class);

        // get the employee
        Employee theEmployee =
                currrentSession.get(Employee.class, theId);

        // return the employee
        return theEmployee;
    }

    @Override
    public void save(Employee theEmployee) {

        //get the current hibernate session
        Session currrentSession = entityManager.unwrap(Session.class);

        // save employee
        currrentSession.save(theEmployee);

    }

    @Override
    public void deleteById(int theId) {

        //get the current hibernate session
        Session currrentSession = entityManager.unwrap(Session.class);

        // delete employee with primary key
        Query theQuery =
                currrentSession.createQuery("delete from Employee where id=:employeeId");
        theQuery.setParameter("employeeId", theId);

        theQuery.executeUpdate();

    }
}
