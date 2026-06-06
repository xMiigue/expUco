package co.edu.uco.expuco.entidad;

// Entidad: representa la fila de la tabla usuario. Inmutable, se arma con Builder.
public final class UsuarioEntidad {

	private Long id;
	private String nombre;

	private UsuarioEntidad(final Builder builder) {
		super();
		this.id = builder.id;
		this.nombre = builder.nombre;
	}

	public Long getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public static final class Builder {

		private Long id;
		private String nombre;

		public Builder id(final Long id) {
			this.id = id;
			return this;
		}

		public Builder nombre(final String nombre) {
			this.nombre = nombre;
			return this;
		}

		public UsuarioEntidad build() {
			return new UsuarioEntidad(this);
		}
	}
}
