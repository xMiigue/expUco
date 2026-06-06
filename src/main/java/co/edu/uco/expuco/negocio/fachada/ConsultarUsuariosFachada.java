package co.edu.uco.expuco.negocio.fachada;

import java.util.List;

import co.edu.uco.expuco.dto.UsuarioDTO;

// Fachada: punto de entrada del negocio para consultar usuarios.
public interface ConsultarUsuariosFachada {

	List<UsuarioDTO> ejecutar();
}
