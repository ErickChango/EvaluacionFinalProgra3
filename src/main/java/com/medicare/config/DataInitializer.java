package com.medicare.config;

import com.medicare.entity.Consulta;
import com.medicare.entity.Medico;
import com.medicare.entity.Paciente;
import com.medicare.entity.PerfilMedico;
import com.medicare.entity.Usuario;
import com.medicare.repository.ConsultaRepository;
import com.medicare.repository.MedicoRepository;
import com.medicare.repository.PacienteRepository;
import com.medicare.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Carga datos iniciales al arrancar la aplicación.
 * Crea usuarios de prueba (admin/admin123 y user/user123) con contraseñas BCrypt,
 * junto con médicos, pacientes y consultas de ejemplo.
 */
@Component
public class DataInitializer implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final MedicoRepository medicoRepository;
    private final PacienteRepository pacienteRepository;
    private final ConsultaRepository consultaRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UsuarioRepository usuarioRepository,
                           MedicoRepository medicoRepository,
                           PacienteRepository pacienteRepository,
                           ConsultaRepository consultaRepository,
                           PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.medicoRepository = medicoRepository;
        this.pacienteRepository = pacienteRepository;
        this.consultaRepository = consultaRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {

        // ── Usuarios ──────────────────────────────────────────────────────────
        Usuario admin = new Usuario();
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("admin123"));
        admin.setRol(Usuario.Rol.ADMIN);
        usuarioRepository.save(admin);

        Usuario user = new Usuario();
        user.setUsername("user");
        user.setPassword(passwordEncoder.encode("user123"));
        user.setRol(Usuario.Rol.USER);
        usuarioRepository.save(user);

        // ── Médicos con perfiles ──────────────────────────────────────────────
        Medico m1 = crearMedico("Carlos", "Ramírez", "LIC-001",
                "Cardiología", "555-1001", "carlos@medicare.com", "Av. Principal 100");

        Medico m2 = crearMedico("Ana", "López", "LIC-002",
                "Pediatría", "555-1002", "ana@medicare.com", "Calle Norte 200");

        Medico m3 = crearMedico("Luis", "Martínez", "LIC-003",
                "Cardiología", "555-1003", "luis@medicare.com", "Av. Sur 300");

        medicoRepository.save(m1);
        medicoRepository.save(m2);
        medicoRepository.save(m3);

        // ── Pacientes ─────────────────────────────────────────────────────────
        Paciente p1 = crearPaciente("Juan", "García", "DNI-001",
                LocalDate.of(1990, 5, 15), "555-2001", "juan@mail.com");

        Paciente p2 = crearPaciente("María", "Fernández", "DNI-002",
                LocalDate.of(1985, 8, 22), "555-2002", "maria@mail.com");

        Paciente p3 = crearPaciente("Pedro", "Sánchez", "DNI-003",
                LocalDate.of(2000, 1, 10), "555-2003", "pedro@mail.com");

        pacienteRepository.save(p1);
        pacienteRepository.save(p2);
        pacienteRepository.save(p3);

        // ── Consultas ─────────────────────────────────────────────────────────
        consultaRepository.save(crearConsulta(
                LocalDateTime.now().minusDays(10),
                "Dolor en el pecho", "Arritmia leve", m1, p1));

        consultaRepository.save(crearConsulta(
                LocalDateTime.now().minusDays(5),
                "Fiebre alta", "Infección viral", m2, p2));

        consultaRepository.save(crearConsulta(
                LocalDateTime.now().minusDays(2),
                "Control anual", "Sin novedades", m1, p3));

        System.out.println("=== Datos iniciales cargados ===");
        System.out.println("Admin -> usuario: admin  / contraseña: admin123");
        System.out.println("User  -> usuario: user   / contraseña: user123");
    }

    private Medico crearMedico(String nombre, String apellido, String licencia,
                                String especialidad, String telefono,
                                String email, String direccion) {
        PerfilMedico perfil = new PerfilMedico();
        perfil.setEspecialidad(especialidad);
        perfil.setTelefono(telefono);
        perfil.setEmail(email);
        perfil.setDireccion(direccion);

        Medico medico = new Medico();
        medico.setNombre(nombre);
        medico.setApellido(apellido);
        medico.setLicencia(licencia);
        medico.setPerfil(perfil);
        return medico;
    }

    private Paciente crearPaciente(String nombre, String apellido, String dni,
                                    LocalDate fechaNacimiento, String telefono,
                                    String email) {
        Paciente paciente = new Paciente();
        paciente.setNombre(nombre);
        paciente.setApellido(apellido);
        paciente.setDni(dni);
        paciente.setFechaNacimiento(fechaNacimiento);
        paciente.setTelefono(telefono);
        paciente.setEmail(email);
        return paciente;
    }

    private Consulta crearConsulta(LocalDateTime fechaHora, String motivo,
                                    String diagnostico, Medico medico, Paciente paciente) {
        Consulta consulta = new Consulta();
        consulta.setFechaHora(fechaHora);
        consulta.setMotivo(motivo);
        consulta.setDiagnostico(diagnostico);
        consulta.setMedico(medico);
        consulta.setPaciente(paciente);
        return consulta;
    }
}
