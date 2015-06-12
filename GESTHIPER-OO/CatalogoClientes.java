/**
 * Classe referente ao catálogo de Clientes
 * 
 * @author (Carlos Sá A59905, Filipe Oliveira A57816, Sérgio Caldas A57779) 
 * @version (a version number or a date)
 */
import java.io.Serializable;
import java.util.TreeSet;
import java.io.*;

public class CatalogoClientes implements Serializable{

  private TreeSet <String> codigosClientes;
  private int clientesValidados;
  private int clientesRejeitados;
  private String nomeFicheiroClientesLido;

  /**
   * Construtores
   */

  //Vazio
  public CatalogoClientes(){
    this.codigosClientes = new TreeSet <String> ();
    this.clientesValidados = 0;
    this.clientesRejeitados = 0;
    this.nomeFicheiroClientesLido = new String();
  }

  //Parametrizado
  public CatalogoClientes(TreeSet <String> catC , int validados , int rejeitados , String nomeFicheiro){
    this.codigosClientes = new TreeSet <String> ();
    for(String codCliente : catC){
      this.codigosClientes.add(codCliente);
    }
    this.clientesValidados = validados;
    this.clientesRejeitados = rejeitados;
    this.nomeFicheiroClientesLido = nomeFicheiro;
  }

  //Copia
  public CatalogoClientes(CatalogoClientes cc){
    this.codigosClientes = new TreeSet <String> ();
    for(String codCliente :cc.getCodigosClientes()){
      this.codigosClientes.add(codCliente);
    }
    this.clientesValidados = cc.getClientesValidados();
    this.clientesRejeitados = cc.getClientesRejeitados();
    this.nomeFicheiroClientesLido = cc.getNomeFicheiroClientesLido();
  }

  /**
   * Getters e Setters
   */
  public TreeSet <String> getCodigosClientes(){
    TreeSet <String> novoCodigosClientes = new TreeSet <String> ();
    for(String codCliente: this.codigosClientes){
      novoCodigosClientes.add(codCliente);
    }
    return novoCodigosClientes;
  }

  public int getClientesValidados(){
    return this.clientesValidados;
  }

  public int getClientesRejeitados(){
    return this.clientesRejeitados;
  }

  public String getNomeFicheiroClientesLido(){
    return this.nomeFicheiroClientesLido;
  }

  public void setCodigosCliente(TreeSet <String> codigosC){
    this.codigosClientes = new TreeSet <String> ();
    for(String codCliente : codigosC){
      this.codigosClientes.add(codCliente);
    }
  }

  public void setClientesValidados( int validados ){
    this.clientesValidados = validados;
  }

  public void setClientesRejeitados( int rejeitados ){
    this.clientesRejeitados = rejeitados;
  }

  public void setNomeFicheiroClientesLido (String nomeFicheiro){
    this.nomeFicheiroClientesLido=nomeFicheiro;
  }

  /**
   * Método que adiciona um codigo de cliente ao catalogo de clientes
   */
  public void adicionaCodigoCliente (String novoCodigo){
    this.codigosClientes.add( novoCodigo );
  }

  /**
   * Método que incrementa clientes validados 
   */
  private void incrementaClientesValidados (){
    this.clientesValidados++;
  }

  /**
   * Método que incrementa clientes rejeitados
   */
  private void incrementaClientesRejeitados (){
    this.clientesRejeitados++;
  }

  /**
   * Método que remove um codigo de cliente ao catalogo de clientes2
   */
  public void removeCodigoCliente (String removeCodigo){
    this.codigosClientes.remove( removeCodigo );
  } 

  /**
   * Método que verifica se o codifo do cliente existe no catalogo
   */
  public boolean existeCodigoCliente ( String codigoCliente ){  
    boolean resultado = false;
    if(this.codigosClientes.contains(codigoCliente)==true){
      resultado = true;
    }
    return resultado;
  }

  /**
   * Método auxiliar que verifica se o codigo do clientes é um código válido
   */
  private boolean verificaCodigoCliente(String codCliente){
    char[] cod = codCliente.toCharArray(); 
    if(codCliente.length()==5){
      if((Character.isLetter(cod[0])==true) && (Character.isLetter(cod[1])==true)){
        if((Character.isDigit(cod[2])==true) && (Character.isDigit(cod[3])==true) && (Character.isDigit(cod[4])==true)){
          return true;
        }
        else{
          return false;
        }
      }
      else{
        return false;
      }       
    }
    else{
      return false;
    }
  }

  public boolean clienteValidoEExiste ( String codigoCliente ){
    return ( (verificaCodigoCliente (codigoCliente ) ) && ( existeCodigoCliente( codigoCliente ) ) );
  }

  public void lerFicheiroClientes( String pathFicheiroClientes ) throws IOException {
    File fich = new File( pathFicheiroClientes );
    this.nomeFicheiroClientesLido = pathFicheiroClientes;
    BufferedReader br = new BufferedReader(new FileReader(fich));
    String codigo;
    while(((codigo = br.readLine())!=null)){

      if (verificaCodigoCliente(codigo)==true){
        this.adicionaCodigoCliente(codigo);
        this.incrementaClientesValidados();
      }
      else{
        this.incrementaClientesRejeitados();
      }
    }
  }

  /* 
   * Consulta Estatística :: 1.1 P0 
   * Nome do ficheiro lido
   */
  public String estatisticas_1_1_P0(){
    return this.nomeFicheiroClientesLido;
  }

  /* 
   * Consulta Estatística :: 1.1 P1 
   * Numero Total de Clientes em Catálogo
   */
  public int estatisticas_1_1_P5(){
    return this.codigosClientes.size();
  }


  /** Método para gravar CatalogoClientes em ficheiro de objecto */
  public void gravaEmObjecto(String ficheiro) throws IOException {
    ObjectOutputStream objStreamOut = new ObjectOutputStream(new FileOutputStream(ficheiro));

    objStreamOut.writeObject(this);
    objStreamOut.flush();
    objStreamOut.close();
  }


  /**
   * equals
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
        CatalogoClientes that = (CatalogoClientes) o;
        if((this.codigosClientes.equals(that.getCodigosClientes())) &&(this.clientesValidados == that.getClientesValidados()) && (this.clientesRejeitados==that.getClientesRejeitados()) && (this.nomeFicheiroClientesLido.equals(that.getNomeFicheiroClientesLido()))) {
          resultado = true;
        }
      }
      return resultado;
    }

  /**
   * toString
   */
  @Override
    public String toString() {
      StringBuilder s= new StringBuilder();
      s.append("## Catalogo de Clientes ##");
      s.append("\nNumero de clientes em catálogo: ").append(this.clientesValidados);
      s.append("\nNumero de clientes rejeitados: ").append(this.clientesRejeitados);
      s.append("\n#############");
      return s.toString();
    }

  /**
   * clone
   */
  @Override
    public CatalogoClientes clone() {
      return new CatalogoClientes(this);
    } 
}
