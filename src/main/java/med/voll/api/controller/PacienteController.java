package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.paciente.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroPaciente dados, UriComponentsBuilder uriBuilder) {
        Paciente paciente = new Paciente(dados);
        repository.save(paciente);

        var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand().toUri();

        return ResponseEntity.created(uri).body(new DetailedPacientDTO(paciente));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemPaciente>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable pagination){
        Page<DadosListagemPaciente> page = repository.findAllByAtivoTrue(pagination).map(DadosListagemPaciente::new);

        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity updatePacientPersonalData(@RequestBody @Valid UpdatePacienteDTO data){
        var pacient = repository.getReferenceById(data.id());
        pacient.updatePersonalData(data);

        return ResponseEntity.ok(new DetailedPacientDTO(pacient));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete (@PathVariable Long id) {
        var pacient = repository.getReferenceById(id);
        pacient.disable();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detail (@PathVariable Long id) {
        var pacient = repository.getReferenceById(id);

        return ResponseEntity.ok(new DetailedPacientDTO(pacient));
    }
}
