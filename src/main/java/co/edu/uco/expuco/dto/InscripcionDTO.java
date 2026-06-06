package co.edu.uco.expuco.dto;

// DTO de peticion del POST. Es mutable (constructor vacio + getters/setters)
// a proposito, para que Jackson lo arme automaticamente desde el JSON {usuarioId, eventoId}.
public final class InscripcionDTO {

	private Long usuarioId;
	private Long eventoId;

	public InscripcionDTO() {
		super();
	}

	public Long getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(final Long usuarioId) {
		this.usuarioId = usuarioId;
	}

	public Long getEventoId() {
		return eventoId;
	}

	public void setEventoId(final Long eventoId) {
		this.eventoId = eventoId;
	}
}
