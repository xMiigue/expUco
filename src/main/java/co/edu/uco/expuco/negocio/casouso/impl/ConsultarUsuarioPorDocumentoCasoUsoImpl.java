package co.edu.uco.expuco.negocio.casouso.impl;

import co.edu.uco.expuco.datos.fabrica.FabricaDAO;
import co.edu.uco.expuco.dto.UsuarioDTO;
import co.edu.uco.expuco.entidad.UsuarioEntidad;
import co.edu.uco.expuco.negocio.casouso.ConsultarUsuarioPorDocumentoCasoUso;

// Implementacion: pide el usuario al DAO por documento y lo convierte a DTO (null si no existe).
public final class ConsultarUsuarioPorDocumentoCasoUsoImpl implements ConsultarUsuarioPorDocumentoCasoUso {

	private final FabricaDAO fabricaDAO;

	public ConsultarUsuarioPorDocumentoCasoUsoImpl(final FabricaDAO fabricaDAO) {
		super();
		this.fabricaDAO = fabricaDAO;
	}

	@Override
	public UsuarioDTO ejecutar(final Long documento) {
		final UsuarioEntidad entidad = fabricaDAO.obtenerUsuarioDAO().consultarPorDocumento(documento);
		if (entidad == null) {
			return null;
		}
		return new UsuarioDTO.Builder()
				.id(entidad.getId())
				.nombre(entidad.getNombre())
				.documento(entidad.getDocumento())
				.build();
	}
}
