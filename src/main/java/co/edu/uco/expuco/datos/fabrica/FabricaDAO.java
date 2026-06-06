package co.edu.uco.expuco.datos.fabrica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import co.edu.uco.expuco.datos.dao.EventoDAO;
import co.edu.uco.expuco.datos.dao.InscripcionDAO;
import co.edu.uco.expuco.datos.dao.UsuarioDAO;
import co.edu.uco.expuco.datos.fabrica.sqlserver.SqlServerFabricaDAO;
import co.edu.uco.expuco.transversal.excepcion.ExpUcoException;

// Fabrica abstracta de DAO. Tambien maneja la CONEXION y la TRANSACCION de forma explicita.
// Se conecta al MISMO SQL Server (base ExpUco) que usa Spring al arrancar.
public abstract class FabricaDAO {

	private static final String URL =
			"jdbc:sqlserver://localhost:1435;databaseName=ExpUco;encrypt=false;trustServerCertificate=true";
	private static final String USUARIO = "sa";
	private static final String CLAVE = "ExpUco2026!";

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
		if (FabricaEnum.SQL_SERVER.equals(tipo)) {
			return new SqlServerFabricaDAO();
		}
		throw ExpUcoException.crear("Tipo de fabrica no soportado.", "FabricaEnum no reconocido: " + tipo);
	}
}
