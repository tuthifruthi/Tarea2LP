/**
 * Created with IntelliJ IDEA.
 * User: Tuthifruthi
 * Date: 27-04-14
 * Time: 12:21
 * To change this template use File | Settings | File Templates.
 */
import java.util.Random;

public class ColorCreator extends AbstractCreator
{
    Character col;

    public Bloque crearBloque()
    {
       BloqueColor block=new BloqueColor();
       Random num=new Random();
       int  n = num.nextInt(5) + 1;

       if(n==1)
       {
           col='R';
       }
       else if(n==2)
       {
           col='B';
       }
        else if(n==3)
       {
           col='O';
       }
        else if(n==4)
       {
           col='G';
       }
        else if(n==5)
       {
           col='Y';
       }
        block.bloque=col;
        return (Bloque) block;
    }

    public char getColorCreado(){
        return this.col;
    }

    public void setColorCreado(Character col) {
        this.col=col;
    }
}
