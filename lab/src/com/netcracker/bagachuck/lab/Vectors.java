package com.netcracker.bagachuck.lab;

import java.io.*;
import java.util.Random;

public class Vectors {

	public static void scalarMult(int number) {
		Vector vector = new ArrayVector();
		for (int i = 0; i < vector.getSize(); i++) {
			vector.setElement(vector.getElement(i) * number, i);
		}

	}

	public static Vector add(Vector v1, Vector v2) {

		if (v1 == null || v2 == null) {
			System.err.println("Worning!not valid!");
		}
		if (v1.getSize() != v2.getSize()) {
			System.err.println("Worning! length not equal!");
		}
		Vector v = new ArrayVector(v1.getSize());
		for (int i = 0; i < v1.getSize(); i++) {
			v.setElement(v1.getElement(i) + v2.getElement(i), i);

		}
		return v;

	}

	public static void bubbleSort(Vector vector) {
		System.out.println("Vector : ");
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
		System.out.println("Sorting vector:");
		for (int i = 0; i < vector.getSize(); i++) {
			System.out.print(vector.getElement(i) + " ");
		}
		System.out.println("");
	}

	public static Vector random(int size) {
		Vector vectorRandom = new ArrayVector(size);
		Random r = new Random();
		for (int i = 0; i < size; i++) {
			vectorRandom.setElement(r.nextDouble(), i);
		}
		return vectorRandom;
	}

	public void outputVector(Vector v, OutputStream out) {
		try {
			DataOutputStream outData = new DataOutputStream(out);
			outData.writeInt(v.getSize());
			for (int i = 0; i < v.getSize(); i++) {
				outData.writeDouble(v.getElement(i));
			}
			outData.flush();
		} catch (IOException e) {
			System.out.println("Some error occurred!");

		}
	}
/////////Err
	static Vector inputVector(InputStream in) {
		Vector v = null;
		try {
			DataInputStream inData = new DataInputStream(in);
			for (int i = 0; i < v.getSize(); i++) {
				v.setElement(inData.readDouble(), i);
			}
		} catch (IOException e) {
			System.out.println("Some error occurred!");
		}
		return v;
	}

	static void writeVector(Vector v, Writer out) {
		try {
			PrintWriter wrout = new PrintWriter(out);
			wrout.print(v.getSize());
			for (int i = 0; i < v.getSize(); i++) {
				wrout.print(" " + v.getElement(i));
			}
			wrout.println();

			wrout.flush();

		} catch (Exception ex) {
			System.out.println("Some error occurred!");
		}
	}
////////ERrr
	static Vector readVector(Reader in) {
		Vector v = null;
		try {
			StreamTokenizer ind = new StreamTokenizer(in);
			ind.nextToken();
			for (int i = 0; i < v.getSize(); i++) {
				ind.nextToken();
				v.setElement(v.getElement(i), i);
			}
		} catch (IOException e) {
			System.out.println("Some error occurred!");
		}
		return v;
	}

}
