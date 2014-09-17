package iastate.cs319.portfolio1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.AbstractListModel;
import javax.swing.JList;

public class DataModel extends AbstractListModel<String> 
{
	private static final long serialVersionUID = -3324475927239713138L;
	private String FILE_NAME = "companies.txt";
	private JList<String> list;
	
	public DataModel(){
		// empty constructor with default file name
	}
	
	public DataModel(String fileName){
		this.FILE_NAME = fileName;
	}
	
	public void setList(JList<String> list){
		this.list = list;
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
	
	public void remove(String toRemove){
		if(toRemove == null || toRemove.trim().length() < 1)
			return;
		File inFile = new File(FILE_NAME);
		File tmpFile = new File(inFile.getAbsolutePath() + ".tmp");
		try(BufferedReader br = new BufferedReader(new FileReader(inFile));
			PrintWriter pw = new PrintWriter(new FileWriter(tmpFile))){
			String curLine;
			while((curLine = br.readLine()) != null){
				if(!toRemove.equals(curLine.trim()))
					pw.println(curLine);
			}
			pw.close();
			br.close();
			inFile.delete();
			tmpFile.renameTo(inFile);
		} catch(Exception e){
			e.printStackTrace();
		}
		list.updateUI();
	}
	
	private void add(String toAdd){
		try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(FILE_NAME, true)))){
			out.println(toAdd);
		} catch(Exception e){
			e.printStackTrace();
		}
		list.updateUI();
	}
	
	public Runnable addAsRunnable(final String toAdd){
		return new Runnable(){

			@Override
			public void run() {
				add(toAdd);
			}
		};
	}
	
}
