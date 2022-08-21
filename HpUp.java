/**
 * HpUp is a subclass of PokemonDecorator
 * @author Darren Seng
 *
 */
public class HpUp extends PokemonDecorator{
  /** Constructor for HpUp class
   *  @param p: Pokemon object
   */
  public HpUp(Pokemon p){
    super(p, "+HP", 1);
  }
}