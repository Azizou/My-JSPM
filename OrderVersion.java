import java.util.ArrayList;
import java.util.Collections;

/**
 * Azizou Ogbone
 */


public class OrderVersion {

	/**
	 * @param args
	 */
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
