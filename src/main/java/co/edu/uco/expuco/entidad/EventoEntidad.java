package co.edu.uco.expuco.entidad;

import java.time.LocalDateTime;

// Entidad: representa la fila de la tabla evento. Inmutable, se arma con Builder.
public final class EventoEntidad {

	private Long id;
	private String nombre;
	private String lugar;
	private LocalDateTime fechaInicio;
	private LocalDateTime fechaFin;
	private LocalDateTime inscripcionInicio;
	private LocalDateTime inscripcionFin;
	private int capacidad;

	private EventoEntidad(final Builder builder) {
		super();
		this.id = builder.id;
		this.nombre = builder.nombre;
		this.lugar = builder.lugar;
		this.fechaInicio = builder.fechaInicio;
		this.fechaFin = builder.fechaFin;
		this.inscripcionInicio = builder.inscripcionInicio;
		this.inscripcionFin = builder.inscripcionFin;
		this.capacidad = builder.capacidad;
	}

	public Long getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getLugar() {
		return lugar;
	}

	public LocalDateTime getFechaInicio() {
		return fechaInicio;
	}

	public LocalDateTime getFechaFin() {
		return fechaFin;
	}

	public LocalDateTime getInscripcionInicio() {
		return inscripcionInicio;
	}

	public LocalDateTime getInscripcionFin() {
		return inscripcionFin;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public static final class Builder {

		private Long id;
		private String nombre;
		private String lugar;
		private LocalDateTime fechaInicio;
		private LocalDateTime fechaFin;
		private LocalDateTime inscripcionInicio;
		private LocalDateTime inscripcionFin;
		private int capacidad;

		public Builder id(final Long id) {
			this.id = id;
			return this;
		}

		public Builder nombre(final String nombre) {
			this.nombre = nombre;
			return this;
		}

		public Builder lugar(final String lugar) {
			this.lugar = lugar;
			return this;
		}

		public Builder fechaInicio(final LocalDateTime fechaInicio) {
			this.fechaInicio = fechaInicio;
			return this;
		}

		public Builder fechaFin(final LocalDateTime fechaFin) {
			this.fechaFin = fechaFin;
			return this;
		}

		public Builder inscripcionInicio(final LocalDateTime inscripcionInicio) {
			this.inscripcionInicio = inscripcionInicio;
			return this;
		}

		public Builder inscripcionFin(final LocalDateTime inscripcionFin) {
			this.inscripcionFin = inscripcionFin;
			return this;
		}

		public Builder capacidad(final int capacidad) {
			this.capacidad = capacidad;
			return this;
		}

		public EventoEntidad build() {
			return new EventoEntidad(this);
		}
	}
}
