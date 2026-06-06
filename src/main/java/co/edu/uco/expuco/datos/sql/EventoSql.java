package co.edu.uco.expuco.datos.sql;

// Sentencias SQL de la tabla evento, centralizadas en un solo lugar.
public final class EventoSql {

	private static final String COLUMNAS =
			"id, nombre, lugar, fecha_inicio, fecha_fin, inscripcion_inicio, inscripcion_fin, capacidad";

	public static final String CONSULTAR_TODOS =
			"SELECT " + COLUMNAS + " FROM evento ORDER BY fecha_inicio";

	public static final String CONSULTAR_POR_ID =
			"SELECT " + COLUMNAS + " FROM evento WHERE id = ?";

	private EventoSql() {
		super();
	}
}
