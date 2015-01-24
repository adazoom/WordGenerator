/**
 * Keeps track of each character that appeared after 
 * the given 2-character pair, along with the number of times it appeared
 * @author aidazhumabekova
 *
 */
import structure5.Vector;
import structure5.Association;

public class FrequencyList {
	private Vector<Association<Character, Integer>> vector;
	private char[] str;
	private char afterCh;
	protected Vector<Character> key;
	protected Vector<Integer> value;

	public FrequencyList(String sentence) {
		// initialize
		str = sentence.toLowerCase().toCharArray();
		vector = new Vector<Association<Character, Integer>>(18);
	}
	/**
	 * Vector getter
	 * @return
	 */
	public Vector<Association<Character, Integer>> getVector() {
		return vector;
	}

	/**
	 * Method to get each character in the word find given pair of char in the
	 * sentence and calc freq of each letter
	 */
	public void calcFreq(char[] chars) {
		char x = chars[0];
		char y = chars[1];
		for (int i = 0; i < str.length - 1; i++) {
			if (str[i] == x && str[i + 1] == y) {
				// if its not null get the char after specified pair
				// check wheteher this key is in the vector
				try {
					if (!inside(str[i + 2])) {
						afterCh = str[i + 2];
						Association<Character, Integer> newChar = new Association<Character, Integer>(
								afterCh, 1);
						vector.add(newChar);
					} else {
						for (Association<Character, Integer> same : vector) {
							if (same.getKey() == str[i + 2]) {
								same.setValue(same.getValue() + 1);
							}
						}
					}

				} catch (ArrayIndexOutOfBoundsException e) {
				}

			}
		}
	}
	/**
	 * Method to make a String output
	 */

	public String toString() {
		String output = "";
		for (Association<Character, Integer> a : vector) {

			String keyIs = String.valueOf(a.getKey());
			String valueIs = a.getValue().toString();
			output += keyIs + " = " + valueIs + " ";

		}

		return output;
	}
	/**
	 * Method to check whether the char is inside the vector
	 * @param cur
	 * @return
	 */

	public boolean inside(char cur) {
		String curS = String.valueOf(cur);
		for (Association<Character, Integer> a : vector) {
			String aS = String.valueOf(a.getKey());
			if (curS.equals(aS)) {
				return true;
			}
		}
		return false;
	}

}
