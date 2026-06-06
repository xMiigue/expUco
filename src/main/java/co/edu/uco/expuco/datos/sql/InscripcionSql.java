package co.edu.uco.expuco.datos.sql;

// Sentencias SQL de la tabla inscripcion, centralizadas en un solo lugar.
public final class InscripcionSql {

	public static final String CREAR =
			"INSERT INTO inscripcion (usuario_id, evento_id) VALUES (?, ?)";

	public static final String CONTAR_POR_EVENTO =
			"SELECT COUNT(*) FROM inscripcion WHERE evento_id = ?";

	// Devuelve los eventos a los que un usuario ya esta inscrito (para el choque de horario).
	public static final String CONSULTAR_EVENTOS_POR_USUARIO =
			"SELECT e.id, e.nombre, e.lugar, e.fecha_inicio, e.fecha_fin, "
			+ "e.inscripcion_inicio, e.inscripcion_fin, e.capacidad "
			+ "FROM evento e JOIN inscripcion i ON i.evento_id = e.id "
			+ "WHERE i.usuario_id = ?";

	private InscripcionSql() {
		super();
	}
}
