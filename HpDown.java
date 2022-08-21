/**
 * HpDown is a subclass of PokemonDecorator
 * @author Darren Seng
 *
 */
public class HpDown extends PokemonDecorator{
  /** Constructor for HpDown class
   *  @param p: Pokemon object
   */
  public HpDown(Pokemon p){
    super(p, "-HP", -1);
  }
}