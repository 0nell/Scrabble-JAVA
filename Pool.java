import java.util.Random;
public class Pool 
{
	//stores the different tile characters (' ' is blank tile)
	private static final String Tiles = "ABCDEFGHIJKLMNOPQRSTUVWXYZ ";		
	//stores the score of the tiles corresponding to the order in the Tiles string
	private static final int [] scoreOfTile = {1,3,3,2,1,4,2,4,1,8,5,1,3,1,1,3,10,1,1,1,1,4,4,8,4,10,0};	
	//stores amount of each type of tile corresponding to Tiles order
	private static int [] amountOfTile = {9,2,2,4,12,2,3,2,9,1,1,4,2,6,8,2,1,6,4,6,4,2,2,1,2,1,2};		
	//Used to stores original values of amount for reset purposes
	private static final int [] resetTile = {9,2,2,4,12,2,3,2,9,1,1,4,2,6,8,2,1,6,4,6,4,2,2,1,2,1,2};		
	private static int numOfTiles = 100;			
	
	
	/*RETURNS SCORE OF TILE CHARACTER INPUT*/
	public static int getValue(char c)
	{
		int tileValue = 0;
		if(c == ' ')				
		{
			tileValue = scoreOfTile[26];
		}
		else 
		{
			tileValue = scoreOfTile[c - 'A'];	//eg A would correspond to scoreOfTile[0], B to scoreOfTile[2]...
		}
		return tileValue;
	}
	
	
	public static void reset() 
	{
		for(int i = 0;i < 27;i++)	//resets the amount array using the res array
		{
			amountOfTile[i] = resetTile[i];
		}
		numOfTiles = 100;				
	}
	
	
	public static int getNumOfTiles()
	{
		return numOfTiles;
	}

	public static boolean isEmpty()
	{
		return (numOfTiles == 0);
	}
	
	
	public static char draw()
	{
		char a = ' ';	
		boolean tileFound = false;
		Random rn = new Random();				
		int rand = rn.nextInt(getNumOfTiles()) + 1;	
		int r = rand;	//initializes r to be a random number between 1 and the amount remaining in the pool,

		for(int i = 0; i < 27 && !tileFound; i++)		
		{
			r -= amountOfTile[i];		//minuses the amount of that specific character in the pool from the random index
			if(r <= 0)			//when the index becomes <= 0 the character of that random index has been reached
			{
				a = Tiles.charAt(i);		//sets a to that character
				amountOfTile[i]--;			
				numOfTiles--;			 	//reduces the total amount of tiles by 1
				tileFound = true;
			}
		}	
		return a;
	}
	
	/*SWAPS INPUT CHARACTER WITH NEW CHARACTER FROM POOL*/
	public static char swapTile(char a)
	{
		if(a == ' ')	
		{
			amountOfTile[26]++;	
		}
		else
		{
			amountOfTile[a - 'A']++;	//eg amount of tiles of char A would correspond to amountOfTile[0], B to amountOfTile[2]...
		}
		
		numOfTiles++;		
		return draw();	
	}
}