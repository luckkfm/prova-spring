package br.edu.faculdade.vagas;

import java.util.Map;

public record EstatisticasResposta(
        long totalDeVagas,
        long aceitamIniciante,
        Map<String, Long> porArea,
        Map<String, Long> porSenioridade
) { }
