package br.edu.faculdade.vagas;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class BuscaService {

    private final VagaRepository repositorio;

    public BuscaService(VagaRepository repositorio) {
        this.repositorio = repositorio;
    }

    public List<VagaResposta> buscar(String area, String senioridade, Boolean aceitaIniciante) {
        return repositorio.findAllByOrderByIdAsc().stream()
                .filter(v -> area == null || v.getArea().equalsIgnoreCase(area))
                .filter(v -> senioridade == null || v.getSenioridade().equalsIgnoreCase(senioridade))
                .filter(v -> aceitaIniciante == null || v.isAceitaIniciante() == aceitaIniciante)
                .map(VagaResposta::de)
                .toList();
    }
}
