/**
 * Created with IntelliJ IDEA.
 * User: Tuthifruthi
 * Date: 27-04-14
 * Time: 12:23
 * To change this template use File | Settings | File Templates.
 */
import java.util.Scanner;
import java.util.Random;


public class Main {
    public static void main(String[] args)
    {
        //para pedir colores por pantalla
        Integer rojo;
        Integer azul;
        Integer naranjo;
        Integer verde;
        Integer amarillo;

        //contadores de colores
        Integer contadorrojo=0;
        Integer contadorazul=0;
        Integer contadornaranjo=0;
        Integer contadorverde=0;
        Integer contadoramarillo=0;

        //para intercambio de bloques
        Integer bloque1x;
        Integer bloque1y;
        Integer bloque2x;
        Integer bloque2y;

        Bloque[][] tablero = new Bloque[15][15];    //tablero original
        Bloque[][] tablerotemp = new Bloque[15][15]; //tablero temporal para realizar jugadas.

        System.out.println("Presione la tecla ENTER para iniciar el juego\n");
        Scanner keyIn = new Scanner(System.in);
        keyIn.nextLine();

        System.out.println("Indique cuántos bloques romper de cada color para finalizar:\n");

        System.out.println("Color Rojo:");
        Scanner scan1 = new Scanner(System.in);
        rojo=scan1.nextInt();

        System.out.println("Color Azul:");
        azul=scan1.nextInt();

        System.out.println("Color Naranjo:");
        naranjo = scan1.nextInt();

        System.out.println("Color Verde:");
        verde = scan1.nextInt();

        System.out.println("Color Amarillo:\n");
        amarillo = scan1.nextInt();

        //Objetos
        AbstractCreator col = new ColorCreator();
        AbstractCreator com = new ComodinCreator();
        Bloque bloque1=new BloqueColor();
        Bloque bloque2=new BloqueColor();


        Random num=new Random();

        for(int i=0; i<15; i++)
        {
            for(int j=0; j<15;j++)
            {
                int  n = num.nextInt(100); //genera num entre 1 y 100

                if(n<95)  //color = 95% posibilidades
                {
                    tablero[i][j]=col.crearBloque();
                }

                else if(n>=95)   //comodin = 5% posibilidades
                {
                    tablero[i][j]= com.crearBloque();
                }
            }
        }

        //Imprimir por pantalla el tablero
        for(int i = 0; i < 15; i++)
        {
            for(int j = 0; j < 15; j++)
            {
                System.out.printf("%c\t", tablero[i][j].getBloque());
            }
            System.out.println();
        }
        System.out.println("\n");

         //mientras no se rompan los bloques ingresados al inicio del juego, el juego continúa
        while(contadorrojo!=rojo && contadoramarillo!=amarillo && contadorazul!=azul && contadornaranjo!=naranjo && contadorverde!=verde)
        {
            //limpiar pantalla por jugada
            System.out.println("\033[2J\033[H");

            //realizar jugada
            System.out.println("Ingrese las coordenadas de los bloques que desea intercambiar de posición: ");

            System.out.println("Bloque 1: ");
            System.out.println("Coordenada X: ");
            bloque1x = scan1.nextInt();

            System.out.println("Coordenada Y: ");
            bloque1y = scan1.nextInt();

            System.out.println("Bloque 2: ");
            System.out.println("Coordenada X: ");
            bloque2x = scan1.nextInt();

            System.out.println("Coordenada Y: ");
            bloque2y = scan1.nextInt();
            System.out.println("\n");

            //Intercambio de posición de bloques pedidos al usuario. Almacena en tablerotemp
            for(int i=0; i<15; i++)
            {
                for(int j=0; j<15;j++)
                {
                    //busca los bloques pedidos por el usuario y los almacena en el tablero temporal
                    if(i==bloque1x && j==bloque1y) //para bloque 1
                    {
                        bloque1=tablero[i][j];
                        System.out.println("Ha pedido intercambiar el bloque "+bloque1.getBloque());
                    }

                    else if(i==bloque2x && j==bloque2y) //para bloque 2
                    {
                        bloque2=tablero[i][j];
                        System.out.println("con el bloque "+bloque2.getBloque());
                        System.out.println("\n");
                    }

                    else  //si no son los pedidos, los guarda directamente en el tablero temporal
                    {
                        tablerotemp[i][j]=tablero[i][j];
                    }

                    tablerotemp[bloque1x][bloque1y]=bloque2;
                    tablerotemp[bloque2x][bloque2y]=bloque1;

                }
            }

            //Imprime por pantalla los bloques cambiados de posicion
            for(int i = 0; i < 15; i++)
            {
                for(int j = 0; j < 15; j++)
                {
                    System.out.printf("%c\t", (tablerotemp[i][j]).getBloque());
                }
                System.out.println();
            }
            System.out.println("\n");


            //Eliminación de bloques iguales

            //Bloque 1
             if(bloque1y<13 && (tablerotemp[bloque1x][bloque1y+1].getBloque()==bloque1.getBloque()) && (tablerotemp[bloque1x][bloque1y+2].getBloque()==bloque1.getBloque()))  // dos bloques a su derecha
            {
                if(bloque1.getBloque()=='R')
                {
                    contadorrojo+=3;
                }
                else if(bloque1.getBloque()=='B')
                {
                    contadorazul+=3;
                }
                else if(bloque1.getBloque()=='O')
                {
                    contadornaranjo+=3;
                }
                else if(bloque1.getBloque()=='G')
                {
                    contadorverde+=3;
                }
                else if(bloque1.getBloque()=='Y')
                {
                    contadoramarillo+=3;
                }

                if (bloque1 instanceof BloqueColor && tablerotemp[bloque1x][bloque1y+1] instanceof BloqueColor && tablerotemp[bloque1x][bloque1y+2] instanceof BloqueColor)  //comprueba que los 3 sean colores
                {
                   System.out.println("Se encontraron dos bloques a la derecha del bloque %c que son iguales a el, ELIMINAR"+bloque1.getBloque());
                   bloque1.DestruirBloque(bloque1);
                   bloque1.DestruirBloque(tablerotemp[bloque1x][bloque1y+1]);
                   bloque1.DestruirBloque(tablerotemp[bloque1x][bloque1y+2]);
                }

                for(int i = 0; i < 15; i++)
                {
                    for(int j = 0; j < 15; j++)
                    {
                        System.out.printf("%c\t\n", (tablerotemp[i][j]).getBloque());
                    }
                    System.out.println();
                }

                System.out.println("Bloques Restantes: ");
                System.out.println("Bloques Azules: %d "+contadorazul);
                System.out.println("Bloques Rojos: %d"+contadorrojo);
                System.out.println("Bloques Amarillos: %d"+contadoramarillo);
                System.out.println("Bloques Verdes: %d"+contadorverde);
                System.out.println("Bloques Naranjos: %d"+contadornaranjo);
                System.out.println("\n");
            }
            else if(bloque1y>1 && tablerotemp[bloque1x][bloque1y-1].getBloque()==bloque1.getBloque() && tablerotemp[bloque1x][bloque1y-2].getBloque()==bloque1.getBloque())    //dos bloques a su izquierda
            {
                if(bloque1.getBloque()=='R')
                {
                    contadorrojo+=3;
                }
                else if(bloque1.getBloque()=='B')
                {
                    contadorazul+=3;
                }
                else if(bloque1.getBloque()=='O')
                {
                    contadornaranjo+=3;
                }
                else if(bloque1.getBloque()=='G')
                {
                    contadorverde+=3;
                }
                else if(bloque1.getBloque()=='Y')
                {
                    contadoramarillo+=3;
                }

                if (bloque1 instanceof BloqueColor && tablerotemp[bloque1x][bloque1y-1] instanceof BloqueColor && tablerotemp[bloque1x][bloque1y-2] instanceof BloqueColor)
                {
                    System.out.println("Se encontraron dos bloques a la izquierda del bloque %c que son iguales a el, ELIMINAR"+bloque1.getBloque());
                    bloque1.DestruirBloque(bloque1);
                    bloque1.DestruirBloque(tablerotemp[bloque1x][bloque1y-1]);
                    bloque1.DestruirBloque(tablerotemp[bloque1x][bloque1y-2]);
                }

                for(int i = 0; i < 15; i++)
                {
                    for(int j = 0; j < 15; j++)
                    {
                        System.out.printf("%c\t\n", (tablerotemp[i][j]).getBloque());
                    }
                    System.out.println();
                }

                System.out.println("Bloques Restantes: ");
                System.out.println("Bloques Azules: %d "+contadorazul);
                System.out.println("Bloques Rojos: %d"+contadorrojo);
                System.out.println("Bloques Amarillos: %d"+contadoramarillo);
                System.out.println("Bloques Verdes: %d"+contadorverde);
                System.out.println("Bloques Naranjos: %d"+contadornaranjo);
                System.out.println("\n");
            }
            else if(bloque1x>1 && tablerotemp[bloque1x-1][bloque1y].getBloque()==bloque1.getBloque() && tablerotemp[bloque1x-2][bloque1y].getBloque()==bloque1.getBloque())  // dos bloques arriba
            {
                if(bloque1.getBloque()=='R')
                {
                    contadorrojo+=3;
                }
                else if(bloque1.getBloque()=='B')
                {
                    contadorazul+=3;
                }
                else if(bloque1.getBloque()=='O')
                {
                    contadornaranjo+=3;
                }
                else if(bloque1.getBloque()=='G')
                {
                    contadorverde+=3;
                }
                else if(bloque1.getBloque()=='Y')
                {
                    contadoramarillo+=3;
                }

                if (bloque1 instanceof BloqueColor && tablerotemp[bloque1x-1][bloque1y] instanceof BloqueColor && tablerotemp[bloque1x-2][bloque1y] instanceof BloqueColor)
                {
                    System.out.println("Se encontraron dos bloques arriba del bloque %c que son iguales a el, ELIMINAR"+bloque1.getBloque());
                    bloque1.DestruirBloque(bloque1);
                    bloque1.DestruirBloque(tablerotemp[bloque1x-1][bloque1y]);
                    bloque1.DestruirBloque(tablerotemp[bloque1x-2][bloque1y]);
                }

                for(int i = 0; i < 15; i++)
                {
                    for(int j = 0; j < 15; j++)
                    {
                        System.out.printf("%c\t\n", (tablerotemp[i][j]).getBloque());
                    }
                    System.out.println();
                }

                System.out.println("Bloques Restantes: ");
                System.out.println("Bloques Azules: %d "+contadorazul);
                System.out.println("Bloques Rojos: %d"+contadorrojo);
                System.out.println("Bloques Amarillos: %d"+contadoramarillo);
                System.out.println("Bloques Verdes: %d"+contadorverde);
                System.out.println("Bloques Naranjos: %d"+contadornaranjo);
                System.out.println("\n");
            }
            else if(bloque1x<13 && tablerotemp[bloque1x+1][bloque1y].getBloque()==bloque1.getBloque() && tablerotemp[bloque1x+2][bloque1y].getBloque()==bloque1.getBloque())   //dos bloques abajo
            {
                if(bloque1.getBloque()=='R')
                {
                    contadorrojo+=3;
                }
                else if(bloque1.getBloque()=='B')
                {
                    contadorazul+=3;
                }
                else if(bloque1.getBloque()=='O')
                {
                    contadornaranjo+=3;
                }
                else if(bloque1.getBloque()=='G')
                {
                    contadorverde+=3;
                }
                else if(bloque1.getBloque()=='Y')
                {
                    contadoramarillo+=3;
                }

                if (bloque1 instanceof BloqueColor && tablerotemp[bloque1x+1][bloque1y] instanceof BloqueColor && tablerotemp[bloque1x+2][bloque1y] instanceof BloqueColor)
                {
                    System.out.println("Se encontraron dos bloques abajo del bloque %c que son iguales a el, ELIMINAR"+bloque1.getBloque());
                    bloque1.DestruirBloque(bloque1);
                    bloque1.DestruirBloque(tablerotemp[bloque1x+1][bloque1y]);
                    bloque1.DestruirBloque(tablerotemp[bloque1x+2][bloque1y]);
                }

                for(int i = 0; i < 15; i++)
                {
                    for(int j = 0; j < 15; j++)
                    {
                        System.out.printf("%c\t\n", (tablerotemp[i][j]).getBloque());
                    }
                    System.out.println();
                }

                System.out.println("Bloques Restantes: ");
                System.out.println("Bloques Azules: %d "+contadorazul);
                System.out.println("Bloques Rojos: %d"+contadorrojo);
                System.out.println("Bloques Amarillos: %d"+contadoramarillo);
                System.out.println("Bloques Verdes: %d"+contadorverde);
                System.out.println("Bloques Naranjos: %d"+contadornaranjo);
                System.out.println("\n");
            }
            else if((bloque1x>0 && bloque1x<14) && tablerotemp[bloque1x+1][bloque1y].getBloque()==bloque1.getBloque() && tablerotemp[bloque1x-1][bloque1y].getBloque()==bloque1.getBloque())//un bloque arriba y otro abajo
            {
                if(bloque1.getBloque()=='R')
                {
                    contadorrojo+=3;
                }
                else if(bloque1.getBloque()=='B')
                {
                    contadorazul+=3;
                }
                else if(bloque1.getBloque()=='O')
                {
                    contadornaranjo+=3;
                }
                else if(bloque1.getBloque()=='G')
                {
                    contadorverde+=3;
                }
                else if(bloque1.getBloque()=='Y')
                {
                    contadoramarillo+=3;
                }

                if (bloque1 instanceof BloqueColor && tablerotemp[bloque1x+1][bloque1y] instanceof BloqueColor && tablerotemp[bloque1x-1][bloque1y] instanceof BloqueColor)
                {
                    System.out.println("Se encontro un bloque arriba y otro abajo del bloque %c que son iguales a el, ELIMINAR"+bloque1.getBloque());
                    bloque1.DestruirBloque(bloque1);
                    bloque1.DestruirBloque(tablerotemp[bloque1x+1][bloque1y]);
                    bloque1.DestruirBloque(tablerotemp[bloque1x-1][bloque1y]);
                }


                for(int i = 0; i < 15; i++)
                {
                    for(int j = 0; j < 15; j++)
                    {
                        System.out.printf("%c\t\n", (tablerotemp[i][j]).getBloque());
                    }
                    System.out.println();
                }

                System.out.println("Bloques Restantes: ");
                System.out.println("Bloques Azules: %d "+contadorazul);
                System.out.println("Bloques Rojos: %d"+contadorrojo);
                System.out.println("Bloques Amarillos: %d"+contadoramarillo);
                System.out.println("Bloques Verdes: %d"+contadorverde);
                System.out.println("Bloques Naranjos: %d"+contadornaranjo);
                System.out.println("\n");
            }
            else if((bloque1y>0 && bloque1y<14) && tablerotemp[bloque1x][bloque1y-1].getBloque()==bloque1.getBloque() && tablerotemp[bloque1x][bloque1y+1].getBloque()==bloque1.getBloque())     //un bloque a la derecha y el otro a la izquierda
            {
                if(bloque1.getBloque()=='R')
                {
                    contadorrojo+=3;
                }
                else if(bloque1.getBloque()=='B')
                {
                    contadorazul+=3;
                }
                else if(bloque1.getBloque()=='O')
                {
                    contadornaranjo+=3;
                }
                else if(bloque1.getBloque()=='G')
                {
                    contadorverde+=3;
                }
                else if(bloque1.getBloque()=='Y')
                {
                    contadoramarillo+=3;
                }

                if (bloque1 instanceof BloqueColor && tablerotemp[bloque1x][bloque1y-1] instanceof BloqueColor && tablerotemp[bloque1x][bloque1y+1] instanceof BloqueColor)
                {
                    System.out.println("Se encontro un bloque a la derecha y otro a la izquierda del bloque %c que son iguales a el, ELIMINAR"+bloque1.getBloque());
                    bloque1.DestruirBloque(bloque1);
                    bloque1.DestruirBloque(tablerotemp[bloque1x][bloque1y-1]);
                    bloque1.DestruirBloque(tablerotemp[bloque1x][bloque1y+1]);
                }

                for(int i = 0; i < 15; i++)
                {
                    for(int j = 0; j < 15; j++)
                    {
                        System.out.printf("%c\t\n", (tablerotemp[i][j]).getBloque());
                    }
                    System.out.println();
                }

                System.out.println("Bloques Restantes: ");
                System.out.println("Bloques Azules: %d "+contadorazul);
                System.out.println("Bloques Rojos: %d"+contadorrojo);
                System.out.println("Bloques Amarillos: %d"+contadoramarillo);
                System.out.println("Bloques Verdes: %d"+contadorverde);
                System.out.println("Bloques Naranjos: %d"+contadornaranjo);
                System.out.println("\n");
             }
                else
                  {
                    System.out.println("No se encontraron bloques a eliminar aledaños al bloque 1");
                    System.out.println("\n");
                  }

             //Bloque 2
            if(bloque2y<13 && (tablerotemp[bloque2x][bloque2y+1].getBloque()==bloque2.getBloque()) && (tablerotemp[bloque2x][bloque2y+2].getBloque()==bloque2.getBloque()))  // dos bloques a su derecha
            {
                if(bloque2.getBloque()=='R')
                {
                    contadorrojo+=3;
                }
                else if(bloque2.getBloque()=='B')
                {
                    contadorazul+=3;
                }
                else if(bloque2.getBloque()=='O')
                {
                    contadornaranjo+=3;
                }
                else if(bloque2.getBloque()=='G')
                {
                    contadorverde+=3;
                }
                else if(bloque2.getBloque()=='Y')
                {
                    contadoramarillo+=3;
                }

                if (bloque2 instanceof BloqueColor && tablerotemp[bloque2x][bloque2y+1] instanceof BloqueColor && tablerotemp[bloque2x][bloque2y+2] instanceof BloqueColor)
                {
                    System.out.println("Se encontraron dos bloques a la derecha del bloque %c que son iguales a el, ELIMINAR"+bloque2.getBloque());
                    bloque2.DestruirBloque(bloque1);
                    bloque2.DestruirBloque(tablerotemp[bloque2x][bloque2y+1]);
                    bloque2.DestruirBloque(tablerotemp[bloque2x][bloque2y+2]);
                }

                for(int i = 0; i < 15; i++)
                {
                    for(int j = 0; j < 15; j++)
                    {
                        System.out.printf("%c\t\n", (tablerotemp[i][j]).getBloque());
                    }
                    System.out.println();
                }

                System.out.println("Bloques Restantes: ");
                System.out.println("Bloques Azules: %d "+contadorazul);
                System.out.println("Bloques Rojos: %d"+contadorrojo);
                System.out.println("Bloques Amarillos: %d"+contadoramarillo);
                System.out.println("Bloques Verdes: %d"+contadorverde);
                System.out.println("Bloques Naranjos: %d"+contadornaranjo);
                System.out.println("\n");
            }
            else if(bloque2y>1 && tablerotemp[bloque2x][bloque2y-1].getBloque()==bloque2.getBloque() && tablerotemp[bloque2x][bloque2y-2].getBloque()==bloque1.getBloque())    //dos bloques a su izquierda
            {
                if(bloque2.getBloque()=='R')
                {
                    contadorrojo+=3;
                }
                else if(bloque2.getBloque()=='B')
                {
                    contadorazul+=3;
                }
                else if(bloque2.getBloque()=='O')
                {
                    contadornaranjo+=3;
                }
                else if(bloque2.getBloque()=='G')
                {
                    contadorverde+=3;
                }
                else if(bloque2.getBloque()=='Y')
                {
                    contadoramarillo+=3;
                }

                if (bloque2 instanceof BloqueColor && tablerotemp[bloque2x][bloque2y-1] instanceof BloqueColor && tablerotemp[bloque2x][bloque2y-2] instanceof BloqueColor)
                {
                    System.out.println("Se encontraron dos bloques a la izquierda del bloque %c que son iguales a el, ELIMINAR"+bloque2.getBloque());
                    bloque2.DestruirBloque(bloque2);
                    bloque2.DestruirBloque(tablerotemp[bloque2x][bloque2y-1]);
                    bloque2.DestruirBloque(tablerotemp[bloque2x][bloque2y-2]);
                }

                for(int i = 0; i < 15; i++)
                {
                    for(int j = 0; j < 15; j++)
                    {
                        System.out.printf("%c\t\n", (tablerotemp[i][j]).getBloque());
                    }
                    System.out.println();
                }

                System.out.println("Bloques Restantes: ");
                System.out.println("Bloques Azules: %d "+contadorazul);
                System.out.println("Bloques Rojos: %d"+contadorrojo);
                System.out.println("Bloques Amarillos: %d"+contadoramarillo);
                System.out.println("Bloques Verdes: %d"+contadorverde);
                System.out.println("Bloques Naranjos: %d"+contadornaranjo);
                System.out.println("\n");
            }
            else if(bloque2x>1 && tablerotemp[bloque2x-1][bloque2y].getBloque()==bloque2.getBloque() && tablerotemp[bloque2x-2][bloque2y].getBloque()==bloque2.getBloque())  // dos bloques arriba
            {
                if(bloque2.getBloque()=='R')
                {
                    contadorrojo+=3;
                }
                else if(bloque2.getBloque()=='B')
                {
                    contadorazul+=3;
                }
                else if(bloque2.getBloque()=='O')
                {
                    contadornaranjo+=3;
                }
                else if(bloque2.getBloque()=='G')
                {
                    contadorverde+=3;
                }
                else if(bloque2.getBloque()=='Y')
                {
                    contadoramarillo+=3;
                }

                if (bloque2 instanceof BloqueColor && tablerotemp[bloque2x-1][bloque2y] instanceof BloqueColor && tablerotemp[bloque2x-2][bloque2y] instanceof BloqueColor)
                {
                    System.out.println("Se encontraron dos bloques arriba del bloque %c que son iguales a el, ELIMINAR"+bloque2.getBloque());
                    bloque2.DestruirBloque(bloque2);
                    bloque2.DestruirBloque(tablerotemp[bloque2x-1][bloque2y]);
                    bloque2.DestruirBloque(tablerotemp[bloque2x-2][bloque2y]);
                }

                for(int i = 0; i < 15; i++)
                {
                    for(int j = 0; j < 15; j++)
                    {
                        System.out.printf("%c\t\n", (tablerotemp[i][j]).getBloque());
                    }
                    System.out.println();
                }

                System.out.println("Bloques Restantes: ");
                System.out.println("Bloques Azules: %d "+contadorazul);
                System.out.println("Bloques Rojos: %d"+contadorrojo);
                System.out.println("Bloques Amarillos: %d"+contadoramarillo);
                System.out.println("Bloques Verdes: %d"+contadorverde);
                System.out.println("Bloques Naranjos: %d"+contadornaranjo);
                System.out.println("\n");
            }
            else if(bloque2x<13 && tablerotemp[bloque2x+1][bloque2y].getBloque()==bloque2.getBloque() && tablerotemp[bloque2x+2][bloque2y].getBloque()==bloque2.getBloque())   //dos bloques abajo
            {
                if(bloque2.getBloque()=='R')
                {
                    contadorrojo+=3;
                }
                else if(bloque2.getBloque()=='B')
                {
                    contadorazul+=3;
                }
                else if(bloque2.getBloque()=='O')
                {
                    contadornaranjo+=3;
                }
                else if(bloque2.getBloque()=='G')
                {
                    contadorverde+=3;
                }
                else if(bloque2.getBloque()=='Y')
                {
                    contadoramarillo+=3;
                }

                if (bloque2 instanceof BloqueColor && tablerotemp[bloque2x+1][bloque2y] instanceof BloqueColor && tablerotemp[bloque2x+2][bloque2y] instanceof BloqueColor)
                {
                    System.out.println("Se encontraron dos bloques abajo del bloque %c que son iguales a el, ELIMINAR"+bloque2.getBloque());
                    bloque2.DestruirBloque(bloque2);
                    bloque2.DestruirBloque(tablerotemp[bloque2x+1][bloque2y]);
                    bloque2.DestruirBloque(tablerotemp[bloque2x+2][bloque2y]);
                }

                for(int i = 0; i < 15; i++)
                {
                    for(int j = 0; j < 15; j++)
                    {
                        System.out.printf("%c\t\n", (tablerotemp[i][j]).getBloque());
                    }
                    System.out.println();
                }

                System.out.println("Bloques Restantes: ");
                System.out.println("Bloques Azules: %d "+contadorazul);
                System.out.println("Bloques Rojos: %d"+contadorrojo);
                System.out.println("Bloques Amarillos: %d"+contadoramarillo);
                System.out.println("Bloques Verdes: %d"+contadorverde);
                System.out.println("Bloques Naranjos: %d"+contadornaranjo);
                System.out.println("\n");
            }
            else if((bloque2x>0 && bloque2x<14) && tablerotemp[bloque2x+1][bloque2y].getBloque()==bloque2.getBloque() && tablerotemp[bloque2x-1][bloque2y].getBloque()==bloque2.getBloque())//un bloque arriba y otro abajo
            {
                if(bloque2.getBloque()=='R')
                {
                    contadorrojo+=3;
                }
                else if(bloque2.getBloque()=='B')
                {
                    contadorazul+=3;
                }
                else if(bloque2.getBloque()=='O')
                {
                    contadornaranjo+=3;
                }
                else if(bloque2.getBloque()=='G')
                {
                    contadorverde+=3;
                }
                else if(bloque2.getBloque()=='Y')
                {
                    contadoramarillo+=3;
                }

                if (bloque2 instanceof BloqueColor && tablerotemp[bloque2x+1][bloque2y] instanceof BloqueColor && tablerotemp[bloque2x-1][bloque2y] instanceof BloqueColor)
                {
                    System.out.println("Se encontro un bloque arriba y otro abajo del bloque %c que son iguales a el, ELIMINAR"+bloque2.getBloque());
                    bloque2.DestruirBloque(bloque2);
                    bloque2.DestruirBloque(tablerotemp[bloque2x+1][bloque2y]);
                    bloque2.DestruirBloque(tablerotemp[bloque2x-1][bloque2y]);
                }


                for(int i = 0; i < 15; i++)
                {
                    for(int j = 0; j < 15; j++)
                    {
                        System.out.printf("%c\t\n", (tablerotemp[i][j]).getBloque());
                    }
                    System.out.println();
                }

                System.out.println("Bloques Restantes: ");
                System.out.println("Bloques Azules: %d "+contadorazul);
                System.out.println("Bloques Rojos: %d"+contadorrojo);
                System.out.println("Bloques Amarillos: %d"+contadoramarillo);
                System.out.println("Bloques Verdes: %d"+contadorverde);
                System.out.println("Bloques Naranjos: %d"+contadornaranjo);
                System.out.println("\n");
            }
            else if((bloque2y>0 && bloque2y<14) && tablerotemp[bloque2x][bloque2y-1].getBloque()==bloque2.getBloque() && tablerotemp[bloque2x][bloque2y+1].getBloque()==bloque2.getBloque())     //un bloque a la derecha y el otro a la izquierda
            {
                if(bloque2.getBloque()=='R')
                {
                    contadorrojo+=3;
                }
                else if(bloque2.getBloque()=='B')
                {
                    contadorazul+=3;
                }
                else if(bloque2.getBloque()=='O')
                {
                    contadornaranjo+=3;
                }
                else if(bloque2.getBloque()=='G')
                {
                    contadorverde+=3;
                }
                else if(bloque2.getBloque()=='Y')
                {
                    contadoramarillo+=3;
                }

                if (bloque2 instanceof BloqueColor && tablerotemp[bloque2x][bloque2y-1] instanceof BloqueColor && tablerotemp[bloque2x][bloque2y+1] instanceof BloqueColor)
                {
                    System.out.println("Se encontro un bloque a la derecha y otro a la izquierda del bloque %c que son iguales a el, ELIMINAR"+bloque2.getBloque());
                    bloque2.DestruirBloque(bloque2);
                    bloque2.DestruirBloque(tablerotemp[bloque2x][bloque2y-1]);
                    bloque2.DestruirBloque(tablerotemp[bloque2x][bloque2y+1]);
                }

                for(int i = 0; i < 15; i++)
                {
                    for(int j = 0; j < 15; j++)
                    {
                        System.out.printf("%c\t\n", (tablerotemp[i][j]).getBloque());
                    }
                    System.out.println();
                }

                System.out.println("Bloques Restantes: ");
                System.out.println("Bloques Azules: %d "+contadorazul);
                System.out.println("Bloques Rojos: %d"+contadorrojo);
                System.out.println("Bloques Amarillos: %d"+contadoramarillo);
                System.out.println("Bloques Verdes: %d"+contadorverde);
                System.out.println("Bloques Naranjos: %d"+contadornaranjo);
                System.out.println("\n");
            }
            else
            {
                System.out.println("No se encontraron bloques a eliminar aledaños al bloque 2");
                System.out.println("\n");
            }

            //Eliminación considerando comodines
            if (bloque1 instanceof BloqueComodin || bloque2 instanceof BloqueComodin) //si alguno de los dos bloques intercambiados es comodin
            {
               if(bloque1 instanceof BloqueComodin)
               {
                  if(bloque1y<13 && (tablerotemp[bloque1x][bloque1y+1].getBloque()==tablerotemp[bloque1x][bloque1y+2].getBloque()))  //dos bloques a la derecha
                  {
                      if (tablerotemp[bloque1x][bloque1y+1] instanceof BloqueColor && tablerotemp[bloque1x][bloque1y+2] instanceof BloqueColor)  //comprueba que los 3 sean colores
                      {
                          System.out.println("Se encontraron dos bloques a la derecha del bloque comodin %c que son iguales entre si, ELIMINAR"+bloque1.getBloque());
                          //destruir bloques e implementar habilidad
                      }

                      for(int i = 0; i < 15; i++)
                      {
                          for(int j = 0; j < 15; j++)
                          {
                              System.out.printf("%c\t\n", (tablerotemp[i][j]).getBloque());
                          }
                          System.out.println();
                      }

                      System.out.println("Bloques Restantes: ");
                      System.out.println("Bloques Azules: %d "+contadorazul);
                      System.out.println("Bloques Rojos: %d"+contadorrojo);
                      System.out.println("Bloques Amarillos: %d"+contadoramarillo);
                      System.out.println("Bloques Verdes: %d"+contadorverde);
                      System.out.println("Bloques Naranjos: %d"+contadornaranjo);
                      System.out.println("\n");
                  }

                  else if(bloque1y>1 && tablerotemp[bloque1x][bloque1y-1].getBloque()==tablerotemp[bloque1x][bloque1y-2].getBloque()) //2 bloques a izquierda
                  {
                      if (tablerotemp[bloque1x][bloque1y-1] instanceof BloqueColor && tablerotemp[bloque1x][bloque1y-2] instanceof BloqueColor)
                      {
                          System.out.println("Se encontraron dos bloques a la izquierda del bloque comodin %c que son iguales entre si, ELIMINAR"+bloque1.getBloque());
                          //destruir bloques e implementar habilidad
                      }

                      for(int i = 0; i < 15; i++)
                      {
                          for(int j = 0; j < 15; j++)
                          {
                              System.out.printf("%c\t\n", (tablerotemp[i][j]).getBloque());
                          }
                          System.out.println();
                      }

                      System.out.println("Bloques Restantes: ");
                      System.out.println("Bloques Azules: %d "+contadorazul);
                      System.out.println("Bloques Rojos: %d"+contadorrojo);
                      System.out.println("Bloques Amarillos: %d"+contadoramarillo);
                      System.out.println("Bloques Verdes: %d"+contadorverde);
                      System.out.println("Bloques Naranjos: %d"+contadornaranjo);
                      System.out.println("\n");
                  }

                  else if(bloque1x>1 && tablerotemp[bloque1x-1][bloque1y].getBloque()==tablerotemp[bloque1x-2][bloque1y].getBloque())   //dos bloques arriba
                  {
                      if (tablerotemp[bloque1x-1][bloque1y] instanceof BloqueColor && tablerotemp[bloque1x-2][bloque1y] instanceof BloqueColor)
                      {
                          System.out.println("Se encontraron dos bloques arriba del bloque comodin %c que son iguales entre si, ELIMINAR"+bloque1.getBloque());
                          //destruir bloques e implementar habilidad
                      }

                      for(int i = 0; i < 15; i++)
                      {
                          for(int j = 0; j < 15; j++)
                          {
                              System.out.printf("%c\t\n", (tablerotemp[i][j]).getBloque());
                          }
                          System.out.println();
                      }

                      System.out.println("Bloques Restantes: ");
                      System.out.println("Bloques Azules: %d "+contadorazul);
                      System.out.println("Bloques Rojos: %d"+contadorrojo);
                      System.out.println("Bloques Amarillos: %d"+contadoramarillo);
                      System.out.println("Bloques Verdes: %d"+contadorverde);
                      System.out.println("Bloques Naranjos: %d"+contadornaranjo);
                      System.out.println("\n");
                  }
                  else if(bloque1x<13 && tablerotemp[bloque1x+1][bloque1y].getBloque()==tablerotemp[bloque1x+2][bloque1y].getBloque())  //dos bloques abajo
                  {
                      if (tablerotemp[bloque1x+1][bloque1y] instanceof BloqueColor && tablerotemp[bloque1x+2][bloque1y] instanceof BloqueColor)
                      {
                          System.out.println("Se encontraron dos bloques abajo del bloque comodin %c que son iguales entre si, ELIMINAR"+bloque1.getBloque());
                          //destruir bloques e implementar habilidad
                      }

                      for(int i = 0; i < 15; i++)
                      {
                          for(int j = 0; j < 15; j++)
                          {
                              System.out.printf("%c\t\n", (tablerotemp[i][j]).getBloque());
                          }
                          System.out.println();
                      }

                      System.out.println("Bloques Restantes: ");
                      System.out.println("Bloques Azules: %d "+contadorazul);
                      System.out.println("Bloques Rojos: %d"+contadorrojo);
                      System.out.println("Bloques Amarillos: %d"+contadoramarillo);
                      System.out.println("Bloques Verdes: %d"+contadorverde);
                      System.out.println("Bloques Naranjos: %d"+contadornaranjo);
                      System.out.println("\n");
                  }

                  else if((bloque1x>0 && bloque1x<14) && tablerotemp[bloque1x+1][bloque1y].getBloque()==tablerotemp[bloque1x-1][bloque1y].getBloque())   //uno arriba y otro abajo
                  {
                      if (tablerotemp[bloque1x+1][bloque1y] instanceof BloqueColor && tablerotemp[bloque1x-1][bloque1y] instanceof BloqueColor)
                      {
                          System.out.println("Se encontraron un bloque arriba y otro abajo del bloque comodin %c que son iguales entre si, ELIMINAR"+bloque1.getBloque());
                          //destruir bloques e implementar habilidad
                      }

                      for(int i = 0; i < 15; i++)
                      {
                          for(int j = 0; j < 15; j++)
                          {
                              System.out.printf("%c\t\n", (tablerotemp[i][j]).getBloque());
                          }
                          System.out.println();
                      }

                      System.out.println("Bloques Restantes: ");
                      System.out.println("Bloques Azules: %d "+contadorazul);
                      System.out.println("Bloques Rojos: %d"+contadorrojo);
                      System.out.println("Bloques Amarillos: %d"+contadoramarillo);
                      System.out.println("Bloques Verdes: %d"+contadorverde);
                      System.out.println("Bloques Naranjos: %d"+contadornaranjo);
                      System.out.println("\n");
                  }

                  else if((bloque1y>0 && bloque1y<14) && tablerotemp[bloque1x][bloque1y-1].getBloque()==tablerotemp[bloque1x][bloque1y+1].getBloque())   //uno a la derecha y el otro a la izquierda
                  {
                      if (tablerotemp[bloque1x][bloque1y-1] instanceof BloqueColor && tablerotemp[bloque1x][bloque1y+1] instanceof BloqueColor)
                      {
                          System.out.println("Se encontraron un bloque a la derecha y otro a la izquierda del bloque comodin %c que son iguales entre si, ELIMINAR"+bloque1.getBloque());
                          //destruir bloques e implementar habilidad
                      }

                      for(int i = 0; i < 15; i++)
                      {
                          for(int j = 0; j < 15; j++)
                          {
                              System.out.printf("%c\t\n", (tablerotemp[i][j]).getBloque());
                          }
                          System.out.println();
                      }

                      System.out.println("Bloques Restantes: ");
                      System.out.println("Bloques Azules: %d "+contadorazul);
                      System.out.println("Bloques Rojos: %d"+contadorrojo);
                      System.out.println("Bloques Amarillos: %d"+contadoramarillo);
                      System.out.println("Bloques Verdes: %d"+contadorverde);
                      System.out.println("Bloques Naranjos: %d"+contadornaranjo);
                      System.out.println("\n");
                  }
               }

               else if(bloque2 instanceof BloqueComodin)
               {
                   if(bloque2y<13 && (tablerotemp[bloque2x][bloque2y+1].getBloque()==tablerotemp[bloque2x][bloque2y+2].getBloque()))  //dos bloques a la derecha
                   {
                       if (tablerotemp[bloque2x][bloque2y+1] instanceof BloqueColor && tablerotemp[bloque2x][bloque2y+2] instanceof BloqueColor)  //comprueba que los 3 sean colores
                       {
                           System.out.println("Se encontraron dos bloques a la derecha del bloque comodin %c que son iguales entre si, ELIMINAR"+bloque1.getBloque());
                           //destruir bloques e implementar habilidad
                       }

                       for(int i = 0; i < 15; i++)
                       {
                           for(int j = 0; j < 15; j++)
                           {
                               System.out.printf("%c\t\n", (tablerotemp[i][j]).getBloque());
                           }
                           System.out.println();
                       }

                       System.out.println("Bloques Restantes: ");
                       System.out.println("Bloques Azules: %d "+contadorazul);
                       System.out.println("Bloques Rojos: %d"+contadorrojo);
                       System.out.println("Bloques Amarillos: %d"+contadoramarillo);
                       System.out.println("Bloques Verdes: %d"+contadorverde);
                       System.out.println("Bloques Naranjos: %d"+contadornaranjo);
                       System.out.println("\n");
                   }

                   else if(bloque2y>1 && tablerotemp[bloque2x][bloque2y-1].getBloque()==tablerotemp[bloque2x][bloque2y-2].getBloque()) //2 bloques a izquierda
                   {
                       if (tablerotemp[bloque2x][bloque2y-1] instanceof BloqueColor && tablerotemp[bloque2x][bloque2y-2] instanceof BloqueColor)
                       {
                           System.out.println("Se encontraron dos bloques a la izquierda del bloque comodin %c que son iguales entre si, ELIMINAR"+bloque1.getBloque());
                           //destruir bloques e implementar habilidad
                       }

                       for(int i = 0; i < 15; i++)
                       {
                           for(int j = 0; j < 15; j++)
                           {
                               System.out.printf("%c\t\n", (tablerotemp[i][j]).getBloque());
                           }
                           System.out.println();
                       }

                       System.out.println("Bloques Restantes: ");
                       System.out.println("Bloques Azules: %d "+contadorazul);
                       System.out.println("Bloques Rojos: %d"+contadorrojo);
                       System.out.println("Bloques Amarillos: %d"+contadoramarillo);
                       System.out.println("Bloques Verdes: %d"+contadorverde);
                       System.out.println("Bloques Naranjos: %d"+contadornaranjo);
                       System.out.println("\n");
                   }

                   else if(bloque2x>1 && tablerotemp[bloque2x-1][bloque2y].getBloque()==tablerotemp[bloque2x-2][bloque2y].getBloque())   //dos bloques arriba
                   {
                       if (tablerotemp[bloque2x-1][bloque2y] instanceof BloqueColor && tablerotemp[bloque2x-2][bloque2y] instanceof BloqueColor)
                       {
                           System.out.println("Se encontraron dos bloques arriba del bloque comodin %c que son iguales entre si, ELIMINAR"+bloque1.getBloque());
                           //destruir bloques e implementar habilidad
                       }

                       for(int i = 0; i < 15; i++)
                       {
                           for(int j = 0; j < 15; j++)
                           {
                               System.out.printf("%c\t\n", (tablerotemp[i][j]).getBloque());
                           }
                           System.out.println();
                       }

                       System.out.println("Bloques Restantes: ");
                       System.out.println("Bloques Azules: %d "+contadorazul);
                       System.out.println("Bloques Rojos: %d"+contadorrojo);
                       System.out.println("Bloques Amarillos: %d"+contadoramarillo);
                       System.out.println("Bloques Verdes: %d"+contadorverde);
                       System.out.println("Bloques Naranjos: %d"+contadornaranjo);
                       System.out.println("\n");
                   }
                   else if(bloque2x<13 && tablerotemp[bloque2x+1][bloque2y].getBloque()==tablerotemp[bloque2x+2][bloque2y].getBloque())  //dos bloques abajo
                   {
                       if (tablerotemp[bloque2x+1][bloque2y] instanceof BloqueColor && tablerotemp[bloque2x+2][bloque2y] instanceof BloqueColor)
                       {
                           System.out.println("Se encontraron dos bloques abajo del bloque comodin %c que son iguales entre si, ELIMINAR"+bloque1.getBloque());
                           //destruir bloques e implementar habilidad
                       }

                       for(int i = 0; i < 15; i++)
                       {
                           for(int j = 0; j < 15; j++)
                           {
                               System.out.printf("%c\t\n", (tablerotemp[i][j]).getBloque());
                           }
                           System.out.println();
                       }

                       System.out.println("Bloques Restantes: ");
                       System.out.println("Bloques Azules: %d "+contadorazul);
                       System.out.println("Bloques Rojos: %d"+contadorrojo);
                       System.out.println("Bloques Amarillos: %d"+contadoramarillo);
                       System.out.println("Bloques Verdes: %d"+contadorverde);
                       System.out.println("Bloques Naranjos: %d"+contadornaranjo);
                       System.out.println("\n");
                   }

                   else if((bloque2x>0 && bloque2x<14) && tablerotemp[bloque2x+1][bloque2y].getBloque()==tablerotemp[bloque2x-1][bloque2y].getBloque())   //uno arriba y otro abajo
                   {
                       if (tablerotemp[bloque2x+1][bloque2y] instanceof BloqueColor && tablerotemp[bloque2x-1][bloque2y] instanceof BloqueColor)
                       {
                           System.out.println("Se encontraron un bloque arriba y otro abajo del bloque comodin %c que son iguales entre si, ELIMINAR"+bloque1.getBloque());
                           //destruir bloques e implementar habilidad
                       }

                       for(int i = 0; i < 15; i++)
                       {
                           for(int j = 0; j < 15; j++)
                           {
                               System.out.printf("%c\t\n", (tablerotemp[i][j]).getBloque());
                           }
                           System.out.println();
                       }

                       System.out.println("Bloques Restantes: ");
                       System.out.println("Bloques Azules: %d "+contadorazul);
                       System.out.println("Bloques Rojos: %d"+contadorrojo);
                       System.out.println("Bloques Amarillos: %d"+contadoramarillo);
                       System.out.println("Bloques Verdes: %d"+contadorverde);
                       System.out.println("Bloques Naranjos: %d"+contadornaranjo);
                       System.out.println("\n");
                   }

                   else if((bloque2y>0 && bloque2y<14) && tablerotemp[bloque2x][bloque2y-1].getBloque()==tablerotemp[bloque2x][bloque2y+1].getBloque())   //uno a la derecha y el otro a la izquierda
                   {
                       if (tablerotemp[bloque2x][bloque2y-1] instanceof BloqueColor && tablerotemp[bloque2x][bloque2y+1] instanceof BloqueColor)
                       {
                           System.out.println("Se encontraron un bloque a la derecha y otro a la izquierda del bloque comodin %c que son iguales entre si, ELIMINAR"+bloque1.getBloque());
                           //destruir bloques e implementar habilidad
                       }

                       for(int i = 0; i < 15; i++)
                       {
                           for(int j = 0; j < 15; j++)
                           {
                               System.out.printf("%c\t\n", (tablerotemp[i][j]).getBloque());
                           }
                           System.out.println();
                       }

                       System.out.println("Bloques Restantes: ");
                       System.out.println("Bloques Azules: %d "+contadorazul);
                       System.out.println("Bloques Rojos: %d"+contadorrojo);
                       System.out.println("Bloques Amarillos: %d"+contadoramarillo);
                       System.out.println("Bloques Verdes: %d"+contadorverde);
                       System.out.println("Bloques Naranjos: %d"+contadornaranjo);
                       System.out.println("\n");
                   }
               }
            }

            else  //de no serlo, alguno de los aledaños puede serlo
            {
                System.out.println("Ninguno de los dos bloques intercambiados es comodin, verificando que los aledaños a el lo sean...");

                //bloque 1
                if(bloque1y<13 && (((tablerotemp[bloque1x][bloque1y+1].getBloque()==bloque1.getBloque()) && tablerotemp[bloque1x][bloque1y+2] instanceof BloqueComodin) || ((tablerotemp[bloque1x][bloque1y+2].getBloque()==bloque1.getBloque()) && tablerotemp[bloque1x][bloque1y+1] instanceof BloqueComodin))) // dos bloques a su derecha
                {
                    System.out.println("Se encontro un bloque comodin a la derecha del bloque %c, ELIMINAR"+bloque1.getBloque());
                    //destruir bloques e implementar habilidad

                    for(int i = 0; i < 15; i++)
                    {
                        for(int j = 0; j < 15; j++)
                        {
                            System.out.printf("%c\t\n", (tablerotemp[i][j]).getBloque());
                        }
                        System.out.println();
                    }

                    System.out.println("Bloques Restantes: ");
                    System.out.println("Bloques Azules: %d "+contadorazul);
                    System.out.println("Bloques Rojos: %d"+contadorrojo);
                    System.out.println("Bloques Amarillos: %d"+contadoramarillo);
                    System.out.println("Bloques Verdes: %d"+contadorverde);
                    System.out.println("Bloques Naranjos: %d"+contadornaranjo);
                    System.out.println("\n");

                }

                else if(bloque1y>1 && (((tablerotemp[bloque1x][bloque1y-1].getBloque()==bloque1.getBloque()) && tablerotemp[bloque1x][bloque1y-2] instanceof BloqueComodin) || ((tablerotemp[bloque1x][bloque1y-2].getBloque()==bloque1.getBloque()) && tablerotemp[bloque1x][bloque1y-1] instanceof BloqueComodin)))  // dos bloques a su izquierda
                {
                    System.out.println("Se encontro un bloque comodin a la izquierda del bloque %c, ELIMINAR"+bloque1.getBloque());
                    //destruir bloques e implementar habilidad

                    for(int i = 0; i < 15; i++)
                    {
                        for(int j = 0; j < 15; j++)
                        {
                            System.out.printf("%c\t\n", (tablerotemp[i][j]).getBloque());
                        }
                        System.out.println();
                    }

                    System.out.println("Bloques Restantes: ");
                    System.out.println("Bloques Azules: %d "+contadorazul);
                    System.out.println("Bloques Rojos: %d"+contadorrojo);
                    System.out.println("Bloques Amarillos: %d"+contadoramarillo);
                    System.out.println("Bloques Verdes: %d"+contadorverde);
                    System.out.println("Bloques Naranjos: %d"+contadornaranjo);
                    System.out.println("\n");
                }

                else if(bloque1x>1 && (((tablerotemp[bloque1x-1][bloque1y].getBloque()==bloque1.getBloque()) && tablerotemp[bloque1x-2][bloque1y] instanceof BloqueComodin) || ((tablerotemp[bloque1x-2][bloque1y].getBloque()==bloque1.getBloque()) && tablerotemp[bloque1x-1][bloque1y] instanceof BloqueComodin)))  //  dos bloques arriba
                {
                    System.out.println("Se encontro un bloque comodin arriba del bloque %c, ELIMINAR"+bloque1.getBloque());
                    //destruir bloques e implementar habilidad

                    for(int i = 0; i < 15; i++)
                    {
                        for(int j = 0; j < 15; j++)
                        {
                            System.out.printf("%c\t\n", (tablerotemp[i][j]).getBloque());
                        }
                        System.out.println();
                    }

                    System.out.println("Bloques Restantes: ");
                    System.out.println("Bloques Azules: %d "+contadorazul);
                    System.out.println("Bloques Rojos: %d"+contadorrojo);
                    System.out.println("Bloques Amarillos: %d"+contadoramarillo);
                    System.out.println("Bloques Verdes: %d"+contadorverde);
                    System.out.println("Bloques Naranjos: %d"+contadornaranjo);
                    System.out.println("\n");
                }

                else if(bloque1x<13 && (((tablerotemp[bloque1x+1][bloque1y].getBloque()==bloque1.getBloque()) && tablerotemp[bloque1x+2][bloque1y] instanceof BloqueComodin) || ((tablerotemp[bloque1x+2][bloque1y].getBloque()==bloque1.getBloque()) && tablerotemp[bloque1x+1][bloque1y] instanceof BloqueComodin))) // dos bloques abajo
                {
                    System.out.println("Se encontro un bloque comodin abajo del bloque %c, ELIMINAR"+bloque1.getBloque());
                    //destruir bloques e implementar habilidad

                    for(int i = 0; i < 15; i++)
                    {
                        for(int j = 0; j < 15; j++)
                        {
                            System.out.printf("%c\t\n", (tablerotemp[i][j]).getBloque());
                        }
                        System.out.println();
                    }

                    System.out.println("Bloques Restantes: ");
                    System.out.println("Bloques Azules: %d "+contadorazul);
                    System.out.println("Bloques Rojos: %d"+contadorrojo);
                    System.out.println("Bloques Amarillos: %d"+contadoramarillo);
                    System.out.println("Bloques Verdes: %d"+contadorverde);
                    System.out.println("Bloques Naranjos: %d"+contadornaranjo);
                    System.out.println("\n");
                }

                else if((bloque1x>0 && bloque1x<14) && (((tablerotemp[bloque1x+1][bloque1y].getBloque()==bloque1.getBloque()) && tablerotemp[bloque1x-1][bloque1y] instanceof BloqueComodin) || ((tablerotemp[bloque1x-1][bloque1y].getBloque()==bloque1.getBloque()) && tablerotemp[bloque1x+1][bloque1y] instanceof BloqueComodin))) // un bloque arriba y otro abajo
                {
                    if((tablerotemp[bloque1x+1][bloque1y].getBloque()==bloque1.getBloque()) && tablerotemp[bloque1x-1][bloque1y] instanceof BloqueComodin) //si el comodin esta arriba
                    {
                        System.out.println("Se encontro un bloque comodin arriba del bloque %c, ELIMINAR"+bloque1.getBloque());
                        //destruir bloques e implementar habilidad
                    }

                    else if ((tablerotemp[bloque1x-1][bloque1y].getBloque()==bloque1.getBloque()) && tablerotemp[bloque1x+1][bloque1y] instanceof BloqueComodin)   //si el comodin esta abajo
                    {
                        System.out.println("Se encontro un bloque comodin abajo del bloque %c, ELIMINAR"+bloque1.getBloque());
                        //destruir bloques e implementar habilidad
                    }


                    for(int i = 0; i < 15; i++)
                    {
                        for(int j = 0; j < 15; j++)
                        {
                            System.out.printf("%c\t\n", (tablerotemp[i][j]).getBloque());
                        }
                        System.out.println();
                    }

                    System.out.println("Bloques Restantes: ");
                    System.out.println("Bloques Azules: %d "+contadorazul);
                    System.out.println("Bloques Rojos: %d"+contadorrojo);
                    System.out.println("Bloques Amarillos: %d"+contadoramarillo);
                    System.out.println("Bloques Verdes: %d"+contadorverde);
                    System.out.println("Bloques Naranjos: %d"+contadornaranjo);
                    System.out.println("\n");
                }

                else if((bloque1y>0 && bloque1y<14)  && (((tablerotemp[bloque1x][bloque1y-1].getBloque()==bloque1.getBloque()) && tablerotemp[bloque1x][bloque1y+1] instanceof BloqueComodin) || ((tablerotemp[bloque1x][bloque1y+1].getBloque()==bloque1.getBloque()) && tablerotemp[bloque1x][bloque1y-1] instanceof BloqueComodin))) //un bloque a la derecha y otro a la izquierda
                {
                    if((tablerotemp[bloque1x][bloque1y-1].getBloque()==bloque1.getBloque()) && tablerotemp[bloque1x][bloque1y+1] instanceof BloqueComodin) //si el comodin esta a la derecha
                    {
                        System.out.println("Se encontro un bloque comodin a la derecha del bloque %c, ELIMINAR"+bloque1.getBloque());
                        //destruir bloques e implementar habilidad
                    }

                    else if ((tablerotemp[bloque1x][bloque1y+1].getBloque()==bloque1.getBloque()) && tablerotemp[bloque1x][bloque1y-1] instanceof BloqueComodin)   //si el comodin esta a la izquierda
                    {
                        System.out.println("Se encontro un bloque comodin a la izquierda del bloque %c, ELIMINAR"+bloque1.getBloque());
                        //destruir bloques e implementar habilidad
                    }


                    for(int i = 0; i < 15; i++)
                    {
                        for(int j = 0; j < 15; j++)
                        {
                            System.out.printf("%c\t\n", (tablerotemp[i][j]).getBloque());
                        }
                        System.out.println();
                    }

                    System.out.println("Bloques Restantes: ");
                    System.out.println("Bloques Azules: %d "+contadorazul);
                    System.out.println("Bloques Rojos: %d"+contadorrojo);
                    System.out.println("Bloques Amarillos: %d"+contadoramarillo);
                    System.out.println("Bloques Verdes: %d"+contadorverde);
                    System.out.println("Bloques Naranjos: %d"+contadornaranjo);
                    System.out.println("\n");
                }

                //bloque 2
                if(bloque2y<13 && (((tablerotemp[bloque2x][bloque2y+1].getBloque()==bloque2.getBloque()) && tablerotemp[bloque2x][bloque2y+2] instanceof BloqueComodin) || ((tablerotemp[bloque2x][bloque2y+2].getBloque()==bloque2.getBloque()) && tablerotemp[bloque2x][bloque2y+1] instanceof BloqueComodin))) // dos bloques a su derecha
                {
                    System.out.println("Se encontro un bloque comodin a la derecha del bloque %c, ELIMINAR"+bloque2.getBloque());
                    //destruir bloques e implementar habilidad

                    for(int i = 0; i < 15; i++)
                    {
                        for(int j = 0; j < 15; j++)
                        {
                            System.out.printf("%c\t\n", (tablerotemp[i][j]).getBloque());
                        }
                        System.out.println();
                    }

                    System.out.println("Bloques Restantes: ");
                    System.out.println("Bloques Azules: %d "+contadorazul);
                    System.out.println("Bloques Rojos: %d"+contadorrojo);
                    System.out.println("Bloques Amarillos: %d"+contadoramarillo);
                    System.out.println("Bloques Verdes: %d"+contadorverde);
                    System.out.println("Bloques Naranjos: %d"+contadornaranjo);
                    System.out.println("\n");

                }

                else if(bloque2y>1 && (((tablerotemp[bloque2x][bloque2y-1].getBloque()==bloque2.getBloque()) && tablerotemp[bloque2x][bloque2y-2] instanceof BloqueComodin) || ((tablerotemp[bloque2x][bloque2y-2].getBloque()==bloque2.getBloque()) && tablerotemp[bloque2x][bloque2y-1] instanceof BloqueComodin)))  // dos bloques a su izquierda
                {
                    System.out.println("Se encontro un bloque comodin a la izquierda del bloque %c, ELIMINAR"+bloque2.getBloque());
                    //destruir bloques e implementar habilidad

                    for(int i = 0; i < 15; i++)
                    {
                        for(int j = 0; j < 15; j++)
                        {
                            System.out.printf("%c\t\n", (tablerotemp[i][j]).getBloque());
                        }
                        System.out.println();
                    }

                    System.out.println("Bloques Restantes: ");
                    System.out.println("Bloques Azules: %d "+contadorazul);
                    System.out.println("Bloques Rojos: %d"+contadorrojo);
                    System.out.println("Bloques Amarillos: %d"+contadoramarillo);
                    System.out.println("Bloques Verdes: %d"+contadorverde);
                    System.out.println("Bloques Naranjos: %d"+contadornaranjo);
                    System.out.println("\n");
                }

                else if(bloque2x>1 && (((tablerotemp[bloque2x-1][bloque2y].getBloque()==bloque2.getBloque()) && tablerotemp[bloque2x-2][bloque2y] instanceof BloqueComodin) || ((tablerotemp[bloque2x-2][bloque2y].getBloque()==bloque2.getBloque()) && tablerotemp[bloque2x-1][bloque2y] instanceof BloqueComodin)))  //  dos bloques arriba
                {
                    System.out.println("Se encontro un bloque comodin arriba del bloque %c, ELIMINAR"+bloque2.getBloque());
                    //destruir bloques e implementar habilidad

                    for(int i = 0; i < 15; i++)
                    {
                        for(int j = 0; j < 15; j++)
                        {
                            System.out.printf("%c\t\n", (tablerotemp[i][j]).getBloque());
                        }
                        System.out.println();
                    }

                    System.out.println("Bloques Restantes: ");
                    System.out.println("Bloques Azules: %d "+contadorazul);
                    System.out.println("Bloques Rojos: %d"+contadorrojo);
                    System.out.println("Bloques Amarillos: %d"+contadoramarillo);
                    System.out.println("Bloques Verdes: %d"+contadorverde);
                    System.out.println("Bloques Naranjos: %d"+contadornaranjo);
                    System.out.println("\n");
                }

                else if(bloque2x<13 && (((tablerotemp[bloque2x+1][bloque2y].getBloque()==bloque2.getBloque()) && tablerotemp[bloque2x+2][bloque2y] instanceof BloqueComodin) || ((tablerotemp[bloque2x+2][bloque2y].getBloque()==bloque2.getBloque()) && tablerotemp[bloque2x+1][bloque2y] instanceof BloqueComodin))) // dos bloques abajo
                {
                    System.out.println("Se encontro un bloque comodin abajo del bloque %c, ELIMINAR"+bloque2.getBloque());
                    //destruir bloques e implementar habilidad

                    for(int i = 0; i < 15; i++)
                    {
                        for(int j = 0; j < 15; j++)
                        {
                            System.out.printf("%c\t\n", (tablerotemp[i][j]).getBloque());
                        }
                        System.out.println();
                    }

                    System.out.println("Bloques Restantes: ");
                    System.out.println("Bloques Azules: %d "+contadorazul);
                    System.out.println("Bloques Rojos: %d"+contadorrojo);
                    System.out.println("Bloques Amarillos: %d"+contadoramarillo);
                    System.out.println("Bloques Verdes: %d"+contadorverde);
                    System.out.println("Bloques Naranjos: %d"+contadornaranjo);
                    System.out.println("\n");
                }

                else if((bloque2x>0 && bloque2x<14) && (((tablerotemp[bloque2x+1][bloque2y].getBloque()==bloque2.getBloque()) && tablerotemp[bloque2x-1][bloque2y] instanceof BloqueComodin) || ((tablerotemp[bloque2x-1][bloque2y].getBloque()==bloque2.getBloque()) && tablerotemp[bloque2x+1][bloque2y] instanceof BloqueComodin))) // un bloque arriba y otro abajo
                {
                    if((tablerotemp[bloque2x+1][bloque2y].getBloque()==bloque2.getBloque()) && tablerotemp[bloque2x-1][bloque2y] instanceof BloqueComodin) //si el comodin esta arriba
                    {
                        System.out.println("Se encontro un bloque comodin arriba del bloque %c, ELIMINAR"+bloque2.getBloque());
                        //destruir bloques e implementar habilidad
                    }

                    else if ((tablerotemp[bloque2x-1][bloque2y].getBloque()==bloque2.getBloque()) && tablerotemp[bloque2x+1][bloque2y] instanceof BloqueComodin)   //si el comodin esta abajo
                    {
                        System.out.println("Se encontro un bloque comodin abajo del bloque %c, ELIMINAR"+bloque2.getBloque());
                        //destruir bloques e implementar habilidad
                    }


                    for(int i = 0; i < 15; i++)
                    {
                        for(int j = 0; j < 15; j++)
                        {
                            System.out.printf("%c\t\n", (tablerotemp[i][j]).getBloque());
                        }
                        System.out.println();
                    }

                    System.out.println("Bloques Restantes: ");
                    System.out.println("Bloques Azules: %d "+contadorazul);
                    System.out.println("Bloques Rojos: %d"+contadorrojo);
                    System.out.println("Bloques Amarillos: %d"+contadoramarillo);
                    System.out.println("Bloques Verdes: %d"+contadorverde);
                    System.out.println("Bloques Naranjos: %d"+contadornaranjo);
                    System.out.println("\n");
                }

                else if((bloque2y>0 && bloque2y<14)  && (((tablerotemp[bloque2x][bloque2y-1].getBloque()==bloque2.getBloque()) && tablerotemp[bloque2x][bloque2y+1] instanceof BloqueComodin) || ((tablerotemp[bloque2x][bloque2y+1].getBloque()==bloque2.getBloque()) && tablerotemp[bloque2x][bloque2y-1] instanceof BloqueComodin))) //un bloque a la derecha y otro a la izquierda
                {
                    if((tablerotemp[bloque2x][bloque2y-1].getBloque()==bloque2.getBloque()) && tablerotemp[bloque2x][bloque2y+1] instanceof BloqueComodin) //si el comodin esta a la derecha
                    {
                        System.out.println("Se encontro un bloque comodin a la derecha del bloque %c, ELIMINAR"+bloque2.getBloque());
                        //destruir bloques e implementar habilidad
                    }

                    else if ((tablerotemp[bloque2x][bloque2y+1].getBloque()==bloque2.getBloque()) && tablerotemp[bloque2x][bloque2y-1] instanceof BloqueComodin)   //si el comodin esta a la izquierda
                    {
                        System.out.println("Se encontro un bloque comodin a la izquierda del bloque %c, ELIMINAR"+bloque2.getBloque());
                        //destruir bloques e implementar habilidad
                    }


                    for(int i = 0; i < 15; i++)
                    {
                        for(int j = 0; j < 15; j++)
                        {
                            System.out.printf("%c\t\n", (tablerotemp[i][j]).getBloque());
                        }
                        System.out.println();
                    }

                    System.out.println("Bloques Restantes: ");
                    System.out.println("Bloques Azules: %d "+contadorazul);
                    System.out.println("Bloques Rojos: %d"+contadorrojo);
                    System.out.println("Bloques Amarillos: %d"+contadoramarillo);
                    System.out.println("Bloques Verdes: %d"+contadorverde);
                    System.out.println("Bloques Naranjos: %d"+contadornaranjo);
                    System.out.println("\n");
                }
            }

            //Se llenan los espacios "vacios" (con X) con bloques random
            for(int i=0; i<15; i++)
            {
                for(int j=0; j<15;j++)
                {
                    int  n = num.nextInt(101); //genera num entre 1 y 100
                    if((tablerotemp[i][j].getBloque())=='X')
                    {
                        if(n<=95)  //color = 95% posibilidades
                        {
                            tablerotemp[i][j]= col.crearBloque();
                        }

                        else if(n>95)   //comodin = 5% posibilidades
                        {
                            tablerotemp[i][j]= com.crearBloque();
                        }
                    }

                }
            }

            //Almaceno todo lo de tablerotemp en el tablero definitivo
            for(int i = 0; i < 15; i++)
            {
                for(int j = 0; j < 15; j++)
                {
                    tablero[i][j]=tablerotemp[i][j];
                }
            }

            //Se imprime el tablero al finalizar una jugada
            for(int i = 0; i < 15; i++)
            {
                for(int j = 0; j < 15; j++)
                {
                    System.out.printf("%c\t", (tablero[i][j]).getBloque());
                }
                System.out.println();
            }
            System.out.println("\n");
        }
        System.out.println("El juego ha finalizado!");
        System.out.println("Bloques Restantes: ");
        System.out.println("Bloques Azules: %d "+contadorazul);
        System.out.println("Bloques Rojos: %d"+contadorrojo);
        System.out.println("Bloques Amarillos: %d"+contadoramarillo);
        System.out.println("Bloques Verdes: %d"+contadorverde);
        System.out.println("Bloques Naranjos: %d"+contadornaranjo);
        System.out.println("\n");


    }
}
