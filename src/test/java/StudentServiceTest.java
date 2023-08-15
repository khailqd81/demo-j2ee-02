
import entity.Student;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import repository.StudentRepository;
import service.StudentService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class StudentServiceTest {

    StudentService studentService;

    StudentRepository studentRepository = mock(StudentRepository.class);

    @Before
    public void initialize() {
        this.studentService = new StudentService(studentRepository);
    }

    @Test
    public void findAll() {
        Assert.assertTrue(studentService.findAll().isEmpty());
    }

    @Test
    public void add() {
        Student st = mock(Student.class);
        when(studentRepository.add(st)).thenReturn(1);
        Assert.assertEquals(1, studentService.add(st));
    }

    @Test
    public void findById() {
        Student st = mock(Student.class);
        when(studentRepository.findById(1)).thenReturn(st);
        Assert.assertEquals(st, studentService.findById(1));
    }

    @Test
    public void findByName() {
        Student st = mock(Student.class);
        List<Student> list = new ArrayList<>();
        list.add(st);
        when(studentRepository.findByName("Khai")).thenReturn(list);
        Assert.assertEquals(list, studentService.findByName("Khai"));
    }
}
