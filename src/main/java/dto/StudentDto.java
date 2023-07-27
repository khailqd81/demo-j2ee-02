package dto;


import javax.validation.constraints.*;

public class StudentDto {

    @NotNull
    @NotEmpty
    @NotBlank
    private String name;

    @Size(min = 2)
    private String address;
    @NotNull
    @Min(value = 7)
    @Max(value = 30)
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "StudentDto{" +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", age=" + age +
                '}';
    }
}

