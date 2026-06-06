package co.edu.uco.expuco.negocio.fachada.impl;

import co.edu.uco.expuco.datos.fabrica.FabricaDAO;
import co.edu.uco.expuco.datos.fabrica.FabricaEnum;
import co.edu.uco.expuco.dto.InscripcionDTO;
import co.edu.uco.expuco.dto.ResultadoInscripcionDTO;
import co.edu.uco.expuco.negocio.casouso.impl.InscribirCasoUsoImpl;
import co.edu.uco.expuco.negocio.fachada.InscribirFachada;
import co.edu.uco.expuco.transversal.excepcion.ExpUcoException;

// Fachada de inscripcion: AQUI VIVE LA TRANSACCION.
// abrir conexion -> iniciar transaccion -> caso de uso (3 reglas + insertar) ->
// si todo va bien CONFIRMA (commit); si hay error CANCELA (rollback); al final CIERRA la conexion.
public final class InscribirFachadaImpl implements InscribirFachada {

	@Override
	public ResultadoInscripcionDTO ejecutar(final InscripcionDTO datos) {
		final FabricaDAO fabricaDAO = FabricaDAO.obtenerInstancia(FabricaEnum.H2);
		try {
			fabricaDAO.abrirConexion();
			fabricaDAO.iniciarTransaccion();

			final ResultadoInscripcionDTO resultado =
					new InscribirCasoUsoImpl(fabricaDAO).ejecutar(datos.getUsuarioId(), datos.getEventoId());

			fabricaDAO.confirmarTransaccion();
			return resultado;
		} catch (final ExpUcoException e) {
			fabricaDAO.cancelarTransaccion();
			throw e;
		} catch (final Exception e) {
			fabricaDAO.cancelarTransaccion();
			throw ExpUcoException.crear(e, "Error inesperado al realizar la inscripcion.", e.getMessage());
		} finally {
			fabricaDAO.cerrarConexion();
		}
	}
}
