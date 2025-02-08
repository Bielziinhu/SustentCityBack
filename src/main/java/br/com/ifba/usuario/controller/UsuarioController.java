package br.com.ifba.usuario.controller;

import br.com.ifba.infrastructure.mapper.ObjectMapperUtil;
import br.com.ifba.usuario.dto.UsuarioGetResponseDto;
import br.com.ifba.usuario.dto.UsuarioLoginResponseDto;
import br.com.ifba.usuario.dto.UsuarioPostRequestDto;
import br.com.ifba.usuario.dto.UsuarioUpdateRequestDto;
import br.com.ifba.usuario.entity.Usuario;
import br.com.ifba.usuario.service.UsuarioIService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UsuarioController {

    private final UsuarioIService usuarioService;
    private final ObjectMapperUtil objectMapperUtil;

    //Salvar usuario usando DTO
    @PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@RequestBody @Valid UsuarioPostRequestDto usuarioPostRequestDto) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ObjectMapperUtil.map(usuarioService.save(
                        (ObjectMapperUtil.map(usuarioPostRequestDto, Usuario.class))),
                        UsuarioGetResponseDto.class
                ));
    }

    //Login do usuario usando DTO
    @PostMapping(path = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@RequestBody @Valid UsuarioLoginResponseDto usuarioLoginRequestDto) {

        // Chama o serviço que autentica o usuário
        Optional<Usuario> usuario = usuarioService.findByLoginAndSenha(
                usuarioLoginRequestDto.getLogin(),
                usuarioLoginRequestDto.getSenha()
        );

        //Alguns retornos para verificar na log se deu erro
        if (usuario.isPresent()) {
            UsuarioLoginResponseDto responseDto = ObjectMapperUtil.map(usuario.get(), UsuarioLoginResponseDto.class);

            return ResponseEntity.status(HttpStatus.OK)
                    .body(Map.of("message", "Login bem-sucedido", "usuario", responseDto));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Login ou senha inválidos"));
        }
    }


    //Excluir usuario pelo ID usando DTO
    @DeleteMapping(path = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        usuarioService.delete(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(Map.of("LOG", "Usuario excluido com sucesso"));
    }

    //Fazer update do usuario pelo ID usando DTO
    @PutMapping(path = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody UsuarioUpdateRequestDto usuarioUpdateRequestDto) {
        Usuario usuarioAtualizado = usuarioService.update(id,
                ObjectMapperUtil.map(usuarioUpdateRequestDto, Usuario.class));

        return ResponseEntity.status(HttpStatus.OK)
                .body(ObjectMapperUtil.map(usuarioAtualizado, UsuarioGetResponseDto.class));
    }

    //Buscando todos os usuarios com mapeamento no DTO
    @GetMapping(path ="/findall", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAll(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(this.usuarioService.findAll(pageable).map(c -> ObjectMapperUtil.map(c, UsuarioGetResponseDto.class)));
    }

}
