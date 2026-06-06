package co.edu.uco.expuco.entidad;

import java.time.LocalDate;

// Entidad: representa la fila de la tabla usuario. Inmutable, se arma con Builder.
public final class UsuarioEntidad {

	private Long id;
	private Long documento;
	private String rol;
	private String nombres;
	private String apellidos;
	private LocalDate fechaNacimiento;
	private String correo;

	private UsuarioEntidad(final Builder builder) {
		super();
		this.id = builder.id;
		this.documento = builder.documento;
		this.rol = builder.rol;
		this.nombres = builder.nombres;
		this.apellidos = builder.apellidos;
		this.fechaNacimiento = builder.fechaNacimiento;
		this.correo = builder.correo;
	}

	public Long getId() {
		return id;
	}

	public Long getDocumento() {
		return documento;
	}

	public String getRol() {
		return rol;
	}

	public String getNombres() {
		return nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public String getCorreo() {
		return correo;
	}

	public static final class Builder {

		private Long id;
		private Long documento;
		private String rol;
		private String nombres;
		private String apellidos;
		private LocalDate fechaNacimiento;
		private String correo;

		public Builder id(final Long id) {
			this.id = id;
			return this;
		}

		public Builder documento(final Long documento) {
			this.documento = documento;
			return this;
		}

		public Builder rol(final String rol) {
			this.rol = rol;
			return this;
		}

		public Builder nombres(final String nombres) {
			this.nombres = nombres;
			return this;
		}

		public Builder apellidos(final String apellidos) {
			this.apellidos = apellidos;
			return this;
		}

		public Builder fechaNacimiento(final LocalDate fechaNacimiento) {
			this.fechaNacimiento = fechaNacimiento;
			return this;
		}

		public Builder correo(final String correo) {
			this.correo = correo;
			return this;
		}

		public UsuarioEntidad build() {
			return new UsuarioEntidad(this);
		}
	}
}
