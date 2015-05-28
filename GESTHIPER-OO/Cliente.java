/**
 * Classe referente a um cliente
 * 
 * @author (Carlos Sá A59905, Filipe Oliveira A57816, Sérgio Caldas A57779) 
 * @version (a version number or a date)
 */

import java.util.HashMap;

public class Cliente{
    
    private String codCliente;
    private HashMap <Integer,Compra> listaCompras;
    
    /**
     * Construtores
     */
    //Vazio
    public Cliente(){
        this.codCliente="";
        this.listaCompras=new HashMap<Integer,Compra>();
    }
    
    //Parametrizado
    public Cliente(String cod, HashMap <Integer,Compra> lCompras){
        this.codCliente=cod;
        HashMap <Integer,Compra> novoLisComp = new HashMap <> ();
        for(Compra c : this.listaCompras.values()){
            novoLisComp.put(c.getMes(),c.clone());
        }
    }
    
    //Por copia
    public Cliente(Cliente c){
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
    
    public void setCodCliente(String cod){
        
    }
}
