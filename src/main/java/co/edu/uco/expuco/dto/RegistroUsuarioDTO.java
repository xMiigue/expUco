package co.edu.uco.expuco.dto;

// DTO de peticion del registro. Mutable a proposito (constructor vacio + getters/setters)
// para que Jackson lo arme desde el JSON {nombre, documento}.
public final class RegistroUsuarioDTO {

	private String nombre;
	private Long documento;

	public RegistroUsuarioDTO() {
		super();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(final String nombre) {
		this.nombre = nombre;
	}

	public Long getDocumento() {
		return documento;
	}

	public void setDocumento(final Long documento) {
		this.documento = documento;
	}
}
