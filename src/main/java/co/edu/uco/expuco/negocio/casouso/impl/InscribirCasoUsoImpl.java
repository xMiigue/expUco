package co.edu.uco.expuco.negocio.casouso.impl;

import java.time.LocalDateTime;
import java.util.List;

import co.edu.uco.expuco.datos.fabrica.FabricaDAO;
import co.edu.uco.expuco.dto.ResultadoInscripcionDTO;
import co.edu.uco.expuco.entidad.EventoEntidad;
import co.edu.uco.expuco.entidad.InscripcionEntidad;
import co.edu.uco.expuco.negocio.casouso.InscribirCasoUso;

// Implementacion del caso de uso de inscripcion: valida las 3 reglas EN ORDEN.
// Si alguna falla, devuelve el motivo y NO inserta. Si todas pasan, inserta la inscripcion.
public final class InscribirCasoUsoImpl implements InscribirCasoUso {

	private final FabricaDAO fabricaDAO;

	public InscribirCasoUsoImpl(final FabricaDAO fabricaDAO) {
		super();
		this.fabricaDAO = fabricaDAO;
	}

	@Override
	public ResultadoInscripcionDTO ejecutar(final Long usuarioId, final Long eventoId) {

		// Buscamos el evento al que se quiere inscribir.
		final EventoEntidad evento = fabricaDAO.obtenerEventoDAO().consultarPorId(eventoId);
		if (evento == null) {
			return fallo("El evento no existe.");
		}

		final LocalDateTime ahora = LocalDateTime.now();

		// REGLA 1 - VIGENTE: ahora debe estar dentro del periodo de inscripcion.
		if (ahora.isBefore(evento.getInscripcionInicio()) || ahora.isAfter(evento.getInscripcionFin())) {
			return fallo("Las inscripciones para este evento no estan vigentes.");
		}

		// REGLA 2 - CUPOS: debe haber cupos disponibles (inscritos < capacidad).
		final int inscritos = fabricaDAO.obtenerInscripcionDAO().contarPorEvento(eventoId);
		if (inscritos >= evento.getCapacidad()) {
			return fallo("No hay cupos disponibles para este evento.");
		}

		// REGLA 3 - CHOQUE DE HORARIO: no puede cruzarse con otro evento ya inscrito.
		// Se cruzan si:  nuevo.inicio < otro.fin  Y  otro.inicio < nuevo.fin.
		final List<EventoEntidad> eventosDelUsuario =
				fabricaDAO.obtenerInscripcionDAO().consultarEventosPorUsuario(usuarioId);
		for (final EventoEntidad otro : eventosDelUsuario) {
			final boolean seCruzan = evento.getFechaInicio().isBefore(otro.getFechaFin())
					&& otro.getFechaInicio().isBefore(evento.getFechaFin());
			if (seCruzan) {
				return fallo("Choque de horario con tu evento ya inscrito: " + otro.getNombre() + ".");
			}
		}

		// Las 3 reglas pasaron -> insertamos la inscripcion.
		final InscripcionEntidad inscripcion = new InscripcionEntidad.Builder()
				.usuarioId(usuarioId)
				.eventoId(eventoId)
				.build();
		fabricaDAO.obtenerInscripcionDAO().crear(inscripcion);

		return new ResultadoInscripcionDTO.Builder()
				.exitoso(true)
				.motivo("Inscripcion exitosa a: " + evento.getNombre() + ".")
				.build();
	}

	// Atajo para construir un resultado fallido con su motivo.
	private ResultadoInscripcionDTO fallo(final String motivo) {
		return new ResultadoInscripcionDTO.Builder()
				.exitoso(false)
				.motivo(motivo)
				.build();
	}
}
