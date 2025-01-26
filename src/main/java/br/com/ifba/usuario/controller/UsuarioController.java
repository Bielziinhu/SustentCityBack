package br.com.ifba.usuario.controller;

import br.com.ifba.usuario.service.UsuarioIService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    //Implementar ao começar a utilizar o service
    //private final UsuarioIService usuarioService;

    @GetMapping(path ="/findall", produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<?> findAll() {
        //return ResponseEntity.ok(usuarioService.findAll());
        return null;
    }
}
