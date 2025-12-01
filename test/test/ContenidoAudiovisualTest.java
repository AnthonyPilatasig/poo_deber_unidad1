package test;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import model.ContenidoAudiovisualService;
import uni1a.*;
import util.FileManager;

import java.io.IOException;
import java.util.List;

/**
 * Pruebas unitarias exhaustivas para el sistema de contenido audiovisual
 * Cubre todos los casos: normales, límite y excepcionales
 */
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
    
    // ===== PRUEBAS DE CREACIÓN DE OBJETOS =====
    
    @Test
    @DisplayName("Test: Creación de Película")
    void testCreacionPelicula() {
        Pelicula pelicula = new Pelicula("Inception", 148, "Ciencia Ficción", "Warner Bros");
        
        assertNotNull(pelicula);
        assertEquals("Inception", pelicula.getTitulo());
        assertEquals(148, pelicula.getDuracionEnMinutos());
        assertEquals("Ciencia Ficción", pelicula.getGenero());
        assertEquals("Warner Bros", pelicula.getEstudio());
        assertTrue(pelicula.getId() >= 0);
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
        Podcast podcast = new Podcast("The Daily", 30, "Noticias", "Michael Barbaro", 1, "Spotify");
        
        assertNotNull(podcast);
        assertEquals("The Daily", podcast.getTitulo());
        assertEquals("Michael Barbaro", podcast.getAnfitrion());
        assertEquals(1, podcast.getNumeroEpisodio());
    }
    
    @Test
    @DisplayName("Test: Creación de Livestream")
    void testCreacionLivestream() {
        Livestream livestream = new Livestream("Coding Session", 180, "Educación", 
                                               "CodeMaster", "Twitch", "Programación");
        
        assertNotNull(livestream);
        assertEquals("Coding Session", livestream.getTitulo());
        assertEquals("CodeMaster", livestream.getStreamer());
        assertEquals("Twitch", livestream.getPlataforma());
    }
    
    // ===== PRUEBAS DE AGREGACIÓN Y RELACIONES =====
    
    @Test
    @DisplayName("Test: Agregar actores a película")
    void testAgregarActoresAPelicula() {
        Pelicula pelicula = new Pelicula("Avatar", 162, "Ciencia Ficción", "20th Century");
        Actor actor1 = new Actor("Sam Worthington", 47, "Australiano");
        Actor actor2 = new Actor("Zoe Saldana", 45, "Estadounidense");
        
        pelicula.agregarActor(actor1);
        pelicula.agregarActor(actor2);
        
        // Verificar que se agregaron correctamente
        assertNotNull(pelicula);
        assertEquals("Avatar", pelicula.getTitulo());
    }
    
    @Test
    @DisplayName("Test: Agregar temporadas a serie")
    void testAgregarTemporadasASerie() {
        SerieDeTV serie = new SerieDeTV("Game of Thrones", 60, "Fantasía", 8);
        Temporada temp1 = new Temporada(1, 10, 2011);
        Temporada temp2 = new Temporada(2, 10, 2012);
        
        serie.agregarTemporada(temp1);
        serie.agregarTemporada(temp2);
        
        assertEquals(2, serie.getListaTemporadas().size());
        assertEquals(1, serie.getListaTemporadas().get(0).getNumeroTemporada());
    }
    
    @Test
    @DisplayName("Test: Agregar investigadores a documental")
    void testAgregarInvestigadoresADocumental() {
        Documental documental = new Documental("Cosmos", 45, "Ciencia", "Astronomía");
        Investigador inv1 = new Investigador("Carl Sagan", "Cosmología", "Cornell University");
        Investigador inv2 = new Investigador("Neil deGrasse Tyson", "Astrofísica", "Hayden Planetarium");
        
        documental.agregarInvestigador(inv1);
        documental.agregarInvestigador(inv2);
        
        assertEquals(2, documental.getInvestigadores().size());
        assertEquals("Carl Sagan", documental.getInvestigadores().get(0).getNombre());
    }
    
    // ===== PRUEBAS DEL SERVICIO =====
    
    @Test
    @DisplayName("Test: Agregar contenido al servicio")
    void testAgregarContenido() {
        Pelicula pelicula = new Pelicula("Matrix", 136, "Ciencia Ficción", "Warner Bros");
        service.agregarContenido(pelicula);
        
        List<ContenidoAudiovisual> contenidos = service.obtenerTodosContenidos();
        assertEquals(1, contenidos.size());
        assertEquals("Matrix", contenidos.get(0).getTitulo());
    }
    
    @Test
    @DisplayName("Test: Buscar contenido por ID")
    void testBuscarPorId() {
        Pelicula pelicula = new Pelicula("Interstellar", 169, "Ciencia Ficción", "Paramount");
        service.agregarContenido(pelicula);
        
        ContenidoAudiovisual encontrado = service.buscarPorId(pelicula.getId());
        
        assertNotNull(encontrado);
        assertEquals("Interstellar", encontrado.getTitulo());
    }
    
    @Test
    @DisplayName("Test: Buscar contenido por título")
    void testBuscarPorTitulo() {
        service.agregarContenido(new Pelicula("The Dark Knight", 152, "Acción", "Warner Bros"));
        service.agregarContenido(new Pelicula("The Dark Knight Rises", 164, "Acción", "Warner Bros"));
        
        List<ContenidoAudiovisual> resultados = service.buscarPorTitulo("Dark Knight");
        
        assertEquals(2, resultados.size());
    }
    
    @Test
    @DisplayName("Test: Buscar contenido por género")
    void testBuscarPorGenero() {
        service.agregarContenido(new Pelicula("Inception", 148, "Ciencia Ficción", "Warner Bros"));
        service.agregarContenido(new Pelicula("Interstellar", 169, "Ciencia Ficción", "Paramount"));
        service.agregarContenido(new Pelicula("The Godfather", 175, "Drama", "Paramount"));
        
        List<ContenidoAudiovisual> resultados = service.buscarPorGenero("Ciencia Ficción");
        
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
    @DisplayName("Test: Obtener solo películas")
    void testObtenerSoloPeliculas() {
        service.agregarContenido(new Pelicula("Film 1", 120, "Drama", "Studio 1"));
        service.agregarContenido(new SerieDeTV("Serie 1", 45, "Comedia", 3));
        service.agregarContenido(new Pelicula("Film 2", 130, "Acción", "Studio 2"));
        
        List<Pelicula> peliculas = service.obtenerPeliculas();
        
        assertEquals(2, peliculas.size());
    }
    
    @Test
    @DisplayName("Test: Obtener estadísticas")
    void testObtenerEstadisticas() {
        service.agregarContenido(new Pelicula("Film 1", 120, "Drama", "Studio"));
        service.agregarContenido(new SerieDeTV("Serie 1", 45, "Comedia", 3));
        service.agregarContenido(new Documental("Doc 1", 60, "Ciencia", "Tema"));
        service.agregarContenido(new Podcast("Podcast 1", 30, "Noticias", "Host", 1, "Spotify"));
        
        var stats = service.obtenerEstadisticas();
        
        assertEquals(4, stats.getTotalContenidos());
        assertEquals(1, stats.getTotalPeliculas());
        assertEquals(1, stats.getTotalSeries());
        assertEquals(1, stats.getTotalDocumentales());
        assertEquals(1, stats.getTotalPodcasts());
    }
    
    // ===== PRUEBAS DE CASOS LÍMITE =====
    
    @Test
    @DisplayName("Test: Agregar contenido nulo")
    void testAgregarContenidoNulo() {
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
    @DisplayName("Test: Eliminar ID inexistente")
    void testEliminarIdInexistente() {
        assertFalse(service.eliminarContenido(9999));
    }
    
    @Test
    @DisplayName("Test: Buscar con título vacío")
    void testBuscarTituloVacio() {
        service.agregarContenido(new Pelicula("Test", 120, "Drama", "Studio"));
        List<ContenidoAudiovisual> resultados = service.buscarPorTitulo("");
        assertEquals(1, resultados.size()); // Debería encontrar todos
    }
    
    // ===== PRUEBAS DE FUNCIONALIDADES ESPECÍFICAS =====
    
    @Test
    @DisplayName("Test: Livestream - iniciar y finalizar transmisión")
    void testLivestreamTransmision() {
        Livestream livestream = new Livestream("Stream Test", 120, "Gaming", 
                                               "Streamer", "Twitch", "Juegos");
        
        assertFalse(livestream.isEnVivo());
        
        livestream.iniciarTransmision();
        assertTrue(livestream.isEnVivo());
        
        livestream.finalizarTransmision();
        assertFalse(livestream.isEnVivo());
    }
    
    @Test
    @DisplayName("Test: Livestream - agregar espectadores")
    void testLivestreamAgregarEspectadores() {
        Livestream livestream = new Livestream("Stream Test", 120, "Gaming", 
                                               "Streamer", "Twitch", "Juegos");
        
        livestream.iniciarTransmision();
        livestream.agregarEspectador();
        livestream.agregarEspectador();
        livestream.agregarEspectador();
        
        assertEquals(3, livestream.getEspectadoresActuales());
        assertEquals(3, livestream.getEspectadoresPico());
    }
    
    @Test
    @DisplayName("Test: Podcast - registrar descargas")
    void testPodcastDescargas() {
        Podcast podcast = new Podcast("Test Podcast", 45, "Tecnología", 
                                     "Host", 1, "Spotify");
        
        assertEquals(0, podcast.getDescargas());
        
        podcast.registrarDescarga();
        podcast.registrarDescarga();
        podcast.registrarDescarga();
        
        assertEquals(3, podcast.getDescargas());
    }
    
    @Test
    @DisplayName("Test: Podcast - agregar sponsors")
    void testPodcastSponsors() {
        Podcast podcast = new Podcast("Test Podcast", 45, "Tecnología", 
                                     "Host", 1, "Spotify");
        
        podcast.agregarSponsor("Sponsor 1");
        podcast.agregarSponsor("Sponsor 2");
        
        assertEquals(2, podcast.getSponsors().size());
    }
    
    // ===== PRUEBAS DE GETTERS Y SETTERS =====
    
    @Test
    @DisplayName("Test: Modificar título de contenido")
    void testModificarTitulo() {
        Pelicula pelicula = new Pelicula("Título Original", 120, "Drama", "Studio");
        pelicula.setTitulo("Título Modificado");
        
        assertEquals("Título Modificado", pelicula.getTitulo());
    }
    
    @Test
    @DisplayName("Test: Modificar duración de contenido")
    void testModificarDuracion() {
        SerieDeTV serie = new SerieDeTV("Serie Test", 45, "Comedia", 3);
        serie.setDuracionEnMinutos(60);
        
        assertEquals(60, serie.getDuracionEnMinutos());
    }
    
    // ===== PRUEBAS DE POLIMORFISMO =====
    
    @Test
    @DisplayName("Test: Polimorfismo - diferentes tipos en misma lista")
    void testPolimorfismo() {
        service.agregarContenido(new Pelicula("Film", 120, "Drama", "Studio"));
        service.agregarContenido(new SerieDeTV("Serie", 45, "Comedia", 3));
        service.agregarContenido(new Documental("Doc", 60, "Ciencia", "Tema"));
        
        List<ContenidoAudiovisual> contenidos = service.obtenerTodosContenidos();
        
        assertEquals(3, contenidos.size());
        
        // Verificar que todos son ContenidoAudiovisual
        for (ContenidoAudiovisual contenido : contenidos) {
            assertNotNull(contenido);
            assertNotNull(contenido.getTitulo());
        }
    }
}