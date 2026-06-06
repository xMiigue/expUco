package co.edu.uco.expuco.dto;

// DTO: lo que viaja al front para un usuario (id, nombre y documento). Se arma con Builder.
public final class UsuarioDTO {

	private Long id;
	private String nombre;
	private Long documento;

	private UsuarioDTO(final Builder builder) {
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

		public UsuarioDTO build() {
			return new UsuarioDTO(this);
		}
	}
}
