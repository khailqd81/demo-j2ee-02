package com.example.demo13;

import entity.Student;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

@Path("/hello-world")
public class HelloResource {
    @Inject
    private EntityManager em;
    @GET
    @Produces("text/plain")
    public String hello() {
        String query = "select s from Student s";
        List<Student> list = em.createQuery(query).getResultList();
        for (Student s: list) {
            System.out.println(s);
        }
        return "Hello, World!";
    }
}