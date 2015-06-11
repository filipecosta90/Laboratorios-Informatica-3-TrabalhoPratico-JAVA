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
    this.comprasValidadas = 0;
  }

  //Parametrizado
  public Compras(TreeMap<String,ComprasCliente> listaTotComprasCliente, TreeMap <Integer , Integer> vendasMensal, TreeMap <Integer,TreeSet<String>> clientesMensal, int comprasValidadas){
    listaTotalCompras = new TreeMap<String,ComprasCliente>();
    for (String codCliente : listaTotComprasCliente.keySet()){
      listaTotalCompras.put(codCliente, listaTotComprasCliente.get(codCliente).clone() );
    }
    mapaVendasMensal = new TreeMap<Integer,Integer>();
    for(Integer mes : vendasMensal.keySet()){
        mapaVendasMensal.put(mes,vendasMensal.get(mes));
    }
    mapaClientesMensal = new TreeMap<Integer,TreeSet<String>>();
    for(Integer mes : clientesMensal.keySet()){
        TreeSet <String> treeSetClientes = mapaClientesMensal.get(mes);
        for(String codCliente : clientesMensal.get(mes)){
            treeSetClientes.add(codCliente);
        }
        mapaClientesMensal.put(mes,treeSetClientes);
    }
    this.comprasValidadas = comprasValidadas;
  }

  //Cópia
  public Compras(Compras compras){
    this.listaTotalCompras = compras.getListaTotalCompras();    //método getListaTotalCompras() já realiza o clone();
    this.comprasValidadas = compras.getComprasValidadas();
    this.mapaVendasMensal = compras.getMapaVendasMensal();
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
      TreeMap <Integer,Integer> vendasMes = new TreeMap();
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
  
  /** Métodos complementares usuais */

  /** Equals */
  @Override
    public boolean equals(Object compras){

      if (this == compras) return true;
      if (compras == null || this.getClass() != compras.getClass() ) return false;

      Compras umaCompra = (Compras) compras;
      return( this.listaTotalCompras.equals(umaCompra.getListaTotalCompras()) && this.mapaVendasMensal.equals(umaCompra.getMapaVendasMensal()) && this.mapaClientesMensal.equals(umaCompra.getMapaClientesMensal()) && this.comprasValidadas == umaCompra.getComprasValidadas());
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








