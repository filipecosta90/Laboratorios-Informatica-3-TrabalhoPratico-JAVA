/**
 * Classe referente ao comparator por unidades e seguidamente por ordem alfabética
 * de um tripo CodProduto -> Unidades Vendidas -> Numero de Vendas
 * 
 * @author (Carlos Sá A59905, Filipe Oliveira A57816, Sérgio Caldas A57779) 
 * @version (a version number or a date)
 */

import java.util.Comparator;

public class ComparatorUnidades_Triplo_Produto_Unidades_Vendas implements Comparator<Triplo_Produto_Unidades_Vendas> {

  /** Ordem das Compras por quantidade e caso as quantidades sejam iguais, ordenar por código de Produto */
  public int compare(Triplo_Produto_Unidades_Vendas c1, Triplo_Produto_Unidades_Vendas c2){
    int valorRetornar = c2.getNumeroUnidadesVendidas() - c1.getNumeroUnidadesVendidas();
    if ( valorRetornar == 0 ){
      valorRetornar = c1.getCodigoProduto().compareTo(c2.getCodigoProduto());
    }
    return valorRetornar;
  }

}
