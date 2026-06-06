package co.edu.uco.expuco.negocio.fachada;

import co.edu.uco.expuco.dto.UsuarioDTO;

// Fachada: punto de entrada del negocio para identificar a un usuario por su documento.
public interface ConsultarUsuarioPorDocumentoFachada {

	UsuarioDTO ejecutar(Long documento);
}
