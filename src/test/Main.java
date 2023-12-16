package test;

import java.util.*;
import java.io.File; // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
	public static int checkNum(boolean[][] visited,List<String> plan, int locationx,int locationy) {
		int sum =0;

		if((locationx+1)< plan.size()&&Character.isDigit(plan.get(locationx+1).charAt(locationy))) {
			sum+=number(visited, plan, locationx+1,locationy);
		}
		if((locationx-1)>=0&&Character.isDigit(plan.get(locationx-1).charAt(locationy))) {
			sum+=number(visited, plan, locationx-1,locationy);
		}
		if((locationy+1)<plan.get(locationx).length()&&Character.isDigit(plan.get(locationx).charAt(locationy+1))) {
			sum+=number(visited, plan, locationx,locationy+1);
		}
		if((locationy-1)>=0&&Character.isDigit(plan.get(locationx).charAt(locationy-1))) {
			sum+=number(visited, plan, locationx,locationy-1);
		}
		if((locationx-1)>=0&&(locationy-1)>=0&&Character.isDigit(plan.get(locationx-1).charAt(locationy-1))) {
			sum+=number(visited, plan, locationx-1,locationy-1);
		}
		if((locationx-1)>=0 && (locationy+1)<plan.get(locationx).length()&&Character.isDigit(plan.get(locationx-1).charAt(locationy+1))) {
			sum+=number(visited, plan, locationx-1,locationy+1);
		}
		if((locationx+1)< plan.size()&& (locationy-1)>=0&&Character.isDigit(plan.get(locationx+1).charAt(locationy-1))) {
			sum+=number(visited, plan, locationx+1,locationy-1);
		}
		if((locationx+1)< plan.size()&& (locationy+1)<plan.get(locationx).length()&&Character.isDigit(plan.get(locationx+1).charAt(locationy+1))) {
			sum+=number(visited, plan, locationx+1,locationy+1);
		}
		return sum;
	}
	public static int number(boolean[][] visited,List<String> plan, int x,int y) {
		if (visited[x][y]== true)
			return 0;
		visited[x][y] = true;
		String sum = ""+plan.get(x).charAt(y);
		int temp =y;
		while(temp>0) {
			temp--;
			if(visited[x][temp] == true)
				break;
			visited[x][temp] = true;
			if(Character.isDigit(plan.get(x).charAt(temp))) {
				sum =plan.get(x).charAt(temp)+sum ;
			}
			else
				break;
			
		}
		temp = y;
		while(temp<plan.get(x).length()-1) {
			temp++;
			if(visited[x][temp] == true)
				break;
			visited[x][temp] = true;
			if(Character.isDigit(plan.get(x).charAt(temp))) {
				sum =sum +plan.get(x).charAt(temp) ;
			}
			else
				break;
		}
	
		return Integer.parseInt(sum);
		
	}
	
	public static void main(String[] args) {
		int sum = 0;
		try {
			File myObj = new File("input.txt");
			Scanner myReader = new Scanner(myObj);
			List<String> schematic = new ArrayList<String>();
			while (myReader.hasNextLine()) {
				schematic.add(myReader.nextLine());
			}
			myReader.close();
			boolean look[][] = new boolean[schematic.size()][schematic.get(0).length()];
			Pattern symbol = Pattern.compile("\\!|\\@|\\#|\\$|\\%|\\^|\\&|\\*|\\+|\\-|\\=|\\/");
			Matcher matcher;
			for(int i=0; i< schematic.size(); i++) {
				matcher = symbol.matcher(schematic.get(i));
				while(matcher.find()) {
				
					sum+=checkNum(look, schematic, i, matcher.start());
					
				}
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		
		System.out.println(sum);
	}

}
