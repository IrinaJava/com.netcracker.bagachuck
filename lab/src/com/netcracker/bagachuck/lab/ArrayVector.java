package com.netcracker.bagachuck.lab;

public class ArrayVector implements Vector {
	// Объявим наш массив
	private double vector[];

	// Конструктор получает в качестве параметра размер массива
	public ArrayVector(int size) {
		// создаём массив, дабы не получать NullPointerException при обращении к
		// нему
		vector = new double[size];

	}

	// Метод возвратит нам элемент по указанному индексу
	public double getElement(int index) {
		// Обязательно проверяем значение индекса
		if (index >= 0 && index < this.getSize()) {
			return vector[index];
		} else {
			// Если кто-то пытается получить несуществующий элемент, выбрасываем
			// исключение
			throw new IllegalArgumentException("Wrong index!");
		}
	}

	// Метод для присваивания значения элементу массива. Также проверяем индекс
	// и кидаем исключение.
	public void setElement(double val, int index) {
		if (index >= 0 && index < this.getSize()) {
			vector[index] = val;
		} else {
			throw new IllegalArgumentException("Wrong index!");
		}
	}

	// Возвращает длину массива или количество элементов.
	public int getSize() {
		return vector.length;

	}

	// заполнение вектора с указанного массива
	public void createMass(double mass[]) {
		vector = new double[mass.length];
		for (int i = 0; i < mass.length; i++) {
			vector[i] = mass[i];

		}
	}

	// заполнение из другого вектора
	public void createVector(Vector otherVector) {
		for (int i = 0; i < otherVector.getSize(); i++) {
			this.setElement(getElement(i), i);

		}
	}

	// умножение вектора на скаляр
	public void scalarMult(int number) {
		for (int i = 0; i < vector.length; i++) {
			vector[i] = vector[i] * number;
		}
	}

	// сложение двух векторов
	public void add(Vector otherVector) {
		for (int i = 0; i < this.getSize(); i++) {
			this.setElement(this.getElement(i) + otherVector.getSize(), i);
		}
	}

	// метод сравнение двух векторов
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

}
