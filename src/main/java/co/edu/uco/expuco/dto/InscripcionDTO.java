package co.edu.uco.expuco.dto;

import java.time.LocalDate;

// DTO de peticion del POST de inscripcion. Mutable a proposito (constructor vacio + getters/setters)
// para que Jackson lo arme desde el JSON. Trae los datos de la persona (paso 1 y paso 2) y el evento.
public final class InscripcionDTO {

	// Paso 1
	private Long documento;
	private String rol;

	// Paso 2
	private String nombres;
	private String apellidos;
	private LocalDate fechaNacimiento;
	private String correo;

	// Evento al que se quiere inscribir
	private Long eventoId;

	public InscripcionDTO() {
		super();
	}

	public Long getDocumento() {
		return documento;
	}

	public void setDocumento(final Long documento) {
		this.documento = documento;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(final String rol) {
		this.rol = rol;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(final String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(final String apellidos) {
		this.apellidos = apellidos;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(final LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(final String correo) {
		this.correo = correo;
	}

	public Long getEventoId() {
		return eventoId;
	}

	public void setEventoId(final Long eventoId) {
		this.eventoId = eventoId;
	}
}
