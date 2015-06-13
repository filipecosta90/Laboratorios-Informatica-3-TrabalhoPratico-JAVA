
/**
 * Classe referente ao comparator por unidades e seguidamente por ordem alfabética
 * de um tripo CodCliente -> Unidades Vendidas -> Valor Gasto
 * 
 * @author (Carlos Sá A59905, Filipe Oliveira A57816, Sérgio Caldas A57779) 
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

