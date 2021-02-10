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
		return "id : " + id + " | body : " + body + " | make : " + make + " | model : " + model + " | year : " + year
				+ " | color : " + color + " | mileage : " + mileage + " | vin : " + vin + " | status : " + status;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((body == null) ? 0 : body.hashCode());
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + id;
		result = prime * result + ((make == null) ? 0 : make.hashCode());
		result = prime * result + Float.floatToIntBits(mileage);
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((vin == null) ? 0 : vin.hashCode());
		result = prime * result + year;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Car other = (Car) obj;
		if (body == null) {
			if (other.body != null)
				return false;
		} else if (!body.equals(other.body))
			return false;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (id != other.id)
			return false;
		if (make == null) {
			if (other.make != null)
				return false;
		} else if (!make.equals(other.make))
			return false;
		if (Float.floatToIntBits(mileage) != Float.floatToIntBits(other.mileage))
			return false;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (vin == null) {
			if (other.vin != null)
				return false;
		} else if (!vin.equals(other.vin))
			return false;
		if (year != other.year)
			return false;
		return true;
	}
	
	
	
}
