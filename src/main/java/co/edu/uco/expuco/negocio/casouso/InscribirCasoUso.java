package co.edu.uco.expuco.negocio.casouso;

import co.edu.uco.expuco.dto.ResultadoInscripcionDTO;

// Caso de uso: inscribir un usuario a un evento validando las 3 reglas de negocio.
public interface InscribirCasoUso {

	ResultadoInscripcionDTO ejecutar(Long usuarioId, Long eventoId);
}
