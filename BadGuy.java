
import java.awt.Graphics;
import java.awt.Image;

public class BadGuy {
	
	Image myImage;
	int x=0,y=0;
	boolean hasPath=false;
	boolean firstWalkable=false;
	boolean secondWalkable=false;
	Node node[][] = new Node [40][40];
	int parX, parY;

	public BadGuy( Image i ) {
		myImage=i;
		x = 30;
		y = 10;
		//populate the bad guy with the node
		for(int j=0; j<40; j++) {
			for(int l=0;l<40;l++) {
				node[j][l] = new Node(0,0);
			}
		}
	}
	
	public void reCalcPath(boolean map[][],int targx, int targy) {
		// TO DO: calculate A* path to targx,targy, taking account of walls defined in map[][]
		parX = x;
		parY = y;
		while(firstWalkable) {
			node[parX][parY].open = true; // add the starting position to open list
			for(int xx=-1; xx<=1; xx++) {
				for(int yy=-1; yy<=1; yy++) {
					if(x+xx<0) continue;
					else if(y+yy<0) continue;
					else if(x+xx>39) continue;
					else if(y+yy>39) continue;
					else if(node[x+xx][y+yy].closed) continue;
					else if (!map[x+xx][y+yy]) {
						node[x+xx][y+yy].open = true;
						System.out.println("Count");
						if(xx==-1&&yy==-1||xx==-1&&yy==1||xx==1&&yy==1||xx==1&&yy==-1)
						{
							//diagonal cases
							node[x+xx][y+yy].g=14;
						}
						else if(xx==0&&yy==0)
						{
							//current position
							node[x+xx][y+yy].g=0;
						}
						else
						{
							//all other cases
							node[x+xx][y+yy].g=10;
						}
						while(!secondWalkable)
						{
							int org_x=x;
							int org_y=y;
							move(map,targx,targy); //record the path
							node[x+xx][y+yy].h+=10; //heuristic cost
						}
						node[x+xx][y+yy].f=node[x+xx][y+yy].g+node[x+xx][y+yy].h;// finding the overall sum
					}
				}
			}
			node[parX][parY].open = false;
			node[parX][parY].closed = true;
		}
		hasPath = true;
	}
	
	public void move(boolean map[][],int targx, int targy) {
		if (hasPath) {
			// TO DO: follow A* path, if we have one defined
			reCalcPath(map, targx, targy);
		}
		else {
			// no path known, so just do a dumb 'run towards' behaviour
			int newx=x, newy=y;
			if (targx<x)
				newx--;
			else if (targx>x)
				newx++;
			if (targy<y)
				newy--;
			else if (targy>y)
				newy++;
			if (!map[newx][newy]) {
				x=newx;
				y=newy;
			}
		}
	}
	
	public void paint(Graphics g) {
		g.drawImage(myImage, x*20, y*20, null);
	}
	
}

