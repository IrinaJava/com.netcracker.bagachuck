package com.netcracker.bagachuck.lab;

import java.io.*;

public class ArrayVector implements Vector, Cloneable, Serializable {

	private double vector[];

	public ArrayVector(int size) {
		vector = new double[size];

	}

	public ArrayVector(double... elements) {
		vector = new double[elements.length];
		for (int i = 0; i < elements.length; i++) {
			this.setElement(elements[i], i);
			;
		}
	}

	public double getElement(int index) {
		if (index >= 0 && index < this.getSize()) {
			return vector[index];
		} else {
			throw new IllegalArgumentException("Wrong index!");
		}
	}

	public void setElement(double val, int index) {
		if (index >= 0 && index < this.getSize()) {
			vector[index] = val;
		} else {
			throw new IllegalArgumentException("Wrong index!");
		}
	}

	public int getSize() {
		return vector.length;

	}

	public void createMass(double mass[]) {
		vector = new double[mass.length];
		for (int i = 0; i < mass.length; i++) {
			vector[i] = mass[i];

		}
	}

	public void createVector(Vector otherVector) {
		for (int i = 0; i < otherVector.getSize(); i++) {
			this.setElement(getElement(i), i);

		}
	}

	public void scalarMult(int number) {
		for (int i = 0; i < vector.length; i++) {
			vector[i] = vector[i] * number;
		}
	}

	public void add(Vector otherVector) {
		for (int i = 0; i < this.getSize(); i++) {
			this.setElement(this.getElement(i) + otherVector.getSize(), i);
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

	public ArrayVector clone() {
		ArrayVector vectorObject = null;
		try {
			vectorObject = (ArrayVector) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		vectorObject.vector = this.vector.clone();
		return vectorObject;
	}

	public static void main(String[] args) {
		ArrayVector myVector = new ArrayVector(5);
		
		myVector.setElement(1.1, 0);
		
		try {
			FileOutputStream fileStream = new FileOutputStream("MyVector.ser");
			ObjectOutputStream os = new ObjectOutputStream(fileStream);
			os.writeObject(myVector);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		try {
			ObjectInputStream is = new ObjectInputStream(new FileInputStream(
					"MyVector.ser"));
			ArrayVector myVectorRestore = (ArrayVector) is.readObject();

			System.out.println(myVectorRestore);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
