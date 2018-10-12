package com.smeghammer.math;

/*
 * like a point, only holding doubles...
 * */
public class Coordinate {
	
	private double x = 0;
	private double y = 0;
	
	public Coordinate(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	public double getX(){
		return this.x;
	}
	
	public double getY(){
		return this.y;
	}
}
