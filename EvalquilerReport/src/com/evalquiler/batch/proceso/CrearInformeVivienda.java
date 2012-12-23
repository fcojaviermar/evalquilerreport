/**
 * 
 */
package com.evalquiler.batch.proceso;

import java.util.Collection;
import java.util.Iterator;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.evalquiler.actionforms.encuesta.DatosEncuestaActionForm;
import com.evalquiler.actionforms.informe.DatosSolicitudInformeActionForm;
import com.evalquiler.batch.operacion.OpCliente;
import com.evalquiler.batch.operacion.OpInforme;
import com.evalquiler.batch.operacion.OpMunicipio;
import com.evalquiler.batch.operacion.OpProvincia;
import com.evalquiler.batch.operacion.OpSolicitud;
import com.evalquiler.batch.operacion.OpTipoVia;
import com.evalquiler.batch.operacion.OpVivienda;
import com.evalquiler.comun.constantes.Constantes;
import com.evalquiler.comun.utilidades.UtilidadesCanvas;
import com.evalquiler.comun.utilidades.UtilidadesFicheros;
import com.evalquiler.excepciones.ExcepcionEjecutarSentancia;
import com.evalquiler.excepciones.cliente.ClienteNoExisteExcepcion;
import com.evalquiler.excepciones.cliente.ClienteRepetidoExcepcion;
import com.evalquiler.excepciones.informe.ErrorObtenerDatosInformeExcepcion;
import com.evalquiler.excepciones.informe.NoHaySolicitudesPendientesException;
import com.evalquiler.excepciones.informe.SolicitudesConUnaFechaException;
import com.evalquiler.excepciones.municipio.NoExisteMunicipioExcepcion;
import com.evalquiler.excepciones.provincia.NoExisteProvinciaExcepcion;
import com.evalquiler.excepciones.tipovia.NoExisteTipoViaExcepcion;
import com.evalquiler.excepciones.vivienda.NoExisteViviendaExcepcion;

/**
 * @author cachorro
 *
 */
public class CrearInformeVivienda {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Collection<DatosSolicitudInformeActionForm> datosSolicitudes 	 = null;
		Iterator<DatosSolicitudInformeActionForm> 	iterDatosSolicitudes = null;
		DatosSolicitudInformeActionForm 			datosSolicitud 		 = null;
		DatosEncuestaActionForm 					datosEncuesta 		 = null;
		String documentoHtml						= "";
		String inicioDocumentoHtml				   	= "<!DOCTYPE html> <html> <body>" +
				"<head> " +
				"	<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"> " +
				"	<title>Informe sobre vivienda</title> " +
				"   <meta name=\"application-name\" content=\"Evalquiler.com\"> " + 
				"   <meta name=\"description\" content=\"Informe sobre una vivienda proporcionado por Evalquiler\">";
		String finDocumentoHtml				    	= "</body> </html>";
		String mensaje = "";
		
		Properties props = new Properties();
		// Nombre del host de correo, es smtp.gmail.com
		props.setProperty("mail.smtp.host", "smtp.gmail.com");
		// TLS si está disponible
		props.setProperty("mail.smtp.starttls.enable", "true");
		// Puerto de gmail para envio de correos
		props.setProperty("mail.smtp.port","587");
		// Nombre del usuario
		props.setProperty("mail.smtp.user", "evalquiler@gmail.com");
		// Si requiere o no usuario y password para conectarse.
		props.setProperty("mail.smtp.auth", "true");
		
