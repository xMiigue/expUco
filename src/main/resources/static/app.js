// Logica del front en JavaScript puro (sin frameworks).

// Usuario que esta identificado en este momento (null si nadie ha entrado).
let usuarioActual = null;

// --- Referencias a la pantalla de identificacion ---
const pantallaIdentificacion = document.getElementById("pantallaIdentificacion");
const inputDocumento = document.getElementById("inputDocumento");
const btnIdentificar = document.getElementById("btnIdentificar");
const msgIdentificacion = document.getElementById("msgIdentificacion");
const formRegistro = document.getElementById("formRegistro");
const inputNombre = document.getElementById("inputNombre");
const inputDocumentoRegistro = document.getElementById("inputDocumentoRegistro");
const btnRegistrar = document.getElementById("btnRegistrar");
const msgRegistro = document.getElementById("msgRegistro");

// --- Referencias a la pantalla de la app ---
const pantallaApp = document.getElementById("pantallaApp");
const saludoUsuario = document.getElementById("saludoUsuario");
const btnSalir = document.getElementById("btnSalir");
const banner = document.getElementById("banner");
const listaEventos = document.getElementById("listaEventos");
const misEventos = document.getElementById("misEventos");

btnIdentificar.onclick = identificar;
btnRegistrar.onclick = registrar;
btnSalir.onclick = salir;

// Valida que el documento sean solo numeros, entre 8 y 10 digitos.
function documentoValido(doc) {
    return /^[0-9]{8,10}$/.test(doc);
}

// Paso 1: el usuario escribe su documento y pulsa Ingresar.
function identificar() {
    const doc = inputDocumento.value.trim();
    formRegistro.classList.add("oculto");

    if (!documentoValido(doc)) {
        mostrarMensaje(msgIdentificacion, "El documento debe tener solo numeros, entre 8 y 10 digitos.", false);
        return;
    }

    fetch("/api/usuarios/documento/" + doc)
        .then(respuesta => {
            if (respuesta.ok) {
                // El documento existe: entramos.
                return respuesta.json().then(entrar);
            }
            if (respuesta.status === 404) {
                // No existe: ofrecemos registro con el documento ya escrito.
                mostrarMensaje(msgIdentificacion, "Ese documento no existe. Puedes registrarte abajo.", false);
                inputDocumentoRegistro.value = doc;
                inputNombre.value = "";
                msgRegistro.classList.add("oculto");
                formRegistro.classList.remove("oculto");
                return null;
            }
            mostrarMensaje(msgIdentificacion, "Ocurrio un error al consultar. Intenta de nuevo.", false);
            return null;
        });
}

// Paso 1-b: registrar un usuario nuevo (nombre + documento).
function registrar() {
    const nombre = inputNombre.value.trim();
    const doc = inputDocumentoRegistro.value.trim();

    if (nombre === "") {
        mostrarMensaje(msgRegistro, "Escribe tu nombre.", false);
        return;
    }
    if (!documentoValido(doc)) {
        mostrarMensaje(msgRegistro, "El documento debe tener solo numeros, entre 8 y 10 digitos.", false);
        return;
    }

    fetch("/api/usuarios", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ nombre: nombre, documento: Number(doc) })
    })
        .then(respuesta => respuesta.json())
        .then(resultado => {
            if (resultado.exitoso) {
                entrar(resultado.usuario);
            } else {
                // Por ejemplo: "Ese documento ya esta registrado."
                mostrarMensaje(msgRegistro, resultado.motivo, false);
            }
        });
}

// Entra a la app con el usuario identificado y carga sus datos.
function entrar(usuario) {
    usuarioActual = usuario;
    saludoUsuario.textContent = "Hola, " + usuario.nombre;
    pantallaIdentificacion.classList.add("oculto");
    pantallaApp.classList.remove("oculto");
    banner.classList.add("oculto");
    cargarEventos();
    cargarMisEventos();
}

// Cierra sesion y vuelve a la pantalla de identificacion.
function salir() {
    usuarioActual = null;
    pantallaApp.classList.add("oculto");
    pantallaIdentificacion.classList.remove("oculto");
    formRegistro.classList.add("oculto");
    msgIdentificacion.classList.add("oculto");
    inputDocumento.value = "";
}

// Trae los eventos y dibuja una tarjeta por cada uno (para inscribirse).
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
    boton.onclick = () => inscribir(evento.id);
    tarjeta.appendChild(boton);

    return tarjeta;
}

// Envia la inscripcion al backend y muestra el resultado.
function inscribir(eventoId) {
    fetch("/api/inscripciones", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ usuarioId: usuarioActual.id, eventoId: eventoId })
    })
        .then(respuesta => respuesta.json())
        .then(resultado => {
            mostrarBanner(resultado);
            cargarEventos();     // refrescar cupos
            cargarMisEventos();  // refrescar mi panel de inscritos
        });
}

// Trae los eventos a los que el usuario actual ya esta inscrito.
function cargarMisEventos() {
    fetch("/api/usuarios/" + usuarioActual.id + "/eventos")
        .then(respuesta => respuesta.json())
        .then(eventos => {
            misEventos.innerHTML = "";
            if (eventos.length === 0) {
                misEventos.innerHTML = "<p>Aun no estas inscrito en ningun evento.</p>";
                return;
            }
            eventos.forEach(evento => {
                const item = document.createElement("div");
                item.className = "tarjeta";
                item.innerHTML = `
                    <h3>${evento.nombre}</h3>
                    <p><strong>Lugar:</strong> ${evento.lugar}</p>
                    <p><strong>Inicio:</strong> ${formatearFecha(evento.fechaInicio)}</p>
                `;
                misEventos.appendChild(item);
            });
        });
}

// Muestra el banner verde (exito) o rojo (fallo) con el motivo de la inscripcion.
function mostrarBanner(resultado) {
    banner.textContent = resultado.motivo;
    banner.classList.remove("oculto", "exito", "error");
    banner.classList.add(resultado.exitoso ? "exito" : "error");
}

// Muestra un mensaje pequeño (siempre en rojo, son avisos de validacion).
function mostrarMensaje(elemento, texto, exito) {
    elemento.textContent = texto;
    elemento.classList.remove("oculto", "exito", "error");
    elemento.classList.add(exito ? "exito" : "error");
}

// Convierte la fecha ISO que manda el backend a un texto legible.
function formatearFecha(fechaIso) {
    return new Date(fechaIso).toLocaleString("es-CO");
}
