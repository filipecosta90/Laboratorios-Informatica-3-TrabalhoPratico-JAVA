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
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;

public class Compras implements Serializable{

  // chave codigoCliente 
  private TreeMap <String,ComprasCliente> listaTotalCompras;
  // mapa mes -> numero vendas
  private TreeMap <Integer , Integer> mapaVendasMensal;
  // mapa mes -> codigos Cliente
  private TreeMap <Integer , TreeSet<String> > mapaClientesMensal;
  private int comprasValidadas;

  public Compras (){
    this.listaTotalCompras = new TreeMap <> ();
    this.mapaVendasMensal = new TreeMap <>();
    this.mapaClientesMensal = new TreeMap <>();
    this.comprasValidadas = 0;
  }

  //Parametrizado
  public Compras(TreeMap<String,ComprasCliente> listaTotComprasCliente, TreeMap <Integer , Integer> vendasMensal, TreeMap <Integer,TreeSet<String>> clientesMensal, int comprasValidadas){
    this.listaTotalCompras = new TreeMap<>();
    for (String codCliente : listaTotComprasCliente.keySet()){
      this.listaTotalCompras.put(codCliente, listaTotComprasCliente.get(codCliente).clone() );
    }
    this.mapaVendasMensal = new TreeMap<>();
    for(int mes : vendasMensal.keySet()){
      this.mapaVendasMensal.put(mes,vendasMensal.get(mes));
    }
    this.mapaClientesMensal = new TreeMap<>();
    for(int mes : clientesMensal.keySet()){
      TreeSet <String> treeSetClientes = mapaClientesMensal.get(mes);
      Iterator<String> iteradorString = treeSetClientes.iterator();
      TreeSet <String> treeSetClientesMensal = new TreeSet <>();
      while(iteradorString.hasNext()){
        String clienteMensal = iteradorString.next();
        treeSetClientesMensal.add(clienteMensal);
      }
      this.mapaClientesMensal.put(mes,treeSetClientesMensal);
    }
    this.comprasValidadas = comprasValidadas;
  }

  //Cópia
  public Compras(Compras compras){
    this.listaTotalCompras = compras.getListaTotalCompras();    //método getListaTotalCompras() já realiza o clone();
    this.mapaVendasMensal = compras.getMapaVendasMensal();
    this.mapaClientesMensal = compras.getMapaClientesMensal();
    this.comprasValidadas = compras.getComprasValidadas();
  }

  //Getters e Setters
  public TreeMap <String,ComprasCliente> getListaTotalCompras(){
    TreeMap<String,ComprasCliente> listaTotal = new TreeMap<>();

    for (String codCliente : this.listaTotalCompras.keySet()){
      listaTotal.put( codCliente, this.listaTotalCompras.get(codCliente).clone() );
    }
    return listaTotal;
  }

  public TreeMap <Integer,Integer> getMapaVendasMensal(){
    TreeMap <Integer,Integer> vendasMes = new TreeMap<>();
    for(Integer mes : this.mapaVendasMensal.keySet()){
      vendasMes.put(mes,this.mapaVendasMensal.get(mes));
    }
    return vendasMes;
  }

  public TreeMap <Integer,TreeSet<String>> getMapaClientesMensal(){
    TreeMap <Integer,TreeSet<String>> clientesMes = new TreeMap<>();
    TreeSet <String> listaClientes = new TreeSet<>();
    for(Integer mes : this.mapaClientesMensal.keySet()){
      TreeSet<String> mapaClientesActual = this.mapaClientesMensal.get(mes);
      for(String codCliente : mapaClientesActual){
        listaClientes.add(codCliente);
      }
      clientesMes.put(mes,listaClientes);
    }
    return clientesMes;
  }

  public int getComprasValidadas(){
    return this.comprasValidadas;
  }

  public void setComprasValidadas(int valComprasValidadas){
    this.comprasValidadas = valComprasValidadas;
  }

  public void incrementaComprasValidadas(){
    this.comprasValidadas++;
  }

  private void adicionaCompraAoMapaMensal( int mesCompra ){
    int numeroVendasMes = 0;
    if (this.mapaVendasMensal.containsKey(mesCompra)){
      numeroVendasMes = this.mapaVendasMensal.get(mesCompra);
      numeroVendasMes++;
      this.mapaVendasMensal.replace (mesCompra , numeroVendasMes);
    }
    else{
      numeroVendasMes++;
      this.mapaVendasMensal.put ( mesCompra , numeroVendasMes );
    }
  }

