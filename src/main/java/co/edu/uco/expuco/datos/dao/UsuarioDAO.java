package co.edu.uco.expuco.datos.dao;

import java.util.List;

import co.edu.uco.expuco.entidad.UsuarioEntidad;

// Contrato del DAO de usuario. Solo lo que ExpUco necesita: listar.
public interface UsuarioDAO {

	List<UsuarioEntidad> consultarTodos();
}
