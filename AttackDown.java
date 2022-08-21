/**
 * AttackDown is a subclass of PokemonDecorator
 * @author Darren Seng
 *
 */
public class AttackDown extends PokemonDecorator{
  /** Constructor for AttackDown class
   *  @param p: Pokemon object
   */
public AttackDown(Pokemon p) {
	super(p, "-ATK",0);
	
}
/** getAttackBonus returns the adjusted attack bonus when the AttackDown debuff is applied
   *  @param type: type of attack, basic or special
   *  @return: returns the attack bonus that will affect the total damage 
   */
@Override
public int getAttackBonus(int type) {
	return super.getAttackBonus(type) - 1;
}
}
