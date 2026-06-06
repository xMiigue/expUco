package co.edu.uco.expuco.entidad;

// Entidad: representa la fila de la tabla inscripcion. Inmutable, se arma con Builder.
public final class InscripcionEntidad {

	private Long id;
	private Long usuarioId;
	private Long eventoId;

	private InscripcionEntidad(final Builder builder) {
		super();
		this.id = builder.id;
		this.usuarioId = builder.usuarioId;
		this.eventoId = builder.eventoId;
	}

	public Long getId() {
		return id;
	}

	public Long getUsuarioId() {
		return usuarioId;
	}

	public Long getEventoId() {
		return eventoId;
	}

	public static final class Builder {

		private Long id;
		private Long usuarioId;
		private Long eventoId;

		public Builder id(final Long id) {
			this.id = id;
			return this;
		}

		public Builder usuarioId(final Long usuarioId) {
			this.usuarioId = usuarioId;
			return this;
		}

		public Builder eventoId(final Long eventoId) {
			this.eventoId = eventoId;
			return this;
		}

		public InscripcionEntidad build() {
			return new InscripcionEntidad(this);
		}
	}
}
