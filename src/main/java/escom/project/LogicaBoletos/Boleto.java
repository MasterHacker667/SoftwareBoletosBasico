package escom.project.LogicaBoletos;

public class Boleto {
    private static int id;
    private int miId;
    private long numeroSerie;
    private Fecha fecha;
    private String nombreEvento;
    private static float precioBase = (float) 10.00;
    private String zona;
    private float precioReal;

    public Boleto(String nombreEvento, Fecha fecha, String zona, int codigoEvento){
        id++;
        this.setNumeroSerie(codigoEvento);
        this.setMiId(id);
        this.setNombreEvento(nombreEvento);
        this.setFecha(fecha);
        if(zona == "general" || zona == "luneta" || zona == "gradas" || zona == "palco"){
            this.setZona(zona);
        }else{
            this.setZona("general");
        }

    }

    protected void setPrecioReal(float pR){
        this.precioReal = pR;
    }
    protected void calcularPrecio(){//Para herencia
        this.setPrecioReal(precioBase * 1);
    }
    public void desplegarDetalles(){//Para herencia en overide

    }

    public int getId() {
        return id;
    }

    public long getNumeroSerie() {
        return this.numeroSerie;
    }

    public Fecha getFecha() {
        return this.fecha;
    }

    public String getNombreEvento() {
        return this.nombreEvento;
    }
    protected float getPrecionBase(){
        return precioBase;
    }

    public String getZona() {
        return this.zona;
    }

    public float getPrecioReal() {
        return this.precioReal;
    }

    public void setFecha(Fecha fecha) {
        this.fecha = fecha;
    }

    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public int getMiId() {
        return miId;
    }

    public void setMiId(int miId) {
        this.miId = miId;
    }

    public void setNumeroSerie(int codigoEvento) {

        this.numeroSerie = (long)(codigoEvento * 1000000L) + id;
    }
}
