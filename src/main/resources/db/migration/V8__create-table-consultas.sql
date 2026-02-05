create table consultas (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    medico_id BIGINT not null,
    paciente_id BIGINT not null,
    data_consulta timestamp with time zone not null,

    constraint fk_consultas_paciente
        foreign key (paciente_id) references pacientes(id),

    constraint fk_consultas_medico
        foreign key (medico_id) references medicos(id)
);