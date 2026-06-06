package co.edu.uco.expuco.datos.dao.sql;

import java.sql.Connection;

// Clase base de todos los DAO SQL: guarda la conexion que les inyecta la fabrica.
public abstract class SQLDAO {

	private final Connection conexion;

	protected SQLDAO(final Connection conexion) {
		super();
		this.conexion = conexion;
	}

	protected Connection getConexion() {
		return conexion;
	}
}
