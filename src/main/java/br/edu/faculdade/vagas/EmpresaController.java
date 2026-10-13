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
@RequestMapping("/empresas")
public class EmpresaController {

    private final EmpresaService servico;

    public EmpresaController(EmpresaService servico) {
        this.servico = servico;
    }

    @GetMapping
    public List<EmpresaResposta> listar() {
        return servico.listar();
    }

    @GetMapping("/{slug}")
    public EmpresaResposta porSlug(@PathVariable String slug) {
        return servico.buscarPorSlug(slug);
    }

    @PostMapping
    public ResponseEntity<EmpresaResposta> criar(@Valid @RequestBody EmpresaEntrada entrada) {
        EmpresaResposta salva = servico.criar(entrada);
        return ResponseEntity.created(URI.create("/empresas/" + salva.slug())).body(salva);
    }

    @PutMapping("/{slug}")
    public EmpresaResposta atualizar(@PathVariable String slug,
                                     @Valid @RequestBody EmpresaEntrada entrada) {
        return servico.atualizar(slug, entrada);
    }

    @DeleteMapping("/{slug}")
    public ResponseEntity<Void> apagar(@PathVariable String slug) {
        servico.apagar(slug);
        return ResponseEntity.noContent().build();
    }
}
