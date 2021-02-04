package com.carlot.model;

public class Car {
	
	private int id;
	private String body;
	private String make;
	private String model;
	private int year;
	private String color;
	private float mileage;
	private String vin;
	private String status;
	
	
	public Car() {}


	public Car(int id, String body, String make, String model, int year, String color, float mileage, String vin,
			String status) {
		super();
		this.id = id;
		this.body = body;
		this.make = make;
		this.model = model;
		this.year = year;
		this.color = color;
		this.mileage = mileage;
		this.vin = vin;
		this.status = status;
	}

	public Car( String body, String make, String model, int year, String color, float mileage, String vin,
			String status) {
		super();

		this.body = body;
		this.make = make;
		this.model = model;
		this.year = year;
		this.color = color;
		this.mileage = mileage;
		this.vin = vin;
		this.status = status;
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getBody() {
		return body;
	}


	public void setBody(String body) {
		this.body = body;
	}


	public String getMake() {
		return make;
	}


	public void setMake(String make) {
		this.make = make;
	}


	public String getModel() {
		return model;
	}


	public void setModel(String model) {
		this.model = model;
	}


	public int getYear() {
		return year;
	}


	public void setYear(int year) {
		this.year = year;
	}


	public String getColor() {
		return color;
	}


	public void setColor(String color) {
		this.color = color;
	}


	public float getMileage() {
		return mileage;
	}


	public void setMileage(float mileage) {
		this.mileage = mileage;
	}


	public String getVin() {
		return vin;
	}


	public void setVin(String vin) {
		this.vin = vin;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	@Override
	public String toString() {
		return "Car [id=" + id + ", body=" + body + ", make=" + make + ", model=" + model + ", year=" + year
				+ ", color=" + color + ", mileage=" + mileage + ", vin=" + vin + ", status=" + status + "]";
	}
	
	
	
}
