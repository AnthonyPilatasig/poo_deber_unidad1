package uni1a;

public class Investigador {
    private String nombre;
    private String especialidad;
    private String institucion;
    
    public Investigador(String nombre, String especialidad, String institucion) {
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.institucion = institucion;
    }
    
    // getters y setters de la clase investigador
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getEspecialidad() {
        return especialidad;
    }
    
    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
    
    public String getInstitucion() {
        return institucion;
    }
    
    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }
    
    @Override
    public String toString() {
        return nombre + " - " + especialidad + " (" + institucion + ")";
    }
}