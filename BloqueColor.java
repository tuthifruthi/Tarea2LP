/**
 * Created with IntelliJ IDEA.
 * User: Tuthifruthi
 * Date: 27-04-14
 * Time: 12:21
 * To change this template use File | Settings | File Templates.
 */
public class BloqueColor extends Bloque
{
    Character color;

    public void DestruirBloque(Bloque block)
    {
        block.bloque='X';
    }

    public char getColor(){
        return this.color;
    }

    public void setColor(Character col) {
        this.color=col;
    }
}
