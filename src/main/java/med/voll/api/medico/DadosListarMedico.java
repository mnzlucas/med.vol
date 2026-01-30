package med.voll.api.medico;

public record DadosListarMedico(String nome, String email, String crm, Especialidade especialidade) {
    public DadosListarMedico(Medico medico){
        this(medico.getNome(), medico.getEmail(), medico.getCrm(),medico.getEspecialidade());
    }
}
