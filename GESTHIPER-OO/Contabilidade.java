
/**
 * Write a description of class Contabilidade here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.TreeMap;
import java.util.Map;
import java.io.Serializable;

public class Contabilidade implements Serializable{

  // HashMap com key: codigoProduto , valor: ComprasProduto 
  private TreeMap <String,ComprasProduto> listaTotalComprasProdutos;

  public Contabilidade(){
    this.listaTotalComprasProdutos = new TreeMap <String,ComprasProduto> ();
  }

  public Contabilidade ( TreeMap <String, ComprasProduto> mapCopia ){
    this.listaTotalComprasProdutos = new TreeMap <String,ComprasProduto> ();
    for ( String codigoProduto : mapCopia.keySet() ){
      this.listaTotalComprasProdutos.put( codigoProduto , mapCopia.get(codigoProduto));
    }
  }

  public Contabilidade ( Contabilidade copia ){
    this.listaTotalComprasProdutos = new TreeMap <String,ComprasProduto> ();
    TreeMap <String, ComprasProduto> mapCopia = copia.getMapComprasProduto();
    for ( String codigoProduto : mapCopia.keySet() ){
      this.listaTotalComprasProdutos.put( codigoProduto , mapCopia.get(codigoProduto));
    }
  }

  public TreeMap <String, ComprasProduto> getMapComprasProduto(){
    TreeMap <String, ComprasProduto> mapCopia = new TreeMap <String,ComprasProduto> ();
    for ( String codigoProduto : this.listaTotalComprasProdutos.keySet() ){
      mapCopia.put( codigoProduto , this.listaTotalComprasProdutos.get(codigoProduto).clone());
    }
    return mapCopia;
  }

  public void adicionaCompraContabilidade( String codigoProduto, float preco , int quantidade , String tipoCompra, String codigoCliente ){

  }

  public void adicionaCompraContabilidadeDeLinha (){
  }
}
