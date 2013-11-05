package com.netcracker.bagachuck.lab;

import java.util.ArrayList;

public class JArrayListVector implements Vector {
	private ArrayList<Double> list;

	public JArrayListVector(int size) {
		ArrayList<Double> list = new ArrayList<Double>();
		for (int i = 0; i < size; i++) {
			list.add(null);
		}

	}

	public JArrayListVector(double... elements) {
		list = new ArrayList<Double>();
		for (int i = 0; i < elements.length; i++) {
			list.add(i, elements[i]);
		}
	}

	@Override
	public int getSize() {
		return list.size();
	}

	@Override
	public void setElement(double val, int index) {
		list.set(index, val);

	}

	@Override
	public void createMass(double mass[]) {
		if (this.getSize() == mass.length) {
			for (int i = 0; i < this.getSize(); i++)
				this.setElement(mass[i], i);
		} else {
			System.out.println("������!����� �������� �� �����!");
		}
	}

	@Override
	public void scalarMult(int number) {
		for (int i = 0; i < this.getSize(); i++) {
			list.set(i, list.get(i) * number);
		}
	}

	@Override
	public double getElement(int index) {
		return list.get(index);
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
	
	  public JArrayListVector clone(){
	        JArrayListVector vectorObject= null;
	        try {
	        	vectorObject = (JArrayListVector) super.clone();
	        	vectorObject.list= (ArrayList<Double>) this.list.clone();

	        } catch (CloneNotSupportedException e) {
	            e.printStackTrace();
	        }

	        return vectorObject;
	    }

}
