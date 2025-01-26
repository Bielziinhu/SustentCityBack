package br.com.ifba.usuario.service;

import br.com.ifba.usuario.entity.Usuario;

import java.util.List;

public interface UsuarioIService {

    List<Usuario> findAll() throws RuntimeException;

    Usuario findById(Long id) throws RuntimeException;

    Usuario save(Usuario usuario) throws RuntimeException;

    void delete(Long id) throws RuntimeException;

    Usuario update(Long id, Usuario usuario) throws RuntimeException;
}
