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
    Bloque block = new Bloque();
    Character col;

    public void crearBloque()
    {
       Random col = new Random(); //Para crear colores aleatorios
       String colores = "RBOGY";
       block.setBloque(colores.charAt(col.nextInt(colores.length())));
    }

    public char getColorCreado(){
        return col;
    }

    public void setColorCreado(Character col) {
        this.col=col;
    }
}
