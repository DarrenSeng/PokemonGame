/** Names: Michael Son, Yuwei Du, Darren Seng
 *  Date: 11/30/21
 *  Group: 13
 *  Project 2: Pokemon Revised
 *  Description: A pokemon game where the user is prompted to move around the map, where they can encounter a person, wild pokemon, city, an item, a new map, or nothing. With these options, the players can fight and capture wild pokemon, explore 3 different maps, enter cities to buy potions, pokeballs, fight gym leaders, as well as heal up at hospitals. They can also have interesting dialogue from people they come across on the journey. 
 */

import java.io.FileNotFoundException;
import java.util.Random;

class Main {
  public static void main(String[] args) {
    System.out.println("Prof. Oak: Hello there new trainer, what is your name?");
    String n = CheckInput.getString();
    System.out.println("Great to meet you, "+ n +"!");
    System.out.println("Choose your first pokemon: \n1. Charmander \n2. Bulbasaur \n3. Squirtle");
    int choice = CheckInput.getIntRange( 1, 3 );
    int trainerLevel = 1;
    int currMap = 0;
    Pokemon firstPokemon = null;
    PokemonGenerator g = PokemonGenerator.getInstance();
    switch(choice){
      case 1:
       firstPokemon = g.getPokemon("Charmander"); 
       break;
      case 2:
       firstPokemon = g.getPokemon("Bulbasaur");
       break;
      case 3:
       firstPokemon = g.getPokemon("Squirtle");
       break;
      default:
       System.out.println("Invalid choice, please select again.");
    }
    
    
    try {
		 Map.getInstance().loadMap(currMap);
	  } catch (FileNotFoundException e) {
		   System.out.println( "File not Found" );
	  }//open map
    Trainer trainer = new Trainer(n, firstPokemon);
    System.out.println(trainer.toString());
    mainMenu(trainer, Map.getInstance(), currMap, trainerLevel);
  }

 /**
  * mainMenu will display the directions the user can choose to go.
  * @param t, t is used to have its toString display the trainer's stats after each movement.
  * @param m, a paramter for the map that will be updated depending on the trainer's movements
  */
  public static void mainMenu(Trainer t, Map m, int currMap, int trainerLevel){
    System.out.println("Main Menu");
    int option = 0;
    do{
        System.out.println("1. Go North \n2. Go South \n3. Go East \n4. Go West \n5. Quit");
        option = CheckInput.getIntRange(1, 5);
        switch(option){
          case 1:
          encounters(m, t, t.goNorth(), currMap, trainerLevel);
          System.out.println(t.toString()); 
          break;

          case 2:
          encounters(m, t, t.goSouth(), currMap, trainerLevel);
          System.out.println(t.toString());  
          break;

          case 3:
          encounters(m, t, t.goEast(), currMap, trainerLevel);
          System.out.println(t.toString());  
          break;

          case 4:
          encounters(m, t, t.goWest(), currMap, trainerLevel);
          System.out.println(t.toString()); 
          break;

          case 5:  //Quit
          System.out.println("Exiting Program...");
          System.out.println("Game over");
          System.exit(0);
          break;
          
          default:
          System.out.println("Invalid menu choice, please try again.");
          System.out.println(t.toString()); 
        }
    }while (option != 5);

  }

