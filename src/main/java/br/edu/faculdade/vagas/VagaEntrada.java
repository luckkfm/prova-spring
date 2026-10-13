package br.edu.faculdade.vagas;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record VagaEntrada(
        @NotBlank(message = "o título é obrigatório")
        @Size(max = 120, message = "o título tem no máximo 120 caracteres")
        String titulo,

        @Size(max = 1000, message = "a descrição tem no máximo 1000 caracteres")
        String descricao,

        @NotBlank(message = "a área é obrigatória")
        @Size(max = 40, message = "a área tem no máximo 40 caracteres")
        String area,

        @NotBlank(message = "a senioridade é obrigatória")
        @Size(max = 20, message = "a senioridade tem no máximo 20 caracteres")
        String senioridade,

        @Size(max = 80, message = "o local tem no máximo 80 caracteres")
        String local,

        Boolean aceitaIniciante,

        @NotBlank(message = "o slug da empresa é obrigatório")
        String empresaSlug
) { }
