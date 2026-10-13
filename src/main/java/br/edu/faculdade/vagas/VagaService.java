package br.edu.faculdade.vagas;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class VagaService {

    private final VagaRepository repositorio;
    private final EmpresaRepository empresas;

    public VagaService(VagaRepository repositorio, EmpresaRepository empresas) {
        this.repositorio = repositorio;
        this.empresas = empresas;
    }

    public List<VagaResposta> listar() {
        return repositorio.findAllByOrderByIdAsc().stream()
                .map(VagaResposta::de)
                .toList();
    }

    public VagaResposta buscarPorId(Long id) {
        return VagaResposta.de(encontrar(id));
    }

    @Transactional
    public VagaResposta criar(VagaEntrada entrada) {
        Vaga nova = new Vaga(entrada.titulo(), entrada.descricao(), entrada.area(),
                entrada.senioridade(), entrada.local(),
                Boolean.TRUE.equals(entrada.aceitaIniciante()),
                empresaDe(entrada.empresaSlug()));
        return VagaResposta.de(repositorio.save(nova));
    }

    @Transactional
    public VagaResposta atualizar(Long id, VagaEntrada entrada) {
        Vaga vaga = encontrar(id);
        vaga.atualizar(entrada.titulo(), entrada.descricao(), entrada.area(),
                entrada.senioridade(), entrada.local(),
                Boolean.TRUE.equals(entrada.aceitaIniciante()),
                empresaDe(entrada.empresaSlug()));
        return VagaResposta.de(vaga);
    }

    @Transactional
    public void apagar(Long id) {
        repositorio.delete(encontrar(id));
    }

    private Vaga encontrar(Long id) {
        return repositorio.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Vaga " + id + " não encontrada."));
    }

    private Empresa empresaDe(String slug) {
        return empresas.findBySlug(slug)
                .orElseThrow(() -> new RegraDeNegocioException(
                        "Não existe empresa com o slug '" + slug + "'."));
    }
}
