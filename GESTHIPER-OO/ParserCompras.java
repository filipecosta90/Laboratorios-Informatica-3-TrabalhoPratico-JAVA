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
import java.io.*;

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

  public void lerFicheiroCompras () throws IOException{
    File fich = new File( this.pathFicheiroCompras );
      BufferedReader br = new BufferedReader(new FileReader(fich));
      String linha;
      while( ( (linha = br.readLine()) != null ) ){
        parseLinhaCompras(linha);
      }
  }

  /**
   * Mêtodo auxiliar que faz o parser a cada linha do ficheiro de compras
   */
  private void parseLinhaCompras ( String linha ){
    Scanner sFile = null;
    String codigoProduto = null;
    float preco = 0;
    int quantidade = 0;
    String tipoCompra = null;
    String codigoCliente = null;
    int mes = 0;
    StringTokenizer st = new StringTokenizer(linha);
    if ( st.countTokens() == 6 ){
      codigoProduto=st.nextToken();
      if ( this.apontadorCatalogoProdutos.produtoValidoEExiste( codigoProduto ) ){
        preco=Float.parseFloat(st.nextToken());
        if (preco > 0.0 ){
          quantidade=Integer.parseInt(st.nextToken());
          if ( quantidade > 0 ){
            tipoCompra=st.nextToken();
            if( Compra.verificaTipoCompra(tipoCompra) ){
              codigoCliente=st.nextToken();
              if ( this.apontadorCatalogoClientes.clienteValidoEExiste( codigoCliente ) ){
                mes=Integer.parseInt(st.nextToken());
                if(mes>=1 && mes<=12){
                  this.apontadorCompras.adicionaCompra( codigoProduto, preco , quantidade , tipoCompra, codigoCliente , mes );
                  this.apontadorContabilidade.adicionaCompraContabilidade( codigoProduto, preco , quantidade , tipoCompra, codigoCliente , mes );
                }
                else{
                  this.apontadorComprasInvalidas.adicionaLinhaInvalida( ComprasInvalidas.ErroParsing.MES_INVALIDO , linha );                                                
                }
              }
              else{
                this.apontadorComprasInvalidas.adicionaLinhaInvalida( ComprasInvalidas.ErroParsing.CLIENTE_INVALIDO , linha );                         
              }
            }
            else{
              this.apontadorComprasInvalidas.adicionaLinhaInvalida( ComprasInvalidas.ErroParsing.TIPO_INVALIDO , linha );
            }
          }
          else{
            this.apontadorComprasInvalidas.adicionaLinhaInvalida( ComprasInvalidas.ErroParsing.QUANTIDADE_INVALIDA , linha );
          }
        }
        else{
          this.apontadorComprasInvalidas.adicionaLinhaInvalida( ComprasInvalidas.ErroParsing.PRECO_INVALIDO , linha );
        }
      }
      else {
        this.apontadorComprasInvalidas.adicionaLinhaInvalida( ComprasInvalidas.ErroParsing.PRODUTO_INVALIDO , linha );
      }
    }
    else {
      this.apontadorComprasInvalidas.adicionaLinhaInvalida( ComprasInvalidas.ErroParsing.ERRO_NUMERO_TOKENS , linha );
    }
  }
}
