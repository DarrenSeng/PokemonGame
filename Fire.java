/**
 * This is a Fire class for Fire Pokemon.
 * @author Darren Seng
 *
 */
public class Fire extends Pokemon{

 /**
  * default constructor for Fire class
  * @param n, name of the Fire pokemon
  * @param h, hp of the Fire pokemon
  * @param m, maxHp of the Fire pokemon
  */
  Fire(String n, int h, int m){
    super(n, h, m);    
  }

 /**
  * prompt attack menu
  * @param atkType, 1 for basic attack, and 2 for Fire attack
  * @return, return prompted attack menu string
  */
  @Override
  public String getAttackMenu(int atkType){
	  if (atkType == 1) {
		  return super.getAttackMenu(atkType);
	  }
	  else {
    return "1.Ember\n2.Fire Blast\n3.Fire Punch";
	  }
  }

 /**
  * get the number of special menu items
  * @param atkType, 1 for basic attack, and 2 for Fire attack
  * @return, return 3 for both basic attack and special attack
  */
  @Override
  public int getNumAttackMenuItems(int atkType){
    return 3;
  }

 /**
  * prompt Attack menu
  * @param atkType, 1 for basic attack, and 2 for Fire attack
  * @param move, move the pokemon will perform.
  * @return, return print out menu
  */
  @Override
  public String getAttackString(int atkType, int move){
    String atkString = null;
		if (atkType == 1) {//basic
			atkString = super.getAttackString(atkType, move);
		}
		else if (atkType == 2) {//special
			switch(move) {
				case 1:
					atkString = "embered";
					break;
				case 2:
					atkString = "blasted with fire";
					break;
				case 3:
					atkString = "punched with fire";
					break;
			}
					
		}
		return atkString;
  }


 /**
  * Generate random attack damage
  * @param atkType, 1 for basic attack, and 2 for Fire attack
  * @param move, move the pokemon will perform.
  * @return, the dmg to other pokemon
  *
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
					atkDamage = rand.nextInt(3);
					break;
				case 2:
					atkDamage = (rand.nextInt(3) + 2);
					break;
				case 3:
					atkDamage = (rand.nextInt(3) + 1);
					break;
			}			
		}
		return atkDamage;
  }

 /**
  * This method returns the attack multiplier for that class's moves
  * @param p, the Pokemon object
  * @param atkType, 1 for basic attack, and 2 for Fire attack
  * @return, attack multiplier
  * 
  */
  @Override
  public double getAttackMultiplier(Pokemon p, int atkType){
    double atkMult = 0;
		if (atkType == 1){//basic
			atkMult = super.getAttackMultiplier(p, atkType);
		}
		else if (atkType == 2){//special
			atkMult = battleTable[0][p.getType()];
		}
		return atkMult;
  }

}//end Fire