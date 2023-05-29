package lexicon;


import java.awt.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class HangmanLexicon implements LexiconInterface {
	ArrayList<String> text= new ArrayList<>();
	int wordCount, randomNumber, indexWordText;
	String filename = "D:\\Универ_2\\OOP\\Лаба_раб_4\\LAB4\\src\\lexicon.txt";
	public HangmanLexicon(String testString) {
	}

	@Override
	public int getWordCount() {
		wordCount=0;
		File file1 = new File(filename);

		try (Scanner scanner = new Scanner(file1)) {
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] words = line.split("\\s+"); // Разделение строки на слова по пробелам
				wordCount += words.length;
				for(String item: words){
					text.add(item);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("!getWordCount()! = "+wordCount+ "  text.size = "+text.get(1));
		return wordCount;
	}

	@Override
	public String getWord(int index) {
		System.out.println("n-th word from lexicon = " + text.get(index));
		return text.get(index);
	}

	@Override
	public String getRandomWord() {
		randomNumber=(int)(Math.random()*83);
		System.out.println("Random word from lexicon = " + text.get(randomNumber));
		return text.get(randomNumber).toLowerCase();
	}


}
