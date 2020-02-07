import java.util.Random;
public class Pool 
{

	private static final String alph = "ABCDEFGHIJKLMNOPQRSTUVWXYZ ";
	private static final int [] score = {1,3,3,2,1,4,2,4,1,8,5,1,3,1,1,3,10,1,1,1,1,4,4,8,4,10,0};
	private int [] amount = {9,2,2,4,12,2,3,2,9,1,1,4,2,6,8,2,1,6,4,6,4,2,2,1,2,1,2};
	private static final int [] res = {9,2,2,4,12,2,3,2,9,1,1,4,2,6,8,2,1,6,4,6,4,2,2,1,2,1,2};
	private int total = 100;
	
	
	
	public int getValue(char c)
	{
		if(c == ' ')
		{
			return score[26];
		}else 
		{
			return score[c - 'A'];
		}
	}
	
	
	public void reset() 
	{
		for(int i = 0;i < 27;i++)
		{
			amount[i] = res[i];
		}
		total = 100;
	}
	
	
	public int remaining()
	{
		return total;
	}
	
	
	public char draw()
	{
		char a = ' ';
		boolean done = false;
		Random rn = new Random();
		while(!done)
		{
			int rand = rn.nextInt(27);
			if(amount[rand] > 0)
			{
				a = alph.charAt(rand);
				amount[rand]--;
				total--;
				done = true;
			}
		}
		
		return a;
	}
	
	public char draw(char a)
	{
		if(a == ' ')
		{
			amount[26]++;
		}
		else
		{
			amount[a - 'A']++;
		}
		
		total++;
		return draw();
	}

}