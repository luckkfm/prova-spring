package br.edu.faculdade.vagas;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class EmpresaService {

    private final EmpresaRepository repositorio;

    public EmpresaService(EmpresaRepository repositorio) {
        this.repositorio = repositorio;
    }

    public List<EmpresaResposta> listar() {
        return repositorio.findAll(Sort.by("nome")).stream()
                .map(EmpresaResposta::de)
                .toList();
    }

    public EmpresaResposta buscarPorSlug(String slug) {
        return EmpresaResposta.de(encontrar(slug));
    }

    @Transactional
    public EmpresaResposta criar(EmpresaEntrada entrada) {
        if (repositorio.existsBySlug(entrada.slug())) {
            throw new ConflitoException("Já existe uma empresa com o slug '" + entrada.slug() + "'.");
        }
        Empresa nova = new Empresa(entrada.nome(), entrada.slug(),
                entrada.site(), entrada.descricao());
        return EmpresaResposta.de(repositorio.save(nova));
    }

    @Transactional
    public EmpresaResposta atualizar(String slug, EmpresaEntrada entrada) {
        Empresa empresa = encontrar(slug);
        if (!slug.equals(entrada.slug()) && repositorio.existsBySlug(entrada.slug())) {
            throw new ConflitoException("Já existe uma empresa com o slug '" + entrada.slug() + "'.");
        }
        empresa.atualizar(entrada.nome(), entrada.slug(), entrada.site(), entrada.descricao());
        return EmpresaResposta.de(empresa);
    }

    @Transactional
    public void apagar(String slug) {
        repositorio.delete(encontrar(slug));
    }

    private Empresa encontrar(String slug) {
        return repositorio.findBySlug(slug)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Empresa '" + slug + "' não encontrada."));
    }
}
