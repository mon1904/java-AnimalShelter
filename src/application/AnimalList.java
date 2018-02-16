package application;

import java.io.Serializable;
import java.util.ArrayList;

public class AnimalList implements Serializable {
	
	private ArrayList <Animal> animals = null;

	//setup singleton design pattern
	//I want to be able to use the same list for all the classes in different windows
	private static AnimalList instance;
	//singleton design pattern
	/*
	 * used because main and controller use the same 
	 * list and we do not want to be passing the list back and forth
	 * once animals are added and removed ect.
	 * This way if either controller or main modify the animal,
	 * each will see the same version and changes will be seen by both.
	 * This is because using the singleton means only 1 instance of animalList will ever exist
	 */
	public static AnimalList getInstance(){
		if(instance == null){
			return new AnimalList();
		}
		else{
			return instance;
		}
	}
	//instantiate arrayList
	public AnimalList()
	{
		animals = new ArrayList <Animal>();
		instance = this;
	}
	
	public void addAnimal(Animal a){
		animals.add(a);
	}


	public ArrayList <Animal> getList(){
		return animals;
	}

	public void removeAnimal(int i){

		if((i>-1) && (i < animals.size()))
			animals.remove(i);

	}

	/*
	 public void removeAnimalByName(String name){
	
		for(int i=0; i<animals.size(); i++)
			if(getAnimal(i).getName().equals(name)){
				animals.remove(i);
			}
	}
	*/
	//returning a boolean as we need to know if the animal exists
	//if true is returned then the animal has been removed 
	//if false then the animal has not been found by the name sent in
	public boolean removeAnimalByName(String name){
		boolean removed = false;
		name = name.toUpperCase();
		for(int i=0; i<animals.size(); i++)
			if(getAnimal(i).getName().toUpperCase().equals(name)){
				animals.remove(i);
				removed = true;
			}
		
		return removed;
	}
	
	//Search for an animal version 2
	public Animal searchAnimal(int id){
		for(Animal a :animals){
			if(a.getID()==id){
				return a;
			}
		}

		return null;
	}

	//Search for an animal version 3
	public int getAnimalbyPosition(int id){
		int i = 0;
		for(Animal a :animals){
			if(a.getID()==id){
				return i; 
			}
			i++;
		}

		return -1;
	}
	
	public Animal getAnimal(int i)
	{
		if((i>-1) && (i < animals.size()))
		{
			return animals.get(i);
		}
		return null;
	}

	public int getSize()
	{
		return animals.size();
	}
	
	public void print(){
		System.out.println(toString());
	}
	
	public String toString(){
		String output = "";
		
		for(Animal an:animals){
			//output += "Animal name: " + an.getName() ;
			
			if(an.getCategory() != null){
				output +="****************"
						+"\n*Amimal Details"+ "*"
						+"\n****************"
						+"\nName: " + an.getName()
						+"\nID: " + an.getID() + "\nAge: " +
					    +an.getAge() + "\nBreed: " +an.getBreed() + "\nType: " + an.getaType()
					    +"\nDescription; "+ an.getDescription() + "\nGender: " 
					    +an.getGender()  + "\nCategory:" + an.getCategory().getType()  
						+an.getCategory().toString()+"\n";
				if(an.getCategory() instanceof Adoption){
					//adoptions have no owner as of yet!
				}
				else{
					output += "Owner:" + an.getCategory().getPerson().getName()+"\n"+"\n";
				}
			}
		}
		
		
		if(output.equals("")){
			output = "No animals in system";
		}
		return output;
	}
	
	//return a string arraylist so we can populate the dropdown menu
	public ArrayList<String> getAnimalsForComboBox(){
		ArrayList<String> names = new ArrayList<String>();
		
		for(Animal a: animals){
			names.add(a.getName());
		}
		return names;
	}
	
	//allows us to return this specific instance of the animal list
	public void setList(ArrayList<Animal> list){
		this.animals = list;
	}
	
	
}
