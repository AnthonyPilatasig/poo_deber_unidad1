package poo;
import uni1a.*;
import java.util.Arrays;

public class PruebaAudioVisual {
    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE CONTENIDO AUDIOVISUAL COMPLETO ===\n");

        // Crear instancias  las subclases
        ContenidoAudiovisual[] contenidos = new ContenidoAudiovisual[5];
        
        // 1. Película con actores
        Pelicula pelicula = new Pelicula("Avatar", 162, "Ciencia Ficción", "20th Century Studios");
        pelicula.agregarActor(new Actor("Sam Worthington", 47, "Australiano"));
        pelicula.agregarActor(new Actor("Zoe Saldana", 45, "Estadounidense"));
        contenidos[0] = pelicula;

        // 2. Serie de TV con temporadas
        SerieDeTV serie = new SerieDeTV("Game of Thrones", 60, "Fantasia", 8);
        serie.agregarTemporada(new Temporada(1, 10, 2011));
        serie.agregarTemporada(new Temporada(2, 10, 2012));
        contenidos[1] = serie;

        // 3. Documental con investigadores
        Documental documental = new Documental("Cosmos", 45, "Ciencia", "Astronomía");
        documental.agregarInvestigador(new Investigador("Neil deGrasse Tyson", "Astrofísica", "Planetario Hayden"));
        documental.agregarInvestigador(new Investigador("Carl Sagan", "Cosmología", "Universidad de Cornell"));
        contenidos[2] = documental;

        // 4. Podcast
        Podcast podcast = new Podcast("Ciencia vs", 45, "Ciencia", "Alex Fernández", 150, "Spotify");
        podcast.agregarSponsor("NordVPN");
        podcast.agregarSponsor("Audible");
        podcast.registrarDescarga();
        podcast.setExclusivo(true);
        contenidos[3] = podcast;

        // 5. Livestream
        Livestream livestream = new Livestream("Programación en Vivo", 120, "Educación", 
                                              "TechMaster", "Twitch", "Programación");
        livestream.agregarModerador("Anthony");
        livestream.agregarModerador("Franklin");
        livestream.recibirDonacion(50.0);
        livestream.iniciarTransmision();
        livestream.agregarEspectador();
        livestream.agregarEspectador();
        contenidos[4] = livestream;

        // Mostrar los detalles de cada contenido audiovisual
        System.out.println("=== MOSTRANDO DETALLES DE TODOS LOS CONTENIDOS ===");
        for (ContenidoAudiovisual contenido : contenidos) {
            contenido.mostrarDetalles();
        }

        // Probar métodos específicos de cada clase
        System.out.println("=== PROBANDO FUNCIONALIDADES ESPECÍFICAS ===");
        
        // Probar Podcast
        System.out.println("Probando Podcast:");
        podcast.reproducir();
        System.out.println("Descargas: " + podcast.getDescargas());
        System.out.println();

        // Probar Livestream
        System.out.println("Probando Livestream:");
        System.out.println("Espectadores actuales: " + livestream.getEspectadoresActuales());
        System.out.println("Espectadores pico: " + livestream.getEspectadoresPico());
        System.out.println("Donaciones totales: $" + livestream.getDonacionesTotales());
        livestream.finalizarTransmision();
        System.out.println();

        // Probar relaciones entre objetos
        System.out.println("=== VERIFICANDO RELACIONES ENTRE CLASES ===");
        
        // Verificar actores en película
        System.out.println("Actores en " + pelicula.getTitulo() + ":");
        for (Actor actor : Arrays.asList(new Actor("Sam Worthington", 47, "Australiano"), 
                                       new Actor("Zoe Saldana", 45, "Estadounidense"))) {
            System.out.println(" - " + actor);
        }
        
        // Verificar temporadas en serie
        System.out.println("\nTemporadas en " + serie.getTitulo() + ":");
        for (Temporada temp : serie.getListaTemporadas()) {
            System.out.println(" - " + temp);
        }

        // Probar getters y setters
        System.out.println("\n=== PROBANDO GETTERS Y SETTERS ===");
        pelicula.setTitulo("Avatar: The Way of Water");
        pelicula.setDuracionEnMinutos(192);
        System.out.println("Película actualizada: " + pelicula.getTitulo() + 
                          " (" + pelicula.getDuracionEnMinutos() + " mins)");

        // Probar polimorfismo
        System.out.println("\n=== DEMOSTRACIÓN DE POLIMORFISMO ===");
        demostrarPolimorfismo(contenidos);
    }

    public static void demostrarPolimorfismo(ContenidoAudiovisual[] contenidos) {
        System.out.println("Usando polimorfismo - todos responden a mostrarDetalles():");
        for (ContenidoAudiovisual contenido : contenidos) {
            contenido.mostrarDetalles(); // Cada objeto usa su propia implementación
        }
    }
}