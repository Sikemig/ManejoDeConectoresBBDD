import dao.CocheDAO;
import dao.PasajeroDAO;
import model.Coche;
import model.Pasajero;

import java.sql.SQLException;
import java.util.Scanner;

public class Entrada {

    private static Scanner scanner = new Scanner(System.in);
    private static int opcion= 0;
    private static CocheDAO cocheDAO = new CocheDAO();
    private static PasajeroDAO pasajeroDAO = new PasajeroDAO();

    public static void main(String[] args) {
        // Menu
        do{
            mostrarMenu();

            // cada case en 1 metodo por legibilidad
            switch (opcion){
                case 1:
                    añadirCoche();
                    break;
                case 2:
                    eliminarCoche();
                    break;
                case 3:
                    consultarCoche();
                    break;
                case 4:
                    modificarCoche();
                    break;
                case 5:
                    listaCoches();
                    break;
                case 6:
                    menuPasajeros();
                    break;
            }
        } while (opcion != 7);

        System.out.println("Finalizando programa");
    }

    public static void menuPasajeros(){
        System.out.println("Bienvenido al MENU DE PASAJEROS:");
        System.out.println("1.- Para añadir un pasajero a la BBDD");
        System.out.println("2.- Para eliminar un pasajero de la BBDD");
        System.out.println("3.- Consultar datos de un pasajero por su ID");
        System.out.println("4.- Para mostrar todos los pasajeros");
        System.out.println("5.- Añadir pasajero a un coche");
        System.out.println("6.- Eliminar pasajero de un coche");
        System.out.println("7.- Consultar pasajeros de un coche por id");

        int opcion2 = scanner.nextInt();

        // como no se pide una opción de volver al menu principal, no haremos el bucle y saldrá automaticamente al menu anterior
        switch (opcion2){
            case 1:
                añadirPasajero();
                break;
            case 2:
                eliminarPasajero();
                break;
            case 3:
                consultarPasajero();
                break;
            case 4:
                listaPasajeros();
                break;
            case 5:
                ponerCocheAPasajero();
                break;
            case 6:
                quitarCocheAPasajero();
                break;
            case 7:
                listadoPasajerosDeCoche();
                break;
        }

        System.out.println("Volviendo al menu principal");
    }

    public static void listadoPasajerosDeCoche(){
        System.out.println("Inserte el ID del coche del que quiera ver los pasajeros");
        int id_coche = scanner.nextInt();

        try {
            cocheDAO.listadoPasajerosCoche(id_coche);

        } catch (SQLException e) {
            System.out.println("Fallo en la ejecución");
        }
    }

    public static void listadoCochesConPasajeros(){
        try {
            cocheDAO.listadoCochesYPasajeros();
        } catch (SQLException e) {
            System.out.println("Fallo en la ejecución");
        }
    }

    public static void quitarCocheAPasajero(){
        System.out.println("Mostrando relaciones de coches y pasajeros:");
        listadoCochesConPasajeros();

        System.out.println("Inserte el ID del pasajero que quiera modificar");
        int id_pasajero = scanner.nextInt();

        try {
            pasajeroDAO.quitarCoche(id_pasajero);
        } catch (SQLException e) {
            System.out.println("Fallo en la ejecucion, no existe el ID");
        }

    }

    public static void ponerCocheAPasajero(){
        System.out.println("Mostrando coches disponibles:");
        listaCoches();

        System.out.println("Inserte el ID del pasajero que quiera modificar");
        int id_pasajero = scanner.nextInt();

        System.out.println("Inserte el ID del coche al que le quiera añadir el pasajero");
        int id_coche = scanner.nextInt();

        try {
            pasajeroDAO.ponerCoche(id_pasajero,id_coche);
        } catch (SQLException e) {
            System.out.println("Fallo en la ejecucion, no existe el ID");
        }

    }


    public static void listaPasajeros(){
        try {
            for(Pasajero pasajero: pasajeroDAO.listadoPasajeros()){
                pasajero.mostrarDatos();
            }
        } catch (SQLException e) {
            System.out.println("Fallo en la ejecucion");
        }
    }

