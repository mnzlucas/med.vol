package med.voll.api.validator;

import med.voll.api.domain.scheduling.SchedulingMedicalAppointmentControllerDTO;


public interface ValidatorSchedulingAppointment {
    void validate(SchedulingMedicalAppointmentControllerDTO data);
}
