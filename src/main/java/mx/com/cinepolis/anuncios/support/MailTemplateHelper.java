package mx.com.cinepolis.anuncios.support;

import java.io.File;
import java.io.InputStream;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MailTemplateHelper {
	private final Logger logger = LoggerFactory.getLogger(MailTemplateHelper.class);

    private static final String REGEX = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    private final Pattern pattern = Pattern.compile(REGEX);
    private static MailTemplateHelper instance = null;


	private final String basePath = "/Users/garellano/Desktop";
    public static MailTemplateHelper getInstance() {
    	if(instance==null) {
    		instance = new MailTemplateHelper();
    	}
    	return instance;
    }


    private MailTemplateHelper() {}
    
    /**
     * Valida la estructura del correo electronico
     * @param email Cadena con el correo electronico a validar
     * @return Boleano con el resultado de la validacion
     */
    public boolean validateMailStructure(String email) {
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    
	public String getMailTemplate(String secTok, String name, String num) {
        String template = getTextFromFile("emailTemplate.txt", false);
        template = template.replace("$USER_NUM", num);
        template = template.replace("$USER_NAME", name);
        template = template.replace("$URL", secTok);
        return template;
	}
    /**
     * Obtiene el cuerpo del mensaje de restablecer el password.
     *
     * @param filename Cadena con la ruta que contiene el cuerpo
     *                 del mensaje que se envia al usuario.
     * @param relative If true, the file will be taken from inside the jar. If false, will use an absolute path.
     *
     * @return Cadena con el cuerpo del mesaje para restablecer
     *         la contraseña del usuario que lo solicita
     */
    private String getTextFromFile(String filename, boolean relative) {
        if(relative) {
            return getTextFromFileWithRelativePath(filename);
        } else {
            return getTextFromFileWithFullPath(basePath+ filename);
        }
    }
    
    private String getTextFromFileWithRelativePath(String filenameRelativepath) {
        InputStream stream =
        		MailTemplateHelper
                .class
                .getClassLoader()
                .getResourceAsStream(filenameRelativepath);
        if(stream==null) {
            logger.error("Error loading "+filenameRelativepath);
            return "<a>bad request</a>";
        }
        Scanner scanner = new Scanner(stream, "UTF-8");
        String text = scanner.useDelimiter("\\A").next();
        scanner.close();
        return text;
    }
    
    private String getTextFromFileWithFullPath(String filenameFullpath) {
        Scanner scanner = null;
        try {
            logger.info("email template given full path: ["+filenameFullpath+"]");
            File file = new File(filenameFullpath);
            scanner = new Scanner(file, "UTF-8" );
            String text = scanner.useDelimiter("\\A").next();
            scanner.close();
            if(text.trim().length()<9) {
                logger.error("Couldn't find the given file: " + filenameFullpath);
                return "'$URL' ("+filenameFullpath+")";
            }
            return text;
        } catch (Exception e) {
            logger.error("Couldn't find the given file: " + filenameFullpath);
            return "'$URL' ("+filenameFullpath+")";
        } finally {
            if(scanner!=null) scanner.close();
        }
    }
}
