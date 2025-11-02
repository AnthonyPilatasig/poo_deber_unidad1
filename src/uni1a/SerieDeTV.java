/**
 * Class SerieDeTV
 */
package uni1a;
import java.util.ArrayList;
import java.util.List;

// Subclase SerieDeTV que extiende de ContenidoAudiovisual
public class SerieDeTV extends ContenidoAudiovisual {
    private int temporadas;
    public List<Temporada> listaTemporadas;
    

    public SerieDeTV(String titulo, int duracionEnMinutos, String genero, int temporadas) {
        super(titulo, duracionEnMinutos, genero);
        this.temporadas = temporadas;
        this.listaTemporadas = new ArrayList<>();
    }

    public int getTemporadas() {
        return temporadas;
    }

    public void setTemporadas(int temporadas) {
        this.temporadas = temporadas;
    }
    public void agregarTemporada(Temporada temporada) {
        this.listaTemporadas.add(temporada);
    }
    
    public List<Temporada> getListaTemporadas() {
        return listaTemporadas;
    }
    
    @Override
    public void mostrarDetalles() {
        System.out.println("Detalles de la película:");
        System.out.println("ID: " + getId());
        System.out.println("Título: " + getTitulo());
        System.out.println("Duración en minutos: " + getDuracionEnMinutos());
        System.out.println("Género: " + getGenero());
        System.out.println("Temporadas: " + this.temporadas);
        if(!listaTemporadas.isEmpty()) {
        	System.out.println("Detalle de temporadas:");
        	for (Temporada  temp: listaTemporadas) {
        		System.out.println(" -"+ temp);
        	}
        }
        System.out.println();
    }
}