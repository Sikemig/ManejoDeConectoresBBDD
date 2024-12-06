package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Coche implements Serializable {

    private final static long serialVersionUID = 123L;
    private int id;
    private String matricula, marca, modelo, color;


    // Constructor sin el ID ya que eso se autoincrementa en la BBDD
    public Coche(String matricula, String marca, String modelo, String color) {
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
    }

    public void mostrarDatos(){
        System.out.println("id= " + id);
        System.out.println("matricula= "+ matricula);
        System.out.println("marca= " + marca);
        System.out.println("modelo= " + modelo);
        System.out.println("color= " + color +"\n");
    }
}
