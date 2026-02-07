package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.scheduling.SchedulingAppointment;
import med.voll.api.domain.scheduling.SchedulingAppointmentService;
import med.voll.api.domain.scheduling.SchedulingDetaisDTO;
import med.voll.api.domain.scheduling.SchedulingMedicalAppointmentControllerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("consultas")
public class schedulingMedicalAppointmentController {

    @Autowired
    private SchedulingAppointmentService service;

    @PostMapping
    @Transactional
    public ResponseEntity schedulingMedicalAppointment(@RequestBody @Valid SchedulingMedicalAppointmentControllerDTO data) {
        SchedulingDetaisDTO detailsDTO = service.scheduleAppointment(data);
        return ResponseEntity.ok(detailsDTO);
    }
}
