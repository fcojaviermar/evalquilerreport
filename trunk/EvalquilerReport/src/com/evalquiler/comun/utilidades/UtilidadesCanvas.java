/**
 * 
 */
package com.evalquiler.comun.utilidades;


/**
 * @author cachorro
 *
 */
public final class UtilidadesCanvas {

	public final static String construirCanva (final int[] numeroRespuestasDadas, final int indicePregunta) {
		
		int valor = 0;
		int totalRespuestas = numeroRespuestasDadas[0] + numeroRespuestasDadas[1] + numeroRespuestasDadas[2] +
							  numeroRespuestasDadas[3] + numeroRespuestasDadas[4];
		
		String canva = "<div id=\"divCanva" + indicePregunta + "\">";
		for (int i=0; i<numeroRespuestasDadas.length; i++) {
			try {
				valor = (200 *numeroRespuestasDadas[i]) / totalRespuestas;
			} catch (ArithmeticException e) {
				valor = 0;
			}
			canva = canva + "<canvas id=\"respuesta" + indicePregunta + "" + i + "\" width=\"200\" height=\"15\"  style=\"border:1px solid #000000;\">" +
							"</canvas>" +
					        "<br>" +
					        "<script>"+
					        "var c" + indicePregunta + "" + i + "   = document.getElementById(\"respuesta" + indicePregunta + "" + i + "\");"+
					        "var ctx" + indicePregunta + "" + i + " = c" + indicePregunta + "" + i + ".getContext(\"2d\");"+
					        "ctx" + indicePregunta + "" + i + ".fillStyle = \"#E0ECF8\";" +
					        "ctx" + indicePregunta + "" + i + ".fillRect(0,0," + valor + ",15);" +
					        "ctx" + indicePregunta + "" + i + ".font=\"10px Arial\";" +
					        "ctx" + indicePregunta + "" + i + ".fillText(\"" + numeroRespuestasDadas[i] + " respuestas" + "\",50,10);" +
					        		
					        "</script>";
		}
		
		canva = canva + "</div>";

		return canva;
	}
	
}
