package co.edu.uco.expuco.negocio.casouso.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import co.edu.uco.expuco.datos.fabrica.FabricaDAO;
import co.edu.uco.expuco.dto.EventoDTO;
import co.edu.uco.expuco.entidad.EventoEntidad;
import co.edu.uco.expuco.negocio.casouso.ConsultarEventosCasoUso;

// Implementacion del caso de uso: por cada evento calcula cupos disponibles y si esta vigente.
public final class ConsultarEventosCasoUsoImpl implements ConsultarEventosCasoUso {

	private final FabricaDAO fabricaDAO;

	public ConsultarEventosCasoUsoImpl(final FabricaDAO fabricaDAO) {
		super();
		this.fabricaDAO = fabricaDAO;
	}

	@Override
	public List<EventoDTO> ejecutar() {
		final LocalDateTime ahora = LocalDateTime.now();
		final List<EventoDTO> resultado = new ArrayList<>();

		for (final EventoEntidad evento : fabricaDAO.obtenerEventoDAO().consultarTodos()) {
			final int inscritos = fabricaDAO.obtenerInscripcionDAO().contarPorEvento(evento.getId());
			final int cuposDisponibles = evento.getCapacidad() - inscritos;
			final boolean vigente = !ahora.isBefore(evento.getInscripcionInicio())
					&& !ahora.isAfter(evento.getInscripcionFin());

			resultado.add(new EventoDTO.Builder()
					.id(evento.getId())
					.nombre(evento.getNombre())
					.lugar(evento.getLugar())
					.fechaInicio(evento.getFechaInicio())
					.fechaFin(evento.getFechaFin())
					.capacidad(evento.getCapacidad())
					.cuposDisponibles(cuposDisponibles)
					.vigente(vigente)
					.build());
		}
		return resultado;
	}
}
