/**
 * @author Azizou Ogbone
 * This class modelled a version number for a package an then sort them. 
 * It implements the Comparable interface because the order used in sorthing
 * is not the natural order used for strings.
 */

import java.util.*;

public class PackageVersion implements Comparable<PackageVersion>{
	
	private String name;
	private int x;
	private int y;
	private int z;
	private String type = "";
	private int type_version = 0;
	private String partial_name = "";
	
	public PackageVersion(String version) {
		name = version;
		initialise(version);
	}

	private void initialise(String version) {
		int pos1, pos2,pos3,type_version_pos = 0, heyphen;
		pos1 = version.indexOf(".");
		pos2 = pos1 +1+version.substring(pos1 + 1).indexOf(".");
		heyphen = version.indexOf("-");
		if(heyphen!=-1){
			type_version_pos =version.substring(heyphen + 1).indexOf(".");
			pos3 = heyphen;
		}
		else
			pos3 = version.length();
		x = Integer.parseInt(version.substring(0,pos1));
		y = Integer.parseInt(version.substring(pos1 + 1,pos2));
		z = Integer.parseInt(version.substring(pos2 + 1,pos3));
		partial_name = x + "." + y+"." + z;
		
		if(heyphen!=-1){
			type = version.substring(heyphen + 1);
			if(type_version_pos!=-1)
				type_version = Integer.parseInt(version.substring(heyphen + type_version_pos + 2));
		}
	}
	
	/**
	 * @return the partial_name
	 */
	public String getPartial_name() {
		return partial_name;
	}

	/**
	 * @param partial_name the partial_name to set
	 */
	public void setPartial_name(String partial_name) {
		this.partial_name = partial_name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		//return "\"" + name + "\"";
      return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * @return the z
	 */
	public int getZ() {
		return z;
	}

	/**
	 * @param z the z to set
	 */
	public void setZ(int z) {
		this.z = z;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the type_version
	 */
	public int getType_version() {
		return type_version;
	}

	/**
	 * @param type_version the type_version to set
	 */
	public void setType_version(int type_version) {
		this.type_version = type_version;
	}

	public boolean hasType(){
		
		return !type.equals("");
	}
	
	public boolean hasTpeVersionNumber(){
		if(!hasType())
			return false;
		return type_version != 0 ;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if(obj.getClass()==this.getClass()){
			PackageVersion other = (PackageVersion)obj;
			return name.equals(other.name);
		}
		
		return false;
	}

	@Override
	public int compareTo(PackageVersion o) {
			if(this==o) return 0;
			if(name.equals(o.name)) return 0;
			if(partial_name.compareTo(o.partial_name)<0) return -1;
			if(partial_name.compareTo(o.partial_name)>0) return 1;
			if(type.equals("")) return 1;
			if(o.type.equals("")) return -1;
			if(type.compareTo(o.type)<0) return -1;
			if(type.compareTo(o.type)>0) return 1;
			//if(type_version<o.type_version) return -1;
			//if(type_version>o.type_version) return 1;
			
		return 0;
	}
   
   public static void main(String[] args) {
		// TODO Auto-generated method stub
		// for testing: String[] test = {"1.2.3","0.0.5","1.5.3-alpha.2","2.0.0","200.153.99","2.0.0-beta.1","2.0.0-beta.2","1.5.3-alpha"};
		ArrayList<PackageVersion> versions= new ArrayList<PackageVersion>();
		for(int i=0;i<args.length;i++){
			versions.add(new PackageVersion(args[i]));
		}
		Collections.sort(versions);
		System.out.print("[");
      int curr = 0; //control the comma separator
		for(PackageVersion version: versions){
			System.out.print(version.getName());
         if(curr + 1<args.length)System.out.print(", ");
         curr++;//increment num of item printed
		}
		System.out.println("]");
	}

	
	
}
