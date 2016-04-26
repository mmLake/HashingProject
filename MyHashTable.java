import java.util.LinkedList;

/*has fields:
 * int numEntry = number of pairs that have been read from the directory 
 * at any given point
 * int collisionCounter = number of pairs that exist in each bracket
 * int tableSize = size of Hash Table (default is 32) no resizing
 * LinkedList<Pair>[] table = Each bracket in the Hash Table is a linked list
 * of pairs */
public class MyHashTable {

	int numEntry;
	int collisionCounter;
	int tableSize = 32;
	LinkedList<Pair>[] table;

	/*Instantiates Hash table with 32 empty LinkedLists*/
	@SuppressWarnings("unchecked")
	public MyHashTable() {
		table = new LinkedList[tableSize];
		for(int i = 0; i < table.length; i++){
			table[i] = new LinkedList<Pair>();
		}
	}

	/*returns hashing value h, and passes key String name
	 * h is always <= tableSize*/
	public int myHashFunction(String name){
		int hash = 13;
		for (int i = 0; i < name.length(); i++) {
			hash = Math.abs(hash * 31 + name.charAt(i));
		}
		return hash % tableSize;
	}

	/*Passes a key, value.
	 *If a key is repeating itself, this method will change the value
	 *of the value for that key
	 *If not, the pair (key, value) will be added to the bracket*/
	public void put(String key, double value){
		int hash = myHashFunction(key);

		for (Pair p : table[hash]) {
			if (p.getKey().equals(key)){
				p.setValue(value);
				return;
			}
		}
		table[hash].add(new Pair(key, value));
		numEntry++;	
	}

	/*Passes string s, returns double d
	 * d = key's value*/
	public double get(String s){
		int hash = myHashFunction(s);

		for (Pair p : table[hash]){
			if (s.equals(p.getKey())){
				return p.getValue();
			}
		}
		throw new NullPointerException();
	}

}
