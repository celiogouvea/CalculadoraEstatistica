package cgtechnology.com.calculadoraestatistica;

/**
 * Created by pdj_3 on 24/02/2017.
 */

public class DadosTabela {

    int classe;
    String intervalo;
    String Fi;
    String Fac;
    String Fr;
    String Frac;

    //construtor
    public DadosTabela(int classe, String intervalo, String Fi, String Fac, String Fr, String Frac){
        this.classe = classe;
        this.intervalo = intervalo;
        this.Fi = Fi;
        this.Fac = Fac;
        this.Fr = Fr;
        this.Frac = Frac;
    }
}
