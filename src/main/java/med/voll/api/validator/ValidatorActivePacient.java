package med.voll.api.validator;

import med.voll.api.domain.paciente.PacienteRepository;
import med.voll.api.domain.scheduling.SchedulingMedicalAppointmentControllerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidatorActivePacient implements ValidatorSchedulingAppointment {

    @Autowired
    PacienteRepository repository;

    @Override
    public void validate(SchedulingMedicalAppointmentControllerDTO data) {

        var isActive = repository.findAtivoById(data.idPaciente());

        if (!isActive) {
            throw new ValidatorException("Paciente não está ativo no sistema.");
        }
    }
}
