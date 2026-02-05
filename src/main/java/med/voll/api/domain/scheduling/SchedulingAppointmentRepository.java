package med.voll.api.domain.scheduling;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SchedulingAppointmentRepository extends JpaRepository<SchedulingAppointment, Long> {
}
