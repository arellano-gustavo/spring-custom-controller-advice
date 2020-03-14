package mx.com.cinepolis.anuncios.api.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import mx.com.cinepolis.anuncios.api.model.Empleado;

@Mapper
public interface EmpleadoMapper {
    @Select("select * from users7")
    List<Empleado> findAll() throws SQLException;

    @Insert("insert into users7(name,salary) values(#{name},#{salary})")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id",
            before = false, resultType = Integer.class)
    void insert(Empleado users) throws SQLException;
}
