package co.edu.uco.expuco.datos.dao.h2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.uco.expuco.datos.dao.UsuarioDAO;
import co.edu.uco.expuco.datos.dao.sql.SQLDAO;
import co.edu.uco.expuco.datos.mapeador.UsuarioMapper;
import co.edu.uco.expuco.datos.sql.UsuarioSql;
import co.edu.uco.expuco.entidad.UsuarioEntidad;
import co.edu.uco.expuco.transversal.excepcion.ExpUcoException;

// Implementacion del UsuarioDAO sobre H2 usando JDBC puro.
public final class UsuarioH2DAO extends SQLDAO implements UsuarioDAO {

	public UsuarioH2DAO(final Connection conexion) {
		super(conexion);
	}

	@Override
	public List<UsuarioEntidad> consultarTodos() {
		final List<UsuarioEntidad> resultado = new ArrayList<>();
		try (PreparedStatement ps = getConexion().prepareStatement(UsuarioSql.CONSULTAR_TODOS);
				ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				resultado.add(UsuarioMapper.obtenerInstancia().mapear(rs));
			}
		} catch (final SQLException e) {
			throw ExpUcoException.crear(e, "Error al consultar los usuarios.", e.getMessage());
		}
		return resultado;
	}
}
