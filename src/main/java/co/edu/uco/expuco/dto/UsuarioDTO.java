package co.edu.uco.expuco.dto;

// DTO: lo que viaja al front para un usuario (id y nombre). Se arma con Builder.
public final class UsuarioDTO {

	private Long id;
	private String nombre;

	private UsuarioDTO(final Builder builder) {
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

		public UsuarioDTO build() {
			return new UsuarioDTO(this);
		}
	}
}
