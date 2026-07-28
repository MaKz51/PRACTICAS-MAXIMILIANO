package org.example;

import java.sql.*;
import java.util.Scanner;
public class Main {
     public static Scanner teclado = new Scanner(System.in);

    static String url = "jdbc:sqlserver://localhost\\SQLEXPRESS;database=ESCUELA_POO;user=sa;password=Maxmi2017Th;encrypt=true;trustServerCertificate=true;";    static Connection conexion;

    public static void main(String[] args) throws SQLException {
        conexion = DriverManager.getConnection(url);

        int opcion = 0;
   do{
       menu();
       opcion = teclado.nextInt();
       teclado.nextLine();

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
   }while (opcion != 6);

   conexion.close();

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
    public static void RegistrarAlumno() throws SQLException {
        System.out.println("-REGISTRAR NUEVO ALUMNO-");
        System.out.println("Matricula:");
        String matricula = teclado.nextLine();

        System.out.println("Nombre completo:");
        String nombre = teclado.nextLine();

        System.out.println("Edad:");
        int edad = teclado.nextInt();
        teclado.nextLine();

        System.out.println("Sexo (Masculino / Femenino):");
        String sexo = teclado.nextLine();

        System.out.println("Correo: ");
        String correo = teclado.nextLine();

        String sql = "INSERT INTO alumnos (matricula, nombre, edad, sexo, correo) VALUES (?, ?, ?, ?, ?)";

        PreparedStatement comando = conexion.prepareStatement(sql);

        comando.setString(1, matricula);
        comando.setString(2, nombre);
        comando.setInt(3, edad);
        comando.setString(4, sexo);
        comando.setString(5, correo);

        comando.executeUpdate();

        System.out.println("Alumno registrado!");

    }
    public static void VerAlumnos() throws SQLException {
        System.out.println("-LISTA DE ALUMNOS REGISTRADOS-");
        String sql = "SELECT * FROM alumnos";

        PreparedStatement comando = conexion.prepareStatement(sql);

        ResultSet resultados = comando.executeQuery();

        System.out.println("id | matrícula | nombre | edad | sexo | correo");
        System.out.println("----------------------------------------------");
        while (resultados.next()){
            int id = resultados.getInt("id");
            String matricula = resultados.getString("matricula");
            String nombre = resultados.getString("nombre");
            int edad = resultados.getInt("edad");
            String sexo = resultados.getString("sexo");
            String correo = resultados.getString("correo");

            System.out.println(id + " | " + matricula + " | " + nombre + " | " + edad + " | " + sexo + " | " + correo);

        }
        System.out.println("----------------------------------------------");
    }
    public static void EditarAlumno() throws SQLException {
        System.out.println("-MODIFICAR DATOS DE ALUMNO-");
        System.out.println("Ingresa la matrícula del alumno a modificar: ");
        String matriculaBuscada = teclado.nextLine();

        System.out.println("Escriba el nuevo nombre: ");
        String nuevoNombre = teclado.nextLine();

        System.out.println("Escriba la nueva edad: ");
        int nuevaEdad = teclado.nextInt();
        teclado.nextLine();

        System.out.println("Escirba el nuevo sexo: ");
        String nuevoSexo = teclado.nextLine();

        System.out.println("Escirba el nuevo correo: ");
        String nuevoCorreo = teclado.nextLine();

        String sql = "UPDATE alumnos SET nombre = ?, edad = ?, sexo = ?, correo = ? WHERE matricula = ?";

        PreparedStatement comando = conexion.prepareStatement(sql);
        comando.setString(1, nuevoNombre);
        comando.setInt(2, nuevaEdad);
        comando.setString(3, nuevoSexo);
        comando.setString(4, nuevoCorreo);
        comando.setString(5, matriculaBuscada);


        int filasAfectadas = comando.executeUpdate();

        if (filasAfectadas > 0){
            System.out.println("Datos actualizados con éxito.");
        }
        else {
            System.out.println("No se encontró ningun con esa matrícula.");
        }
    }
    public static void VerCantidad() throws SQLException {

    }
    public static void EliminarAlumno() throws SQLException {

    }

}