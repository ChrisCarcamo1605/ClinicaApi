use mi_clinica;
create table veterinarios(
    id bigint  not null auto_increment primary key ,
    nombre varchar(250) not null unique,
    correo varchar(250) not null unique,
    especialidad varchar(50) not null,
    calle varchar(20),
    ciudad varchar(50) not null,
    colonia varchar(10) not null

)