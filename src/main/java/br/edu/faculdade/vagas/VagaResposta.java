package br.edu.faculdade.vagas;

public record VagaResposta(
        Long id,
        String titulo,
        String descricao,
        String area,
        String senioridade,
        String local,
        boolean aceitaIniciante,
        String empresaSlug,
        String empresaNome
) {

    public static VagaResposta de(Vaga v) {
        return new VagaResposta(v.getId(), v.getTitulo(), v.getDescricao(), v.getArea(),
                v.getSenioridade(), v.getLocal(), v.isAceitaIniciante(),
                v.getEmpresa().getSlug(), v.getEmpresa().getNome());
    }
}
