package co.edu.uco.expuco.negocio.fachada.impl;
import co.edu.uco.expuco.datos.fabrica.FabricaDAO;
import co.edu.uco.expuco.datos.fabrica.FabricaEnum;
import co.edu.uco.expuco.dto.InscripcionDTO;
import co.edu.uco.expuco.dto.ResultadoInscripcionDTO;
import co.edu.uco.expuco.entidad.UsuarioEntidad;
import co.edu.uco.expuco.negocio.casouso.impl.InscribirCasoUsoImpl;
import co.edu.uco.expuco.negocio.fachada.InscribirFachada;
import co.edu.uco.expuco.transversal.excepcion.ExpUcoException;
// Fachada de inscripcion: AQUI VIVE LA TRANSACCION.
// abrir conexion -> iniciar transaccion -> caso de uso (reglas + crear persona + insertar) ->
// si todo va bien CONFIRMA (commit); si una regla fallo o hay error CANCELA (rollback); al final CIERRA.
public final class InscribirFachadaImpl implements InscribirFachada {
	@Override
	public ResultadoInscripcionDTO ejecutar(final InscripcionDTO datos) {
		final FabricaDAO fabricaDAO = FabricaDAO.obtenerInstancia(FabricaEnum.SQL_SERVER);
		try {
			fabricaDAO.abrirConexion();
			fabricaDAO.iniciarTransaccion();
			// Armamos la persona con los datos capturados en los dos pasos del formulario.
			final UsuarioEntidad persona = new UsuarioEntidad.Builder()
					.documento(datos.getDocumento())
					.rol(datos.getRol())
					.nombres(datos.getNombres())
					.apellidos(datos.getApellidos())
					.fechaNacimiento(datos.getFechaNacimiento())
					.correo(datos.getCorreo())
					.build();
			final ResultadoInscripcionDTO resultado =
					new InscribirCasoUsoImpl(fabricaDAO).ejecutar(persona, datos.getEventoId());

			// Revisamos el resultado antes de decidir:
			// si la inscripcion fue exitosa, confirmamos (commit);
			// si una regla fallo, cancelamos (rollback) para deshacer cualquier cambio
			// que el caso de uso haya hecho, como una persona recien creada.
			if (resultado.isExitoso()) {
				fabricaDAO.confirmarTransaccion();
			} else {
				fabricaDAO.cancelarTransaccion(); // -> rollback cuando regla de negocio falla
			}
			return resultado;
		} catch (final ExpUcoException e) {
			fabricaDAO.cancelarTransaccion(); // -> rollback  
											//es para errores que el código ya 
											//controló y envolvió en ExpUcoException, 
											//normalmente fallos de la base de datos manejados desde la FabricaDAO
			throw e;
		} catch (final Exception e) {
			fabricaDAO.cancelarTransaccion(); // -> rollback error inesperado
			throw ExpUcoException.crear(e, "Error inesperado al realizar la inscripcion.", e.getMessage());
		} finally {
			fabricaDAO.cerrarConexion();
		}
	}
}