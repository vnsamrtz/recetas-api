package com.recetas.recetas_api.modules.user.controller;

import com.recetas.recetas_api.modules.user.entity.User;
import com.recetas.recetas_api.modules.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> obtenerTodos() {
        return userService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> obtenerPorId(@PathVariable Long id) {
        return userService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public User crear(@RequestBody User user) {
        return userService.guardar(user);
    }

    @PostMapping("/{id}/foto")
    public User subirFotoPerfil(@PathVariable Long id, @RequestParam("archivo") MultipartFile archivo) {
        return userService.actualizarFotoPerfil(id, archivo);
    }

    @PutMapping("/{id}/biografia")
    public User actualizarBiografia(@PathVariable Long id, @RequestBody Map<String, String> body) {
        return userService.actualizarBiografia(id, body.get("biografia"));
    }

    @PutMapping("/{id}/nombre")
    public User actualizarNombre(@PathVariable Long id, @RequestBody Map<String, String> body) {
        return userService.actualizarNombre(id, body.get("nombre"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        userService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar")
    public List<User> buscar(@RequestParam String nombre) {
        return userService.buscarPorNombre(nombre);
    }
}