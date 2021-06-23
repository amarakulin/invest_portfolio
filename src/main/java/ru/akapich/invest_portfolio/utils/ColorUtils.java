package ru.akapich.invest_portfolio.utils;

import org.springframework.stereotype.Component;

import java.awt.*;

/**
 * External methods to work with colors
 *
 * @author Aleksandr Marakulin
 **/

@Component
public class ColorUtils {

	private static final int HEX_MASK = 0x1000000;

	public String getRandomColorString(){
		Color randomColor = new Color((int)(Math.random() * HEX_MASK));

		String hex = "#"+Integer.toHexString(randomColor.getRGB()).substring(2).toUpperCase();
		return hex;
	}

}
