import java.util.Random;
/**
 * The Pokemon class is a subclass of the Entity class.
 * @author  Darren Seng
 *
 */
public abstract class Pokemon extends Entity{
  protected static Random rand = new Random();
  /**
   * battleTable will have its values used to determine the damage multiplier of an attack. It depends on the defending Pokemon's type.
   */
  public static final double [][] battleTable = {{1,.5,2},{2,1,.5},{.5,2,1}};
  
  /** Initializes a Pokemon's name and HP values
   *  @param n: Pokemon's name
   *  @param h: Pokemon's HP
   *  @param m: Pokemon's Max HP
   */
  public Pokemon(String n, int h, int m) {
    super(n, h, m);
  }

  /** This method brings the menu on a basic attack or a special attack.
   *  @return string representation of the attack menu.
   */
  public String getAttackTypeMenu() {
	  return String.format("1. Basic Attack\n2. Special Attack");
  }

  /** This method gets the number of attack menu options
   *  @return the number of attack menu items.
   */
  public int getNumAttackTypeMenuItems() {
	  return 2;
  }

  /** This method returns the attack menu option for the attacks
   *  @param atkType: The type of attack being passed (basic or special).
   *  @return: returns the string slam tackle or punch.
   */
  public String getAttackMenu(int atkType){
    return "1.Slam\n2.Tackle\n3.Punch";
  }

  /** This method returns the number of attack menu options
   *  @return: the number of options for attack menu
   */
  public int getNumAttackMenuItems(int atkType){
    return 3;
  }

  /** This method calculates the total damage, deals it to the defending pokemon, and builds full attack string that will be displayed during a fight. 
   *  @param p: Pokemon object
   *  @param atkType: Type of attack (basic or special)
   *  @param move: move the pokemon will perform
   *  @return: returns the attack string.
   */
  public String attack(Pokemon p, int atkType, int move){
	  int totalDMG = this.getAttackBonus(atkType);
	  if (atkType == 1){ 
		 totalDMG += this.getAttackDamage(atkType, move);
	  }
	  else if(atkType == 2){
     totalDMG += (int)(this.getAttackDamage(atkType, move) * this.getAttackMultiplier(p, atkType));
	  }
	  if(totalDMG < 0){
			totalDMG = 0;
		}
	  p.takeDamage(totalDMG);
	  return p.getName() + " is " + this.getAttackString(atkType, move) + " by " + this.getName() + " and takes " + totalDMG + " damage!.";
  }

  /** This method returns the partial string for the move
   *  @param atkType: Type of attack (basic or special)
   *  @param move: the move the pokemon will perform
   *  @return: returns the partial attack string.
   */
  public String getAttackString(int atkType, int move){
    String atkString = "bananas";
    if(atkType == 1){
      switch(move){
        case 1:
          atkString = "slammed";
          break;
        case 2:
          atkString = "tackled";
          break;
        case 3:
          atkString = "punched";
          break;
      }
    }
    return atkString;
  }

  /** This method returns the randomized damage for the chosen move
   *  @param atkType: Type of attack (basic or special)
   *  @param move: Move the pokemon will perform
   *  @return: randomized damage of chosen move.
   */
  public int getAttackDamage(int atkType, int move){
    int atkDamage = 0;
    if(atkType == 1){
      switch(move){
        	case 1:
					atkDamage = (rand.nextInt(6));
					break;
				case 2:
					atkDamage = (rand.nextInt(2) + 2);
					break;
				case 3:
					atkDamage = (rand.nextInt(4) + 1);
					break;
      }
    }
    return atkDamage;
  }

  /** This method returns the attack multiplier for that class's moves
   *  @param p: Pokemon object
   *  @param atkType: Type of attack (basic or special)
   *  @return: returns the multiplier
   */
  public double getAttackMultiplier(Pokemon p, int atkType){
    return 1;
  }

  /** This method returns the attack bonus that will be added to the calculated damage.
   *  @param atkType: Type of attack (basic or special).
   *  @return: returns the attack bonus.
   */
  public int getAttackBonus(int atkType){
    return 0;
  }

  /** This method gets the type of the pokemon to determine the type advantage/disadvantage
   *  @return 0, 1, 2, or - 1 depending on the type of pokemon.
   */
  public int getType() {
	  if(this instanceof Fire){
      return 0;
    }
    else if(this instanceof Water){
      return 1;
    }
    else if(this instanceof Grass){
      return 2;
    }
    else{
      return -1;
    }
  }
}