import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Alumno extends Persona implements Serializable {
    private String carrera;
    private List<Curso> cursos;

    public Alumno(String nombre, String id, String carrera) {
        super(nombre, id);
        this.carrera = carrera;
        this.cursos = new ArrayList<>();
    }

    public String getCarrera() {
        return carrera;
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public void inscribirCurso(Curso curso) {
        cursos.add(curso);
        System.out.println("Inscrito en el curso: " + curso.getNombre());
    }

    public void desinscribirCurso(Curso curso) {
        cursos.remove(curso);
        System.out.println("Desinscrito del curso: " + curso.getNombre());
    }

    @Override
    public void mostrarDetalles() {
        System.out.println("Alumno: " + getNombre() + ", ID: " + getId() + ", Carrera: " + carrera);
        System.out.println("Cursos Inscritos:");
        for (Curso curso : cursos) {
            System.out.println(" - " + curso.getNombre());
        }
    }
}