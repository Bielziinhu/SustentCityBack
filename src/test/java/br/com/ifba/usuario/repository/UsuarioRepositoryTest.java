package br.com.ifba.usuario.repository;

import br.com.ifba.usuario.entity.Usuario;
import jakarta.validation.ConstraintViolationException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

@DataJpaTest
@DisplayName("Teste no repository em usuarios")
@ActiveProfiles("test")
public class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    @DisplayName("Sucesso ao encontrar user pelo login")
    public void findByLogin_whenSuccessful() {
        this.saveUsuario(this.createUsuario());

        Optional<Usuario> usuarioFound = usuarioRepository
                .findByLogin("Gabriel");

        Assertions.assertThat(usuarioFound.isPresent()).isTrue();
        Assertions.assertThat(usuarioFound).isNotNull();
        Assertions.assertThat(usuarioFound.get().getLogin())
                .isEqualTo("Gabriel");
    }

    private Usuario createUsuario() {
        return Usuario.builder()
                .nome("Gabriel")
                .login("Gabriel")
                .email("gabriel@gmail.com")
                .senha("123456")
                .build();
    }

    private void saveUsuario(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    @Test
    @DisplayName("Achar usuario pelo login quando usuario não for encontrado")
    public void findByLogin_whenUsuarioNotFound() {
        Optional<Usuario> usuario = usuarioRepository
                .findByLogin("non_existent_user");

        Assertions.assertThat(usuario).isNotPresent();
    }

    @Test
    @DisplayName("Sucesso ao encontrar o usuário por login e senha")
    public void findByLoginAndSenha_whenSuccessful() {
        Optional<Usuario> usuarioFound = usuarioRepository
                .findByLoginAndSenha("Gabriel", "123456");

        Assertions.assertThat(usuarioFound.isPresent()).isTrue();
        Assertions.assertThat(usuarioFound.get().getLogin())
                .isEqualTo("Gabriel");
        Assertions.assertThat(usuarioFound.get().getSenha())
                .isEqualTo("123456");
    }

    @Test
    @DisplayName("Encontrar usuário por login e senha quando Usuário não for encontrado")
    public void findByLoginAndSenha_whenUsuarioNotFound() {
        Optional<Usuario> usuario = usuarioRepository
                .findByLoginAndSenha("Gabriel", "123456");

        Assertions.assertThat(usuario).isNotPresent();
    }

    @BeforeEach
    public void setup() {
        Usuario usuario = Usuario.builder()
                .nome("Gabriel")
                .login("Gabriel")
                .email("gabriel@gmail.com")
                .senha("123456")
                .build();

        usuarioRepository.save(usuario);
        System.out.println("Usuário preparado para o teste: " + usuario);
    }

    @Test
    @DisplayName("Exceção ao salvar com nome vazio")
    void save_throwsConstraintViolationException_whenNameIsEmpty() {
        Usuario usuario = new Usuario();

        Assertions.assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> this.usuarioRepository.save(usuario))
                .withMessageContaining("O campo nome do usuário é obrigatório");
    }
}
