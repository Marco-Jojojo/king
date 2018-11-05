package mx.gob.segob.dgtic.business.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import mx.gob.segob.dgtic.business.service.NotificacionesService;
import mx.gob.segob.dgtic.business.service.ParametroService;
import mx.gob.segob.dgtic.business.service.base.ServiceBase;
import mx.gob.segob.dgtic.comun.util.mail.MailUtil;

@Service
public class NotificacionesServiceImpl extends ServiceBase implements NotificacionesService {
	
	@Autowired
	private ParametroService parametroService;
	private Map<String, String> parametros = new HashMap<>();
	private Session session;
	
	@PostConstruct
	public void init() {
		//parametros = parametroService.obtieneParametrosEspecificos("config.correo");
		//session = creaSesionParaCorreo();
	}
	
	@Override
	public void enviaCorreo(String destinatario, String asunto, String texto) {
		try {
			MailUtil.enviarNotificacion(session, destinatario, asunto, texto);
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage());
		}
	}
	
	private Session creaSesionParaCorreo() {
		Properties properties = System.getProperties();
		properties.setProperty("mail.smtp.host", parametros.get("host"));
		properties.setProperty("mail.smtp.ehlo", "false");
		properties.setProperty("mail.smtp.user", parametros.get("username"));
		properties.setProperty("mail.smtp.password", parametros.get("contraseña"));
		properties.setProperty("mail.transport.protocol", "smtp");
		properties.setProperty("mail.smtp.port", parametros.get("port"));
		properties.setProperty("mail.smtp.auth", "true");
		
		Authenticator auth = new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(parametros.get("fromEmail"), parametros.get("contraseña"));
			}
		};
		
		Session session = Session.getInstance(properties, auth);
		return session;
	}
}
