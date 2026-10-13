package br.edu.faculdade.vagas;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class PessoaService {

    private final PessoaRepository repositorio;

    public PessoaService(PessoaRepository repositorio) {
        this.repositorio = repositorio;
    }

    public List<PessoaResposta> listar() {
        return repositorio.findAll(Sort.by("id")).stream()
                .map(PessoaResposta::de)
                .toList();
    }

    public PessoaResposta buscarPorId(Long id) {
        return PessoaResposta.de(encontrar(id));
    }

    @Transactional
    public PessoaResposta criar(PessoaEntrada entrada) {
        if (repositorio.existsByEmailIgnoreCase(entrada.email())) {
            throw new ConflitoException("Já existe uma pessoa com o e-mail '" + entrada.email() + "'.");
        }
        Pessoa nova = new Pessoa(entrada.nome(), entrada.email(),
                entrada.area(), entrada.senioridade());
        return PessoaResposta.de(repositorio.save(nova));
    }

    @Transactional
    public PessoaResposta atualizar(Long id, PessoaEntrada entrada) {
        Pessoa pessoa = encontrar(id);
        if (repositorio.existsByEmailIgnoreCaseAndIdNot(entrada.email(), id)) {
            throw new ConflitoException("Já existe uma pessoa com o e-mail '" + entrada.email() + "'.");
        }
        pessoa.atualizar(entrada.nome(), entrada.email(), entrada.area(), entrada.senioridade());
        return PessoaResposta.de(pessoa);
    }

    @Transactional
    public void apagar(Long id) {
        repositorio.delete(encontrar(id));
    }

    private Pessoa encontrar(Long id) {
        return repositorio.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Pessoa " + id + " não encontrada."));
    }
}
