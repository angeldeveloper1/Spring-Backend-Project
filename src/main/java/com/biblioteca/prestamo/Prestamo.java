package com.biblioteca.prestamo;

import com.biblioteca.libro.Libro;
import com.biblioteca.usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "prestamo")
public class Prestamo {

    @Id
    @Column(name = "id_prestamo")
    @SequenceGenerator(
            sequenceName = "sequence_prestamo",
            name = "sequence_prestamo"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sequence_prestamo"
    )
    private Long id;
    @Column(name = "devuelto", nullable = false)
    private boolean devuelto;
    @Column(name = "fecha_devolucion")
    private LocalDate fechaDevolucion;
    @Column(name = "fecha_expiracion", nullable = false)
    private LocalDate fechaExpiracion;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(
            name = "id_usuario",
            referencedColumnName = "id_usuario"
    )
    private Usuario usuario;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(
            name = "id_libro",
            referencedColumnName = "id_libro"
    )
    private Libro libro;

    public Prestamo() {
    }

    public Prestamo(Long id, boolean devuleto, LocalDate fechaDevolucion, LocalDate fechaExpiracion) {
        this.id = id;
        this.devuelto = devuleto;
        this.fechaDevolucion = fechaDevolucion;
        this.fechaExpiracion = fechaExpiracion;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isDevuelto() {
        return devuelto;
    }

    public void setDevuelto(boolean devuelto) {
        this.devuelto = devuelto;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public LocalDate getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(LocalDate fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prestamo prestamo = (Prestamo) o;
        return devuelto == prestamo.devuelto && Objects.equals(id, prestamo.id) && Objects.equals(fechaDevolucion, prestamo.fechaDevolucion) && Objects.equals(fechaExpiracion, prestamo.fechaExpiracion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, devuelto, fechaDevolucion, fechaExpiracion);
    }

    @Override
    public String toString() {
        return "Prestamo{" +
                "id=" + id +
                ", devuleto=" + devuelto +
                ", fechaDevolucion=" + fechaDevolucion +
                ", fechaExpiracion=" + fechaExpiracion +
                '}';
    }
}
