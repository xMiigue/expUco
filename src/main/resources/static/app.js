// Logica del front en JavaScript puro (sin frameworks).

const selectUsuario = document.getElementById("selectUsuario");
const listaEventos = document.getElementById("listaEventos");
const banner = document.getElementById("banner");

// Al cargar la pagina, traemos usuarios y eventos.
cargarUsuarios();
cargarEventos();

// Trae los usuarios y los pone en el dropdown.
function cargarUsuarios() {
    fetch("/api/usuarios")
        .then(respuesta => respuesta.json())
        .then(usuarios => {
            selectUsuario.innerHTML = "";
            usuarios.forEach(usuario => {
                const opcion = document.createElement("option");
                opcion.value = usuario.id;
                opcion.textContent = usuario.nombre;
                selectUsuario.appendChild(opcion);
            });
        });
}

// Trae los eventos y dibuja una tarjeta por cada uno.
function cargarEventos() {
    fetch("/api/eventos")
        .then(respuesta => respuesta.json())
        .then(eventos => {
            listaEventos.innerHTML = "";
            eventos.forEach(evento => listaEventos.appendChild(crearTarjeta(evento)));
        });
}

// Construye la tarjeta HTML de un evento.
function crearTarjeta(evento) {
    const tarjeta = document.createElement("div");
    tarjeta.className = "tarjeta";

    const sinCupos = evento.cuposDisponibles <= 0;

    tarjeta.innerHTML = `
        <h3>${evento.nombre}</h3>
        <p><strong>Lugar:</strong> ${evento.lugar}</p>
        <p><strong>Inicio:</strong> ${formatearFecha(evento.fechaInicio)}</p>
        <p><strong>Fin:</strong> ${formatearFecha(evento.fechaFin)}</p>
        <p><strong>Cupos:</strong> ${evento.cuposDisponibles} / ${evento.capacidad}</p>
        <p>${evento.vigente ? "Inscripciones abiertas" : "Inscripciones cerradas"}</p>
    `;

    // Boton para inscribirse al evento.
    const boton = document.createElement("button");
    boton.textContent = "Inscribirme";
    boton.onclick = () => inscribir(evento.id);
    tarjeta.appendChild(boton);

    return tarjeta;
}

// Envia la inscripcion al backend y muestra el resultado.
function inscribir(eventoId) {
    const usuarioId = selectUsuario.value;

    fetch("/api/inscripciones", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ usuarioId: usuarioId, eventoId: eventoId })
    })
        .then(respuesta => respuesta.json())
        .then(resultado => {
            mostrarBanner(resultado);
            cargarEventos(); // recargamos para ver los cupos actualizados
        });
}

// Muestra el banner verde (exito) o rojo (fallo) con el motivo.
function mostrarBanner(resultado) {
    banner.textContent = resultado.motivo;
    banner.classList.remove("oculto", "exito", "error");
    banner.classList.add(resultado.exitoso ? "exito" : "error");
}

// Convierte la fecha ISO que manda el backend a un texto legible.
function formatearFecha(fechaIso) {
    return new Date(fechaIso).toLocaleString("es-CO");
}
