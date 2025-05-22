/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion;

import java.util.Random;

public class Presentacion {

    public static void main(String[] args) throws InterruptedException {
        
        MonitorServidorJuego monitor = new MonitorServidorJuego();
        Random random = new Random();
        
        System.out.println("--- INICIO DE MONITOREO DEL SERVIDOR ---");
        monitor.imprimirMetricasJmx();
        System.out.println("\n--- SIMULANDO ACTIVIDAD DE CLIENTES (1000 eventos) ---");

        for (int i = 1; i <= 1000; i++) {
            int clienteID;
            
            clienteID = random.nextInt(499) + 1;
            monitor.procesarEventoCliente(clienteID);

            if (clienteID == 123) {
                System.out.println("--- Después de " + i + " eventos ---");
                monitor.imprimirMetricasJmx();
            }
        }

        System.out.println("\n--- FIN DE LA SIMULACIÓN: Métricas Finales ---");
        monitor.imprimirMetricasJmx();
    }
}