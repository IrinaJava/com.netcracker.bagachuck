package com.netcracker.bagachuck.lab;

import java.util.LinkedList;

public class JLinkedListVector implements Vector,Cloneable {
	private LinkedList<Double> list;

	public JLinkedListVector(int size) {
		LinkedList<Double> list = new LinkedList<Double>();
		for (int i = 0; i < size; i++) {
			list.add(null);
		}

	}

	public JLinkedListVector(double... elements) {
		list = new LinkedList<Double>();
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
	public double getElement(int index) {

		return list.get(index);
	}

	@Override
	public void createMass(double mass[]) {
		if (this.getSize() == mass.length) {
			for (int i = 0; i < this.getSize(); i++)
				this.setElement(mass[i], i);
		} else {
			System.out.println("Ошибка!Длины векторов не равны!");
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
	
	public JLinkedListVector clone(){
        JLinkedListVector vectorObject= null;
        try {
        	vectorObject = (JLinkedListVector) super.clone();
        	vectorObject.list=(LinkedList<Double>)this.list.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return vectorObject;
    }

}