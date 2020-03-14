package mx.com.cinepolis.anuncios.pruebas;

import static org.junit.Assert.assertTrue;

import org.jasypt.util.text.BasicTextEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JasyptTest {
	private Logger logger = LoggerFactory.getLogger(JasyptTest.class);
			
    @Value("${cinepolis.message}")
    private String welcome;

    @Test
    public void encryptorTest(){
        logger.info("WELCOME: -------------------------------------------->"+welcome+"<---------------------");
        assertTrue("No coincide el password", "Password@1".equals(welcome));
    }

    @Test
    public void encript() {
        String jasyptPassword = "password";
        String cadenaOculta ="UrbiEtOrbi1";

        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword(jasyptPassword);

        String myEncryptedText = textEncryptor.encrypt(cadenaOculta);
        logger.info("Cadena encriptada:"+myEncryptedText);

        String plainText = textEncryptor.decrypt(myEncryptedText);
        logger.info("Cadena des-encriptada:"+plainText);

        assertTrue("Las cadenas no fueros iguales", cadenaOculta.equals(plainText));
    }
}
