
import java.awt.*;
import java.applet.*;

public class Othello_game extends Applet implements Runnable
{
   Thread runner;                

   boolean red_shown=false;    
   boolean show=false;

   final int RED = 1;          
   final int WHITE = 2;
   final int EMPTY = 0;
   final int OFFBOARD = -1;

   final static int Game[][] = new int[10][10];    

   protected int red_count = 0;        
   protected int white_count = 0;

   Event evt;
   int   x,y;



   public void start()                     
      {
      if (runner == null)
         {
         red_shown=false;
         runner = new Thread(this);
         runner.start();
         }
      }

   public void stop()                    
      {
      if (runner != null)
         {
         runner.stop();
         runner = null;
         red_shown=false;
         }
      }

//INITIALISE SCREEN
   public synchronized void run()                
      {
      setBackground(Color.black);

      for (int i=0; i<10; i++)      
         {
            Game[i][0] = OFFBOARD;
            Game[i][9] = OFFBOARD;
            Game[0][i] = OFFBOARD;
            Game[9][i] = OFFBOARD;
         }

      for (int i=1; i<9; i++)       
         for (int j=1; j<9; j++)
            Game[i][j] = EMPTY;

      Game[4][4] = WHITE;          
      Game[5][4] = RED;
      Game[4][5] = RED;
      Game[5][5] = WHITE;

      while (runner!=null)         
         {                         
         while (!red_shown)
            {
            try {
                wait();
            } catch (InterruptedException e){ }
            red_shown=false;
            showStatus("Good move!");
            pause(1000);
            whiteResponds();
            }
         }
      }
               
 
   // red clicked on a square - update screen
   public synchronized boolean mouseUp(Event evt, int x, int y)
   {
      Dimension d = size();             // SQUARE CLICKED
      int c = (x*8)/d.width + 1;        // COL
      int r = (y*8)/d.height + 1;       // ROW
      boolean red_done;                 // IF RED CANNOT MOVE EVERYWHERE

      if (valid_move(r,c,RED,WHITE,true))
        {
        Game[r][c] = RED;               // MARK THE SQUARE RED
        repaint();
        red_shown=true;
        notify();
        }
      else showStatus("Not a legal move");
      red_done=true;                    //AVAILABLE MOVES FOR RED
      for (int i=1; i<9; i++)
         for (int j=1; j<9; j++)
            if (valid_move(i,j,RED,WHITE,false) )
               red_done=false;
      
      if (red_done)                     //NO MOVE FOR RED
         for (int i=1; i<65; i++)
            whiteResponds();

      return true;
   } 


   //COMPUTER PLAYER
   public void whiteResponds()
      {
      boolean found;   	                 
      int i, j;				
  
      found=false;
      if (valid_move(1,1,WHITE,RED,true))      //CHECK CORNERS
         {
         Game[1][1]=WHITE;
         found=true;
         }
      if ( (!found) && (valid_move(8,8,WHITE,RED,true)) )
         {
         Game[8][8]=WHITE;
         found=true;
         }
      if ( (!found) && (valid_move(1,8,WHITE,RED,true)) )
         {
         Game[1][8]=WHITE;
         found=true;
         }
      if ( (!found) && (valid_move(8,1,WHITE,RED,true)) )
         {
         Game[8][1]=WHITE;
         found=true;
         }

	i=3;				                      //CHECK CENTRE SQUARES
	while ((!found) && (i < 7))
	{
	    j=3;
            while ( (!found) && (j < 7))
	    {
	    if	(valid_move(i,j,WHITE,RED,true)) 
               {
               Game[i][j]=WHITE;
               found=true;
               }
	       j++;
	     }
	     i++;
	}

	i=3;
	while ((!found) && (i < 7))               //CHECK EDGES NOT SURROUNDING A CORNER
         {                           
         if (valid_move(1,i,WHITE,RED,true))
            {
            Game[1][i]=WHITE;
            found=true;
            }
         if ( (!found) && (valid_move(8,i,WHITE,RED,true)))
              {
            Game[8][i]=WHITE;
            found=true;
            }
         if ( (!found) && (valid_move(i,1,WHITE,RED,true)))
            {
            Game[i][1]=WHITE;
            found=true;
            }
         if ( (!found) && (valid_move(i,8,WHITE,RED,true)))
            {
            Game[i][8]=WHITE;
            found=true;
            }
	    i++;
         }
      

      i=3;             
      while ((!found) && (i < 7))		     //CHECK INNER EDGES
         {
         if (valid_move(2,i,WHITE,RED,true))
            {
            Game[2][i]=WHITE;
            found=true;
            }
         if ( (!found) && (valid_move(7,i,WHITE,RED,true)))
            {
            Game[7][i]=WHITE;
            found=true;
            }
         if ( (!found) && (valid_move(i,2,WHITE,RED,true)))
            {
            Game[i][2]=WHITE;
            found=true;
            }
         if ( (!found) && (valid_move(i,7,WHITE,RED,true)))
            {
            Game[i][7]=WHITE;
            found=true;
            }
	    i++;
         }
   
	 i=1;		                              //CHECK SQUARES SURROUNDING A CORNER
	 while ((!found) && (i < 9))
	 {
	   j=1;
	   while ((!found) && (j < 9))
	   {	
            if (valid_move(i,j,WHITE,RED,true)) 
               {
               found=true;
               Game[i][j]=WHITE;
               }
	       j++;
	   }
	   i++;
	}
     
      repaint();
      }
//END OF COMPUTER MOVE
   
   

