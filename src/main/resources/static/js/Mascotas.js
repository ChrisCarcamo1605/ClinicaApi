function getCookie(name) {
    const value = `; ${document.cookie}`;
    const parts = value.split(`; ${name}=`);
    if (parts.length === 2) return parts.pop().split(';').shift();
    return null;
}

document.addEventListener("DOMContentLoaded", function () {
    // Verificar autenticación al cargar la página
    fetch('/mascotas', {
        method: 'GET',
        credentials: 'include' // Incluir cookies automáticamente
    })
        .then(response => {
            if (response.ok) {
                console.log("Usuario autenticado");
                // Obtener el token desde las cookies y mostrarlo en el log
                const token = getCookie("JWTtoken");
                if (token) {
                    console.log("Token JWT:", token);
                } else {
                    console.log("No se encontró el token en las cookies.");
                }


            } else {
                console.log("Token no válido o sesión expirada");
                window.location.href = '/auth'; // Redirige al login si el token no es válido
            }
        })
        .catch(error => {
            console.error("Error al verificar la autenticación:", error);
            window.location.href = '/auth';
        });
});