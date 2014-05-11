import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Tuthifruthi
 * Date: 27-04-14
 * Time: 12:21
 * To change this template use File | Settings | File Templates.
 */
public class BloqueComodin extends Bloque
{
    Character tipo;

    public void DestruirBloque(Bloque block)
    {
        block.bloque='X';

    }

   /* public HabilityBehavior Habilidad(Bloque block)
    {
        if(block.bloque=='$')    //comodin tipo 1
        {
           HabilityT1 hab1=new HabilityT1();
           hab1.Hability();
           return (HabilityBehavior) hab1;
        }

        else if(block.bloque=='&')  //comodin tipo 2
        {
            HabilityT2 hab2=new HabilityT2();
            hab2.Hability();
            return (HabilityBehavior) hab2;
        }
    }  */

    public char getBloqueComodin(){
        return tipo;
    }

    public void setBloqueComodin(Character type) {
        this.tipo=type;
    }
}
