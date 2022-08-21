import java.util.ArrayList;
import java.awt.Point;
import java.util.Random;
/**
 * The Trainer class is a subclass of the Entity class.
 * @author Darren Seng
 *
 */
public class Trainer extends Entity{
  private int money;
  private int potions;
  private int pokeballs;
  private Point loc;
  private ArrayList < Pokemon > pokemon = new ArrayList < Pokemon > ();

 /**
  * default constructor
  * @param n, passing in trainer's name
  * @param p, passing in the pokemon that player selected
  */
 public Trainer(String n, Pokemon p) {
	  super(n, 25, 25);//n,h,m
	  money = 25;
	  potions = 3;
	  pokeballs = 8;
	  //add chosen pokemon to arraylist pokemon
    pokemon.add(p);
    loc = Map.getInstance().findStart();
  }

 /**
  * Print out amt of money
  * @return, return amt of money that player has
  */
 public int getMoney(){
   return money;
  }

 /**
  * check if have enough money and then use amt of Money
  * @param amt, passing in the amt of money spent
  * @return, true if has money to spend and return the rest of money,
            false if not enough money to spend.
  */
 public boolean spendMoney(int amt){
   if( money - amt >= 0){
     this.money = getMoney() - amt;
     return true;
    }
    else{
      System.out.println("You don't have enough money.");
      return false;
    }
  }

 /**
  * Add received amt to Money
  * @param amt, the amt that received
  */
 public void receiveMoney(int amt){
   this.money = getMoney() + amt;
 }
 
  /**
  * check if the number of potions is greater than 0
  * @return True, if potion is greater than 0. False if potion is less than 0
  */
 public boolean hasPotion(){
   if(potions > 0){
     return true;
    }
    else{
      System.out.println("Not enough potion.");
      return false;
    }
  }

 /**
  * receivePotion, adds a potion when called by adding 1 to the total potion amount
  */
 public void receivePotion(){
   this.potions +=1;
  }

 /**
  * Heal a choosed pokemon, and the pokemon gets a random buff
  * @param pokeIndex, the index that pokemon in the arrayList
  */
 public void usePotion(int pokeIndex){
	 PokemonGenerator gen = PokemonGenerator.getInstance();
   if(hasPotion() == true){
     this.potions = potions - 1;
     pokemon.get(pokeIndex - 1).heal();
     pokemon.set(pokeIndex -1, gen.addRandomBuff(pokemon.get(pokeIndex - 1)));
   }
  }

 /**
  * Check if player has a poke Ball
  * @return, true if pokeball greater than 0
             false if pokebal less or equal than 0
  */
 public boolean hasPokeball(){
   if(pokeballs > 0){
     pokeballs -= 1;
     return true;
   }
   else{
     System.out.println("Not enough pokeballs.");
     return false;
    }
  }

 /**
  * receivePokeball, adds a pokeball when called by adding 1 to the pokeball amount.
  */
 public void receivePokeball(){
   pokeballs += 1;
 }
 

 /**
  * Catch a pokemon and add it to ArrayList, has hp/maxHp chance to catch it, and max number of pokemon is 6, when arrayList has 7 pokemons, ask player to remove one
  * @param p, the catched pokemon
  * @return, true if pokemon is catched
             false if not catch it
  */
 public boolean catchPokemon(Pokemon p){
   Random rand = new Random();
   int chance = p.getHp();
   int a = rand.nextInt(p.getMaxHp() + 1);
   if (a > chance && chance != 0){
      //pokeballs-=1;
      if(pokemon.size() < 6){
        pokemon.add(p); 
      }else{//>6
      pokemon.add(p);
      System.out.println("You reach the maximum number of pokemon, please choose one to transfer to professor.");
      System.out.println(getPokemonList());
      int index = CheckInput.getIntRange(1, 7);
      removePokemon(index);
    }
     return true;
    }
    return false;
  }

 /**
  * Initialized player's location
  * @return, retuen the player's location(x,y)
  */
 public Point getLocation(){
   return loc;
  }

