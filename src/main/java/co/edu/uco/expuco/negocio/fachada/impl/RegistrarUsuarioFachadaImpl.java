package co.edu.uco.expuco.negocio.fachada.impl;

import co.edu.uco.expuco.datos.fabrica.FabricaDAO;
import co.edu.uco.expuco.datos.fabrica.FabricaEnum;
import co.edu.uco.expuco.dto.ResultadoRegistroDTO;
import co.edu.uco.expuco.negocio.casouso.impl.RegistrarUsuarioCasoUsoImpl;
import co.edu.uco.expuco.negocio.fachada.RegistrarUsuarioFachada;
import co.edu.uco.expuco.transversal.excepcion.ExpUcoException;

// Fachada de registro: maneja la TRANSACCION (igual patron que la inscripcion).
// abrir -> iniciar -> caso de uso -> confirmar (commit) o cancelar (rollback) -> cerrar.
public final class RegistrarUsuarioFachadaImpl implements RegistrarUsuarioFachada {

	@Override
	public ResultadoRegistroDTO ejecutar(final String nombre, final Long documento) {
		final FabricaDAO fabricaDAO = FabricaDAO.obtenerInstancia(FabricaEnum.SQL_SERVER);
		try {
			fabricaDAO.abrirConexion();
			fabricaDAO.iniciarTransaccion();

			final ResultadoRegistroDTO resultado =
					new RegistrarUsuarioCasoUsoImpl(fabricaDAO).ejecutar(nombre, documento);

			fabricaDAO.confirmarTransaccion();
			return resultado;
		} catch (final ExpUcoException e) {
			fabricaDAO.cancelarTransaccion();
			throw e;
		} catch (final Exception e) {
			fabricaDAO.cancelarTransaccion();
			throw ExpUcoException.crear(e, "Error inesperado al registrar el usuario.", e.getMessage());
		} finally {
			fabricaDAO.cerrarConexion();
		}
	}
}
