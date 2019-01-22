import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.ImageObserver;
import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Game extends JPanel implements Runnable,ActionListener,KeyListener, ImageObserver {

			String path = new File("").getAbsoluteFile()+"\\src\\images\\"; // Images Folder Path
		   
		   boolean p1_left,p1_right,p1_shoot;
	       int p1_score=-4;	  
		   static int p1_jet_init_move=0;
		   static int p1_fire=18,p1_fire_shoot=410;
		   
		   boolean p2_left,p2_right,p2_shoot;
		   int p2_score=-4;
		   static int p2_jet_init_move=375;
		   static int p2_fire=405,p2_fire_shoot=410;
		   
		   static int limit=375;
		   int i;
		   int positionx[] = {0,95,190,285,380,45,140,235,325,420,0,95,190,285,380,45,140,235,325,420};
		   int positiony[] = {90,90,90,90,90,120,120,120,120,120,150,150,150,150,150,180,180,180,180,180};
		   
		   static int minute=1,seconds=0;
		   final Timer timer = new Timer();
		 
		   
		   public static void main(String args[]){
	              Game t1 = new Game();
	    	      JFrame f = new JFrame();
	    	      f.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
	              f.add(t1);
	              f.setSize(440,600);
	              f.setVisible(true);
	              f.setResizable(false);
	              f.setLocationRelativeTo(null);
	              f.setTitle("CivilWar:The Bloody Road South");
	                                            }
	      Thread t;
	      Game(){
	    	   addKeyListener(this);
	    	   setFocusable(true);
	    	   t=new Thread(this);
	    	   t.start();
	    	   timer.scheduleAtFixedRate(new TimerTask() {
		            public void run() {
		            	if(minute==0&&seconds==0){
			               stop();
			               timer.cancel();
			            }
		            	if(seconds<0){
		                   seconds=59;
		                   minute--;
		                }
		            	else
		                   seconds--;
		            }
		        }, 0, 1000);
	    	     }
	       
	      void sleep(){
	    	   try{
	    	       Thread.sleep(8);
	    	      }catch(Exception ex){
	    	    	   
	    	                          }
	                  }
	       
	      void start(){
	    	   if(t==null){
	    		  t=new Thread(this,"New Thread");
	    		  t.start();
	    	              }
	    	          }
	      
	      public void stop(){
	    	     if(t!=null){
	    		    t=null;
	    	                }
	                        }
	       
	      public void run(){
	    	     Thread t1=Thread.currentThread();
	    	     while(t==t1){
	    		       repaint();
	    		       try{
	    			        Thread.currentThread().sleep(90);
	    		          }catch(Exception e){
	    		        	  e.printStackTrace();
	    			                         }
	    	                 }
	                       }
	       
	      public void actionPerformed(ActionEvent e){
	    	                                        } 
	       
	      public void paint(Graphics g){
	    	  // Background Image                    
	    	  g.drawImage(Toolkit.getDefaultToolkit().getImage(path+"HomeBG.png"),0,0,435,450,this);
	          g.finalize();
	          
	          //Moving Spaceships
	          for(int i=0;i<20;i++){
	        	  if(i%2!=0){
	        		 positionx[i] = (positionx[i]+10)%430;
	        	     g.drawImage(Toolkit.getDefaultToolkit().getImage(path+"ufo1.png"),positionx[i],positiony[i],30,30,this);	  
	                 g.finalize();
	        	  			}
	        	  if(i%2==0){
	        		 positionx[i] = positionx[i]-5;
	        	     if(positionx[i]<=0)
	        		    positionx[i]+=430;
	        	     g.drawImage(Toolkit.getDefaultToolkit().getImage(path+"ufo2.png"),positionx[i],positiony[i],40,20,this);	  
	                 g.finalize();
	         	  			}
	                               }
	          
	        //Time
	    	  g.setColor(Color.BLACK);
	          g.drawString("Time",180,470);
	          g.drawImage(Toolkit.getDefaultToolkit().getImage(path+"write.png"),170,470,100,100,this);	  
	          g.finalize();
	          if(seconds==-1)
	             g.drawString(minute+" : "+0,178,490);
	          else
	        	 g.drawString(minute+" : "+seconds,178,490);
	          
	          //Player1 Image
	          g.drawImage(Toolkit.getDefaultToolkit().getImage(path+"player1.png"),20,460,50,50,this);	  
	          g.finalize();
	          //fire
	          if(p1_fire_shoot>=0)
	    	      p1_fire_shoot = (p1_fire_shoot - 10)%420;
	          else{
	    		   p1_fire = p1_jet_init_move+18;
	    		   p1_fire_shoot = 410;
	    		   } 
	          g.drawImage(Toolkit.getDefaultToolkit().getImage(path+"missile1.png"),p1_fire,p1_fire_shoot,15,30,this);	  
	          g.finalize();
	          
	          //Player1 Jet
	          g.drawImage(Toolkit.getDefaultToolkit().getImage(path+"jet1.png"),p1_jet_init_move,400,50,50,this);	  
	          g.finalize();
	          //Player1 Name
	          g.setColor(Color.BLACK);
	          g.drawString("Captain",25,520);
	          
	          
	          //Player2 Image
	          g.drawImage(Toolkit.getDefaultToolkit().getImage(path+"player2.png"),360,460,50,50,this);	  
	          g.finalize();
	          //fire
	          if(p2_fire_shoot>=0)
	    	      p2_fire_shoot = (p2_fire_shoot - 10)%420;
	    	   else{
	    		   p2_fire = p2_jet_init_move+18;
	    		   p2_fire_shoot = 410;
	    		   } 
	          g.drawImage(Toolkit.getDefaultToolkit().getImage(path+"missile2.png"),p2_fire,p2_fire_shoot,15,30,this);	  
	          g.finalize();
	          
	          //Player2 Jet
	          g.drawImage(Toolkit.getDefaultToolkit().getImage(path+"jet2.png"),p2_jet_init_move,400,50,50,this);	  
	          g.finalize();
	          //Player2 Name
	          g.setColor(Color.BLACK);
	          g.drawString("Deadpool",360,520);
	          
	          
	          for(int i=0;i<20;i++){
	        	  //Blue
	        	  if(Math.abs(p1_fire-positionx[i])<25&&Math.abs(p1_fire_shoot-positiony[i])<10){
	        		  g.drawImage(Toolkit.getDefaultToolkit().getImage(path+"boom.png"),positionx[i],positiony[i],70,70,this);
	        		  positionx[i]=0;
	        		  p1_fire_shoot=410;
	        		  p1_fire = p1_jet_init_move + 18;
	        		  p1_score++;	  
	        	  }
	        	  // Red
	        	  if(Math.abs(p2_fire-positionx[i])<25&&Math.abs(p2_fire_shoot-positiony[i])<10){
	        		  g.drawImage(Toolkit.getDefaultToolkit().getImage(path+"boom.png"),positionx[i],positiony[i],70,70,this);
	        		  positionx[i]=375;
	        		  p2_fire_shoot=410;
	        		  p2_fire = p2_jet_init_move + 18;
	        		  p2_score++;
	        		  
	        	  }
	          }
	          //Player1 Score
	         // g.fillRect(20,535,100,100);
	          g.drawImage(Toolkit.getDefaultToolkit().getImage(path+"write.png"),20,535,100,100,this);	  
	          g.finalize();
	          g.drawString("Score : "+p1_score,20,545);
	          g.setColor(Color.BLACK);
	          
	          //Player2 Score
	          //g.fillRect(330,535,100,100);
	          g.drawImage(Toolkit.getDefaultToolkit().getImage(path+"write.png"),330,535,100,100,this);	  
	          g.finalize();
	          g.drawString("Score : "+p2_score,330,545);
	          g.setColor(Color.BLACK);
	          
	          if(minute==0&&seconds==0){
	         	 Font f = new Font("Arial", Font.ITALIC, 35);
	 	         g.setFont(f); 
	         	 g.setColor(Color.WHITE); 
	         	 if(p1_score>p2_score){g.drawString("Captain America Wins!",60,260);}
	         	 if(p2_score>p1_score){g.drawString("Deadpool Wins!",140,260);}
	         	 if(p1_score==p2_score){g.drawString("To be continued...",120,260);}
	         	 stop();
	                                   }
	                    
	                                                                             }
		
		public void keyPressed(KeyEvent p) {
			   int keycode = p.getKeyCode();
			   if( keycode == KeyEvent.VK_A)move_left_j1();
			                                   							   
			   if( keycode == KeyEvent.VK_D)move_right_j1();
			                                    							
	          // if( keycode == KeyEvent.VK_W)p1_fire1();
	                                      								  
			   if( keycode == KeyEvent.VK_LEFT){move_left_j2();}
			   
			   if( keycode == KeyEvent.VK_RIGHT)move_right_j2();
			                                   							
	          // if( keycode == KeyEvent.VK_UP)p2_fire2();
	        
	           
			  	                           }

		public static void move_right_j1(){
		       if(p1_jet_init_move<limit){p1_jet_init_move+=5;}
		}
		
	    public static void move_right_j2(){
		       if(p2_jet_init_move<limit){p2_jet_init_move+=5;}
		}
	    
	    public static void move_left_j1(){
		       if(p1_jet_init_move>0){p1_jet_init_move-=5;}
		}
	    
	    public static void move_left_j2(){
		       if(p2_jet_init_move>0){p2_jet_init_move-=5;}
	    }
	    /*
	    public static void p1_fire1(){
	    	   left = true;
	    }
	    
	    public static void p2_fire2(){
	    	   right = true;
	    }
	  */  
		public void keyReleased(KeyEvent p){
			                               }

		public void keyTyped(KeyEvent a){
			 	                        }

		public void setTitle(String string){		
		                                   }
	
}
