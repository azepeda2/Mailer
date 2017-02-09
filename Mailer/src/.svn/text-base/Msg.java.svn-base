import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class Msg {

	private ArrayList<StringBuilder> msgparts;
	private ArrayList<String> variables;

	public Msg() {
		this.msgparts = new ArrayList<StringBuilder>();;
		this.variables = new ArrayList<String>();
	}

	public void fillArray(String file) {

		StringBuilder tempString = new StringBuilder();
		String variableName = "";
		Scanner scan = null;

		try {
			scan = new Scanner(new FileReader(file)); //change once file input is added

			while(scan.hasNext()) {
				String word = scan.next();
				String check = word.substring(0,1);
				if(check.compareTo("$") != 0) {	 //word doesn't start with '$'
					tempString.append(word + " ");
				} else { 						//"word starts with '$'
					msgparts.add(tempString);
					tempString =  new StringBuilder();
					variableName = word.substring(1);
					variableName = variableName.replaceAll("\\W", "");
					variables.add(variableName);
				}
			}

			msgparts.add(tempString);

		}  catch (FileNotFoundException e) {
			System.out.println("File Not Found! Program is now quitting.");
			System.exit(0);
		}

	}

	public StringBuilder compileMsg(HashMap<String, String> vals) {
		int msgCount = 0;
		int varCount = 0;
		String varCheck = "";
		StringBuilder compMsg = new StringBuilder();
		while(msgCount < msgparts.size() || varCount < variables.size()) {
			if(msgCount < msgparts.size()) {
				if(varCount < variables.size()) {
					varCheck = vals.get(variables.get(varCount));
				}
				if(varCheck == null) {
					System.out.println("Variable not addressed in the address file. Program now quitting");
					System.exit(0);
				} else {
					StringBuilder msg = msgparts.get(msgCount);
					if(msg.toString() == "") { //the case that there is a variable before
						varCheck = vals.get(variables.get(varCount));
						if(varCheck == null) {
							System.out.println("Variable not addressed in the address file. Program now quitting");
							System.exit(0);
						}
						compMsg.append(vals.get((variables.get(varCount))) + " ");
						varCount++;
					} else {
						compMsg.append(msgparts.get(msgCount));
						msgCount++;
					}
				}
				if(varCount < variables.size()) {
					varCheck = vals.get(variables.get(varCount));
					if(varCheck != null) {
						compMsg.append(vals.get((variables.get(varCount))) + " ");
						varCount++;
					} else {
						System.out.println("Variable not addressed in the address file. Program now quitting");
						System.exit(0);
					}
				}
			}
		}
		return compMsg;
		}


		public void printMsgArray(ArrayList<StringBuilder> array) {
			for(int i = 0; i < array.size(); i++) {
				System.out.println(array.get(i));
			}
		}

		public void printVarArray(ArrayList<String> array) {
			for(int i = 0; i < array.size(); i++) {
				System.out.println(array.get(i));
			}
		}

		public ArrayList<StringBuilder> getMsgList() {
			return this.msgparts;
		}

		public ArrayList<String> getVariables() {
			return this.variables;
		}


	}
