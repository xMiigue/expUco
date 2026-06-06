package co.edu.uco.expuco.negocio.casouso;

import java.util.List;

import co.edu.uco.expuco.dto.EventoDTO;

// Caso de uso: consultar los eventos a los que un usuario YA esta inscrito.
public interface ConsultarEventosInscritosCasoUso {

	List<EventoDTO> ejecutar(Long usuarioId);
}
