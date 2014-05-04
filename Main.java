/**
 * Created with IntelliJ IDEA.
 * User: Tuthifruthi
 * Date: 27-04-14
 * Time: 12:23
 * To change this template use File | Settings | File Templates.
 */
import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;

public class Main {
    public static void main(String[] args)
    {
        Integer rojo;
        Integer azul;
        Integer naranjo;
        Integer verde;
        Integer amarillo;
        Character color;
        Character comodin;

        Character[][] tablero = new Character[15][15];

        System.out.println("Presione la tecla ENTER para iniciar el juego\n");
        Scanner keyIn = new Scanner(System.in);
        keyIn.nextLine();

        System.out.println("Indique cuántos bloques romper de cada color para finalizar:\n");

        System.out.println("Color Rojo:");
        Scanner scan1 = new Scanner(System.in);
        rojo=scan1.nextInt();

        System.out.println("Color Azul:");
        Scanner scan2 = new Scanner(System.in);
        azul=scan2.nextInt();

        System.out.println("Color Naranjo:");
        Scanner scan3 = new Scanner(System.in);
        naranjo = scan3.nextInt();

        System.out.println("Color Verde:");
        Scanner scan4 = new Scanner(System.in);
        verde = scan4.nextInt();

        System.out.println("Color Amarillo:");
        Scanner scan5 = new Scanner(System.in);
        amarillo = scan5.nextInt();

        //Tablero
        BloqueColor col = new BloqueColor();
        BloqueComodin com = new BloqueComodin();
        ColorCreator colc= new ColorCreator();
        ComodinCreator comc=new ComodinCreator();

        Random num=new Random();

        for(int i=0; i<15; i++)
        {
            for(int j=0; j<15;j++)
            {
                int  n = num.nextInt(100); //genera num entre 1 y 100

                if(n<=95)  //color = 95% posibilidades
                {
                    col.setColor(colc.crearBloque());  //se ejecuta función crear bloque de color random
                    color=col.getColor();
                    tablero[i][j]=color;
                }

                else if(n>95)   //comodin = 5% posibilidades
                {
                    com.setBloqueComodin(comc.crearBloque()); //se ejecuta función crear bloque comodin random
                    comodin=com.getBloqueComodin();
                    tablero[i][j]=comodin;
                }
            }
        }

        //Imprimir por pantalla el tablero
        for(int i = 0; i < 15; i++)
        {
            for(int j = 0; j < 15; j++)
            {
                System.out.printf("%c\t", tablero[i][j]);
            }
            System.out.println();
        }
    }
}
