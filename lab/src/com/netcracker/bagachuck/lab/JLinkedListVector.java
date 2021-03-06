package com.netcracker.bagachuck.lab;

import java.io.*;
import java.util.LinkedList;

public class JLinkedListVector implements Vector, Cloneable, Serializable {
	private LinkedList<Double> list;

	public JLinkedListVector(int size) {
		list = new LinkedList<Double>();
		for (int i = 0; i < size; i++) {
			this.list.add(i,null);
		}

	}

	public JLinkedListVector(double... elements) {
		list = new LinkedList<Double>();
		for (int i = 0; i < elements.length; i++) {
			this.list.add(i, elements[i]);

		}
	}

	@Override
	public int getSize() {
		return this.list.size();
	}

	@Override
	public void setElement(double val, int index) {
		this.list.set(index, val);

	}

	@Override
	public double getElement(int index) {

		return this.list.get(index);
	}
	
	 public void print() {
	        for (int i = 0; i < this.getSize(); i++) {
	            System.out.println(this.list.get(i));
	        }
	    }

	@Override
	public void createMass(double mass[]) {
		if (this.getSize() == mass.length) {
			for (int i = 0; i < this.getSize(); i++)
				this.setElement(mass[i], i);
		} else {
			System.out.println("Worning! length not equal!");
		}
	}

	@Override
	public void scalarMult(int number) {
		for (int i = 0; i < this.getSize(); i++) {
			list.set(i, list.get(i) * number);
		}
	}

	@Override
	public void createVector(Vector otherVector) {
		for (int i = 0; i < otherVector.getSize(); i++) {
			this.setElement(getElement(i), i);

		}
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
		for (int i = 0; i < this.getSize(); i++) {
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

	public JLinkedListVector clone() {
		JLinkedListVector vectorObject = null;
		try {
			vectorObject = (JLinkedListVector) super.clone();
			vectorObject.list = (LinkedList<Double>) this.list.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}

		return vectorObject;
	}
	public static void main(String[] args) {
		JLinkedListVector myVector2 = new JLinkedListVector(4);
		myVector2.setElement(0.5, 0);
		try {
			FileOutputStream fileStream = new FileOutputStream("MyVector2.ser");
			ObjectOutputStream os = new ObjectOutputStream(fileStream);
			os.writeObject(myVector2);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		try {
			ObjectInputStream is = new ObjectInputStream(new FileInputStream(
					"MyVector2.ser"));
			JLinkedListVector myVector2Restore = (JLinkedListVector) is.readObject();
			myVector2Restore.print();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}