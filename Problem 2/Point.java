import java.util.Scanner;

//Model a point
class Point{
	private static final double INF = 1L;
	private double x;
	private double y;

	Point(double x, double y){
		this.x = x;
		this.y = y;
		
	}
	Point(){
		this(0.0,0.0) ;
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	
	public void replace(final Point p){
		if(p!=null){
			x = x-p.x;
			y = y - p.y;
		}
	}
	
	public double x_intersect(Point p){
		if(null!=p)			
			return x -y*(p.x - x)/(p.y - y);
		return INF;
	}
	
	public boolean crossed(final Point p){
		if(null!=p){
			return p.y*y<0;
		}
		return false;
	}
	
	public static void main(String[] args){
		//array of point for polygone:
      Scanner sc = new Scanner(System.in);
      System.out.println("How many vertices does your polygone has?");
      
      int nVertices = 3;
      while(true){
         try{
         nVertices = sc.nextInt();
         break;
         }
         catch(Exception e){
         System.out.println("Please enter an Integer value (at least three :");
         }
      }
      double[][] polygone = new double[nVertices + 1][2];
      
      
      System.out.println("Enter the coordinates of the vertices of the polygones. You must end  with the first vertex:");
      System.out.println("(Example: 1 1 1 5 4 1 1 1 to represent: [[1,1],[1,5],[4,1],[1,1])"); 
      int position = 0;//initialize the counter
      while(position<(nVertices + 1)){
         double x = sc.nextDouble();
         double y = sc.nextDouble();
         polygone[position][0] = x;
         polygone[position][1]=y;
         position++;
         
      }      
		//double[][] polygone = {{1,1},{1,5},{4,1},{1,1}};
      while(true){
         System.out.println("Enter the test point sepatated by a space(e.g. 2 2 for (2,2)):");
         
   		Point p = new Point(sc.nextDouble(),sc.nextDouble());
   		//Point[] polygonec = new Point[polygone.length];
   				
   		double w = winding(polygone,p);
   		if(w!=0)
   			System.out.println("Point is inside!");
   		else
   			System.out.println("Point is ouside");
         System.out.println("Would you like to test another point (yes/no)?");
         if(sc.next().toLowerCase().equals("no"))
            break;
       }
       System.out.println("Good bye");
	}
   
   public static double winding(double[][] polygone, Point p){
      Point[] polygonec = new Point[polygone.length];
      for(int i=0;i<polygone.length;i++){
   			polygonec[i] = new Point(polygone[i][0],polygone[i][1]);
   			polygonec[i].replace(p);
   		}
   		double w = 0;
   		for(int i=0;i<polygone.length - 1;i++){
   			if(polygonec[i].crossed(polygonec[i+1])){
   				double r = polygonec[i].x_intersect(polygonec[i+1]);
   				if(r>0){
   					if(polygonec[i].getY()<0)
   						w++;
   					else
   						w--;
   				}
   			}
   			if(polygonec[i].getY()==0 && polygonec[i].getX()>0){
   				if(polygonec[i+1].getY()<0)
   					w -= 0.5;
   				else
   					w += 0.5;
   			}
   			else if(polygonec[i+1].getY()==0 && polygonec[i+1].getX()>0){
   				if(polygonec[i].getY()<0)
   					w += 0.5;
   				else
   					w -= 0.5;
   			}
   		}
         return w;

   
   }
}