package co.edu.uco.expuco.datos.mapeador;

import java.sql.ResultSet;
import java.sql.SQLException;

import co.edu.uco.expuco.entidad.UsuarioEntidad;

// Convierte una fila del ResultSet en una UsuarioEntidad.
public final class UsuarioMapper {

	private static final UsuarioMapper INSTANCIA = new UsuarioMapper();

	private UsuarioMapper() {
		super();
	}

	public static UsuarioMapper obtenerInstancia() {
		return INSTANCIA;
	}

	public UsuarioEntidad mapear(final ResultSet rs) throws SQLException {
		return new UsuarioEntidad.Builder()
				.id(rs.getLong("id"))
				.nombre(rs.getString("nombre"))
				.build();
	}
}
