/**
 * Classe referente ao catálogo de Clientes
 * 
 * @author (Carlos Sá A59905, Filipe Oliveira A57816, Sérgio Caldas A57779) 
 * @version (a version number or a date)
 */

import java.util.TreeSet;

public class Catalogo_Clientes{
    
    private TreeSet <String> cataCli;
    
    /**
     * Construtores
     */
    public Catalogo_Clientes(){
        this.cataCli = new TreeSet <String> ();
    }
    
    public Catalogo_Clientes(TreeSet <String> catP){
        this.cataCli=catP;
    }
    
    public Catalogo_Clientes(Catalogo_Clientes cc){
        this.cataCli=cc.getCataCli();
    }
    
    /**
     * gets and sets
     */
    public TreeSet <String> getCataCli(){
        return this.cataCli;
    }
    
    public void setCataCli(TreeSet <String> cc){
        this.cataCli = cc;
    }
    
        /**
     * Método que adiciona um codigo de cliente ao catalogo de clientes
     */
    public void addCodToCatalCli (String s){
        this.cataCli.add(s);
    }
    
    /**
     * Método que remove um codigo de cliente ao catalogo de clientes2
     */
    public void removeCodFromCatalCli (String s){
        this.cataCli.remove(s);
    } 
    
    /**
     * equals
     */
        
    public boolean equals(Object o) {
        if(this==o) return true;
        if((o==null) || this.getClass()!=o.getClass()) return false;
        else{
            Catalogo_Clientes cp = (Catalogo_Clientes) o;
            if(this.cataCli.equals(cp.getCataCli())) return true;
            else return false;
        }
    }
    
        /**
     * toString
     */
    public String toString() {
        StringBuilder s= new StringBuilder();
        s.append("\nCatalogo Clientes");
        for(String st : this.cataCli){
            s.append("\nCodigo Cliente: "+st);
        }
        return s.toString();
    }
    
    /**
     * clone
     */
    public Catalogo_Clientes clone() {
        return new Catalogo_Clientes(this);
    } 
}
