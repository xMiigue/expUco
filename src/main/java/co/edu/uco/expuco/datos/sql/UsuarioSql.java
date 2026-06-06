package co.edu.uco.expuco.datos.sql;

// Sentencias SQL de la tabla usuario, centralizadas en un solo lugar.
public final class UsuarioSql {

	public static final String CONSULTAR_TODOS =
			"SELECT id, nombre, documento FROM usuario ORDER BY id";

	public static final String CONSULTAR_POR_DOCUMENTO =
			"SELECT id, nombre, documento FROM usuario WHERE documento = ?";

	// El id lo genera SQL Server (IDENTITY), por eso no lo incluimos en el INSERT.
	public static final String CREAR =
			"INSERT INTO usuario (nombre, documento) VALUES (?, ?)";

	private UsuarioSql() {
		super();
	}
}
