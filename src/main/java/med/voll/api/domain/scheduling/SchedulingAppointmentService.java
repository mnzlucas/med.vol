package med.voll.api.domain.scheduling;

import med.voll.api.domain.medico.Especialidade;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.PacienteRepository;
import med.voll.api.validator.ValidatorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SchedulingAppointmentService {

    @Autowired
    private SchedulingAppointmentRepository schedulingRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    public void scheduleAppointment(SchedulingMedicalAppointmentControllerDTO data) {
        if (!pacienteRepository.existsById(data.idPaciente())) {
            throw new ValidatorException("Id do paciente informado não existe!");
        }

        //validete id medico is not null and exists - is just in case they send it

        if (data.idMedico() != null && !medicoRepository.existsById(data.idMedico())) {
            throw new ValidatorException("Id do médico informado não existe!");
        }

        var paciente = pacienteRepository.getReferenceById(data.idPaciente());
        var medico = handleGettingMedicByIdOrChoosingItRandomly(data);
        var appointment = new SchedulingAppointment(
                null,
                medico,
                paciente,
                data.dataHora()
        );
        schedulingRepository.save(appointment);
    }

    // handle Getting Medic By Id Or Choosing It Randomly
    private Medico handleGettingMedicByIdOrChoosingItRandomly(SchedulingMedicalAppointmentControllerDTO data) {
        Long idMedico = data.idMedico();
        Especialidade especialidade = data.especialidade();
        LocalDateTime dataHora = data.dataHora();

        if (idMedico != null) {
            return medicoRepository.getReferenceById(idMedico);
        }

        if (especialidade == null) {
            throw new ValidatorException("Especialidade é obrigatória quando o médico não for escolhido!");
        }

        var medicSpecialistAvailable = medicoRepository.findRandomlyActiveByEspecialidadeAndAvailable(especialidade, dataHora);
        if (medicSpecialistAvailable == null) {
            throw new ValidatorException("Não há médicos disponíveis para a especialidade " + especialidade + " na data " + dataHora);
        }
        return medicSpecialistAvailable;
    }
}
