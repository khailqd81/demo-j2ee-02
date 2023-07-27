package repository;

import entity.Student;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@RequestScoped
@Transactional
public class StudentRepository {
    @Inject
    private EntityManager em;

    public List<Student> findAll() {
        String queryStr = "select s from Student s";
        return em.createQuery(queryStr).getResultList();
    }

    public int add(Student newSt) {
        em.persist(newSt);
        return newSt.getId();
    }

    public Student findById(int id) {
        return em.find(Student.class, id);
    }

    public int update(Student st) {
        em.merge(st);
        return st.getId();
    }

    public void remove(int id) {
        String queryStr = "delete from Student s where s.id =:id";
        em.createQuery(queryStr).setParameter("id", id).executeUpdate();
    }

    public List<Student> findByName(String name) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Student> criteria = cb.createQuery(Student.class);
        Root<Student> student = criteria.from(Student.class);
        criteria.select(student).where(cb.like(student.get("name"), "%" + name + "%"));
        return em.createQuery(criteria).getResultList();
    }
}
