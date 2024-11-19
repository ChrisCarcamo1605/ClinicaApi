alter table consultas add column idusuario bigint not null;
alter table consultas add foreign key (idusuario) references usuarios(id);