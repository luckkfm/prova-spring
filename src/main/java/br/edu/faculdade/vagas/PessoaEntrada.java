package br.edu.faculdade.vagas;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PessoaEntrada(
        @NotBlank(message = "o nome é obrigatório")
        @Size(max = 100, message = "o nome tem no máximo 100 caracteres")
        String nome,

        @NotBlank(message = "o e-mail é obrigatório")
        @Email(message = "o e-mail não é válido")
        @Size(max = 120, message = "o e-mail tem no máximo 120 caracteres")
        String email,

        @NotBlank(message = "a área é obrigatória")
        @Size(max = 40, message = "a área tem no máximo 40 caracteres")
        String area,

        @NotBlank(message = "a senioridade é obrigatória")
        @Size(max = 20, message = "a senioridade tem no máximo 20 caracteres")
        String senioridade
) { }
