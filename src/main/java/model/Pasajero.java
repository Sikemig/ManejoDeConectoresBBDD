package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pasajero {

    private int id;
    private String nombre;
    private int edad;
    private double peso;
    private int id_coche;

    // Constructor sin id que se autoincrementa

    // Los pasajeros pueden existir sin estar asignados a un coche, se crea constructor sin el id del coche para añadir
    public Pasajero(String nombre, int edad, double peso) {
        this.nombre = nombre;
        this.edad = edad;
        this.peso = peso;
    }


    public void mostrarDatos(){
        System.out.println("id= " + id);
        System.out.println("nombre= "+ nombre);
        System.out.println("edad= " + edad);
        System.out.println("peso= " + peso);
        System.out.println("id_coche= " + id_coche + "\n");
    }
}
