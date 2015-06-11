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

  // chave codigoCliente 
  private TreeMap <String,ComprasCliente> listaTotalCompras;
  private int comprasValidadas;
  private int comprasRejeitadas;

  public Compras (){
    this.listaTotalCompras = new TreeMap < String , ComprasCliente > ();
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
    TreeMap<String,ComprasCliente> listaTotal = new TreeMap<>();

    for (String codCliente : this.listaTotalCompras.keySet()){
      listaTotal.put( codCliente, this.listaTotalCompras.get(codCliente).clone() );
    }
    return listaTotal;
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

  public void incrementaComprasValidadas(){
    this.comprasValidadas++;
  }

  public void incrementaComprasRejeitadas(){
    this.comprasRejeitadas++;

  }

  public void adicionaCompra( String codigoProduto, float preco , int quantidade , String tipoCompra, String codigoCliente , int mes){
    ComprasCliente comprasClienteAssociado = null;
    if ( this.listaTotalCompras.containsKey(codigoCliente) ){
      comprasClienteAssociado = this.listaTotalCompras.get(codigoCliente);
      comprasClienteAssociado.adicionaCompra( codigoProduto , preco, quantidade, tipoCompra, codigoCliente , mes );
    }
    else {
      comprasClienteAssociado = new ComprasCliente ( codigoProduto , preco, quantidade, tipoCompra, codigoCliente,  mes );
      this.listaTotalCompras.put (codigoCliente , comprasClienteAssociado );
    }
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
      StringBuilder sb = new StringBuilder("----- Lista Total de Compras -----\n");
      sb.append("Total de Compras Validadas: " + this.comprasValidadas + "\n");
      sb.append("Total de Compras Rejeitadas: " + this.comprasRejeitadas + "\n");
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


  /** QUERIE 3 Dado um mês válido, determinar o número total de compras e o total de clientes distintos que as realizaram */

}   








