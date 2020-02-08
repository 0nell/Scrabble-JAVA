import java.util.Random;
public class Pool 
{

	private static final String Tiles = "ABCDEFGHIJKLMNOPQRSTUVWXYZ ";		//stores the different tile characters (' ' is blank tile)
	private static final int [] scoreOfTile = {1,3,3,2,1,4,2,4,1,8,5,1,3,1,1,3,10,1,1,1,1,4,4,8,4,10,0};	//stores the score of the tiles corresponding to the order in the Tiles string
	private static int [] amountOfTile = {9,2,2,4,12,2,3,2,9,1,1,4,2,6,8,2,1,6,4,6,4,2,2,1,2,1,2};		//stores amount of each type of tile corresponding to Tiles order
	private static final int [] resetTile = {9,2,2,4,12,2,3,2,9,1,1,4,2,6,8,2,1,6,4,6,4,2,2,1,2,1,2};		//Used to stores original values of amount for reset purposes
	private static int numOfTiles = 100;			//stores amount of tiles in pool
	
	
	/*RETURNS SCORE OF TILE CHARACTER INPUT*/
	public static int getValue(char c)
	{
		if(c == ' ')				//space needs to be specifically checked for as its not stored directly after capital letters in ascii
		{
			return scoreOfTile[26];
		}
		else 
		{
			return scoreOfTile[c - 'A'];	//eg A would correspond to scoreOfTile[0], B to scoreOfTile[2]...
		}
	}
	
	/* RESETS POOL */
	public static void reset() 
	{
		for(int i = 0;i < 27;i++)	//resets the amount array using the res array
		{
			amountOfTile[i] = resetTile[i];
		}
		numOfTiles = 100;				//resets tile total to 100
	}
	
	/*RETURNS AMOUNT OF TILES IN POOL*/
	public static int getNumOfTiles()
	{
		return numOfTiles;
	}

	public static boolean isEmpty()
	{
		return (numOfTiles == 0);
	}
	
	/*DRAWS A RANDOM CHARACTER FROM THE POOL*/
	public static char draw()
	{
		char a = ' ';	

		Random rn = new Random();				
		int rand = rn.nextInt(getNumOfTiles()) + 1;	
		int r = rand;	//initializes r to be a random number between 1 and the amount remaining in the pool,

		for(int i = 0; i < 27; i++)		//used to loop through the tile characters
		{
			r -= amountOfTile[i];		//minuses the amount of that specific character in the pool from the random index
			if(r <= 0)			//when the index becomes <= 0 the character of that random index has been reached
			{
				a = Tiles.charAt(i);		//sets a to that character
				amountOfTile[i]--;			//reducees the amount of that tile int the pool by 1
				numOfTiles--;			 	//reduces the total amount of tiles by 1
				break;
			}
		}	
		return a;
	}
	
	/*SWAPS INPUT CHARACTER WITH NEW CHARACTER FROM POOL*/
	public static char draw(char a)
	{
		if(a == ' ')	//space needs to be specifically checked for as its not stored directly after capital letters in ascii
		{
			amountOfTile[26]++;	
		}
		else
		{
			amountOfTile[a - 'A']++;	//eg amount of tiles of char A would correspond to amountOfTile[0], B to amountOfTile[2]...
		}
		
		numOfTiles++;		//increases the total tiles as tile returned to pool
		return draw();	//returns a random character using the overloaded draw function
	}
}