package com.biblioteca.prestamo;

import java.util.Objects;

public class Prestamo {
    private Long id;

    private boolean devuelto;

    public Prestamo() {
    }

    public Prestamo(Long id, boolean devuelto) {
        this.id = id;
        this.devuelto = devuelto;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prestamo prestamo = (Prestamo) o;
        return devuelto == prestamo.devuelto && Objects.equals(id, prestamo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, devuelto);
    }

    @Override
    public String toString() {
        return "Prestamo{" +
                "id=" + id +
                ", devuelto=" + devuelto +
                '}';
    }
}
