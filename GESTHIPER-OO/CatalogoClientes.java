/**
 * Classe referente ao catálogo de Clientes
 * 
 * @author (Carlos Sá A59905, Filipe Oliveira A57816, Sérgio Caldas A57779) 
 * @version (a version number or a date)
 */
import java.io.Serializable;
import java.util.TreeSet;

public class CatalogoClientes implements Serializable{
    
    private TreeSet <String> cataCli;
    
    /**
     * Construtores
     */
    
    //Vazio
    public CatalogoClientes(){
        this.cataCli = new TreeSet <String> ();
    }
    
    //Parametrizado
    public CatalogoClientes(TreeSet <String> catP){
        TreeSet <String> novoCatC = new TreeSet <> ();
        for(String s : this.cataCli){
            novoCatC.add(s);
        }
    }
    
    //Copia
    public CatalogoClientes(CatalogoClientes cc){
        TreeSet <String> novoCatC = new TreeSet <> ();
        for(String s :cc.getCataCli()){
            novoCatC.add(s);
        }
    }
    
    /**
     * Getters e Setters
     */
    public TreeSet <String> getCataCli(){
        TreeSet <String> novoCatC = new TreeSet <> ();
        for(String s : this.cataCli){
            novoCatC.add(s);
        }
        return novoCatC;
    }
    
    public void setCataCli(TreeSet <String> cc){
        this.cataCli = cc;
    }
    
    
    
    /**
     * Método que adiciona um codigo de cliente ao catalogo de clientes
     */
    public void addCodToCatalCli (String s){
        this.cataCli.add( s );
    }
    
    /**
     * Método que remove um codigo de cliente ao catalogo de clientes2
     */
    public void removeCodFromCatalCli (String s){
        this.cataCli.remove(s);
    } 
    
    /**
     * Método que verifica se o codifo do cliente existe no catalogo
     */
    public boolean existeCli(String cod){  
        if(this.cataCli.contains(cod)==true){return true;}
        else{return false;}
    }
    
    /**
     * equals
     */
    @Override    
    public boolean equals(Object o) {
        if(this==o) return true;
        if((o==null) || this.getClass()!=o.getClass()) return false;

        CatalogoClientes cc = (CatalogoClientes) o;
        if(this.cataCli.equals(cc.getCataCli())) return true;
        else return false;

    }
    
        /**
     * toString
     */
    @Override
    public String toString() {
        StringBuilder s= new StringBuilder();
        s.append("\nCatalogo Clientes");
        for(String st : this.cataCli){
            s.append("\n"+st);
        }
        return s.toString();
    }
    
    /**
     * clone
     */
    @Override
    public CatalogoClientes clone() {
        return new CatalogoClientes(this);
    } 
}
