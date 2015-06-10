
/**
 * Write a description of class Contabilidade here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.TreeMap;
import java.io.Serializable;

public class Contabilidade implements Serializable{

    // HashMap com key: codigoProduto , valor: ComprasProduto 
    private TreeMap <String,ComprasProduto> listaTotalComprasProdutos;
    
    public Contabilidade(){
     this.listaTotalComprasProdutos = new TreeMap <String,ComprasProduto> ();
    }
    
    public Contabilidade ( Contabilidade copia ){
     this.listaTotalComprasProdutos = new TreeMap <String,ComprasProduto> ();
    }
    
}