  /** 
   * encounters, different encounters on map depends on char on map, 
   * s-start point
   * f-finish,encounter with a gym leader, after winning, jumps to next map
   * n-no encounter
   * i-find an random item(poke ball/potion)
   * w-meet with a wild pokemmon, trainer can choose to fight/use potion/throw poke ball to catch wild pokemon/run away
   * p-meet with a random person encounter with randmon result. Receive items or take dmg
   * c-enter a city, trainer can choose to go store buy items or go hospital to heal all pokemons
   * @param m, passing in the map which trainer is on
   * @param t, passing in trainer's info
   * @param a, the char shows on map where trainer is located
   * @param currMap, the number of map, 1,2,3,1,2,3
   * @param trainerLevel, counter of trainer level
 * @throws FileNotFoundException 
   */
  public static void encounters(Map m, Trainer t, char a, int currMap, int trainerLevel) {
	  PokemonGenerator g = PokemonGenerator.getInstance();
    Random rand = new Random();

    switch(a){
      case 'f': //finish, triggers the loading of the next map. Load maps in the order 1,2,3,1,2,3, etc
    	  System.out.println("The finish is blocked by a gym leader!");
    	  boolean gymDefeated = false;
    	  boolean trainerLost = false;
    	  Pokemon gymPoke = g.generateRandomPokemon(trainerLevel + 2);
    	  
    		  System.out.println("A " + gymPoke + " has appeared.");
    	      gymPoke.toString();
    	      
    	     int gymBattle = 0;
    	      do{
    	        System.out.println("What do you want to do?\n1. Fight\n2. Use Potion");
    	        gymBattle =  CheckInput.getIntRange(1, 2);
    	        switch( gymBattle ){
    	          case 1: //Fight
    	          trainerAttack(t, gymPoke);
    	          break;

    	          case 2: //use Potion
    	          if(t.hasPotion() == true){
    	            System.out.println(t.getPokemonList());
    	            System.out.println("Choose a pokemon to heal.");
    	            int heal = CheckInput.getIntRange(1, t.getNumPokemon());
    	            t.usePotion(heal);
    	            t.getPokemon(heal);
    	            System.out.println(t.getPokemonList());
    	          }t.getPokemonList();
    	          break;
    	          default:
    	              System.out.println("Invalid option, please try again.");
    	        }
    	        if (gymPoke.getHp() <= 0) {gymDefeated = true;};
    	        int zero_elements = 0;
    	        for (int i = 0; i < t.getNumPokemon();i++) {
    	        	if (t.getPokemon(i + 1).getHp() <= 0) {
    	        		zero_elements++;
    	        	}
    	        	} 
    	        if (t.getNumPokemon() == zero_elements){
    	        	//zero_elements =0;
    	        		trainerLost = true;
    	        		System.out.println("You have failed to defeat the Gym Leader. Come back when you are stronger");
    	        		int runAway = rand.nextInt( 4 ) + 1;
    	                switch(runAway){
    	                  case 1:
    	                  if(t.getLocation().getX()-1 >= 0){
    	                  t.goNorth();
    	                  }else if(t.getLocation().getX()+1 < 5){
    	                   t.goSouth();
    	                  }else if(t.getLocation().getY()+1 < 5){
    	                   t.goEast();
    	                  }else{
    	                   t.goWest();
    	                  }
    	                  break;  

    	                  case 2:
    	                  if(t.getLocation().getX()+1 < 5){
    	                    t.goSouth();
    	                  }else if(t.getLocation().getY()+1 < 5){
    	                   t.goEast();
    	                  }else if(t.getLocation().getY()-1 >= 0){
    	                   t.goWest();
    	                  }else{
    	                    t.goNorth();
    	                  }
    	                  break;  

    	                  case 3:
    	                  if(t.getLocation().getY()+1 < 5){
    	                    t.goEast();
    	                  }else if(t.getLocation().getY()-1 >= 0){
    	                   t.goWest();
    	                  }else if(t.getLocation().getX()-1 >= 0){
    	                  t.goNorth();
    	                  }else {
    	                   t.goSouth();
    	                  }
    	                  break;  

    	                  case 4:
    	                  if(t.getLocation().getY()-1 >= 0){
    	                    t.goWest();
    	                  }else if(t.getLocation().getX()-1 >= 0){
    	                    t.goNorth();
    	                  }else if(t.getLocation().getX()+1 < 5){
    	                    t.goSouth();
    	                  }else{
    	                   t.goEast();
    	                  }
    	                  break;  
    	                  
    	                }
    	        		
    	        	}
    	        
    	      } while (gymDefeated == false & trainerLost == false);
      if (gymDefeated == true) {
      t.buffAllPokemon();
      trainerLevel ++;
      System.out.println("You've defeated the Gym Leader and arrived at the finish!");
      System.out.println("You are in level "+ trainerLevel +" now!");
      currMap++;
      if (currMap == 3) {
    		 currMap = 0;
    	 }
      Map map = Map.getInstance();
      try {
        map.loadMap(currMap);
      }catch (FileNotFoundException e) {
        e.printStackTrace();
      }
      System.out.println(t.toString()); 
      mainMenu(t, map, currMap, trainerLevel); 
      }
      break;

      case 'n': //nothing
      System.out.println("There's nothing here...");
      break;

      case 'i': //item
       int i = rand.nextInt( 2 ) + 1;
       if(i == 1){ //give the trainer a potion
         System.out.println("You find a potion!");
         t.receivePotion();
         m.removeCharAt(t.getLocation());
       }else{
         //give the trainer a poke ball
         System.out.println("You find a poke ball!");
         t.receivePokeball();
         m.removeCharAt(t.getLocation());
       }
       break;

      case 'w': //wild pokemon, begins a fight with a wild pokemon. 
      Pokemon wild = g.generateRandomPokemon(trainerLevel);
      System.out.println("A wild "+ wild + " has appeared.");
      wild.toString();
      
      int battle = 0;
      int notCaught = 0; 
      int currentHp = t.getHp();
      do{
        System.out.println("What do you want to do?\n1. Fight\n2. Use Potion\n3. Throw Poke Ball\n4. Run Away");
        battle =  CheckInput.getIntRange(1, 4);
        switch( battle ){
          case 1: //Fight
          trainerAttack(t, wild);
          //wild = g.addRandomBuff(wild);
          break;

          case 2: //use Potion
          if(t.hasPotion() == true){
            System.out.println(t.getPokemonList());
            System.out.println("Choose a pokemon to heal.");
            int heal = CheckInput.getIntRange(1, t.getNumPokemon());
            t.usePotion(heal);
            t.getPokemon(heal);
            System.out.println(t.getPokemonList());
          }
          break;

          case 3: //throw Pokeball
          if(t.hasPokeball() == true){
            System.out.println("Shake...Shake...Shake...");
            if(t.catchPokemon(wild) == true){
              System.out.println("You caught a " + wild.getName() + " !");
              m.removeCharAt(t.getLocation());
              notCaught = 0;
              break;
            }else
            System.out.println("You didn't catch " + wild.getName() +" !");
            notCaught = 1;
          }
          System.out.println(t.getPokemonList());
          break;

          case 4: //runs away in a random direction (no encounter)
          int runAway = rand.nextInt( 4 ) + 1;
          switch(runAway){
            case 1:
            if(t.getLocation().getX()-1 >= 0){
            t.goNorth();
            }else if(t.getLocation().getX()+1 < 5){
             t.goSouth();
            }else if(t.getLocation().getY()+1 < 5){
             t.goEast();
            }else{
             t.goWest();
            }
            break;  

            case 2:
            if(t.getLocation().getX()+1 < 5){
              t.goSouth();
            }else if(t.getLocation().getY()+1 < 5){
             t.goEast();
            }else if(t.getLocation().getY()-1 >= 0){
             t.goWest();
            }else{
              t.goNorth();
            }
            break;  

            case 3:
            if(t.getLocation().getY()+1 < 5){
              t.goEast();
            }else if(t.getLocation().getY()-1 >= 0){
             t.goWest();
            }else if(t.getLocation().getX()-1 >= 0){
            t.goNorth();
            }else {
             t.goSouth();
            }
            break;  

            case 4:
            if(t.getLocation().getY()-1 >= 0){
              t.goWest();
            }else if(t.getLocation().getX()-1 >= 0){
              t.goNorth();
            }else if(t.getLocation().getX()+1 < 5){
              t.goSouth();
            }else{
             t.goEast();
            }
            break;  
            
          }
          break;
          
          default:
          System.out.println("Invalid option, please try again.");
        }
      }while(battle!=4 & (battle !=3 | notCaught != 0) & wild.getHp() != 0 & (t.getHp() == currentHp));
      if (t.getHp() != currentHp) {
    	  System.out.println(wild.getName() + " ran away!");
    	  m.removeCharAt(t.getLocation());
      }
      if (wild.getHp() <= 0){
        m.removeCharAt(t.getLocation());
      }
      break;

      case 'p': //person â€“ random person that the trainer encounters during their travels.
      int p = rand.nextInt( 5 ) + 1; //5 random case
      if( p == 1){ //Misty smack player
        System.out.println("You run into Misty.\nMisty: Where's my bike, twerp!\nMisty SMACKS you for 2 damage");
        t.takeDamage(2);
        m.removeCharAt(t.getLocation());

      }else if(p == 2){//Officer Jenny gave play a pokeball
       System.out.println("You run into Officer Jenny\nOfficer Jenny: Hey! How are you today? Here's what I found on the ground, I think it is yours. Oh, I left my motorcycle at the front desk. I need to go and get it, bye!\nYou received a pokeball from Officer Jenny");
       t.receivePokeball();
       m.removeCharAt(t.getLocation());
       
      }else if(p == 3){//Mom gave play money
       System.out.println("You run into mom\nMom: Hey,"+t.getName()+", where are you going? If you have time, can you please to the grocery store to buy some potatoes? Keep the extra money.\n You received money from mom!");
       t.receiveMoney(5);
       m.removeCharAt(t.getLocation());
      }else if(p == 4) { //Player was caught in one of Team Rocketâ€™s traps 
        System.out.println("You were caught in one of Team Rocketes traps.\nIt hurt you 1 damage.");
        t.takeDamage(1);
        m.removeCharAt(t.getLocation());

      }else{ //Charmander gets angry at player and blasts him with fire
       System.out.println("You met a wild Charmander, you walked over quietly but it still got angry because of your apperance, and it BLASTED you with fire. \nYou took 2 damage.");
        t.takeDamage(2);
        m.removeCharAt(t.getLocation());
      }
      break;

      case 'c': //city
      store(t);
      break;
    }
  }

