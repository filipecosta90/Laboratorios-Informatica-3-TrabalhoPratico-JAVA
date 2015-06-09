
/**
 * Classe referente as compras de um cliente
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.io.Serializable;
import java.util.TreeMap;
import java.util.HashSet;

public class ComprasProduto implements Serializable{
    
    private TreeMap <Integer, HashSet<Compra>> listaComprasProduto;
    
    public ComprasProduto(){
        this.listaComprasProduto = new TreeMap <Integer, HashSet<Compra>>();
    }
    
    public ComprasProduto(TreeMap <Integer, HashSet<Compra>> lista){
        HashSet <Compra> novaCompras = new HashSet <> ();
        TreeMap <Integer, HashSet<Compra>> novaLista = new TreeMap <> ();
        for(HashSet <Compra> cmp : lista.values()){
            for(Compra c : cmp){
                novaCompras.add(c.clone());
                novaLista.put(c.getMes(),novaCompras);
            }
        }
    }
    
    public ComprasProduto(ComprasProduto cc){
        TreeMap <Integer, HashSet<Compra>> novaLista = new TreeMap <Integer, HashSet<Compra>> ();
        HashSet <Compra> novaCompras = new HashSet <Compra> ();
        for(HashSet <Compra> cmp : cc.getListaComprasProduto().values()){
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
    public TreeMap <Integer,HashSet<Compra>> getListaComprasProduto(){
        TreeMap <Integer, HashSet<Compra> > novaLista = new TreeMap <Integer,HashSet<Compra>> ();
        HashSet <Compra> novaCompras = new HashSet <Compra>();
        for(HashSet<Compra> cmp : this.listaComprasProduto.values()){
            for(Compra c: cmp){
                novaCompras.add(c.clone());
                novaLista.put(c.getMes(),novaCompras);
            }
        }
        return novaLista;
    }
    
    /**
     * Método auxiliar que junta uma compra ao HashSet
     */
    private HashSet <Compra> addCompraToSet (Compra c){
        HashSet <Compra> compras = new HashSet<>();
        compras.add(c);
        return compras;
    }
    
    /**
     * Método que adiciona uma compra na estrutura
     */
    public void addCompraToLista(Compra c){
        int mes = c.getMes();
        this.listaComprasProduto.put(mes,addCompraToSet(c));
    }
    
        /**
     * toString
     */
    @Override
    public String toString(){
        StringBuilder s= new StringBuilder();
        s.append("\nInformação de Compras");
        s.append("\nCompras do Produto:");
        for(HashSet <Compra> cmp : this.listaComprasProduto.values()){
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
            ComprasProduto cp = (ComprasProduto) o;
            if(this.listaComprasProduto.equals(cp.getListaComprasProduto())) return true;
            else return false;
        }
    }
    
    /**
     * Método clone
     */
    @Override
    public ComprasProduto clone() {
        return new ComprasProduto(this);
    } 
}

