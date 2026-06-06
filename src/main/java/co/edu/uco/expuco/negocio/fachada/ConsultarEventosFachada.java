package co.edu.uco.expuco.negocio.fachada;

import java.util.List;

import co.edu.uco.expuco.dto.EventoDTO;

// Fachada: punto de entrada del negocio para consultar eventos.
public interface ConsultarEventosFachada {

	List<EventoDTO> ejecutar();
}
