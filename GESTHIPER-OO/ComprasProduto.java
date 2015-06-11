
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
  public float getTotalFaturado(HashSet <Compra> comprasMensais){
      float totalFaturado = 0;
      for(Compra compraActual : comprasMensais){    
          totalFaturado+=compraActual.getTotalFaturado();
      }
      return totalFaturado;
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

