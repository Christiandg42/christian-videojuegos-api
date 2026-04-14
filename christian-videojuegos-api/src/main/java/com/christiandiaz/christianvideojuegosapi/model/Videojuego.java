package com.christiandiaz.christianvideojuegosapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "videojuegos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Videojuego {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String titulo;

    @Column(nullable = false, length = 80)
    private String genero;

    @Column(nullable = false, length = 80)
    private String plataforma;

    private LocalDate fechaLanzamiento;

    @Column(nullable = false)
    private Integer notaPersonal;

    @Column(nullable = false)
    private Boolean completado;

    @Column(length = 120)
    private String estudio;

    @Column(length = 500)
    private String comentario;
}