create table consultas
(
    id         bigint primary key not null auto_increment,
    idveterinario   bigint             not null,
    idmascota bigint             not null,
    fecha      datetime           not null,

    constraint fk_consultas_veterubario_id foreign key (idveterinario) references veterinarios (id),
    constraint fk_consultas_mascotas_id foreign key (idmascota) references mascotas(id)
);