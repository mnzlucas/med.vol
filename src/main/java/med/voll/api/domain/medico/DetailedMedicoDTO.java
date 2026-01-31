package med.voll.api.domain.medico;

import med.voll.api.domain.endereco.Endereco;

public record DetailedMedicoDTO(
        Long id,
        String nome,
        String email,
        String crm,
        String telefone,
        Especialidade especialidade,
        Endereco endereco,
        boolean ativo) {
    public DetailedMedicoDTO(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getTelefone(), medico.getEspecialidade(), medico.getEndereco(), medico.isAtivo());
    }
}
