package co.edu.uco.expuco.dto;

// DTO de respuesta del registro: dice si pudo (exitoso), el motivo y, si fue exitoso, el usuario creado.
public final class ResultadoRegistroDTO {

	private boolean exitoso;
	private String motivo;
	private UsuarioDTO usuario;

	private ResultadoRegistroDTO(final Builder builder) {
		super();
		this.exitoso = builder.exitoso;
		this.motivo = builder.motivo;
		this.usuario = builder.usuario;
	}

	public boolean isExitoso() {
		return exitoso;
	}

	public String getMotivo() {
		return motivo;
	}

	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public static final class Builder {

		private boolean exitoso;
		private String motivo;
		private UsuarioDTO usuario;

		public Builder exitoso(final boolean exitoso) {
			this.exitoso = exitoso;
			return this;
		}

		public Builder motivo(final String motivo) {
			this.motivo = motivo;
			return this;
		}

		public Builder usuario(final UsuarioDTO usuario) {
			this.usuario = usuario;
			return this;
		}

		public ResultadoRegistroDTO build() {
			return new ResultadoRegistroDTO(this);
		}
	}
}
