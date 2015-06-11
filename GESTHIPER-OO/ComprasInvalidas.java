
/**
 * Write a description of class ComprasInvalidas here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.HashMap;
import java.io.Serializable;

public class ComprasInvalidas
{
  public static enum ErroParsing { 
    PRODUTO_INVALIDO, 
      PRECO_INVALIDO,
      QUANTIDADE_INVALIDA,
      TIPO_INVALIDO, 
      CLIENTE_INVALIDO,
      MES_INVALIDO,
      ERRO_NUMERO_TOKENS
  }
  // chave: ErrosParsing , valor : Linha
  private HashMap < ErroParsing , String> linhasInvalidas;

  /**
   * Construtores
   */
  public ComprasInvalidas()
  {
    this.linhasInvalidas = new HashMap < ErroParsing , String >();
  }

  /**
   * Métodos
   */
  public void adicionaLinhaInvalida ( ErroParsing erro , String linhaInvalida ){
    this.linhasInvalidas.put( erro , linhaInvalida );
  }
}
