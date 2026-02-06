package med.voll.api.domain.scheduling;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.medico.Especialidade;

import java.time.LocalDateTime;

public record SchedulingMedicalAppointmentControllerDTO(
        @NotNull
        Long idMedico,
        @NotNull
        Long idPaciente,
        @NotNull
        @Future
        LocalDateTime dataHora,
        Especialidade especialidade
) {
}
