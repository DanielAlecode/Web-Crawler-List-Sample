package com.mycompany.listschrunk;

import com.mycompany.listschrunk.impl.ListaEnlazada;
import java.util.Scanner;

public class ListsChrunk {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ListaEnlazada<String> tareas = new ListaEnlazada<>();
        int opcion;

        do {
            System.out.println("\n******************* SISTEMA PARA LA GESTION DE TAREAS ******************");
            System.out.println("1. Agregar tarea al inicio");
            System.out.println("2. Agregar tarea al final");
            System.out.println("3. Insertar tarea en posición específica");
            System.out.println("4. Eliminar tarea por nombre");
            System.out.println("5. Mostrar todas las tareas");
            System.out.println("6. Ver primera tarea");
            System.out.println("7. Ver última tarea");
            System.out.println("8. Ordenar tareas");
            System.out.println("9. Consultar si existe una tarea por nombre");
            System.out.println("10. Limpiar lista");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> {
                    System.out.print("Ingrese la tarea: ");
                    String tarea = scanner.nextLine();
                    tareas.agregarInicio(tarea);
                    System.out.println("Tarea agregada al inicio.");
                }
                case 2 -> {
                    System.out.print("Ingrese la tarea: ");
                    String tarea = scanner.nextLine();
                    tareas.agregarFinal(tarea);
                    System.out.println("✅Tarea agregada al final.");
                }
                case 3 -> {
                    System.out.print("Ingrese la posición (comienza en 0): ");
                    int posicion = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Ingrese la tarea: ");
                    String tarea = scanner.nextLine();
                    try {
                        tareas.insertarEn(posicion, tarea);
                        System.out.println("✅ Tarea insertada.");
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("osición inválida.");
                    }
                }
                case 4 -> {
                    System.out.print("Ingrese el nombre de la tarea a eliminar: ");
                    String tarea = scanner.nextLine();
                    boolean eliminada = tareas.eliminar(tarea);
                    if (eliminada) {
                        System.out.println("✅ Tarea eliminada.");
                    } else {
                        System.out.println("Tarea no encontrada.");
                    }
                }
                case 5 -> tareas.listar();
                case 6 -> {
                    try {
                        System.out.println("📌 Primera tarea: " + tareas.obtenerPrimero());
                    } catch (Exception e) {
                        System.out.println("Lista vacía.");
                    }
                }
                case 7 -> {
                    try {
                        System.out.println("📌Última tarea: " + tareas.obtenerUltimo());
                    } catch (Exception e) {
                        System.out.println("Lista vacía.");
                    }
                }
                case 8 -> {
                    tareas.ordenar();
                    System.out.println("✅ Tareas ordenadas.");
                }
                case 9 -> {
                    System.out.println("Ingrase el nombre de la tarea a consultar");
                    String tarea = scanner.nextLine();
                    boolean encontrada = tareas.contiene(tarea);
                    try{
                        if(encontrada){
                            System.out.println("La tarea se encuenta dentro de esta lista.");
                        }else{
                           System.out.println("La tarea no se encuenta dentro de esta lista.");
                        }
                    }catch(Exception e){
                        System.out.println("No se encontro ese valor en la lista.");
                    }
                }
                case 10 -> {
                    tareas.limpiar();
                    System.out.println("Lista vaciada.");
                }
                case 0 -> System.out.println("👋 Programa terminado.");
                default -> System.out.println("❌ Opción no válida.");
            }

        } while (opcion != 0);

        scanner.close();
    }
}
