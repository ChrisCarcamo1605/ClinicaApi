let currentIndex = 0;
const images = document.querySelectorAll('.galeria-imagenes img');
const totalImages = images.length;

const prevButton = document.querySelector('.prev');
const nextButton = document.querySelector('.next');
//
// // Función para obtener el token JWT desde la cookie (o almacenamiento local)
// function getJWTToken() {
//     return document.cookie.replace(/(?:(?:^|.*;\s*)JWT\s*\=\s*([^;]*).*$)|^.*$/, "$1");
// }
//
// function verifyToken() {
//     const token = getJWTToken();
//
//     // Verificar si el token existe
//     if (token) {
//         fetch('/home', {
//             method: 'GET',
//             headers: {
//                 'Authorization': 'Bearer ' + token  // Añadir el token en el encabezado Authorization
//             }
//         })
//             .then(response => {
//                 if (response.status === 200) {
//                     console.log("Usuario autenticado");
//                     // Llamar a la función para inicializar la galería solo si el token es válido
//                     initializeGallery();
//                 } else {
//                     console.log("Token no válido o sesión expirada");
//                     // Redirigir a la página de login si el token es inválido
//                     window.location.href = '/auth';
//                 }
//             })
//             .catch(error => {
//                 console.error("Error al verificar la autenticación", error);
//                 // Redirigir al login en caso de error
//                 window.location.href = '/auth';
//             });
//     } else {
//         console.log("No se encontró token");
//         // Redirigir al login si no hay token
//         window.location.href = '/auth';
//     }
// }

// Función para inicializar la galería, que solo se ejecutará si el token es válido
function initializeGallery() {
    function showImage(index) {
        // Ajustamos el desplazamiento para que las imágenes se muevan de forma cíclica
        const offset = -index * (images[0].clientWidth + 20); // 20px es el margen entre imágenes
        document.querySelector('.galeria-imagenes').style.transform = `translateX(${offset}px)`;
    }

    // Mover a la siguiente imagen
    nextButton.addEventListener('click', () => {
        currentIndex++;
        if (currentIndex >= totalImages) {
            currentIndex = 0; // Si llega al final, vuelve a la primera imagen
        }
        showImage(currentIndex);
    });

    // Mover a la imagen anterior
    prevButton.addEventListener('click', () => {
        currentIndex--;
        if (currentIndex < 0) {
            currentIndex = totalImages - 1; // Si está en la primera, va a la última
        }
        showImage(currentIndex);
    });
}

// Llamar a la función para verificar el token cuando se carga la página
document.addEventListener('DOMContentLoaded', (event) => {
    verifyToken();
});
