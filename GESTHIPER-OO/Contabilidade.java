
/**
 * Write a description of class Contabilidade here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Iterator;
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
  private int numeroComprasZero;

  // Construtores

  //Vazio
  public Contabilidade(){
    this.listaTotalComprasProdutos = new TreeMap <> ();
    this.mapaFacturacaoMensal = new TreeMap<>();
    this.comprasValidadas = 0;
    this.numeroComprasZero = 0;
  }

  //Parametrizado
  public Contabilidade ( TreeMap <String, ComprasProduto> mapCopia, TreeMap <Integer,Float> facturacaoMensal){
    this.comprasValidadas = 0;
    numeroComprasZero = 0;
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
    this.numeroComprasZero = contabilidade.getNumeroComprasZero();
  }

  //Getters e Setters

  public int getComprasValidadas(){
    return this.comprasValidadas;
  }
  
  public int getNumeroComprasZero(){
    return this.numeroComprasZero;
    }

  public void setComprasValidadas(int valComprasValidadas){
    this.comprasValidadas = valComprasValidadas;
  }

  public void incrementaComprasValidadas(){
    this.comprasValidadas++;
  }

  public void incrementaComprasZero(){
     this.numeroComprasZero++;
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
  public ArrayList<String> querie1(CatalogoProdutos catalogoProdutos){
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
  
/*
* Interactiva :: Querie 8
* Determinar o conjunto dos X produtos mais vendidos em todo o ano (em número de
* unidades vendidas) indicando o número total de distintos clientes que o compraram
* (X é um inteiro dado pelo utilizador);
*/
 public ArrayList <String> querie8 (int topN ){
    ArrayList<String> listaQuerie8 = new ArrayList<>();
    TreeSet <Triplo_Produto_Unidades_Vendas> topVendas = new TreeSet <>( new ComparatorUnidades_Triplo_Produto_Unidades_Vendas() );
    for ( String codigoProduto : this.listaTotalComprasProdutos.keySet() ){
        ComprasProduto comprasProdutoActual = this.listaTotalComprasProdutos.get(codigoProduto);
        int numeroUnidadesVendidas = comprasProdutoActual.getNumeroUnidadesVendidas();
        Triplo_Produto_Unidades_Vendas parActual = new Triplo_Produto_Unidades_Vendas ( codigoProduto, numeroUnidadesVendidas , 0 );
        topVendas.add(parActual);   
    }
    Iterator<Triplo_Produto_Unidades_Vendas> iteradorTop=topVendas.iterator();
    int nActual = 1;
    while(iteradorTop.hasNext() && nActual < topN ){
      StringBuilder linha = new StringBuilder ();
      Triplo_Produto_Unidades_Vendas triploActual = iteradorTop.next();
      linha.append( triploActual.ProdutoUnidadesVendidasString() );
      listaQuerie8.add(linha.toString());
      nActual++;
    }
    return listaQuerie8;
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
    if ( preco == 0) {
        incrementaComprasZero();
    }
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
   * Consulta Estatística 1.1 P8
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
  
    /* 
   * Consulta Estatística :: 1.1 P7 
   * Numero total de compras de valor igual a 0
   */
  public int estatisticas_1_1_P7(){
      return this.numeroComprasZero;
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
