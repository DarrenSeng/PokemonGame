import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
/**
 * Pokemon Generator will generate random pokemon. It will read from a file and store into a Hashmap the different pokemon names and their elemental type
 * @author Darren Seng
 *
 */
public class PokemonGenerator {
  private HashMap<String,String> pokemon = new HashMap <String,String>();
  static PokemonGenerator instance = null;
  /**
  * default constructor
  * reads from PokemonList.txt to create a hashmap of pokemon
  */
  private PokemonGenerator()  {
	  try {
	  String currentLine;
	  File list1 = new File("PokemonList.txt");
	  Scanner reader = new Scanner(list1);
	  while (reader.hasNextLine()) {
		  currentLine = reader.nextLine();
		  String[] currentPokemon = currentLine.split(",");
		  pokemon.put(currentPokemon[0], currentPokemon[1]);
	  }
	  } catch (FileNotFoundException e) {
		  e.printStackTrace();
	  }
	  
  } 
  /**
  * getInstance singleton
  * used to get the instance of Pokemon Generator in order to buff a pokemon or generate a new Pokemon
  */
  public static PokemonGenerator getInstance() {
	  if (instance == null) {
		  instance = new PokemonGenerator();
	  }
	  return instance;
  }
/**
  * generateRandomPokemon
  * @param int, the level of the generated pokemon
  *a random pokemon is selected and created from the HashMap. It is then returned
  */
  public Pokemon generateRandomPokemon(int level) {
	  Pokemon randoP = null;
	  Random rand = new Random();
	  List<String> keys = new ArrayList<String>(pokemon.keySet());
	  String randPokemonName = keys.get(rand.nextInt(keys.size()));
	  String randPokemonType = pokemon.get(randPokemonName);
	  int randomMHP = rand.nextInt(6) + 20;
	  if (randPokemonType.equals("Fire")) {
		  randoP = new Fire(randPokemonName,randomMHP,randomMHP);
	  }
	  else if (randPokemonType.equals("Water")) {
		  randoP = new Water(randPokemonName,randomMHP,randomMHP);
	  }
	  else if (randPokemonType.equals("Grass")) {
		  randoP = new Grass(randPokemonName,randomMHP, randomMHP);
	  }
	  if (level > 1) {
		  for (int i = 2; i <= level; i++) {
			  randoP = this.addRandomBuff(randoP);
		  }
	  }
	  return randoP;  
  }
  
  /**
  * getPokemon
  * @param name, passing in the name of the Pokemon to get from the hashmap in order creates a Pokemon object 
  */
  public Pokemon getPokemon(String name) {
	  Random rand = new Random();
	  String pokeType = pokemon.get(name);
	  int randomMHP = rand.nextInt(6) + 20;
	  if (pokeType.equals("Fire")) {
		  return new Fire(name,randomMHP,randomMHP);
	  }
	  else if (pokeType.equals("Water")) {
		  return new Water(name,randomMHP,randomMHP);
	  }
	  else if (pokeType.equals("Grass")) {
		  return new Grass(name,randomMHP,randomMHP);
	  }
    else{
      return null;
    }
  }
  /**
  * addRandomBuff
  * @param p, passing in a pokemon object
  * the pokemon will have a buff randomly applied to it, either HP up or Attack up
  */
  public Pokemon addRandomBuff(Pokemon p) {
	  //Pokemon buffedPoke = p;
	  Random rand = new Random();
	  int randBuff = rand.nextInt(2);
	  if (randBuff == 0) {
		  p = new AttackUp(p);
	  }
	  else if (randBuff == 1) {
		  p = new HpUp(p);
	  }
	  return p;
  }
   /**
  * addRandomDeuff
  * @param p, passing in a pokemon object
  * the pokemon will have a debuff randomly applied to it, either HP down or Attack down
  */
  public Pokemon addRandomDebuff(Pokemon p) {
	  Random rand = new Random();
	  int randDebuff = rand.nextInt(2);
	  if (randDebuff == 0) {
		  p = new AttackDown(p);
	  }
	  else if (randDebuff == 1) {
		  p = new HpDown(p);
	  }
	  return p;
  }
}