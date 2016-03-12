package org.dragon.robot.common;

import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

public class HedgeAlgebra {
	private static final String DATA_FILE_NAME = "hedge.conf";
	private static final String VERY_DUST = "VERY_DUST";
	private static final String MORE_DUST = "MORE_DUST";
	private static final String POSSIBLE_DUST = "POSSIBLE_DUST";
	private static final String LESS_DUST = "LESS_DUST";
	private static final String LESS_CLEAN = "LESS_CLEAN";
	private static final String POSSIBLE_CLEAN = "POSSIBLE_CLEAN";
	private static final String MORE_CLEAN = "MORE_CLEAN";
	private static final String VERY_CLEAN = "VERY_CLEAN";

	String[] langValueKey = { VERY_DUST, MORE_DUST, POSSIBLE_DUST, LESS_DUST,
			LESS_CLEAN, POSSIBLE_CLEAN, MORE_CLEAN, VERY_CLEAN };

	public static String[] langValuesVi = { "Rất bẩn", "Hơn bẩn", "Khả năng bẩn", "Ít bẩn",
			"Ít sạch", "Khả năng sạch", "Hơn sạch", "Rất sạch" };

	public static String[] langValueEn = { "Very dust", "More dust", "Possible dust",
			"Less dust", "Less clean", "Possible clean", "More clean",
			"Very clean" };

	private int veryDust = 85;
	private int moreDust = 75;
	private int possibleDust = 65;
	private int lessDust = 55;
	private int lessClean = 45;
	private int possibleClean = 35;
	private int moreClean = 25;
	private int veryClean = 15;

	private HashMap<String, Integer> dustLanguageValue;

	public HedgeAlgebra() {
		Properties prop = new Properties();
		dustLanguageValue = new HashMap<>();
		try {
			prop.load(ProcessUtil.getConfigInputStream(DATA_FILE_NAME));
			this.veryDust = parseStringValue(prop.getProperty(VERY_DUST),
					this.veryDust);
			this.moreDust = parseStringValue(prop.getProperty(MORE_DUST),
					this.moreDust);
			this.possibleDust = parseStringValue(
					prop.getProperty(POSSIBLE_DUST), this.possibleDust);
			this.lessDust = parseStringValue(prop.getProperty(LESS_DUST),
					this.lessDust);
			this.lessClean = parseStringValue(prop.getProperty(LESS_CLEAN),
					this.lessClean);
			this.possibleClean = parseStringValue(
					prop.getProperty(POSSIBLE_CLEAN), this.possibleClean);
			this.moreClean = parseStringValue(prop.getProperty(MORE_CLEAN),
					this.moreClean);
			this.veryClean = parseStringValue(prop.getProperty(VERY_CLEAN),
					this.veryClean);

			dustLanguageValue.put(VERY_DUST, this.veryDust);
			dustLanguageValue.put(MORE_DUST, this.moreDust);
			dustLanguageValue.put(POSSIBLE_DUST, this.possibleDust);
			dustLanguageValue.put(LESS_DUST, this.lessDust);
			dustLanguageValue.put(LESS_CLEAN, this.lessClean);
			dustLanguageValue.put(POSSIBLE_CLEAN, this.possibleClean);
			dustLanguageValue.put(MORE_CLEAN, this.moreClean);
			dustLanguageValue.put(VERY_CLEAN, this.veryClean);
		} catch (IOException e) {
			// DO NOTHING
		}
	}

	public int getLanguageValue(String key) {
		String langKey = key;
		for (int i = 0; i < langValueEn.length; i++) {
			if (langValueEn[i].equals(key)) {
				langKey = langValueKey[i];
				break;
			}
		}
		if (langKey.equals(key)) {
			for (int i = 0; i < langValuesVi.length; i++) {
				if (langValuesVi[i].equals(key)) {
					langKey = langValueKey[i];
					break;
				}
			}
		}
		if (langKey.equals(key)) {
			return Integer.parseInt(key);
		}
		return this.dustLanguageValue.get(langKey);
	}

	private int parseStringValue(String value, int defaultValue) {
		try {
			return Integer.parseInt(value);
		} catch (Exception e) {
			return defaultValue;
		}
	}

	public int getVeryDust() {
		return veryDust;
	}

	public void setVeryDust(int veryDust) {
		this.veryDust = veryDust;
	}

	public int getMoreDust() {
		return moreDust;
	}

	public void setMoreDust(int moreDust) {
		this.moreDust = moreDust;
	}

	public int getPossibleDust() {
		return possibleDust;
	}

	public void setPossibleDust(int possibleDust) {
		this.possibleDust = possibleDust;
	}

	public int getLessDust() {
		return lessDust;
	}

	public void setLessDust(int lessDust) {
		this.lessDust = lessDust;
	}

	public int getLessClean() {
		return lessClean;
	}

	public void setLessClean(int lessClean) {
		this.lessClean = lessClean;
	}

	public int getPossibleClean() {
		return possibleClean;
	}

	public void setPossibleClean(int possibleClean) {
		this.possibleClean = possibleClean;
	}

	public int getMoreClean() {
		return moreClean;
	}

	public void setMoreClean(int moreClean) {
		this.moreClean = moreClean;
	}

	public int getVeryClean() {
		return veryClean;
	}

	public void setVeryClean(int veryClean) {
		this.veryClean = veryClean;
	}

}
