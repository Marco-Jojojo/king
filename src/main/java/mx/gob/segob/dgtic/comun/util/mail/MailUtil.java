package mx.gob.segob.dgtic.comun.util.mail;

import java.io.UnsupportedEncodingException;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class MailUtil {
	/** Logger. */
	 private static Logger logger = LoggerFactory.getLogger(MailUtil.class);
	 
	 /** Cuenta de correo desde donde se envían las notificaciones */
	 private String de;
	 
	 /** Dirección del servidor*/
	 private String host;
	 
	 private MailUtil() {
	        throw new IllegalStateException("Utility class");
	    }
	 
	 /**
	  * Envia notificacion por correo electrónico.
	  * @param session
	  * @param destinatario
	  * @param asunto
	  * @param texto
	  * @throws UnsupportedEncodingException 
	  */
	public static void enviarNotificacion(Session session, String destinatario, String asunto, String texto) throws UnsupportedEncodingException 
   {
     try {
        // Creamos un objecto de tipo MimeMessage.
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress("cni@secretariadoejecutivo.gob.mx", "Centro Nacional de Información"));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
        message.setSubject(asunto);
        message.setContent(texto, "text/html");

        // Enviamos el correo
        Transport.send(message);
     } catch (MessagingException ex) {
        logger.error(ex.getMessage());
     }
   }
}
