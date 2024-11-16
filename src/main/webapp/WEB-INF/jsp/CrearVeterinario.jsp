<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Crear Veterinario</title>
</head>
<body>
<h1>Crear Veterinario</h1>
<form action="/veterinarios/guardar" method="post">
  <label for="nombre">Nombre:</label>
  <input type="text" id="nombre" name="nombre" required><br>

  <label for="especialidad">Especialidad:</label>
  <input type="text" id="especialidad" name="especialidad" required><br>

  <label for="telefono">Teléfono:</label>
  <input type="text" id="telefono" name="telefono" required><br>

  <label for="correo">Correo:</label>
  <input type="email" id="correo" name="correo" required><br>

  <label for="direccion">Dirección:</label>
  <input type="text" id="direccion" name="direccion" required><br>

  <button type="submit">Guardar</button>
</form>
</body>
</html>