package com.biblioteca.usuario;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;
@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @Column(name = "id_usuario")
    @SequenceGenerator(
            sequenceName = "sequence_usuario",
            name = "sequence_usuario"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sequence_usuario"
    )
    private Long id;
    @Column(name = "name", nullable = false, length = 50, unique = true)
    private String name;
    @Column(name = "email", nullable = false, length = 300, unique = true)
    private String email;
    @Column(name = "birthdate")
    private LocalDate birthDate;
    @Column(name = "password", nullable = false, length = 20, unique = true)
    private String password;

    public Usuario() {
    }
    public Usuario(Long id, String name, String email, LocalDate birthDate, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
        this.password = password;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id) && Objects.equals(name, usuario.name) && Objects.equals(email, usuario.email) && Objects.equals(birthDate, usuario.birthDate) && Objects.equals(password, usuario.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, birthDate, password);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", birthDate=" + birthDate +
                ", password='" + password + '\'' +
                '}';
    }
}
