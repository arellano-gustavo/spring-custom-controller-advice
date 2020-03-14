package mx.com.cinepolis.anuncios.api.service;

import java.util.List;

import mx.com.cinepolis.anuncios.api.exceptions.DivByZeroException;
import mx.com.cinepolis.anuncios.api.exceptions.OutOfLoveException;
import mx.com.cinepolis.anuncios.api.exceptions.OutOfMoneyException;
import mx.com.cinepolis.anuncios.api.model.Empleado;

public interface EmpleadoService {
	/**
	 * Crea y retorna un objeto de tipo Empleado
	 * 
	 * @return Emplado (Objecto de negocio)
     * @throws OutOfMoneyException Transformador de negocio "Money"
	 */
    Empleado creaEmpleado() throws OutOfMoneyException;
    
    /**
     * Retorna todos los empleados de la base de datos
     * 
     * @return List<Empleado> lista de objetos de tipo Empleado
     * @throws OutOfLoveException Transformador de negocio "Love"
     */
    List<Empleado> getAll() throws OutOfLoveException;
    
    /**
     * Calcula la división entera de dos enteros.
     * 
     * @param a Numerador
     * @param b Denominador
     * @return int División entera
     * 
     * @throws DivByZeroException Se dispara cuando el denominador es cero
     */
    int divide(int a, int b) throws DivByZeroException;
}
