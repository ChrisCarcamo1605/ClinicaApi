create table pacientes(
    id bigint primary key auto_increment,
    nombre varchar(50) not null,
    apellido varchar(50) not null,
    telefono varchar(8) not null,
    correo varchar(40) not null,
    dui varchar(9) not null,
    calle varchar(30),
    ciudad varchar(30),
    numero varchar(30),
    activo bool default 1

)