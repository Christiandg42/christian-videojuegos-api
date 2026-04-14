package com.christiandiaz.christianvideojuegosapi.service.impl;

import com.christiandiaz.christianvideojuegosapi.model.Videojuego;
import com.christiandiaz.christianvideojuegosapi.repository.VideojuegoRepository;
import com.christiandiaz.christianvideojuegosapi.service.VideojuegoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VideojuegoServiceImpl implements VideojuegoService {

    private final VideojuegoRepository videojuegoRepository;

    public VideojuegoServiceImpl(VideojuegoRepository videojuegoRepository) {
        this.videojuegoRepository = videojuegoRepository;
    }

    @Override
    public List<Videojuego> obtenerTodos() {
        return videojuegoRepository.findAll();
    }

    @Override
    public Optional<Videojuego> obtenerPorId(Long id) {
        return videojuegoRepository.findById(id);
    }

    @Override
    public Videojuego guardar(Videojuego videojuego) {
        validarVideojuego(videojuego);
        return videojuegoRepository.save(videojuego);
    }

    @Override
    public Videojuego actualizar(Long id, Videojuego videojuegoActualizado) {
        validarVideojuego(videojuegoActualizado);

        return videojuegoRepository.findById(id)
                .map(videojuego -> {
                    videojuego.setTitulo(videojuegoActualizado.getTitulo());
                    videojuego.setGenero(videojuegoActualizado.getGenero());
                    videojuego.setPlataforma(videojuegoActualizado.getPlataforma());
                    videojuego.setFechaLanzamiento(videojuegoActualizado.getFechaLanzamiento());
                    videojuego.setNotaPersonal(videojuegoActualizado.getNotaPersonal());
                    videojuego.setCompletado(videojuegoActualizado.getCompletado());
                    videojuego.setEstudio(videojuegoActualizado.getEstudio());
                    videojuego.setComentario(videojuegoActualizado.getComentario());
                    return videojuegoRepository.save(videojuego);
                })
                .orElseThrow(() -> new RuntimeException("No existe un videojuego con id " + id));
    }

    @Override
    public void borrar(Long id) {
        if (!videojuegoRepository.existsById(id)) {
            throw new RuntimeException("No existe un videojuego con id " + id);
        }
        videojuegoRepository.deleteById(id);
    }

    @Override
    public List<Videojuego> buscarPorGenero(String genero) {
        return videojuegoRepository.findByGenero(genero);
    }

    @Override
    public List<Videojuego> buscarPorPlataforma(String plataforma) {
        return videojuegoRepository.findByPlataforma(plataforma);
    }

    @Override
    public List<Videojuego> buscarPorTitulo(String titulo) {
        return videojuegoRepository.findByTituloContainingIgnoreCase(titulo);
    }

    private void validarVideojuego(Videojuego videojuego) {
        if (videojuego.getTitulo() == null || videojuego.getTitulo().isBlank()) {
            throw new RuntimeException("El título no puede estar vacío");
        }

        if (videojuego.getGenero() == null || videojuego.getGenero().isBlank()) {
            throw new RuntimeException("El género no puede estar vacío");
        }

        if (videojuego.getPlataforma() == null || videojuego.getPlataforma().isBlank()) {
            throw new RuntimeException("La plataforma no puede estar vacía");
        }

        if (videojuego.getNotaPersonal() == null || videojuego.getNotaPersonal() < 0 || videojuego.getNotaPersonal() > 10) {
            throw new RuntimeException("La nota personal debe estar entre 0 y 10");
        }

        if (videojuego.getCompletado() == null) {
            throw new RuntimeException("El campo completado no puede ser nulo");
        }
    }
}