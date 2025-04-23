import java.io.*;
import java.util.Scanner;

public class Cajero_Automático {
    private static final String ARCHIVO_SALDO = "saldo.dat";
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Asegurar que el archivo de saldo exista con un valor inicial
        inicializarSaldo(1000.00);
        
        int opcion;
        do {
            mostrarMenu();
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            
            switch (opcion) {
                case 1:
                    consultarSaldo();
                    break;
                case 2:
                    System.out.print("Ingrese el monto a retirar: ");
                    double monto = scanner.nextDouble();
                    retirarDinero(monto);
                    break;
                case 3:
                    System.out.println("Gracias por usar el cajero automático. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 3);
        
        scanner.close();
    }
    
    // Método para mostrar el menú de opciones
    private static void mostrarMenu() {
        System.out.println("\n--- Menú Cajero Automático ---");
        System.out.println("1. Consultar saldo");
        System.out.println("2. Retirar dinero");
        System.out.println("3. Salir");
    }
    
    // Método para inicializar el archivo de saldo si no existe
    private static void inicializarSaldo(double saldoInicial) {
        File archivo = new File(ARCHIVO_SALDO);
        if (!archivo.exists()) {
            guardarSaldo(saldoInicial);
        }
    }
    
    // Método para consultar el saldo almacenado en el archivo
    private static void consultarSaldo() {
        double saldo = leerSaldo();
        System.out.println("Su saldo actual es: Q" + saldo);
    }
    
    // Método para retirar dinero del saldo
    private static void retirarDinero(double monto) {
        double saldo = leerSaldo();
        
        if (monto > saldo) {
            System.out.println("Saldo insuficiente. No se puede realizar el retiro.");
        } else {
            saldo -= monto;
            guardarSaldo(saldo);
        }
    }
    
    // Método para leer el saldo desde el archivo
    private static double leerSaldo() {
        try (DataInputStream dis = new DataInputStream(new FileInputStream(ARCHIVO_SALDO))) {
            return dis.readDouble();
        } catch (IOException e) {
            System.out.println("Error al leer el saldo. Se establecerá a Q0.");
            return 0.0;
        }
    }
    
    // Método para guardar el saldo en el archivo
    private static void guardarSaldo(double saldo) {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(ARCHIVO_SALDO))) {
            dos.writeDouble(saldo);
        } catch (IOException e) {
            System.out.println("Error al guardar el saldo.");
        }
    }
}