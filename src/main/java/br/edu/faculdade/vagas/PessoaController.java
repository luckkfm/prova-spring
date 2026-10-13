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
@RequestMapping("/pessoas")
public class PessoaController {

    private final PessoaService servico;

    public PessoaController(PessoaService servico) {
        this.servico = servico;
    }

    @GetMapping
    public List<PessoaResposta> listar() {
        return servico.listar();
    }

    @GetMapping("/{id}")
    public PessoaResposta porId(@PathVariable Long id) {
        return servico.buscarPorId(id);
    }

    @PostMapping
    public ResponseEntity<PessoaResposta> criar(@Valid @RequestBody PessoaEntrada entrada) {
        PessoaResposta salva = servico.criar(entrada);
        return ResponseEntity.created(URI.create("/pessoas/" + salva.id())).body(salva);
    }

    @PutMapping("/{id}")
    public PessoaResposta atualizar(@PathVariable Long id,
                                    @Valid @RequestBody PessoaEntrada entrada) {
        return servico.atualizar(id, entrada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagar(@PathVariable Long id) {
        servico.apagar(id);
        return ResponseEntity.noContent().build();
    }
}
