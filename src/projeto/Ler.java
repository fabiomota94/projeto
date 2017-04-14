/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author a34693
 */
public class Ler {

    public static String umaString() {
        String s = "";
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            s = in.readLine();
        } catch (IOException e) {
            System.out.println("Erro ao ler fluxo de entrada.");
            System.out.println("Tente novamente\n");
        }
        return s;
    }

    public static int umInt() {
        while (true) {
            try {
                return Integer.valueOf(umaString().trim()).intValue();
            } catch (Exception e) {
                System.out.println("Não é um inteiro válido!!!\n");
                System.out.println("Tente novamente\n");
            }
        }
    }

    public static double umDoub() {
        while (true) {
            try {
                return Double.valueOf(umaString().trim()).intValue();
            } catch (Exception e) {
                System.out.println("Não é um Double válido!!!");
                System.out.println("Tente novamente\n");
            }
        }
    }

    public static boolean umBoo() {
        while (true) {
            try {
                return Boolean.valueOf(umaString().trim()).booleanValue();
            } catch (Exception e) {
                System.out.println("Não é um Bool válido!!!");
                System.out.println("Tente novamente\n");
            }
        }
    }

    public static float umfloat() {
        while (true) {
            try {
                return Float.valueOf(umaString().trim()).intValue();
            } catch (Exception e) {
                System.out.println("Não é um Double válido!!!");
                System.out.println("Tente novamente\n");
            }
        }
    }

    public static char umchar() {
        while (true) {
            try {
                return umaString().trim().charAt(0);
            } catch (Exception e) {
                System.out.println("Não é um Char válido!!!");
                System.out.println("Tente novamente\n");
            }
        }
    }

    public static int escolha1() {
        int a;
        while (true) {
            try {
                do {
                    a = Integer.valueOf(umaString().trim()).intValue();
                    if (a < 0 | a > 1) {
                        System.out.println("Escolha Invalida !");
                    }
                } while (a < 0 | a > 1);
                return a;
            } catch (Exception e) {
                System.out.println("Não é um inteiro válido!!!\n");
                System.out.println("Tente novamente\n");
            }
        }

    }

    public static int escolha2() {
        int a;
        while (true) {
            try {
                do {
                    a = Integer.valueOf(umaString().trim()).intValue();
                    if (a < 0 | a > 2) {
                        System.out.println("Escolha Invalida !");
                    }
                } while (a < 0 | a > 2);
                return a;
            } catch (Exception e) {
                System.out.println("Não é um inteiro válido!!!\n");
                System.out.println("Tente novamente\n");
            }
        }

    }

    public static String umaNoticia() { // max lenght 180 caracteres
        String s = "";
     
       
        while(true){
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            s = in.readLine();
           
            if(s.length()<=180){
              break;
                        }
           
            if(s.length()>180){
                System.out.println("Noticia com demasiados caracteres! Tente novamente.");
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler a noticia.");
         
        }
             
    }
          return s;
    }
    
    public static int escolha3() {
        int a;
        while (true) {
            try {
                do {
                    a = Integer.valueOf(umaString().trim()).intValue();
                    if (a < 0 | a > 3) {
                        System.out.println("Escolha Invalida !");
                    }
                } while (a < 0 | a > 3);
                return a;
            } catch (Exception e) {
                System.out.println("Não é um inteiro válido!!!\n");
                System.out.println("Tente novamente\n");
            }
        }

    }

    public static int escolha4() {
        int a;
        while (true) {
            try {
                do {
                    a = Integer.valueOf(umaString().trim()).intValue();
                    if (a < 0 | a > 4) {
                        System.out.println("Escolha Invalida !");
                    }
                } while (a < 0 | a > 4);
                return a;
            } catch (Exception e) {
                System.out.println("Não é um inteiro válido!!!\n");
                System.out.println("Tente novamente\n");
            }
        }

    }

    public static int escolha5() {
        int a;
        while (true) {
            try {
                do {
                    a = Integer.valueOf(umaString().trim()).intValue();
                    if (a < 0 | a > 5) {
                        System.out.println("Escolha Invalida !");
                    }
                } while (a < 0 | a > 5);
                return a;
            } catch (Exception e) {
                System.out.println("Não é um inteiro válido!!!\n");
                System.out.println("Tente novamente\n");
            }
        }
    }

    public static int escolha6() {
        int a;
        while (true) {
            try {
                do {
                    a = Integer.valueOf(umaString().trim()).intValue();
                    if (a < 0 | a > 6) {
                        System.out.println("Escolha Invalida !");
                    }
                } while (a < 0 | a > 6);
                return a;
            } catch (Exception e) {
                System.out.println("Não é um inteiro válido!!!\n");
                System.out.println("Tente novamente\n");
            }
        }
    }

    public static int ano() {
        int a;
        while (true) {
            try {
                do {
                    a = Integer.valueOf(umaString().trim()).intValue();
                    if (a >= 2001) {
                        System.out.println("Não é posivel registar esse atleta, so Atletas entre o ano 1950 e o ano 2000 introduza o ano novamente\n!");
                    }
                    if (a < 1954) {
                        System.out.println("Não é posivel registar esse atleta, so Atletas entre o ano 1950 e o ano 1999 introduza o ano novamente\n!");
                    }
                } while (a > 2000 | a < 1954);
                return a;
            } catch (Exception e) {
                System.out.println("Não é um inteiro válido!!!\n");
                System.out.println("Tente novamente\n");
            }
        }
    }

    public static int dia() {
        int a;
        while (true) {
            try {
                do {
                    a = Integer.valueOf(umaString().trim()).intValue();
                    if (a < 1 | a > 31) {
                        System.out.println("Dia inexistente!");
                    }
                } while (a < 1 | a > 31);
                return a;
            } catch (Exception e) {
                System.out.println("Não é um inteiro válido!!!\n");
                System.out.println("Tente novamente\n");
            }
        }
    }

    public static int Mes() {
        int a;
        while (true) {
            try {
                do {
                    a = Integer.valueOf(umaString().trim()).intValue();
                    if (a < 0 | a > 12) {
                        System.out.println("Mes invalido !");
                    }
                } while (a < 0 | a > 12);
                return a;
            } catch (Exception e) {
                System.out.println("Não é um inteiro válido!!!\n");
                System.out.println("Tente novamente\n");
            }
        }

    }
}
