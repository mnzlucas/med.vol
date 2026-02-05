package med.voll.api.domain.scheduling;

import java.time.LocalDateTime;

public record schedulingDetaisDTO(
        Long id,
        Long idMedico,
        Long idPaciente,
        LocalDateTime dataHora
) {
}
