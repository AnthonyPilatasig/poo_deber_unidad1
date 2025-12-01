package view;

import uni1a.*;
import model.ContenidoAudiovisualService.EstadisticasSistema;
import java.util.List;
import java.util.Scanner;


public class ContenidoAudiovisualView {
    private final Scanner scanner;
    
    public ContenidoAudiovisualView() {
        this.scanner = new Scanner(System.in);
    }
    
    public void mostrarMenuPrincipal() {
        System.out.println("\n╔════════════════════════════════════════════╗");
        System.out.println("║   SISTEMA DE CONTENIDO AUDIOVISUAL        ║");
        System.out.println("╚════════════════════════════════════════════╝");
        System.out.println("1. Agregar contenido");
        System.out.println("2. Listar todos los contenidos");
        System.out.println("3. Buscar contenido");
        System.out.println("4. Eliminar contenido");
        System.out.println("5. Ver estadísticas");
        System.out.println("6. Guardar datos");
        System.out.println("7. Cargar datos");
        System.out.println("0. Salir");
        System.out.print("\nSeleccione una opción: ");
    }
    
    public int mostrarMenuTipoContenido() {
        System.out.println("\n╔════════════════════════════════════════════╗");
        System.out.println("║   SELECCIONAR TIPO DE CONTENIDO           ║");
        System.out.println("╚════════════════════════════════════════════╝");
        System.out.println("1. Película");
        System.out.println("2. Serie de TV");
        System.out.println("3. Documental");
        System.out.println("4. Podcast");
        System.out.println("5. Livestream");
        System.out.println("0. Cancelar");
        System.out.print("\nSeleccione tipo: ");
        return leerEntero();
    }
    
    public int leerOpcion() {
        return leerEntero();
    }
    
