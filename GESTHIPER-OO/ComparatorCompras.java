/**
 * Classe referente a um cliente
 * 
 * @author (Carlos Sá A59905, Filipe Oliveira A57816, Sérgio Caldas A57779) 
 * @version (a version number or a date)
 */

import java.util.Comparator;

public class ComparatorCompras implements Comparator<Compra> {
    
    /** Ordem das Compras por quantidade e caso as quantidades sejam iguais, ordenar por código de Produto */
    public int compare(Compra c1, Compra c2){
        int valorRetornar = c1.getQuantidade() - c2.getQuantidade();
        if ( valorRetornar == 0 ){
            valorRetornar = c1.getCodigoProduto().compareTo(c2.getCodigoProduto());
        }
        return valorRetornar;
    }
    
}
