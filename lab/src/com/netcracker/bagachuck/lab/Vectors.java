package com.netcracker.bagachuck.lab;

import java.util.Random;

public class Vectors {
	// умножение указанного вектора на скаляр
	public static void scalarMult(int number, Vector vector) {
		for (int i = 0; i < vector.getSize(); i++) {
			vector.setElement(vector.getElement(i) * number, i);
		}

	}

	// сложение двух указанных векторов
	public static void add(Vector v1, Vector v2) {
		if (v1.getSize() != v2.getSize()) {
			System.out.println("Ошибка! Длины не совпадают!");
		}
		if (v1 == null || v2 == null) {
			System.out.println("Недопустимое значение!");
		}
		for (int i = 0; i < v1.getSize(); i++) {
			v1.setElement(v1.getElement(i) + v2.getElement(i), i);
		}

	}

	// сортировка указанного вектора
	public static void bubbleSort(Vector vector) {
		System.out.println("Заданный массив : ");
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
		System.out.println("Отсортированный массив :");
		for (int i = 0; i < vector.getSize(); i++) {
			System.out.print(vector.getElement(i) + " ");
		}
		System.out.println("");
	}

	static double[] createArray(int size) {
		Random r = new Random();
		double[] newArray = new double[size];
		for (int i = 0; i < size; i++)
			newArray[i] = r.nextDouble();
		return newArray;
	}

}