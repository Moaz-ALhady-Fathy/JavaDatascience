package labExercise2;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class MainClass {

	public static void main(String[] args) {
		String string1 = "Hello World";
		String string2 = "This is a test";
		String betterString;
		
		System.out.println("-- the first string is the better string --");
		betterString = betterString(string1, string2, (s1,s2)-> true);
		System.out.println("Between \""+string1+"\", \""+string2 +"\", The better string is: "+ betterString);
		
		System.out.println("-- the longer string is the better string --");
		betterString = betterString(string1, string2, (s1,s2)-> s1.length()>s2.length());
		System.out.println("Between \""+string1+"\", \""+string2 +"\", The better string is: "+ betterString);
		
		System.out.println("-- Chech if a string only contain alphabet --");
		if (checkString(string1, Character::isLetter)) {
			System.out.println("The string: "+ string1+ " cointains alphabet only");
		}else {
			System.out.println("The string \""+ string1+ "\" is alphanumeric");
		}
		
		
		
		
	}
	
	public static String betterString(String string1, String string2, BiPredicate<String,String> p) {
		if(p.test(string1, string2)) {
			return string1;
		} else {
			return string2;
		}
	}
	
	public static boolean checkString(String string, Predicate<Character> p) {
		int l = string.length();
		
		for (int i=0; i<l; i++) {
			char c = string.charAt(i);
			if (p.test(c)){
				continue;
			}else {
				return false;
			}
		}
		
		return true;
	}
}
