
/**
 * Write a description of class Contabilidade here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.HashMap;
import java.io.Serializable;

public class Contabilidade implements Serializable{
    private String codProduto;
    private HashMap <Integer,Compra> listaCompras;
    
    /**
     * Construtores
     */
    //Vazio
    public Contabilidade(){
        this.codProduto="";
        this.listaCompras=new HashMap<Integer,Compra>();
    }
    
    //Parametrizado
    public Contabilidade(String cod, HashMap <Integer,Compra> lCompras){
        this.codProduto=cod;
        HashMap <Integer,Compra> novoLisComp = new HashMap <> ();
        for(Compra c : this.listaCompras.values()){
            novoLisComp.put(c.getMes(),c.clone());
        }
    }
    
    //Por copia
    public Contabilidade(Contabilidade c){
        this.codProduto=c.getCodProduto();
        HashMap <Integer,Compra> novoLisComp = new HashMap <> ();
        for(Compra cmp : c.getListaCompras().values()){
            novoLisComp.put(cmp.getMes(),cmp.clone());
        }
    }
    
    /**
     * Getter and Setter
     */
    public String getCodProduto(){
        return this.codProduto;
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
        s.append("\nCodigo Produto: "+this.codProduto);
        s.append("\nCompras do Produto:");
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
            Contabilidade c = (Contabilidade) o;
            if(this.codProduto.equals(c.getCodProduto()) && this.listaCompras.equals(c.getListaCompras())) return true;
            else return false;
        }
    }
    
        /**
     * Método clone
     */
    @Override
    public Contabilidade clone() {
        return new Contabilidade(this);
    }    
}
