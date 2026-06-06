package co.edu.uco.expuco.negocio.casouso.impl;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.expuco.datos.fabrica.FabricaDAO;
import co.edu.uco.expuco.dto.EventoDTO;
import co.edu.uco.expuco.entidad.EventoEntidad;
import co.edu.uco.expuco.negocio.casouso.ConsultarEventosInscritosCasoUso;

// Implementacion: reutiliza consultarEventosPorUsuario del DAO y arma los EventoDTO para mostrar.
public final class ConsultarEventosInscritosCasoUsoImpl implements ConsultarEventosInscritosCasoUso {

	private final FabricaDAO fabricaDAO;

	public ConsultarEventosInscritosCasoUsoImpl(final FabricaDAO fabricaDAO) {
		super();
		this.fabricaDAO = fabricaDAO;
	}

	@Override
	public List<EventoDTO> ejecutar(final Long usuarioId) {
		final List<EventoDTO> resultado = new ArrayList<>();
		for (final EventoEntidad evento : fabricaDAO.obtenerInscripcionDAO().consultarEventosPorUsuario(usuarioId)) {
			resultado.add(new EventoDTO.Builder()
					.id(evento.getId())
					.nombre(evento.getNombre())
					.lugar(evento.getLugar())
					.fechaInicio(evento.getFechaInicio())
					.fechaFin(evento.getFechaFin())
					.capacidad(evento.getCapacidad())
					.build());
		}
		return resultado;
	}
}
