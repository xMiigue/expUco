package co.edu.uco.expuco.negocio.fachada.impl;

import java.util.List;

import co.edu.uco.expuco.datos.fabrica.FabricaDAO;
import co.edu.uco.expuco.datos.fabrica.FabricaEnum;
import co.edu.uco.expuco.dto.EventoDTO;
import co.edu.uco.expuco.negocio.casouso.impl.ConsultarEventosCasoUsoImpl;
import co.edu.uco.expuco.negocio.fachada.ConsultarEventosFachada;
import co.edu.uco.expuco.transversal.excepcion.ExpUcoException;

// Fachada de consulta (solo lectura): abre la conexion, ejecuta y la cierra. No necesita transaccion.
public final class ConsultarEventosFachadaImpl implements ConsultarEventosFachada {

	@Override
	public List<EventoDTO> ejecutar() {
		final FabricaDAO fabricaDAO = FabricaDAO.obtenerInstancia(FabricaEnum.SQL_SERVER);
		try {
			fabricaDAO.abrirConexion();
			return new ConsultarEventosCasoUsoImpl(fabricaDAO).ejecutar();
		} catch (final ExpUcoException e) {
			throw e;
		} catch (final Exception e) {
			throw ExpUcoException.crear(e, "Error inesperado al consultar los eventos.", e.getMessage());
		} finally {
			fabricaDAO.cerrarConexion();
		}
	}
}
