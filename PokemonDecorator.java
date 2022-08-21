/**
 * Pokemon Decorator is a class that will have base pokemon types of Fire, Water, or Grass. Those base types will then be decorated with different types of buffs and debuffs to update the pokemon's stats.
 *
 * @author Darren Seng
 *
 */

public abstract class PokemonDecorator extends Pokemon{
  private Pokemon pokemon;

 /**
  * default constructor
  * @param p, pokemon object
  * @param extraName, extra name to be added
  * @param extraHp, extra hp to be added
  */
  public PokemonDecorator(Pokemon p, String extraName, int extraHp){
    super(p.getName()+extraName, p.getHp()+extraHp, p.getMaxHp()+extraHp);
    pokemon = p;
  }

 /**prompt attack menu
  * @param atkType, 1 for basic attack, and 2 for Fire attack
  * @return, return prompted attack menu string
  */
  @Override
  public String getAttackMenu(int atkType){
    return pokemon.getAttackMenu(atkType);
  }

 /**
  * get the number of special menu items
  * @param atkType, 1 for basic attack, and 2 for Fire attack
  * @return, number of attack menu item
  */
  @Override
  public int getNumAttackMenuItems(int atkType){
    return pokemon.getNumAttackMenuItems(atkType);
  }


 /**
  * prompt Attack menu
  * @param atkType, 1 for basic attack, and 2 for Fire attack
  * @param move, move the pokemon will perform.
  * @return, return print out menu
  */
  @Override
  public String getAttackString(int atkType, int move){
    return pokemon.getAttackString(atkType, move);
  }

 /**
  * Generate random attack damage
  * @param atkType, 1 for basic attack, and 2 for Fire attack
  * @param move, move the pokemon will perform.
  * @return, the dmg to other pokemon
  */
  @Override
  public int getAttackDamage(int atkType, int move){
    return pokemon.getAttackDamage(atkType, move);
  }

 /**
  * the attack multiplier for that class's moves
  * @param p, the Pokemon object
  * @param atkType, 1 for basic attack, and 2 for Fire attack
  * @return, attack multiplier
  */
  @Override
  public double getAttackMultiplier(Pokemon p, int type){
    return pokemon.getAttackMultiplier(p, type);
  }


 /**
  * the adjusted attack bonus when the AttackUp buff is applied
  * @param atkType, 1 for basic attack, and 2 for Fire attack
  * @return, return the attack bouns number
  */
  @Override
  public int getAttackBonus(int type){
    return pokemon.getAttackBonus(type);
  }

 /**
  * gets the type of the pokemon to determine the type advantage/disadvantage
  *  @return 0, 1, 2, or - 1 depending on the type of pokemon
  */
  @Override
  public int getType() {
    return pokemon.getType();
  }
}