
/**
 * Esta classe implementa um menu em modo texto.
 * 
 * @author José Creissac Campos 
 * @version v1.0
 */

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
  // variáveis de instância
  private List<String> opcoes;
  private int op;

  /**
   * Constructor for objects of class Menu
   */
  public Menu(String[] opcoes) {
    this.opcoes = new ArrayList<String>();
    for (String op : opcoes) //(int i=0; i<opcoes.length; i++)
      this.opcoes.add(op);
    this.op = 0;
  }

  /**
   * Método para apresentar o menu e ler uma opção.
   * 
   */
  public void executa() {
    do {
      showMenu();
      this.op = lerOpcao();
    } while (this.op == -1);
  }

  /** Apresentar o menu */
  private void showMenu() {
    //System.out.print('\u000C');
    System.out.println("\n *** Menu *** ");
    for (int i=0; i<this.opcoes.size(); i++) {
      System.out.print(i+1);
      System.out.print(" - ");
      System.out.println(this.opcoes.get(i));
    }
    System.out.println("0 - Sair");
  }

  /** Ler uma opção válida */
  private int lerOpcao() {
    int op; 
    Scanner is = new Scanner(System.in);

    System.out.print("Opção: ");
    op = is.nextInt();
    if (op<0 || op>this.opcoes.size()) {
      System.out.println("Opção Inválida!!!");
      op = -1;
    }
    return op;
  }

  public static void esperaReturn(){
    Scanner is = new Scanner(System.in);
    System.out.print("Pressione qualquer tecla para continuar... ");
    is.nextLine();
  }

  /**
   * Método para obter a opção lida
   */
  public int getOpcao() {
    return this.op;
  }
}

