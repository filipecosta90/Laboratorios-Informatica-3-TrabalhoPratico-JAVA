/**
 * Classe referente as compras de um cliente
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.io.Serializable;
import java.util.TreeMap;
import java.util.HashSet;
import java.util.TreeSet;

public class ComprasCliente implements Serializable{

  private TreeMap <Integer, HashSet<Compra>> listaComprasCliente;

  public ComprasCliente(){
    this.listaComprasCliente = new TreeMap <Integer, HashSet<Compra>>();
    for ( int mes = 1 ; mes <= 12 ; mes++ ){
      HashSet<Compra> listaMensal = new HashSet<Compra>();
      this.listaComprasCliente.put(mes , listaMensal );
    }
  }

  public ComprasCliente( String codigoProduto , float preco, int quantidade, String tipoCompra, String codigoCliente , int mesCompra ){
    this.listaComprasCliente = new TreeMap <Integer, HashSet<Compra>>();
    Compra compraAdicionar = new Compra ( codigoProduto , preco , quantidade , tipoCompra , codigoCliente , mesCompra );
    for ( int mes = 1 ; mes <= 12 ; mes++ ){
      HashSet<Compra> listaMensal = new HashSet<Compra>();
      if ( mes == mesCompra ){
        listaMensal.add (compraAdicionar);
      }
      this.listaComprasCliente.put(mes , listaMensal );
    }
  }

  /*
   * este método está mal! tem que ser corrigido!!
   */
  public ComprasCliente(TreeMap <Integer, HashSet<Compra>> lista){
    HashSet <Compra> novaCompras = new HashSet <> ();
    TreeMap <Integer, HashSet<Compra>> novaLista = new TreeMap <> ();
    for(HashSet <Compra> cmp : lista.values()){
      for(Compra c : cmp){
        novaCompras.add(c.clone());
        novaLista.put(c.getMes(),novaCompras);
      }
    }
  }


  /*
   * este método está mal! tem que ser corrigido!!
   */
  public ComprasCliente(ComprasCliente cc){
    TreeMap <Integer, HashSet<Compra>> novaLista = new TreeMap <Integer, HashSet<Compra>> ();
    HashSet <Compra> novaCompras = new HashSet <Compra> ();
    for(HashSet <Compra> cmp : cc.getListaComprasCliente().values()){
      for(Compra c : cmp){
        novaCompras.add(c.clone());
        novaLista.put(c.getMes(),novaCompras);
      }
    }
  }

  /**
   * Getters and Setters
   */

  /**
   * Perguntar ao prof problema com o clone
   */
  public TreeMap <Integer,HashSet<Compra>> getListaComprasCliente(){
    TreeMap <Integer, HashSet<Compra> > novaLista = new TreeMap <Integer,HashSet<Compra>> ();
    HashSet <Compra> novaCompras = new HashSet <Compra>();
    for(HashSet<Compra> cmp : this.listaComprasCliente.values()){
      for(Compra c: cmp){
        novaCompras.add(c.clone());
        novaLista.put(c.getMes(),novaCompras);
      }
    }
    return novaLista;
  }

  public void adicionaCompra( String codigoProduto , float preco , int quantidade , String tipoCompra , String codigoCliente , int mes ){
    HashSet<Compra> listaMensal = this.listaComprasCliente.get(mes);
    Compra compraAdicionar = new Compra ( codigoProduto , preco , quantidade , tipoCompra , codigoCliente , mes );
    listaMensal.add (compraAdicionar);
  }

  /** Método auxiliar Q4 para retornar a String com informação: |Mes|Compras|Produtos|Total Gasto|Total Acumulado */
  public String getMapComprasMensal(){
    StringBuilder mapaString = new StringBuilder();
    float totalAnual = 0.0f;
    mapaString.append("Mês\t#Compras\t#Produtos\tTotal Gasto\tTotal Acumulado\n");
    for(Integer mes : this.listaComprasCliente.keySet()){
      HashSet <Compra> comprasMensais = this.listaComprasCliente.get(mes);
      mapaString.append("\n").append(mes);
      mapaString.append("\t").append(comprasMensais.size());
      mapaString.append("\t").append(getNumeroProdutosMes(comprasMensais));
      float totalMensal = getTotalFacturadoMes(comprasMensais);
      totalAnual+= totalMensal;
      mapaString.append("\t").append(totalMensal);
      mapaString.append("\t").append(totalAnual);
      mapaString.append("\n");
    }
    mapaString.append("Total Anual: ").append(totalAnual);
    return mapaString.toString();
  }


  /** Método auxiliar Q4 que retorna o número de produtos distintos vendidos num dado mês  */
  private int getNumeroProdutosMes(HashSet<Compra> comprasMensais){
    TreeSet<String> produtosDistintos = new TreeSet<>();

    for(Compra compraAtual : comprasMensais){
      produtosDistintos.add(compraAtual.getCodigoProduto());
    }
<<<<<<< HEAD
    
    
     /** toString */
    @Override
=======

    return produtosDistintos.size();
  }


  /** Método auxiliar Q4 que retorna o total Faturado */
  private float getTotalFacturadoMes(HashSet<Compra> comprasMensais){
    float totalFaturado = 0.0f;

    for(Compra compraAtual : comprasMensais){
      totalFaturado += compraAtual.getTotalFaturado();
    }

    return totalFaturado;
  }




  /** toString */
  @Override
>>>>>>> becbac7978bfde2add279598c3b5593bc59574ed
    public String toString(){
      StringBuilder s= new StringBuilder();
      s.append("\nInformação de Compras");
      s.append("\nCompras do Cliente:");
      for(HashSet <Compra> cmp : this.listaComprasCliente.values()){
        for(Compra c : cmp){
          s.append("\n"+cmp.toString());
        }
      }
      return s.toString();
    }

  /** Equals */
  @Override
    public boolean equals(Object o) {
      if(this==o) return true;
      if((o==null) || this.getClass()!=o.getClass()) return false;
      else{
        ComprasCliente cc = (ComprasCliente) o;
        if(this.listaComprasCliente.equals(cc.getListaComprasCliente())) return true;
        else return false;
      }
    }
<<<<<<< HEAD
    
    /** Método clone */
    @Override
=======

  /**
   * Método clone
   */
  @Override
>>>>>>> becbac7978bfde2add279598c3b5593bc59574ed
    public ComprasCliente clone() {
      return new ComprasCliente(this);
    }


}
