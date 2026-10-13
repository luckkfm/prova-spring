package br.edu.faculdade.vagas;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VagaRepository extends JpaRepository<Vaga, Long> {

    @EntityGraph(attributePaths = "empresa")
    List<Vaga> findAllByOrderByIdAsc();

    long countByAceitaInicianteTrue();

    boolean existsByEmpresa(Empresa empresa);
}
