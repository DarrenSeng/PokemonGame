/**
 * The Water class is a subclass of the Pokemon class.
 * @author Darren Seng
 *
 */
public class Water extends Pokemon {
  /** Constructor for Water.
   * 
   * @param n: Name 
   * @param h: Hp
   * @param m: Max HP
   *
   */
	public Water(String n, int h, int m) {
		super(n,h,m);
	}
  /** getAttackMenu returns the menu listing the options of the pokemon's moves
   *  @param atkType: Type of attack, basic or special.
   *  @return: returns the menu depending on attack type
   */
	@Override
	public String getAttackMenu(int atkType) {
		if (atkType == 1) {
			  return super.getAttackMenu(atkType);
		  }
		  else {
		return "1. Water Gun\n2. Bubble Beam\n3. Waterfall";
		  }
	}
  /** getNumAttackMenuItems returns the number of moves in the menu
   *  @param atkType: Type of attack, basic or special.
   *  @return: returns the number of attacks in the menu depending on attack type
   */
	@Override
	public int getNumAttackMenuItems(int atkType){
		  return 3;
	  }
  /** getAttackString returns the attack string of a move
   *  @param atkType: Type of attack, basic or special.
   * @param move: The move selected 
   *  @return: returns the string for whatever attack is performed
   */
	@Override
	public String getAttackString(int atkType, int move) {
		String atkString = null;
		if (atkType == 1) {
			atkString = super.getAttackString(atkType, move);
		}
		else if (atkType == 2) {
			switch(move) {
				case 1:
					atkString = "shot with Water Gun";
					break;
				case 2:
					atkString = "hit by a Bubble Beam";
					break;
				case 3:
					atkString = "showered in a Waterfall";
					break;
			}
					
		}
		return atkString;
	}
  /** getAttackDamage returns the damage of the attack depending on the move and attack type
   *  @param atkType: Type of attack, basic or special.
   * @param move: Type of move selected
   *  @return: returns the damage value of the specific move
   */
	@Override
	public int getAttackDamage(int atkType, int move) {
		int atkDamage = 0;
		if (atkType == 1) {
			atkDamage = super.getAttackDamage(atkType, move);
		}
		else if (atkType == 2) {
			switch(move) {
				case 1:
					atkDamage = (rand.nextInt(3) + 2);
					break;
				case 2:
					atkDamage = (rand.nextInt(2) + 1);
					break;
				case 3:
					atkDamage = (rand.nextInt(3) + 1);
					break;
			}
					
		}
		return atkDamage;
	}
  /** getAttackMultiplier returns the multiplier of the attack's damage 
  * @param move: opposing pokemon
   *  @param atkType: Type of attack, basic or special.
   *  @return: returns the multiplier depending on the attack type
   */
	@Override
	public double getAttackMultiplier(Pokemon p, int atkType) {
		double atkMult = 0;
		if (atkType == 1) {
			atkMult = super.getAttackMultiplier(p, atkType);
		}
		else if (atkType == 2) {
			atkMult = battleTable[1][p.getType()];
		}
		return atkMult;
	}
  
}