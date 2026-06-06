package co.edu.uco.expuco.datos.fabrica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import co.edu.uco.expuco.datos.dao.EventoDAO;
import co.edu.uco.expuco.datos.dao.InscripcionDAO;
import co.edu.uco.expuco.datos.dao.UsuarioDAO;
import co.edu.uco.expuco.datos.fabrica.h2.H2FabricaDAO;
import co.edu.uco.expuco.transversal.excepcion.ExpUcoException;

// Fabrica abstracta de DAO. Tambien maneja la CONEXION y la TRANSACCION de forma explicita.
// Se conecta a la MISMA base H2 en memoria que Spring crea al arrancar (jdbc:h2:mem:expuco).
public abstract class FabricaDAO {

	private static final String URL = "jdbc:h2:mem:expuco;DB_CLOSE_DELAY=-1";
	private static final String USUARIO = "sa";
	private static final String CLAVE = "";

	protected Connection conexion;

	protected FabricaDAO() {
		super();
	}

	// Cada fabrica concreta sabe construir sus DAO con la conexion abierta.
	public abstract UsuarioDAO obtenerUsuarioDAO();

	public abstract EventoDAO obtenerEventoDAO();

	public abstract InscripcionDAO obtenerInscripcionDAO();

	public void abrirConexion() {
		try {
			conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
		} catch (final SQLException e) {
			throw ExpUcoException.crear(e, "Error al abrir la conexion con la base de datos.", e.getMessage());
		}
	}

	public void cerrarConexion() {
		try {
			if (conexion != null && !conexion.isClosed()) {
				conexion.close();
			}
		} catch (final SQLException e) {
			throw ExpUcoException.crear(e, "Error al cerrar la conexion con la base de datos.", e.getMessage());
		}
	}

	// Inicia la transaccion: desactiva el autocommit para controlar commit/rollback nosotros.
	public void iniciarTransaccion() {
		try {
			conexion.setAutoCommit(false);
		} catch (final SQLException e) {
			throw ExpUcoException.crear(e, "Error al iniciar la transaccion.", e.getMessage());
		}
	}

	// Confirma (guarda) todos los cambios de la transaccion.
	public void confirmarTransaccion() {
		try {
			conexion.commit();
		} catch (final SQLException e) {
			throw ExpUcoException.crear(e, "Error al confirmar la transaccion.", e.getMessage());
		}
	}

	// Cancela (deshace) todos los cambios de la transaccion.
	public void cancelarTransaccion() {
		try {
			conexion.rollback();
		} catch (final SQLException e) {
			throw ExpUcoException.crear(e, "Error al cancelar la transaccion.", e.getMessage());
		}
	}

	public static FabricaDAO obtenerInstancia(final FabricaEnum tipo) {
		if (FabricaEnum.H2.equals(tipo)) {
			return new H2FabricaDAO();
		}
		throw ExpUcoException.crear("Tipo de fabrica no soportado.", "FabricaEnum no reconocido: " + tipo);
	}
}