    public static void consultarPasajero(){
        System.out.println("Por favor, inserte el ID del pasajero a consultar");
        int id = scanner.nextInt();

        try {
            Pasajero pasajero = pasajeroDAO.getPasajero(id);
            pasajero.mostrarDatos();
        } catch (SQLException e) {
            System.out.println("Fallo en la ejecucion");
        }
    }

    public static void eliminarPasajero(){
        System.out.println("Por favor, inserte el ID del pasajero que quiere eliminar");
        int id = scanner.nextInt();

        try {
            pasajeroDAO.deletePasajero(id);
        } catch (SQLException e) {
            System.out.println("Fallo en la ejecucion");
        }
    }

    public static void añadirPasajero(){
        System.out.println("Por favor, inserte nombre de pasajero");
        String nombre = scanner.next();

        System.out.println("Por favor, inserte edad");
        int edad = scanner.nextInt();

        System.out.println("Por favor, inserte peso");
        double peso = scanner.nextDouble();


        try {
            if(!pasajeroDAO.addPasajero(new Pasajero(nombre,edad,peso))){
                System.out.println("Pasajero añadido correctamente");
            } else {
                System.out.println("No se ha podido añadir el pasajero");
            }
        } catch (SQLException e) {
            System.out.println("Fallo en la ejecución");
        }
    }

    public static void listaCoches(){
        try {
            for(Coche coche: cocheDAO.listadoCoches()){
                coche.mostrarDatos();
            }
        } catch (SQLException e) {
            System.out.println("Fallo en la ejecucion");
        }
    }


    public static void modificarCoche(){
        System.out.println("Inserte el ID del vehiculo a modificar");
        int id = scanner.nextInt();

        System.out.println("Por favor, inserte nueva matrícula");
        String matricula = scanner.next();

        System.out.println("Por favor, inserte nueva marca");
        String marca = scanner.next();

        System.out.println("Por favor, inserte nuevo modelo");
        String modelo = scanner.next();

        System.out.println("Por favor, inserte nuevo color");
        String color = scanner.next();

        try {
            cocheDAO.updateCoche(id,matricula,marca,modelo,color);
        } catch (SQLException e) {
            System.out.println("Fallo en la ejecucion");
        }
    }

    public static void consultarCoche(){
        System.out.println("Por favor, inserte el ID del coche a consultar");
        int id = scanner.nextInt();

        try {
            Coche coche = cocheDAO.getCoche(id);
            coche.mostrarDatos();
        } catch (SQLException e) {
            System.out.println("Fallo en la ejecucion");
        }
    }

    public static void eliminarCoche(){
        System.out.println("Por favor, inserte el ID del coche que quiere eliminar");
        int id = scanner.nextInt();

        try {
            cocheDAO.deleteCoche(id);
        } catch (SQLException e) {
            System.out.println("Fallo en la ejecucion");
        }
    }

    public static void añadirCoche(){
        System.out.println("Por favor, inserte matrícula");
        String matricula = scanner.next();

        System.out.println("Por favor, inserte marca");
        String marca = scanner.next();

        System.out.println("Por favor, inserte modelo");
        String modelo = scanner.next();

        System.out.println("Por favor, inserte color");
        String color = scanner.next();

        try {
            if(!cocheDAO.addCoche(new Coche(matricula,marca,modelo,color))){
                System.out.println("Coche añadido correctamente");
            } else {
                System.out.println("No se ha podido añadir el coche");
            }
        } catch (SQLException e) {
            System.out.println("Fallo en la ejecución");
        }
    }

    public static void mostrarMenu(){
        System.out.println("Por favor, elija una de las opciones siguientes:");
        System.out.println("1.- Para añadir un coche nuevo");
        System.out.println("2.- Para eliminar un coche de la BBDD");
        System.out.println("3.- Consultar datos de un coche por su ID");
        System.out.println("4.- Modificar datos de un coche por su ID");
        System.out.println("5.- Para mostrar todos los coches");
        System.out.println("6.- MENU DE PASAJEROS");
        System.out.println("7.- SALIR");

        opcion = scanner.nextInt();
    }
}