		try {
			try {
				datosSolicitudes = OpSolicitud.consultarSolicitudesPendientes();

				iterDatosSolicitudes = datosSolicitudes.iterator();
    			while (iterDatosSolicitudes.hasNext()) {
    				datosSolicitud = iterDatosSolicitudes.next();
    				try {
						datosEncuesta = OpInforme.consultarDatosInforme(datosSolicitud);

						datosSolicitud.setDatosCliente(OpCliente.consultarPorPk(datosSolicitud.getDatosCliente()));
    					
						datosSolicitud.setDatosVivienda(OpVivienda.consultarVivienda(datosSolicitud.getDatosVivienda()));
    						
    					datosSolicitud.getDatosVivienda().setTipoVia(OpTipoVia.consultarTipoVia(datosSolicitud.getDatosVivienda()));
    							
    					datosSolicitud.getDatosVivienda().setMunicipio(OpMunicipio.consultarMunicipio(datosSolicitud.getDatosVivienda()));
    
    					datosSolicitud.getDatosVivienda().setProvincia(OpProvincia.consultarProvincia(datosSolicitud.getDatosVivienda()));
    
    					mensaje = datosSolicitud.getDatosParaInforme().concat(datosSolicitud.getDatosCliente().getDatosParaInforme().
    																   concat(datosSolicitud.getDatosVivienda().getDatosParaInforme().
    																   concat(datosEncuesta.getDatosParaInforme())));
    					
    					BodyPart texto = new MimeBodyPart();
    					BodyPart adjunto = null;
    					MimeMultipart multiParte = null;
    					
    					documentoHtml = inicioDocumentoHtml + mensaje + finDocumentoHtml;
    					UtilidadesFicheros.escribirHTML(documentoHtml, datosSolicitud.getIdSolicitudInforme());
    					
    					try {
    						String textoMensaje = Constantes.TEXTO_CORREO.concat( (""+datosSolicitud.getIdSolicitudInforme()).
    											  concat(Constantes.TEXTO_FIRMA.concat(Constantes.TEXTO_POR_ERROR))); 
							texto.setText(textoMensaje);
							adjunto = new MimeBodyPart();
							adjunto.setDataHandler(new DataHandler(new FileDataSource("c:/logs/Informe solicitud " + datosSolicitud.getIdSolicitudInforme() + ".html")));
							adjunto.setFileName("Informe solicitud " + datosSolicitud.getIdSolicitudInforme() + ".html");
    					
							multiParte = new MimeMultipart();

							multiParte.addBodyPart(texto);
	    					multiParte.addBodyPart(adjunto);
						} catch (MessagingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

    					
//    					UtilidadesFicheros.escribir(mensaje);						
//    					
//    					enviarMensaje(props, multiParte, datosSolicitud.getDatosCliente().getEmail());
//    
//    					OpSolicitud.actualizarProcesado(datosSolicitud);
//    				} catch (NoHaySolicitudesPendientesException e) {
//    					// Se está actualizando una solicitud que ha sido procesada y ahora no se encuentra.
					} catch (SolicitudesConUnaFechaException e1) {
    					// TODO Auto-generated catch block
    				} catch (NoExisteProvinciaExcepcion e) {
    					// TODO Auto-generated catch block
    				} catch (NoExisteMunicipioExcepcion e) {
    					// TODO Auto-generated catch block
    				} catch (NoExisteTipoViaExcepcion e) {
    					// TODO Auto-generated catch block
    				} catch (NoExisteViviendaExcepcion e) {
    					// TODO Auto-generated catch block
    				} catch (ClienteNoExisteExcepcion e) {
    					// TODO Auto-generated catch block
    				} catch (ClienteRepetidoExcepcion e) {
    					// TODO Auto-generated catch block
    				}
    			}
			} catch (NoHaySolicitudesPendientesException e) {
			}
		} catch (ExcepcionEjecutarSentancia e) {
			// TODO Auto-generated catch block
		} catch (ErrorObtenerDatosInformeExcepcion e) {
			// TODO Auto-generated catch block
		}
	}
	
	private static void enviarMensaje(final Properties props, final MimeMultipart mensaje, final String destinatario) {
		Session session = Session.getDefaultInstance(props);
		//Es la versión definitiva la siguiente línea hay que quitarla, solo es para tener más información de lo que está pasando.
		session.setDebug(true);
		
		
		//Construcción del mensaje que se va a enviar.
		MimeMessage message = new MimeMessage(session);
		
		// Quien envia el correo
		try {
			message.setFrom(new InternetAddress("evalquiler@gmail.com"));
		} catch (AddressException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}

		try {
			message.setSubject("Informe de evaluación de un alquiler");
		} catch (MessagingException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
		
		// A quien va dirigido
		try {
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
		} catch (AddressException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}	
		
		try {			
// 			message.setText(mensaje);
			//Se construye un mensaje en formato html.
			//message.setText(mensaje, "ISO-8859-1", "html");
			message.setContent(mensaje);
		} catch (MessagingException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
		
		
		//Se envía el mensaje.
		Transport t = null;
		try {
			t = session.getTransport("smtp");
		} catch (NoSuchProviderException e) {
			// TODO Bloque catch generado automáticamente
		}
		
		try {
			t.connect("evalquiler@gmail.com","evalquiler2012");
		} catch (MessagingException e) {
			// TODO Bloque catch generado automáticamente
		}
		
		try {
			t.sendMessage(message, message.getAllRecipients());
		} catch (IllegalStateException e) {
			//No se ha obtenido una conexión con el servidor de correo.
		} catch (MessagingException e) {
			// TODO Bloque catch generado automáticamente
		}
		
		//Se cierra la conexion
		try {
			t.close();
		} catch (MessagingException e) {
			// TODO Bloque catch generado automáticamente
		}		
	}


}
