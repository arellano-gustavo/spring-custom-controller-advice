package mx.com.cinepolis.anuncios.api.model;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class Empleado {
    private Integer id;
	
    @NotNull(message = "Please provide a name")
    @Size(min=3, message = "Please provide a name bigger than 3")
    private String name;
    
    @NotNull(message = "Please provide a salary")
    @DecimalMin(message = "El salario no puede ser inferior a 10.00", value="10.00")
    private Long salary;
    
    @NotNull(message = "Please provide a mail")
    @Pattern(regexp = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$", message = "Please privide a fucking right mail asshole !!!")
    private String mail;

    public Empleado() {}


    public Empleado(Integer id, String name, Long salary) {
        this.name = name;
        this.salary = salary;
        this.id = id;
    }

    public Empleado(Integer id, String name, Long salary, String mail) {
        this.name = name;
        this.salary = salary;
        this.id = id;
        this.mail = mail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

}
