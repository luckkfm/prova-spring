package br.edu.faculdade.vagas;

public record PessoaResposta(
        Long id,
        String nome,
        String email,
        String area,
        String senioridade
) {

    public static PessoaResposta de(Pessoa p) {
        return new PessoaResposta(p.getId(), p.getNome(), p.getEmail(),
                p.getArea(), p.getSenioridade());
    }
}
