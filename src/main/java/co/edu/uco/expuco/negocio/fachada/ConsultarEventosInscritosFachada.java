package co.edu.uco.expuco.negocio.fachada;

import java.util.List;

import co.edu.uco.expuco.dto.EventoDTO;

// Fachada: punto de entrada del negocio para consultar los eventos inscritos de un usuario.
public interface ConsultarEventosInscritosFachada {

	List<EventoDTO> ejecutar(Long usuarioId);
}
