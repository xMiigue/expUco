package co.edu.uco.expuco.datos.dao.sqlserver;

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

// Implementacion del UsuarioDAO sobre SQL Server usando JDBC puro.
public final class UsuarioSqlServerDAO extends SQLDAO implements UsuarioDAO {

	public UsuarioSqlServerDAO(final Connection conexion) {
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

	@Override
	public UsuarioEntidad consultarPorDocumento(final Long documento) {
		try (PreparedStatement ps = getConexion().prepareStatement(UsuarioSql.CONSULTAR_POR_DOCUMENTO)) {
			ps.setLong(1, documento);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return UsuarioMapper.obtenerInstancia().mapear(rs);
				}
			}
		} catch (final SQLException e) {
			throw ExpUcoException.crear(e, "Error al consultar el usuario por documento.", e.getMessage());
		}
		return null;
	}

	@Override
	public void crear(final UsuarioEntidad usuario) {
		try (PreparedStatement ps = getConexion().prepareStatement(UsuarioSql.CREAR)) {
			ps.setString(1, usuario.getNombre());
			ps.setLong(2, usuario.getDocumento());
			ps.executeUpdate();
		} catch (final SQLException e) {
			throw ExpUcoException.crear(e, "Error al registrar el usuario.", e.getMessage());
		}
	}
}
