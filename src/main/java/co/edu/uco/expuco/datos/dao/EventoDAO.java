package co.edu.uco.expuco.datos.dao;

import java.util.List;

import co.edu.uco.expuco.entidad.EventoEntidad;

// Contrato del DAO de evento. Solo lo que ExpUco necesita: listar y buscar por id.
public interface EventoDAO {

	List<EventoEntidad> consultarTodos();

	EventoEntidad consultarPorId(Long id);
}
