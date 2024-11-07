create table usuarios(
    id bigint primary key auto_increment,
    username varchar(50) not null unique,
    nombre varchar(20) not null unique,
    password varchar(200) not null,
    correo       varchar(100) not null unique,
    dui   varchar(9)  not null unique,
    calle       varchar(100) not null,
    colonia      varchar(20),
    ciudad      varchar(100) not null,
    telefono    varchar(20)  not null,
    activo      tinyint default 1
);
