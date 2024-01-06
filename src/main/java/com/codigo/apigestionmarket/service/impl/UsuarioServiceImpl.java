package com.codigo.apigestionmarket.service.impl;

import com.codigo.apigestionmarket.constantes.Constants;
import com.codigo.apigestionmarket.dao.UsuarioDAO;
import com.codigo.apigestionmarket.entity.Usuarios;
import com.codigo.apigestionmarket.service.UsuarioService;
import com.codigo.apigestionmarket.util.MarketUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    private UsuarioDAO usuarioDAO;
    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        log.info("Ingreso a registrar usuario");
        usuarioDAO.save(getUsuariosMap(requestMap));
        log.info("Termino de registrar usuario");
        return MarketUtils.getResponseEntity(Constants.MSG_USUARIO_CREADO, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> login(Map<String, String> requestMap) {
        return null;
    }

    @Override
    public List<Usuarios> obtenerTodos() {
        return usuarioDAO.findAll();
    }

    private Usuarios getUsuariosMap(Map<String, String> requestMap) {
        Usuarios usuarios = new Usuarios();
        usuarios.setNombre(requestMap.get("nombre"));
        usuarios.setNumeroContacto(requestMap.get("numeroContacto"));
        usuarios.setEmail(requestMap.get("email"));
        usuarios.setPassword(requestMap.get("password"));
        usuarios.setStatus(Constants.ACTIVO);
        usuarios.setRole(Constants.ROLE_USUARIO);
        return usuarios;
    }
}
