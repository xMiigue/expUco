package co.edu.uco.expuco.controlador;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uco.expuco.dto.EventoDTO;
import co.edu.uco.expuco.dto.InscripcionDTO;
import co.edu.uco.expuco.dto.ResultadoInscripcionDTO;
import co.edu.uco.expuco.dto.UsuarioDTO;
import co.edu.uco.expuco.negocio.fachada.ConsultarEventosFachada;
import co.edu.uco.expuco.negocio.fachada.ConsultarUsuariosFachada;
import co.edu.uco.expuco.negocio.fachada.InscribirFachada;
import co.edu.uco.expuco.negocio.fachada.impl.ConsultarEventosFachadaImpl;
import co.edu.uco.expuco.negocio.fachada.impl.ConsultarUsuariosFachadaImpl;
import co.edu.uco.expuco.negocio.fachada.impl.InscribirFachadaImpl;

// Unico controlador REST. Cada endpoint solo llama a su fachada de negocio.
@RestController
@RequestMapping("/api")
public class ExpUcoControlador {

	// GET /api/usuarios -> lista de usuarios para elegir quien se inscribe.
	@GetMapping("/usuarios")
	public List<UsuarioDTO> consultarUsuarios() {
		final ConsultarUsuariosFachada fachada = new ConsultarUsuariosFachadaImpl();
		return fachada.ejecutar();
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
