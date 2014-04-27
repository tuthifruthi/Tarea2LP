/**
 * Created with IntelliJ IDEA.
 * User: Tuthifruthi
 * Date: 27-04-14
 * Time: 12:23
 * To change this template use File | Settings | File Templates.
 */
import java.util.Scanner;

public class Main {
    public static void main(String[] args)
    {
        Integer rojo;
        Integer azul;
        Integer naranjo;
        Integer verde;
        Integer amarillo;
        Character[][] tablero = new Character[15][15];

        System.out.println("Presione la tecla ENTER para iniciar el juego\n");
        Scanner keyIn = new Scanner(System.in);
        keyIn.nextLine();

        System.out.println("Indique cuántos bloques romper de cada color para finalizar:\n");

        System.out.println("Color Rojo:");
        Scanner scan1 = new Scanner(System.in);
        rojo = scan1.nextInt();
        scan1.close();

        System.out.println("Color Azul:");
        Scanner scan2 = new Scanner(System.in);
        azul = scan2.nextInt();
        scan2.close();

        System.out.println("Color Naranjo:");
        Scanner scan3 = new Scanner(System.in);
        naranjo = scan3.nextInt();
        scan3.close();

        System.out.println("Color Verde:");
        Scanner scan4 = new Scanner(System.in);
        verde = scan4.nextInt();
        scan4.close();

        System.out.println("Color Amarillo:");
        Scanner scan5 = new Scanner(System.in);
        amarillo = scan5.nextInt();
        scan5.close();

        //Llenar tablero
        BloqueColor col = new BloqueColor();
        BloqueComodin com = new BloqueComodin();

        for(int i=0; i<15; i++)
        {
            for(int j=0; j<15; j++)
            {

            }
        }

    }
}
