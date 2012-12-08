/**
 * 
 */
package com.evalquiler.batch.proceso;

import java.util.Collection;
import java.util.Iterator;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.evalquiler.actionforms.cliente.DatosClienteActionForm;
import com.evalquiler.actionforms.encuesta.DatosEncuestaActionForm;
import com.evalquiler.actionforms.informe.DatosSolicitudInformeActionForm;
import com.evalquiler.batch.operacion.OpCliente;
import com.evalquiler.batch.operacion.OpInforme;
import com.evalquiler.batch.operacion.OpMunicipio;
import com.evalquiler.batch.operacion.OpProvincia;
import com.evalquiler.batch.operacion.OpSolicitud;
import com.evalquiler.batch.operacion.OpTipoVia;
import com.evalquiler.batch.operacion.OpVivienda;
import com.evalquiler.comun.utilidades.UtilidadesFicheros;
import com.evalquiler.excepciones.ExcepcionEjecutarSentancia;
import com.evalquiler.excepciones.cliente.ClienteNoExisteExcepcion;
import com.evalquiler.excepciones.cliente.ClienteRepetidoExcepcion;
import com.evalquiler.excepciones.informe.ErrorObtenerDatosInformeExcepcion;
import com.evalquiler.excepciones.informe.NoHaySolicitudesPendientesException;
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
		DatosClienteActionForm 						datosCliente 		 = null;
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
				} catch (NoHaySolicitudesPendientesException e) {
				}
				iterDatosSolicitudes = datosSolicitudes.iterator();
				while (iterDatosSolicitudes.hasNext()) {
					datosSolicitud = iterDatosSolicitudes.next();
					datosEncuesta = OpInforme.consultarDatosInforme(datosSolicitud);
					try {
						datosSolicitud.setDatosCliente(OpCliente.consultarPorPk(datosSolicitud.getDatosCliente()));
						try {
							datosSolicitud.setDatosVivienda(OpVivienda.consultarVivienda(datosSolicitud.getDatosVivienda()));
							
							try {
								datosSolicitud.getDatosVivienda().setTipoVia(OpTipoVia.consultarTipoVia(datosSolicitud.getDatosVivienda()));
								
								try {
									datosSolicitud.getDatosVivienda().setMunicipio(OpMunicipio.consultarMunicipio(datosSolicitud.getDatosVivienda()));

									try {
										datosSolicitud.getDatosVivienda().setProvincia(OpProvincia.consultarProvincia(datosSolicitud.getDatosVivienda()));

										mensaje = datosSolicitud.getDatosParaInforme().concat(datosSolicitud.getDatosCliente().getDatosParaInforme().
										   concat(datosSolicitud.getDatosVivienda().getDatosParaInforme().
										   concat(datosEncuesta.getDatosParaInforme()))); 

										UtilidadesFicheros.escribir(mensaje);						

		//enviarMensaje(props, mensaje, datosCliente.getEmail());
										
									} catch (NoExisteProvinciaExcepcion e) {
										// TODO Auto-generated catch block
									}
								} catch (NoExisteMunicipioExcepcion e) {
									// TODO Auto-generated catch block
								}
							} catch (NoExisteTipoViaExcepcion e) {
								// TODO Auto-generated catch block
							}
						} catch (NoExisteViviendaExcepcion e) {
							// TODO Auto-generated catch block
						}
					} catch (ClienteNoExisteExcepcion e) {
						// TODO Auto-generated catch block
					} catch (ClienteRepetidoExcepcion e) {
						// TODO Auto-generated catch block
					}
				}
				
			} catch (ExcepcionEjecutarSentancia e) {
				// TODO Auto-generated catch block
			} catch (ErrorObtenerDatosInformeExcepcion e) {
				// TODO Auto-generated catch block
			}
	}
	
	private static void enviarMensaje(final Properties props, final String mensaje, final String destinatario) {
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
			message.setText(mensaje, "ISO-8859-1", "html");
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
