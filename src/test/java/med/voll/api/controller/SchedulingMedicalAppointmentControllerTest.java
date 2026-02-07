package med.voll.api.controller;

import med.voll.api.domain.medico.Especialidade;
import med.voll.api.domain.scheduling.SchedulingAppointmentService;
import med.voll.api.domain.scheduling.SchedulingDetaisDTO;
import med.voll.api.domain.scheduling.SchedulingMedicalAppointmentControllerDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.spi.LocaleServiceProvider;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
@AutoConfigureJsonTesters
class SchedulingMedicalAppointmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<SchedulingMedicalAppointmentControllerDTO> schedulingMedicalAppointmentControllerDTO;

    @Autowired
    private JacksonTester<SchedulingDetaisDTO> schedulingDetaisDTO;

    @Mock
    private SchedulingAppointmentService schedulingService;

    @Test
    @DisplayName("Deve devolver codigo http 400 quando informações para agendamento não estiverem inválidas")
    @WithMockUser
    void schedulingMedicalAppointmentScenarioONe() throws  Exception {
        var response = mockMvc.perform(post("/consultas"))
                .andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deve devolver codigo http 200 quando informações para agendamento estiverem inválidas")
    @WithMockUser
    void schedulingMedicalAppointmentScenarioTwo() throws  Exception {

        // create a date for next monday at 10am
        LocalDateTime nextMonday = LocalDateTime.now()
                .with(java.time.temporal.TemporalAdjusters.next(DayOfWeek.MONDAY))
                .withHour(10)
                .withMinute(0)
                .withSecond(0)
                .withNano(0);


        var testSchedulingDetaisDTO = new SchedulingDetaisDTO(
                null, 1l, 1l, nextMonday, Especialidade.CARDIOLOGIA
        );
        when(schedulingService.scheduleAppointment(any())).thenReturn(testSchedulingDetaisDTO);

        var response = mockMvc.perform(post("/consultas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(schedulingMedicalAppointmentControllerDTO.write(
                                new SchedulingMedicalAppointmentControllerDTO(
                                        1L,
                                        1L,
                                        nextMonday,
                                        Especialidade.CARDIOLOGIA
                                )
                        ).getJson())
                )
                .andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        var jsonExpected = schedulingDetaisDTO.write(testSchedulingDetaisDTO).getJson();
        assertThat(response.getContentAsString()).isEqualTo(jsonExpected);
    }
}