package co.edu.uco.expuco.datos.dao.sqlserver;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.uco.expuco.datos.dao.InscripcionDAO;
import co.edu.uco.expuco.datos.dao.sql.SQLDAO;
import co.edu.uco.expuco.datos.mapeador.EventoMapper;
import co.edu.uco.expuco.datos.sql.InscripcionSql;
import co.edu.uco.expuco.entidad.EventoEntidad;
import co.edu.uco.expuco.entidad.InscripcionEntidad;
import co.edu.uco.expuco.transversal.excepcion.ExpUcoException;

// Implementacion del InscripcionDAO sobre SQL Server usando JDBC puro.
public final class InscripcionSqlServerDAO extends SQLDAO implements InscripcionDAO {

	public InscripcionSqlServerDAO(final Connection conexion) {
		super(conexion);
	}

	@Override
	public int contarPorEvento(final Long eventoId) {
		try (PreparedStatement ps = getConexion().prepareStatement(InscripcionSql.CONTAR_POR_EVENTO)) {
			ps.setLong(1, eventoId);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return rs.getInt(1);
				}
			}
		} catch (final SQLException e) {
			throw ExpUcoException.crear(e, "Error al contar las inscripciones del evento.", e.getMessage());
		}
		return 0;
	}

	@Override
	public List<EventoEntidad> consultarEventosPorUsuario(final Long usuarioId) {
		final List<EventoEntidad> resultado = new ArrayList<>();
		try (PreparedStatement ps = getConexion().prepareStatement(InscripcionSql.CONSULTAR_EVENTOS_POR_USUARIO)) {
			ps.setLong(1, usuarioId);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					resultado.add(EventoMapper.obtenerInstancia().mapear(rs));
				}
			}
		} catch (final SQLException e) {
			throw ExpUcoException.crear(e, "Error al consultar los eventos del usuario.", e.getMessage());
		}
		return resultado;
	}

	@Override
	public void crear(final InscripcionEntidad entidad) {
		try (PreparedStatement ps = getConexion().prepareStatement(InscripcionSql.CREAR)) {
			ps.setLong(1, entidad.getUsuarioId());
			ps.setLong(2, entidad.getEventoId());
			ps.executeUpdate();
		} catch (final SQLException e) {
			throw ExpUcoException.crear(e, "Error al registrar la inscripcion.", e.getMessage());
		}
	}
}
