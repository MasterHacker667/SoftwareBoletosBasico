package escom.project.LogicaBoletos;

public class BoletoPalco extends Boleto{
    private int capMaxima;
    private int capMinima;

    public BoletoPalco(String nombreEvento, Fecha fecha, String zona, int codigoEvento){
        super(nombreEvento, fecha, zona, codigoEvento);
        this.setCapMinima(5);
        this.setCapMaxima((20));
        this.calcularPrecio();
    }

    @Override
    protected void calcularPrecio() {
        this.setPrecioReal(getPrecionBase() * 3);
    }

    public int getCapMaxima() {
        return capMaxima;
    }

    public void setCapMaxima(int capMaxima) {
        this.capMaxima = capMaxima;
    }

    public int getCapMinima() {
        return capMinima;
    }

    public void setCapMinima(int capMinima) {
        this.capMinima = capMinima;
    }
    public void desplegarDetalles(){
        System.out.println("-----------------------------------------------\nID: " + this.getMiId()+"\nNumero Serie: "+this.getNumeroSerie() + "\nFecha: "+this.getFecha().getDia()+" / "+this.getFecha().getMes()+" / "+this.getFecha().getAno()+"\nNombre del Evento: "+this.getNombreEvento()+"\nPrecio: $"+this.getPrecioReal()+"\nZona: "+this.getZona()+"\n\nCapacidad Minima: "+this.getCapMinima()+" personas\n"+"Capacidad Maxima: "+this.getCapMaxima()+" personas"+"\n-----------------------------------------------");
    }

}
