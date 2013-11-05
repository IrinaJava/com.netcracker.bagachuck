package com.netcracker.bagachuck.lab;

public class LinkedListVector implements Vector {
	class Link implements Cloneable {
		public double value;
		Link next;
		Link prev;

		public Link(double val) {
			value = val;

		}

		// глубокое клонирование
		public Link clone() throws CloneNotSupportedException {
			// Вызов Object.clone()
			Link cloned = (Link) super.clone();

			// Клонирование изменяемых полей
			cloned.next = (Link) next.clone();
			cloned.prev = (Link) prev.clone();
			return cloned;

		}

	}

	// создаем первый элемент списка(голова)
	private Link first;

	// длина списка
	private int size;

	public int getSize() {
		return size;
	}

	// создаем связный список
	public LinkedListVector() {
		first = null;
	}

	// возвращает true если список пуст
	public boolean isEmpty() {
		return first == null;
	}

	// Добавить в конец
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

	// установить элемент по указанному индексу
	public void setElement(double val, int index) {
		Link link = first;
		for (int i = 0; i < index; i++) {
			link = link.next;
		}
		link.value = val;
	}

	// получение элемента по индексу
	public double getElement(int index) {
		Link link = first;
		for (int i = 0; i < index; i++) {
			link = link.next;
		}
		return link.value;
	}

	// удаление элемента по индексу
	public void delete(int index) {
		Link link = first;
		// не срабатыват на 1й элемент, не входит в цикл

		if (index == 0) {
			// first.next=link.next; link.next.prev=link.next;
			// size--;
			link.prev.next = link.next;
			link.next.prev = link.prev;
			size--;
			first = link.next;
		}

		for (int i = 0; i < index; i++) {
			link = link.next;
		}
		link.prev.next = link.next;
		link.next.prev = link.prev;
		size--;
	}

	// Удаление последнего элемента списка
	public void delete() {
		if (first == null) {
			System.out.println("Список пуст!!!");
		} else {

			first.prev = first.prev.prev;
			first.prev.prev.next = first;
			size--;
		}
	}

	// заполнение вектора с указанного массива
	public void createMass(double mass[]) {
		this.first = null;
		this.size = 0;

		for (int i = 0; i < mass.length; i++) {
			this.add(mass[i]);
		}
	}

	// сложение двух векторов
	public void addVector(Vector otherVector) {
		if (first == null) {
			for (int i = 0; i < otherVector.getSize(); i++) {
				this.setElement(otherVector.getElement(i), i);
			}
			if (this.getSize() != otherVector.getSize()) {
				System.out.print("Ошибка!Длины не равны!");
			}
			for (int i = 0; i < this.getSize(); i++) {
				this.setElement(
						(otherVector.getElement(i) + this.getElement(i)), i);
			}
		}
	}

	// умножение указанного вектора на скаляр
	public void scalarMult(int number) {
		for (int i = 0; i < this.getSize(); i++) {
			this.setElement(this.getElement(i) * number, i);
		}
	}

	// сортировка указанного вектора
	public static void bubbleSort(LinkedListVector vector) {
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

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (other == null) {
			return false;
		}
		if (!(other instanceof LinkedListVector)) {
			return false;
		}

		if (this.getSize() != ((LinkedListVector) other).getSize()) {
			return false;
		}
		for (int i = 0; i < this.getSize(); i++) {
			if (this.getElement(i) != ((LinkedListVector) other).getElement(i)) {
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

	

	public static void main(String[] args) {
		LinkedListVector myList = new LinkedListVector();

		myList.add(0.0);
		myList.add(1.0);
		myList.add(2.0);
		myList.add(3.0);
		myList.add(4.0);
		myList.add(5.0);
		// myList.toString();

		// myList.setElement(0.0, 2);
		// myList.delete(0);
		// myList.printList();
		// myList.printList();
		System.out.println("Длина списка: " + myList.getSize());
		System.out.println("List: ");
		for (int i = 0; i < myList.getSize(); i++) {
			System.out.print("  " + myList.getElement(i));
		}
		// System.out.print("Длина списка: " + myList.getElement(0));
		// System.out.print("Длина списка: " + myList.getSize());
	}

}
