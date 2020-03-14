package mx.com.cinepolis.anuncios.pruebas;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import mx.com.cinepolis.anuncios.support.MailTemplateHelper;

public class MailValidatorTest {
    @Test
    public void encryptorTest(){
    	MailTemplateHelper mv = MailTemplateHelper.getInstance();
        assertTrue("No coincide el password", mv.validateMailStructure("arellano@aol.com"));
    }
}
