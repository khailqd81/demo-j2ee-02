package service;

import entity.Student;
import org.apache.commons.beanutils.BeanUtils;
import repository.StudentRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@RequestScoped
public class StudentService {
    @Inject
    public StudentRepository studentRepository;
    public List<Student> findAll(){
        return studentRepository.findAll();
    }

    public int add(Student newSt){
        return studentRepository.add(newSt);
    }

    public Student findById(int id){
        return studentRepository.findById(id);
    }

    public int update(Student st) throws InvocationTargetException, IllegalAccessException {
        Student stInDb = studentRepository.findById(st.getId());
        if (stInDb == null) {
            throw new BadRequestException("Student not found");
        }
        BeanUtils.copyProperties(stInDb, st);
        return studentRepository.update(st);
    }

    public void remove(int id)  {
        Student st = studentRepository.findById(id);
        if (st == null) {
            throw new BadRequestException("Student not found");
        }
        studentRepository.remove(id);
    }

    public List<Student> findByName(String name){
        return studentRepository.findByName(name);
    }
}
