package co.edu.uco.expuco.entidad;

// Entidad: representa la fila de la tabla usuario. Inmutable, se arma con Builder.
public final class UsuarioEntidad {

	private Long id;
	private String nombre;
	private Long documento;

	private UsuarioEntidad(final Builder builder) {
		super();
		this.id = builder.id;
		this.nombre = builder.nombre;
		this.documento = builder.documento;
	}

	public Long getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public Long getDocumento() {
		return documento;
	}

	public static final class Builder {

		private Long id;
		private String nombre;
		private Long documento;

		public Builder id(final Long id) {
			this.id = id;
			return this;
		}

		public Builder nombre(final String nombre) {
			this.nombre = nombre;
			return this;
		}

		public Builder documento(final Long documento) {
			this.documento = documento;
			return this;
		}

		public UsuarioEntidad build() {
			return new UsuarioEntidad(this);
		}
	}
}
