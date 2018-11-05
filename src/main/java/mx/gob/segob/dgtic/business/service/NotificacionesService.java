package mx.gob.segob.dgtic.business.service;

public interface NotificacionesService {
	void enviaCorreo(String destinatario, String asunto, String texto);
}
