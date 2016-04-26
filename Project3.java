import java.io.*;
import java.util.*;

/* contains:
 * hash table totalScores: holds value of every pair in the directory
 * hash table countRepeats: holds key = name, value = number of times key appears
 * in directory
 * LinkedList<String> keys: holds the value of every key in a linked list
 * */
public class Project3 {

	MyHashTable totalScores = new MyHashTable();
	MyHashTable countRepeats = new MyHashTable();
	LinkedList<String> keys = new LinkedList<>(); 

	/*
	 * This program provides the skeleton to process multiple files from a directory
	 * The directory name is provided in args[0] as I call "java Testdir hashingdata"
	  To error check, we pass most of the arguments through try catch, so that if 
	  a value is missing, the key value is not stored. */
	public Project3(String[] args){

		if (args.length < 1) {
			System.out.println("Error: Directory name is missing");
			System.out.println("Usage: java scoreProcess directory_name");
			return;
		}
		File directory = new File(args[0]); // args[0] contains the directory name
		File[] files = directory.listFiles(); // get the list of files from that directory
		System.out.println(args[0]);
		File file;
		Scanner input;

		
		for (int i = 0; i < files.length; i++) {
			try{
				if (files[i].toString().contains(".DS_Store")) {
					continue;
				}
				input = new Scanner(files[i]);
				System.out.println("Current file name: " + files[i].getName());

				String key = null;
				Double value = null;
				while (input.hasNext())
				{
					key = "";
					while (!input.hasNextDouble()){
						key += input.next();
						if (!input.hasNextDouble()) {
							key += " ";
						}
					}
					value = new Double(input.next());
					addCountRepeats(key, value);
				}
				input.close();
			}catch (Exception e){

			}
		}
		printStats();
		printIndivStats();
	}

	/*Returns an ArrayList of the pair(s) that contain the smallest score value*/
	public ArrayList<Pair> minAverages(){
		ArrayList<Pair> minAvgs = new ArrayList<Pair>();

		for (String key : keys){
			if (minAvgs.isEmpty()){
				minAvgs.add(new Pair(key, getAvg(key)));
			}

			else if (getAvg(key) < minAvgs.get(0).getValue()) {
				minAvgs.clear();
				minAvgs.add(new Pair(key, getAvg(key)));
			}

			else if (getAvg(key) == minAvgs.get(0).getValue()){
				minAvgs.add(new Pair(key, getAvg(key)));
			}
		}
		return minAvgs;
	}

	/*Returns an ArrayList of the pair(s) that contain the largest score value*/
	public  ArrayList<Pair> maxAverages(){
		ArrayList<Pair> maxAvgs = new ArrayList<Pair>();

		for (String key : keys){
			if (maxAvgs.isEmpty()){
				maxAvgs.add(new Pair(key, getAvg(key)));
			}

			else if (getAvg(key) > maxAvgs.get(0).getValue()) {
				maxAvgs.clear();
				maxAvgs.add(new Pair(key, getAvg(key)));
			}

			else if (getAvg(key) == maxAvgs.get(0).getValue()){
				maxAvgs.add(new Pair(key, getAvg(key)));
			}
		}

		return maxAvgs;
	}

	/*instantiates hash table totalScores and countRepeats to their appropriate values */
	public void addCountRepeats(String key, double value){

		try{
			totalScores.put(key, value + totalScores.get(key));
			countRepeats.put(key, countRepeats.get(key) + 1.0);
		} catch(NullPointerException e) {
			totalScores.put(key, value);
			countRepeats.put(key, 1.0);
			keys.add(key);		
		}
	}

	/*passes the key String s
	 * returns a value of the average of all of the scores of the key*/
	public double getAvg(String s){
		return totalScores.get(s) / countRepeats.get(s);
	}

	/*Prints out the basic information of a directory;
	 * Prints min and max average, number of collisions, size of table, and 
	 * number of names*/
	public void printStats(){
		System.out.println("# of collisions: " + totalScores.collisionCounter);
		System.out.println("Size of table: " + totalScores.tableSize);
		System.out.println();
		System.out.println("# of Names: " + keys.size());
		System.out.println("Minimum average: " + minAverages().get(0).getValue()); 
		for (int i = 0; i < minAverages().size(); i++){
			System.out.println("  " + minAverages().get(i).getKey());
		}
		System.out.println("Maximum average: " + maxAverages().get(0).getValue()); 
		for (int i = 0; i < maxAverages().size(); i++){
			System.out.println("  " + maxAverages().get(i).getKey());
		}
		System.out.println();
	}

	/*Prompts user in a continuous while loop for entry of a String name that 
	 * exists in the directory
	 * Prints out the key's average and number of scores stored*/
	public void printIndivStats(){
		String name;
		Scanner input = new Scanner(System.in);

		do{
			System.out.print("Enter name: ");
			name = input.nextLine();
			try{
				System.out.println(name + ": Avg: " + getAvg(name) + " # Scores: " + countRepeats.get(name));
			} catch(NullPointerException e){
				System.out.println(name + " not found");
			}
		}while(true);	
	}

	/*Main method that calls the Project3 method
	 * Although Project3 seems like a secondary main method, we do this because the main
	 * method's default is to be static*/
	public static void main(String[] args){
		Project3 proj = new Project3(args);

	}

}
