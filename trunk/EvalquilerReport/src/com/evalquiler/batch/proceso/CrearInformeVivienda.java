/**
 * 
 */
package com.evalquiler.batch.proceso;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.CodeSource;
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
import com.evalquiler.comun.constantes.ConstantesCodigosExcepciones;
import com.evalquiler.comun.constantes.ConstantesHtml;
import com.evalquiler.comun.utilidades.UtilidadesFicheros;
import com.evalquiler.excepciones.ExcepcionEjecutarSentancia;
import com.evalquiler.excepciones.cliente.ClienteNoExisteExcepcion;
import com.evalquiler.excepciones.cliente.ClienteRepetidoExcepcion;
import com.evalquiler.excepciones.comun.ExcepcionComun;
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
		System.out.println("Inicio CrearInformeVivienda");
		System.out.println("Leida propiedad. " + System.getProperty("user.dir"));
		Collection<DatosSolicitudInformeActionForm> datosSolicitudes 	 = null;
		Iterator<DatosSolicitudInformeActionForm> 	iterDatosSolicitudes = null;
		DatosSolicitudInformeActionForm 			datosSolicitud 		 = null;
		DatosEncuestaActionForm 					datosEncuesta 		 = null;
		Properties 									props 				 = null;
		MimeMultipart 								multiParte 			 = null;
		String documentoHtml						= "";
		String mensaje 								= "";
		
		try {
			props = getPropiedades();
		} catch (ExcepcionComun e2) {
			System.out.println("Error al leer la propiedades.");
			System.exit(1);
		}
		
		try {
			try {
				datosSolicitudes = OpSolicitud.consultarSolicitudesPendientes();

				iterDatosSolicitudes = datosSolicitudes.iterator();
    			while (iterDatosSolicitudes.hasNext()) {
    				datosSolicitud = iterDatosSolicitudes.next();
    				try {
						datosEncuesta  = OpInforme.consultarDatosInforme(datosSolicitud);
						datosSolicitud = getDatosSolicitud(datosSolicitud);
							
    					mensaje = datosSolicitud.getDatosParaInforme().concat(datosSolicitud.getDatosCliente().getDatosParaInforme().
    																   concat(datosSolicitud.getDatosVivienda().getDatosParaInforme().
    																   concat(datosEncuesta.getDatosParaInforme())));
    					
    					documentoHtml = ConstantesHtml.INICIO_DOCUMENTO_HTNL + mensaje + ConstantesHtml.FIN_DOCUMENTO_HTML;
    					UtilidadesFicheros.escribirHTML(documentoHtml, datosSolicitud.getIdSolicitudInforme());
    					
    					try {
    						String textoMensaje = Constantes.TEXTO_CORREO.concat((String.valueOf(datosSolicitud.getIdSolicitudInforme()).
    											  concat(Constantes.TEXTO_CORREO2.concat(datosSolicitud.getDatosVivienda().getNombreVia().
    											  concat(Constantes.TEXTO_FIRMA.concat(Constantes.TEXTO_POR_ERROR))))));
    						multiParte = getObjetoCorreo(textoMensaje, datosSolicitud, props);
						} catch (MessagingException e) {
							// TODO Auto-generated catch block							
						}
    					
    					UtilidadesFicheros.escribir(mensaje);						
    					
    					try {
							enviarMensaje(props, multiParte, datosSolicitud.getDatosCliente().getEmail());
						} catch (ExcepcionComun e) {
							System.out.println("Error al enviar el mensaje de correo.");
							System.exit(2);
						}
    
//    					OpSolicitud.actualizarProcesado(datosSolicitud);
//    				} catch (NoHaySolicitudesPendientesException e) {
//    					// Se esta actualizando una solicitud que ha sido procesada y ahora no se encuentra.
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
				System.out.println("Finalización correcta porque no hay solicitudes pendientes.");
				System.exit(0);
			}
		} catch (ExcepcionEjecutarSentancia e) {
			System.out.println("Se ha producido un error.");
			System.exit(3);
		} catch (ErrorObtenerDatosInformeExcepcion e) {
			System.out.println("Error al obtener datos del informe.");
			System.exit(4);
		}
		System.out.println("Finalización correcta.");
		System.exit(0);
	}
	
	
	private static void enviarMensaje(final Properties props, final MimeMultipart mensaje, final String destinatario) throws ExcepcionComun {
		Session session = Session.getDefaultInstance(props);
		session.setDebug(Boolean.parseBoolean(props.getProperty(Constantes.DEBUG)));
		
		//Construccion del mensaje que se va a enviar.
		MimeMessage message = new MimeMessage(session);
		
		// Quien envia el correo
		try {
			message.setFrom(new InternetAddress(props.getProperty(Constantes.USUARIO)));
		} catch (AddressException e) {
			throw new ExcepcionComun(ConstantesCodigosExcepciones.ERROR.concat(
				 				 	 ConstantesCodigosExcepciones.FUNCIONALIDAD_INFORMES.concat(
				 				 	 ConstantesCodigosExcepciones.CODIGO_DATOS_ENVIO_CORREO)), 
				 				 	 "", e.getMessage());
		} catch (MessagingException e) {
			throw new ExcepcionComun(ConstantesCodigosExcepciones.ERROR.concat(
				 	 ConstantesCodigosExcepciones.FUNCIONALIDAD_INFORMES.concat(
				 	 ConstantesCodigosExcepciones.CODIGO_DATOS_ENVIO_CORREO)), 
				 	 "", e.getMessage());			
		}

		try {
			message.setSubject("Informe de evaluación de alquiler");
		} catch (MessagingException e) {
			throw new ExcepcionComun(ConstantesCodigosExcepciones.ERROR.concat(
				 	 ConstantesCodigosExcepciones.FUNCIONALIDAD_INFORMES.concat(
				 	 ConstantesCodigosExcepciones.CODIGO_DATOS_ENVIO_CORREO)), 
				 	 "", e.getMessage());			
		}
		
		// A quien va dirigido
		try {
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
		} catch (AddressException e) {
			throw new ExcepcionComun(ConstantesCodigosExcepciones.ERROR.concat(
				 	 ConstantesCodigosExcepciones.FUNCIONALIDAD_INFORMES.concat(
				 	 ConstantesCodigosExcepciones.CODIGO_DATOS_ENVIO_CORREO)), 
				 	 "", e.getMessage());			
		} catch (MessagingException e) {
			throw new ExcepcionComun(ConstantesCodigosExcepciones.ERROR.concat(
				 	 ConstantesCodigosExcepciones.FUNCIONALIDAD_INFORMES.concat(
				 	 ConstantesCodigosExcepciones.CODIGO_DATOS_ENVIO_CORREO)), 
				 	 "", e.getMessage());			
		}	
		
		try {			
// 			message.setText(mensaje);
			//Se construye un mensaje en formato html.
			//message.setText(mensaje, "ISO-8859-1", "html");
			message.setContent(mensaje);
		} catch (MessagingException e) {
			throw new ExcepcionComun(ConstantesCodigosExcepciones.ERROR.concat(
				 	 ConstantesCodigosExcepciones.FUNCIONALIDAD_INFORMES.concat(
				 	 ConstantesCodigosExcepciones.CODIGO_DATOS_ENVIO_CORREO)), 
				 	 "", e.getMessage());			
		}
		
		
		//Se envia el mensaje.
		Transport t = null;
		try {
			t = session.getTransport(props.getProperty(Constantes.PROTOCOLO));
		} catch (NoSuchProviderException e) {
			throw new ExcepcionComun(ConstantesCodigosExcepciones.ERROR.concat(
				 	 ConstantesCodigosExcepciones.FUNCIONALIDAD_INFORMES.concat(
				 	 ConstantesCodigosExcepciones.CODIGO_DATOS_ENVIO_CORREO)), 
				 	 "", e.getMessage());
		}
		
		try {
			//t.connect("alu.12057@usj.es","contrase�a");
			t.connect(props.getProperty(Constantes.USUARIO), 
					  props.getProperty(Constantes.CONTRASENA));
		} catch (MessagingException e) {
			throw new ExcepcionComun(ConstantesCodigosExcepciones.ERROR.concat(
				 	 ConstantesCodigosExcepciones.FUNCIONALIDAD_INFORMES.concat(
				 	 ConstantesCodigosExcepciones.CODIGO_DATOS_ENVIO_CORREO)), 
				 	 "", e.getMessage());			
		}
		
		try {
			t.sendMessage(message, message.getAllRecipients());
		} catch (IllegalStateException e) {
			throw new ExcepcionComun(ConstantesCodigosExcepciones.ERROR.concat(
				 	 ConstantesCodigosExcepciones.FUNCIONALIDAD_INFORMES.concat(
				 	 ConstantesCodigosExcepciones.CODIGO_DATOS_ENVIO_CORREO)), 
				 	 "", e.getMessage());
		} catch (MessagingException e) {
			throw new ExcepcionComun(ConstantesCodigosExcepciones.ERROR.concat(
				 	 ConstantesCodigosExcepciones.FUNCIONALIDAD_INFORMES.concat(
				 	 ConstantesCodigosExcepciones.CODIGO_DATOS_ENVIO_CORREO)), 
				 	 "", e.getMessage());			
		}
		
		try {
			t.close();
		} catch (MessagingException e) {
			throw new ExcepcionComun(ConstantesCodigosExcepciones.ERROR.concat(
				 	 ConstantesCodigosExcepciones.FUNCIONALIDAD_INFORMES.concat(
				 	 ConstantesCodigosExcepciones.CODIGO_DATOS_ENVIO_CORREO)), 
				 	 "", e.getMessage());			
		}		
	}

	
	private final static Properties getPropiedades() throws ExcepcionComun {
//		// Nombre del host de correo, es smtp.gmail.com
//		props.setProperty("mail.smtp.host", props.getProperty("mail.smtp.host"));
//		// Puerto de gmail para envio de correos
//		props.setProperty("mail.smtp.port", props.getProperty("mail.smtp.port"));
//		// Nombre del usuario
//		//props.setProperty("mail.smtp.user", "alu.12057@usj.es");
//		props.setProperty("mail.smtp.user", props.getProperty("mail.smtp.user"));
//		// Si requiere o no usuario y password para conectarse.
//		props.setProperty("mail.smtp.auth", props.getProperty("mail.smtp.auth"));

		// TLS si está disponible
		//props.setProperty("mail.smtp.starttls.enable", props.getProperty("mail.smtp.starttls.enable"));
		File 	   jarFile 		= null;
		Properties defaultProps = null;

		jarFile = new File(System.getProperty("user.dir"));
   			
		File jarDir = jarFile.getParentFile();
		if (jarDir != null && jarDir.isDirectory()) {
			defaultProps = new Properties();
			try {
				defaultProps.load(new BufferedReader(new FileReader(System.getProperty("user.dir")+"\\"+Constantes.NOMBRE_PROPERTIES)));
				
			} catch (FileNotFoundException e) {
				throw new ExcepcionComun(ConstantesCodigosExcepciones.ERROR.concat(
					 	 ConstantesCodigosExcepciones.FUNCIONALIDAD_INFORMES.concat(
					 	 ConstantesCodigosExcepciones.CODIGO_DATOS_ENVIO_CORREO)), 
					 	 "", e.getMessage());			
			} catch (IOException e) {
				throw new ExcepcionComun(ConstantesCodigosExcepciones.ERROR.concat(
					 	 ConstantesCodigosExcepciones.FUNCIONALIDAD_INFORMES.concat(
					 	 ConstantesCodigosExcepciones.CODIGO_DATOS_ENVIO_CORREO)), 
					 	 "", e.getMessage());			

			}
		}
		return defaultProps;
	}

	
	private final static DatosSolicitudInformeActionForm getDatosSolicitud(DatosSolicitudInformeActionForm datosSolicitud) 
		throws ClienteNoExisteExcepcion, ClienteRepetidoExcepcion, ExcepcionEjecutarSentancia, NoExisteViviendaExcepcion, NoExisteTipoViaExcepcion, 
			   NoExisteMunicipioExcepcion, NoExisteProvinciaExcepcion {
		
		datosSolicitud.setDatosCliente(OpCliente.consultarPorPk(datosSolicitud.getDatosCliente()));
		datosSolicitud.setDatosVivienda(OpVivienda.consultarVivienda(datosSolicitud.getDatosVivienda()));
		datosSolicitud.getDatosVivienda().setTipoVia(OpTipoVia.consultarTipoVia(datosSolicitud.getDatosVivienda()));
		datosSolicitud.getDatosVivienda().setMunicipio(OpMunicipio.consultarMunicipio(datosSolicitud.getDatosVivienda()));
		datosSolicitud.getDatosVivienda().setProvincia(OpProvincia.consultarProvincia(datosSolicitud.getDatosVivienda()));

		return datosSolicitud;
	}
	
	
	private final static MimeMultipart getObjetoCorreo(final String textoMensaje, final DatosSolicitudInformeActionForm datosSolicitud,
													   final Properties props) throws MessagingException {
		
		BodyPart 	  								adjunto 	 		 = null;
		MimeMultipart 								multiParte 			 = null;

		BodyPart texto = new MimeBodyPart();
		texto.setText(textoMensaje);
		adjunto = new MimeBodyPart();
		adjunto.setDataHandler(new DataHandler(new FileDataSource(props.getProperty(Constantes.RUTA_DESCARGA_ADJUNTOS) + 
																  Constantes.NOMBRE_ADJUNTO  + 
																  datosSolicitud.getIdSolicitudInforme() + 
																  Constantes.EXTENSION_ADJUNTO)));
		adjunto.setFileName(Constantes.NOMBRE_ADJUNTO + 
							datosSolicitud.getIdSolicitudInforme() + 
							Constantes.EXTENSION_ADJUNTO);
	
		multiParte = new MimeMultipart();
		multiParte.addBodyPart(texto);
		multiParte.addBodyPart(adjunto);
	
		return multiParte;
	}
}
