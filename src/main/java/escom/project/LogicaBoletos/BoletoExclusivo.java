package escom.project.LogicaBoletos;

public class BoletoExclusivo extends Boleto{
    private char seccion;
    private char fila;
    private int asiento;
    public BoletoExclusivo(String nombreEvento, Fecha fecha, String zona, int codigoEvento, char seccion, char fila, int asiento){
        super(nombreEvento, fecha, zona, codigoEvento);
        this.setSeccion(seccion);
        this.setFila(fila);
        this.calcularPrecio();
        this.setAsiento(asiento);
    }

    @Override
    protected void calcularPrecio() {
        if(this.getZona() == "luneta"){
            float pb = this.getPrecionBase();
            this.setPrecioReal((float) (pb*1.85));
        } else if (this.getZona() == "gradas") {
            float pb = this.getPrecionBase();
            this.setPrecioReal((float) pb);
        }

    }

    public void setSeccion(char seccion) {
        char letra = Character.toUpperCase(seccion);
        if(letra >= 'A' && letra <= 'Z'){
            this.seccion = seccion;
        }else{
            throw new IllegalAccessError("ERROR: Seccion "+seccion+" invalida");
        }

    }

    public void setFila(char fila) {
        char letra = Character.toUpperCase(fila); // Normalizamos a Mayúscula
        if(letra >= 'A' && letra <= 'Z'){
            this.fila = fila;
        }else{
            throw new IllegalArgumentException("ERROR: Fila "+fila+" invalida");
        }

    }

    public void setAsiento(int asiento) {
        if(asiento >= 1 && asiento <= 25){
            this.asiento = asiento;
        }else{
            throw new IllegalArgumentException("ERROR: "+asiento+" no es valido");
        }

    }

    public char getSeccion() {
        return this.seccion;
    }

    public char getFila() {
        return this.fila;
    }

    public int getAsiento() {
        return this.asiento;
    }
    public void desplegarDetalles(){
        System.out.println("-----------------------------------------------\nID: " + this.getMiId()+"\nNumero Serie: "+this.getNumeroSerie() + "\nFecha: "+this.getFecha().getDia()+" / "+this.getFecha().getMes()+" / "+this.getFecha().getAno()+"\nNombre del Evento: "+this.getNombreEvento()+"\nPrecio: $"+this.getPrecioReal()+"\nZona: "+this.getZona()+"\n\nSeccion: "+this.getSeccion()+"\nFila: "+this.getFila()+"\nAsiento: "+this.getAsiento()+"\n-----------------------------------------------");
    }
}
