/**
 * Classe referente ao catálogo de Produtos
 * 
 * @author (Carlos Sá A59905, Filipe Oliveira A57816, Sérgio Caldas A57779) 
 * @version (a version number or a date)
 */
import java.io.Serializable;
import java.util.TreeSet;

public class CatalogoProdutos implements Serializable{
    
    private TreeSet <String> cataProd;
    
    /**
     * Construtores
     */
    public CatalogoProdutos(){
        this.cataProd = new TreeSet <String> ();
    }
    
    public CatalogoProdutos(TreeSet <String> catP){
        TreeSet <String> novoCatP = new TreeSet <> ();
        for(String s: this.cataProd){
            novoCatP.add(s);
        }
    }
    
    public CatalogoProdutos(CatalogoProdutos cp){
        TreeSet <String> novoCatP = new TreeSet <> ();
        for(String s : cp.getCataProd()){
            novoCatP.add(s);
        }
    }
    
    /**
     * gets and sets
     */
    public TreeSet <String> getCataProd(){
        TreeSet <String> novoCatP = new TreeSet <> ();
        for(String s: this.cataProd){
            novoCatP.add(s);
        }
        return novoCatP;
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
     * Método que verifica se o codifo do produto existe no catalogo
     */
    public boolean existeProd(String cod){  
        if(this.cataProd.contains(cod)==true){return true;}
        else{return false;}
    }
    
    /**
     * equals
     */
    @Override    
    public boolean equals(Object o) {
        if(this==o) return true;
        if((o==null) || this.getClass()!=o.getClass()) return false;
        else{
            CatalogoProdutos cp = (CatalogoProdutos) o;
            if(this.cataProd.equals(cp.getCataProd())) return true;
            else return false;
        }
    }
    
    /**
     * toString
     */
    @Override    
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
    @Override    
    public CatalogoProdutos clone() {
        return new CatalogoProdutos(this);
    } 
}
