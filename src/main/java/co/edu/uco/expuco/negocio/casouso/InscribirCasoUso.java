package co.edu.uco.expuco.negocio.casouso;

import co.edu.uco.expuco.dto.ResultadoInscripcionDTO;
import co.edu.uco.expuco.entidad.UsuarioEntidad;

// Caso de uso: inscribir una persona a un evento validando las reglas de negocio.
public interface InscribirCasoUso {

	ResultadoInscripcionDTO ejecutar(UsuarioEntidad persona, Long eventoId);
}
