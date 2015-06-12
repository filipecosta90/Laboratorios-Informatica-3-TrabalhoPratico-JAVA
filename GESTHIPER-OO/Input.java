/**
 * Classe que abstrai a utilização da classe Scanner, escondendo todos os
 * problemas relacionados com excepções, e que oferece métodos simples e
 * robustos para a leitura de valores de tipos simples.
 *
 * -----  Utilização: Exemplos
 *
 * int i = Input.lerInt();
 * String linha = Input.lerString();
 * double raio = Input.lerDouble();
 * ---------------------------------------
 *
 * @author F. Mário Martins
 * @version 1.0 (6/2006)
 */

import static java.lang.System.out;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Input {

  /**
   * Métodos de Classe
   */

  public static String lerString( ) {
    boolean ok = false; 
    Scanner input = new Scanner(System.in);
    String txt = "";
    while(!ok) {
      try {
        txt = input.next();
        ok = true;
      }
      catch(InputMismatchException e) 
      { out.println("Texto Inválido"); 
        out.print("Novo valor: ");
        input.nextLine(); 
      }
    }
    return txt;
  } 


  public static int lerInt( ) {
    boolean ok = false; 
    Scanner input = new Scanner(System.in);
    int i = 0; 
    while(!ok) {
      try {
        i = input.nextInt();
        ok = true;
      }
      catch(InputMismatchException e) 
      { out.println("Inteiro Inválido"); 
        out.print("Novo valor: ");
        input.nextLine(); 
      }
    }
    return i;
  } 

}