  /**
   * store will allow the trainer to either enter the store or Pokemon Hospital. At the store, the trainer can either buy
   * pokeballs or buy potions. At the hospital, the user can heal their Pokemon.
   * @param t, Trainer t is passed in as a parameter in order for the user to be able to spend money, heal pokemon, and receive items
   */
  public static void store(Trainer t) {
    System.out.println("You've entered the city. Where would you like to go?\n1. Store\n2. Pokemon Hospital");
    int userOption = 0;
    int storeOption = 0;
    userOption = CheckInput.getIntRange(1,2);
    if (userOption == 1) {
      while (true) {
          System.out.println("Hello! What can I help you with?\n1. Buy Potions - $5\n2. Buy Pokeballs - $3\n3. Exit");
          storeOption = CheckInput.getIntRange(1, 3);
           if (storeOption == 1) {
             if(t.spendMoney(5) == true){
               System.out.println("Here's your potion.");
               t.receivePotion();
              }
            }
       else if (storeOption == 2) {
         if(t.spendMoney(3) == true){
           System.out.println("Here's your Pokeball.");
           t.receivePokeball();
           }
        }
       else if (storeOption == 3) {
         System.out.println("Thank you, come again soon!"); 
         break;
        }
      }
    }
    else if (userOption == 2) {
       System.out.println("Hello! Welcome to the Pokemon Hospital. \nI'll fix your poor pokemon up in a jiffy! \nThere you go! See you again soon.");
       t.healAllPokemon();
      }
    else {
       System.out.println("Invalid menu choice, please try again.");
    }
  }

