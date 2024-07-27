import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

public class Menu {
    private List<Alumno> alumnos;
    private List<Catedratico> catedraticos;
    private List<Curso> cursos;

    public Menu() {
        alumnos = new ArrayList<>();
        catedraticos = new ArrayList<>();
        cursos = new ArrayList<>();
        cargarDatos();
    }

    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("----- Menú Principal -----");
            System.out.println("1. Registrar Alumno");
            System.out.println("2. Registrar Catedrático");
            System.out.println("3. Inscribir Alumno en Curso");
            System.out.println("4. Asignar Curso a Catedrático");
            System.out.println("5. Mostrar Alumnos");
            System.out.println("6. Mostrar Catedráticos");
            System.out.println("7. Mostrar Cursos");
            System.out.println("8. Desinscribir Alumno de Curso");
            System.out.println("9. Remover Curso de Catedrático");
            System.out.println("10. Buscar Alumno por ID");
            System.out.println("11. Buscar Catedrático por ID");
            System.out.println("12. Actualizar Alumno");
            System.out.println("13. Actualizar Catedrático");
            System.out.println("14. Eliminar Alumno");
            System.out.println("15. Eliminar Catedrático");
            System.out.println("16. Salir");
            System.out.print("Elige una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    registrarPersona(alumnos, "Alumno", scanner, id -> new Alumno("", id, ""));
                    break;
                case 2:
                    registrarPersona(catedraticos, "Catedrático", scanner, id -> new Catedratico("", id, ""));
                    break;
                case 3:
                    inscribirAlumnoEnCurso(scanner);
                    break;
                case 4:
                    asignarCursoACatedratico(scanner);
                    break;
                case 5:
                    mostrarPersonas(alumnos, "Alumnos");
                    break;
                case 6:
                    mostrarPersonas(catedraticos, "Catedráticos");
                    break;
                case 7:
                    mostrarCursos();
                    break;
                case 8:
                    desinscribirAlumnoDeCurso(scanner);
                    break;
                case 9:
                    removerCursoDeCatedratico(scanner);
                    break;
                case 10:
                    buscarPersonaPorId(alumnos, "Alumno", scanner);
                    break;
                case 11:
                    buscarPersonaPorId(catedraticos, "Catedrático", scanner);
                    break;
                case 12:
                    actualizarPersona(alumnos, "Alumno", scanner);
                    break;
                case 13:
                    actualizarPersona(catedraticos, "Catedrático", scanner);
                    break;
                case 14:
                    eliminarPersona(alumnos, "Alumno", scanner);
                    break;
                case 15:
                    eliminarPersona(catedraticos, "Catedrático", scanner);
                    break;
                case 16:
                    System.out.println("Saliendo del programa...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }
        }
    }

    private void mostrarCursos() {

    }

    private <T extends Persona> void registrarPersona(List<T> lista, String tipo, Scanner scanner, Function<String, T> constructor) {
        System.out.print("Nombre del " + tipo + ": ");
        String nombre = scanner.nextLine();
        System.out.print("ID del " + tipo + ": ");
        String id = scanner.nextLine();
        System.out.print("Detalles adicionales: ");
        String detalle = scanner.nextLine();  // Adaptar según tipo

        T persona = constructor.apply(id);
        persona.setNombre(nombre);
        // Configurar otros detalles específicos del tipo
        lista.add(persona);
        System.out.println(tipo + " registrado exitosamente.\n");
    }

    private <T extends Persona> void mostrarPersonas(List<T> lista, String tipo) {
        if (lista.isEmpty()) {
            System.out.println("No hay " + tipo.toLowerCase() + " registrados.\n");
        } else {
            System.out.println("----- Lista de " + tipo + " -----");
            for (T persona : lista) {
                persona.mostrarDetalles();
                System.out.println();
            }
        }
    }

    private <T extends Persona> void buscarPersonaPorId(List<T> lista, String tipo, Scanner scanner) {
        System.out.print("Introduce el ID del " + tipo + " a buscar: ");
        String id = scanner.nextLine();

        for (T persona : lista) {
            if (persona.getId().equals(id)) {
                persona.mostrarDetalles();
                return;
            }
        }
        System.out.println(tipo + " no encontrado.\n");
    }

