
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
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;

public class ComprasProduto implements Serializable{

  private TreeMap <Integer, HashSet<Compra>> listaComprasProduto; //Key = Mês

  /**
   * Construtor vazio
   */
  public ComprasProduto(){
    this.listaComprasProduto = new TreeMap <Integer, HashSet<Compra>>();
    for ( int mes = 1 ; mes <= 12 ; mes++ ){
      HashSet<Compra> listaMensalPorProduto = new HashSet<Compra>();
      this.listaComprasProduto.put(mes , listaMensalPorProduto );
    }
  }

  /**
   * Construtor já com uma compra 
   */
  public ComprasProduto( String codigoProduto , float preco, int quantidade, String tipoCompra, String codigoCliente , int mesCompra ){
    this.listaComprasProduto = new TreeMap <Integer, HashSet<Compra>>();
    Compra compraAdicionar = new Compra ( codigoProduto , preco , quantidade , tipoCompra , codigoCliente , mesCompra );
    for ( int mes = 1 ; mes <= 12 ; mes++ ){
      HashSet<Compra> listaMensalPorProduto = new HashSet<Compra>();
      if ( mes == mesCompra ){
        listaMensalPorProduto.add (compraAdicionar);
      }
      this.listaComprasProduto.put(mes , listaMensalPorProduto );
    }
  }

