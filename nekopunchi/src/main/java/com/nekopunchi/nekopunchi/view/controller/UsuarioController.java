package com.nekopunchi.nekopunchi.view.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nekopunchi.nekopunchi.services.UsuarioService;
import com.nekopunchi.nekopunchi.shared.UsuarioDTO;
import com.nekopunchi.nekopunchi.view.model.UsuarioRequest;
import com.nekopunchi.nekopunchi.view.model.UsuarioResponse;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> obterTodos(){
        
        List<UsuarioDTO> usuarios = usuarioService.obterTodos();

        ModelMapper mapper = new ModelMapper();

        List<UsuarioResponse> resposta = usuarios.stream()
        .map(usuario -> mapper.map(usuario, UsuarioResponse.class))
        .collect(Collectors.toList());

        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<UsuarioResponse>> obterPorId(@PathVariable Integer id){

    // try{

        Optional<UsuarioDTO> dto = usuarioService.obterPorId(id);

        UsuarioResponse usuario = new ModelMapper()
        .map(dto.get(), UsuarioResponse.class);

        return new ResponseEntity<>(Optional.of(usuario), HttpStatus.OK);

    // } catch (Exception e) {
    //  return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    // }    

    }

    @PostMapping
    public ResponseEntity<UsuarioResponse> adicionar(@RequestBody UsuarioRequest usuarioReq){

        ModelMapper mapper = new ModelMapper();

        UsuarioDTO usuarioDto = mapper.map(usuarioReq, UsuarioDTO.class);

        usuarioDto = usuarioService.adicionar(usuarioDto);

        return new ResponseEntity<>(mapper.map(usuarioDto, UsuarioResponse.class), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Integer id){
        
        usuarioService.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponse> atualizar(@RequestBody UsuarioRequest usuarioReq, @PathVariable Integer id){
        
        ModelMapper mapper = new ModelMapper();

        UsuarioDTO usuarioDto = mapper.map(usuarioReq, UsuarioDTO.class);

        usuarioDto = usuarioService.atualizar(id, usuarioDto);
        
        return new ResponseEntity<>(mapper.map(usuarioDto, UsuarioResponse.class), HttpStatus.OK);
    }

}