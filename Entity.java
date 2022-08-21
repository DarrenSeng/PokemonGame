/**
 * The Entity class helps create all entities for the game (trainers and pokemon), that have some amount of HP.
 * @author Darren Seng
 *
 */
public abstract class Entity {
  private String name;
  private int hp;
  private int maxHp;
  
  /**default constructor
   * 
   * @param n. the entity's name
   * @param h. the entity's current hp
   * @param m, the entity's maximum hp
   */
  public Entity(String n, int h, int m){
    name = n;
    maxHp = m;
    hp = h;
  }
  /**
   * Gets the current hp amount
   * @return the amount of hp that the entity has
   */
  public int getHp() {
    return hp;
  }
  /**
   * Gets the maximum hp amount
   * @return the maximum hp amount that the entity has
   */
  public int getMaxHp() {
    return maxHp;
  }
  /**
   * Subtracts an amount of damage from the entity's hp
   * @param d the amount of damage that the hp will take
   */
  public void takeDamage(int d) {
    hp -= d;
    if (hp < 0) {
      hp = 0;
    }
  }
  /**
   * heal recovers full hp to the entity
   */
  public void heal() {
      hp = maxHp;
  }
  /**
   * Gets the name of the entity
   * @return returns the name of the entity
   */
  public String getName() {
    return name;
  }
  @Override
  public String toString() {
    return (name + " HP: " + hp + "/" + maxHp);
  }
}

