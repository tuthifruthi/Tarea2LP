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

        Integer contadorrojo=0;
        Integer contadorazul=0;
        Integer contadornaranjo=0;
        Integer contadorverde=0;
        Integer contadoramarillo=0;

        Integer bloque1x=0;
        Integer bloque1y=0;
        Integer bloque2x=0;
        Integer bloque2y=0;

        Character color=null;
        Character comodin=null;
        Character bloque1=null;
        Character bloque2=null;
        Character bloque11=null;
        Character bloque22=null;

        Character[][] tablero = new Character[15][15];    //tablero original
        Character[][] tablerotemp = new Character[15][15]; //tablero temporal para realizar jugadas

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
        AbstractCreator col = new ColorCreator();
        Bloque blockcol= col.crearBloque();

        AbstractCreator com = new ComodinCreator();
        Bloque blockcom= com.crearBloque();


        Random num=new Random();

        for(int i=0; i<15; i++)
        {
            for(int j=0; j<15;j++)
            {
                int  n = num.nextInt(100); //genera num entre 1 y 100

                if(n<=95)  //color = 95% posibilidades
                {
                    color=blockcol.getBloque();  //se ejecuta función crear bloque de color random
                    tablero[i][j]=color;
                }

                else if(n>95)   //comodin = 5% posibilidades
                {
                    comodin=blockcom.getBloque(); //se ejecuta función crear bloque comodin random
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

        //mientras no se rompan los bloques ingresados al inicio del juego, el juego continúa
        while(contadorrojo!=rojo && contadoramarillo!=amarillo && contadorazul!=azul && contadornaranjo!=naranjo && contadorverde!=verde)
        {
            //realizar jugada
            System.out.println("Ingrese las coordenadas de los bloques que desea intercambiar de posición: ");

            System.out.println("Bloque 1: ");
            System.out.println("Coordenada X: ");
            Scanner scan6 = new Scanner(System.in);
            bloque1x = scan6.nextInt();

            System.out.println("Coordenada Y: ");
            Scanner scan8 = new Scanner(System.in);
            bloque1y = scan8.nextInt();

            System.out.println("Bloque 2: ");
            System.out.println("Coordenada X: ");
            Scanner scan7 = new Scanner(System.in);
            bloque2x = scan7.nextInt();

            System.out.println("Coordenada Y: ");
            Scanner scan9 = new Scanner(System.in);
            bloque2y = scan9.nextInt();

            //Intercambio de posición de bloques pedidos al usuario
            for(int i=0; i<15; i++)
            {
                for(int j=0; j<15;j++)
                {
                    //busca los bloques pedidos por el usuario y los almacena en el tablero temporal
                    if(i==bloque1x && j==bloque1y) //para bloque 1
                    {
                        bloque1=tablero[i][j];
                        System.out.println("Ha pedido intercambiar el bloque %c"+bloque1);
                    }

                    else if(i==bloque2x && j==bloque2y) //para bloque 2
                    {
                        bloque2=tablero[i][j];
                        System.out.println("con el bloque %c"+bloque2);
                    }

                    else  //si no son los pedidos, los guarda directamente en el tablero temporal
                    {
                        tablerotemp[i][j]=tablero[i][j];
                    }

                    tablerotemp[bloque1x][bloque1y]=bloque2;
                    tablerotemp[bloque2x][bloque2y]=bloque1;

                }
            }

            //Eliminación de bloques iguales
            for(int i=0; i<15; i++)
            {
                for(int j=0; j<15;j++)
                {
                    //bloque 1
                    if(tablerotemp[][])  // dos bloques a su derecha
                    {

                    }
                    else if()    //dos bloques a su izquierda
                    {

                    }
                    else if()  // dos bloques arriba
                    {

                    }
                    else if()   //dos bloques abajo
                    {

                    }
                    else if()     //un bloque arriba y otro abajo
                    {

                    }
                    else if()     //un bloque a la derecha y el otro a la izquierda
                    {

                    }
                }
            }
            //Se imprime el tablero al finalizar una jugada
            for(int i = 0; i < 15; i++)
            {
                for(int j = 0; j < 15; j++)
                {
                    System.out.printf("%c\t", tablero[i][j]);
                }
                System.out.println();
            }
        }
        System.out.println("El juego ha finalizado!");
        System.out.println("Bloques Restantes: ");
        System.out.println("Bloques Azules: %c "+contadorazul);
        System.out.println("Bloques Rojos: %c"+contadorrojo);
        System.out.println("Bloques Amarillos: %c"+contadoramarillo);
        System.out.println("Bloques Verdes: %c"+contadorverde);
        System.out.println("Bloques Naranjos: %c"+contadornaranjo);

        if(bloque1=='R')
        {
            contadorrojo+=3;
        }
        else if(bloque1=='B')
        {
            contadorazul+=3;
        }
        else if(bloque1=='O')
        {
            contadornaranjo+=3;
        }
        else if(bloque1=='G')
        {
            contadorverde+=3;
        }
        else if(bloque1=='Y')
        {
            contadoramarillo+=3;
        }
    }
}
