package mx.com.cinepolis.anuncios.api.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mx.com.cinepolis.anuncios.api.exceptions.DivByZeroException;
import mx.com.cinepolis.anuncios.api.exceptions.OutOfLoveException;
import mx.com.cinepolis.anuncios.api.exceptions.OutOfMoneyException;
import mx.com.cinepolis.anuncios.api.model.Empleado;
import mx.com.cinepolis.anuncios.api.service.EmpleadoService;
import mx.com.cinepolis.anuncios.api.service.HealthService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "administracion")
@RequestMapping(value = "/")
public class EmpleadoController {
    @Autowired
    private EmpleadoService empleadoService;
    
    @Autowired
    private HealthService healthService;

    @GetMapping(
        value = "/all-employees.json",
        produces = "application/json; charset=utf-8")
    public List<Empleado> getAllEmployees() throws OutOfLoveException {
        return empleadoService.getAll();
    }
    
    @GetMapping(
        value = "/services.json",
        produces = "application/json; charset=utf-8")
    public Empleado getEmpleado() throws OutOfMoneyException {
        return empleadoService.creaEmpleado();
    }
    
    @GetMapping(
        path = "/logout.json",
        produces = "application/json; charset=utf-8")
    public String logout(HttpServletRequest request) throws ServletException {
        String name = "tavo";
        request.logout();
        String res="{-"+name+"-:-you have been loged out-}";
        return res.replace('-', '"');
    }

    @ApiOperation(
        value = "AdminController::getAllUsers",
        notes = "Regresa una lista de todos los usuarios en el sistema "
            + "debidamente paginados con base en el payload de "
            + "request que determina el tamaño de la página, la "
            + "longitud de la página, el campo por el que se va a "
            + "ordenar y si el orden es ascendente o descendente."
            + "<br/><br/>"
            + "En el caso de que los parámetros proporcionados "
            + "<b><i><label style='color:blue;'>excedan</label><i></b> las "
            + "dimensiones de la lista real de datos, este método es "
            + "capaz de ajustar lo necesario para que la lista resultante "
            + "sea suceptible de ser manipulada adecuadamente.")
    @PostMapping(
            value = "/sample.json",
            produces = "application/json; charset=utf-8")
    public Empleado sample(@Valid @RequestBody Empleado emp) throws OutOfMoneyException {
        return empleadoService.creaEmpleado();
    }

/* 
curl \
--location \
--request POST 'http://192.168.100.4:8081/sample' \
--header 'Content-Type: application/json' \
--data-raw '{
    "id":1,
	"name":"Rod",
	"salary":12345
}'


 curl -X POST 'http://localhost:8081/sample.json' \
--header 'Content-Type: application/json' \
--data-raw '{
	"mail":"xxx",
	"name":"yyy",
	"salary":5
}'

Resulta en:

{
"errors":
	[
		"El salario no puede ser inferior a 10.00",
		"Please privide a fucking right mail asshole !!!"
	],
"timestamp":1584210422892
}



 */

    
    @GetMapping(
            value = "/sample2.json",
            produces = "application/json; charset=utf-8")
    public Empleado sample2() throws OutOfMoneyException {
        return empleadoService.creaEmpleado();
    }
    
    @PostMapping(
            value = "/otro.json",
            produces = "application/json; charset=utf-8")
    public Empleado otro(@Valid @RequestBody Empleado emp) throws OutOfMoneyException {
        return empleadoService.creaEmpleado();
    }
    
    @GetMapping(
            value = "/divide.json",
            produces = "application/json; charset=utf-8")
    public Map<String, String> divide(int a, int b) throws DivByZeroException {
        int r = empleadoService.divide(a,b);
        return buildResponse(r);
    }
    
    private Map<String, String> buildResponse(int n) {
        Map<String, String> response = new HashMap<>();
        response.put("la multiplicacion es", n+"");
        return response;
    }
    
    @ApiOperation(
            value = "AdminController::health",
            notes = "Entrega un informe a cerca de las variables del sistema")
        @RequestMapping(
            value = "/health.json",
            method = GET,
            produces = "application/json; charset=utf-8")
        public Map<String, String> health(@RequestParam  String data) throws Exception {
            return healthService.getInfo(data);
        }
    @ApiOperation(
            value = "AdminController::health",
            notes = "Entrega el log del sistema")
        @RequestMapping(
            value = "/log.json",
            method = GET,
            produces = "application/json; charset=utf-8")
        public List<String> health(@RequestParam  Integer last) {
            return healthService.getLog(last);
        }
}
