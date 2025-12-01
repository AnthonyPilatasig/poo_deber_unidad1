package test;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import uni1a.*;
import util.FileManager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Pruebas unitarias para FileManager
 * Prueba manejo de archivos CSV
 */
public class FileManagerTest {
    
    private FileManager fileManager;
    private static final String TEST_DATA_DIR = "data";
    
    @BeforeEach
    void setUp() {
        fileManager = new FileManager();
        limpiarArchivosDeTest();
    }
    
    @AfterEach
    void tearDown() {
        limpiarArchivosDeTest();
    }
    
    @Test
    @DisplayName("Test: Guardar y cargar películas")
    void testGuardarYCargarPeliculas() throws IOException {
        List<ContenidoAudiovisual> contenidos = new ArrayList<>();
        contenidos.add(new Pelicula("Inception", 148, "Ciencia Ficción", "Warner Bros"));
        contenidos.add(new Pelicula("The Matrix", 136, "Ciencia Ficción", "Warner Bros"));
        
        fileManager.guardarContenidos(contenidos);
        
        List<ContenidoAudiovisual> cargados = fileManager.cargarContenidos();
        
        assertEquals(2, cargados.size());
        assertTrue(cargados.get(0) instanceof Pelicula);
    }
    
    @Test
    @DisplayName("Test: Guardar y cargar series")
    void testGuardarYCargarSeries() throws IOException {
        List<ContenidoAudiovisual> contenidos = new ArrayList<>();
        contenidos.add(new SerieDeTV("Breaking Bad", 47, "Drama", 5));
        contenidos.add(new SerieDeTV("Game of Thrones", 60, "Fantasía", 8));
        
        fileManager.guardarContenidos(contenidos);
        
        List<ContenidoAudiovisual> cargados = fileManager.cargarContenidos();
        
        assertEquals(2, cargados.size());
        assertTrue(cargados.get(0) instanceof SerieDeTV);
    }
    
    @Test
    @DisplayName("Test: Guardar y cargar documentales")
    void testGuardarYCargarDocumentales() throws IOException {
        List<ContenidoAudiovisual> contenidos = new ArrayList<>();
        contenidos.add(new Documental("Planet Earth", 50, "Naturaleza", "Vida Salvaje"));
        contenidos.add(new Documental("Cosmos", 45, "Ciencia", "Astronomía"));
        
        fileManager.guardarContenidos(contenidos);
        
        List<ContenidoAudiovisual> cargados = fileManager.cargarContenidos();
        
        assertEquals(2, cargados.size());
        assertTrue(cargados.get(0) instanceof Documental);
    }
    
    @Test
    @DisplayName("Test: Guardar y cargar podcasts")
    void testGuardarYCargarPodcasts() throws IOException {
        List<ContenidoAudiovisual> contenidos = new ArrayList<>();
        contenidos.add(new Podcast("The Daily", 30, "Noticias", "Michael", 1, "Spotify"));
        contenidos.add(new Podcast("Tech Talk", 45, "Tecnología", "John", 5, "Apple"));
        
        fileManager.guardarContenidos(contenidos);
        
        List<ContenidoAudiovisual> cargados = fileManager.cargarContenidos();
        
        assertEquals(2, cargados.size());
        assertTrue(cargados.get(0) instanceof Podcast);
    }
    
    @Test
    @DisplayName("Test: Guardar y cargar livestreams")
    void testGuardarYCargarLivestreams() throws IOException {
        List<ContenidoAudiovisual> contenidos = new ArrayList<>();
        contenidos.add(new Livestream("Coding Session", 180, "Educación", 
                                      "CodeMaster", "Twitch", "Programación"));
        contenidos.add(new Livestream("Gaming Stream", 240, "Entretenimiento", 
                                      "Gamer123", "YouTube", "Gaming"));
        
        fileManager.guardarContenidos(contenidos);
        
        List<ContenidoAudiovisual> cargados = fileManager.cargarContenidos();
        
        assertEquals(2, cargados.size());
        assertTrue(cargados.get(0) instanceof Livestream);
    }
    
    @Test
    @DisplayName("Test: Guardar y cargar contenido mixto")
    void testGuardarYCargarContenidoMixto() throws IOException {
        List<ContenidoAudiovisual> contenidos = new ArrayList<>();
        contenidos.add(new Pelicula("Avatar", 162, "Ciencia Ficción", "20th Century"));
        contenidos.add(new SerieDeTV("Stranger Things", 50, "Ciencia Ficción", 4));
        contenidos.add(new Documental("Our Planet", 50, "Naturaleza", "Medio Ambiente"));
        contenidos.add(new Podcast("History Podcast", 40, "Historia", "Historian", 10, "Spotify"));
        contenidos.add(new Livestream("Live Coding", 120, "Educación", 
                                      "Developer", "Twitch", "Programación"));
        
        fileManager.guardarContenidos(contenidos);
        
        List<ContenidoAudiovisual> cargados = fileManager.cargarContenidos();
        
        assertEquals(5, cargados.size());
    }
    
    @Test
    @DisplayName("Test: Cargar desde archivos inexistentes")
    void testCargarDesdeArchivosInexistentes() throws IOException {
        List<ContenidoAudiovisual> cargados = fileManager.cargarContenidos();
        
        assertEquals(0, cargados.size());
    }
    
    @Test
    @DisplayName("Test: Guardar lista vacía")
    void testGuardarListaVacia() throws IOException {
        List<ContenidoAudiovisual> contenidos = new ArrayList<>();
        
        assertDoesNotThrow(() -> fileManager.guardarContenidos(contenidos));
    }
    
    @Test
    @DisplayName("Test: Verificar creación de directorio data")
    void testCreacionDirectorio() throws IOException {
        File dataDir = new File(TEST_DATA_DIR);
        if (dataDir.exists()) {
            dataDir.delete();
        }
        
        List<ContenidoAudiovisual> contenidos = new ArrayList<>();
        contenidos.add(new Pelicula("Test", 120, "Drama", "Studio"));
        
        fileManager.guardarContenidos(contenidos);
        
        assertTrue(dataDir.exists());
        assertTrue(dataDir.isDirectory());
    }
    
    @Test
    @DisplayName("Test: Integridad de datos después de guardar y cargar")
    void testIntegridadDatos() throws IOException {
        String titulo = "Test Movie";
        int duracion = 150;
        String genero = "Action";
        String estudio = "Test Studio";
        
        List<ContenidoAudiovisual> contenidos = new ArrayList<>();
        Pelicula peliculaOriginal = new Pelicula(titulo, duracion, genero, estudio);
        contenidos.add(peliculaOriginal);
        
        fileManager.guardarContenidos(contenidos);
        List<ContenidoAudiovisual> cargados = fileManager.cargarContenidos();
        
        Pelicula peliculaCargada = (Pelicula) cargados.get(0);
        
        assertEquals(titulo, peliculaCargada.getTitulo());
        assertEquals(duracion, peliculaCargada.getDuracionEnMinutos());
        assertEquals(genero, peliculaCargada.getGenero());
        assertEquals(estudio, peliculaCargada.getEstudio());
    }
    
    // ===== MÉTODOS AUXILIARES =====
    
    private void limpiarArchivosDeTest() {
        File dataDir = new File(TEST_DATA_DIR);
        if (dataDir.exists()) {
            File[] files = dataDir.listFiles();
            if (files != null) {
                for (File file : files) {
                    file.delete();
                }
            }
        }
    }
}