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

  /** Método para gravar as Compras em ficheiro de objecto */
  public void gravaEmObjecto(String ficheiro) throws IOException {
    ObjectOutputStream objStreamOut = new ObjectOutputStream(new FileOutputStream(ficheiro));
    objStreamOut.writeObject(this);
    objStreamOut.flush();
    objStreamOut.close();
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
  public ArrayList<String> totalComprasEClientesDistintosQueARealizaram(int mes){

    ArrayList<String> querie3 = new ArrayList<>();

    int totalCompras = 0;
    int totalClientesDistintos = 0;

    totalCompras = this.mapaVendasMensal.get(mes);
    totalClientesDistintos = this.mapaClientesMensal.get(mes).size();

    StringBuilder querie3Info = new StringBuilder();
    querie3Info.append("Total Compras: " + totalCompras + "\n");
    querie3Info.append("Total Clientes Distintos: " + totalClientesDistintos + "\n");
    querie3.add(querie3Info.toString());

    return querie3;
  } 

   
  /** QUERIE 1.2 P3 - Número de distintos clientes que compraram em cada mês (não interessa quantas vezes o cliente comprou mas apenas quem de facto comprou) */
  public int totalClientesDistintosQueCompraramEmCadaMes(){
      int totalClientesDistintosQueCompraramEmCadaMes = 0;
      
      totalClientesDistintosQueCompraramEmCadaMes = this.mapaClientesMensal.values().size();
      
      return totalClientesDistintosQueCompraramEmCadaMes;
  }
  
  
  
  
  
  
  
  
  /** Métodos complementares usuais */

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
        Compras that = (Compras) o;
        if(this.listaTotalCompras.equals(that.getListaTotalCompras()) && ( this.mapaVendasMensal.equals(that.getMapaVendasMensal()) ) 
            && ( this.mapaClientesMensal.equals(that.getMapaClientesMensal())) &&  (this.comprasValidadas == that.getComprasValidadas()) ){
          resultado = true;
            }
      }
      return resultado;
    }

  /** toString */
  @Override
    public String toString(){
      StringBuilder sb = new StringBuilder("----- Compras :: Módulo Relacciona Compras->Clientes -----\n");
      sb.append("Total de Compras Validadas: " + this.comprasValidadas + "\n");
      sb.append("Total de Clientes com Compras Associadas: " + this.listaTotalCompras.size() + "\n");
      return sb.toString();
    }

  /** clone */
  @Override
    public Compras clone(){
      return new Compras(this);
    }
}