    public int leerEntero() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    public String leerCadena(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
    
    public Pelicula solicitarDatosPelicula() {
        System.out.println("\n--- NUEVA PELÍCULA ---");
        String titulo = leerCadena("Título: ");
        int duracion = leerEnteroConPrompt("Duración (minutos): ");
        String genero = leerCadena("Género: ");
        String estudio = leerCadena("Estudio: ");
        
        Pelicula pelicula = new Pelicula(titulo, duracion, genero, estudio);
        
        System.out.print("¿Agregar actores? (s/n): ");
        if (scanner.nextLine().trim().equalsIgnoreCase("s")) {
            agregarActoresAPelicula(pelicula);
        }
        
        return pelicula;
    }
    
    public SerieDeTV solicitarDatosSerie() {
        System.out.println("\n--- NUEVA SERIE DE TV ---");
        String titulo = leerCadena("Título: ");
        int duracion = leerEnteroConPrompt("Duración por episodio (minutos): ");
        String genero = leerCadena("Género: ");
        int temporadas = leerEnteroConPrompt("Número de temporadas: ");
        
        SerieDeTV serie = new SerieDeTV(titulo, duracion, genero, temporadas);
        
        System.out.print("¿Agregar información de temporadas? (s/n): ");
        if (scanner.nextLine().trim().equalsIgnoreCase("s")) {
            agregarTemporadasASerie(serie);
        }
        
        return serie;
    }
    
    public Documental solicitarDatosDocumental() {
        System.out.println("\n--- NUEVO DOCUMENTAL ---");
        String titulo = leerCadena("Título: ");
        int duracion = leerEnteroConPrompt("Duración (minutos): ");
        String genero = leerCadena("Género: ");
        String tema = leerCadena("Tema: ");
        
        Documental documental = new Documental(titulo, duracion, genero, tema);
        
        System.out.print("¿Agregar investigadores? (s/n): ");
        if (scanner.nextLine().trim().equalsIgnoreCase("s")) {
            agregarInvestigadoresADocumental(documental);
        }
        
        return documental;
    }
    
    public Podcast solicitarDatosPodcast() {
        System.out.println("\n--- NUEVO PODCAST ---");
        String titulo = leerCadena("Título: ");
        int duracion = leerEnteroConPrompt("Duración (minutos): ");
        String genero = leerCadena("Género: ");
        String anfitrion = leerCadena("Anfitrión: ");
        int episodio = leerEnteroConPrompt("Número de episodio: ");
        String plataforma = leerCadena("Plataforma: ");
        
        return new Podcast(titulo, duracion, genero, anfitrion, episodio, plataforma);
    }
    
    public Livestream solicitarDatosLivestream() {
        System.out.println("\n--- NUEVO LIVESTREAM ---");
        String titulo = leerCadena("Título: ");
        int duracion = leerEnteroConPrompt("Duración estimada (minutos): ");
        String genero = leerCadena("Género: ");
        String streamer = leerCadena("Streamer: ");
        String plataforma = leerCadena("Plataforma: ");
        String categoria = leerCadena("Categoría: ");
        
        return new Livestream(titulo, duracion, genero, streamer, plataforma, categoria);
    }
    
    public void mostrarListaContenidos(List<ContenidoAudiovisual> contenidos) {
        if (contenidos.isEmpty()) {
            mostrarMensaje("No hay contenidos registrados.");
            return;
        }
        
        System.out.println("\n╔════════════════════════════════════════════╗");
        System.out.println("║   LISTA DE CONTENIDOS                     ║");
        System.out.println("╚════════════════════════════════════════════╝");
        
        for (ContenidoAudiovisual contenido : contenidos) {
            contenido.mostrarDetalles();
        }
    }
    
    public void mostrarEstadisticas(EstadisticasSistema stats) {
        System.out.println("\n╔════════════════════════════════════════════╗");
        System.out.println("║   ESTADÍSTICAS DEL SISTEMA                ║");
        System.out.println("╚════════════════════════════════════════════╝");
        System.out.println("Total de contenidos: " + stats.getTotalContenidos());
        System.out.println("  • Películas:       " + stats.getTotalPeliculas());
        System.out.println("  • Series:          " + stats.getTotalSeries());
        System.out.println("  • Documentales:    " + stats.getTotalDocumentales());
        System.out.println("  • Podcasts:        " + stats.getTotalPodcasts());
        System.out.println("  • Livestreams:     " + stats.getTotalLivestreams());
    }
    
    public void mostrarMensaje(String mensaje) {
        System.out.println("\n" + mensaje);
    }
    
    public void mostrarError(String error) {
        System.err.println("\n❌ ERROR: " + error);
    }
    
    public void mostrarExito(String mensaje) {
        System.out.println("\n✓ " + mensaje);
    }
    

    
    private int leerEnteroConPrompt(String prompt) {
        int valor;
        while (true) {
            System.out.print(prompt);
            valor = leerEntero();
            if (valor >= 0) {
                return valor;
            }
            mostrarError("Debe ingresar un número válido");
        }
    }
    
    private void agregarActoresAPelicula(Pelicula pelicula) {
        System.out.print("¿Cuántos actores desea agregar?: ");
        int cantidad = leerEntero();
        
        for (int i = 0; i < cantidad; i++) {
            System.out.println("\nActor " + (i + 1) + ":");
            String nombre = leerCadena("  Nombre: ");
            int edad = leerEnteroConPrompt("  Edad: ");
            String nacionalidad = leerCadena("  Nacionalidad: ");
            
            pelicula.agregarActor(new Actor(nombre, edad, nacionalidad));
        }
    }
    
    private void agregarTemporadasASerie(SerieDeTV serie) {
        System.out.print("¿Cuántas temporadas desea registrar?: ");
        int cantidad = leerEntero();
        
        for (int i = 0; i < cantidad; i++) {
            System.out.println("\nTemporada " + (i + 1) + ":");
            int numero = leerEnteroConPrompt("  Número: ");
            int episodios = leerEnteroConPrompt("  Episodios: ");
            int anio = leerEnteroConPrompt("  Año de lanzamiento: ");
            
            serie.agregarTemporada(new Temporada(numero, episodios, anio));
        }
    }
    
    private void agregarInvestigadoresADocumental(Documental documental) {
        System.out.print("¿Cuántos investigadores desea agregar?: ");
        int cantidad = leerEntero();
        
        for (int i = 0; i < cantidad; i++) {
            System.out.println("\nInvestigador " + (i + 1) + ":");
            String nombre = leerCadena("  Nombre: ");
            String especialidad = leerCadena("  Especialidad: ");
            String institucion = leerCadena("  Institución: ");
            
            documental.agregarInvestigador(
                new Investigador(nombre, especialidad, institucion)
            );
        }
    }
    
    public void cerrar() {
        scanner.close();
    }
}