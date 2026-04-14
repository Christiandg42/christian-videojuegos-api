package com.christiandiaz.christianvideojuegosapi.service;

import com.christiandiaz.christianvideojuegosapi.model.Videojuego;

import java.util.List;
import java.util.Optional;

public interface VideojuegoService {

    List<Videojuego> obtenerTodos();

    Optional<Videojuego> obtenerPorId(Long id);

    Videojuego guardar(Videojuego videojuego);

    Videojuego actualizar(Long id, Videojuego videojuegoActualizado);

    void borrar(Long id);

    List<Videojuego> buscarPorGenero(String genero);

    List<Videojuego> buscarPorPlataforma(String plataforma);

    List<Videojuego> buscarPorTitulo(String titulo);
}