  /**
   * está mal. precisa de ser visto!!!!!
   */
  public ComprasProduto(ComprasProduto cc){
    TreeMap <Integer, HashSet<Compra>> novaLista = new TreeMap <Integer, HashSet<Compra>> ();
    HashSet <Compra> novaCompras = new HashSet <Compra> ();
    for(HashSet <Compra> cmp : cc.getListaComprasProduto().values()){
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
  public TreeMap <Integer,HashSet<Compra>> getListaComprasProduto(){
    TreeMap <Integer, HashSet<Compra> > novaLista = new TreeMap <Integer,HashSet<Compra>> ();
    HashSet <Compra> novaCompras = new HashSet <Compra>();
    for(HashSet<Compra> cmp : this.listaComprasProduto.values()){
      for(Compra c: cmp){
        novaCompras.add(c.clone());
        novaLista.put(c.getMes(),novaCompras);
      }
    }
    return novaLista;
  }

  /**
   * Método auxiliar que junta uma compra ao HashSet
   */
  private HashSet <Compra> addCompraToSet (Compra c){
    HashSet <Compra> compras = new HashSet<>();
    compras.add(c);
    return compras;
  }

  /**
   * Método que adiciona uma compra na estrutura
   */
  public void addCompraToLista(Compra c){
    int mes = c.getMes();
    this.listaComprasProduto.put(mes,addCompraToSet(c));
  }

  public void adicionaCompra( String codigoProduto , float preco , int quantidade , String tipoCompra , String codigoCliente , int mes ){
    HashSet<Compra> listaMensal = this.listaComprasProduto.get(mes);
    Compra compraAdicionar = new Compra ( codigoProduto , preco , quantidade , tipoCompra , codigoCliente , mes );
    listaMensal.add (compraAdicionar);
  }


  /** Método auxiliar Q5 para retornar a String com informação: |Mes|Numero Vezes Comprado|Clientes Distintos|Tota Facturado */
  public ArrayList <String> getMapComprasMensal(){
    ArrayList<String> listaMesAMes = new ArrayList<>();
    StringBuilder mapaString = new StringBuilder();
    mapaString.append("Mês\t#Numero de vezes Comprado\t#Clientes Distintos\tTotal Facturado\n");
    listaMesAMes.add(mapaString.toString());
    for(Integer mes : this.listaComprasProduto.keySet()){
      mapaString = new StringBuilder();
      HashSet <Compra> comprasMensais = this.listaComprasProduto.get(mes);
      mapaString.append("\n").append(mes);
      mapaString.append("\t").append(numeroVezesComprado(comprasMensais));
      mapaString.append("\t").append(numeroClientesDistintosQueComprouProduto(comprasMensais));
      float totalMensal = getTotalFacturadoMes(comprasMensais);
      mapaString.append("\t").append(totalMensal);
      mapaString.append("\n");
      listaMesAMes.add(mapaString.toString());
    }
    return listaMesAMes;
  }

  /**
   * Método auxiliar Q5 que calcula o numero de vezes que um produto foi comprado
   */
  public int numeroVezesComprado (HashSet <Compra> comprasMensais){
    int contaVezes = comprasMensais.size();
    return contaVezes;
  }

  /**
   * Método auxiliar Q5 que calcula por quantos clientes distintos um produto foi comprado
   */
  public int numeroClientesDistintosQueComprouProduto(HashSet <Compra> comprasMensais){
    int numeroClientesDistintos;
    TreeSet <String> clientesDistintos = new TreeSet<>();
    for(Compra compraActual : comprasMensais){
      clientesDistintos.add(compraActual.getCodigoCliente());
    }
    numeroClientesDistintos = clientesDistintos.size();
    return numeroClientesDistintos;
  }

  /**
   * Método auxiliar Q5 que calcula o total faturado
   */
  public float getTotalFacturadoMes(HashSet <Compra> comprasMensais){
    float totalFaturado = 0;
    for(Compra compraActual : comprasMensais){    
      totalFaturado+=compraActual.getTotalFaturado();
    }
    return totalFaturado;
  }

  /**
   * Método auxiliar Q6 que calcula vezes que foi comprado em modo N;
   */
  public int vezesCompradoEmModoN (HashSet <Compra> comprasMensais){
    int numeroVezes = 0;
    for(Compra compraActual:comprasMensais){
      if(compraActual.getTipoCompra().equals("N") || compraActual.getTipoCompra().equals("n")){
        numeroVezes++;
      }
    }
    return numeroVezes;
  }

  /**
   * Método auxiliar Q6 que calcula vezes que foi comprado em modo P;
   */
  public int vezesCompradoEmModoP (HashSet <Compra> comprasMensais){
    int numeroVezes = 0;
    for(Compra compraActual:comprasMensais){
      if(compraActual.getTipoCompra().equals("P") || compraActual.getTipoCompra().equals("p")){
        numeroVezes++;
      }
    }
    return numeroVezes;
  }

  /**
   * Método auxiliar Q6 que calcula total facturado em modo N;
   */
  public float totalFacturadoEmModoN (HashSet <Compra> comprasMensais){
    float totalFacturado = 0.0f;
    for(Compra compraActual:comprasMensais){
      if(compraActual.getTipoCompra().equals("N") || compraActual.getTipoCompra().equals("n")){
        totalFacturado+=compraActual.getTotalFaturado();
      }
    }
    return totalFacturado;
  }

  /**
   * Método auxiliar Q6 que calcula total facturado em modo P;
   */
  public float totalFacturadoEmModoP (HashSet <Compra> comprasMensais){
    float totalFacturado = 0.0f;
    for(Compra compraActual:comprasMensais){
      if(compraActual.getTipoCompra().equals("P") || compraActual.getTipoCompra().equals("p")){
        totalFacturado+=compraActual.getTotalFaturado();
      }
    }
    return totalFacturado;
  }

  /**
   * Método auxiliar Q6 para retornar a String com informação: |Mes|Modo|Numero Vezes Comprado|Tota Facturado 
   */
  public ArrayList <String> getMapComprasMensalModo(){
    ArrayList<String> listaMesAMes = new ArrayList<>();
    StringBuilder mapaString = new StringBuilder();
    mapaString.append("Mês\tCompras N\tTotal Facturado N\tCompras P\tTotal Facturado P\n");
    listaMesAMes.add(mapaString.toString());
    for(Integer mes : this.listaComprasProduto.keySet()){
      mapaString = new StringBuilder();
      HashSet <Compra> comprasMensais = this.listaComprasProduto.get(mes);
      mapaString.append("\n").append(mes);
      mapaString.append("\t").append(vezesCompradoEmModoN(comprasMensais));
      mapaString.append("\t").append(totalFacturadoEmModoN(comprasMensais));
      mapaString.append("\t").append(vezesCompradoEmModoP(comprasMensais));
      mapaString.append("\t").append(totalFacturadoEmModoP(comprasMensais));
      mapaString.append("\n");
      listaMesAMes.add(mapaString.toString());
    }
    return listaMesAMes;
  }

  /** Método para gravar ComprasProduto em ficheiro de objecto */
  public void gravaEmObjecto(String ficheiro) throws IOException {
    ObjectOutputStream objStreamOut = new ObjectOutputStream(new FileOutputStream(ficheiro));

    objStreamOut.writeObject(this);
    objStreamOut.flush();
    objStreamOut.close();
  }


  /**
   * toString
   */
  @Override
    public String toString(){
      StringBuilder s= new StringBuilder();
      s.append("\nInformação de Compras");
      s.append("\nCompras do Produto:");
      for(HashSet <Compra> cmp : this.listaComprasProduto.values()){
        for(Compra c : cmp){
          s.append("\n"+cmp.toString());
        }
      }
      return s.toString();
    }

  /**
   * Equals
   */
  @Override
    public boolean equals(Object o) {
      if(this==o) return true;
      if((o==null) || this.getClass()!=o.getClass()) return false;
      else{
        ComprasProduto cp = (ComprasProduto) o;
        if(this.listaComprasProduto.equals(cp.getListaComprasProduto())) return true;
        else return false;
      }
    }

  /**
   * Método clone
   */
  @Override
    public ComprasProduto clone() {
      return new ComprasProduto(this);
    } 
}
