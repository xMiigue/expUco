package co.edu.uco.expuco.dto;

import java.time.LocalDateTime;

// DTO: lo que viaja al front para un evento. Incluye cupos disponibles y si esta vigente.
public final class EventoDTO {

	private Long id;
	private String nombre;
	private String lugar;
	private LocalDateTime fechaInicio;
	private LocalDateTime fechaFin;
	private int capacidad;
	private int cuposDisponibles;
	private boolean vigente;

	private EventoDTO(final Builder builder) {
		super();
		this.id = builder.id;
		this.nombre = builder.nombre;
		this.lugar = builder.lugar;
		this.fechaInicio = builder.fechaInicio;
		this.fechaFin = builder.fechaFin;
		this.capacidad = builder.capacidad;
		this.cuposDisponibles = builder.cuposDisponibles;
		this.vigente = builder.vigente;
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

	public int getCapacidad() {
		return capacidad;
	}

	public int getCuposDisponibles() {
		return cuposDisponibles;
	}

	public boolean isVigente() {
		return vigente;
	}

	public static final class Builder {

		private Long id;
		private String nombre;
		private String lugar;
		private LocalDateTime fechaInicio;
		private LocalDateTime fechaFin;
		private int capacidad;
		private int cuposDisponibles;
		private boolean vigente;

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

		public Builder capacidad(final int capacidad) {
			this.capacidad = capacidad;
			return this;
		}

		public Builder cuposDisponibles(final int cuposDisponibles) {
			this.cuposDisponibles = cuposDisponibles;
			return this;
		}

		public Builder vigente(final boolean vigente) {
			this.vigente = vigente;
			return this;
		}

		public EventoDTO build() {
			return new EventoDTO(this);
		}
	}
}
