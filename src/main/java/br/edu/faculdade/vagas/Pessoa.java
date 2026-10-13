package br.edu.faculdade.vagas;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "pessoas")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, unique = true, length = 120)
    private String email;

    @Column(nullable = false, length = 40)
    private String area;

    @Column(nullable = false, length = 20)
    private String senioridade;

    protected Pessoa() {
    }

    public Pessoa(String nome, String email, String area, String senioridade) {
        atualizar(nome, email, area, senioridade);
    }

    public void atualizar(String nome, String email, String area, String senioridade) {
        this.nome = nome;
        this.email = email;
        this.area = area;
        this.senioridade = senioridade;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getArea() {
        return area;
    }

    public String getSenioridade() {
        return senioridade;
    }
}
