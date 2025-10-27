import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GestorCatalogo gestor = new GestorCatalogo();
        Scanner sc = new Scanner(System.in);

        // Datos de ejemplo
        gestor.agregarPelicula(new Pelicula("P001", "Matrix", 136, "Ciencia ficción", 3));
        gestor.agregarPelicula(new Pelicula("P002", "Toy Story", 81, "Animación", 2));
        gestor.agregarPelicula(new Pelicula("P003", "El Padrino", 175, "Drama", 1));

        boolean salir = false;
        while (!salir) {
            System.out.println("--- Sistema de Renta de Películas ---");
            System.out.println("1. Mostrar catálogo");
            System.out.println("2. Agregar película");
            System.out.println("3. Eliminar película");
            System.out.println("4. Rentar película");
            System.out.println("5. Devolver película");
            System.out.println("6. Reportes");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");
            String op = sc.nextLine();
            switch (op) {
                case "1":
                    System.out.println("Catálogo:");
                    gestor.obtenerCatalogo().imprimirLista();
                    break;
                case "2":
                    System.out.print("Código: "); String codigo = sc.nextLine();
                    System.out.print("Título: "); String titulo = sc.nextLine();
                    System.out.print("Duración (min): "); int dur = Integer.parseInt(sc.nextLine());
                    System.out.print("Género: "); String gen = sc.nextLine();
                    System.out.print("Cantidad disponible: "); int cant = Integer.parseInt(sc.nextLine());
                    boolean agregado = gestor.agregarPelicula(new Pelicula(codigo, titulo, dur, gen, cant));
                    System.out.println(agregado ? "Película agregada." : "No se pudo agregar (código ya existe).");
                    break;
                case "3":
                    System.out.print("Código a eliminar: "); String codEl = sc.nextLine();
                    boolean eliminado = gestor.eliminarPelicula(codEl);
                    System.out.println(eliminado ? "Película eliminada." : "No se pudo eliminar (no existe o hay copias rentadas).");
                    break;
                case "4":
                    System.out.print("Código a rentar: "); String codR = sc.nextLine();
                    boolean rentado = gestor.rentarPelicula(codR);
                    System.out.println(rentado ? "Renta realizada." : "No se pudo rentar (no existe o no hay copias disponibles).");
                    break;
                case "5":
                    System.out.print("Código a devolver: "); String codD = sc.nextLine();
                    boolean devuelto = gestor.devolverPelicula(codD);
                    System.out.println(devuelto ? "Devolución realizada." : "No se pudo devolver (no está rentada o código incorrecto).");
                    break;
                case "6":
                    System.out.println("--- Reportes ---");
                    System.out.println("a) Películas disponibles para renta:");
                    ListaPeliculas disp = gestor.reporteDisponibles();
                    disp.imprimirLista();

                    System.out.println("b) Películas actualmente rentadas:");
                    gestor.reporteRentadas().imprimirLista();

                    System.out.println("c) Top 3 películas más rentadas:");
                    Pelicula[] mas = gestor.reporteMasRentadas(3);
                    for (int i = 0; i < mas.length; i++) System.out.println((i+1) + ". " + mas[i].toString());

                    System.out.println("d) Top 3 películas menos rentadas:");
                    Pelicula[] menos = gestor.reporteMenosRentadas(3);
                    for (int i = 0; i < menos.length; i++) System.out.println((i+1) + ". " + menos[i].toString());

                    System.out.println("e) Películas clasificadas por género:");
                    ListaGrupoGenero grupos = gestor.reportePorGenero();
                    grupos.imprimirLista();

                    break;
                case "0":
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
        sc.close();
        System.out.println("Programa finalizado.");
    }
}