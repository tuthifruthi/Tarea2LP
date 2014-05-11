import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Tuthifruthi
 * Date: 27-04-14
 * Time: 12:21
 * To change this template use File | Settings | File Templates.
 */
public class BloqueComodin  extends Bloque
{
    Character tipo;

    public void DestruirBloque(Bloque block)
    {
        block=null;
    }

    public char getBloqueComodin(){
        return tipo;
    }

    public void setBloqueComodin(Character type) {
        this.tipo=type;
    }
}
