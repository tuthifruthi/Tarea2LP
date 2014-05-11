import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Tuthifruthi
 * Date: 27-04-14
 * Time: 12:21
 * To change this template use File | Settings | File Templates.
 */
public class ComodinCreator extends AbstractCreator
{
    Character com;

        public Bloque crearBloque()
        {
            BloqueComodin comodin=new BloqueComodin();
            Random num = new Random();
            int  n = num.nextInt(2) + 1;

            if(n==1)
            {
                com='&';
            }
            else if(n==2)
            {
                com='$';
            }

            comodin.tipo=com;
            return (Bloque) comodin;
        }

        public char getComodinCreado(){
            return this.com;
        }

        public void setComodinCreado(Character com) {
            this.com=com;
        }
}
