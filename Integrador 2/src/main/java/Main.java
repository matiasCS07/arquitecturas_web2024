import Entidades.Carrera;
import Entidades.Estudiante;
import Servicios.CarreraSer;
import Servicios.EstudianteSer;
import Servicios.InscripcionSer;
import javax.persistence.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Example");
        EstudianteSer estudianteService = new EstudianteSer();
        CarreraSer carreraService = new CarreraSer(emf);
        InscripcionSer inscripcionService = new InscripcionSer();

        // Crear carreras
        Carrera carrera1 = new Carrera();
        carrera1.setNombre("Ingeniería de Software");
        carreraService.createCarrera(carrera1);

        Carrera carrera2 = new Carrera();
        carrera2.setNombre("Ingeniería Civil");
        carreraService.createCarrera(carrera2);

        // Crear estudiantes
        Estudiante estudiante1 = new Estudiante();
        estudiante1.setNombre("Juan");
        estudiante1.setApellido("Pérez");
        estudiante1.setEdad(22);
        estudiante1.setGenero("Masculino");
        estudiante1.setDocumento(12345678);
        estudiante1.setCiudadResidencia("Buenos Aires");
        estudiante1.setLibretaUniversitaria(20230001);
        estudianteService.insertEstudiante(estudiante1);

        Estudiante estudiante2 = new Estudiante();
        estudiante2.setNombre("Ana");
        estudiante2.setApellido("García");
        estudiante2.setEdad(23);
        estudiante2.setGenero("Femenino");
        estudiante2.setDocumento(87654321);
        estudiante2.setCiudadResidencia("Córdoba");
        estudiante2.setLibretaUniversitaria(20230002);
        estudianteService.insertEstudiante(estudiante2);

        // Inscribir estudiantes en carreras
        inscripcionService.matricularEstudiante(estudiante1, carrera1, 1);
        inscripcionService.matricularEstudiante(estudiante2, carrera2, 2);

        // Mostrar estudiantes ordenados por edad
        List<Estudiante> estudiantesPorEdad = estudianteService.getEstudiantesByEdad();
        estudiantesPorEdad.forEach((e) -> {
            System.out.println("Estudiante: " + e.getNombre() + " " + e.getApellido() + " (" + e.getEdad() + ")");
        });

        // Buscar estudiante por número de libreta
        Estudiante estudianteRecuperado = estudianteService.getEstudianteByLibreta(20230001);
        System.out.println("Estudiante recuperado: " + estudianteRecuperado.getNombre() + " " + estudianteRecuperado.getApellido());

        // Buscar estudiantes por género
        List<Estudiante> estudiantesPorGenero = estudianteService.getEstudianteByGenero("Masculino");
        System.out.println("Estudiantes masculinos:");
        estudiantesPorGenero.forEach((e) -> {
            System.out.println(e.getNombre() + " " + e.getApellido());
        });

        // Mostrar carreras con cantidad de inscriptos
        List<Object[]> carrerasConInscriptos = carreraService.getCarrerasByInscriptos();
        carrerasConInscriptos.forEach((result) -> {
            System.out.println("Carrera: " + result[0] + ", Cantidad de inscriptos: " + result[1]);
        });

        System.out.println("\nReporte de Carreras con inscriptos y egresados por año:");
        List<Object[]> reporteCarreras = inscripcionService.getReporteCarreras();
        reporteCarreras.forEach((fila) -> {
            String nombreCarrera = (String) fila[0];
            int antiguedad = (int) fila[1];
            long egresados = (long) fila[2];
            long inscriptos = (long) fila[3];
            System.out.println("Carrera: " + nombreCarrera + ", Año: " + antiguedad +
                    ", Inscriptos: " + inscriptos + ", Egresados: " + egresados);
        });

        emf.close();
    }
}
