package med.voll.api.validator;

import med.voll.api.domain.scheduling.SchedulingDetaisDTO;
import med.voll.api.domain.scheduling.SchedulingMedicalAppointmentControllerDTO;
import org.springframework.stereotype.Component;

@Component
public class ValidatorClinicOpeningHours implements ValidatorSchedulingAppointment {

    @Override
    public void validate(SchedulingMedicalAppointmentControllerDTO data) {
        var dataHora = data.dataHora();
        var diaSemana = dataHora.getDayOfWeek();
        var hora = dataHora.getHour();

        if (diaSemana == java.time.DayOfWeek.SATURDAY || diaSemana == java.time.DayOfWeek.SUNDAY) {
            throw new ValidatorException("Agendamento fora do horário de funcionamento da clínica.");
        }

        if (hora < 7 || hora > 18) {
            throw new ValidatorException("Agendamento fora do horário de funcionamento da clínica.");
        }
    }
}
