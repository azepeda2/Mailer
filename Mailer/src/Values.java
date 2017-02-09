import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class Values {
	
	private ArrayList<HashMap<String, String>> arraylist;
	
	public Values() {
		this.arraylist = new ArrayList<HashMap<String, String>>();
	}
	public void createHashMap(String file) {
		
		HashMap<String, String> keyvals = new HashMap<String, String>();
		Scanner scan = null;

		try {
			scan = new Scanner(new FileReader(file)); //change once file input is added
			
			while(scan.hasNext()) {
				String line = scan.nextLine();
				if(line.trim().equals("") && (!keyvals.isEmpty())) { //checks if line is empty indicating new hash map
						arraylist.add(keyvals);                      //and adds the HashMap only if its not empty
						keyvals = new HashMap<String, String>();
										
				} else if(line.contains("=")) { 
					String[] split = line.split("="); 
					String key = split[0].trim();
					String val = split[1].trim();
					keyvals.put(key, val);
				} /*else {
					System.out.println("The address file is not formatted correctly, program is now quiting");
					System.exit(0);
				}*/
			}
			
			if(!keyvals.isEmpty()) { //adds the last HashMap to the arraylist
				arraylist.add(keyvals);
			}
					

		}  catch (FileNotFoundException e) {
			System.out.println("File Not Found! Program is now quitting.");
			System.exit(0);
		}
		
		
	}
	
	public ArrayList<HashMap<String, String>> getHashArray() {
		return this.arraylist;
	}

}
