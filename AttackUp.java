import java.util.Random;
/**
 * AttackUp is a subclass of PokemonDecorator
 * @author Darren Seng
 *
 */
public class AttackUp extends PokemonDecorator{
/** Constructor for AttackUp class
   *  @param p: Pokemon object
   */
public AttackUp(Pokemon p) {
	super(p, "+ATK",0);
	
}
/** getAttackBonus returns the adjusted attack bonus when the AttackUp buff is applied
   *  @param type: type of attack, basic or special
   *  @return: returns the attack bonus that will affect the total damage 
   */
@Override
public int getAttackBonus(int type) {
	Random rand = new Random();
	int damageAmt = rand.nextInt(2) + 1;
	return damageAmt + super.getAttackBonus(type);
}
}
