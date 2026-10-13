package br.edu.faculdade.vagas;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class EstatisticasService {

    private final VagaRepository vagas;

    public EstatisticasService(VagaRepository vagas) {
        this.vagas = vagas;
    }

    public EstatisticasResposta resumo() {
        List<Vaga> todas = vagas.findAll();
        return new EstatisticasResposta(
                todas.size(),
                vagas.countByAceitaInicianteTrue(),
                contarPor(todas, Vaga::getArea),
                contarPor(todas, Vaga::getSenioridade));
    }

    private TreeMap<String, Long> contarPor(List<Vaga> todas, Function<Vaga, String> chave) {
        return todas.stream()
                .collect(Collectors.groupingBy(chave, TreeMap::new, Collectors.counting()));
    }
}
