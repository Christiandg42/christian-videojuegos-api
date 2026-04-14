package com.christiandiaz.christianvideojuegosapi.repository;

import com.christiandiaz.christianvideojuegosapi.model.Videojuego;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideojuegoRepository extends JpaRepository<Videojuego, Long> {

    List<Videojuego> findByGenero(String genero);

    List<Videojuego> findByPlataforma(String plataforma);

    List<Videojuego> findByTituloContainingIgnoreCase(String titulo);
}