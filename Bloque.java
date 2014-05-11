/**
 * Created with IntelliJ IDEA.
 * User: Tuthifruthi
 * Date: 27-04-14
 * Time: 12:20
 * To change this template use File | Settings | File Templates.
 */
abstract class Bloque
{
    Character bloque;

    abstract void DestruirBloque(Bloque bloque);

    public char getBloque(){
        return bloque;
    }

    public void setBloque(Character bloque) {
        this.bloque=bloque;
    }
}
