package co.edu.uco.expuco.datos.dao;

import co.edu.uco.expuco.entidad.UsuarioEntidad;

// Contrato del DAO de usuario: buscar por documento y crear.
public interface UsuarioDAO {

	// Busca un usuario por su documento. Null si no existe (para crearlo al inscribirse).
	UsuarioEntidad consultarPorDocumento(Long documento);

	// Inserta un usuario nuevo con sus datos.
	void crear(UsuarioEntidad usuario);
}
