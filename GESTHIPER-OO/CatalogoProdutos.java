/**
 * Classe referente ao catálogo de Produtos
 * 
 * @author (Carlos Sá A59905, Filipe Oliveira A57816, Sérgio Caldas A57779) 
 * @version (a version number or a date)
 */
import java.io.Serializable;
import java.util.TreeSet;
import java.io.*;

public class CatalogoProdutos implements Serializable{

  private TreeSet <String> codigosProdutos;
  private int produtosValidados;
  private int produtosRejeitados;

  //Vazio
  public CatalogoProdutos(){
    this.codigosProdutos = new TreeSet <String> ();
    this.produtosValidados = 0;
    this.produtosRejeitados = 0;
  }

  //Parametrizado
  public CatalogoProdutos(TreeSet <String> catP , int validados , int rejeitados){
    this.codigosProdutos = new TreeSet <String> ();
    for(String codProduto : catP){
      this.codigosProdutos.add(codProduto);
    }
    this.produtosValidados = validados;
    this.produtosRejeitados = rejeitados;
  }

  //Copia
  public CatalogoProdutos(CatalogoProdutos catP){
    this.codigosProdutos = new TreeSet <String> ();
    for(String codProduto :catP.getCodigosProdutos()){
      this.codigosProdutos.add(codProduto);
    }
    this.produtosValidados = catP.getProdutosValidados();
    this.produtosRejeitados = catP.getProdutosRejeitados();
  }

  /**
   * Getters e Setters
   */
  public TreeSet <String> getCodigosProdutos(){
    TreeSet <String> novoCodigosProdutos = new TreeSet <String> ();
    for(String codProduto: this.codigosProdutos){
      novoCodigosProdutos.add(codProduto);
    }
    return novoCodigosProdutos;
  }

  public int getProdutosValidados(){
    return this.produtosValidados;
  }

  public int getProdutosRejeitados(){
    return this.produtosRejeitados;
  }

  public void setCodigosProdutos(TreeSet <String> codigosP){
    this.codigosProdutos = new TreeSet <String> ();
    for(String codProduto : codigosP){
      this.codigosProdutos.add(codProduto);
    }
  }

  public void setProdutosValidados( int validados ){
    this.produtosValidados = validados;
  }

  public void setProdutosRejeitados( int rejeitados ){
    this.produtosRejeitados = rejeitados;
  }

  /**
   * Método que incrementa clientes validados 
   */
  private void incrementaProdutosValidados (){
    this.produtosValidados++;
  }

  /**
   * Método que incrementa clientes rejeitados
   */
  private void incrementaProdutosRejeitados (){
    this.produtosRejeitados++;
  }

  /**
   * Método que adiciona um codigo de produto ao catalogo de produtos
   */
  public void adicionaCodigoProduto (String novoCodigo){
    this.codigosProdutos.add( novoCodigo );
  }

  /**
   * Método que remove um codigo de produto do catalogo de produtos
   */
  public void removeCodigoProduto (String removeCodigo){
    this.codigosProdutos.remove( removeCodigo );
  } 

  /**
   * Método que verifica se o código do produto existe no catalogo
   */
  public boolean existeCodigoProduto ( String codigoProduto ){  
    boolean resultado = false;
    if(this.codigosProdutos.contains(codigoProduto)==true){
      resultado = true;
    }
    return resultado;
  }

  /**
   * Método auxiliar que verifica se o código do produto é um código válido
   */    
  private boolean verificaCodigoProduto(String codProduto){
    char[] cod = codProduto.toCharArray(); 
    if(codProduto.length()==6){
      if((Character.isLetter(cod[0])==true) && (Character.isLetter(cod[1])==true)){
        if((Character.isDigit(cod[2])==true) && (Character.isDigit(cod[3])==true) && (Character.isDigit(cod[4])==true) && (Character.isDigit(cod[5])==true)){
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

  public boolean produtoValidoEExiste ( String codigoProduto ){
    return ( (verificaCodigoProduto (codigoProduto ) ) && ( existeCodigoProduto( codigoProduto ) ) );
  }

  /**
   * Método que lê o ficheiro produtos
   */    
  public void lerFicheiroProdutos(String pathFicheiroProdutos ) throws IOException {
    File fich = new File(pathFicheiroProdutos);
    BufferedReader br = new BufferedReader(new FileReader(fich));
    String codigoProduto;
    while(((codigoProduto = br.readLine())!=null)){
      if(verificaCodigoProduto(codigoProduto)==true){
        this.adicionaCodigoProduto(codigoProduto);
        this.incrementaProdutosValidados();
      }
      else{
        this.incrementaProdutosRejeitados();
      }
    }
<<<<<<< HEAD
}

=======
  }
  
>>>>>>> 31740d1c54b99f440e8211f5ee44734b29a9e6a8
  /** Método para gravar CatalogoProdutos em ficheiro de objecto */
  public void gravaEmObjecto(String ficheiro) throws IOException {
        ObjectOutputStream objStreamOut = new ObjectOutputStream(new FileOutputStream(ficheiro));
        
        objStreamOut.writeObject(this);
        objStreamOut.flush();
        objStreamOut.close();
  }
<<<<<<< HEAD

=======
  
>>>>>>> 31740d1c54b99f440e8211f5ee44734b29a9e6a8
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
        CatalogoProdutos that = (CatalogoProdutos) o;
        if(this.codigosProdutos.equals(that.getCodigosProdutos())) {
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
      s.append("## Catalogo de Produtos ##");
      s.append("\nNumero de produtos em catálogo: ").append(this.produtosValidados);
      s.append("\nNumero de produtos rejeitados: ").append(this.produtosRejeitados);
      s.append("\n#############");
      return s.toString();
    }

  /**
   * clone
   */
  @Override    
    public CatalogoProdutos clone() {
      return new CatalogoProdutos(this);
    } 
}
