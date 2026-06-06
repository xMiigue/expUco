package co.edu.uco.expuco.datos.dao;

import java.util.List;

import co.edu.uco.expuco.entidad.UsuarioEntidad;

// Contrato del DAO de usuario: listar, buscar por documento y crear.
public interface UsuarioDAO {

	List<UsuarioEntidad> consultarTodos();

	// Busca un usuario por su numero de documento (para identificacion). Null si no existe.
	UsuarioEntidad consultarPorDocumento(Long documento);

	// Inserta un usuario nuevo (registro en vivo).
	void crear(UsuarioEntidad usuario);
}
