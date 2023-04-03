package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Labeled;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import model.Toy;
import model.Animals;
import model.BoardGames;
import model.Figures;
import model.Puzzles;

public class Manager implements Initializable{

	private final String FILE_PATH = "res/toys.txt";
	ArrayList<Toy> toyList;
	Toy toy;
	
	@FXML
	Button searchToys;
	
	@FXML
	Button clearButton;
	
	@FXML
	RadioButton searchBySerialNumber;
	@FXML
	RadioButton searchByToyName;
	@FXML
	RadioButton searchByToyType;
	@FXML
	ToggleGroup searchTypes;
	
	@FXML
	TextField serialNumberInput;
	@FXML
	TextField toyNameInput;
	
	@FXML
	ComboBox<String> toyTypeInput;
	@FXML
	String[] toyTypes = {"Figure","Animal","Puzzle","Board Game"};
	
	@FXML
	TextField newSerialNumber;
	@FXML
	TextField newToyName;
	@FXML
	TextField newToyBrand;
	@FXML
	TextField newToyPrice;
	@FXML
	TextField newAvailableCount;
	@FXML
	TextField newAppropriateAge;
	
	@FXML
	ComboBox<String> newFigureClassification;
	@FXML
	String[] figureClassifications = {"Action","Doll","Historic"};
	
	@FXML
	ComboBox<String> newPuzzleType;
	@FXML
	String[] puzzleTypes = {"Mechanical","Cryptic","Logic","Trivia","Riddle"};

	@FXML
	TextField newAnimalMaterial;
	@FXML
	ComboBox<String> newAnimalSize;
	@FXML
	String[] animalSizes = {"Small","Medium","Large"};
	
	@FXML
	TextField newBoardGameMinCount;
	@FXML
	TextField newBoardGameMaxCount;
	@FXML
	TextField newBoardGameDesigners;
	
	@FXML 
	TextField removeSerialNumber;
	
	@FXML
	ListView<String> toyListView = new ListView<>();
	
	@FXML 
	ListView<String> removeListView = new ListView<>();
	
	//global variables 
	int globalSelectedIndex;
	
	ArrayList<Toy> globalMatchingToys = new ArrayList<>();
	
