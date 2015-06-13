
/**
 * Classe referente ao comparator por numero de produtos distintos e seguidamente por ordem alfabética
 * de um par CodCliente -> Numero de Produtos Distintos
 * 
 * @author (Carlos Sá A59905, Filipe Oliveira A57816, Sérgio Caldas A57779) 
 * @version (a version number or a date)
 */

import java.util.Comparator;

public class ComparatorProdutosDistintos_Par_Cliente_ProdutosDistintos implements Comparator<Par_Cliente_ProdutosDistintos>{
  /** Ordem das Compras por quantidade e caso as quantidades sejam iguais, ordenar por código de Produto */
  public int compare(Par_Cliente_ProdutosDistintos c1, Par_Cliente_ProdutosDistintos c2){
    int valorRetornar = c2.getNumeroProdutosDistintos() - c1.getNumeroProdutosDistintos();
    if ( valorRetornar == 0 ){
      valorRetornar = c1.getCodigoCliente().compareTo(c2.getCodigoCliente());
    }
    return valorRetornar;
  }

}
