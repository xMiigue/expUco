package co.edu.uco.expuco.negocio.casouso.impl;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.expuco.datos.fabrica.FabricaDAO;
import co.edu.uco.expuco.dto.UsuarioDTO;
import co.edu.uco.expuco.entidad.UsuarioEntidad;
import co.edu.uco.expuco.negocio.casouso.ConsultarUsuariosCasoUso;

// Implementacion del caso de uso: pide las entidades al DAO y las convierte a DTO.
public final class ConsultarUsuariosCasoUsoImpl implements ConsultarUsuariosCasoUso {

	private final FabricaDAO fabricaDAO;

	public ConsultarUsuariosCasoUsoImpl(final FabricaDAO fabricaDAO) {
		super();
		this.fabricaDAO = fabricaDAO;
	}

	@Override
	public List<UsuarioDTO> ejecutar() {
		final List<UsuarioDTO> resultado = new ArrayList<>();
		for (final UsuarioEntidad entidad : fabricaDAO.obtenerUsuarioDAO().consultarTodos()) {
			resultado.add(new UsuarioDTO.Builder()
					.id(entidad.getId())
					.nombre(entidad.getNombre())
					.build());
		}
		return resultado;
	}
}
