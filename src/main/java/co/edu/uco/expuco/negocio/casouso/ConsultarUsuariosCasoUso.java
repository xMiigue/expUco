package co.edu.uco.expuco.negocio.casouso;

import java.util.List;

import co.edu.uco.expuco.dto.UsuarioDTO;

// Caso de uso: consultar todos los usuarios.
public interface ConsultarUsuariosCasoUso {

	List<UsuarioDTO> ejecutar();
}
