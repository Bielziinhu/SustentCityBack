package br.com.ifba.usuario.service;

import br.com.ifba.usuario.entity.Usuario;
import br.com.ifba.usuario.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService implements UsuarioIService{

    private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioService.class);

    private final UsuarioRepository usuarioRepository;

    public List<Usuario> findAll() {
        LOGGER.info("Buscando todos os usuários");
        return usuarioRepository.findAll();
    }

    public Usuario save(Usuario usuario) {
        LOGGER.info("Salvando usuário");
        return usuarioRepository.save(usuario);
    }

    public Usuario findById(Long id) {
        LOGGER.info("Buscando usuário por id");
        return usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    public void delete(Long id) {
        LOGGER.info("Deletando usuário");
        usuarioRepository.deleteById(id);
    }

    public Usuario update(Long id, Usuario usuario) {
        LOGGER.info("Atualizando usuário");
        Usuario usuarioSalvo = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        usuarioSalvo.setNome(usuario.getNome());
        usuarioSalvo.setEmail(usuario.getEmail());
        return usuarioRepository.save(usuarioSalvo);
    }


}
