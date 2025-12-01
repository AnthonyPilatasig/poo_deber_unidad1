package controller;

import model.ContenidoAudiovisualService;
import view.ContenidoAudiovisualView;
import uni1a.*;
import java.io.IOException;
import java.util.List;


public class ContenidoAudiovisualController {
    private final ContenidoAudiovisualService service;
    private final ContenidoAudiovisualView view;
    private boolean ejecutando;
    
    public ContenidoAudiovisualController(ContenidoAudiovisualService service, 
                                         ContenidoAudiovisualView view) {
        this.service = service;
        this.view = view;
        this.ejecutando = true;
    }
    
    public void iniciar() {
        view.mostrarMensaje("Bienvenido al Sistema de Contenido Audiovisual");
        
        while (ejecutando) {
            view.mostrarMenuPrincipal();
            int opcion = view.leerOpcion();
            procesarOpcion(opcion);
        }
        
        view.cerrar();
        view.mostrarMensaje("¡Hasta pronto!");
    }
    
    private void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1: agregarContenido(); break;
            case 2: listarContenidos(); break;
            case 3: buscarContenido(); break;
            case 4: eliminarContenido(); break;
            case 5: mostrarEstadisticas(); break;
            case 6: guardarDatos(); break;
            case 7: cargarDatos(); break;
            case 0: ejecutando = false; break;
            default: view.mostrarError("Opción inválida");
        }
    }
    
    private void agregarContenido() {
        int tipo = view.mostrarMenuTipoContenido();
        
        try {
            ContenidoAudiovisual contenido = crearContenidoSegunTipo(tipo);
            
            if (contenido != null) {
                service.agregarContenido(contenido);
                view.mostrarExito("Contenido agregado exitosamente (ID: " + 
                                 contenido.getId() + ")");
            }
        } catch (Exception e) {
            view.mostrarError("Error al agregar contenido: " + e.getMessage());
        }
    }
    
    private ContenidoAudiovisual crearContenidoSegunTipo(int tipo) {
        switch (tipo) {
            case 1: return view.solicitarDatosPelicula();
            case 2: return view.solicitarDatosSerie();
            case 3: return view.solicitarDatosDocumental();
            case 4: return view.solicitarDatosPodcast();
            case 5: return view.solicitarDatosLivestream();
            case 0: 
                view.mostrarMensaje("Operación cancelada");
                return null;
            default:
                view.mostrarError("Tipo de contenido inválido");
                return null;
        }
    }
    
    private void listarContenidos() {
        List<ContenidoAudiovisual> contenidos = service.obtenerTodosContenidos();
        view.mostrarListaContenidos(contenidos);
    }
    
    private void buscarContenido() {
        view.mostrarMensaje("\n--- BÚSQUEDA DE CONTENIDO ---");
        view.mostrarMensaje("1. Buscar por ID");
        view.mostrarMensaje("2. Buscar por título");
        view.mostrarMensaje("3. Buscar por género");
        view.mostrarMensaje("0. Cancelar");
        
        int opcion = view.leerOpcion();
        
        switch (opcion) {
            case 1: buscarPorId(); break;
            case 2: buscarPorTitulo(); break;
            case 3: buscarPorGenero(); break;
            case 0: view.mostrarMensaje("Búsqueda cancelada"); break;
            default: view.mostrarError("Opción inválida");
        }
    }
    
    private void buscarPorId() {
        view.mostrarMensaje("Ingrese el ID:");
        int id = view.leerEntero();
        ContenidoAudiovisual contenido = service.buscarPorId(id);
        
        if (contenido != null) {
            contenido.mostrarDetalles();
        } else {
            view.mostrarError("No se encontró contenido con ID: " + id);
        }
    }
    
    private void buscarPorTitulo() {
        String titulo = view.leerCadena("Ingrese el título (o parte de él): ");
        List<ContenidoAudiovisual> resultados = service.buscarPorTitulo(titulo);
        
        if (!resultados.isEmpty()) {
            view.mostrarListaContenidos(resultados);
        } else {
            view.mostrarError("No se encontraron contenidos con ese título");
        }
    }
    
    private void buscarPorGenero() {
        String genero = view.leerCadena("Ingrese el género: ");
        List<ContenidoAudiovisual> resultados = service.buscarPorGenero(genero);
        
        if (!resultados.isEmpty()) {
            view.mostrarListaContenidos(resultados);
        } else {
            view.mostrarError("No se encontraron contenidos de ese género");
        }
    }
    
    private void eliminarContenido() {
        view.mostrarMensaje("Ingrese el ID del contenido a eliminar:");
        int id = view.leerEntero();
        
        ContenidoAudiovisual contenido = service.buscarPorId(id);
        if (contenido != null) {
            contenido.mostrarDetalles();
            String confirmacion = view.leerCadena("¿Confirma eliminación? (s/n): ");
            
            if (confirmacion.equalsIgnoreCase("s")) {
                if (service.eliminarContenido(id)) {
                    view.mostrarExito("Contenido eliminado exitosamente");
                } else {
                    view.mostrarError("No se pudo eliminar el contenido");
                }
            } else {
                view.mostrarMensaje("Eliminación cancelada");
            }
        } else {
            view.mostrarError("No se encontró contenido con ID: " + id);
        }
    }
    
    private void mostrarEstadisticas() {
        view.mostrarEstadisticas(service.obtenerEstadisticas());
    }
    
    private void guardarDatos() {
        try {
            service.guardarEnArchivos();
            view.mostrarExito("Datos guardados exitosamente en archivos CSV");
        } catch (IOException e) {
            view.mostrarError("Error al guardar datos: " + e.getMessage());
        }
    }
    
    private void cargarDatos() {
        try {
            String confirmacion = view.leerCadena(
                "¿Desea cargar datos? Esto reemplazará los datos actuales (s/n): ");
            
            if (confirmacion.equalsIgnoreCase("s")) {
                service.cargarDesdeArchivos();
                view.mostrarExito("Datos cargados exitosamente desde archivos CSV");
                mostrarEstadisticas();
            } else {
                view.mostrarMensaje("Carga de datos cancelada");
            }
        } catch (IOException e) {
            view.mostrarError("Error al cargar datos: " + e.getMessage());
        }
    }
}