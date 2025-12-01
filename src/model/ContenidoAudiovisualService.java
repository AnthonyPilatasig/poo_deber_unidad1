package model;

import uni1a.*;
import util.FileManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class ContenidoAudiovisualService {
    private List<ContenidoAudiovisual> contenidos;
    private final FileManager fileManager;
    
    public ContenidoAudiovisualService(FileManager fileManager) {
        this.contenidos = new ArrayList<>();
        this.fileManager = fileManager;
    }
    
    public void agregarContenido(ContenidoAudiovisual contenido) {
        if (contenido != null) {
            contenidos.add(contenido);
        }
    }
    
    public boolean eliminarContenido(int id) {
        return contenidos.removeIf(c -> c.getId() == id);
    }
    
    public ContenidoAudiovisual buscarPorId(int id) {
        return contenidos.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);
    }
    
    public List<ContenidoAudiovisual> buscarPorTitulo(String titulo) {
        return contenidos.stream()
                .filter(c -> c.getTitulo().toLowerCase()
                        .contains(titulo.toLowerCase()))
                .collect(Collectors.toList());
    }
    
    public List<ContenidoAudiovisual> buscarPorGenero(String genero) {
        return contenidos.stream()
                .filter(c -> c.getGenero().equalsIgnoreCase(genero))
                .collect(Collectors.toList());
    }
    
    public List<Pelicula> obtenerPeliculas() {
        return contenidos.stream()
                .filter(c -> c instanceof Pelicula)
                .map(c -> (Pelicula) c)
                .collect(Collectors.toList());
    }
    
    public List<SerieDeTV> obtenerSeries() {
        return contenidos.stream()
                .filter(c -> c instanceof SerieDeTV)
                .map(c -> (SerieDeTV) c)
                .collect(Collectors.toList());
    }
    
    public List<Documental> obtenerDocumentales() {
        return contenidos.stream()
                .filter(c -> c instanceof Documental)
                .map(c -> (Documental) c)
                .collect(Collectors.toList());
    }
    
    public List<Podcast> obtenerPodcasts() {
        return contenidos.stream()
                .filter(c -> c instanceof Podcast)
                .map(c -> (Podcast) c)
                .collect(Collectors.toList());
    }
    
    public List<Livestream> obtenerLivestreams() {
        return contenidos.stream()
                .filter(c -> c instanceof Livestream)
                .map(c -> (Livestream) c)
                .collect(Collectors.toList());
    }
    
    public List<ContenidoAudiovisual> obtenerTodosContenidos() {
        return new ArrayList<>(contenidos);
    }
    
    public void guardarEnArchivos() throws IOException {
        fileManager.guardarContenidos(contenidos);
    }
    
    public void cargarDesdeArchivos() throws IOException {
        contenidos = fileManager.cargarContenidos();
    }
    
    public EstadisticasSistema obtenerEstadisticas() {
        return new EstadisticasSistema(
            contenidos.size(),
            obtenerPeliculas().size(),
            obtenerSeries().size(),
            obtenerDocumentales().size(),
            obtenerPodcasts().size(),
            obtenerLivestreams().size()
        );
    }
    
    public void limpiarContenidos() {
        contenidos.clear();
    }
    

    public static class EstadisticasSistema {
        private final int totalContenidos;
        private final int totalPeliculas;
        private final int totalSeries;
        private final int totalDocumentales;
        private final int totalPodcasts;
        private final int totalLivestreams;
        
        public EstadisticasSistema(int total, int peliculas, int series, 
                                   int documentales, int podcasts, int livestreams) {
            this.totalContenidos = total;
            this.totalPeliculas = peliculas;
            this.totalSeries = series;
            this.totalDocumentales = documentales;
            this.totalPodcasts = podcasts;
            this.totalLivestreams = livestreams;
        }
        
        public int getTotalContenidos() { return totalContenidos; }
        public int getTotalPeliculas() { return totalPeliculas; }
        public int getTotalSeries() { return totalSeries; }
        public int getTotalDocumentales() { return totalDocumentales; }
        public int getTotalPodcasts() { return totalPodcasts; }
        public int getTotalLivestreams() { return totalLivestreams; }
    }
}