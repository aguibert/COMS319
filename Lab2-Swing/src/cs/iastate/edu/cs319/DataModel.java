package cs.iastate.edu.cs319;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.AbstractListModel;

public class DataModel extends AbstractListModel<String> 
{
	private static final long serialVersionUID = -3324475927239713138L;
	private String FILE_NAME = "companies.txt";
	
	public DataModel(){
		// empty constructor with default file name
	}
	
	public DataModel(String fileName){
		this.FILE_NAME = fileName;
	}

	@Override
	public int getSize() {
		int size = 0;
		try(Scanner s = new Scanner(new File(FILE_NAME))){
			while(s.hasNextLine()){
				size++;
				s.nextLine();
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		return size;
	}

	@Override
	public String getElementAt(int index) {
		try(Scanner s = new Scanner(new File(FILE_NAME))){
			// Move to the desired line
			for(int i = 0; i < index; i++){
				if(!s.hasNextLine())
					throw new RuntimeException("Index out of bounds: " + index);
				else
					s.nextLine();
			}
			// Return the desired line
			return s.nextLine();
		} catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public List<String> getAllCompanies() {
		List<String> toRet = new ArrayList<String>();
		try(Scanner s = new Scanner(new File(FILE_NAME));){
			while(s.hasNextLine())
				toRet.add(s.nextLine());
		} catch (Exception e){
			e.printStackTrace();
		}
		return toRet;
	}
	
}
