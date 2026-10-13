package br.edu.faculdade.vagas;

public record EmpresaResposta(
        Long id,
        String nome,
        String slug,
        String site,
        String descricao
) {

    public static EmpresaResposta de(Empresa e) {
        return new EmpresaResposta(e.getId(), e.getNome(), e.getSlug(),
                e.getSite(), e.getDescricao());
    }
}
