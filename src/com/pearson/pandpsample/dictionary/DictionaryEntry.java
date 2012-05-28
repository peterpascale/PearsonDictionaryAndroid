package com.pearson.pandpsample.dictionary;

/**
 * Simple java bean/DTO representing a Dictionary Entry.
 * 
 * @author Peter Pascale
 */
public class DictionaryEntry {

	private final String word;
	private final String definition;
	
	public DictionaryEntry(String word, String definition) {
		this.word = word;
		this.definition = definition;
	}
	
	public String getWord() {
		return word;
	}
	
	public String getDefinition() {
		return definition;
	}
	
	public String toString() {
		return word + ": " + definition;
	}
}

