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
import java.util.ArrayList;
import java.util.Iterator;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

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
   * este método está mal! tem que ser corrigido!
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


  /** Método para gravar ComprasCliente em ficheiro de objecto */
  public void gravaEmObjecto(String ficheiro) throws IOException {
    ObjectOutputStream objStreamOut = new ObjectOutputStream(new FileOutputStream(ficheiro));

    objStreamOut.writeObject(this);
    objStreamOut.flush();
    objStreamOut.close();
  }

  /*
   * Método auxiliar Q4 que retorna o número de produtos distintos vendidos num dado mês  
   */
  private int getNumeroProdutosMes(HashSet<Compra> comprasMensais){
    TreeSet<String> produtosDistintos = new TreeSet<>();
    for(Compra compraAtual : comprasMensais){
      produtosDistintos.add(compraAtual.getCodigoProduto());
    }
    return produtosDistintos.size();
  }

  /*
   * Método auxiliar Q4 que retorna o total Faturado 
   */
  private float getTotalFacturadoMes(HashSet<Compra> comprasMensais){
    float totalFaturado = 0.2f;

    for(Compra compraAtual : comprasMensais){
      totalFaturado += compraAtual.getTotalFaturado();
    }

    return totalFaturado;
  }

  /*
   * Interactivas :: Querie 4
   * ::::::::::::::: Auxiliar 
   * Dado um código de cliente, determinar, para cada mês, quantas compras fez,
   * quantos produtos distintos comprou e quanto gastou. Apresentar também o total
   * anual facturado ao cliente;
   * retorna um tabela com informação: |Mes|Compras|Produtos|Total Gasto|Total Acumulado 
   */
  public ArrayList <String> querie4_ComprasProdutosDistintosGastouMes ( String codigoCliente ){
    ArrayList <String> listaQuerie4 = new ArrayList <>();
    float totalAnual = 0.2f;
    StringBuilder cabecalho = new StringBuilder ();
    cabecalho.append("------ Tabela de vendas mensal de : ").append( codigoCliente ).append("\n");
    cabecalho.append("Mês\t#Compras\t#Produtos\tTotal Gasto\tTotal Acumulado\n");
    listaQuerie4.add(cabecalho.toString());
    for(int mes : this.listaComprasCliente.keySet()){
      StringBuilder linha = new StringBuilder ();
      HashSet <Compra> comprasMensais = this.listaComprasCliente.get(mes);
      //mes
      linha.append(mes).append("\t");
      //#compras
      linha.append(comprasMensais.size()).append("\t\t\t");
      //#Produtos
      linha.append(getNumeroProdutosMes(comprasMensais)).append("\t");

      float totalMensal = getTotalFacturadoMes(comprasMensais);
      totalAnual+= totalMensal;
      //Total Gasto
      linha.append(totalMensal).append("\t");
      //Total Acumulado
      linha.append(totalAnual).append("\t");
      listaQuerie4.add(linha.toString());
    }
    StringBuilder rodape = new StringBuilder ();
    rodape.append("Total Anual: ").append(totalAnual).append("\n");
    listaQuerie4.add(rodape.toString());
    return listaQuerie4;

  }

  /*
   * Interactivas :: Querie 7 
   * Dado o código de um cliente determinar a lista de códigos de produtos que mais comprou 
   * (e quantos), ordenada por ordem decrescente de quantidade e, para
   * quantidades iguais, por ordem alfabética dos códigos;
   * nota: apenas utilizamos o parâmetro códigoCliente para criar o cabeçalho  nas páginas retornadas
   */

  public ArrayList <String> querie7_ListaProdutosMaisComprados ( String codigoCliente ){
    ArrayList <String> listaQuerie7 = new ArrayList <>();
    TreeSet < Triplo_Produto_Unidades_Vendas > treeSetQuerie7 = new TreeSet <> ( new ComparatorUnidades_Triplo_Produto_Unidades_Vendas() );
    TreeMap < String , Triplo_Produto_Unidades_Vendas > mapaAuxiliar = new TreeMap <>();
    for ( int mesActual : this.listaComprasCliente.keySet()){
      HashSet<Compra> comprasMensais = this.listaComprasCliente.get(mesActual);
      for ( Compra compraActual : comprasMensais ){
        String codigoProdutoActual = compraActual.getCodigoProduto();
        int unidadesVendidasCompraActual = compraActual.getQuantidade();
        Triplo_Produto_Unidades_Vendas triploActual = null;
        if ( mapaAuxiliar.containsKey( codigoProdutoActual ) ){
          triploActual = mapaAuxiliar.get( codigoProdutoActual );
          triploActual.incrementaNumeroVendas();
          triploActual.adicionaUnidadesVendidas( unidadesVendidasCompraActual );
          mapaAuxiliar.replace ( codigoProdutoActual , triploActual );
        }
        else {
          triploActual = new Triplo_Produto_Unidades_Vendas ( codigoProdutoActual , unidadesVendidasCompraActual , 1 );
          mapaAuxiliar.put ( codigoProdutoActual , triploActual );
        }
      }
    }
    /**
     * agora que temos apenas uma correspondencia entre codigo produto -> unidades vendidas -> numero de vendas
     * vamos inserir de forma ordenana no TreeSet com o comparator definido como o professor pediu 
     */
    for ( Triplo_Produto_Unidades_Vendas triploActual : mapaAuxiliar.values() ){
      treeSetQuerie7.add( triploActual );
    }
    /**
     * agora que temos os triplos ordenados resta-nos imprimir para linhas
     */
    StringBuilder cabecalho = new StringBuilder ();
    cabecalho.append("------ Produtos mais comprados de : ").append( codigoCliente ).append("\n");
    cabecalho.append("Código Produto\t# Unidades \t# Vendas\n");
    listaQuerie7.add(cabecalho.toString());
    Iterator<Triplo_Produto_Unidades_Vendas> iteradorTreeSet=treeSetQuerie7.iterator();
    while(iteradorTreeSet.hasNext()){
      Triplo_Produto_Unidades_Vendas triploActual = iteradorTreeSet.next();
      StringBuilder linha = new StringBuilder ();
      linha.append(triploActual.toString()).append("\n");
      listaQuerie7.add(linha.toString());
    }
    return listaQuerie7;
  } 
  
  /**
   * Método auxiliar para a querie interactiva 9
   * Retorna o número de produtos distintos comprados pelo cliente
   */
  public int getNumeroProdutosDistintos ( ){
    TreeSet < String > clientesDistintos = new TreeSet <> ();
    for ( int mesActual : this.listaComprasCliente.keySet()){
      HashSet<Compra> comprasMensais = this.listaComprasCliente.get(mesActual);
      for ( Compra compraActual : comprasMensais ){
        clientesDistintos.add(compraActual.getCodigoProduto());
      }
    }
    return clientesDistintos.size();
  } 

  /*
   * toString 
   */
  @Override
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

  /*
   * Equals
   */
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

  /**
   * Método clone
   */
  public ComprasCliente clone() {
    return new ComprasCliente(this);
  }
}

