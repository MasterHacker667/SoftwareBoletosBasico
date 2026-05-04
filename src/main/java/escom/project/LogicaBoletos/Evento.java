package escom.project.LogicaBoletos;

import java.util.ArrayList;
import java.util.List;

public class Evento {
    private int codigo;
    private String nombre;
    private Fecha fecha;
    private List<Boleto> boletos;
    private int noLuneta;
    private int noGradas;
    private int noGeneral;
    private int noPalco;
    public Evento(int codigo, String nombre, Fecha fecha, int cantBoletos){
        this.setCodigo(codigo);
        this.setNombre(nombre);
        this.setFecha(fecha);
        this.boletos = new ArrayList<>();
        this.inicializarBoletos(cantBoletos, this.getCodigo());
    }
    private void inicializarBoletos(int totalBoletos, int codigoEvento) {
        // 1. GRADAS (100 unidades)
        for (int i = 0; i < 100; i++) {
            boolean asignado = false;
            while (!asignado) {
                char s = generarLetraAleatoria('A', 'Z');
                char f = generarLetraAleatoria('A', 'Z');
                int n = (int) (Math.random() * 25) + 1;

                // Validación de unicidad
                if (buscarBoleto(s, f, n) == null) {
                    // AQUÍ YA INCLUIMOS EL codigoEvento
                    boletos.add(new BoletoExclusivo(this.getNombre(), fecha, "gradas", codigoEvento, s, f, n));
                    asignado = true;
                }
            }
        }
        this.setNoGradas(100);

        // 2. LUNETA (50 unidades)
        for (int i = 0; i < 50; i++) {
            boolean asignado = false;
            while (!asignado) {
                char s = generarLetraAleatoria('A', 'Z');
                char f = generarLetraAleatoria('A', 'Z');
                int n = (int) (Math.random() * 25) + 1;

                if (buscarBoleto(s, f, n) == null) {
                    // TAMBIÉN AQUÍ
                    boletos.add(new BoletoExclusivo(this.getNombre(), fecha, "luneta", codigoEvento, s, f, n));
                    asignado = true;
                }
            }
        }
        this.setNoLuneta(50);

        // 3. Crear 10 Boletos de Palco
        for (int i = 1; i <= 10; i++) {
            // Capacidad aleatoria o fija entre 5 y 20
            int cap = 5 + (i % 16);
            boletos.add(new BoletoPalco(this.getNombre(), fecha, "palco", cap));
        }
        this.setNoPalco(10);
        // 4. El resto para General
        int restantes = totalBoletos - 160;
        for (int i = 0; i < restantes; i++) {
            boletos.add(new BoletoGeneral(this.getNombre(), fecha, "general", codigoEvento));
        }
        this.setNoGeneral(restantes);
    }
    public void crearBoleto(String tipo){
        if(tipo == "general"){
            this.boletos.add(new BoletoGeneral(this.nombre, this.fecha, tipo, this.codigo));
            this.setNoGeneral(this.getNoGeneral() + 1);
        } else if (tipo == "palco") {
            this.boletos.add(new BoletoPalco(this.getNombre(), this.getFecha(), tipo, this.codigo));
            this.setNoPalco(this.getNoPalco() + 1);
        }else{
            throw new RuntimeException("Tipo no reconocido");
        }
    }
    public void crearBoleto(String tipo, char seccion, char fila, int asiento){
        this.boletos.add(new BoletoExclusivo(this.getNombre(), this.getFecha(), tipo, this.getCodigo(), seccion, fila, asiento));
        if(tipo == "luneta"){
            this.setNoLuneta(this.getNoLuneta() + 1);
        }else{
            this.setNoGradas(this.getNoGradas() + 1);
        }
    }
    public boolean eliminarBoleto(long numSerie){
        for (int i = 0; i< this.boletos.size(); i++){
            Boleto b = this.boletos.get(i);

            if (b.getNumeroSerie() == numSerie) {
                if(b.getZona() == "general"){
                    this.setNoGeneral(this.getNoGeneral() - 1);
                } else if (b.getZona() == "luneta") {
                    this.setNoLuneta(this.getNoLuneta() - 1);
                } else if (b.getZona() == "gradas") {
                    this.setNoGradas(this.getNoGradas() - 1);
                }else{
                    this.setNoPalco(this.getNoPalco() - 1);
                }
                boletos.remove(i); // Eliminamos el objeto del ArrayList
                System.out.println("✅ Boleto con serie " + numSerie + " eliminado exitosamente.");
                return true; // Terminamos el método con éxito
            }
        }
        System.out.println("❌ Error: No se encontró ningún boleto con el número de serie: " + numSerie);
        return false;
    }
    public Boleto buscarBoleto(int id) {
        for (Boleto b : boletos) {
            if (b.getMiId() == id) return b;
        }
        return null;
    }
    public Boleto buscarBoleto(char seccion, char fila, int asiento) {
        for (Boleto b : boletos) {
            // Solo buscamos en boletos que tienen asiento (Grada o Luneta)
            if (b instanceof BoletoExclusivo) {
                BoletoExclusivo be = (BoletoExclusivo) b;
                if (be.getSeccion() == seccion && be.getFila() == fila && be.getAsiento() == asiento) {
                    return be;
                }
            }
        }
        return null;
    }
    private char generarLetraAleatoria(char inicio, char fin) {
        return (char) (Math.random() * (fin - inicio + 1) + inicio);
    }
    //MOSTRAR TODOS LOS BOLETOS
    public void mostrarBoletos(){
        for(Boleto bol : this.boletos){
            bol.desplegarDetalles();
        }
        System.out.println("------Numero de boletos------");
        System.out.println("Luneta: " + this.getNoLuneta());
        System.out.println("Gradas: " + this.getNoGradas());
        System.out.println("Palco: " + this.getNoPalco());
        System.out.println("General: " + this.getNoGeneral());
        System.out.println("  Total de boletos: " + (this.getNoGeneral() + this.getNoGradas() + this.getNoPalco() + this.getNoLuneta()));
    }
    //setters
    public void setCodigo(int c) {
        this.codigo = c;
    }
    public void setNombre(String n){
        this.nombre = n;
    }
    public void setFecha(Fecha fecha) {
        this.fecha = fecha;
    }

    //getters
    public int getCodigo() {
        return this.codigo;
    }
    public Fecha getFecha() {
        return this.fecha;
    }
    public String getNombre() {
        return this.nombre;
    }


    public int getNoLuneta() {
        return noLuneta;
    }

    public void setNoLuneta(int noLuneta) {
        this.noLuneta = noLuneta;
    }

    public int getNoGradas() {
        return noGradas;
    }

    public void setNoGradas(int noGradas) {
        this.noGradas = noGradas;
    }

    public int getNoGeneral() {
        return noGeneral;
    }

    public void setNoGeneral(int noGeneral) {
        this.noGeneral = noGeneral;
    }

    public int getNoPalco() {
        return noPalco;
    }

    public void setNoPalco(int noPalco) {
        this.noPalco = noPalco;
    }
}
