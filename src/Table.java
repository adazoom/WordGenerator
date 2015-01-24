/**
 * Creates table of frequencies
 * @author aidazhumabekova
 *
 */
import structure5.Vector;
import structure5.Association;

import java.util.Arrays;
import java.util.Random;

public class Table {

	private Vector<Association<char[], FrequencyList>> vectorFreq;
	private String sent;
	private char[] newPair;
	private String genText;
	private char[] firstRandomPair;
	
	public Table(String sentence) {
		// init
		sent = sentence;

		vectorFreq = new Vector<Association<char[], FrequencyList>>(18);
		makeKeys();
		printVector();
		getRandomKey();

	}

	/**
	 * Method to get the sentence and split it to the charachters of two
	 */
	public void makeKeys() {
		// iterate through the sentence and get each pair of charachters
		char[] charSent = sent.toLowerCase().toCharArray();
		for (int i = 0; i < charSent.length - 1; i++) {
			newPair = new char[2];
			newPair[0] = charSent[i];
			newPair[1] = charSent[i + 1];
			// if the pair is unique add it to the vector
			if (!inside(newPair)) {
				FrequencyList list = new FrequencyList(sent);
				list.calcFreq(newPair);
				Association<char[], FrequencyList> newAs = new Association<char[], FrequencyList>(
						newPair, list);
				vectorFreq.add(newAs);
			}

		}
	}
	/**
	 * Method to check whether the char is inside the vector
	 * @param cur
	 * @return
	 */


	public boolean inside(char[] cur) {
		String curS = new String(cur);
		for (Association<char[], FrequencyList> a : vectorFreq) {
			String aS = new String(a.getKey());
			if (curS.equals(aS)) {
				return true;
			}
		}
		return false;
	}
/**
 * Method that prints out the table
 */
	public void printVector() {
		for (Association<char[], FrequencyList> a : vectorFreq) {
			String key = new String(a.getKey());
			System.out.println(key + " --> " + a.getValue().toString());
		}
	}
	/**
	 * Method to concatenate generated strings
	 * @param ranLetter
	 * @return
	 */
	public String genText(String ranLetter){
		//choose a random key from the table
		if(genText==null){
		genText=new String(firstRandomPair);
		}
		genText= genText+ranLetter;
		//System.out.println("genText is: "+genText);
		findPair(genText);
		return genText;
	}
	/**
	 * Generates random letter to concatenate generated text with the random letter from the frequency list of last pair in the text
	 * @param random
	 * @return
	 */
	public String calcFrequent(Association<char[], FrequencyList> random){
		Vector<String> list= new Vector<String>();
		//calculate frequency of charachters in the list
		Vector<Association<Character, Integer>> v=random.getValue().getVector();
		//iterate through list and for each value and store each key as many times as there are values for each key
		for (Association<Character, Integer> a : v) {
			String keyIs = String.valueOf(a.getKey());
			Integer valueIs = a.getValue();
			for(int i=0; i<valueIs; i++){
				list.add(keyIs);
			}
		}
		//System.out.println("vector of strings is: "+list.toString());
		String randomLetter="";
		//choose random string from list
		if(list.size()==0){
			//if there is no matched letter for the pair then finish the text
			genText(".");
			System.out.println("Generated text is:"+genText);
		}
		else{
		int cap=list.size();
		//System.out.println("size is: "+cap);
		Random rand = new Random();
	    int randomNum = rand.nextInt(cap);
	    //System.out.println("random is: "+randomNum);
	    randomLetter=list.get(randomNum);
	    //System.out.println("random choosed letter is: "+ randomLetter);
	    //concatenate random letter with the generated text
	    genText(randomLetter);
		}
		return randomLetter;
	}
	/**
	 * Generates random pair to start with
	 * @return
	 */
	public void getRandomKey(){
		int cap=vectorFreq.size();
		Random rand = new Random();
	    int randomNum = rand.nextInt(cap);
		Association<char[], FrequencyList> randomAs=vectorFreq.get(randomNum);
		//set the first random pair to start with
		firstRandomPair=randomAs.getKey();
		//generate random letter to add
		calcFrequent(randomAs);
	}

	/**
	 * Find the pair of generated text in the table
	 */
	public void findPair(String value){
		String lastTwo="";
		//get last two letters of generated text
		if (value !=null && value.length()>=2){  
			  lastTwo =value.substring(value.length()-2);
			}
		//find them in the table
		//System.out.println("last two letters of genText are: "+lastTwo);
		for (Association<char[], FrequencyList> a : vectorFreq) {
			String key = new String(a.getKey());
			
			if(key.equals(lastTwo)){
				//System.out.println("matched key is: "+key);
				//if found generate a random letter
				calcFrequent(a);
			}
	}
	}
}
