package co.edu.uco.expuco.datos.sql;

// Sentencias SQL de la tabla usuario, centralizadas en un solo lugar.
public final class UsuarioSql {

	public static final String CONSULTAR_POR_DOCUMENTO =
			"SELECT id, documento, rol, nombres, apellidos, fecha_nacimiento, correo "
			+ "FROM usuario WHERE documento = ?";

	// El id lo genera SQL Server (IDENTITY), por eso no lo incluimos en el INSERT.
	public static final String CREAR =
			"INSERT INTO usuario (documento, rol, nombres, apellidos, fecha_nacimiento, correo) "
			+ "VALUES (?, ?, ?, ?, ?, ?)";

	private UsuarioSql() {
		super();
	}
}
