
/**
 * Write a description of class ComprasInvalidas here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.HashMap;
import java.io.*;
import java.io.Serializable;

public class ComprasInvalidas implements Serializable {

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
  private int numeroLinhasInvalidas;

  /**
   * Construtores
   */
  public ComprasInvalidas()
  {
    this.linhasInvalidas = new HashMap < ErroParsing , String >();
    this.numeroLinhasInvalidas = 0;
  }

  public int getNumeroLinhasInvalidas(){
    return this.numeroLinhasInvalidas;
  }

  public void setNumeroLinhasInvalidas(int invalidas){
    this.numeroLinhasInvalidas = invalidas;
  }

  public void incrementaLinhasInvalidas(){
    this.numeroLinhasInvalidas++;
  }

  /**
   * Métodos
   */
  public void adicionaLinhaInvalida ( ErroParsing erro , String linhaInvalida ){
    this.linhasInvalidas.put( erro , linhaInvalida );
    incrementaLinhasInvalidas();
  }

  /** Método para gravar as Compras Inválidas em ficheiro de objecto */
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
    public String toString() {
      StringBuilder s = new StringBuilder("----- Compras Inválidas :: Módulo Relacciona ErrosParsing->Linhas Vendas -----\n");
      s.append("\nTipo Invalidez\t\t#Registos");
      s.append("\n-------------------------------");
      int totalErros = 0;
      for ( ErroParsing erro : this.linhasInvalidas.keySet() ){
        int numeroErroActual = this.linhasInvalidas.get(erro).length();
        totalErros+=numeroErroActual;
        s.append("\n").append(erro).append("\t\t").append(numeroErroActual);
      }
      s.append("\n-------------------------------");
      s.append("\nTotal Compras Inválidas: ").append(numeroLinhasInvalidas).append("\n");
      return s.toString();
    }
}

