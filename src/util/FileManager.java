package util;

import uni1a.*;
import java.io.*;
import java.util.*;


public class FileManager {
    private static final String DELIMITER = ",";
    private static final String PELICULAS_FILE = "data/peliculas.csv";
    private static final String SERIES_FILE = "data/series.csv";
    private static final String DOCUMENTALES_FILE = "data/documentales.csv";
    private static final String PODCASTS_FILE = "data/podcasts.csv";
    private static final String LIVESTREAMS_FILE = "data/livestreams.csv";
    
    //Guarda una lista de contenidos audiovisuales en archivos CSV

    public void guardarContenidos(List<ContenidoAudiovisual> contenidos) throws IOException {
        crearDirectorioSiNoExiste();
        
        List<Pelicula> peliculas = new ArrayList<>();
        List<SerieDeTV> series = new ArrayList<>();
        List<Documental> documentales = new ArrayList<>();
        List<Podcast> podcasts = new ArrayList<>();
        List<Livestream> livestreams = new ArrayList<>();
        
        // Separar por tipo
        for (ContenidoAudiovisual contenido : contenidos) {
            if (contenido instanceof Pelicula) {
                peliculas.add((Pelicula) contenido);
            } else if (contenido instanceof SerieDeTV) {
                series.add((SerieDeTV) contenido);
            } else if (contenido instanceof Documental) {
                documentales.add((Documental) contenido);
            } else if (contenido instanceof Podcast) {
                podcasts.add((Podcast) contenido);
            } else if (contenido instanceof Livestream) {
                livestreams.add((Livestream) contenido);
            }
        }
        
        guardarPeliculas(peliculas);
        guardarSeries(series);
        guardarDocumentales(documentales);
        guardarPodcasts(podcasts);
        guardarLivestreams(livestreams);
    }
    
    //Carga todos los contenidos desde archivos CSV
     
    public List<ContenidoAudiovisual> cargarContenidos() throws IOException {
        List<ContenidoAudiovisual> contenidos = new ArrayList<>();
        
        contenidos.addAll(cargarPeliculas());
        contenidos.addAll(cargarSeries());
        contenidos.addAll(cargarDocumentales());
        contenidos.addAll(cargarPodcasts());
        contenidos.addAll(cargarLivestreams());
        
        return contenidos;
    }
    
    // Metodos privados para peliculas
    
