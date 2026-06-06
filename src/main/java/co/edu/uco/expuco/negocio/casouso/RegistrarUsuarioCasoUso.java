package co.edu.uco.expuco.negocio.casouso;

import co.edu.uco.expuco.dto.ResultadoRegistroDTO;

// Caso de uso: registrar un usuario nuevo (nombre + documento).
public interface RegistrarUsuarioCasoUso {

	ResultadoRegistroDTO ejecutar(String nombre, Long documento);
}
