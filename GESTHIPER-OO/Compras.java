/**
 * Classe referente a um cliente
 * 
 * @author (Carlos Sá A59905, Filipe Oliveira A57816, Sérgio Caldas A57779) 
 * @version (a version number or a date)
 */

import java.util.TreeMap;
import java.io.Serializable;

public class Compras implements Serializable{
   
    private TreeMap <String,ComprasCliente> listaTotalCompras;
     private int comprasValidadas;
     private int comprasRejeitadas;
     
     Compras (){
        this.listaTotalCompras = new TreeMap < String , ComprasCliente > ();
        this.comprasValidadas = 0;
        this.comprasRejeitadas = 0;
        }
}