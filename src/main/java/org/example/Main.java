package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        int opcion = 0;
   do{
       menu();
       opcion = teclado.nextInt();
       switch (opcion){
           case 1:
               RegistrarAlumno();
               break;
           case 2:
               VerAlumnos();
               break;
           case 3:
               EditarAlumno();
               break;
           case 4:
               VerCantidad();
               break;
           case 5:
               EliminarAlumno();
               break;
           case 6:
               System.out.println("Terminando programa.");
               break;
           default:
               System.out.println("Opción no válida.");
       }
   }while (opcion != 5);
    }

    public static void menu(){
        System.out.println("--------------MENÚ------------");
        System.out.println("Seleccione una opción:");
        System.out.println("1) Registrar nuevo alumno.");
        System.out.println("2) Ver todos los alumnos.");
        System.out.println("3) Editar datos del alumno por matrícula.");
        System.out.println("4) Ver cantidad de alumnos hombres y mujeres.");
        System.out.println("5) Eliminar alumno por matrícula.");
        System.out.println("6) Salir.");
    }
    public static void RegistrarAlumno(){

    }
    public static void VerAlumnos(){

    }
    public static void EditarAlumno(){

    }
    public static void VerCantidad(){

    }
    public static void EliminarAlumno(){

    }


}