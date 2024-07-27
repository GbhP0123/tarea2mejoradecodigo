import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Catedratico extends Persona implements Serializable {
    private String profesion;
    private List<Curso> cursos;

    public Catedratico(String nombre, String id, String profesion) {
        super(nombre, id);
        this.profesion = profesion;
        this.cursos = new ArrayList<>();
    }

    public String getProfesion() {
        return profesion;
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public void asignarCurso(Curso curso) {
        cursos.add(curso);
        System.out.println("Asignado al curso: " + curso.getNombre());
    }

    public void removerCurso(Curso curso) {
        cursos.remove(curso);
        System.out.println("Curso removido: " + curso.getNombre());
    }

    @Override
    public void mostrarDetalles() {
        System.out.println("Catedratico: " + getNombre() + ", ID: " + getId() + ", Profesion: " + profesion);
        System.out.println("Cursos Asignados:");
        for (Curso curso : cursos) {
            System.out.println(" - " + curso.getNombre());
        }
    }
}