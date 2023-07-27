package controller;

import dto.StudentDto;
import entity.Student;
import org.modelmapper.ModelMapper;
import service.StudentService;

import javax.inject.Inject;
import javax.mvc.binding.BindingResult;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Path("student")
public class StudentController {
    @Inject
    private StudentService studentService;

    @Inject
    private ModelMapper mapper;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        return Response.status(Response.Status.OK).entity(studentService.findAll()).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(@Valid StudentDto dto) {
        Student newSt = new Student();
        mapper.map(dto, newSt);
        int newId = studentService.add(newSt);
        Map<String, Integer> result = new HashMap<>();
        result.put("newId", newId);
        return Response.status(Response.Status.OK).entity(result).build();
    }

    @PATCH
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(Student st) throws InvocationTargetException, IllegalAccessException {
        studentService.update(st);
        Map<String, String> result = new HashMap<>();
        result.put("success", "true");
        return Response.status(Response.Status.OK).entity(result).build();
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") int id) throws BadRequestException {
        studentService.remove(id);
        Map<String, String> result = new HashMap<>();
        result.put("success", "true");
        return Response.status(Response.Status.OK).entity(result).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") int id) {
        Student st = studentService.findById(id);
        if (st == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Student not found").build();
        }
        return Response.status(Response.Status.OK).entity(st).build();
    }

    @GET
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByName(@QueryParam("name") String name) {
        List<Student> st = studentService.findByName(name);
        return Response.status(Response.Status.OK).entity(st).build();
    }
}
