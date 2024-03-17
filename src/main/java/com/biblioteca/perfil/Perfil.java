package com.biblioteca.perfil;

import jakarta.persistence.*;

import java.util.Objects;
@Entity
@Table(name = "perfil")
public class Perfil {
    @Id
    @Column(name = "id_perfil")
    @SequenceGenerator(
            sequenceName = "sequence_perfil",
            name = "sequence_perfil"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sequence_perfil"
    )
    Long id;
    @Column(name = "name", nullable = false, length = 50)
    String name;
    @Column(name = "telefono", nullable = true, length = 20, unique = true)
    String telefono;
    @Column(name = "puntos_premios", nullable = false)
    Long puntosPremios;

    public Perfil() {
    }

    public Perfil(Long id, String name, String telefono, Long puntosPremios) {
        this.id = id;
        this.name = name;
        this.telefono = telefono;
        this.puntosPremios = puntosPremios;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Long getPuntosPremios() {
        return puntosPremios;
    }

    public void setPuntosPremios(Long puntosPremios) {
        this.puntosPremios = puntosPremios;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Perfil perfil = (Perfil) o;
        return Objects.equals(id, perfil.id) && Objects.equals(name, perfil.name) && Objects.equals(telefono, perfil.telefono) && Objects.equals(puntosPremios, perfil.puntosPremios);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, telefono, puntosPremios);
    }

    @Override
    public String toString() {
        return "Perfil{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", telefono='" + telefono + '\'' +
                ", puntosPremios=" + puntosPremios +
                '}';
    }
}
