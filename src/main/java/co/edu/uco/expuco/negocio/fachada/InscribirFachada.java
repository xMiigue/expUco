package co.edu.uco.expuco.negocio.fachada;

import co.edu.uco.expuco.dto.InscripcionDTO;
import co.edu.uco.expuco.dto.ResultadoInscripcionDTO;

// Fachada: punto de entrada del negocio para inscribir (orquesta la transaccion).
public interface InscribirFachada {

	ResultadoInscripcionDTO ejecutar(InscripcionDTO datos);
}
