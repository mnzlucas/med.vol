package med.voll.api.validator;

import med.voll.api.domain.scheduling.SchedulingMedicalAppointmentControllerDTO;
import org.springframework.stereotype.Component;

@Component
public class ValidatorScheduleAtLeastThirtyMinutesInAdvance implements ValidatorSchedulingAppointment {

    @Override
    public void validate(SchedulingMedicalAppointmentControllerDTO data) {
        var dataHora = data.dataHora();
        var now = java.time.LocalDateTime.now();

        if (dataHora.isBefore(now.plusMinutes(30))) {
            throw new ValidatorException("Agendamento deve ser feito com pelo menos 30 minutos de antecedência.");
        }
    }
}