  private void adicionaClienteAoMapaMensal ( int mesCompra , String codigoCliente ){
    TreeSet <String> treeSetClientesMensal = null;
    if (this.mapaClientesMensal.containsKey(mesCompra)){
      treeSetClientesMensal = this.mapaClientesMensal.get(mesCompra);
      treeSetClientesMensal.add(codigoCliente);
      this.mapaClientesMensal.replace (mesCompra , treeSetClientesMensal);
    }
    else{
      treeSetClientesMensal = new TreeSet<>();
      treeSetClientesMensal.add(codigoCliente);
      this.mapaClientesMensal.put ( mesCompra , treeSetClientesMensal );
    }

  }

  public void adicionaCompra( String codigoProduto, float preco , int quantidade , String tipoCompra, String codigoCliente , int mes){
    ComprasCliente comprasClienteAssociado = null;
    incrementaComprasValidadas();
    adicionaCompraAoMapaMensal(mes);
    adicionaClienteAoMapaMensal ( mes , codigoCliente);
    if ( this.listaTotalCompras.containsKey(codigoCliente) ){
      comprasClienteAssociado = this.listaTotalCompras.get(codigoCliente);
      comprasClienteAssociado.adicionaCompra( codigoProduto , preco, quantidade, tipoCompra, codigoCliente , mes );
    }
    else {
      comprasClienteAssociado = new ComprasCliente ( codigoProduto , preco, quantidade, tipoCompra, codigoCliente,  mes );
      this.listaTotalCompras.put (codigoCliente , comprasClienteAssociado );
    }
  }

  /**
   * Método para gravar as Compras em ficheiro de objecto 
   */
  public void gravaEmObjecto(String ficheiro) throws IOException {
    ObjectOutputStream objStreamOut = new ObjectOutputStream(new FileOutputStream(ficheiro));
    objStreamOut.writeObject(this);
    objStreamOut.flush();
    objStreamOut.close();
  }

  /**
   * Interactivas :: Querie 2
   * Lista ordenada com os códigos dos clientes que nunca compraram e seu total; 
   */
  public ArrayList<String> querie2( CatalogoClientes catalogoClientes ){
    ArrayList<String> listaCodClientesSemCompras = new ArrayList<>();

    for (String codCliente : catalogoClientes.getCodigosClientes() ){
      if ( this.listaTotalCompras.containsKey(codCliente) == false ){
        listaCodClientesSemCompras.add(codCliente);
      }
    }

    return listaCodClientesSemCompras;
  }

  /** 
   * Interactivas :: Querie 3
   * Dado um mês válido, determinar o número total de compras e o total de clientes distintos que as realizaram 
   */
  public ArrayList<String> querie3(int mes){
    ArrayList<String> querie3 = new ArrayList<>();

    int totalCompras = 0;
    int totalClientesDistintos = 0;

    totalCompras = this.mapaVendasMensal.get(mes);
    totalClientesDistintos = this.mapaClientesMensal.get(mes).size();

    StringBuilder querie3Info = new StringBuilder();
    querie3Info.append("\t"+totalCompras+"\t");
    querie3Info.append("\t"+totalClientesDistintos);
    querie3.add(querie3Info.toString());

    return querie3;
  } 

  /**
   * Interactivas :: Querie 4
   * Dado um código de cliente, determinar, para cada mês, quantas compras fez,
   * quantos produtos distintos comprou e quanto gastou. Apresentar também o total
   * anual facturado ao cliente;
   */
  public ArrayList<String> querie4 ( String CodigoCliente ){
    ArrayList<String> querie4 = new ArrayList<>();
    ComprasCliente comprasClienteAssociado = null;
    if ( this.listaTotalCompras.containsKey(CodigoCliente) ){
      comprasClienteAssociado = this.listaTotalCompras.get( CodigoCliente );
      querie4 = comprasClienteAssociado.querie4_ComprasProdutosDistintosGastouMes (CodigoCliente);
    }
    return querie4;
  }

