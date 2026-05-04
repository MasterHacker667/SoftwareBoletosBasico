package escom.project;
import escom.project.LogicaBoletos.*;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner leer = new Scanner(System.in);

        // 1. Crear el evento (Datos iniciales)
        System.out.println("--- Registro de Nuevo Evento ---");
        System.out.print("Nombre: "); String nombre = leer.nextLine();
        System.out.print("Código (4 dígitos): "); int codigo = leer.nextInt();
        // Suponiendo que ya tienes tu objeto Fecha listo
        Evento miEvento = new Evento(codigo, nombre, new Fecha(), 200);

        int opcion;
        do {
            System.out.println("\n--- SISTEMA DE BOLETAJE: " + nombre + " ---");
            System.out.println("1. Buscar Boleto (por ID o Asiento)");
            System.out.println("2. Agregar nuevo Boleto (Venta individual)");
            System.out.println("3. Eliminar Boleto (Cancelación)");
            System.out.println("4. Mostrar Inventario Completo");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = leer.nextInt();

            switch (opcion) {
                case 1: // Buscar y Desplegar
                    System.out.println("Inserte un ID valido: ");
                    int aa = leer.nextInt();
                    Boleto a = miEvento.buscarBoleto(aa);
                    if(a != null){
                        a.desplegarDetalles();
                    }else{
                        System.out.println("No existe el Boleto con ID: " + aa);
                    }
                    // Aquí usas la sobrecarga: preguntar si quiere buscar por ID o por Asiento
                    break;
                case 2: // Agregar nuevo Boleto
                    System.out.println("\n--- TIPO DE BOLETO A VENDER ---");
                    System.out.println("1. General");
                    System.out.println("2. Gradas");
                    System.out.println("3. Luneta");
                    System.out.println("4. Palco");
                    System.out.print("Seleccione zona: ");
                    int tipoZona = leer.nextInt();
                    leer.nextLine(); // Limpiar buffer

                    switch (tipoZona) {
                        case 1: // General
                            miEvento.crearBoleto ("general");
                            System.out.println("✅ Boleto General agregado.");
                            break;

                        case 2: // Gradas
                        case 3: // Luneta
                            String zonaNombre = (tipoZona == 2) ? "gradas" : "luneta";
                            char s, f;
                            int n;
                            boolean ocupado = true;

                            // Ciclo de validación de asiento
                            while (ocupado) {
                                System.out.print("Ingrese Sección (A-Z): "); s = leer.next().toUpperCase().charAt(0);
                                System.out.print("Ingrese Fila (A-Z): "); f = leer.next().toUpperCase().charAt(0);
                                System.out.print("Ingrese Asiento (1-25): "); n = leer.nextInt();

                                if (miEvento.buscarBoleto(s, f, n) == null) {
                                    //miEvento.crearBoleto(new BoletoExclusivo(nombre, new Fecha(), zonaNombre, codigo, s, f, n));
                                    miEvento.crearBoleto(zonaNombre, s, f, n);
                                    System.out.println("✅ Boleto " + zonaNombre + " agregado en " + s + f + "-" + n);
                                    ocupado = false;
                                } else {
                                    System.out.println("❌ Error: Ese asiento ya está ocupado. Intente con otro.");
                                }
                            }
                            break;

                        case 4: // Palco
                            System.out.print("Ingrese capacidad del palco (5-20): ");
                            int cap = leer.nextInt();
                            // Aquí tu método setter de Palco debería validar los límites
                            //miEvento.getBoletos().add(new BoletoPalco(nombre, fecha, "Palco", codigo, cap));
                            miEvento.crearBoleto("palco");
                            System.out.println("✅ Boleto de Palco agregado.");
                            break;


                        default:
                            System.out.println("Opción no válida.");
                    }
                    break;
                case 3: // Eliminar
                    System.out.print("Ingrese el número de serie a eliminar: ");
                    long serie = leer.nextLong();
                    miEvento.eliminarBoleto(serie);
                    break;
                case 4:
                    miEvento.mostrarBoletos(); // Un simple for que recorra la lista
                    break;
            }
        } while (opcion != 5);
    }
}