    private void guardarPeliculas(List<Pelicula> peliculas) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PELICULAS_FILE))) {
            writer.write("titulo,duracion,genero,estudio\n");
            for (Pelicula pelicula : peliculas) {
                writer.write(String.format("%s,%d,%s,%s\n",
                    escaparCampo(pelicula.getTitulo()),
                    pelicula.getDuracionEnMinutos(),
                    escaparCampo(pelicula.getGenero()),
                    escaparCampo(pelicula.getEstudio())
                ));
            }
        }
    }
    
    private List<Pelicula> cargarPeliculas() throws IOException {
        List<Pelicula> peliculas = new ArrayList<>();
        File file = new File(PELICULAS_FILE);
        
        if (!file.exists()) {
            return peliculas;
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            reader.readLine(); // Saltar encabezado
            String line;
            
            while ((line = reader.readLine()) != null) {
                String[] datos = line.split(DELIMITER);
                if (datos.length >= 4) {
                    Pelicula pelicula = new Pelicula(
                        datos[0].trim(),
                        Integer.parseInt(datos[1].trim()),
                        datos[2].trim(),
                        datos[3].trim()
                    );
                    peliculas.add(pelicula);
                }
            }
        }
        
        return peliculas;
    }
    
    //Metodos privados para series
    
    private void guardarSeries(List<SerieDeTV> series) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(SERIES_FILE))) {
            writer.write("titulo,duracion,genero,temporadas\n");
            for (SerieDeTV serie : series) {
                writer.write(String.format("%s,%d,%s,%d\n",
                    escaparCampo(serie.getTitulo()),
                    serie.getDuracionEnMinutos(),
                    escaparCampo(serie.getGenero()),
                    serie.getTemporadas()
                ));
            }
        }
    }
    
    private List<SerieDeTV> cargarSeries() throws IOException {
        List<SerieDeTV> series = new ArrayList<>();
        File file = new File(SERIES_FILE);
        
        if (!file.exists()) {
            return series;
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            reader.readLine(); // Saltar encabezado
            String line;
            
            while ((line = reader.readLine()) != null) {
                String[] datos = line.split(DELIMITER);
                if (datos.length >= 4) {
                    SerieDeTV serie = new SerieDeTV(
                        datos[0].trim(),
                        Integer.parseInt(datos[1].trim()),
                        datos[2].trim(),
                        Integer.parseInt(datos[3].trim())
                    );
                    series.add(serie);
                }
            }
        }
        
        return series;
    }
    
    //Metodos privados para documentales
    
    private void guardarDocumentales(List<Documental> documentales) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DOCUMENTALES_FILE))) {
            writer.write("titulo,duracion,genero,tema\n");
            for (Documental documental : documentales) {
                writer.write(String.format("%s,%d,%s,%s\n",
                    escaparCampo(documental.getTitulo()),
                    documental.getDuracionEnMinutos(),
                    escaparCampo(documental.getGenero()),
                    escaparCampo(documental.getTema())
                ));
            }
        }
    }
    
    private List<Documental> cargarDocumentales() throws IOException {
        List<Documental> documentales = new ArrayList<>();
        File file = new File(DOCUMENTALES_FILE);
        
        if (!file.exists()) {
            return documentales;
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            reader.readLine(); // Saltar encabezado
            String line;
            
            while ((line = reader.readLine()) != null) {
                String[] datos = line.split(DELIMITER);
                if (datos.length >= 4) {
                    Documental documental = new Documental(
                        datos[0].trim(),
                        Integer.parseInt(datos[1].trim()),
                        datos[2].trim(),
                        datos[3].trim()
                    );
                    documentales.add(documental);
                }
            }
        }
        
        return documentales;
    }
    
    //Metodos privados para podcast
    
    private void guardarPodcasts(List<Podcast> podcasts) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PODCASTS_FILE))) {
            writer.write("titulo,duracion,genero,anfitrion,episodio,plataforma\n");
            for (Podcast podcast : podcasts) {
                writer.write(String.format("%s,%d,%s,%s,%d,%s\n",
                    escaparCampo(podcast.getTitulo()),
                    podcast.getDuracionEnMinutos(),
                    escaparCampo(podcast.getGenero()),
                    escaparCampo(podcast.getAnfitrion()),
                    podcast.getNumeroEpisodio(),
                    escaparCampo(podcast.getPlataforma())
                ));
            }
        }
    }
    
    private List<Podcast> cargarPodcasts() throws IOException {
        List<Podcast> podcasts = new ArrayList<>();
        File file = new File(PODCASTS_FILE);
        
        if (!file.exists()) {
            return podcasts;
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            reader.readLine(); // Saltar encabezado
            String line;
            
            while ((line = reader.readLine()) != null) {
                String[] datos = line.split(DELIMITER);
                if (datos.length >= 6) {
                    Podcast podcast = new Podcast(
                        datos[0].trim(),
                        Integer.parseInt(datos[1].trim()),
                        datos[2].trim(),
                        datos[3].trim(),
                        Integer.parseInt(datos[4].trim()),
                        datos[5].trim()
                    );
                    podcasts.add(podcast);
                }
            }
        }
        
        return podcasts;
    }
    
    //Metodos privados para livestream
    
    private void guardarLivestreams(List<Livestream> livestreams) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LIVESTREAMS_FILE))) {
            writer.write("titulo,duracion,genero,streamer,plataforma,categoria\n");
            for (Livestream livestream : livestreams) {
                writer.write(String.format("%s,%d,%s,%s,%s,%s\n",
                    escaparCampo(livestream.getTitulo()),
                    livestream.getDuracionEnMinutos(),
                    escaparCampo(livestream.getGenero()),
                    escaparCampo(livestream.getStreamer()),
                    escaparCampo(livestream.getPlataforma()),
                    escaparCampo(livestream.getCategoria())
                ));
            }
        }
    }
    
    private List<Livestream> cargarLivestreams() throws IOException {
        List<Livestream> livestreams = new ArrayList<>();
        File file = new File(LIVESTREAMS_FILE);
        
        if (!file.exists()) {
            return livestreams;
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            reader.readLine(); // Saltar encabezado
            String line;
            
            while ((line = reader.readLine()) != null) {
                String[] datos = line.split(DELIMITER);
                if (datos.length >= 6) {
                    Livestream livestream = new Livestream(
                        datos[0].trim(),
                        Integer.parseInt(datos[1].trim()),
                        datos[2].trim(),
                        datos[3].trim(),
                        datos[4].trim(),
                        datos[5].trim()
                    );
                    livestreams.add(livestream);
                }
            }
        }
        
        return livestreams;
    }
    
    //Metodos auxiliares
    
    private void crearDirectorioSiNoExiste() {
        File directory = new File("data");
        if (!directory.exists()) {
            directory.mkdir();
        }
    }
    
    private String escaparCampo(String campo) {
        if (campo.contains(",")) {
            return "\"" + campo + "\"";
        }
        return campo;
    }
}