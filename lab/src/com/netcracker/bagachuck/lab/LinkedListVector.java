package com.netcracker.bagachuck.lab;

import java.io.*;

public class LinkedListVector implements Vector, Cloneable,Serializable {

	// head
	private Link first;

	private int size;

	public int getSize() {
		return size;
	}

	public LinkedListVector() {
		first = null;
	}

	public void add(double val) {
		Link link = new Link(val);
		if (first == null) {
			size = 1;
			first = link;
			first.next = first;
			first.prev = first;
		} else {
			link.next = first;
			link.prev = first.prev;
			first.prev.next = link;
			first.prev = link;
			size++;
		}
	}

	public void setElement(double val, int index) {
		Link link = first;
		for (int i = 0; i < index; i++) {
			link = link.next;
		}
		link.value = val;
	}

	public double getElement(int index) {
		Link link = first;
		for (int i = 0; i < index; i++) {
			link = link.next;
		}
		return link.value;
	}

	public void print() {
		Link current = first;
		for (int i = 0; i < this.getSize(); i++) {
			System.out.println(current.value);
			current = current.next;
		}
	}

	public void delete(int index) {
		Link link = first;
		if (index == 0) {
			first = link.next;
			link.prev.next = link.next;
			link.next.prev = link.prev;
			size--;

		}

		for (int i = 0; i < index; i++) {
			link = link.next;
		}
		link.prev.next = link.next;
		link.next.prev = link.prev;
		size--;
	}

	public void delete() {
		if (first == null) {
			System.out.println("������ ����!!!");
		} else {

			first.prev = first.prev.prev;
			first.prev.prev.next = first;
			size--;
		}
	}

	public void createMass(double mass[]) {
		this.first = null;
		this.size = 0;

		for (int i = 0; i < mass.length; i++) {
			this.add(mass[i]);
		}
	}

	public void addVector(Vector otherVector) {
		if (first == null) {
			for (int i = 0; i < otherVector.getSize(); i++) {
				this.setElement(otherVector.getElement(i), i);
			}
			if (this.getSize() != otherVector.getSize()) {
				System.out.print("������!����� �� �����!");
			}
			for (int i = 0; i < this.getSize(); i++) {
				this.setElement(
						(otherVector.getElement(i) + this.getElement(i)), i);
			}
		}
	}

	public void scalarMult(int number) {
		for (int i = 0; i < this.getSize(); i++) {
			this.setElement(this.getElement(i) * number, i);
		}
	}

	public static void bubbleSort(LinkedListVector vector) {
		System.out.println("�������� ������ : ");
		for (int i = 0; i < vector.getSize(); i++) {
			System.out.print(vector.getElement(i) + " ");
		}
		for (int i = 0; i < vector.getSize(); i++) {
			for (int j = 0; j < vector.getSize() - 1; j++) {
				if (vector.getElement(j) > vector.getElement(j + 1)) {
					double temp = vector.getElement(j);
					vector.setElement(vector.getElement(j + 1), j);
					vector.setElement(temp, j + 1);
				}
			}
		}
		System.out.println("Sorting vector :");
		for (int i = 0; i < vector.getSize(); i++) {
			System.out.print(vector.getElement(i) + " ");
		}
		System.out.println("");
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (other == null) {
			return false;
		}
		if (!(other instanceof Vector)) {
			return false;
		}

		if (this.getSize() != ((Vector) other).getSize()) {
			return false;
		}
		for (int i = 0; i < this.getSize(); i++) {
			if (this.getElement(i) != ((Vector) other).getElement(i)) {
				return false;
			}

		}

		return true;
	}

	@Override
	public int hashCode() {
		int result = 0;
		for (int i = 0; i < getSize(); i++) {
			long bits = Double.doubleToRawLongBits(getElement(i));
			result ^= ((int) (bits & 0x00000000FFFFFFFFL))
					^ ((int) ((bits & 0xFFFFFFFF00000000L) >> 32));
		}
		return result;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("[");

		for (int i = 0; i < this.getSize(); i++) {
			if (i == this.getSize() - 1) {
				str.append(getElement(i));
				break;
			}
			str.append(getElement(i) + ", ");
		}

		str.append("]");

		return str.toString();
	}

	@Override
	public void createVector(Vector otherVector) {
		for (int i = 0; i < otherVector.getSize(); i++) {
			this.setElement(getElement(i), i);

		}
	}

	public LinkedListVector clone() {
		LinkedListVector vectorObject = null;
		try {
			vectorObject = (LinkedListVector) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		vectorObject.size = 0;
		vectorObject.first = null;

		for (int i = 0; i < this.size; i++) {
			vectorObject.add(this.getElement(i));
		}
		return vectorObject;
	}

	class Link implements Serializable {
		public double value;
		Link next;
		Link prev;

		public Link(double val) {
			value = val;
			prev = this;
			next = this;
		}

	}

	public static void main(String[] args) {
		LinkedListVector myList = new LinkedListVector();
		
		myList.add(0.1);
		myList.add(1.1);
		 myList.print(); 
		 System.out.println("����� ������: " + myList.getSize());
		 try {
				FileOutputStream fileStream = new FileOutputStream("MyVector3.ser");
				ObjectOutputStream os = new ObjectOutputStream(fileStream);
				os.writeObject(myList);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			try {
				ObjectInputStream is = new ObjectInputStream(new FileInputStream(
						"MyVector3.ser"));
				LinkedListVector  myListRestore = (LinkedListVector) is.readObject();
				 myListRestore.print();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}


