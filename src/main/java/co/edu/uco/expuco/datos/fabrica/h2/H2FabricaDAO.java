package co.edu.uco.expuco.datos.fabrica.h2;

import co.edu.uco.expuco.datos.dao.EventoDAO;
import co.edu.uco.expuco.datos.dao.InscripcionDAO;
import co.edu.uco.expuco.datos.dao.UsuarioDAO;
import co.edu.uco.expuco.datos.dao.h2.EventoH2DAO;
import co.edu.uco.expuco.datos.dao.h2.InscripcionH2DAO;
import co.edu.uco.expuco.datos.dao.h2.UsuarioH2DAO;
import co.edu.uco.expuco.datos.fabrica.FabricaDAO;

// Fabrica concreta para H2: entrega los DAO de H2 usando la conexion ya abierta.
public final class H2FabricaDAO extends FabricaDAO {

	public H2FabricaDAO() {
		super();
	}

	@Override
	public UsuarioDAO obtenerUsuarioDAO() {
		return new UsuarioH2DAO(conexion);
	}

	@Override
	public EventoDAO obtenerEventoDAO() {
		return new EventoH2DAO(conexion);
	}

	@Override
	public InscripcionDAO obtenerInscripcionDAO() {
		return new InscripcionH2DAO(conexion);
	}
}
