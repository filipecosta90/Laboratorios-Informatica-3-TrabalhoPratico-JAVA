
/**
 * Write a description of class Contabilidade here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.TreeMap;
import java.util.Map;
import java.io.Serializable;
import java.util.ArrayList;

public class Contabilidade implements Serializable{

  // HashMap com key: codigoProduto , valor: ComprasProduto 
  private TreeMap <String,ComprasProduto> listaTotalComprasProdutos;


  // Construtores

  //Vazio
  public Contabilidade(){
    this.listaTotalComprasProdutos = new TreeMap <String,ComprasProduto> ();
  }

  //Parametrizado
  public Contabilidade ( TreeMap <String, ComprasProduto> mapCopia ){
    this.listaTotalComprasProdutos = new TreeMap <String,ComprasProduto> ();
    for ( String codigoProduto : mapCopia.keySet() ){
      this.listaTotalComprasProdutos.put( codigoProduto , mapCopia.get(codigoProduto));
    }
  }

  //Copia
  public Contabilidade ( Contabilidade copia ){
    this.listaTotalComprasProdutos = new TreeMap <String,ComprasProduto> ();
    TreeMap <String, ComprasProduto> mapCopia = copia.getMapComprasProduto();
    for ( String codigoProduto : mapCopia.keySet() ){
      this.listaTotalComprasProdutos.put( codigoProduto , mapCopia.get(codigoProduto));
    }
  }


  //Getters e Setters
  public TreeMap <String, ComprasProduto> getMapComprasProduto(){
    TreeMap <String, ComprasProduto> mapCopia = new TreeMap <String,ComprasProduto> ();
    for ( String codigoProduto : this.listaTotalComprasProdutos.keySet() ){
      mapCopia.put( codigoProduto , this.listaTotalComprasProdutos.get(codigoProduto).clone());
    }
    return mapCopia;
  }



  /** QUERIE 1 - Lista ordenada com os códigos dos produtos nunca comprados e respectivo total */
  public ArrayList<String> codProdutosNuncaComprados(CatalogoProdutos catalogoProdutos){
    ArrayList<String> listaCodProdutosNuncaComprados = new ArrayList<>();

    for ( String codProduto : catalogoProdutos.getCodigosProdutos()){
      if ( this.listaTotalComprasProdutos.containsKey(codProduto) == false ){
        listaCodProdutosNuncaComprados.add(codProduto);
      }
    }

    return listaCodProdutosNuncaComprados;
  }

  /**
   * Querie 5 - Dado o código de um produto existente, determinar, mês a mês, quantas vezes foi
   * comprado, por quantos clientes diferentes e o total facturado;
   */
  public ArrayList <String> querie5 (String codProduto){
      ArrayList<String> listaQuerie5 = new ArrayList<>();
      ComprasProduto comprasProdutoQuerie5 = this.listaTotalComprasProdutos.get(codProduto);
      listaQuerie5=comprasProdutoQuerie5.getMapComprasMensal();
      return listaQuerie5;
  }
  

  public void adicionaCompraContabilidade( String codigoProduto, float preco , int quantidade , String tipoCompra, String codigoCliente , int mes  ){

  }

  public void adicionaCompraContabilidadeDeLinha (){
  }
}
