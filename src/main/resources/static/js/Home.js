// Función para obtener una cookie por nombre
function getCookie(name) {
    const value = `; ${document.cookie}`;
    const parts = value.split(`; ${name}=`);
    if (parts.length === 2) return parts.pop().split(';').shift();
    return null;
}

document.addEventListener("DOMContentLoaded", function () {
    // Verificar autenticación al cargar la página
    fetch('/home', {
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

                initializeGallery(); // Inicializa la galería si está autenticado
            } else {
                console.log("Token no válido o sesión expirada");
                window.location.href = '/auth'; // Redirige al login si el token no es válido
            }
        })
        .catch(error => {
            console.error("Error al verificar la autenticación:", error);
            window.location.href = '/auth'; // Redirige al login en caso de error
        });
});



// Inicializar galería y navegación de imágenes
function initializeGallery() {
    console.log("Inicializando galería");

    let currentIndex = 0;
    const images = document.querySelectorAll('.galeria-imagenes img');
    const totalImages = images.length;

    // Referencias a botones
    const prevButton = document.querySelector('.prev');
    const nextButton = document.querySelector('.next');

    // Asegurarse de que los botones y las imágenes existan antes de configurar eventos
    if (images.length === 0 || !prevButton || !nextButton) {
        console.error("No se encontraron imágenes o botones en la galería");
        return;
    }

    // Mostrar imagen en la posición actual
    function showImage(index) {
        const offset = -index * (images[0].clientWidth + 20); // 20px es el margen entre imágenes
        document.querySelector('.galeria-imagenes').style.transform = `translateX(${offset}px)`;
    }

    // Configurar eventos de los botones
    nextButton.addEventListener('click', () => {
        currentIndex = (currentIndex + 1) % totalImages; // Ir a la siguiente imagen, volver al inicio si es necesario
        showImage(currentIndex);
    });

    prevButton.addEventListener('click', () => {
        currentIndex = (currentIndex - 1 + totalImages) % totalImages; // Ir a la imagen anterior, volver al final si es necesario
        showImage(currentIndex);
    });

    // Mostrar la primera imagen al inicializar
    showImage(currentIndex);
}

