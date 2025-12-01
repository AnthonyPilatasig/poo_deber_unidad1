package main;

import controller.ContenidoAudiovisualController;
import model.ContenidoAudiovisualService;
import view.ContenidoAudiovisualView;
import util.FileManager;


public class SistemaContenidoAudiovisual {
    
    public static void main(String[] args) {
        // Crear las dependencias 
        FileManager fileManager = new FileManager();
        ContenidoAudiovisualService service = new ContenidoAudiovisualService(fileManager);
        ContenidoAudiovisualView view = new ContenidoAudiovisualView();
        
        // Crear el controlador con sus dependencias
        ContenidoAudiovisualController controller = 
            new ContenidoAudiovisualController(service, view);
        
        // Iniciar la aplicación
        controller.iniciar();
    }
}