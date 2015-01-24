import java.util.Scanner;

import structure5.Vector;
import structure5.Association;

/**
 * Main class
 * read input
 * build table
 * print table
 * generate and print out random word
 * @author aidazhumabekova
 *
 */
public class WordGen {

	private Scanner s;
	private Table table;
	String sentence;
	
	/**
	 * Start reading input
	 */
	public static void	main(String[] args) 
	{
		new WordGen();
	}
	
	public WordGen(){
		//start the program
		readInput();
		table=new Table(sentence);
		if(sentence!=null)
			table.makeKeys();
		
	}
	/**
	 *  Get input from the user
	 */
	public  void readInput() 
	{
		//read input
		do{
		//initiate variables 
				s = new Scanner(System.in);
				// for each word on input
				sentence=s.nextLine();
				
				
				
	}while(s.hasNext());
	
	}
}
