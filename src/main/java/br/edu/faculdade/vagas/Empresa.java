package br.edu.faculdade.vagas;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "empresas")
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 80)
    private String nome;

    @Column(nullable = false, unique = true, length = 60)
    private String slug;

    @Column(length = 120)
    private String site;

    @Column(length = 500)
    private String descricao;

    protected Empresa() {
    }

    public Empresa(String nome, String slug, String site, String descricao) {
        atualizar(nome, slug, site, descricao);
    }

    public void atualizar(String nome, String slug, String site, String descricao) {
        this.nome = nome;
        this.slug = slug;
        this.site = site;
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getSlug() {
        return slug;
    }

    public String getSite() {
        return site;
    }

    public String getDescricao() {
        return descricao;
    }
}
