package br.edu.faculdade.vagas;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "vagas")
public class Vaga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String titulo;

    @Column(length = 1000)
    private String descricao;

    @Column(nullable = false, length = 40)
    private String area;

    @Column(nullable = false, length = 20)
    private String senioridade;

    @Column(name = "localizacao", length = 80)
    private String local;

    @Column(nullable = false)
    private boolean aceitaIniciante;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "empresa_id", nullable = false)
    private Empresa empresa;

    protected Vaga() {
    }

    public Vaga(String titulo, String descricao, String area, String senioridade,
                String local, boolean aceitaIniciante, Empresa empresa) {
        atualizar(titulo, descricao, area, senioridade, local, aceitaIniciante, empresa);
    }

    public void atualizar(String titulo, String descricao, String area, String senioridade,
                          String local, boolean aceitaIniciante, Empresa empresa) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.area = area;
        this.senioridade = senioridade;
        this.local = local;
        this.aceitaIniciante = aceitaIniciante;
        this.empresa = empresa;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getArea() {
        return area;
    }

    public String getSenioridade() {
        return senioridade;
    }

    public String getLocal() {
        return local;
    }

    public boolean isAceitaIniciante() {
        return aceitaIniciante;
    }

    public Empresa getEmpresa() {
        return empresa;
    }
}
