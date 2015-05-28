/**
 * Classe referente a um cliente
 * 
 * @author (Carlos Sá A59905, Filipe Oliveira A57816, Sérgio Caldas A57779) 
 * @version (a version number or a date)
 */

import java.util.HashMap;
import java.io.Serializable;

public class Compras implements Serializable{
    
    private String codCliente;
    private HashMap <Integer,Compra> listaCompras;
    
    /**
     * Construtores
     */
    //Vazio
    public Compras(){
        this.codCliente="";
        this.listaCompras=new HashMap<Integer,Compra>();
    }
    
    //Parametrizado
    public Compras(String cod, HashMap <Integer,Compra> lCompras){
        this.codCliente=cod;
        HashMap <Integer,Compra> novoLisComp = new HashMap <> ();
        for(Compra c : this.listaCompras.values()){
            novoLisComp.put(c.getMes(),c.clone());
        }
    }
    
    //Por copia
    public Compras(Compras c){
        this.codCliente=c.getCodCliente();
        HashMap <Integer,Compra> novoLisComp = new HashMap <> ();
        for(Compra cmp : c.getListaCompras().values()){
            novoLisComp.put(cmp.getMes(),cmp.clone());
        }
    }
    
    /**
     * Getter and Setter
     */
    public String getCodCliente(){
        return this.codCliente;
    }
    
    public HashMap <Integer,Compra> getListaCompras(){
        HashMap <Integer,Compra> novoLisComp = new HashMap <> ();
        for(Compra c : this.listaCompras.values()){
            novoLisComp.put(c.getMes(),c.clone());
        }
        return novoLisComp;
    }
    
    //Falta set verificar se faz falta
    
    /**
     * toString
     */
    @Override
    public String toString(){
        StringBuilder s= new StringBuilder();
        s.append("\nInformação de Compras");
        s.append("\nCodigo Cliente: "+this.codCliente);
        s.append("\nCompras do Cliente:");
        for(Compra cmp : this.listaCompras.values()){
            s.append("\n"+cmp.toString());
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
            Compras c = (Compras) o;
            if(this.codCliente.equals(c.getCodCliente()) && this.listaCompras.equals(c.getListaCompras())) return true;
            else return false;
        }
    }
    
    /**
     * Método clone
     */
    @Override
    public Compras clone() {
        return new Compras(this);
    }    
}