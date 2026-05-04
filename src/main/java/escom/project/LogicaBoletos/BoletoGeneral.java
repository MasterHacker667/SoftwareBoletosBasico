package escom.project.LogicaBoletos;

public class BoletoGeneral extends Boleto{

    public BoletoGeneral(String nombreEvento, Fecha fecha, String zona, int codigoEvento){
        super(nombreEvento, fecha, zona, codigoEvento);
        this.calcularPrecio();
    }

    @Override
    protected void calcularPrecio() {
        this.setPrecioReal((float ) this.getPrecionBase() * (float )1.5);
    }
    public void desplegarDetalles(){
        System.out.println("-----------------------------------------------\nID: " + this.getMiId()+"\nNumero Serie: "+this.getNumeroSerie() + "\nFecha: "+this.getFecha().getDia()+" / "+this.getFecha().getMes()+" / "+this.getFecha().getAno()+"\nNombre del Evento: "+this.getNombreEvento()+"\nPrecio: $"+this.getPrecioReal()+"\nZona: "+this.getZona()+"\n-----------------------------------------------");
    }

}
