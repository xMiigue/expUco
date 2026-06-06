package co.edu.uco.expuco.negocio.casouso;

import co.edu.uco.expuco.dto.UsuarioDTO;

// Caso de uso: buscar un usuario por su documento (para identificarse). Null si no existe.
public interface ConsultarUsuarioPorDocumentoCasoUso {

	UsuarioDTO ejecutar(Long documento);
}
