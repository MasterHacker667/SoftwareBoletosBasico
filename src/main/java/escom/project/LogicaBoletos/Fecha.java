package escom.project.LogicaBoletos;
import java.time.LocalDate;

public class Fecha {
    private String Dia;
    private String Mes;
    private String Ano;
    public Fecha(String dia, String mes, String ano){
        this.Dia = dia;
        this.Mes = mes;
        this.Ano = ano;
    }
    public Fecha(){
        LocalDate hoy = LocalDate.now();
        this.Dia = String.valueOf(hoy.getDayOfMonth());
        this.Mes = String.valueOf(hoy.getMonthValue());
        this.Ano = String.valueOf(hoy.getYear());
    }
    public String getDia(){
        return this.Dia;
    }

    public String getAno() {
        return this.Ano;
    }

    public String getMes() {
        return this.Mes;
    }
    public void setDia(String d){
        this.Dia = d;
    }
    public void setMes(String m){
        this.Mes = m;
    }
    public void setAno(String a){
        this.Ano = a;
    }
}
