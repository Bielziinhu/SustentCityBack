package br.com.ifba.usuario.service;

import br.com.ifba.infrastructure.exception.BusinessException;
import br.com.ifba.usuario.entity.Usuario;
import br.com.ifba.usuario.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
//Service utilizando o repository
public class UsuarioService implements UsuarioIService{

    private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioService.class);

    private final UsuarioRepository usuarioRepository;

    //Usando paginação para encontrar todos os usuarios ?size=3
    public Page<Usuario> findAll(Pageable pageable) {
        LOGGER.info("Buscando todos os usuários");
        return usuarioRepository.findAll(pageable);
    }

    @Transactional
    public Usuario save(Usuario usuario) {
        LOGGER.info("Salvando usuário");
        return usuarioRepository.save(usuario);
    }

    public Usuario findById(Long id) {
        LOGGER.info("Buscando usuário por id");
        return usuarioRepository.findById(id).orElseThrow(() -> new BusinessException("Usuário não encontrado"));
    }

    public void delete(Long id) {
        LOGGER.info("Deletando usuário");
        usuarioRepository.deleteById(id);
    }

    public Usuario update(Long id, Usuario usuario) {
        LOGGER.info("Atualizando usuário");
        Usuario usuarioSalvo = usuarioRepository.findById(id).orElseThrow(() -> new BusinessException("Usuário não encontrado"));
        usuarioSalvo.setNome(usuario.getNome());
        usuarioSalvo.setEmail(usuario.getEmail());
        return usuarioRepository.save(usuarioSalvo);
    }


}
