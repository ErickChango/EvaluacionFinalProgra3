package com.medicare.dto;

public class MedicoDTO {
    private String nombre;
    private String apellido;
    private String licencia;
    private PerfilMedicoDTO perfil;

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getLicencia() { return licencia; }
    public void setLicencia(String licencia) { this.licencia = licencia; }

    public PerfilMedicoDTO getPerfil() { return perfil; }
    public void setPerfil(PerfilMedicoDTO perfil) { this.perfil = perfil; }
}
