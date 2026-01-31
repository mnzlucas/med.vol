package med.voll.api.paciente;

import med.voll.api.endereco.Endereco;
import med.voll.api.medico.Especialidade;
import med.voll.api.medico.Medico;

public record DetailedPacientDTO(
        Long id,
        String nome,
        String email,
        String cpf,
        String telefone,
        Endereco endereco,
        boolean ativo) {
    public DetailedPacientDTO(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf(), paciente.getTelefone(), paciente.getEndereco(), paciente.isAtivo());
    }
}
