package br.com.ifba.usuario.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioLoginResponseDto {

    //Mapeando a resposta do DTO para o JSON
    @JsonProperty(value = "login")
    private String login;

    @JsonProperty(value = "senha")
    private String senha;

}
