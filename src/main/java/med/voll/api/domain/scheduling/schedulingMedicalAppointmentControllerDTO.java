package med.voll.api.domain.scheduling;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record schedulingMedicalAppointmentControllerDTO(
        @NotNull
        Long idMedico,
        @NotNull
        Long idPaciente,
        @NotNull
        @Future
        LocalDateTime dataHora
) {
}
