package co.edu.uco.expuco.negocio.fachada;

import co.edu.uco.expuco.dto.ResultadoRegistroDTO;

// Fachada: punto de entrada del negocio para registrar un usuario nuevo (orquesta la transaccion).
public interface RegistrarUsuarioFachada {

	ResultadoRegistroDTO ejecutar(String nombre, Long documento);
}
