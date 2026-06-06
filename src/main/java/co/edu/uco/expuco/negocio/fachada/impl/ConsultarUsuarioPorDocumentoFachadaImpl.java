package co.edu.uco.expuco.negocio.fachada.impl;

import co.edu.uco.expuco.datos.fabrica.FabricaDAO;
import co.edu.uco.expuco.datos.fabrica.FabricaEnum;
import co.edu.uco.expuco.dto.UsuarioDTO;
import co.edu.uco.expuco.negocio.casouso.impl.ConsultarUsuarioPorDocumentoCasoUsoImpl;
import co.edu.uco.expuco.negocio.fachada.ConsultarUsuarioPorDocumentoFachada;
import co.edu.uco.expuco.transversal.excepcion.ExpUcoException;

// Fachada de consulta (solo lectura): abre la conexion, ejecuta y la cierra. No necesita transaccion.
public final class ConsultarUsuarioPorDocumentoFachadaImpl implements ConsultarUsuarioPorDocumentoFachada {

	@Override
	public UsuarioDTO ejecutar(final Long documento) {
		final FabricaDAO fabricaDAO = FabricaDAO.obtenerInstancia(FabricaEnum.SQL_SERVER);
		try {
			fabricaDAO.abrirConexion();
			return new ConsultarUsuarioPorDocumentoCasoUsoImpl(fabricaDAO).ejecutar(documento);
		} catch (final ExpUcoException e) {
			throw e;
		} catch (final Exception e) {
			throw ExpUcoException.crear(e, "Error inesperado al identificar al usuario.", e.getMessage());
		} finally {
			fabricaDAO.cerrarConexion();
		}
	}
}
