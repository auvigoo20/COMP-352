//Written by: Auvigoo Ahmed (40128901)
//COMP 352
//Assignment 3
//Due Date: June 14th 2020
public class Vehicle {
	
	private String brand;
	private String accident;
	
	public Vehicle(String brand, String accident) {
		this.brand = brand;
		this.accident = accident;
	}
	
	public String getBrand() {
		return this.brand;
	}
	
	public String getAccident() {
		return this.accident;
	}
	
	public String toString() {
		return brand +" | "+ accident;
	}
}
