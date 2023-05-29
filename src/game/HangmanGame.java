package game;


import junit.TestLexicon;
import lexicon.HangmanLexicon;

import java.util.*;
import java.util.HashSet;
import java.util.Set;

public class HangmanGame implements HangmanGameInterface {
	public String word ;
	public String letterTextField;
	public int guessesLeft=0, gameWon;
	public int incorrectGuesses, gameLost;
	int correctGuesses;
	private Set<Character> guessedCharacters = new HashSet<>();

	public HangmanGame(HangmanLexicon hangmanLexicon) {
	}

	public HangmanGame(TestLexicon testLexicon) {
		this.word = "";
	}


	@Override
	public boolean guess(char letter) {

		if(word.contains(Character.toString(letter))) {
			char guess = Character.toUpperCase(letter);

			if (word.contains(Character.toString(guess)) || guessedCharacters.contains(guess)) {
				return false;
			}

			guessedCharacters.add(guess);
			// Assuming you have the "word" variable available within the class
			return word.toUpperCase().contains(Character.toString(guess));
		}
		return false;
	}

	private ArrayList<Character> guessedLetters = new ArrayList<>();

	@Override
	public String getPartlyGuessedWord() {
		StringBuilder partlyGuessedWord = new StringBuilder();

		for (int i = 0; i < word.length(); i++) {
			char letter = word.charAt(i);
			char letter1 = letterTextField != null ? letterTextField.charAt(letterTextField.length()-1) : '-';

			if (letter == letter1) {
				partlyGuessedWord.append(Character.toUpperCase(letter));
				guessedLetters.add(letter); // Добавляем отгаданную букву в список
			} else if (guessedLetters.contains(letter)) {
				partlyGuessedWord.append(Character.toUpperCase(letter)); // Если буква уже отгадана ранее, добавляем её
			} else {
				partlyGuessedWord.append('-');
			}
		}
		return partlyGuessedWord.toString();
	}

	@Override
	public String getHangmanWord() {
		return word.toUpperCase();
	}

	@Override
	public String getGuessedLetters() {
		StringBuilder guessedLetters = new StringBuilder();
		for (char letter : guessedCharacters) {
			guessedLetters.append(letter);
		}
		return guessedLetters.toString();
	}

	@Override
	public boolean isGameLost() {
		if (gameLost > 9) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isGameWon() {
		char[] result=word.toCharArray();
		HashSet<Character> set=new HashSet<>();
		for(char item:result){
			if(!set.contains(item)){
				set.add(item);
			}
		}
		if (gameWon ==set.size() )
		{return true;}

		return false;
	}

	@Override
	public int getGuessesLeft() {
		guessesLeft=9-gameLost;
		System.out.println("Left attempt:"+guessesLeft);
		return guessesLeft;
	}

	@Override
	public int getIncorrectGuesses() {
		System.out.println("incorrect guesses = "+gameLost);
		return incorrectGuesses = gameLost;
	}

	@Override
	public int getCorrectGuesses() {
		System.out.println("correct guesses = "+guessedCharacters.size());
		return guessedCharacters.size();
	}
}
