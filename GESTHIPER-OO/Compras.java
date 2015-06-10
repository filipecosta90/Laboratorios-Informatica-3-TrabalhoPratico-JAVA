/**
 * Classe referente a um cliente
 * 
 * @author (Carlos Sá A59905, Filipe Oliveira A57816, Sérgio Caldas A57779) 
 * @version (a version number or a date)
 */

import java.util.TreeMap;
import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;
import java.util.ArrayList;

public class Compras implements Serializable{
<<<<<<< HEAD
    
    //variáveis instância
    private TreeMap <String,ComprasCliente> listaTotalCompras;  //key = Código Cliente
    private int comprasValidadas;
    private int comprasRejeitadas;

        
    //Construtores
    
    //Vazio
    public Compras(){
        this.listaTotalCompras = new TreeMap <String,ComprasCliente>();
=======
   
    // chave codigoCliente 
    private TreeMap <String,ComprasCliente> listaTotalCompras;
     private int comprasValidadas;
     private int comprasRejeitadas;
     
     Compras (){
        this.listaTotalCompras = new TreeMap < String , ComprasCliente > ();
>>>>>>> fedd4c39556f0771878f1392b051e8c2c048abc3
        this.comprasValidadas = 0;
        this.comprasRejeitadas = 0;
    }
    
    //Parametrizado
    public Compras(TreeMap<String,ComprasCliente> listaTotComprasCliente, int comprasValidadas, int comprasRejeitadas){
        listaTotalCompras = new TreeMap<String,ComprasCliente>();
        for (String codCliente : listaTotComprasCliente.keySet()){
            listaTotalCompras.put(codCliente, listaTotComprasCliente.get(codCliente).clone() );
        }
        this.comprasValidadas = comprasValidadas;
        this.comprasRejeitadas = comprasRejeitadas;
    }
    
    
    //Cópia
    public Compras(Compras compras){
        this.listaTotalCompras = compras.getListaTotalCompras();    //método getListaTotalCompras() já realiza o clone();
        this.comprasValidadas = compras.getComprasValidadas();
        this.comprasRejeitadas = compras.getComprasRejeitadas();
    }
    
    
    
    
    //Getters e Setters
    public TreeMap<String,ComprasCliente> getListaTotalCompras(){
        TreeMap<String,ComprasCliente> aux = new TreeMap<>();
        
        for (String codCliente : this.listaTotalCompras.keySet()){
            aux.put( codCliente, this.listaTotalCompras.get(codCliente).clone() );
        }
        
        return aux;
    }
    
    public int getComprasValidadas(){
        return this.comprasValidadas;
    }
    
    public int getComprasRejeitadas(){
        return this.comprasRejeitadas;
    }
    
    public void setComprasValidadas(int valComprasValidadas){
        this.comprasValidadas = valComprasValidadas;
    }

    public void setComprasRejeitadas(int valComprasRejeitadas){
        this.comprasRejeitadas = valComprasRejeitadas;
    }
    
    
    
    
    /** Métodos complementares usuais */
    
    /** Equals */
    @Override
    public boolean equals(Object compras){
        
        if (this == compras) return true;
        if (compras == null || this.getClass() != compras.getClass() ) return false;
        
        Compras umaCompra = (Compras) compras;
        return( this.listaTotalCompras.equals(umaCompra) && 
                this.comprasValidadas == umaCompra.getComprasValidadas() && 
                this.comprasRejeitadas == umaCompra.getComprasRejeitadas());
    }
    
    /** toString */
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder("----- LISTA TOTAL DE COMPRAS -----\n");
        
        for ( String codCliente : listaTotalCompras.keySet() ){
            sb.append( "Cliente: " + codCliente + "\n");
            sb.append("Compras Realizadas: " + listaTotalCompras.get(codCliente).toString() + "\n"); 
        }
        sb.append("Total de Compras VALIDADAS: " + this.comprasValidadas + "\n");
        sb.append("Total de Compras REJEITADAS: " + this.comprasRejeitadas + "\n");
        
        return sb.toString();
    }
    
    /** clone */
    @Override
    public Compras clone(){
        return new Compras(this);
    }
    
    

    
    
    /** QUERIE 2 - Lista ordenada com os códigos dos clientes que nunca compraram e seu total; */
    public ArrayList<String> codClienteSemCompras( CatalogoClientes catalogoClientes ){
        ArrayList<String> listaCodClientesSemCompras = new ArrayList<>();
        
        for (String codCliente : catalogoClientes.getCodigosClientes() ){
            if ( this.listaTotalCompras.containsKey(codCliente) == false ){
                listaCodClientesSemCompras.add(codCliente);
            }
        }
        
        return listaCodClientesSemCompras;
    }
    
}   
   

   
    
    
    
    
    
