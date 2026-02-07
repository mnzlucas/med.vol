package med.voll.api.validator;

import med.voll.api.domain.scheduling.SchedulingAppointmentRepository;
import med.voll.api.domain.scheduling.SchedulingMedicalAppointmentControllerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidatorMedicIsFree implements ValidatorSchedulingAppointment {

    @Autowired
    SchedulingAppointmentRepository repository;

    @Override
    public void validate(SchedulingMedicalAppointmentControllerDTO data) {
        if (data.idMedico() == null || data.dataHora() == null) {
            return;
        }

        var medicoIsFree = repository.existsByMedicoIdAndDataConsulta(data.idMedico(), data.dataHora());

        if (!medicoIsFree) {
            throw new ValidatorException("Médico já possui outro agendamento nesse horário.");
        }
    }
}
