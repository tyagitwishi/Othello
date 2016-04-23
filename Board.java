
public class Board {

	int Player1Symbol = 1;
	int Player2Symbol = 2;
	public final int BOARDSIZE = 8;
	int board[][] = new int[BOARDSIZE][BOARDSIZE];
	Board()
	{
		for(int i = 0;i<BOARDSIZE;i++)
		{
			for(int j = 0;j<BOARDSIZE;j++)
			{
				board[i][j] = 0;
			}
		}
		board[3][3] = board[4][4] = 1;
		board[3][4] = board[4][3] = 2;

	}
//	public final int PLAYER1WON = 1;
//	public final int PLAYER2WON = 2;
//	public final int DRAW = 3;
//	public final int CONTINUE = 4; 

	public boolean move(int player,int x,int y)
	{

		if(x >= BOARDSIZE || y >= BOARDSIZE)
		{
			System.out.println("Invalid coordinates!! Try Again");
			return false;
		}
		boolean flag = false,chk = false;
		int ch = 1;
		int p;
		if(player == 1)
		{
			p = 2;
		}
		else
		{
			p = 1;
		}
		int pos_x,pos_y;
		switch(ch)
		{
		case 1:
			
			flag = false;
			while(flag==false)
			{	
				if(player==1)
				{
					if(y!=7 && board[x][y+1]!=2)
						break;
				}
				else 
				{
					if(y!=7 && board[x][y+1]!=1)
					{
						System.out.println("Break case 1");	
						break;
					}
				}
				pos_x = x;
				pos_y = y+1;
				while(pos_y<BOARDSIZE-1 && board[x][pos_y]!=player)
				{
					pos_y++;
				}
				flag = true;
				if(pos_y<=BOARDSIZE-1 && board[x][pos_y]==player)
				{
					while(pos_y!=y-1)
					{
						board[pos_x][pos_y] = player;
						pos_y--;
					}
					chk = true;
				}
			}
		case 2:
			flag = false;
			while(flag ==false)
			{
				if(player==1)
				{
					if(y!=7 && x!=0 && board[x-1][y+1]!=2)
						break;
				}
				else
				{
					if(y!=7 && x!=0 && board[x-1][y+1]!=1)
					{
						System.out.println("Break case 2");	
						break;
					}
				}
				pos_x = x-1;
				pos_y = y+1;
				while(pos_x>0 && pos_y<BOARDSIZE-1 && board[pos_x][pos_y]!=player)
				{
					pos_x--;
					pos_y++;
				}
				flag = true;
				if(pos_x>=0 && pos_y<=BOARDSIZE-1 && board[pos_x][pos_y]==player)
				{
					while(pos_x!=x+1 && pos_y!=y-1)
					{
						board[pos_x][pos_y]=player;
						pos_x++;
						pos_y--;
					}
					chk = true;
				}
					
			}
		case 3:
			flag = false;
			while(flag==false)
			{
				if(player==1)
				{
					if(x!=0 && board[x-1][y]!=2)
						break;
				}
				else 
				{
					if(x!=0 && board[x-1][y]!=1)
					{
						System.out.println("Break case 3");	
						break;
					}
				}
				pos_x = x-1;
				pos_y = y;
				while(pos_x>0 && board[pos_x][pos_y]!=player && board[pos_x][pos_y]==p)
				{
					pos_x--;
					System.out.println(pos_x+"Enter while1");	
				}
				flag = true;
				if(pos_x>=0 && board[pos_x][pos_y]==player)
				{
					while(pos_x!=x+1)
					{
						board[pos_x][pos_y] = player;
						pos_x++;
					}
					chk = true;
					System.out.println("Enter while loop");	
				}
			}
		case 4:
			flag = false;
			while(flag ==false)
			{
				if(player==1)
				{
					if(x!=0 && y!=0 && board[x-1][y-1]!=2)
						break;
				}
				else
				{
					if(x!=0 && y!=0 && board[x-1][y-1]!=1)
					{
						System.out.println("Break case 4");	
						break;
					}
				}
				pos_x = x-1;
				pos_y = y-1;
				while(pos_x>0 && pos_y>0 && board[pos_x][pos_y]!=player)
				{
					pos_x--;
					pos_y--;
				}
				flag = true;
				if(pos_x>=0 && pos_y>=0 && board[pos_x][pos_y]==player)
				{
					while(pos_x!=x+1 && pos_y!=y+1)
					{
						board[pos_x][pos_y]=player;
						pos_x++;
						pos_y++;
					}
					
					chk = true;
				}
					
			}
		case 5:
			flag = false;
			while(flag == false)
			{
				if(player==1)
				{
					if(y!=0 && board[x][y-1]!=2)
						break;
				}
				else 
				{
					if(y!=0 && board[x][y-1]!=1)
					{
						System.out.println("Break case 5");	
						break;
					}
				}
				pos_x = x;
				pos_y = y-1;
				while(pos_y>0 && board[x][pos_y]!=player)
				{
					pos_y--;
				}
				flag = true;
				if(pos_y>=0 && board[x][pos_y]==player)
				{
					while(pos_y!=y+1)
					{
						board[pos_x][pos_y] = player;
						pos_y++;
					}
				
					chk = true;
				}
			}
		case 6:
			flag = false;
			while(flag ==false)
			{
				if(player==1)
				{
					if(x!=7 && y!=0 && board[x+1][y-1]!=2)
						break;
				}
				else
				{
					if(x!=7 && y!=0 && board[x+1][y-1]!=1)
					{
						System.out.println("Break case 6");	
						break;
					}
				}
				pos_x = x+1;
				pos_y = y-1;
				while(pos_x<BOARDSIZE-1 && pos_y>0 && board[pos_x][pos_y]!=player)
				{
					pos_x++;
					pos_y--;
				}
				flag = true;
				if(pos_x<=BOARDSIZE-1 && pos_y>=0 && board[pos_x][pos_y]==player)
				{
					while(pos_x!=x-1 && pos_y!=y+1)
					{
						board[pos_x][pos_y]=player;
						pos_x--;
						pos_y++;
					}
					
					chk = true;
				}
					
			}
		case 7:
			flag = false;
			while(flag == false)
			{
				if(player==1)
				{
					if(x!=7 && board[x+1][y]!=2)
						break;
				}
				else 
				{
					if(x!=7 && board[x+1][y]!=1)
					{
						System.out.println("Break case 7");	
						break;
					}
				}
				pos_x = x+1;
				pos_y = y;
				while(pos_x<BOARDSIZE-1 && board[pos_x][pos_y]!=player)
				{
					pos_x++;
				}
				flag = true;
				if(pos_x<=BOARDSIZE-1 && board[pos_x][pos_y]==player)
				{
					while(pos_x!=x-1)
					{
						board[pos_x][pos_y] = player;
						pos_x--;
					}
					
					chk = true;
				}
			}
		case 8:
			flag = false;
			while(flag ==false)
			{
				if(player==1)
				{
					if(x!=7 && y!=7 && board[x+1][y+1]!=2)
						break;
				}
				else
				{
					if(x!=7 && y!=7 && board[x+1][y+1]!=1)
					{
						System.out.println("Break case 8");	
						break;
					}
				}
				pos_x = x+1;
				pos_y = y+1;
				while(pos_x<BOARDSIZE-1 && pos_y<BOARDSIZE-1 && board[pos_x][pos_y]!=player)
				{
					pos_x++;
					pos_y++;
				}
				flag = true;
				if(pos_x<=BOARDSIZE-1 && pos_y<=BOARDSIZE-1 && board[pos_x][pos_y]==player)
				{
					while(pos_x!=x-1 && pos_y!=y-1)
					{
						board[pos_x][pos_y]=player;
						pos_x--;
						pos_y--;
					}
					chk = true;
				}
			}
		}
		System.out.println("Out of switch");	
		if(chk==false)
		{
			System.out.println("Invalid move!! Try Again");
		}
		return chk;

	}

	public void print()
	{
		for(int i = 0;i<BOARDSIZE;i++)
		{
			for(int j = 0; j < BOARDSIZE;j++)
			{
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public boolean isAvailable()
	{
		int count = 0;
		for(int i = 0;i<BOARDSIZE;i++)
		{
			for(int j = 0;j<BOARDSIZE;j++)
			{
				if(board[i][j]==0)
				{
					count++;
				}
			}
		}
		if(count == 0)
			return false;
		else
			return true;
	}
	
	public int winner()
	{
		int p1 = 0;
		int p2 = 0;
		for(int i = 0;i<BOARDSIZE;i++)
		{
			for(int j=0;j<BOARDSIZE;j++)
			{
				if(board[i][j]==1)
					p1++;
				else
					p2++;
			}
		}
		if(p1>p2)
		{
			return 1;
		}
		else if(p2>p1)
		{
			return 2;
		}
		else
		{
			return 0;
		}
	}

}