    private <T extends Persona> void actualizarPersona(List<T> lista, String tipo, Scanner scanner) {
        if (lista.isEmpty()) {
            System.out.println("No hay " + tipo.toLowerCase() + " registrados.\n");
            return;
        }
        System.out.println("Lista de " + tipo + ":");
        for (int i = 0; i < lista.size(); i++) {
            System.out.println(i + 1 + ". " + lista.get(i).getNombre());
        }
        System.out.print("Elige el número del " + tipo + " a actualizar: ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine();

        T persona = lista.get(index);
        System.out.print("Nuevo nombre: ");
        String nombre = scanner.nextLine();
        // Obtener y actualizar otros detalles específicos del tipo
        persona.setNombre(nombre);
        System.out.println(tipo + " actualizado exitosamente.\n");
    }

    private <T extends Persona> void eliminarPersona(List<T> lista, String tipo, Scanner scanner) {
        if (lista.isEmpty()) {
            System.out.println("No hay " + tipo.toLowerCase() + " registrados.\n");
            return;
        }
        System.out.println("Lista de " + tipo + ":");
        for (int i = 0; i < lista.size(); i++) {
            System.out.println(i + 1 + ". " + lista.get(i).getNombre());
        }
        System.out.print("Elige el número del " + tipo + " a eliminar: ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine();

        lista.remove(index);
        System.out.println(tipo + " eliminado exitosamente.\n");
    }

    private void inscribirAlumnoEnCurso(Scanner scanner) {
        if (alumnos.isEmpty()) {
            System.out.println("No hay alumnos registrados.\n");
            return;
        }
        Alumno alumno = seleccionarElemento(alumnos, "Lista de Alumnos");
        System.out.print("Nombre del Curso: ");
        String nombreCurso = scanner.nextLine();
        System.out.print("Código del Curso: ");
        String codigoCurso = scanner.nextLine();

        Curso curso = new Curso(nombreCurso, codigoCurso);
        alumno.inscribirCurso(curso);
        cursos.add(curso);
    }

    private void asignarCursoACatedratico(Scanner scanner) {
        if (catedraticos.isEmpty()) {
            System.out.println("No hay catedráticos registrados.\n");
            return;
        }
        Catedratico catedratico = seleccionarElemento(catedraticos, "Lista de Catedráticos");
        System.out.print("Nombre del Curso: ");
        String nombreCurso = scanner.nextLine();
        System.out.print("Código del Curso: ");
        String codigoCurso = scanner.nextLine();

        Curso curso = new Curso(nombreCurso, codigoCurso);
        catedratico.asignarCurso(curso);
        cursos.add(curso);
    }

    private void desinscribirAlumnoDeCurso(Scanner scanner) {
        if (alumnos.isEmpty()) {
            System.out.println("No hay alumnos registrados.\n");
            return;
        }
        Alumno alumno = seleccionarElemento(alumnos, "Lista de Alumnos");
        if (alumno.getCursos().isEmpty()) {
            System.out.println("El alumno no está inscrito en ningún curso.\n");
            return;
        }

        Curso curso = seleccionarElemento(alumno.getCursos(), "Cursos Inscritos");
        alumno.desinscribirCurso(curso);
    }

    private void removerCursoDeCatedratico(Scanner scanner) {
        if (catedraticos.isEmpty()) {
            System.out.println("No hay catedráticos registrados.\n");
            return;
        }
        Catedratico catedratico = seleccionarElemento(catedraticos, "Lista de Catedráticos");
        if (catedratico.getCursos().isEmpty()) {
            System.out.println("El catedrático no tiene cursos asignados.\n");
            return;
        }

        Curso curso = seleccionarElemento(catedratico.getCursos(), "Cursos Asignados");
        catedratico.removerCurso(curso);
    }

    private <T> T seleccionarElemento(List<T> lista, String mensaje) {
        System.out.println(mensaje);
        for (int i = 0; i < lista.size(); i++) {
            System.out.println((i + 1) + ". " + lista.get(i).toString());
        }
        Scanner scanner = new Scanner(System.in);
        System.out.print("Elige el número: ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine();
        return lista.get(index);
    }

    private void cargarDatos() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("datos.ser"))) {
            alumnos = (List<Alumno>) ois.readObject();
            catedraticos = (List<Catedratico>) ois.readObject();
            cursos = (List<Curso>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No se pudo cargar los datos. " + e.getMessage());
        }
    }


}