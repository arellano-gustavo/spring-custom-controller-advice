/*
 * Licencia:    Este código se encuentra bajo la protección
 *              que otorga el contrato establecido entre
 *              Ultrasist SA de CV y su cliente, IMPI, por lo
 *              que queda estrictamente prohibido copiar, donar
 *              vender y/o distribuir el presente código por
 *              cualquier medio electrónico o impreso sin el
 *              permiso explícito y por escrito del cliente.
 *
 * Proyecto:    Chatbot IMPI
 * Paquete:     mx.gob.impi.chatbot.persistence.api.service
 * Modulo:      Mail
 * Tipo:        clase
 * Autor:       Gustavo A. Arellano (GAA)
 * Fecha:       Viernes 20 de Septiembre de 2019 (13_41)
 * Version:     1.0-SNAPSHOT
 * .
 * Implementacion del Servicio del envio de mail
 *
 * Historia:    .
 *              20190920_1341 Creación del tipo
 *
 *
 */
package mx.com.cinepolis.anuncios.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Descripción:</p>
 * Implementacion del servicio de envio de mail
 *
 * @author Gustavo A. Arellano (GAA)
 * @version 1.0-SNAPSHOT
 */
@Service
public class ChatbotMailSenderServiceImpl implements ChatbotMailSenderService {
    private Logger logger = LoggerFactory.getLogger(ChatbotMailSenderServiceImpl.class);

    @Autowired
    private JavaMailSender javaMailSender;


    public static final int NUM_QUICK_SERVICE_THREADS = 20;
    private ScheduledExecutorService quickService = Executors.newScheduledThreadPool(NUM_QUICK_SERVICE_THREADS); // Creates a thread pool that reuses fixed number of threads(as specified by noOfThreads in this case).

  @Override
  public void sendMail2(String to, String subject, String body) {
    SimpleMailMessage mail = new SimpleMailMessage();
    mail.setTo(to);
    mail.setSubject(subject);
    mail.setText(body);
    logger.info("Sending...");
    javaMailSender.send(mail);
    logger.info("Done!");
  }

    @Override
    @Async
    public void sendHtmlMail(String to, String subject, String body) {
      try {
            MimeMessage mail = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body, true);
            javaMailSender.send(mail);
        } catch (MessagingException me) {
            logger.error("error in mail service sendHtmlMail method"+me.getMessage());
        }
    }

    @Override
    public void sendASynchronousHtmlMail(String to,String subject,String body) {
        logger.debug("inside sendASynchronousMail method");
        MimeMessage mail = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body, true);
        } catch(MessagingException me) {
            logger.error("error in mail service sendHtmlMail method"+me.getMessage());
        }
        // FROM: https://www.oodlestechnologies.com/blogs/Asynchronous-Mail-In-Spring-Boot/
        quickService.submit(() -> {
            try{
                javaMailSender.send(mail);
            }catch(Exception e){
                logger.error("Exception occur while send a mail : ",e);
            }
        });
    }

}
