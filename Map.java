import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.awt.Point;

/**
 * The Map class will create a 5x5 2D array representing the map of the game by reading and storing contents of text files.
 *
 */
public class Map {
  //declare 2d array for characters on map
  private char[][] map;
  //declare 2d array for what's revealed 
  private boolean[][] revealed;
  //map instance for the whole program
  private static Map instance  = null;

 /**
  * default constructor
  * map and reveal are initialized with 5 rows and 5 columns. map is a 2D char array. revealed is a 2D boolean array
  */
  private Map() {
    map = new char[5][5];
    revealed = new boolean[5][5];
  }

 /** 
  * mapInstance, Singleton, using only one single instance of Map class
  * @return, return the map Instance
  */
  public static Map getInstance(){
    if(instance == null){
      instance  = new Map();
    }
    return instance;
  }


 /**
  * loadMap loads the map by reading a text file.
  * @param mapNum, the value of mapNum determines which map will be loaded.
  * @exception FileNotFoundException an exception is thrown if the file is not found
  */
  public void loadMap(int mapNum) throws FileNotFoundException {
    File mapFile = new File("Area1.txt");
	  File mapFile2 = new File("Area2.txt");
	  File mapFile3 = new File("Area3.txt");
    int a = 0;
	  int b = 0;
	  String currentLine;
	  File[] files = new File[] {mapFile, mapFile2, mapFile3};
    	Scanner readMap1 = new Scanner(files[mapNum]);
    	while (readMap1.hasNextLine()) {
	    	currentLine = readMap1.nextLine();
	    	Scanner currentLineScanner = new Scanner(currentLine);
	    	while (currentLineScanner.hasNext()) {
	    		map[a][b] = currentLineScanner.next().charAt(0);
	    		revealed[a][b] = false;
	    		b++;
	    	}
	    	currentLineScanner.close();
	    	b = 0;
	    	a++;
	  }
	    readMap1.close();
  }

 /**
  * Gets the character at the location of Point p
  * @param p, passes in the Point that will have its character returned
  * @return returns the character of p from its map location
  */
  public char getCharAtLoc(Point p) {
    //implement out of bounds, default
    return map[(int)p.getX()][(int)p.getY()];
  }

 /**
  * mapToString will display the map, showing the user's position and explored/unexplored points
  * @param p, the point that gets passed into MapToString to determine the user's position as "*"
  * @return, print string map
  */
   public String mapToString(Point p){
	  String mapString = "";
    for (int x = 0; x < 5; x++){
      for (int y = 0; y < 5; y++){
        if (p.getX()==x & p.getY() ==y){
          mapString +=("* "); //user's position
        }
        else if (revealed[x][y]==false) {
          mapString+=("x ");
        }
        else {
          mapString+=(map[x][y] + " ");      //[rows][columns]   
          }
      }
      mapString+=("\n");
    }
	return mapString;
  }

 /**
  * gets the location of the starting point "s"
  * @return, returns the starting point location
  */
  public Point findStart() {
    Point start = null;
    for (int i = 0; i < 5; i++){
      for (int j = 0; j < 5; j++) {
        if (map[i][j] == 's') {
          start = new Point(i,j);
        }
      }
    }
    return start;
  }

 /**
  * reveals the current point, so that the mapToString will display the character at that point instead of 'x'
  * @param p, the current point that will have its character revealed
  */
  public void reveal(Point p) {
    revealed[(int)p.getX()][(int)p.getY()] = true;
  }

 /**
  * A character will have its current character value replaced with 'n'
  * @param p, the point that will have its character swapped with 'n'
  */
  public void removeCharAt(Point p) {
    map[(int)p.getX()][(int)p.getY()] = 'n';
  }
}