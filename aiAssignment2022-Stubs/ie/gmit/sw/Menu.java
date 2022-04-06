package ie.gmit.sw;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Menu {
	private static Menu m = null;
	private int choice;
	private File file;
	private Scanner in = new Scanner(System.in);
	private ReadCSV read;
	private static NNFactory factory=NNFactory.getInstance();
	private EncogNN nn;
	

	private Menu() {

	}

	public static Menu getInstance() {
		if (m == null)
			m = new Menu();

		return m;
	}

	public void showMenu() throws IOException {
		
		System.out.println(ConsoleColour.WHITE);
		System.out.println("************************************************************");
		System.out.println("*      GMIT - Dept. Computer Science & Applied Physics     *");
		System.out.println("*                                                          *");
		System.out.println("*             Artificial Intelligence Assignment           *");
		System.out.println("*                                                          *");
		System.out.println("************************************************************");
		System.out.println("(1) Upload Test data + Train Network");
		System.out.println("(2) Upload Test Data");
		System.out.println("(3) Don't hard-code paths / file fames or bad things will happen");
		System.out.println("(4) Quit");

		// Output a menu of options and solicit text from the user
		System.out.print(ConsoleColour.BLACK_BOLD_BRIGHT);
		System.out.print("Select Option [1-4]>");
		System.out.println();
		choice=in.nextInt();
		processChoice();
		//in.close();
	}

	private void processChoice() throws IOException {
		Scanner scanner;
		String filename;
		int vecSize ;
		int shingleSize;
		switch (choice) {
		case 1:
			if(file!=null) {
				System.out.println("Training");
			}else {
				System.out.println("There is no training file selected!");
			}
			break;
		case 2:
			scanner= new Scanner(System.in);
			System.out.println("Please enter file name: ");
			filename = scanner.nextLine();
			System.out.println("Please enter the vector size for the inputs: ");
			vecSize = scanner.nextInt();
			System.out.println("Please enter the size of the shingles: ");
			shingleSize = scanner.nextInt();
			
			nn=factory.create(vecSize, shingleSize, filename);
			break;
		
		case 3: 
			if(nn==null) {
				System.out.println("No neural network available, please upload test data");
			}else {
				scanner= new Scanner(System.in);
				System.out.println("Please enter file name: ");
				filename= scanner.nextLine();
				System.out.println("Please enter the vector size for the inputs: ");
				vecSize = scanner.nextInt();
				System.out.println("Please enter the size of the shingles: ");
				shingleSize = scanner.nextInt();
				factory.test(nn, vecSize, shingleSize, filename);
			}
			break;
		}
		System.out.print("Select Option [1-4]>");
		System.out.println();
		choice=in.nextInt();
		processChoice();
		
		
	}

	public int getChoice() {
		return choice;
	}

	public void setChoice(int choice) {
		this.choice = choice;
	}

}
