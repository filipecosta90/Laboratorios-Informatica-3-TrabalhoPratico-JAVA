/**
 * Classe referente a um cliente
 * 
 * @author (Carlos Sá A59905, Filipe Oliveira A57816, Sérgio Caldas A57779) 
 * @version (a version number or a date)
 */

import java.util.Comparator;
import java.io.Serializable;

public class ComparatorCompras implements Comparator<Compra>, Serializable {
    
    /** Ordem das Compras por quantidade */
    public int compare(Compra c1, Compra c2){
        if ( c1.getQuantidade() < c2.getQuantidade() ) return -1;
        if ( c1.getQuantidade() == c2.getQuantidade() ) return 0;
        else return 1;
    }
    
}
