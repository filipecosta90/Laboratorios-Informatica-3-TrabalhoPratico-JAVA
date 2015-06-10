/**
 * Classe responsável pelo parser dos ficheiros de input.
 * 
 * @author (Carlos Sá A59905, Filipe Oliveira A57816, Sérgio Caldas A57779) 
 * @version (a version number or a date)
 */

import java.io.Serializable;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.lang.String;

public class ParserCompras implements Serializable{

  private String pathFicheiroCompras;
  private CatalogoProdutos apontadorCatalogoProdutos;
  private CatalogoClientes apontadorCatalogoClientes;
  private ComprasInvalidas apontadorComprasInvalidas;
  private Compras apontadorCompras;
  private Contabilidade apontadorContabilidade;


  public ParserCompras (){
    this.pathFicheiroCompras = new String();
    this.apontadorCatalogoProdutos = null;
    this.apontadorCatalogoClientes = null;
    this.apontadorComprasInvalidas = null;
    this.apontadorCompras = null;
    this.apontadorContabilidade = null;

  }

  public ParserCompras( String path , CatalogoProdutos apontadorProdutos, 
      CatalogoClientes apontadorClientes , ComprasInvalidas apontadorInvalidas,
      Compras apontadorComp, Contabilidade apontadorCont ){
    this.pathFicheiroCompras = path;
    this.apontadorCatalogoProdutos = apontadorProdutos;
    this.apontadorCatalogoClientes = apontadorClientes;
    this.apontadorComprasInvalidas = apontadorInvalidas;
    this.apontadorCompras = apontadorComp;
    this.apontadorContabilidade = apontadorCont;
  }

  public ParserCompras( ParserCompras parser ){
    this.pathFicheiroCompras = parser.getPathFicheiroCompras();
    this.apontadorCatalogoProdutos = parser.getApontadorCatalogoProdutos();
    this.apontadorCatalogoClientes = parser.getApontadorCatalogoClientes();
    this.apontadorComprasInvalidas = parser.getApontadorComprasInvalidas();
    this.apontadorCompras = parser.getApontadorCompras();
    this.apontadorContabilidade = parser.getApontadorContabilidade();
  }

  public String getPathFicheiroCompras(){
    return this.pathFicheiroCompras;
  }

  public CatalogoProdutos getApontadorCatalogoProdutos(){
    return this.apontadorCatalogoProdutos;
  }

  public CatalogoClientes getApontadorCatalogoClientes(){
    return this.apontadorCatalogoClientes;
  }

  public ComprasInvalidas getApontadorComprasInvalidas(){
    return this.apontadorComprasInvalidas;
  }

  public Compras getApontadorCompras(){
    return this.apontadorCompras;
  }

  public Contabilidade getApontadorContabilidade(){
    return this.apontadorContabilidade;
  }

  /**
   * Mêtodo auxiliar que faz o parser a cada linha do ficheiro de compras
   */
  private boolean parserLinhaCompras ( String linha ){
    Scanner sFile = null;
    String codigoProduto = null;
    float preco = 0;
    int quantidade = 0;
    String tipoCompra = null;
    String codigoCliente = null;
    int mes = 0;
    boolean erro = false;
    StringTokenizer st = new StringTokenizer(linha);
    while (st.hasMoreTokens() && !erro) {
      codigoProduto=st.nextToken();
      if ( this.apontadorCatalogoProdutos.produtoValidoEExiste( codigoProduto ) ){
        preco=Float.parseFloat(st.nextToken());
        quantidade=Integer.parseInt(st.nextToken());
        tipoCompra=st.nextToken();
        if( Compra.verificaTipoCompra(tipoCompra) ){
          codigoCliente=st.nextToken();
          if ( this.apontadorCatalogoClientes.clienteValidoEExiste( codigoCliente ) ){
            mes=Integer.parseInt(st.nextToken());
            if(mes>=1 && mes<=12){
              erro=true;
            }
            else{
              erro=false;
            }
          }
          else{
            erro=false;
          }
        }
        else{
          erro=false;
        }
      }
      else{
        erro=false;
      }
    }
    if(erro==true){return true;}
    else{return false;}
  }
}
