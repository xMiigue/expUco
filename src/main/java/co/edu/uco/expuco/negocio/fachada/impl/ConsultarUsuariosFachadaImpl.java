package co.edu.uco.expuco.negocio.fachada.impl;

import java.util.List;

import co.edu.uco.expuco.datos.fabrica.FabricaDAO;
import co.edu.uco.expuco.datos.fabrica.FabricaEnum;
import co.edu.uco.expuco.dto.UsuarioDTO;
import co.edu.uco.expuco.negocio.casouso.impl.ConsultarUsuariosCasoUsoImpl;
import co.edu.uco.expuco.negocio.fachada.ConsultarUsuariosFachada;
import co.edu.uco.expuco.transversal.excepcion.ExpUcoException;

// Fachada de consulta (solo lectura): abre la conexion, ejecuta y la cierra. No necesita transaccion.
public final class ConsultarUsuariosFachadaImpl implements ConsultarUsuariosFachada {

	@Override
	public List<UsuarioDTO> ejecutar() {
		final FabricaDAO fabricaDAO = FabricaDAO.obtenerInstancia(FabricaEnum.H2);
		try {
			fabricaDAO.abrirConexion();
			return new ConsultarUsuariosCasoUsoImpl(fabricaDAO).ejecutar();
		} catch (final ExpUcoException e) {
			throw e;
		} catch (final Exception e) {
			throw ExpUcoException.crear(e, "Error inesperado al consultar los usuarios.", e.getMessage());
		} finally {
			fabricaDAO.cerrarConexion();
		}
	}
}
