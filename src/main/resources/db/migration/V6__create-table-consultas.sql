create table consultas(
    id bigint primary key  auto_increment,
    medico bigint,
    paciente bigint,
    fecha datetime,
    especialidad varchar(45),
    constraint pacienteFG foreign key(paciente) references pacientes(id),
    constraint  medicoFG foreign key (medico) references medicos(id)
)