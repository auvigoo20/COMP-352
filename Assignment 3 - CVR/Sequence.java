//Written by: Auvigoo Ahmed (40128901)
//COMP 352
//Assignment 3
//Due Date: June 14th 2020

import java.util.*;
public class Sequence {
	
	class Node{
		
		String key;
		Vehicle value;
		Node next;
		Node prev;
		
		public Node() {
			key = null;
			value = null;
			next = null;
			prev = null;
		}
		
		public Node(String key, Vehicle value) {
			this.key = key;
			this.value = value;
			next = null;
			prev = null;
		}

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public Vehicle getValue() {
			return value;
		}

		public void setValue(Vehicle value) {
			this.value = value;
		}

		public Node getNext() {
			return next;
		}

		public void setNext(Node next) {
			this.next = next;
		}

		public Node getPrev() {
			return prev;
		}

		public void setPrev(Node prev) {
			this.prev = prev;
		}
	}
	
	private Node head;
	private Node last;
	private int size;
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public Sequence() {
		head = null;
		last = null;
		size = 0;
	}
	public void add(String key, Vehicle value) {

		Node entry = new Node(key, value);
		if(isEmpty()) {
			head = entry;
			last = entry;
			size++;
		}
		else {
			entry.next = null;
			entry.prev = last;
			last.next = entry;
			last = entry;
			size++;
		}
	}
	
	public String remove(String key) {
		Node temp = head;
		Node lastTemp = last;
		
		//if key is at the head
		if(temp.key.equals(key)) {
			Vehicle value = temp.value;
			head = temp.next;
			size--;
			temp = null;
			return key+ "with value: "+ value+" removed";
		}
		if(lastTemp.key.equals(key)) {
			Vehicle value = lastTemp.value;
			last = lastTemp.prev;
			size--;
			lastTemp = null;
			return key+ "with value: "+ value+" removed";
		}
		
		Vehicle value = null;
		while(temp != null) {
			if(temp.key.equals(key)) {
				value = temp.value;
				temp.prev.next = temp.next;
				temp.next.prev = temp.prev;
			}
			temp = temp.next;
		}
		size--;
		temp = null;
		return key+ "with value: "+ value;
	}
	
	public Vehicle getValue(String key) {
		Node temp = head;
		while(temp != null) {
			if(temp.key.equals(key)) {
				return temp.value;
			}
			temp = temp.next;
		}
		return null;
	}
	
	public String getNextKey(String key) {
		Node temp = head;
		while(temp.next != null) {
			if(temp.key.equals(key)) {
				return temp.next.key;
			}
			temp = temp.next;
		}
		System.out.println("Key is not found");
		return null;
	}
	public String getPrevKey(String key) {
		Node temp = last;
		while(temp != head) {
			if(temp.key.equals(key)) {
				return temp.prev.key;
			}
			temp = temp.prev;
		}
		return null;
	}
	
	public int getSize() {
		return size;
	}
	
	public ArrayList<String> allKeys(){
		ArrayList<String> keyList = new ArrayList<String>();
		Node temp = head;
		while(temp != null) {
			keyList.add(temp.key);
			temp = temp.next;
		}
		Collections.sort(keyList);
		return keyList;
	}
	
	public void display() {
		Node temp = head;
		while(temp != null) {
			System.out.println(temp.key + " : "+temp.value.getBrand()+ " | "+ temp.value.getAccident());
			temp = temp.next;
		}
		System.out.println("X");
	}
	

	
//	public static void main(String[] args) {
//		Sequence s = new Sequence();
//		s.add("fdhskfjh", 5);
//		s.add("sdfsjh", 6);
//		s.add("oiupnkfjh", 7);
//		s.add("qwqchskfjh", 8);
//		s.display();
//		s.remove("oiupnkfjh");
//		s.display();
//		System.out.println(s.getPrevKey("sdfsjh"));
//	}
	
	
}
