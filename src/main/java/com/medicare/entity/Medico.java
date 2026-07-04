package com.medicare.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

// Entidad medico, tiene relacion 1:1 con perfil y 1:N con consultas
@Entity
@Table(name = "medicos")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    // numero de licencia unico por medico
    @Column(nullable = false, unique = true)
    private String licencia;

    // cada medico tiene un solo perfil
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "perfil_id", referencedColumnName = "id")
    private PerfilMedico perfil;

    // un medico puede tener muchas consultas
    @OneToMany(mappedBy = "medico", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("medico-consultas")
    private List<Consulta> consultas = new ArrayList<>();

    public Medico() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getLicencia() { return licencia; }
    public void setLicencia(String licencia) { this.licencia = licencia; }

    public PerfilMedico getPerfil() { return perfil; }
    public void setPerfil(PerfilMedico perfil) { this.perfil = perfil; }

    public List<Consulta> getConsultas() { return consultas; }
    public void setConsultas(List<Consulta> consultas) { this.consultas = consultas; }
}
