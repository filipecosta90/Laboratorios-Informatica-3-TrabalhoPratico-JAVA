
/**
 * Write a description of class ComparatorValor_Par_Cliente_ValorGasto here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.Comparator;

public class ComparatorUnidades_Triplo_Cliente_Unidades_ValorGasto implements Comparator<Triplo_Cliente_Unidades_ValorGasto>{
    
    /** Ordem do par por unidades vendidas e caso os valores gastos sejam iguais, ordenar por código de Cliente */
  public int compare(Triplo_Cliente_Unidades_ValorGasto c1, Triplo_Cliente_Unidades_ValorGasto c2){
    int valorRetornar = (int) ( c2.getUnidadesVendidas() - c1.getUnidadesVendidas());
    if ( valorRetornar == 0 ){
      valorRetornar = c1.getCodigoCliente().compareTo(c2.getCodigoCliente());
    }
    return valorRetornar;
  }

}

