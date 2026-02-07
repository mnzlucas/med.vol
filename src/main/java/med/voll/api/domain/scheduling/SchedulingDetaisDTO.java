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
    public SchedulingDetaisDTO(SchedulingAppointment appointment) {
        this(appointment.getId(), appointment.getMedico().getId(), appointment.getPaciente().getId(), appointment.getDataConsulta(), appointment.getMedico().getEspecialidade());
    }
}
