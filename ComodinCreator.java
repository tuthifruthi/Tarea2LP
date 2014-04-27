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
    Bloque block = new Bloque();
    Character com;

        public void crearBloque()
        {
            Random com = new Random();
            String comodin = "&$";
            block.setBloque(comodin.charAt(com.nextInt(comodin.length())));
        }

        public char getComodinCreado(){
            return com;
        }

        public void setComodinCreado(Character com) {
            this.com=com;
        }
}
