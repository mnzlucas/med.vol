package med.voll.api.validator;

import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.scheduling.SchedulingMedicalAppointmentControllerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidatorActiveMedic implements ValidatorSchedulingAppointment {

    @Autowired
    MedicoRepository repository;

    @Override
    public void validate(SchedulingMedicalAppointmentControllerDTO data) {
        // medico é opcional no agendamento
        if (data.idMedico() == null) {
            return;
        }

        var isActive = repository.findAtivoById(data.idMedico());

        if (!isActive) {
            throw new ValidatorException("Médico não está ativo no sistema.");
        }
    }
}
