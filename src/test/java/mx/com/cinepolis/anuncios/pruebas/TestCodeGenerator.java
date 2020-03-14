package mx.com.cinepolis.anuncios.pruebas;

import mx.com.ultrasist.ci.generator.GenCode;

public class TestCodeGenerator {
    public static void main(String args[]) {
    	/**/
        GenCode codeGenerator = new GenCode("Cliente.json");
        
        System.out.println("**************************************************************");
        System.out.println(codeGenerator.generate2("velocity/Template.java.vm", "model"));
        System.out.println("**************************************************************");
        System.out.println(codeGenerator.generate2("velocity/TemplateController.java.vm", "controller"));
        System.out.println("**************************************************************");
        System.out.println(codeGenerator.generate2("velocity/TemplateMapper.java.vm", "mapper"));
        System.out.println("**************************************************************");
        System.out.println(codeGenerator.generate2("velocity/TemplateService.java.vm", "service"));
        System.out.println("**************************************************************");
        System.out.println(codeGenerator.generate2("velocity/TemplateServiceImpl.java.vm", "service"));
        System.out.println("**************************************************************");
        /**/
    }
}

/*
	String:1
	int:4
	short:5
	double:6
	float:7
	double:8

java.math.BigDecimal:2
java.math.BigDecimal:3
*/