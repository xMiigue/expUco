package co.edu.uco.expuco.datos.dao;

import java.util.List;

import co.edu.uco.expuco.entidad.EventoEntidad;
import co.edu.uco.expuco.entidad.InscripcionEntidad;

// Contrato del DAO de inscripcion. Solo lo que ExpUco necesita para inscribir.
public interface InscripcionDAO {

	// Para la regla de cupos: cuantos inscritos tiene un evento.
	int contarPorEvento(Long eventoId);

	// Para la regla de choque: eventos a los que el usuario ya esta inscrito.
	List<EventoEntidad> consultarEventosPorUsuario(Long usuarioId);

	// Inserta la inscripcion cuando pasan las 3 reglas.
	void crear(InscripcionEntidad entidad);
}
