package uni1a;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
	
public class Livestream extends ContenidoAudiovisual {
    private String streamer;
    private LocalDateTime fechaTransmision;
    private int espectadoresPico;
    private int espectadoresActuales;
    private String plataforma;
    private List<String> moderadores;
    private double donacionesTotales;
    private boolean enVivo;
    private String categoria;
    
    public Livestream(String titulo, int duracionEnMinutos, String genero,
                      String streamer, String plataforma, String categoria) {
        super(titulo, duracionEnMinutos, genero);
        this.streamer = streamer;
        this.plataforma = plataforma;
        this.categoria = categoria;
        this.fechaTransmision = LocalDateTime.now();
        this.moderadores = new ArrayList<>();
        this.espectadoresPico = 0;
        this.espectadoresActuales = 0;
        this.donacionesTotales = 0.0;
        this.enVivo = false;
    }
    
    // Getters y Setters
    public String getStreamer() {
        return streamer;
    }
    
    public void setStreamer(String streamer) {
        this.streamer = streamer;
    }
    
    public LocalDateTime getFechaTransmision() {
        return fechaTransmision;
    }
    
    public void setFechaTransmision(LocalDateTime fechaTransmision) {
        this.fechaTransmision = fechaTransmision;
    }
    
    public int getEspectadoresPico() {
        return espectadoresPico;
    }
    
    public int getEspectadoresActuales() {
        return espectadoresActuales;
    }
    
    public String getPlataforma() {
        return plataforma;
    }
    
    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }
    
    public String getCategoria() {
        return categoria;
    }
    
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
    public double getDonacionesTotales() {
        return donacionesTotales;
    }
    
    public boolean isEnVivo() {
        return enVivo;
    }
    
    // Métodos específicos
    public void iniciarTransmision() {
        this.enVivo = true;
        this.fechaTransmision = LocalDateTime.now();
        System.out.println("¡Transmisión iniciada!");
    }
    
    public void finalizarTransmision() {
        this.enVivo = false;
        this.espectadoresActuales = 0;
        System.out.println("Transmisión finalizada");
    }
    
    public void agregarEspectador() {
        if (enVivo) {
            this.espectadoresActuales++;
            if (this.espectadoresActuales > this.espectadoresPico) {
                this.espectadoresPico = this.espectadoresActuales;
            }
        }
    }
    
    public void quitarEspectador() {
        if (espectadoresActuales > 0) {
            this.espectadoresActuales--;
        }
    }
    
    public void agregarModerador(String moderador) {
        this.moderadores.add(moderador);
    }
    
    public void recibirDonacion(double monto) {
        this.donacionesTotales += monto;
        System.out.println("Donación recibida: $" + monto);
    }
    
    public List<String> getModeradores() {
        return moderadores;
    }
    
    @Override
    public void mostrarDetalles() {
        System.out.println("=== DETALLES DEL LIVESTREAM ===");
        System.out.println("ID: " + getId());
        System.out.println("Título: " + getTitulo());
        System.out.println("Streamer: " + streamer);
        System.out.println("Plataforma: " + plataforma);
        System.out.println("Categoría: " + categoria);
        System.out.println("Género: " + getGenero());
        System.out.println("Duración: " + getDuracionEnMinutos() + " minutos");
        System.out.println("Estado: " + (enVivo ? "EN VIVO" : "FINALIZADO"));
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        System.out.println("Fecha: " + fechaTransmision.format(formatter));
        
        System.out.println("Espectadores actuales: " + espectadoresActuales);
        System.out.println("Pico de espectadores: " + espectadoresPico);
        System.out.println("Donaciones totales: $" + String.format("%.2f", donacionesTotales));
        
        if (!moderadores.isEmpty()) {
            System.out.println("Moderadores:");
            for (String mod : moderadores) {
                System.out.println("  - " + mod);
            }
        }
        System.out.println();
    }
}