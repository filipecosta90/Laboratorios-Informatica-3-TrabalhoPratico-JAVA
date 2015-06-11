
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
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;

public class Contabilidade implements Serializable{

  // HashMap com key: codigoProduto , valor: ComprasProduto 
  private TreeMap <String,ComprasProduto> listaTotalComprasProdutos;
  // mapa mes -> faturacaoTotal
  private TreeMap <Integer,Float> mapaFacturacaoMensal;
  private int comprasValidadas;

  // Construtores

  //Vazio
  public Contabilidade(){
    this.listaTotalComprasProdutos = new TreeMap <String,ComprasProduto> ();
    this.comprasValidadas = 0;
  }

  //Parametrizado
  public Contabilidade ( TreeMap <String, ComprasProduto> mapCopia ){
    this.comprasValidadas = 0;
    this.listaTotalComprasProdutos = new TreeMap <String,ComprasProduto> ();
    for ( String codigoProduto : mapCopia.keySet() ){
      this.listaTotalComprasProdutos.put( codigoProduto , mapCopia.get(codigoProduto));
    }
  }

  //Copia
  public Contabilidade ( Contabilidade copia ){
    this.comprasValidadas = 0;
    this.listaTotalComprasProdutos = new TreeMap <String,ComprasProduto> ();
    TreeMap <String, ComprasProduto> mapCopia = copia.getMapComprasProduto();
    for ( String codigoProduto : mapCopia.keySet() ){
      this.listaTotalComprasProdutos.put( codigoProduto , mapCopia.get(codigoProduto));
    }
  }

  //Getters e Setters

  public int getComprasValidadas(){
    return this.comprasValidadas;
  }

  public void setComprasValidadas(int valComprasValidadas){
    this.comprasValidadas = valComprasValidadas;
  }

  public void incrementaComprasValidadas(){
    this.comprasValidadas++;
  }

  public TreeMap <String, ComprasProduto> getMapComprasProduto(){
    TreeMap <String, ComprasProduto> mapCopia = new TreeMap <String,ComprasProduto> ();
    for ( String codigoProduto : this.listaTotalComprasProdutos.keySet() ){
      mapCopia.put( codigoProduto , this.listaTotalComprasProdutos.get(codigoProduto).clone());
    }
    return mapCopia;
  }

  /** Método para gravar a Contabilidade em ficheiro de objecto */
  public void gravaEmObjecto(String ficheiro) throws IOException {
        ObjectOutputStream objStreamOut = new ObjectOutputStream(new FileOutputStream(ficheiro));
        
        objStreamOut.writeObject(this);
        objStreamOut.flush();
        objStreamOut.close();
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
  
  /**
   * Querie 6 - Dado o código de um produto existente, determinar, mês a mês, quantas vezes foi
   * comprado em modo N e em modo P e respectivas facturações;
   */
    public ArrayList <String> querie6 (String codProduto){
    ArrayList<String> listaQuerie6 = new ArrayList<>();
    ComprasProduto comprasProdutoQuerie6 = this.listaTotalComprasProdutos.get(codProduto);
    listaQuerie6=comprasProdutoQuerie6.getMapComprasMensalModo();
    return listaQuerie6;
  }

  public void adicionaCompraContabilidade( String codigoProduto, float preco , int quantidade , String tipoCompra, String codigoCliente , int mes){
    ComprasProduto comprasProdutoAssociado = null;
    incrementaComprasValidadas();
    if ( this.listaTotalComprasProdutos.containsKey(codigoProduto) ){
      comprasProdutoAssociado = this.listaTotalComprasProdutos.get(codigoProduto);
      comprasProdutoAssociado.adicionaCompra( codigoProduto , preco, quantidade, tipoCompra, codigoCliente , mes );
    }
    else {
      comprasProdutoAssociado = new ComprasProduto ( codigoProduto , preco, quantidade, tipoCompra, codigoCliente,  mes );
      this.listaTotalComprasProdutos.put (codigoProduto , comprasProdutoAssociado );
    }
  }

  /** toString */
  @Override
    public String toString(){
      StringBuilder sb = new StringBuilder("----- Contabilidade :: Módulo Relacciona Compras->Produtos -----\n");
      sb.append("Total de Vendas Validadas: " + this.comprasValidadas + "\n");
      sb.append("Total de Produtos com Vendas Associadas: " + this.listaTotalComprasProdutos.size() + "\n");
      return sb.toString();
    }
}
