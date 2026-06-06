package co.edu.uco.expuco.datos.dao.sqlserver;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
			ps.setLong(1, usuario.getDocumento());
			ps.setString(2, usuario.getRol());
			ps.setString(3, usuario.getNombres());
			ps.setString(4, usuario.getApellidos());
			ps.setDate(5, Date.valueOf(usuario.getFechaNacimiento()));
			ps.setString(6, usuario.getCorreo());
			ps.executeUpdate();
		} catch (final SQLException e) {
			throw ExpUcoException.crear(e, "Error al registrar el usuario.", e.getMessage());
		}
	}
}
