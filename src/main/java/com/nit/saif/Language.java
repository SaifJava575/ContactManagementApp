package com.nit.saif;

import java.util.Locale;
import java.util.Scanner;

public class Language {

	public static void main(String[] args) {
		
		
		 String[] countryCodes = Locale.getISOLanguages();
		 
		 for (String countryCode : countryCodes) {
	            Locale locale = new Locale("", countryCode);
	            String code = locale.getLanguage();
	            String name = locale.getDisplayLanguage();
	             
	            System.out.printf("[%s] %s\n", code, name);
	        }
		

	}

}
