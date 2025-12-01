package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import model.ContenidoAudiovisualService;
import uni1a.*;
import util.FileManager;
import java.util.List;


public class ContenidoAudiovisualTest {
    
    private ContenidoAudiovisualService service;
    private FileManager fileManager;
    
    @BeforeEach
    void setUp() {
        fileManager = new FileManager();
        service = new ContenidoAudiovisualService(fileManager);
    }
    
    @AfterEach
    void tearDown() {
        service.limpiarContenidos();
    }
    
    
    @Test
    @DisplayName("Test: Creación de Película")
    void testCreacionPelicula() {
        Pelicula pelicula = new Pelicula("Inception", 148, "Ciencia Ficción", "Warner Bros");
        
        assertNotNull(pelicula);
        assertEquals("Inception", pelicula.getTitulo());
        assertEquals(148, pelicula.getDuracionEnMinutos());
        assertEquals("Ciencia Ficción", pelicula.getGenero());
        assertEquals("Warner Bros", pelicula.getEstudio());
    }
    
    @Test
    @DisplayName("Test: Creación de Serie de TV")
    void testCreacionSerieTV() {
        SerieDeTV serie = new SerieDeTV("Breaking Bad", 47, "Drama", 5);
        
        assertNotNull(serie);
        assertEquals("Breaking Bad", serie.getTitulo());
        assertEquals(5, serie.getTemporadas());
    }
    
    @Test
    @DisplayName("Test: Creación de Documental")
    void testCreacionDocumental() {
        Documental documental = new Documental("Planet Earth", 50, "Naturaleza", "Vida Salvaje");
        
        assertNotNull(documental);
        assertEquals("Planet Earth", documental.getTitulo());
        assertEquals("Vida Salvaje", documental.getTema());
    }
    
    @Test
    @DisplayName("Test: Creación de Podcast")
    void testCreacionPodcast() {
        Podcast podcast = new Podcast("The Daily", 30, "Noticias", "Michael", 1, "Spotify");
        
        assertNotNull(podcast);
        assertEquals("The Daily", podcast.getTitulo());
        assertEquals("Michael", podcast.getAnfitrion());
    }
    
    @Test
    @DisplayName("Test: Creación de Livestream")
    void testCreacionLivestream() {
        Livestream livestream = new Livestream("Coding", 180, "Educación", 
                                               "CodeMaster", "Twitch", "Programación");
        
        assertNotNull(livestream);
        assertEquals("Coding", livestream.getTitulo());
        assertEquals("CodeMaster", livestream.getStreamer());
    }
    

    
    @Test
    @DisplayName("Test: Agregar actores a película")
    void testAgregarActores() {
        Pelicula pelicula = new Pelicula("Avatar", 162, "Ciencia Ficción", "20th Century");
        Actor actor1 = new Actor("Sam Worthington", 47, "Australiano");
        
        pelicula.agregarActor(actor1);
        
        assertNotNull(pelicula);
    }
    
    @Test
    @DisplayName("Test: Agregar temporadas a serie")
    void testAgregarTemporadas() {
        SerieDeTV serie = new SerieDeTV("Game of Thrones", 60, "Fantasía", 8);
        Temporada temp1 = new Temporada(1, 10, 2011);
        
        serie.agregarTemporada(temp1);
        
        assertEquals(1, serie.getListaTemporadas().size());
    }
    
    @Test
    @DisplayName("Test: Agregar investigadores")
    void testAgregarInvestigadores() {
        Documental documental = new Documental("Cosmos", 45, "Ciencia", "Astronomía");
        Investigador inv1 = new Investigador("Carl Sagan", "Cosmología", "Cornell");
        
        documental.agregarInvestigador(inv1);
        
        assertEquals(1, documental.getInvestigadores().size());
    }
    

    
    @Test
    @DisplayName("Test: Agregar contenido")
    void testAgregarContenido() {
        Pelicula pelicula = new Pelicula("Matrix", 136, "Ciencia Ficción", "Warner Bros");
        service.agregarContenido(pelicula);
        
        List<ContenidoAudiovisual> contenidos = service.obtenerTodosContenidos();
        assertEquals(1, contenidos.size());
    }
    
    @Test
    @DisplayName("Test: Buscar por ID")
    void testBuscarPorId() {
        Pelicula pelicula = new Pelicula("Interstellar", 169, "Ciencia Ficción", "Paramount");
        service.agregarContenido(pelicula);
        
        ContenidoAudiovisual encontrado = service.buscarPorId(pelicula.getId());
        
        assertNotNull(encontrado);
        assertEquals("Interstellar", encontrado.getTitulo());
    }
    
    @Test
    @DisplayName("Test: Buscar por título")
    void testBuscarPorTitulo() {
        service.agregarContenido(new Pelicula("The Dark Knight", 152, "Acción", "Warner Bros"));
        service.agregarContenido(new Pelicula("The Dark Knight Rises", 164, "Acción", "Warner Bros"));
        
        List<ContenidoAudiovisual> resultados = service.buscarPorTitulo("Dark Knight");
        
        assertEquals(2, resultados.size());
    }
    
    @Test
    @DisplayName("Test: Eliminar contenido")
    void testEliminarContenido() {
        Pelicula pelicula = new Pelicula("Titanic", 195, "Romance", "20th Century");
        service.agregarContenido(pelicula);
        
        assertTrue(service.eliminarContenido(pelicula.getId()));
        assertEquals(0, service.obtenerTodosContenidos().size());
    }
    
    @Test
    @DisplayName("Test: Obtener estadísticas")
    void testObtenerEstadisticas() {
        service.agregarContenido(new Pelicula("Film1", 120, "Drama", "Studio"));
        service.agregarContenido(new SerieDeTV("Serie1", 45, "Comedia", 3));
        
        var stats = service.obtenerEstadisticas();
        
        assertEquals(2, stats.getTotalContenidos());
        assertEquals(1, stats.getTotalPeliculas());
        assertEquals(1, stats.getTotalSeries());
    }
    

    @Test
    @DisplayName("Test: Agregar nulo")
    void testAgregarNulo() {
        service.agregarContenido(null);
        assertEquals(0, service.obtenerTodosContenidos().size());
    }
    
    @Test
    @DisplayName("Test: Buscar ID inexistente")
    void testBuscarIdInexistente() {
        ContenidoAudiovisual resultado = service.buscarPorId(9999);
        assertNull(resultado);
    }
    

    
    @Test
    @DisplayName("Test: Livestream transmisión")
    void testLivestreamTransmision() {
        Livestream livestream = new Livestream("Stream", 120, "Gaming", 
                                               "Streamer", "Twitch", "Juegos");
        
        assertFalse(livestream.isEnVivo());
        
        livestream.iniciarTransmision();
        assertTrue(livestream.isEnVivo());
        
        livestream.finalizarTransmision();
        assertFalse(livestream.isEnVivo());
    }
    
    @Test
    @DisplayName("Test: Podcast descargas")
    void testPodcastDescargas() {
        Podcast podcast = new Podcast("Test", 45, "Tecnología", "Host", 1, "Spotify");
        
        assertEquals(0, podcast.getDescargas());
        
        podcast.registrarDescarga();
        podcast.registrarDescarga();
        
        assertEquals(2, podcast.getDescargas());
    }
}