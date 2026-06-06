package co.edu.uco.expuco.controlador;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uco.expuco.dto.EventoDTO;
import co.edu.uco.expuco.dto.InscripcionDTO;
import co.edu.uco.expuco.dto.RegistroUsuarioDTO;
import co.edu.uco.expuco.dto.ResultadoInscripcionDTO;
import co.edu.uco.expuco.dto.ResultadoRegistroDTO;
import co.edu.uco.expuco.dto.UsuarioDTO;
import co.edu.uco.expuco.negocio.fachada.ConsultarEventosFachada;
import co.edu.uco.expuco.negocio.fachada.ConsultarEventosInscritosFachada;
import co.edu.uco.expuco.negocio.fachada.ConsultarUsuarioPorDocumentoFachada;
import co.edu.uco.expuco.negocio.fachada.ConsultarUsuariosFachada;
import co.edu.uco.expuco.negocio.fachada.InscribirFachada;
import co.edu.uco.expuco.negocio.fachada.RegistrarUsuarioFachada;
import co.edu.uco.expuco.negocio.fachada.impl.ConsultarEventosFachadaImpl;
import co.edu.uco.expuco.negocio.fachada.impl.ConsultarEventosInscritosFachadaImpl;
import co.edu.uco.expuco.negocio.fachada.impl.ConsultarUsuarioPorDocumentoFachadaImpl;
import co.edu.uco.expuco.negocio.fachada.impl.ConsultarUsuariosFachadaImpl;
import co.edu.uco.expuco.negocio.fachada.impl.InscribirFachadaImpl;
import co.edu.uco.expuco.negocio.fachada.impl.RegistrarUsuarioFachadaImpl;

// Unico controlador REST. Cada endpoint solo llama a su fachada de negocio.
@RestController
@RequestMapping("/api")
public class ExpUcoControlador {

	// GET /api/usuarios -> lista de usuarios.
	@GetMapping("/usuarios")
	public List<UsuarioDTO> consultarUsuarios() {
		final ConsultarUsuariosFachada fachada = new ConsultarUsuariosFachadaImpl();
		return fachada.ejecutar();
	}

	// GET /api/usuarios/documento/{documento} -> identifica a un usuario por su documento.
	// 200 con el usuario si existe; 404 si no existe (para que el front ofrezca registro).
	@GetMapping("/usuarios/documento/{documento}")
	public ResponseEntity<UsuarioDTO> consultarUsuarioPorDocumento(@PathVariable final Long documento) {
		final ConsultarUsuarioPorDocumentoFachada fachada = new ConsultarUsuarioPorDocumentoFachadaImpl();
		final UsuarioDTO usuario = fachada.ejecutar(documento);
		if (usuario == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(usuario);
	}

	// POST /api/usuarios -> registra un usuario nuevo. Siempre responde 200 con {exitoso, motivo, usuario}.
	@PostMapping("/usuarios")
	public ResultadoRegistroDTO registrarUsuario(@RequestBody final RegistroUsuarioDTO datos) {
		final RegistrarUsuarioFachada fachada = new RegistrarUsuarioFachadaImpl();
		return fachada.ejecutar(datos.getNombre(), datos.getDocumento());
	}

	// GET /api/usuarios/{usuarioId}/eventos -> eventos a los que ese usuario ya esta inscrito.
	@GetMapping("/usuarios/{usuarioId}/eventos")
	public List<EventoDTO> consultarEventosInscritos(@PathVariable final Long usuarioId) {
		final ConsultarEventosInscritosFachada fachada = new ConsultarEventosInscritosFachadaImpl();
		return fachada.ejecutar(usuarioId);
	}

	// GET /api/eventos -> lista de eventos con cupos disponibles y si estan vigentes.
	@GetMapping("/eventos")
	public List<EventoDTO> consultarEventos() {
		final ConsultarEventosFachada fachada = new ConsultarEventosFachadaImpl();
		return fachada.ejecutar();
	}

	// POST /api/inscripciones -> intenta inscribir y devuelve {exitoso, motivo}.
	@PostMapping("/inscripciones")
	public ResultadoInscripcionDTO inscribir(@RequestBody final InscripcionDTO datos) {
		final InscribirFachada fachada = new InscribirFachadaImpl();
		return fachada.ejecutar(datos);
	}
}
