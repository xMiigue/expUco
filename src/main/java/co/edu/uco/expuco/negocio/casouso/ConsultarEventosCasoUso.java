package co.edu.uco.expuco.negocio.casouso;

import java.util.List;

import co.edu.uco.expuco.dto.EventoDTO;

// Caso de uso: consultar los eventos con sus cupos disponibles y si estan vigentes.
public interface ConsultarEventosCasoUso {

	List<EventoDTO> ejecutar();
}