  /**
   * Interactivas :: Querie 7
   * Dado o código de um cliente determinar a lista de códigos de produtos que mais comprou (e quantos), 
   * ordenada por ordem decrescente de quantidade e, para quantidades iguais, por ordem alfabética dos códigos; 
   */
  public ArrayList<String> querie7 ( String CodigoCliente ){

    ArrayList<String> querie7 = new ArrayList<>();
    ComprasCliente comprasClienteAssociado = null;
    if ( this.listaTotalCompras.containsKey(CodigoCliente) ){
      comprasClienteAssociado = this.listaTotalCompras.get( CodigoCliente );
      querie7 = comprasClienteAssociado.querie7_ListaProdutosMaisComprados (CodigoCliente);
    }
    return querie7;
  } 

  /** 
   * Consulta Estatística :: 1.2 P1 
   * Número total de Compras por mês (não é a faturação) 
   */
  public ArrayList<String> estatisticas_1_2_P1 (){ 
    ArrayList<String> querie121 = new ArrayList<>();
    StringBuilder cabecalho = new StringBuilder();
    cabecalho.append("---- Total de compras por mês (não é faturação) ---- \n");
    querie121.add(cabecalho.toString());
    for (Integer mes : this.mapaVendasMensal.keySet()){
      StringBuilder linha = new StringBuilder();
      linha.append("Mês: " + mes).append("\t"); 
      linha.append("Total de compras: " + this.mapaVendasMensal.get(mes) + "\n");
      querie121.add(linha.toString());
    }
    return querie121;
  }

  /**
   * Consulta Estatística :: 1.2 P3
   * Número de distintos clientes que compraram em cada mês 
   * (não interessa quantas vezes o cliente comprou mas apenas quem de facto comprou) 
   */
  public ArrayList<String> estatisticas_1_2_P3 (){
    ArrayList<String> querie123 = new ArrayList<>();
    StringBuilder cabecalho = new StringBuilder();
    cabecalho.append("----- Numero clientes distintos que compraram em cada mês ----");
    querie123.add(cabecalho.toString());
    for (Integer mes : this.mapaVendasMensal.keySet()){
      StringBuilder linha = new StringBuilder();
      linha.append("Mês: " + mes).append("\t");
      linha.append("Total clientes distintos que compraram em cada mês: " + this.mapaClientesMensal.get(mes).size() + "\n");
      querie123.add(linha.toString());
    }
    return querie123;
  } 


  /** QUERIE 1.2 P3 - Número de distintos clientes que compraram em cada mês (não interessa quantas vezes o cliente comprou mas apenas quem de facto comprou) */
  public ArrayList<String> totalClientesDistintosQueCompraramEmCadaMes(){
    ArrayList<String> querie123 = new ArrayList<>();
    StringBuilder cabecalho = new StringBuilder();

    cabecalho.append("----- Numero clientes distintos que compraram em cada mês ----\n");
    querie123.add(cabecalho.toString());
    for (Integer mes : this.mapaVendasMensal.keySet()){
      StringBuilder linha = new StringBuilder();
      linha.append("Mês: " + mes).append("\t");
      linha.append("Total clientes distintos que compraram em cada mês: " + this.mapaClientesMensal.get(mes).size() + "\n");
      querie123.add(linha.toString());
    }

    return querie123;
  }

  /** 
   * Consulta Estatística :: 1.1 P5 
   * Numero Total de Clientes que realizaram compras
   */
  public int estatisticas_1_1_P5(){
    return this.listaTotalCompras.size();
  }

  /**
   * Métodos complementares usuais 
   */

  /**
   * Equals 
   */
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
        Compras that = (Compras) o;
        if(this.listaTotalCompras.equals(that.getListaTotalCompras()) && ( this.mapaVendasMensal.equals(that.getMapaVendasMensal()) ) 
            && ( this.mapaClientesMensal.equals(that.getMapaClientesMensal())) &&  (this.comprasValidadas == that.getComprasValidadas()) ){
          resultado = true;
            }
      }
      return resultado;
    }

  /**
   * toString 
   */
  @Override
    public String toString(){
      StringBuilder sb = new StringBuilder("----- Compras :: Módulo Relacciona Compras->Clientes -----\n");
      sb.append("Total de Compras Validadas: " + this.comprasValidadas + "\n");
      sb.append("Total de Clientes com Compras Associadas: " + this.listaTotalCompras.size() + "\n");
      return sb.toString();
    }

  /**
   * clone
   */
  @Override
    public Compras clone(){
      return new Compras(this);
    }
}

