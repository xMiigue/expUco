package co.edu.uco.expuco.datos.fabrica.sqlserver;

import co.edu.uco.expuco.datos.dao.EventoDAO;
import co.edu.uco.expuco.datos.dao.InscripcionDAO;
import co.edu.uco.expuco.datos.dao.UsuarioDAO;
import co.edu.uco.expuco.datos.dao.sqlserver.EventoSqlServerDAO;
import co.edu.uco.expuco.datos.dao.sqlserver.InscripcionSqlServerDAO;
import co.edu.uco.expuco.datos.dao.sqlserver.UsuarioSqlServerDAO;
import co.edu.uco.expuco.datos.fabrica.FabricaDAO;

// Fabrica concreta para SQL Server: entrega los DAO de SQL Server usando la conexion ya abierta.
public final class SqlServerFabricaDAO extends FabricaDAO {

	public SqlServerFabricaDAO() {
		super();
	}

	@Override
	public UsuarioDAO obtenerUsuarioDAO() {
		return new UsuarioSqlServerDAO(conexion);
	}

	@Override
	public EventoDAO obtenerEventoDAO() {
		return new EventoSqlServerDAO(conexion);
	}

	@Override
	public InscripcionDAO obtenerInscripcionDAO() {
		return new InscripcionSqlServerDAO(conexion);
	}
}
