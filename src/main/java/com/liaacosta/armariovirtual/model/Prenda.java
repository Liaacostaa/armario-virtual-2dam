package com.liaacosta.armariovirtual.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "prendas")
public class Prenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="id_usuario")
    private Usuario usuario;
    @Column(name = "nombre")
    private String nombre;
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo")
    private TipoPrenda tipo;
    @Column(name = "color")
    private String color;
    @Enumerated(EnumType.STRING)
    @Column(name = "ocasion")
    private Ocasion ocasion;
    @Enumerated(EnumType.STRING)
    @Column(name = "temporada")
    private Temporada temporada;
    @Column(name = "foto")
    private String foto;
    @PrePersist
    protected void onCreate() {
        this.fechaCreacion = LocalDateTime.now();
    }
    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoPrenda getTipo() {
        return tipo;
    }

    public void setTipo(TipoPrenda tipo) {
        this.tipo = tipo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Ocasion getOcasion() {
        return ocasion;
    }

    public void setOcasion(Ocasion ocasion) {
        this.ocasion = ocasion;
    }

    public Temporada getTemporada() {
        return temporada;
    }

    public void setTemporada(Temporada temporada) {
        this.temporada = temporada;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
