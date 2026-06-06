package co.edu.uco.expuco.transversal.excepcion;

// Excepcion propia de la aplicacion (capa transversal), al estilo de uco-parking.
// Guarda un mensaje para el usuario, un mensaje tecnico y la causa raiz.
public class ExpUcoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private final Throwable excepcionRaiz;
	private final String mensajeUsuario;
	private final String mensajeTecnico;

	protected ExpUcoException(final Throwable excepcionRaiz, final String mensajeUsuario, final String mensajeTecnico) {
		super(mensajeTecnico);
		this.excepcionRaiz = excepcionRaiz != null ? excepcionRaiz : new Exception();
		this.mensajeUsuario = mensajeUsuario != null ? mensajeUsuario.trim() : "";
		this.mensajeTecnico = mensajeTecnico != null ? mensajeTecnico.trim() : "";
	}

	// Fabrica: solo mensaje para el usuario.
	public static ExpUcoException crear(final String mensajeUsuario) {
		return new ExpUcoException(new Exception(), mensajeUsuario, mensajeUsuario);
	}

	// Fabrica: mensaje de usuario + mensaje tecnico.
	public static ExpUcoException crear(final String mensajeUsuario, final String mensajeTecnico) {
		return new ExpUcoException(new Exception(), mensajeUsuario, mensajeTecnico);
	}

	// Fabrica: con la causa raiz (por ejemplo una SQLException).
	public static ExpUcoException crear(final Throwable excepcionRaiz, final String mensajeUsuario,
			final String mensajeTecnico) {
		return new ExpUcoException(excepcionRaiz, mensajeUsuario, mensajeTecnico);
	}

	public Throwable getExcepcionRaiz() {
		return excepcionRaiz;
	}

	public String getMensajeUsuario() {
		return mensajeUsuario;
	}

	public String getMensajeTecnico() {
		return mensajeTecnico;
	}
}