  /** Attack option for when the "Fight" option is selected.
   *  @param t: The trainer (user)
   *  @param wild: The wild pokemon the trainer is fighting.
   */
  public static void trainerAttack(Trainer t, Pokemon wild){
	PokemonGenerator g = PokemonGenerator.getInstance();
	Random rand = new Random();
    System.out.println("Choose a Pokemon:");
    System.out.println(t.getPokemonList());
    int userInput = CheckInput.getIntRange(1, t.getNumPokemon());
    if(t.getPokemon(userInput).getHp() <= 0){
      int rand1 = rand.nextInt(5) + 1;
      t.takeDamage(rand1);
      System.out.println(t.getPokemon(userInput).getName() + " has 0 hp. " + wild.getName() +" does " + rand1 + " damage to you."); 
    }
    else if(t.getPokemon(userInput).getHp() > 0){
      System.out.println(t.getPokemon(userInput).getName() + ", I choose you!");
      System.out.println(t.getPokemon(userInput).getAttackTypeMenu());
      int atkType = CheckInput.getIntRange(1,2);
      System.out.println(t.getPokemon(userInput).getAttackMenu(atkType)); //prints basic or special
      int moveType = CheckInput.getIntRange(1, t.getPokemon(userInput).getNumAttackMenuItems(atkType));
      System.out.println(t.getPokemon(userInput).attack(wild,atkType, moveType));
      int randDebuff = rand.nextInt(4);
      int correctDebuff = 3;
      if (randDebuff == correctDebuff) {
    	  wild = g.addRandomDebuff(wild);
    	  System.out.println("The enemy pokemon has been debuffed!");
      }
      if(wild.getHp() < 1){
        System.out.println("You beat " + wild.getName());
      }
      else{
    	  int randEnemyDebuff = rand.nextInt(10);
    	  int correctEnemyDebuff = 9;
          int randATKtype = rand.nextInt(2) + 1;
          int randMove = rand.nextInt(wild.getNumAttackMenuItems(randATKtype)) + 1;
          System.out.println(wild.attack(t.getPokemon(userInput), randATKtype, randMove));
          if (randEnemyDebuff == correctEnemyDebuff) {
        	  t.debuffPokemon(userInput -1);
        	  System.out.println("Your pokemon has been debuffed!");
          }
          System.out.println("\n" +wild);
          
      }
    }
  }
}