package br.com.ifba.usuario.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioPostRequestDto {

    @JsonProperty(value = "nome")
    @NotNull(message = "O nome é obrigatório")
    @NotBlank(message = "Nome vazio, insira um nome!")
    private String nome;

    @JsonProperty(value = "email")
    @Email(message = "Email inválido")
    private String email;

    @JsonProperty(value = "login")
    @NotNull(message = "O login é obrigatório")
    @NotBlank(message = "Login vazio, insira um login!")
    @Size(min = 3, max = 15, message = "O login deve ter entre 3 e 15 caracteres")
    private String login;

    @JsonProperty(value = "senha")
    @NotNull(message = "A senha é obrigatória")
    @NotBlank(message = "Senha vazia, insira uma senha!")
    private String senha;
}
