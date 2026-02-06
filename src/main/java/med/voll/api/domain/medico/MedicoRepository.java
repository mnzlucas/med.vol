package med.voll.api.domain.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Page<Medico> findAllByAtivoTrue(Pageable pagination);

    @Query("""
        SELECT m FROM Medico m
        WHERE m.ativo = true
        AND m.especialidade = :especialidade
        AND m.id NOT IN (
            SELECT c.medico.id FROM SchedulingAppointment c
            WHERE c.data_consulta = :dateTime
        )
        ORDER BY RAND()
        limit 1
    """)
    Medico findRandomlyActiveByEspecialidadeAndAvailable(Especialidade especialidade, LocalDateTime dateTime);
}
