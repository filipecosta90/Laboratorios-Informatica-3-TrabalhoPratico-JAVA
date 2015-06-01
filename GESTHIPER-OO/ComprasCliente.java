/**
 * Classe referente as compras de um cliente
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.io.Serializable;
import java.util.TreeMap;
import java.util.HashSet;

public class ComprasCliente implements Serializable{
    
    private TreeMap <Integer, HashSet<Compra>> listaComprasCliente;
    
    public ComprasCliente(){
        this.listaComprasCliente = new TreeMap <Integer, HashSet<Compra>>();
    }
    
    public ComprasCliente(TreeMap <Integer, HashSet<Compra>> lista){
        HashSet <Compra> novaCompras = new HashSet <> ();
        TreeMap <Integer, HashSet<Compra>> novaLista = new TreeMap <> ();
        for(HashSet <Compra> cmp : lista.values()){
            for(Compra c : cmp){
                novaCompras.add(c.clone());
                novaLista.put(c.getMes(),novaCompras);
            }
        }
    }
    
    public ComprasCliente(ComprasCliente cc){
        TreeMap <Integer, HashSet<Compra>> novaLista = new TreeMap <Integer, HashSet<Compra>> ();
        HashSet <Compra> novaCompras = new HashSet <Compra> ();
        for(HashSet <Compra> cmp : cc.getListaComprasCliente().values()){
            for(Compra c : cmp){
                novaCompras.add(c.clone());
                novaLista.put(c.getMes(),novaCompras);
            }
        }
    }
    
    /**
     * Getters and Setters
     */
    
    /**
     * Perguntar ao prof problema com o clone
     */
    public TreeMap <Integer,HashSet<Compra>> getListaComprasCliente(){
        TreeMap <Integer, HashSet<Compra> > novaLista = new TreeMap <Integer,HashSet<Compra>> ();
        HashSet <Compra> novaCompras = new HashSet <Compra>();
        for(HashSet<Compra> cmp : this.listaComprasCliente.values()){
            for(Compra c: cmp){
                novaCompras.add(c.clone());
                novaLista.put(c.getMes(),novaCompras);
            }
        }
        return novaLista;
    }
    
        /**
     * toString
     */
    @Override
    public String toString(){
        StringBuilder s= new StringBuilder();
        s.append("\nInformação de Compras");
        s.append("\nCompras do Cliente:");
        for(HashSet <Compra> cmp : this.listaComprasCliente.values()){
            for(Compra c : cmp){
                 s.append("\n"+cmp.toString());
            }
        }
        return s.toString();
    }
    
    /**
     * Equals
     */
    @Override
    public boolean equals(Object o) {
        if(this==o) return true;
        if((o==null) || this.getClass()!=o.getClass()) return false;
        else{
            ComprasCliente cc = (ComprasCliente) o;
            if(this.listaComprasCliente.equals(cc.getListaComprasCliente())) return true;
            else return false;
        }
    }
    
    /**
     * Método clone
     */
    @Override
    public ComprasCliente clone() {
        return new ComprasCliente(this);
    } 
}
