package med.voll.api.domain.scheduling;

import med.voll.api.domain.medico.Especialidade;

import java.time.LocalDateTime;

public record SchedulingDetaisDTO(
        Long id,
        Long idMedico,
        Long idPaciente,
        LocalDateTime dataHora,
        Especialidade especialidade
) {
}
