// Logica del front en JavaScript puro (sin frameworks).

// Datos que se van capturando durante el flujo de inscripcion.
let eventoSeleccionadoId = null;
let rolSeleccionado = null;

// --- Referencias del DOM ---
const banner = document.getElementById("banner");
const listaEventos = document.getElementById("listaEventos");

const modal = document.getElementById("modal");
const modalTitulo = document.getElementById("modalTitulo");
const btnCerrarModal = document.getElementById("btnCerrarModal");

const paso1 = document.getElementById("paso1");
const paso2 = document.getElementById("paso2");
const opcionesRol = document.querySelectorAll(".opcion-rol");
const inputDocumento = document.getElementById("inputDocumento");
const msgPaso1 = document.getElementById("msgPaso1");
const btnContinuar = document.getElementById("btnContinuar");

const inputNombres = document.getElementById("inputNombres");
const inputApellidos = document.getElementById("inputApellidos");
const inputFechaNacimiento = document.getElementById("inputFechaNacimiento");
const inputCorreo = document.getElementById("inputCorreo");
const msgPaso2 = document.getElementById("msgPaso2");
const btnConfirmar = document.getElementById("btnConfirmar");

// Al abrir la pagina mostramos los eventos (sin identificarse).
cargarEventos();

btnContinuar.onclick = continuarPaso2;
btnConfirmar.onclick = confirmarInscripcion;
btnCerrarModal.onclick = cerrarModal;
modal.onclick = (e) => { if (e.target === modal) cerrarModal(); };

// Seleccion de rol (resalta la tarjeta elegida).
opcionesRol.forEach(boton => {
    boton.onclick = () => {
        rolSeleccionado = boton.dataset.rol;
        opcionesRol.forEach(b => b.classList.remove("seleccionada"));
        boton.classList.add("seleccionada");
    };
});

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

    tarjeta.innerHTML = `
        <h3>${evento.nombre}</h3>
        <p><strong>Lugar:</strong> ${evento.lugar}</p>
        <p><strong>Inicio:</strong> ${formatearFecha(evento.fechaInicio)}</p>
        <p><strong>Fin:</strong> ${formatearFecha(evento.fechaFin)}</p>
        <p><strong>Cupos:</strong> ${evento.cuposDisponibles} / ${evento.capacidad}</p>
        <p>${evento.vigente ? "Inscripciones abiertas" : "Inscripciones cerradas"}</p>
    `;

    const boton = document.createElement("button");
    boton.textContent = "Inscribirme";
    boton.onclick = () => abrirModal(evento);
    tarjeta.appendChild(boton);

    return tarjeta;
}

// Abre el modal en el PASO 1, limpio, para el evento elegido.
function abrirModal(evento) {
    eventoSeleccionadoId = evento.id;
    rolSeleccionado = null;
    modalTitulo.textContent = "Inscripcion: " + evento.nombre;

    // Reiniciar campos y mensajes.
    opcionesRol.forEach(b => b.classList.remove("seleccionada"));
    inputDocumento.value = "";
    inputNombres.value = "";
    inputApellidos.value = "";
    inputFechaNacimiento.value = "";
    inputCorreo.value = "";
    msgPaso1.classList.add("oculto");
    msgPaso2.classList.add("oculto");

    paso2.classList.add("oculto");
    paso1.classList.remove("oculto");
    modal.classList.remove("oculto");
}

function cerrarModal() {
    modal.classList.add("oculto");
}

// PASO 1 -> PASO 2: valida rol y documento.
function continuarPaso2() {
    if (rolSeleccionado === null) {
        mostrarMensaje(msgPaso1, "Selecciona un rol (Estudiante o Docente).");
        return;
    }
    const doc = inputDocumento.value.trim();
    if (!/^[0-9]{1,10}$/.test(doc)) {
        mostrarMensaje(msgPaso1, "El documento debe ser solo numeros (maximo 10 digitos).");
        return;
    }
    // Pasamos al paso 2.
    paso1.classList.add("oculto");
    paso2.classList.remove("oculto");
}

// PASO 2: valida datos y envia la inscripcion al backend.
function confirmarInscripcion() {
    const nombres = inputNombres.value.trim();
    const apellidos = inputApellidos.value.trim();
    const fechaNacimiento = inputFechaNacimiento.value; // formato yyyy-MM-dd
    const correo = inputCorreo.value.trim();

    if (nombres === "" || apellidos === "") {
        mostrarMensaje(msgPaso2, "Escribe tus nombres y apellidos.");
        return;
    }
    if (fechaNacimiento === "") {
        mostrarMensaje(msgPaso2, "Selecciona tu fecha de nacimiento.");
        return;
    }
    if (!/^\S+@\S+\.\S+$/.test(correo)) {
        mostrarMensaje(msgPaso2, "Escribe un correo electronico valido.");
        return;
    }

    const cuerpo = {
        documento: Number(inputDocumento.value.trim()),
        rol: rolSeleccionado,
        nombres: nombres,
        apellidos: apellidos,
        fechaNacimiento: fechaNacimiento,
        correo: correo,
        eventoId: eventoSeleccionadoId
    };

    fetch("/api/inscripciones", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(cuerpo)
    })
        .then(respuesta => respuesta.json())
        .then(resultado => {
            cerrarModal();
            mostrarBanner(resultado);
            cargarEventos(); // refrescar cupos
        });
}

// Banner verde (exito) o rojo (fallo) con el motivo de la inscripcion.
function mostrarBanner(resultado) {
    banner.textContent = resultado.motivo;
    banner.classList.remove("oculto", "exito", "error");
    banner.classList.add(resultado.exitoso ? "exito" : "error");
    window.scrollTo(0, 0);
}

// Mensaje pequeño de validacion (siempre en rojo).
function mostrarMensaje(elemento, texto) {
    elemento.textContent = texto;
    elemento.classList.remove("oculto");
    elemento.classList.add("error");
}

// Convierte la fecha ISO que manda el backend a un texto legible.
function formatearFecha(fechaIso) {
    return new Date(fechaIso).toLocaleString("es-CO");
}
