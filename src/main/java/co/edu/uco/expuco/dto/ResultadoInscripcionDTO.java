package co.edu.uco.expuco.dto;

// DTO de respuesta de la inscripcion: dice si pudo (exitoso) y el motivo.
public final class ResultadoInscripcionDTO {

	private boolean exitoso;
	private String motivo;

	private ResultadoInscripcionDTO(final Builder builder) {
		super();
		this.exitoso = builder.exitoso;
		this.motivo = builder.motivo;
	}

	public boolean isExitoso() {
		return exitoso;
	}

	public String getMotivo() {
		return motivo;
	}

	public static final class Builder {

		private boolean exitoso;
		private String motivo;

		public Builder exitoso(final boolean exitoso) {
			this.exitoso = exitoso;
			return this;
		}

		public Builder motivo(final String motivo) {
			this.motivo = motivo;
			return this;
		}

		public ResultadoInscripcionDTO build() {
			return new ResultadoInscripcionDTO(this);
		}
	}
}
