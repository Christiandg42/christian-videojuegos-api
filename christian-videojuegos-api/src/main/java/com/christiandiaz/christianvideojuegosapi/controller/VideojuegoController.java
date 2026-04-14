package com.christiandiaz.christianvideojuegosapi.controller;

import com.christiandiaz.christianvideojuegosapi.model.Videojuego;
import com.christiandiaz.christianvideojuegosapi.service.VideojuegoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/videojuegos")
@CrossOrigin(origins = "*")
public class VideojuegoController {

    private final VideojuegoService videojuegoService;

    public VideojuegoController(VideojuegoService videojuegoService) {
        this.videojuegoService = videojuegoService;
    }

    @GetMapping
    public ResponseEntity<List<Videojuego>> obtenerTodos() {
        return ResponseEntity.ok(videojuegoService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        return videojuegoService.obtenerPorId(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No se ha encontrado el videojuego con id " + id));
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Videojuego videojuego) {
        try {
            Videojuego nuevo = videojuegoService.guardar(videojuego);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Videojuego videojuego) {
        try {
            Videojuego actualizado = videojuegoService.actualizar(id, videojuego);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrar(@PathVariable Long id) {
        try {
            videojuegoService.borrar(id);
            return ResponseEntity.ok("Videojuego eliminado correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/genero/{genero}")
    public ResponseEntity<List<Videojuego>> buscarPorGenero(@PathVariable String genero) {
        return ResponseEntity.ok(videojuegoService.buscarPorGenero(genero));
    }

    @GetMapping("/plataforma/{plataforma}")
    public ResponseEntity<List<Videojuego>> buscarPorPlataforma(@PathVariable String plataforma) {
        return ResponseEntity.ok(videojuegoService.buscarPorPlataforma(plataforma));
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Videojuego>> buscarPorTitulo(@RequestParam String titulo) {
        return ResponseEntity.ok(videojuegoService.buscarPorTitulo(titulo));
    }
}