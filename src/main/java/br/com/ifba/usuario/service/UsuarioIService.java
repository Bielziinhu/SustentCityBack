package br.com.ifba.usuario.service;

import br.com.ifba.usuario.entity.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UsuarioIService {

    Page<Usuario> findAll(Pageable pageable);

    Usuario findById(Long id);

    Optional<Usuario> findByLoginAndSenha(String login, String senha);

    Usuario save(Usuario usuario);

    void delete(Long id);

    Usuario update(Long id, Usuario usuario);
}
