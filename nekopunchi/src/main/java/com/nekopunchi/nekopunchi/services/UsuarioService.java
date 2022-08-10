package com.nekopunchi.nekopunchi.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nekopunchi.nekopunchi.model.Usuario;
import com.nekopunchi.nekopunchi.model.exception.ResourceNotFoundException;
import com.nekopunchi.nekopunchi.repository.UsuarioRepository;
import com.nekopunchi.nekopunchi.shared.UsuarioDTO;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Método para retornar uma lista de usuários.
     * @return lista de usuários.
     */
    public List<UsuarioDTO> obterTodos(){

        //Retorna uma lista de usuário model.
        List<Usuario> usuarios = usuarioRepository.findAll();

        return usuarios.stream()
        .map(usuario -> new ModelMapper().map(usuario, UsuarioDTO.class))
        .collect(Collectors.toList()); 
    }

     /**
     * Método que retorna uma lista de usuários.
     * @param id do usuário que será localizado.
     * @return Retorna um usuário caso tenha encontrado.
     */
    public Optional<UsuarioDTO> obterPorId(Integer id){

        //Obtendo optional de usuario pelo id.
        Optional<Usuario> usuario = usuarioRepository.findById(id);

        //Se não encontrar, lança a exception...
        if(usuario.isEmpty()){
            throw new ResourceNotFoundException("Usuario de id " + id + " não encontrado!");
        }

        //Convertendo o meu optional de usuário em um UsuarioDTO.
        UsuarioDTO dto = new ModelMapper().map(usuario.get(), UsuarioDTO.class);

        //Criando e retornando um optional de dto.
        return Optional.of(dto);
    }

    /**
     * Método para adicionar usuário na lista.
     * @param usuario que será adicionado.
     * @return Retorna o produto que foi adicionado na lista.
     */
    public UsuarioDTO adicionar(UsuarioDTO usuarioDto){

        //Removendo o id para realizar o cadastro.
        usuarioDto.setId(null);

        //Criar um objeto de mapeamento.
        ModelMapper mapper = new ModelMapper();

        //Converter o nosso UsuarioDTO em um Usuario.
        Usuario usuario = mapper.map(usuarioDto, Usuario.class);

        //Salvar o usuario no banco.
        usuario = usuarioRepository.save(usuario);

        usuarioDto.setId(usuario.getId());

        //Retornar o UsuarioDTO atualizado.

        return usuarioDto;
    }

    /**
     * Método para deletar o usuário por id.
     * @param id do usuário que será deletado.
     */
    public void deletar (Integer id){

        //Obtendo optional de usuario pelo id.
        Optional<Usuario> usuario = usuarioRepository.findById(id);

        //Se não encontrar, lança a exception...
        if(usuario.isEmpty()){
            throw new ResourceNotFoundException("Usuário de id " + id + " não encontrado!");
        }

        usuarioRepository.deleteById(id);
    }

    /**
     * Método para atualizar o usuário na lista.
     * @param id do usuário que será atualizado.
     * @return Retorna o usuário após atualizar a lista.
     */
    public UsuarioDTO atualizar (Integer id, UsuarioDTO usuarioDto){

        //Passar o id para o usuarioDto.
        usuarioDto.setId(id);

        //Ciar um objeto de mapeamento.
        ModelMapper mapper = new ModelMapper();

        //Converter o UsuarioDTO em um Usuario.
        Usuario usuario = mapper.map(usuarioDto, Usuario.class);

        //Atualizar o usuario no banco de dados.
        usuarioRepository.save(usuario);

        //Retornar o usuarioDto atualizado.
        return usuarioDto;
    }
}