 /**
  * Check bound and move to North
  * @return, return the char on that location after moving
  */
 public char goNorth(){
   Map.getInstance().reveal(loc);
   if(loc.getX() - 1 >= 0){
     loc.translate( -1, 0 );
     Map.getInstance().reveal(loc);
     return Map.getInstance().getCharAtLoc(loc);
   }else{
     System.out.println("You cannot go that way.");
     return Map.getInstance().getCharAtLoc(loc);
    } 
  }

 /**
  * Check bound and move to South
  * @return, return the char on that location after moving
  */
 public char goSouth(){
   Map.getInstance().reveal(loc);
   if(loc.getX() + 1 < 5 ){ 
	   loc.translate( +1, 0 );
     Map.getInstance().reveal(loc);
     return Map.getInstance().getCharAtLoc(loc);
   }else{
     System.out.println("You cannot go that way.");
     return Map.getInstance().getCharAtLoc(loc);
   }
 }

 /**
  * Check bound and move to East
  * @return, return the char on that location after moving
  */
 public char goEast(){
   Map.getInstance().reveal(loc);
   if(loc.getY() + 1 < 5 ){
	   loc.translate( 0, +1 );
     Map.getInstance().reveal(loc);
     return Map.getInstance().getCharAtLoc(loc);
   }else{
     System.out.println("You cannot go that way.");
     return Map.getInstance().getCharAtLoc(loc);
   }
 }
 
 /**
  * Check bound and move to West
  * @return, return the char on that location after moving
  */
 public char goWest(){
   Map.getInstance().reveal(loc);
   if(loc.getY() - 1 >= 0 ){
     loc.translate( 0, -1 );
     Map.getInstance().reveal(loc);
     return Map.getInstance().getCharAtLoc(loc);
   }else{
     System.out.println("You cannot go that way.");
     return Map.getInstance().getCharAtLoc(loc);
   }
 }

 /**
  * get the number of total getPokemons
  * @return, the size of pokemon arrayList
  */
 public int getNumPokemon(){
  return pokemon.size();
 }

 /**
  * healAllPokemon, loops through pokemon list and call getheal(), heal all pokemon.
  */
 public void healAllPokemon(){
   for(int i = 0; i < pokemon.size(); i++){
     pokemon.get(i).heal();
   }
 }

 /**
  * all pokemon get a random buff
  */
 public void buffAllPokemon() {
   PokemonGenerator gen = PokemonGenerator.getInstance();
   for(int i = 0; i < pokemon.size(); i++){
     pokemon.set(i, gen.addRandomBuff(pokemon.get(i)));
   }
 }

 /**
   * all pokemon get a random debuff
   */
 public void debuffPokemon(int index) {
   PokemonGenerator gen = PokemonGenerator.getInstance();
   pokemon.set(index, gen.addRandomDebuff(pokemon.get(index)));
   
 }



 /**
  * get the pokemon's info
  * @param index, the indext that the pokemon in the pokemon ArrayList
  * @return, the choosed pokemon
  */
 public Pokemon getPokemon(int index){
   return pokemon.get(index - 1);
 }

 /**
  * print out all pokemon's hp/maxhp
  * @return, print the formatting of pokemon arraylist
  */
 public String getPokemonList(){
   String n = "";
   for(int i = 0; i < pokemon.size(); i++){
     n += (i+1) +". "+ pokemon.get(i).getName() + " HP: " + pokemon.get(i).getHp() + "/" +pokemon.get(i).getMaxHp() + "\n";
    }
    return n;
  }

 /**
  * remove the chosen pokemon
  * @param index, index-1 is the chosen pokemon's position  in arrayList
  * @return, removed pokemon
  */
 public Pokemon removePokemon(int index){
    return pokemon.remove(index - 1);
  }

 /**
  * Return a string in a formatted way, 
  * name, hp/maxHp, inventory, list of pokÃ©mon, and location on the map
  *  @return string representation of the player and player's pokemon.
  */
 @Override
 public String toString(){
   return this.getName() + " HP: " + this.getHp() + "/" + this.getMaxHp() 
   + "\nMoney: " + getMoney() 
   + "\nPotions: " + potions 
   + "\nPoke Balls: " + pokeballs 
   + "\nPokemon" 
   + "\n-------" 
   + "\n" + getPokemonList()
   + "\n "
   + "\n" + Map.getInstance().mapToString(loc) 
   + "\n ";
  }

  
 }//end Trainer class