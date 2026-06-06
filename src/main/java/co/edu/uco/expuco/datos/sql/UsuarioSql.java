package co.edu.uco.expuco.datos.sql;

// Sentencias SQL de la tabla usuario, centralizadas en un solo lugar.
public final class UsuarioSql {

	public static final String CONSULTAR_TODOS =
			"SELECT id, nombre FROM usuario ORDER BY id";

	private UsuarioSql() {
		super();
	}
}
