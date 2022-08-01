package com.nekopunchi.nekopunchi.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.nekopunchi.nekopunchi.model.Usuario;
import com.nekopunchi.nekopunchi.model.exception.ResourceNotFoundException;

@Repository
public class UsuarioRepository {

    private List<Usuario> usuarios = new ArrayList<Usuario>();
    private Integer ultimoId = 0;

    /**
     * Método para retornar uma lista de usuários.
     * @return lista de usuários.
     */
    public List<Usuario> obterUsuarios(){
        return usuarios;
    }

    /**
     * Método que retorna uma lista de usuários.
     * @param id do usuário que será localizado.
     * @return Retorna um usuário caso tenha encontrado.
     */
    public Optional<Usuario> obterPorId(Integer id){
        return usuarios
            .stream()
            .filter(usuario -> usuario.getId() == id)
            .findFirst();
    }

    /**
     * Método para adicionar usuário na lista.
     * @param usuario que será adicionado.
     * @return Retorna o produto que foi adicionado na lista.
     */
    public Usuario adicionar(Usuario usuario){
        
        ultimoId ++;
        usuario.setId(ultimoId);
        usuarios.add(usuario);
        return usuario;
    } 

    /**
     * Método para deletar o usuário por id.
     * @param id do usuário que será deletado.
     */
    public void deletar (Integer id){
        usuarios.removeIf(usuario -> usuario.getId() == id);
    }

    /**
     * Método para atualizar o usuário na lista.
     * @param id usuario que será atualizado.
     * @return Retorna o usuário após atualizar a lista.
     */
    public Usuario atualizar (Usuario usuario){

        //Encontrar o usuário na lista:
        Optional<Usuario> usuarioEncontrado = obterPorId(usuario.getId());

        if (usuarioEncontrado.isEmpty()){
            throw new ResourceNotFoundException("Usuário não encontrado");
        }
        
        //Remover o usuário antigo da lista:
        deletar(usuario.getId());

        //Adicionar o usuário atualizado na lista:    
        usuarios.add(usuario);
        
        return usuario;
    }
}
