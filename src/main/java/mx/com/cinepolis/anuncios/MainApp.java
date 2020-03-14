package mx.com.cinepolis.anuncios;

import org.apache.ibatis.type.MappedTypes;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import mx.com.cinepolis.anuncios.api.model.Empleado;

@MappedTypes(Empleado.class)
@MapperScan("mx.com.cinepolis.anuncios.api.mapper")
@SpringBootApplication
public class MainApp {
    public static void main(String[] args) {
        SpringApplication.run(MainApp.class, args);
    }
}
