/**
 * The Grass class is a subclass of the Pokemon class.
 * @author Darren Seng
 *
 */
public class Grass extends Pokemon{
  
  /** Constructor for Grass.
   * 
   * @param n: Name 
   * @param h: Hp
   * @param m: Max HP
   *
   */
  Grass(String n, int h, int m){
    super(n, h, m);    
  }

  /** This method returns the menu listing the options of the pokemon's moves
   *  @param atkType: Type of attack (basic or special).
   *  @return: returns the menu listing.
   */
  @Override
  public String getAttackMenu(int atkType){
	  if (atkType == 1) {
		  return super.getAttackMenu(atkType);
	  }
	  else {
		  return "1.Vine Whip\n2.Razor Leaf\n3.Solar Beam";
	  }
  }

  /** This method returns the number of moves in the menu.
   *  @param atkType: Type of attack (basic or special).
   *  @return: the number of attack moves in the menu.
   */
  @Override
  public int getNumAttackMenuItems(int atkType){
    return 3;
  }

  /** This method returns the partial string for the chosen move.
   *  @param atkType: Type of attack (basic or special)
   *  @param move: move the pokemon will perform.
   *  @return: partial attack string.
   */
  @Override
  public String getAttackString(int atkType, int move){
    String atkString = null;
		if (atkType == 1){
			atkString = super.getAttackString(atkType, move);
		}
		else if (atkType == 2){
			switch(move){
				case 1:
					atkString = "whipped with vines";
					break;
				case 2:
					atkString = "shredded with leaves";
					break;
				case 3:
					atkString = "hit with a solar beam";
					break;
			}			
		}
		return atkString;
  }

  /** This method returns the randomized damage for the chosen move.
   *  @param atkType: Type of attack (basic or special)
   *  @param move: move the pokemon will perform
   *  @return: returns the randomized damage.
   */
  @Override
  public int getAttackDamage(int atkType, int move){
    int atkDamage = 0;
		if (atkType == 1){
			atkDamage = super.getAttackDamage(atkType, move);
		}
		else if (atkType == 2){
			switch(move){
				case 1:
					atkDamage = (rand.nextInt(2) + 1);
					break;
				case 2:
					atkDamage = (rand.nextInt(2) + 2);
					break;
				case 3:
					atkDamage = (rand.nextInt(5));
					break;
			}			
		}
		return atkDamage;
  }

  /** This method returns the attack multiplier for that class's moves
   *  @param p: Pokemon object
   *  @param atkType: Type of attack(basic or special)
   *  @return: attack multiplier
   */
  @Override
  public double getAttackMultiplier(Pokemon p, int atkType){
    double atkMult = 0;
		if (atkType == 1){
			atkMult = super.getAttackMultiplier(p, atkType);
		}
		else if (atkType == 2){
			atkMult = battleTable[2][p.getType()];
		}
		return atkMult;
  }

}