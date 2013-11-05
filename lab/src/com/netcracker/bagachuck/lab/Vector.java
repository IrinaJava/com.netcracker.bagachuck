package com.netcracker.bagachuck.lab;

public interface Vector {
	 int getSize();
	 void setElement(double val, int index);
	 double getElement(int index);
	 void createMass(double mass[]);
	 public void createVector(Vector otherVector);
	 void scalarMult(int number);
	 
	 boolean equals(Object other);
}
