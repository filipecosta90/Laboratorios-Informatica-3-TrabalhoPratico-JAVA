
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
    this.listaTotalComprasProdutos = new TreeMap <> ();
    this.mapaFacturacaoMensal = new TreeMap<>();
    this.comprasValidadas = 0;
  }

  //Parametrizado
  public Contabilidade ( TreeMap <String, ComprasProduto> mapCopia, TreeMap <Integer,Float> facturacaoMensal){
    this.comprasValidadas = 0;
    this.listaTotalComprasProdutos = new TreeMap<>();
    for ( String codigoProduto : mapCopia.keySet() ){
      this.listaTotalComprasProdutos.put( codigoProduto , mapCopia.get(codigoProduto));
    }
    this.mapaFacturacaoMensal = new TreeMap<>();
    for(int mes : facturacaoMensal.keySet()){
       this.mapaFacturacaoMensal.put(mes,facturacaoMensal.get(mes));
    }
  }

  //Copia
  public Contabilidade ( Contabilidade contabilidade ){
      this.listaTotalComprasProdutos = contabilidade.getMapComprasProduto();
      this.mapaFacturacaoMensal = contabilidade.getMapaFactuaracaoMensal();
    this.comprasValidadas = contabilidade.getComprasValidadas();
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
    TreeMap <String, ComprasProduto> mapCopia = new TreeMap <> ();
    for ( String codigoProduto : this.listaTotalComprasProdutos.keySet() ){
      mapCopia.put( codigoProduto , this.listaTotalComprasProdutos.get(codigoProduto).clone());
    }
    return mapCopia;
  }
  
  public TreeMap <Integer,Float> getMapaFactuaracaoMensal(){
      TreeMap <Integer,Float> facturacaoMensal = new TreeMap <>();
      for(int mes : this.mapaFacturacaoMensal.keySet()){
          facturacaoMensal.put(mes,this.mapaFacturacaoMensal.get(mes));
      }
      return facturacaoMensal;
  }

  /** Método para gravar a Contabilidade em ficheiro de objecto */
  public void gravaEmObjecto(String ficheiro) throws IOException {
    ObjectOutputStream objStreamOut = new ObjectOutputStream(new FileOutputStream(ficheiro));
    objStreamOut.writeObject(this);
    objStreamOut.flush();
    objStreamOut.close();
  }


  /*
   * Interactiva :: Querie 1 
   * Lista ordenada com os códigos dos produtos nunca comprados e respectivo total
   */
  public ArrayList<String> codProdutosNuncaComprados(CatalogoProdutos catalogoProdutos){
    ArrayList<String> listaCodProdutosNuncaComprados = new ArrayList<>();

    for ( String codProduto : catalogoProdutos.getCodigosProdutos()){
      if ( this.listaTotalComprasProdutos.containsKey(codProduto) == false ){
        listaCodProdutosNuncaComprados.add(codProduto);
      }
    }

    return listaCodProdutosNuncaComprados;
  }

  /*
   * Interactiva :: Querie 5 
   * Dado o código de um produto existente, determinar, mês a mês, quantas vezes foi
   * comprado, por quantos clientes diferentes e o total facturado;
   */
  public ArrayList <String> querie5 (String codProduto){
    ArrayList<String> listaQuerie5 = new ArrayList<>();
    ComprasProduto comprasProdutoQuerie5 = this.listaTotalComprasProdutos.get(codProduto);
    listaQuerie5=comprasProdutoQuerie5.getMapComprasMensal();
    return listaQuerie5;
  }

  /*
   * Interactiva :: Querie 6 
   * Dado o código de um produto existente, determinar, mês a mês, quantas vezes foi
   * comprado em modo N e em modo P e respectivas facturações;
   */
  public ArrayList <String> querie6 (String codProduto){
    ArrayList<String> listaQuerie6 = new ArrayList<>();
    ComprasProduto comprasProdutoQuerie6 = this.listaTotalComprasProdutos.get(codProduto);
    listaQuerie6=comprasProdutoQuerie6.getMapComprasMensalModo();
    return listaQuerie6;
  }
  
  private void adicionaFaturacaoAoMapaMensal(int mesCompra , int quantidade, float preco){
      float facturacaoMes = 0.0f;
      if(this.mapaFacturacaoMensal.containsKey(mesCompra)){
          facturacaoMes=this.mapaFacturacaoMensal.get(mesCompra);
          facturacaoMes+=quantidade*preco;
          this.mapaFacturacaoMensal.replace(mesCompra,facturacaoMes);
      }
      else{
        facturacaoMes+=quantidade*preco;
        this.mapaFacturacaoMensal.put(mesCompra,facturacaoMes);
      }
  }

  public void adicionaCompraContabilidade( String codigoProduto, float preco , int quantidade , String tipoCompra, String codigoCliente , int mes){
    ComprasProduto comprasProdutoAssociado = null;
    incrementaComprasValidadas();
    adicionaFaturacaoAoMapaMensal(mes,quantidade,preco);
    if ( this.listaTotalComprasProdutos.containsKey(codigoProduto) ){
      comprasProdutoAssociado = this.listaTotalComprasProdutos.get(codigoProduto);
      comprasProdutoAssociado.adicionaCompra( codigoProduto , preco, quantidade, tipoCompra, codigoCliente , mes );
    }
    else {
      comprasProdutoAssociado = new ComprasProduto ( codigoProduto , preco, quantidade, tipoCompra, codigoCliente,  mes );
      this.listaTotalComprasProdutos.put (codigoProduto , comprasProdutoAssociado );
    }
  }
  
  /* 
   * Consulta Estatística :: 1.1 P3 
   * Numero Total de Produtos Comprados
   */
  public int estatisticas_1_1_P3(){
      return this.listaTotalComprasProdutos.size();
    }
  
  /*
   * Estatisticas 1.1 P8
   * Método auxiliar querie 1.2 P2 que calcula o total anual
   */
  private float facturacaoAnual(){
      float facturacao = 0.0f;
      for(Float facturacaoActual : this.mapaFacturacaoMensal.values()){
          facturacao+=facturacaoActual;
      }
      return facturacao;
  }
  
  /* 
   * Consulta Estatística :: 1.2 P2 
   * Facturação total por mês (valor total das compras/vendas) e total global
   */
  public ArrayList <String> estatisticas_1_2_P2 (){
      ArrayList <String> querie122Info = new ArrayList<>();
      StringBuilder cabecalho = new StringBuilder();
      cabecalho.append("----Facturação total por mês (valor total das compras/vendas) e total global----");
      querie122Info.add(cabecalho.toString());
      for(Integer mes : this.mapaFacturacaoMensal.keySet()){
          StringBuilder linha = new StringBuilder();
          linha.append("Mes: "+mes).append("\t");
          linha.append("Facturado: "+this.mapaFacturacaoMensal.get(mes)).append("");
          querie122Info.add(linha.toString());
      }
      StringBuilder totalAnual = new StringBuilder();
      totalAnual.append("\nFacturacao Anual: "+facturacaoAnual());
      querie122Info.add(totalAnual.toString());
      return querie122Info;
  }
  
  /** toString */
  @Override
    public String toString(){
      StringBuilder sb = new StringBuilder("----- Contabilidade :: Módulo Relacciona Compras->Produtos -----\n");
      sb.append("Total de Vendas Validadas: " + this.comprasValidadas + "\n");
      sb.append("Total de Produtos com Vendas Associadas: " + this.listaTotalComprasProdutos.size() + "\n");
      return sb.toString();
    }
    
  /** Equals */
  @Override
    public boolean equals(Object o) {
      boolean resultado = false;
      //mesmo objecto
      if(this==o) {
        resultado = true;
      }
      // objecto nulo ou de classe diferente
      else if((o==null) || this.getClass()!=o.getClass()) {
        resultado = false; 
      }
      // objecto mesma classe
      else {
        Contabilidade that = (Contabilidade) o;
        if(this.listaTotalComprasProdutos.equals(that.getMapComprasProduto()) && ( this.mapaFacturacaoMensal.equals(that.getMapaFactuaracaoMensal()) ) 
            &&  (this.comprasValidadas == that.getComprasValidadas()) ){
          resultado = true;
            }
      }
      return resultado;
    }
    
    /** clone */
  @Override
  public Contabilidade clone(){
      return new Contabilidade(this);
  }
}