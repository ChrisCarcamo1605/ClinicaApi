let currentIndex = 0;
const images = document.querySelectorAll('.galeria-imagenes img');
const totalImages = images.length;

const prevButton = document.querySelector('.prev');
const nextButton = document.querySelector('.next');

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
