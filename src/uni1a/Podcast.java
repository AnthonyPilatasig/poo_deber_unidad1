package uni1a;

import java.util.ArrayList;
import java.util.List;

public class Podcast extends ContenidoAudiovisual {
    private String anfitrion;
    private int numeroEpisodio;
    private String plataforma;
    private List<String> sponsors;
    private int descargas;
    private boolean esExclusivo;
    
    public Podcast(String titulo, int duracionEnMinutos, String genero, 
                   String anfitrion, int numeroEpisodio, String plataforma) {
        super(titulo, duracionEnMinutos, genero);
        this.anfitrion = anfitrion;
        this.numeroEpisodio = numeroEpisodio;
        this.plataforma = plataforma;
        this.sponsors = new ArrayList<>();
        this.descargas = 0;
        this.esExclusivo = false;
    }
    
    // Getters y Setters
    public String getAnfitrion() {
        return anfitrion;
    }
    
    public void setAnfitrion(String anfitrion) {
        this.anfitrion = anfitrion;
    }
    
    public int getNumeroEpisodio() {
        return numeroEpisodio;
    }
    
    public void setNumeroEpisodio(int numeroEpisodio) {
        this.numeroEpisodio = numeroEpisodio;
    }
    
    public String getPlataforma() {
        return plataforma;
    }
    
    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }
    
    public int getDescargas() {
        return descargas;
    }
    
    public boolean isExclusivo() {
        return esExclusivo;
    }
    
    public void setExclusivo(boolean exclusivo) {
        this.esExclusivo = exclusivo;
    }
    
    // Métodos específicos
    public void agregarSponsor(String sponsor) {
        this.sponsors.add(sponsor);
    }
    
    public void registrarDescarga() {
        this.descargas++;
    }
    
    public List<String> getSponsors() {
        return sponsors;
    }
    
    public void reproducir() {
        System.out.println("Reproduciendo episodio " + numeroEpisodio + " de " + getTitulo());
    }
    
    @Override
    public void mostrarDetalles() {
        System.out.println("=== DETALLES DEL PODCAST ===");
        System.out.println("ID: " + getId());
        System.out.println("Título: " + getTitulo());
        System.out.println("Episodio #: " + numeroEpisodio);
        System.out.println("Duración: " + getDuracionEnMinutos() + " minutos");
        System.out.println("Género: " + getGenero());
        System.out.println("Anfitrión: " + anfitrion);
        System.out.println("Plataforma: " + plataforma);
        System.out.println("Descargas: " + descargas);
        System.out.println("Exclusivo: " + (esExclusivo ? "Sí" : "No"));
        if (!sponsors.isEmpty()) {
            System.out.println("Patrocinadores:");
            for (String sponsor : sponsors) {
                System.out.println("  - " + sponsor);
            }
        }
        System.out.println();
    }
}