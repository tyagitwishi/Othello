

import java.util.Scanner;

public class Game {
	 static Board b = new Board();
	 static Scanner scn = new Scanner(System.in);
	 static Player p1,p2;
		

	public static void main(String[] args)
	{
		start();
	}
	

	private static void start() 
	{
		p1 = getPlayer(1);
		p2 = getPlayer(2);
		b.print();
		play();
	}

	private static Player getPlayer(int i) {
		System.out.println("Enter name : ");
		String name = scn.nextLine();
		Player p = new Player(name,i);
		return p;
	}
	
	private static void play()
	{
		boolean chk = true;
		int chance = 1;
		boolean ans;
		int x,y;
		while(chk)
		{
			if(chance == 1)
			{
				System.out.println("Player 1 move : ");
				System.out.println("Enter X : ");
				x = scn.nextInt();
				System.out.println("Enter Y : ");
				y = scn.nextInt();
				ans = b.move(1, x, y);
				b.print();
				if(ans == true)
					chance = 2;
			}
			else if(chance == 2)
			{
				System.out.println("Player 2 move : ");
				System.out.println("Enter X : ");
				x = scn.nextInt();
				System.out.println("Enter Y : ");
				y = scn.nextInt();
				ans = b.move(2, x, y);
				b.print();
				if(ans == true)
					chance = 1;
			}
			chk = b.isAvailable();
			if(chk == false)
			{
				System.out.println("No possible move");
			}
		}
		int win = b.winner();
		if(win == 1)
		{
			System.out.println(p1.name + " won");
		}
		else if(win == 2)
		{
			System.out.println(p2.name + " won");
		}
		else 
		{
			System.out.println("Match Draw");
		}
	}

}
