//Written by: Auvigoo Ahmed (40128901)
//COMP 352
//Assignment 3
//Due Date: June 14th 2020
import java.util.*;


public class CVR {
	
	private int threshold;
	private int keyLength;
	private boolean isSequence = false;
	private boolean isHashtable = false;
	private Sequence s1 = null;
	private Hashtable<String,Vehicle> h1 = null;
	private final String[] carBrands = {"Nissan", "Tesla", "Toyota", "Honda", "Acura", "Subaru", "Suzuki", "Mitsubishi", "BMW", "Ford", "Mercedes"};
	private final String[] months = {"01", "02", "03", "04", "05", "06", "07", "08", "09","10", "11", "12"};
	private final String[] days = {"01", "02", "03", "04", "05", "06", "07", "08", "09","10", "11", "12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30"};
	private final int[] years = {1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011,2012,2013,2014,2015,2016,2017,2018,2019,2020};//sie 23
	
	
	public String randomString() {
		Random rn = new Random();
		int index = rn.nextInt(11);
		return carBrands[index];
	}
	
	public String randomDate() {
		Random rn = new Random();
		int monthIndex = rn.nextInt(12);
		String month = months[monthIndex];
		int dayIndex = rn.nextInt(30);
		String day = days[dayIndex];
		int yearIndex = rn.nextInt(23);
		int year = years[yearIndex];
		String date = day +"/"+ month +"/"+ year;
		String no = "N/A";
		int randomDate = rn.nextInt(2);
		String[] dateArray = {date, no};
		return dateArray[randomDate];
	}
	
	public Vehicle randomVehicle() {
		Random rn = new Random();
		int index = rn.nextInt(2);
		return new Vehicle(randomString(), randomDate());
				
	}
	
	
	public CVR() {
		threshold = 0;
		keyLength = 0;
	}
	
	public CVR(int size) {
		Scanner keyIn = new Scanner(System.in);
		System.out.print("Enter the threshold: ");
		int threshold = keyIn.nextInt();
		setThreshold(threshold);
		
		if(size < threshold) {
			isSequence = true;
		}
		else if(size >= threshold) {
			isHashtable = true;
		}
		
		if(isSequence) {
			s1 = new Sequence();
			System.out.println("data structure used is sequence");
		}
		else if(isHashtable) {
			h1 = new Hashtable<String, Vehicle>();
			System.out.println("data structure used is hash table");
		}
	}
	
	
	public void setThreshold(int threshold) {
		if(threshold >= 100 && threshold <= 900000 ) {
			this.threshold = threshold;
		}
		else {
			System.out.println("Invalid threshold value");
		}
			
	}
	
	public void setKeyLength(int keyLength) {
		if(keyLength >= 10 && keyLength <= 17) {
			this.keyLength = keyLength;
		}
		else {
			System.out.println("Key Length out of range");
			System.exit(0);
		}
	}
	
	public ArrayList<String> generate(int numOfKeys){
		ArrayList<String> keys = new ArrayList<String>();
		for(int i = 0 ; i < numOfKeys; i++) {
			String randomKey = getSaltString(keyLength);
			keys.add(randomKey);
			}
		return keys;
	}
	
	public void allKeys() {
		if(isSequence) {
			ArrayList<String> stuff = s1.allKeys();
			for(String s : stuff) {
				System.out.println(s);
			}
		}
		else if(isHashtable) {
			Enumeration<String> e1 = h1.keys();
			ArrayList<String> allKeys = new ArrayList<String>();
			while(e1.hasMoreElements()) {
				allKeys.add(e1.nextElement());
			}
			Collections.sort(allKeys);
			for(String s : allKeys) {
				System.out.println(s);
			}
		}

	}
	
	public void add(String key, Vehicle value) {
		if(isSequence) {
			if(s1.getSize() == threshold) {
				
				System.out.println("The size of CVR has reached its threshold. Please restart the program.");
				System.exit(0);
			}
			s1.add(key, value);
		}
		else if(isHashtable) {
			h1.put(key, value);
		}
	}
	
	public String remove(String key) {
		if(isSequence) {
			String keyRemoved = s1.remove(key);
			return keyRemoved;
		}
		else if(isHashtable) {
			if(h1.size() == threshold) {
				System.out.println("The size of CVR is below its threshold. Please restart the program.");
				System.exit(0);
			}
			Vehicle valueRemoved = h1.remove(key);
			return key+" with value: "+valueRemoved+" removed";
		}
		return null;
	}
	
	public Vehicle getValue(String key) {
		if(isSequence) {
			Vehicle value = s1.getValue(key);
			return value;
		}
		else if(isHashtable) {
			return h1.get(key);
		}
		return null;
	}
	
