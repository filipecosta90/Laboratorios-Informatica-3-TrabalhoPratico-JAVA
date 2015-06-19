
/**
 * Classe que relacciona um produto com as suas vendas mensais
 * 
 * @author (Carlos Sá A59905, Filipe Oliveira A57816, Sérgio Caldas A57779) 
 * @version (a version number or a date)
 */

import java.io.Serializable;
import java.util.TreeMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.ArrayList;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.util.Iterator;

public class ComprasProduto implements Serializable{

  private HashMap <Integer, HashSet<Compra>> listaComprasProduto; //Key = Mês

  /**
   * Construtor vazio
   */
  public ComprasProduto(){
    this.listaComprasProduto = new HashMap <Integer, HashSet<Compra>>();
    for ( int mes = 1 ; mes <= 12 ; mes++ ){
      HashSet<Compra> listaMensalPorProduto = new HashSet<Compra>();
      this.listaComprasProduto.put(mes , listaMensalPorProduto );
    }
  }

  /**
   * Construtor já com uma compra 
   */
  public ComprasProduto( String codigoProduto , float preco, int quantidade, String tipoCompra, String codigoCliente , int mesCompra ){
    this.listaComprasProduto = new HashMap <Integer, HashSet<Compra>>();
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
    mapaString.append("Mês\tNumero de vezes Comprado\tClientes Distintos\tTotal Facturado\n");
    listaMesAMes.add(mapaString.toString());
    float totalAnual = 0.0f;
    for(Integer mes : this.listaComprasProduto.keySet()){
      mapaString = new StringBuilder();
      HashSet <Compra> comprasMensais = this.listaComprasProduto.get(mes);
      mapaString.append(mes);
      mapaString.append("\t\t\t").append(numeroVezesComprado(comprasMensais));
      mapaString.append("\t\t\t").append(numeroClientesDistintosQueComprouProduto(comprasMensais));
      float totalMensal = getTotalFacturadoMes(comprasMensais);
      String faturacao2Casas = String.format("%.2f", totalMensal) ;
      mapaString.append("\t\t\t").append(faturacao2Casas);
      listaMesAMes.add(mapaString.toString());
      totalAnual+=totalMensal;
    }
    StringBuilder rodape = new StringBuilder();
          String faturacao2Casas = String.format("%.2f", totalAnual) ;
    rodape.append("\nTotal Anual: "+faturacao2Casas);
    listaMesAMes.add(rodape.toString());
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
      mapaString.append(mes);
      mapaString.append("\t\t").append(vezesCompradoEmModoN(comprasMensais));
      float facturadoN = totalFacturadoEmModoN(comprasMensais);
            String faturacao2Casas = String.format("%.2f", facturadoN) ;
      mapaString.append("\t\t").append(faturacao2Casas);
      mapaString.append("\t\t").append(vezesCompradoEmModoP(comprasMensais));
       float facturadoP = totalFacturadoEmModoP(comprasMensais);
      faturacao2Casas = String.format("%.2f", facturadoP) ;
      mapaString.append("\t\t").append(faturacao2Casas);
      listaMesAMes.add(mapaString.toString());
    }
    return listaMesAMes;
  }

  /**
   * Método auxiliar Q8 - retorna o numero de unidades vendidas no mes  
   */
  public int numeroUnidadesVendidasMes (HashSet <Compra> comprasMensais){
    int numeroUnidadesVendidas = 0;
    for(Compra compraActual:comprasMensais){
      numeroUnidadesVendidas += compraActual.getQuantidade();
    }
    return numeroUnidadesVendidas;
  }

  /**
   * Método auxiliar Q8 - retorna o numero de unidades vendidas no ano  
   */
  public int getNumeroUnidadesVendidas(){
    int numeroUnidadesVendidas = 0;
    for(Integer mes : this.listaComprasProduto.keySet()){
      HashSet <Compra> comprasMensais = this.listaComprasProduto.get(mes);
      numeroUnidadesVendidas += numeroUnidadesVendidasMes(comprasMensais);
    }
    return numeroUnidadesVendidas;
  }

  public ArrayList <String> querie10_TopVendasProduto ( String codigoProduto , int topN ){
    ArrayList <String> listaQuerie10 = new ArrayList <>();
    TreeSet < Triplo_Cliente_Unidades_ValorGasto > topVendasPorCliente = new TreeSet <> ( new ComparatorUnidades_Triplo_Cliente_Unidades_ValorGasto() );
    TreeMap < String , Triplo_Cliente_Unidades_ValorGasto > mapaAuxiliar = new TreeMap <>();
    for ( int mesActual : this.listaComprasProduto.keySet()){
      HashSet<Compra> comprasMensais = this.listaComprasProduto.get(mesActual);
      for ( Compra compraActual : comprasMensais ){
        String codigoClienteActual = compraActual.getCodigoCliente();
        int unidadesVendidasCompraActual = compraActual.getQuantidade();
        float facturacaoActual = compraActual.getTotalFaturado();
        Triplo_Cliente_Unidades_ValorGasto triploActual = null;
        if ( mapaAuxiliar.containsKey( codigoClienteActual ) ){
          triploActual = mapaAuxiliar.get( codigoClienteActual );
          triploActual.incrementaValorGasto( facturacaoActual );
          triploActual.incrementaUnidadesVendidas( unidadesVendidasCompraActual );
          mapaAuxiliar.replace ( codigoClienteActual , triploActual );
        }
        else {
          triploActual = new Triplo_Cliente_Unidades_ValorGasto ( codigoClienteActual , unidadesVendidasCompraActual , facturacaoActual );
          mapaAuxiliar.put ( codigoClienteActual , triploActual );
        }
      }
    }
    /**
     * agora que temos apenas uma correspondencia entre codigo produto -> unidades vendidas -> numero de vendas
     * vamos inserir de forma ordenana no TreeSet com o comparator definido como o professor pediu 
     */
    for ( Triplo_Cliente_Unidades_ValorGasto triploActual : mapaAuxiliar.values() ){
      topVendasPorCliente.add( triploActual );
    }
    /**
     * agora que temos os triplos ordenados resta-nos imprimir para linhas
     */
    StringBuilder cabecalho = new StringBuilder ();

    Iterator<Triplo_Cliente_Unidades_ValorGasto> iteradorTreeSet=topVendasPorCliente.iterator();
    int topActual = 1;
    while(iteradorTreeSet.hasNext() && topActual <= topN ){
      Triplo_Cliente_Unidades_ValorGasto triploActual = iteradorTreeSet.next();
      StringBuilder linha = new StringBuilder ();
      linha.append(triploActual.toString());
      listaQuerie10.add(linha.toString());
      topActual++;
    }
    return listaQuerie10;
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
