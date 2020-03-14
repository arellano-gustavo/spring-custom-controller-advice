package mx.com.cinepolis.anuncios.api.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.cinepolis.anuncios.api.exceptions.DivByZeroException;
import mx.com.cinepolis.anuncios.api.exceptions.OutOfLoveException;
import mx.com.cinepolis.anuncios.api.exceptions.OutOfMoneyException;
import mx.com.cinepolis.anuncios.api.mapper.EmpleadoMapper;
import mx.com.cinepolis.anuncios.api.model.Empleado;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {
    @Autowired
    private EmpleadoMapper empleadoMapper;

    @Override
    public Empleado creaEmpleado() throws OutOfMoneyException {
        Empleado e = new Empleado(1, "gus", 12345L);
        if(e.getSalary()==0) throw new OutOfMoneyException("Corto creaEmpleado method", "Largo creaEmpleado method", "ERR-BBCA-110");
        return e;
    }

    @Override
    public List<Empleado> getAll() throws OutOfLoveException {
        try {
			return empleadoMapper.findAll();
		} catch (SQLException e) {
			throw new OutOfLoveException("Cortito para findAll", "Largo para findAll", "ERR-FFA0-120");
		}
    }

    @Override
    public int divide(int a, int b) throws DivByZeroException {
    	if(b==0) throw new DivByZeroException("Division por cero", "Esta función no está definida para divisores con valor 0", "ERR-071774-AFFD-140");
    	return a/b;
    }
}