	public String nextKey(String key) {
		if(isSequence) {
			String nextKey = s1.getNextKey(key);
			return nextKey;
		}
		else if(isHashtable) {
			Enumeration<String> e1 = h1.keys();
			ArrayList<String> allKeys = new ArrayList<String>();
			while(e1.hasMoreElements()) {
				allKeys.add(e1.nextElement());
			}
			int i = 0;
			for(i = 0; i < allKeys.size(); i++) {
				if(allKeys.get(i).equals(key)) {
					break;
				}
			}
			int nextKeyIndex = i+1;
			if(nextKeyIndex == allKeys.size()) {
				return "key doesn't exist";
			}
			return allKeys.get(nextKeyIndex);
			
		}
		return null;
	}
	
	public String prevKey(String key) {
		if(isSequence) {
			String prevKey = s1.getPrevKey(key);
			return prevKey;
		}
		else if(isHashtable) {
			Enumeration<String> e1 = h1.keys();
			ArrayList<String> allKeys = new ArrayList<String>();
			while(e1.hasMoreElements()) {
				allKeys.add(e1.nextElement());
			}
			int i = 0;
			for(i = 0; i < allKeys.size(); i++) {
				if(allKeys.get(i).equals(key)) {
					break;
				}
			}
			int nextKeyIndex = i-1;
			if(nextKeyIndex < 0) {
				return "Key doesn't exist";
			}
			return allKeys.get(nextKeyIndex);
			
		}
		return null;
	}
	
	public String prevAccids(String key) {
		if(isSequence) {
			String accident = s1.getValue(key).getAccident();
			return accident;
		}
		else if (isHashtable) {
			String accident = h1.get(key).getAccident();
			return accident;
		}
		return null;
	}
	
	
	public void display() {
		if(isSequence) {
			s1.display();
		}
		else if(isHashtable) {
			Enumeration<String> allKeys = h1.keys();
			Enumeration<Vehicle> allValues = h1.elements();
			while(allKeys.hasMoreElements() && allValues.hasMoreElements()) {
				System.out.println(allKeys.nextElement() +": " +allValues.nextElement());
			}
		}
	}
	
	
	public static String getSaltString(int n) {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < n) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }
	
	
	
	public static void main(String[] args) {

		Scanner keyIn = new Scanner(System.in);
		System.out.println("****************************");
		System.out.println("*      Welcome to CVR      *");
		System.out.println("****************************\n");
		System.out.print("Enter the number of vehicle identification numbers: ");
		int vin = keyIn.nextInt();
		if(vin < 100) {
			System.out.println("Not enough vehicles. Exiting the program.");
			System.exit(0);
		}
		CVR c1 = new CVR(vin);
		
		System.out.print("Enter the key length of each vin: ");
		int keyLength = keyIn.nextInt();
		c1.setKeyLength(keyLength);
		ArrayList<String> list = c1.generate(vin);
		int i = 0;
		for(String item : list) {
			c1.add(item, c1.randomVehicle());
		}
		c1.display();
		
		boolean menu = true;
		int choice;

		while(menu) {
			System.out.println("*********************************************");
			System.out.println("What would you like to do?");
			System.out.println("\t1. add new key-value pair");
			System.out.println("\t2. remove key");
			System.out.println("\t3. get value of a key");
			System.out.println("\t4. get next key");
			System.out.println("\t5. get previous key");
			System.out.println("\t6. show all keys in lexicographic order");
			System.out.println("\t7. get previous accidents of a key");
			System.out.println("\t0. exit");
			
			choice = keyIn.nextInt();
			
			if(choice == 1) {
				System.out.println("Enter the key of the new vehicle: ");
				String newKey = keyIn.next();
				System.out.println("Enter the brand of the new vehicle: ");
				String newBrand = keyIn.next();
				System.out.println("Enter the accidents of the new vehicle: ");
				String newAccident = keyIn.next();//DON'T PUT SPACE
				c1.add(newKey, new Vehicle(newBrand, newAccident));
				c1.display();
			}
			else if(choice == 2) {
				System.out.println("Enter a key to remove: ");
				String remove = keyIn.next();
				c1.remove(remove);
				c1.display();
			}
			else if(choice == 3) {
				System.out.println("Enter a key to retrieve its value: ");
				String keyValue = keyIn.next();
				System.out.println(c1.getValue(keyValue));
			}
			else if(choice == 4) {
				System.out.println("Enter a key to get its next key: ");
				String nextKey = keyIn.next();
				System.out.println(c1.nextKey(nextKey));
			}
			else if(choice == 5) {
				System.out.println("Enter a key to get its previous key: ");
				String prevKey = keyIn.next();
				System.out.println(c1.prevKey(prevKey));
			}
			else if(choice == 6) {
				c1.allKeys();
			}
			else if(choice == 7) {
				System.out.println("Enter a key to get its previous accidents: ");
				String accidentKey = keyIn.next();
				String accident = c1.prevAccids(accidentKey);
				System.out.println(accident);
			}
			else if(choice == 0) {
				System.out.println("Thank you for using CVR. Goodbye.");
				menu = false;
			}
		}
		

	}

}
