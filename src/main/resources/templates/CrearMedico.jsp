<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Veterinarios</title>
</head>
<body>
<h1>Lista de Veterinarios</h1>
<table id="tabla-veterinarios">
  <thead>
  <tr>
    <th>ID</th>
    <th>Nombre</th>
    <th>Especialidad</th>
    <th>Teléfono</th>
    <th>Correo</th>
    <th>Dirección</th>
    <th>Activo</th>
  </tr>
  </thead>
  <tbody>
  <!-- Datos serán cargados vía AJAX -->
  </tbody>
</table>

<script>
  const token = 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJiaWVuZ2VpQGdtYWlsLmNvbSIsImlzcyI6Im1pX2NsaW5pY2EiLCJleHAiOjE3MzE3MDY3OTEsIklEOiI6MX0.S-5GDDqPdz7xGEraIkYiy1bo7OvNA1drQUR7kxwhFc4'; // Esto debería obtenerse después de iniciar sesión

  fetch('/veterinarios', {
    headers: {
      'Authorization': `Bearer ${token}`
    }
  })
          .then(response => response.json())
          .then(data => {
            const tbody = document.getElementById('tabla-veterinarios').querySelector('tbody');
            data.forEach(veterinario => {
              const row = `<tr>
                    <td>${veterinario.id}</td>
                    <td>${veterinario.nombre}</td>
                    <td>${veterinario.especialidad}</td>
                    <td>${veterinario.telefono}</td>
                    <td>${veterinario.correo}</td>
                    <td>${veterinario.direccion.calle}, ${veterinario.direccion.colonia}, ${veterinario.direccion.ciudad}</td>
                    <td>${veterinario.activo ? 'Sí' : 'No'}</td>
                </tr>`;
              tbody.innerHTML += row;
            });
          })
          .catch(error => console.error('Error:', error));
</script>
</body>
</html>

