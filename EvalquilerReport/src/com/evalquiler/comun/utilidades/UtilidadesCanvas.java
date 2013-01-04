/**
 * 
 */
package com.evalquiler.comun.utilidades;


/**
 * @author cachorro
 *
 */
public final class UtilidadesCanvas {

	public final static String construirCanva (int[] numeroRespuestasDadas, final int indicePregunta) {
		
		int valor = 0;
		int totalRespuestas = numeroRespuestasDadas[0] + numeroRespuestasDadas[1] + numeroRespuestasDadas[2] +
							  numeroRespuestasDadas[3] + numeroRespuestasDadas[4];
		
		String canva = "<div id=\"divCanva" + indicePregunta + "\"> <table border=\"2\" width=\"300px\" height=\"60\"><tr><td><br>";
		for (int i=0; i<numeroRespuestasDadas.length; i++) {
			try {
				valor = (200 *numeroRespuestasDadas[i]) / totalRespuestas;
				
				if (0 != numeroRespuestasDadas[i]) {
    				canva = canva + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;0 " +
    				"<canvas id=\"respuesta" + indicePregunta + "" + i + "\" width=\"200\" height=\"15\"  style=\"border:1px solid #000000;\">" +
    				"</canvas> 100" +
    		        "<br>" +
    		        "<script>"+
        		        "var c" + indicePregunta + "" + i + "   = document.getElementById(\"respuesta" + indicePregunta + "" + i + "\");"+
        		        "var ctx" + indicePregunta + "" + i + " = c" + indicePregunta + "" + i + ".getContext(\"2d\");"+
        		        "ctx" + indicePregunta + "" + i + ".fillStyle = \"#E0ECF8\";" +
        		        "ctx" + indicePregunta + "" + i + ".fillRect(0,0," + valor + ",15);" +
        		        "ctx" + indicePregunta + "" + i + ".font=\"bold 10px Arial\";" +
        		        "ctx" + indicePregunta + "" + i + ".fillText(\"" + numeroRespuestasDadas[i] + " respuestas" + "\",50,10);" +
    		        "</script>";
				}
			} catch (ArithmeticException e) {
				valor = 0;
			}
		}
		
		canva = canva + "<br></td></tr></table></div><br><br>";

		return canva;
	}

	
	public final static String construirCanva (final int indicePregunta, final int indiceRespuesta,  
											   final int totalRespuestas, final int numeroRespuestas) {
		
		int valor = 0;
		
		String canva = "<div id=\"divCanva" + indicePregunta + "" + indiceRespuesta + "\">";
		try {
			valor = (200 * numeroRespuestas) / totalRespuestas;
		} catch (ArithmeticException e) {
			valor = 0;
		}
		canva = canva + "0 <canvas id=\"respuesta" + indicePregunta + "" + indiceRespuesta + "\" width=\"200\" height=\"15\"  style=\"border:1px solid #000000;\">" +
						"</canvas> 100" +
				        "<script>"+
					        "var c" + indicePregunta + "" + indiceRespuesta + "   = document.getElementById(\"respuesta" + indicePregunta + "" + indiceRespuesta + "\");"+
					        "var ctx" + indicePregunta + "" + indiceRespuesta + " = c" + indicePregunta + "" + indiceRespuesta + ".getContext(\"2d\");"+
					        "ctx" + indicePregunta + "" + indiceRespuesta + ".fillStyle = \"#E0ECF8\";" +
					        "ctx" + indicePregunta + "" + indiceRespuesta + ".fillRect(0,0," + valor + ",15);" +
					        "ctx" + indicePregunta + "" + indiceRespuesta + ".font=\"bold 10px Arial\";" +
					        "ctx" + indicePregunta + "" + indiceRespuesta + ".fillText(\"" + numeroRespuestas + " respuestas" + "\",50,10);" +
				        "</script>";
		canva = canva + "</div>";

		return canva;
	}

}
