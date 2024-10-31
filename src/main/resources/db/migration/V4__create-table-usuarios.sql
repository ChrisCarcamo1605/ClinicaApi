create table usuarios(
    id bigint primary key auto_increment,
    username varchar(20) not null unique,
    password varchar(200) not null
)