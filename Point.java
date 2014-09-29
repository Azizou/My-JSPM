
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
		double[][] polygone = {{1,1},{1,5},{4,1},{1,1}};
		Point p = new Point(2,2);
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
		
		
		if(w!=0)
			System.out.println("Point is inside!");
		else
			System.out.println("Point is ouside");
	}
}