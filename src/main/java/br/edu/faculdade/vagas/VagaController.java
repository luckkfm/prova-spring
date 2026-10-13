package br.edu.faculdade.vagas;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/vagas")
public class VagaController {

    private final VagaService servico;

    public VagaController(VagaService servico) {
        this.servico = servico;
    }

    @GetMapping
    public List<VagaResposta> listar() {
        return servico.listar();
    }

    @GetMapping("/{id}")
    public VagaResposta porId(@PathVariable Long id) {
        return servico.buscarPorId(id);
    }

    @PostMapping
    public ResponseEntity<VagaResposta> criar(@Valid @RequestBody VagaEntrada entrada) {
        VagaResposta salva = servico.criar(entrada);
        return ResponseEntity.created(URI.create("/vagas/" + salva.id())).body(salva);
    }

    @PutMapping("/{id}")
    public VagaResposta atualizar(@PathVariable Long id,
                                  @Valid @RequestBody VagaEntrada entrada) {
        return servico.atualizar(id, entrada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagar(@PathVariable Long id) {
        servico.apagar(id);
        return ResponseEntity.noContent().build();
    }
}
