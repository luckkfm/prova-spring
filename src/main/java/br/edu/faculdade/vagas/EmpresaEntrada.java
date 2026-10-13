package br.edu.faculdade.vagas;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record EmpresaEntrada(
        @NotBlank(message = "o nome é obrigatório")
        @Size(max = 80, message = "o nome tem no máximo 80 caracteres")
        String nome,

        @NotBlank(message = "o slug é obrigatório")
        @Size(max = 60, message = "o slug tem no máximo 60 caracteres")
        @Pattern(regexp = "^[a-z0-9]+(-[a-z0-9]+)*$",
                message = "o slug aceita só letras minúsculas, números e hífen")
        String slug,

        @Size(max = 120, message = "o site tem no máximo 120 caracteres")
        String site,

        @Size(max = 500, message = "a descrição tem no máximo 500 caracteres")
        String descricao
) { }
