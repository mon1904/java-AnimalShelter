package application;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class SaveAndLoad {
	
	String fileName;
	
	public SaveAndLoad(String file){
		this.fileName = file;
	}
	

	public void saveToFile(AnimalList al){
		try {
            FileOutputStream fileOutput = new FileOutputStream(fileName);
            ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);
            objectOutput.writeObject(al);
		}catch(Exception e){
			
		}
	}
	
	public ArrayList<Animal> readFromFile(){
		AnimalList al;
		try {
            FileInputStream fileOutput = new FileInputStream(fileName);
            ObjectInputStream objectOutput = new ObjectInputStream(fileOutput);
            al = (AnimalList) objectOutput.readObject();
            return al.getList();
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

}
