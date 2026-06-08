package co.edu.uco.expuco.negocio.casouso.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;

import co.edu.uco.expuco.datos.fabrica.FabricaDAO;
import co.edu.uco.expuco.dto.ResultadoInscripcionDTO;
import co.edu.uco.expuco.entidad.EventoEntidad;
import co.edu.uco.expuco.entidad.InscripcionEntidad;
import co.edu.uco.expuco.entidad.UsuarioEntidad;
import co.edu.uco.expuco.negocio.casouso.InscribirCasoUso;

// Implementacion del caso de uso de inscripcion: valida las reglas EN ORDEN.
// Si alguna falla, devuelve el motivo y NO inserta. Si todas pasan, crea la persona (si es nueva) e inserta.
public final class InscribirCasoUsoImpl implements InscribirCasoUso {

	// Edad minima para poder inscribirse (aplica a TODOS los roles).
	private static final int EDAD_MINIMA = 18;

	private final FabricaDAO fabricaDAO;

	public InscribirCasoUsoImpl(final FabricaDAO fabricaDAO) {
		super();
		this.fabricaDAO = fabricaDAO;
	}

	@Override
	public ResultadoInscripcionDTO ejecutar(final UsuarioEntidad persona, final Long eventoId) {

		// Buscamos el evento al que se quiere inscribir.
		final EventoEntidad evento = fabricaDAO.obtenerEventoDAO().consultarPorId(eventoId);
		if (evento == null) {
			return fallo("El evento no existe.");
		}

		final LocalDateTime ahora = LocalDateTime.now();

		// REGLA 1 - VIGENTE: ahora debe estar dentro del periodo de inscripcion.
		if (ahora.isBefore(evento.getInscripcionInicio()) || ahora.isAfter(evento.getInscripcionFin())) {
			return fallo("Las inscripciones para este evento no estan vigentes.");
		}

		// REGLA 2 - CUPOS: debe haber cupos disponibles (inscritos < capacidad).
		final int inscritos = fabricaDAO.obtenerInscripcionDAO().contarPorEvento(eventoId);
		if (inscritos >= evento.getCapacidad()) {
			return fallo("No hay cupos disponibles para este evento.");
		}

		// Buscamos a la persona por documento (puede que ya exista de una inscripcion anterior).
		UsuarioEntidad usuario = fabricaDAO.obtenerUsuarioDAO().consultarPorDocumento(persona.getDocumento());

		// REGLA 3 - CHOQUE DE HORARIO: solo aplica si la persona ya tiene inscripciones.
		// Se cruzan si:  nuevo.inicio < otro.fin  Y  otro.inicio < nuevo.fin.
		if (usuario != null) {
			final List<EventoEntidad> eventosDelUsuario =
					fabricaDAO.obtenerInscripcionDAO().consultarEventosPorUsuario(usuario.getId());
			for (final EventoEntidad otro : eventosDelUsuario) {
				final boolean seCruzan = evento.getFechaInicio().isBefore(otro.getFechaFin())
						&& otro.getFechaInicio().isBefore(evento.getFechaFin());
				if (seCruzan) {
					return fallo("Choque de horario con tu evento ya inscrito: " + otro.getNombre() + ".");
				}
			}
		}

		// REGLA 4 - EDAD: TODA persona debe ser mayor o igual a 18 anios, SIN IMPORTAR EL ROL.
		if (persona.getFechaNacimiento() == null) {
			return fallo("La fecha de nacimiento es obligatoria.");
			}
		 final int edad = Period.between(persona.getFechaNacimiento(), LocalDate.now()).getYears();
		if (edad < EDAD_MINIMA) {
		return fallo("Debes ser mayor o igual a 18 anios para inscribirte.");
		} 
		
		//Regla 5 si la persona ya tiene un evento pendiente
		if (usuario != null) {
		    final List<EventoEntidad> eventosDelUsuario =
		            fabricaDAO.obtenerInscripcionDAO().consultarEventosPorUsuario(usuario.getId());
		    for (final EventoEntidad otro: eventosDelUsuario) {
		        if (otro.getFechaFin() != null && otro.getFechaFin().isAfter(ahora)) {
		            return fallo("Ya tienes un evento pendiente por asistir");
		        }
		    }
		}
			
			
			
		// Si la persona no existe, la creamos ahora (ya pasaron todas las reglas).
		if (usuario == null) {
			fabricaDAO.obtenerUsuarioDAO().crear(persona);
			usuario = fabricaDAO.obtenerUsuarioDAO().consultarPorDocumento(persona.getDocumento());
		}

		// Insertamos la inscripcion.
		final InscripcionEntidad inscripcion = new InscripcionEntidad.Builder()
				.usuarioId(usuario.getId())
				.eventoId(eventoId)
				.build();
		fabricaDAO.obtenerInscripcionDAO().crear(inscripcion);

		return new ResultadoInscripcionDTO.Builder()
				.exitoso(true)
				.motivo("Inscripcion exitosa a: " + evento.getNombre()
						+ ". Se enviara una confirmacion al correo " + persona.getCorreo() + ".")
				.build();
	}

	// Atajo para construir un resultado fallido con su motivo.
	private ResultadoInscripcionDTO fallo(final String motivo) {
		return new ResultadoInscripcionDTO.Builder()
				.exitoso(false)
				.motivo(motivo)
				.build();
	}
}