	public Manager() {
 		try {

 		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		toyList = new ArrayList<>();
		loadData();
		toyTypeInput.getItems().addAll(toyTypes);
		newFigureClassification.getItems().addAll(figureClassifications);
		newPuzzleType.getItems().addAll(puzzleTypes);
		newAnimalSize.getItems().addAll(animalSizes);
    }
	
	@FXML
	void save() {
		File db = new File(FILE_PATH);
		PrintWriter printWriter;
		try {
			printWriter = new PrintWriter(db);
			for (Toy t: toyList) {
				printWriter.println(t.format());
			}
			printWriter.close();
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	void loadData() {
		File db = new File(FILE_PATH);
		String currentLine;
		String[] splitLine;
		
		if (db.exists()) {
			
			Scanner fileReader;
			try {
				fileReader = new Scanner(db);
				while (fileReader.hasNextLine()) {
					currentLine = fileReader.nextLine();
					splitLine = currentLine.split(";");
							
					String serialNumber = splitLine[0];
					String toyName = splitLine[1];
					String toyBrand = splitLine[2];
					double toyPrice = Double.parseDouble(splitLine[3]);
					int availableCount = Integer.parseInt(splitLine[4]);
					int appropriateAge = Integer.parseInt(splitLine[5]);

					if (serialNumber.charAt(0) == '0' || serialNumber.charAt(0) == '1') {
						String figureClassification = splitLine[6];
						
						toy = new Figures(serialNumber, toyName, toyBrand, toyPrice, availableCount, appropriateAge, figureClassification); 
					}
					else if (serialNumber.charAt(0) == '2' || serialNumber.charAt(0) == '3') {
						String animalMaterial = splitLine[6];
						String animalSize = splitLine[7];
						
						toy = new Animals(serialNumber, toyName, toyBrand, toyPrice, availableCount, appropriateAge, animalMaterial, animalSize);
					}
					else if (serialNumber.charAt(0) == '4' || serialNumber.charAt(0) == '5' || serialNumber.charAt(0) == '6') {
						String puzzleType = splitLine[6];
						
						toy = new Puzzles(serialNumber, toyName, toyBrand, toyPrice, availableCount, appropriateAge, puzzleType);
					}
					else if (serialNumber.charAt(0) == '7' || serialNumber.charAt(0) == '8' || serialNumber.charAt(0) == '9') {
						int minimumPlayerCount = 0;
						int maximumPlayerCount = 0;
						String boardGameDesigners;
						String checkForString = splitLine[6];
						
						if (checkForString.length()!= 1) {
							minimumPlayerCount = Character.getNumericValue(splitLine[6].charAt(0));
							maximumPlayerCount = Character.getNumericValue(splitLine[6].charAt(2));	
							boardGameDesigners = splitLine[7];
						}else {
							minimumPlayerCount = Character.getNumericValue(splitLine[6].charAt(0));
							maximumPlayerCount = Character.getNumericValue(splitLine[7].charAt(0));
							boardGameDesigners = splitLine[8];

						}
						
						toy = new BoardGames(serialNumber, toyName, toyBrand, toyPrice, availableCount, appropriateAge, minimumPlayerCount, maximumPlayerCount, boardGameDesigners);
					}
						toyList.add(toy); 

				}
				fileReader.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		else {
			try {
				db.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * sorts through toyList and pulls toy if inputSN and toySN match
	 * @param serialNumber
	 */
	@FXML
	void searchBySerialNumber(ActionEvent Event) {	
		ObservableList<String> matchingNumbers = FXCollections.observableArrayList();
		ArrayList<Toy> matchingToys = new ArrayList<>();
		String serialNumber = serialNumberInput.getText().trim();
		for (Toy toy:toyList) {
			String currentToySN = toy.getSerialNumber();
			if (currentToySN.equals(serialNumber)) {
				matchingToys.add(toy); //object list
				matchingNumbers.add(toy.toString()); //observable list
			}
		}
		toyListView.setItems(matchingNumbers);
		
		//send list as array list of toy objects 
		searchListHolder(matchingToys); //could get rid of this
		
		globalMatchingToys.clear();
		globalMatchingToys = matchingToys;
	}
	
	/**
	 * sorts through toyList and pulls toy if inputName in toyName
	 * @param searchToyName
	 */
	@FXML
	void searchByToyName(ActionEvent Event) {
		ObservableList<String> matchingNames = FXCollections.observableArrayList();
		ArrayList<Toy> matchingToys = new ArrayList<>();
		String toyName = toyNameInput.getText().trim().toLowerCase();
		for (Toy toy:toyList) {
			String currentToyName = toy.getToyName();		
			if (currentToyName.toLowerCase().contains(toyName)) {
				matchingNames.add(toy.toString());
			}
		}
		toyListView.setItems(matchingNames);
		
		//send list as array list of toy objects 
				searchListHolder(matchingToys);
				
//				globalMatchingToys.clear();
				//might have to try appending each toy in a for loop
				
				globalMatchingToys = matchingToys;
	}
	
	/**
	 * sorts throught toyList and pulls if inputType and toyType match
	 * @param toyType
	 */
	@FXML
	void searchByToyType(ActionEvent Event) {
		ObservableList<String> matchingTypes = FXCollections.observableArrayList();
		ArrayList<Toy> matchingToys = new ArrayList<>();
		String toyType = toyTypeInput.getValue();
		for (Toy toy:toyList) {
			if (toyType.equals("Figure")) {
				if (toy.getSerialNumber().charAt(0) == '0' || toy.getSerialNumber().charAt(0) == '1') {
					matchingTypes.add(toy.toString());
				}
			}
			else if (toyType.equals("Animal")) {
				if (toy.getSerialNumber().charAt(0) == '2' || toy.getSerialNumber().charAt(0) == '3') {
					matchingTypes.add(toy.toString());
				}
			}
			else if (toyType.equals("Puzzle")) {
				if (toy.getSerialNumber().charAt(0) == '4' || toy.getSerialNumber().charAt(0) == '5' || toy.getSerialNumber().charAt(0) == '6') {
					matchingTypes.add(toy.toString());
				}
			}
			else if (toyType.equals("Board Game")) {
				if (toy.getSerialNumber().charAt(0) == '7' || toy.getSerialNumber().charAt(0) == '8' || toy.getSerialNumber().charAt(0) == '9') {
					matchingTypes.add(toy.toString());
				}
			}
		}
		toyListView.setItems(matchingTypes);
		
		//send list as array list of toy objects 
				searchListHolder(matchingToys);
				
				globalMatchingToys.clear();
				globalMatchingToys = matchingToys;
	}
	
	@FXML
	void search(ActionEvent Event) {
		System.out.println("Event is happening");
		if (searchBySerialNumber.isSelected()) {
			searchBySerialNumber(Event);
		}
		else if (searchByToyName.isSelected()) {
			searchByToyName(Event);
		}
		else if (searchByToyType.isSelected()) {
			searchByToyType(Event);
		}
	}
	
	@FXML
	void clear(ActionEvent Event) {
		System.out.println("Event is happening");
		//clearing SN
		serialNumberInput.clear();
		
		//clearing toy name
		toyNameInput.clear();
	}
	
	@FXML 
	void purchase(ActionEvent Event) {
		System.out.println("purchase exists");
		int selected;
		
		//gettng selected thing
		selected = toyListView.getSelectionModel().getSelectedIndex();
		System.out.println(selected);
		
		//setting it to the global variable
		globalSelectedIndex = selected; //might delete
		
		//have to make another method that compares indexes of the two lists and removes the proper item
		//also have to save it after each run 
		
		System.out.println(globalMatchingToys);
		
		//yanking result's available count 
		int inventory = globalMatchingToys.get(selected).getAvailableCount();
		System.out.println(inventory);
		
		
	}
	
	//this one gets called automatically after the search methods have been called 
	void searchListHolder(ArrayList<Toy> results) {
		
		
		//global index can be reached 

		
	}
	
	void purchaseAction(ArrayList<Toy> results) {
		//take global selected variable 
		//call said item from toy with specified index
		//deduct one from toy count and rewrite list 
		
		//yanking result's available count 
		int inventory = results.get(globalSelectedIndex).getAvailableCount();
		System.out.println(inventory);
	}
	
	@FXML
	void addToy(ActionEvent Event) {		
		String serialNumber = newSerialNumber.getText().trim();
		String toyName = newToyName.getText().trim();
		String toyBrand = newToyBrand.getText().trim();
		double toyPrice = Double.parseDouble(newToyPrice.getText().trim());
		int availableCount = Integer.parseInt(newAvailableCount.getText().trim());
		int appropriateAge = Integer.parseInt(newAppropriateAge.getText().trim());
		
		String figureClassification = newFigureClassification.getValue();
		
		String puzzleType = newPuzzleType.getValue();
		
		String animalMaterial = newAnimalMaterial.getText().trim();
		String animalSize = newAnimalSize.getValue();
		
		int boardGameMinCount = Integer.parseInt(newBoardGameMinCount.getText().trim());
		int boardGameMaxCount = Integer.parseInt(newBoardGameMaxCount.getText().trim());
		String boardGameDesigners = newBoardGameDesigners.getText().trim();
		
		if (serialNumber.charAt(0) == '0' || serialNumber.charAt(0) == '1') {
			Toy addToy = new Figures(serialNumber,toyName,toyBrand,toyPrice,availableCount,appropriateAge,figureClassification);
			toyList.add(addToy);
		}
		else if (serialNumber.charAt(0) == '2' || serialNumber.charAt(0) == '3') {
			Toy addToy = new Animals(serialNumber,toyName,toyBrand,toyPrice,availableCount,appropriateAge,animalMaterial,animalSize);
			toyList.add(addToy);
		}
		else if (serialNumber.charAt(0) == '4' || serialNumber.charAt(0) == '5' || serialNumber.charAt(0) == '6') {
			Toy addToy = new Puzzles(serialNumber,toyName,toyBrand,toyPrice,availableCount,appropriateAge,puzzleType);
			toyList.add(addToy);
		}
		else if (serialNumber.charAt(0) == '7' || serialNumber.charAt(0) == '8' || serialNumber.charAt(0) == '9') {
			Toy addToy = new BoardGames(serialNumber,toyName,toyBrand,toyPrice,availableCount,appropriateAge,boardGameMinCount,boardGameMaxCount,boardGameDesigners);
			toyList.add(addToy);
		}
		save();
	}
	
	@FXML
	void removeToy(ActionEvent Event) {	
		ObservableList<String> matchingNumbers = FXCollections.observableArrayList();
		String serialNumber = removeSerialNumber.getText().trim();
		for (Toy toy:toyList) {
			String currentToySN = toy.getSerialNumber();
			if (currentToySN.equals(serialNumber)) {
				matchingNumbers.add(toy.toString());
			}
		}
		removeListView.setItems(matchingNumbers);
	}

	
}
