package co.edu.uco.expuco.datos.mapeador;

import java.sql.ResultSet;
import java.sql.SQLException;

import co.edu.uco.expuco.entidad.EventoEntidad;

// Convierte una fila del ResultSet en una EventoEntidad.
public final class EventoMapper {

	private static final EventoMapper INSTANCIA = new EventoMapper();

	private EventoMapper() {
		super();
	}

	public static EventoMapper obtenerInstancia() {
		return INSTANCIA;
	}

	public EventoEntidad mapear(final ResultSet rs) throws SQLException {
		return new EventoEntidad.Builder()
				.id(rs.getLong("id"))
				.nombre(rs.getString("nombre"))
				.lugar(rs.getString("lugar"))
				.fechaInicio(rs.getTimestamp("fecha_inicio").toLocalDateTime())
				.fechaFin(rs.getTimestamp("fecha_fin").toLocalDateTime())
				.inscripcionInicio(rs.getTimestamp("inscripcion_inicio").toLocalDateTime())
				.inscripcionFin(rs.getTimestamp("inscripcion_fin").toLocalDateTime())
				.capacidad(rs.getInt("capacidad"))
				.build();
	}
}
