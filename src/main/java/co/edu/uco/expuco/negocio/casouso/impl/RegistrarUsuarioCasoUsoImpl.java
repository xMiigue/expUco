package co.edu.uco.expuco.negocio.casouso.impl;

import co.edu.uco.expuco.datos.fabrica.FabricaDAO;
import co.edu.uco.expuco.dto.ResultadoRegistroDTO;
import co.edu.uco.expuco.dto.UsuarioDTO;
import co.edu.uco.expuco.entidad.UsuarioEntidad;
import co.edu.uco.expuco.negocio.casouso.RegistrarUsuarioCasoUso;

// Implementacion: si el documento ya existe avisa; si no, crea el usuario y lo devuelve.
public final class RegistrarUsuarioCasoUsoImpl implements RegistrarUsuarioCasoUso {

	private final FabricaDAO fabricaDAO;

	public RegistrarUsuarioCasoUsoImpl(final FabricaDAO fabricaDAO) {
		super();
		this.fabricaDAO = fabricaDAO;
	}

	@Override
	public ResultadoRegistroDTO ejecutar(final String nombre, final Long documento) {

		// Si ya existe un usuario con ese documento, no se registra de nuevo.
		final UsuarioEntidad existente = fabricaDAO.obtenerUsuarioDAO().consultarPorDocumento(documento);
		if (existente != null) {
			return new ResultadoRegistroDTO.Builder()
					.exitoso(false)
					.motivo("Ese documento ya esta registrado.")
					.build();
		}

		// Insertamos el usuario nuevo.
		final UsuarioEntidad nuevo = new UsuarioEntidad.Builder()
				.nombre(nombre)
				.documento(documento)
				.build();
		fabricaDAO.obtenerUsuarioDAO().crear(nuevo);

		// Lo volvemos a leer para obtener el id que genero la base de datos.
		final UsuarioEntidad creado = fabricaDAO.obtenerUsuarioDAO().consultarPorDocumento(documento);
		final UsuarioDTO usuarioDTO = new UsuarioDTO.Builder()
				.id(creado.getId())
				.nombre(creado.getNombre())
				.documento(creado.getDocumento())
				.build();

		return new ResultadoRegistroDTO.Builder()
				.exitoso(true)
				.motivo("Usuario registrado correctamente.")
				.usuario(usuarioDTO)
				.build();
	}
}
