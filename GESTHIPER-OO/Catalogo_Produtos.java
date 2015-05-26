/**
 * Classe referente ao catálogo de Produtos
 * 
 * @author (Carlos Sá A59905, Filipe Oliveira A57816, Sérgio Caldas A57779) 
 * @version (a version number or a date)
 */

import java.util.TreeSet;

public class Catalogo_Produtos{
    
    private TreeSet <String> cataProd;
    
    /**
     * Construtores
     */
    public Catalogo_Produtos(){
        this.cataProd = new TreeSet <String> (new ComparatorProdutos());
    }
    
    public Catalogo_Produtos(TreeSet <String> catP){
        this.cataProd=catP;
    }
    
    public Catalogo_Produtos(Catalogo_Produtos cp){
        this.cataProd=cp.getCataProd();
    }
    
    /**
     * gets and sets
     */
    public TreeSet <String> getCataProd(){
        return this.cataProd;
    }
    
    public void setCataProd(TreeSet <String> cp){
        this.cataProd = cp;
    }
    
    /**
     * Método que adiciona um codigo de produto ao catalogo de produtos
     */
    public void addCodToCatalProd (String s){
        this.cataProd.add(s);
    }
    
    /**
     * Método que remove um codigo de produto ao catalogo de produtos
     */
    public void removeCodFromCatalProd (String s){
        this.cataProd.remove(s);
    }
    
    /**
     * equals
     */
        
    public boolean equals(Object o) {
        if(this==o) return true;
        if((o==null) || this.getClass()!=o.getClass()) return false;
        else{
            Catalogo_Produtos cp = (Catalogo_Produtos) o;
            if(this.cataProd.equals(cp.getCataProd())) return true;
            else return false;
        }
    }
    
    /**
     * toString
     */
    public String toString() {
        StringBuilder s= new StringBuilder();
        s.append("\nCatalogo Produtos");
        for(String st : this.cataProd){
            s.append("\n"+st);
        }
        return s.toString();
    }
    
    /**
     * clone
     */
    public Catalogo_Produtos clone() {
        return new Catalogo_Produtos(this);
    } 
}
