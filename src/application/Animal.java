package application;

import java.io.Serializable;

public class Animal implements Serializable {

	private int ID,age;
	private String name,description,aType,breed,colour;
	private boolean gender;
	private static int id = 1;
	Category myCat;
	
	
	//Main constructor
	Animal(String name, boolean gender, String description,String aType) {
		this.name = name;
		this.gender = gender;
		this.description = description;
		this.aType = aType;
		this.ID = id;
		id++;
		myCat = null;
	}

	Animal(String name,int age,boolean gender, String description,String aType, String breed,String colour){
		this.ID = id;
		id++;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.description = description;
		this.aType = aType;
		this.breed = breed;
		this.colour = colour;
	}
	//**********************Getters and Setters *****************//
	public Category getCategory() {
		return myCat;
	}
	public void setCategory(Category myCat) {
		this.myCat = myCat;
	}
	
	public void setID(int ID)
	{
		this.ID = ID;
	}
	public int getID()
	{
		return ID;
	}


	public void setAge(int age)
	{
		this.age = age;
	}
	public int getAge()
	{
		return age;
	}
	
	public void setBreed(String breed)
	{
		this.breed = breed;
	}
	public String getBreed()
	{
		return breed;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	public String getName()
	{
		return name;
	}
	
	public void setColour(String colour)
	{
		this.colour = colour;
	}
	public String getColour()
	{
		return colour;
	}
	
	public void setaType(String aType)
	{
		this.aType = aType;
	}
	public String getaType()
	{
		return aType;
	}
	
	
	public void setDescription(String description)
	{
		this.description = description;
	}
	public String getDescription()
	{
		return description;
	}
	
	
	public void setGender(boolean gender)
	{
		this.gender = gender;
	}
	public boolean getGender()
	{
		return gender;
	}
	

//***********************toString and print*********************//
	
	
    public String toString()
    {
        String output = "Animal's name is : " + this.name + " " + "Animal's ID: " + this.ID + "Age: " +
    this.age + "Animal breed is: " +this.breed + "Animaml's type is : " + this.aType + 
    "Animal's Description: "+ this.description + "Animal gender: " + this.gender;
        return output;
    }
    
    public void print()
    {
        System.out.println(toString());
    }
}

//picture:jpeg
//animalCat: Category
