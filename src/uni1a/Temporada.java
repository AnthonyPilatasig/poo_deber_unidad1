package uni1a;

public class Temporada {
    private int numeroTemporada;
    private int episodios;
    private int anioLanzamiento;
    
    public Temporada(int numeroTemporada, int episodios, int anioLanzamiento) {
        this.numeroTemporada = numeroTemporada;
        this.episodios = episodios;
        this.anioLanzamiento = anioLanzamiento;
    }
    
    //getters y setters de la clase temporada
    public int getNumeroTemporada() {
        return numeroTemporada;
    }
    
    public void setNumeroTemporada(int numeroTemporada) {
        this.numeroTemporada = numeroTemporada;
    }
    
    public int getEpisodios() {
        return episodios;
    }
    
    public void setEpisodios(int episodios) {
        this.episodios = episodios;
    }
    
    public int getAnioLanzamiento() {
        return anioLanzamiento;
    }
    
    public void setAnioLanzamiento(int anioLanzamiento) {
        this.anioLanzamiento = anioLanzamiento;
    }
    
    @Override
    public String toString() {
        return "Temporada " + numeroTemporada + " (" + episodios + " episodios, " + anioLanzamiento + ")";
    }
}