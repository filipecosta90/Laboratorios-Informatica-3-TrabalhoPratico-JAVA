/**
 * Classe referente ao catálogo de Clientes
 * 
 * @author (Carlos Sá A59905, Filipe Oliveira A57816, Sérgio Caldas A57779) 
 * @version (a version number or a date)
 */
import java.io.Serializable;
import java.util.TreeSet;

public class CatalogoClientes implements Serializable{

  private TreeSet <String> codigosClientes;
  private int clientesValidados;
  private int clientesRejeitados;

  /**
   * Construtores
   */

  //Vazio
  public CatalogoClientes(){
    this.codigosClientes = new TreeSet <String> ();
    this.clientesValidados = 0;
    this.clientesRejeitados = 0;
  }

  //Parametrizado
  public CatalogoClientes(TreeSet <String> catC , int validados , int rejeitados){
    this.codigosClientes = new TreeSet <String> ();
    for(String codCliente : catC){
      this.codigosClientes.add(codCliente);
    }
    this.clientesValidados = validados;
    this.clientesRejeitados = rejeitados;
  }

  //Copia
  public CatalogoClientes(CatalogoClientes cc){
    this.codigosClientes = new TreeSet <String> ();
    for(String codCliente :cc.getCodigosClientes()){
      this.codigosClientes.add(codCliente);
    }
    this.clientesValidados = cc.getClientesValidados();
    this.clientesRejeitados = cc.getClientesRejeitados();
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

  /**
   * Método que adiciona um codigo de cliente ao catalogo de clientes
   */
  public void adicionaCodigoCliente (String novoCodigo){
    this.codigosClientes.add( novoCodigo );
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
      if(this.codigosClientes.equals(that.getCodigosClientes())) {
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
    s.append("#############");
    s.append("\nCatalogo Clientes");
    for(String codigoCliente : this.codigosClientes){
      s.append("\n"+codigoCliente);
    }
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
