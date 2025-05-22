/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion;

import javax.management.Attribute;
import javax.management.AttributeList;

public class MonitorServidorJuego {


    private int totalConexionesActivas = 0;
    private long conexionesProcesadasTotal = 0;
    private int clientesConCodigo123Detectados = 0; 
    private int ultimoClienteIDProcesado = 0; 


    private final AttributeList jmxMetricsList;

    public MonitorServidorJuego() {
        jmxMetricsList = new AttributeList();
        
        jmxMetricsList.add(new Attribute("TotalConexionesActivas", this.totalConexionesActivas));
        jmxMetricsList.add(new Attribute("ConexionesProcesadasTotal", this.conexionesProcesadasTotal));
        jmxMetricsList.add(new Attribute("ClientesConCodigo123Detectados", this.clientesConCodigo123Detectados));
        jmxMetricsList.add(new Attribute("UltimoClienteIDProcesado", this.ultimoClienteIDProcesado));
    }
    public void procesarEventoCliente(int clienteID) {
        
        this.conexionesProcesadasTotal++;
        this.ultimoClienteIDProcesado = clienteID;
        this.totalConexionesActivas++;
        
        if (clienteID == 123) {
            this.clientesConCodigo123Detectados++;
            System.out.println("\n [ALERTA JMX-SIM]: ¡Cliente ID 123 detectado! Reduciendo conexiones activas...");
            reducirConexionesActivas(50); 
        }

        actualizarMetricasJMX();
    }

    public void reducirConexionesActivas(int cantidad) {
        System.out.println("  [OPERACIÓN JMX-SIM]: Reduciendo " + cantidad + " conexiones activas.");
        this.totalConexionesActivas = Math.max(0, this.totalConexionesActivas - cantidad);
        actualizarMetricasJMX(); 
    }

  
    
    
    private void updateAttributeInList(String name, Object value) {
        jmxMetricsList.removeIf(attr -> ((Attribute) attr).getName().equals(name));
        jmxMetricsList.add(new Attribute(name, value));
    }
    private void actualizarMetricasJMX() {
        updateAttributeInList("TotalConexionesActivas", this.totalConexionesActivas);
        updateAttributeInList("ConexionesProcesadasTotal", this.conexionesProcesadasTotal);
        updateAttributeInList("ClientesConCodigo123Detectados", this.clientesConCodigo123Detectados);
        updateAttributeInList("UltimoClienteIDProcesado", this.ultimoClienteIDProcesado);
    }


    public void imprimirMetricasJmx() {
        System.out.println("  > Estado actual del Servidor (vista JMX):");
        for (Object item : jmxMetricsList) {
            if (item instanceof Attribute) {
                Attribute attr = (Attribute) item;
                System.out.println("    " + attr.getName() + ": " + attr.getValue());
            }
        }
    }

}