package br.com.ifba.client;

import br.com.ifba.usuario.dto.UsuarioPostRequestDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Log4j2
//Classe para consumir a API
public class SpringClient {

    public static void main(String[] args) {
        WebClient webClient = WebClient.builder()
                .baseUrl("http://localhost:8080/usuarios/")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        //Inserindo dados na API
        UsuarioPostRequestDto usuarioPostRequestDto = new UsuarioPostRequestDto();
        usuarioPostRequestDto.setNome("Sergio");
        usuarioPostRequestDto.setEmail("sergio@gmail.com");
        usuarioPostRequestDto.setLogin("sergio");
        usuarioPostRequestDto.setSenha("123456");

        //Salvando pelo webclient
        String respostaSave = webClient.post()
                .uri("/save")
                .body(Mono.just(usuarioPostRequestDto), UsuarioPostRequestDto.class)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        //Mostra o usuario salvo no teste
        log.info(respostaSave);

        //Buscando todos os usuarios depois que foram salvos
        String respostaFind = webClient.get()
                .uri("/findall")
                .retrieve()
                .bodyToMono(String.class)
                .block();

        //Mostra os usuarios buscados
        log.info(respostaFind);
    }
}
