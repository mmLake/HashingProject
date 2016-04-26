
/*The Pair class is comprised of a constructor, a getter and a setter for the values
 *instantiated in constructor*/
public class Pair {

	private String key;
	private double value;
	
	/*constructor; declares that a pair only has a string and a double */
	public Pair(String key, double value) {
		this.key = key;
		this.value = value;
	}
	
	public String getKey() {
		return this.key;
	}
	
	public double setValue(double value) {
		return this.value = value;
	}
	
	public double getValue() {
		return this.value;
	}
}
