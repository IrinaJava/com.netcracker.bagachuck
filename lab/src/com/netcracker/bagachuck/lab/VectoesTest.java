package com.netcracker.bagachuck.lab;

import java.io.*;

public class VectoesTest {
	private double myVector[];

	public VectoesTest(int size) {
		myVector = new double[size];

	}

	public double getElement(int index) {
		if (index >= 0 && index < this.getSize()) {
			return myVector[index];
		} else {
			throw new IllegalArgumentException("Wrong index!");
		}
	}

	public void setElement(double val, int index) {
		if (index >= 0 && index < this.getSize()) {
			myVector[index] = val;
		} else {
			throw new IllegalArgumentException("Wrong index!");
		}
	}

	public int getSize() {
		return myVector.length;

	}

	public static void outputVector(Vector v, OutputStream out) {
		DataOutputStream dos = new DataOutputStream(out);

		try {
			dos.writeInt(v.getSize());
			for (int i = 0; i < v.getSize(); i++) {
				dos.writeDouble(v.getElement(i));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static VectoesTest inputVector(InputStream in) {
		DataInputStream din = new DataInputStream(in);

		VectoesTest myVector = new VectoesTest(3);
		try {
			int count = (int) din.readDouble();

			for (int i = 0; i < count; i++) {
				myVector.setElement(din.readDouble(), i);
				// collection.add(dis.readDouble());
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return myVector;
	}
	
	
	
	/*
	 * public static void writeVector(Vector v, Writer out) { BufferedWriter bw
	 * = new BufferedWriter(out);
	 * 
	 * try { bw.write(v.size()+"");
	 * 
	 * 
	 * for (int i = 0; i < v.size(); i++) { bw.write(String.valueOf(" " +
	 * v.get(i))); } bw.write("\r\n"); bw.flush(); } catch (IOException e) {
	 * e.printStackTrace(); } }
	 * 
	 * public static Vector readVector(Reader in) { StreamTokenizer nums = new
	 * StreamTokenizer(in);
	 * 
	 * Vector collection = Vectors.createInstance();
	 * 
	 * try { nums.nextToken();
	 * 
	 * while (nums.nextToken() != StreamTokenizer.TT_EOF) { if (nums.ttype ==
	 * StreamTokenizer.TT_NUMBER) collection.add(nums.nval);
	 * 
	 * } } catch (Exception e) { e.printStackTrace(); }
	 * 
	 * return collection; }
	 */

	public static void main(String[] args) {
		//VectoesTest start= new VectoesTest(5);

	}

}
