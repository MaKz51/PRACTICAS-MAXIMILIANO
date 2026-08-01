package org.example;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    static Estacionamiento estacionamiento = new Estacionamiento();
    static Scanner teclado = new Scanner(System.in);

    static String url = "jdbc:sqlserver://localhost\\SQLEXPRESS;database=ESTACIONAMIENTO_POO";

    public static void main(String[] args) {


    }
}