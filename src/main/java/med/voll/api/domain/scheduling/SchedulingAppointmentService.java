package med.voll.api.domain.scheduling;

import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SchedulingAppointmentService {

    @Autowired
    private SchedulingAppointmentRepository schedulingRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    public void scheduleAppointment(schedulingMedicalAppointmentControllerDTO data) {


        var medico = medicoRepository.getReferenceById(data.idMedico());
        var paciente = pacienteRepository.getReferenceById(data.idPaciente());
        var appointment = new SchedulingAppointment(
                null,
                medico,
                paciente,
                data.dataHora()
        );
        schedulingRepository.save(appointment);
    }
}
