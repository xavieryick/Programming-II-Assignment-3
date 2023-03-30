package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import model.Toy;
import model.Animals;
import model.BoardGames;
import model.Figures;
import model.Puzzles;

public class Manager {

	private final String FILE_PATH = "res/toys.txt";
	ArrayList<Toy> toyList;
	Toy toy;

	@FXML
	RadioButton searchBySerialNumber;
	@FXML
	RadioButton searchByToyName;
	@FXML
	RadioButton searchByToyType;
	
	@FXML
	TextField serialNumberInput;
	@FXML
	TextField toyNameInput;
	@FXML
	ComboBox<String> toyTypeInput = new ComboBox<>();
	
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
	TextField newFigureClassification;
	@FXML
	TextField newPuzzleType;
	@FXML
	TextField newAnimalMaterial;
	@FXML
	TextField newAnimalSize;
	@FXML
	TextField newBoardGameMinCount;
	@FXML
	TextField newBoardGameMaxCount;
	@FXML
	TextField newBoardGameDesigners;
	
	@FXML
	ListView<String> toyListView = new ListView<>();
	
	public Manager() {
		toyList = new ArrayList<>();
 		try {
			loadData();
		} 
		catch (Exception e) {
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
		String serialNumber = serialNumberInput.getText().trim();
		for (Toy toy:toyList) {
			String currentToySN = toy.getSerialNumber();
			if (currentToySN.equals(serialNumber)) {
				matchingNumbers.add(toy.toString());
			}
		}
		toyListView.setItems(matchingNumbers);
	}
	
	/**
	 * sorts through toyList and pulls toy if inputName in toyName
	 * @param searchToyName
	 */
	@FXML
	void searchByToyName(ActionEvent Event) {
		ObservableList<String> matchingNames = FXCollections.observableArrayList();
		String toyName = toyNameInput.getText().trim().toLowerCase();
		for (Toy toy:toyList) {
			String currentToyName = toy.getToyName();		
			if (currentToyName.toLowerCase().contains(toyName)) {
				matchingNames.add(toy.toString());
			}
		}
		toyListView.setItems(matchingNames);
	}
	
	/**
	 * sorts throught toyList and pulls if inputType and toyType match
	 * @param toyType
	 */
	@FXML
	void searchByToyType(ActionEvent Event) {
		ObservableList<String> matchingTypes = FXCollections.observableArrayList();
		String toyType = toyTypeInput.getValue();
		for (Toy toy:toyList) {
			if (toyType.equals("Figure")) {
				if (toy.getSerialNumber().charAt(0) == '0' || toy.getSerialNumber().charAt(0) == '1') {
					matchingTypes.add(toy.toString());
				}
			}
			else if (toyType.equals("Puzzle")) {
				if (toy.getSerialNumber().charAt(0) == '2' || toy.getSerialNumber().charAt(0) == '3') {
					matchingTypes.add(toy.toString());
				}
			}
			else if (toyType.equals("Animal")) {
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
	}
	
	@FXML
	void search(ActionEvent Event) {
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
	void addToy() {		
		String serialNumber = newSerialNumber.getText();
		String toyName = newToyName.getText();
		String toyBrand = newToyBrand.getText();
		double toyPrice = Double.parseDouble(newToyPrice.getText());
		int availableCount = Integer.parseInt(newAvailableCount.getText());
		int appropriateAge = Integer.parseInt(newAppropriateAge.getText());
		
		char figureClassification = newFigureClassification.getText().charAt(0);
		
		char puzzleType = newPuzzleType.getText().charAt(0);
		
		String animalMaterial = newAnimalMaterial.getText();
		char animalSize = newAnimalSize.getText().charAt(0);
		
		int boardGameMinCount = Integer.parseInt(newBoardGameMinCount.getText());
		int boardGameMaxCount = Integer.parseInt(newBoardGameMaxCount.getText());
		String boardGameDesigners = newBoardGameDesigners.getText();
	}
	
}
