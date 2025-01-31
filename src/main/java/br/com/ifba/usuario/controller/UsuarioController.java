package br.com.ifba.usuario.controller;

import br.com.ifba.infrastructure.mapper.ObjectMapperUtil;
import br.com.ifba.usuario.dto.UsuarioGetResponseDto;
import br.com.ifba.usuario.dto.UsuarioPostRequestDto;
import br.com.ifba.usuario.dto.UsuarioUpdateRequestDto;
import br.com.ifba.usuario.entity.Usuario;
import br.com.ifba.usuario.service.UsuarioIService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
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
    public ResponseEntity<?> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(ObjectMapperUtil.mapList(this.usuarioService.findAll(), UsuarioGetResponseDto.class));
    }

}
