package com.medicare.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

// Perfil del medico con datos de contacto y especialidad
@Entity
@Table(name = "perfiles_medico")
public class PerfilMedico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String especialidad;

    @Column(nullable = false)
    private String telefono;

    @Column(nullable = false)
    private String email;

    private String direccion;

    // referencia inversa al medico, se ignora en el JSON para evitar ciclos
    @OneToOne(mappedBy = "perfil")
    @JsonIgnore
    private Medico medico;

    public PerfilMedico() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public Medico getMedico() { return medico; }
    public void setMedico(Medico medico) { this.medico = medico; }
}