   // CHECK IF MOVE IS LEGAL
   public boolean valid_move(int r, int c, int color, int othercolor,
                            boolean flip)
   {
      int i,j;                                  
      boolean legal;                            
      int stepcount;                            
      legal = false;
  
      if (Game[r][c] == EMPTY)                  
         {
	   for (int xaxis=-1; xaxis < 2; xaxis++)
		for (int yaxis=-1; yaxis < 2; yaxis++)
		{
		  stepcount = 0;
		  do
		  {
			stepcount++;
			i = r + stepcount*xaxis; //STEPS ALONG X-AXIS
			j = c + stepcount*yaxis; //STEPS ALONG Y-AXIS
		   }
		  while ( (i > 0) && (i < 9) && (j > 0) && (j < 9) &&
			  (Game[i][j] == othercolor));
		  if (( i > 0) && (i < 9) && (j > 0) && (j < 9) &&
		          (stepcount > 1) && 
			// You must move more than one step for legal move
			  (Game[i][j] == color) )
			{ legal = true;
			  if (flip)
			  for (int k = 1; k < stepcount; k++)
				Game[r+xaxis*k][c+yaxis*k] = color;
			}
		} 
	}  
      if ( legal==true) return true;
      else return false;
   }


   void pause(int time)                     
      {
      try { runner.sleep(time); }         
      catch (InterruptedException e) { }
      }

       
   //PAINTING THE SQUARE
   public void paint(Graphics g)
   {
	   resize(300,300);
      Dimension d = size();
      g.setColor(Color.white);
      int xoff = d.width/8;
      int yoff = d.height/8;

      red_count=0;                        
      white_count=0;                        
      boolean done;                         //TRUE IF GAME OVER

      //DRAW LINES
      for (int i=1; i<=8; i++)              
      {
         g.drawLine(i*xoff, 0, i*xoff, d.height);
         g.drawLine(0, i*yoff, d.width, i*yoff);
      }
      g.setColor(Color.red);
      for (int i=1; i<9; i++)              
         for (int j=1; j<9; j++)
            {
            if (Game[i][j] == RED)       
               {
               g.fillOval((j*yoff+3)-yoff,(i*xoff+3)-xoff,30,30); 
               red_count++;
               }
            }
 
      g.setColor(Color.white);
      for (int i=1; i<9; i++)             
         for (int j=1; j<9; j++)
            {
            if (Game[i][j] == WHITE)       
               {
               g.fillOval((j*yoff+3)-yoff,(i*xoff+3)-xoff,30,30);
               white_count++;
               }
            }

      g.setColor(Color.BLUE);

      done=true;

      for (int i=1; i<9; i++)
         for (int j=1; j<9; j++)
            if ((valid_move(i,j,RED,WHITE,false))||(valid_move(i,j,WHITE,RED,false)))
               done=false;

      if (done==true) 
         {
         if (white_count>red_count)
            g.drawString("White won with "+white_count+" discs.",10,20);
         else if (red_count>white_count)
            g.drawString("Red won with "+red_count+" discs.",10,20);
         else g.drawString("Tied game",10,20);
         }
      else
         {     
    	  g.drawString("White: "+(white_count)+" discs",10,20);
    	  g.drawString("Red: "+(red_count)+" discs",10,40);
         if (white_count > red_count)
            g.drawString("White is winning with "+(white_count-red_count)+" discs",10,60);
         else if (red_count > white_count)
            g.drawString("Red is winning with "+(red_count-white_count)+" discs",10,60);
         else g.drawString("Currently tied",10,60);
         }
      
      if (show==true)
         {
         for (int i=1; i<9; i++)
            for (int j=1; j<9; j++)
               if (valid_move(i,j,RED,WHITE,false))
                  g.fillOval((j*yoff+15)-yoff,(i*xoff+15)-xoff,5,5);
         }
   }
 
   public void Action()
   {
      if (show==false)
         {
         show=true;
         repaint();
         }
      else
         {
         show=false;
         repaint();
         }
   }
}