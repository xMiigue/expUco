package co.edu.uco.expuco.datos.dao.sqlserver;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.uco.expuco.datos.dao.EventoDAO;
import co.edu.uco.expuco.datos.dao.sql.SQLDAO;
import co.edu.uco.expuco.datos.mapeador.EventoMapper;
import co.edu.uco.expuco.datos.sql.EventoSql;
import co.edu.uco.expuco.entidad.EventoEntidad;
import co.edu.uco.expuco.transversal.excepcion.ExpUcoException;

// Implementacion del EventoDAO sobre SQL Server usando JDBC puro.
public final class EventoSqlServerDAO extends SQLDAO implements EventoDAO {

	public EventoSqlServerDAO(final Connection conexion) {
		super(conexion);
	}

	@Override
	public List<EventoEntidad> consultarTodos() {
		final List<EventoEntidad> resultado = new ArrayList<>();
		try (PreparedStatement ps = getConexion().prepareStatement(EventoSql.CONSULTAR_TODOS);
				ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				resultado.add(EventoMapper.obtenerInstancia().mapear(rs));
			}
		} catch (final SQLException e) {
			throw ExpUcoException.crear(e, "Error al consultar los eventos.", e.getMessage());
		}
		return resultado;
	}

	@Override
	public EventoEntidad consultarPorId(final Long id) {
		try (PreparedStatement ps = getConexion().prepareStatement(EventoSql.CONSULTAR_POR_ID)) {
			ps.setLong(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return EventoMapper.obtenerInstancia().mapear(rs);
				}
			}
		} catch (final SQLException e) {
			throw ExpUcoException.crear(e, "Error al consultar el evento.", e.getMessage());
		}
		return null;
	}
}
