package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import model.Toy;
import model.Animals;
import model.BoardGames;
import model.Figures;
import model.Puzzles;
import exceptions.CustomException;

import java.util.logging.*;

/**
 * This class contains all of the methods needed and manages the program
 * @author xavie
 * @author kaydence eng
 *
 */
public class Manager implements Initializable{

	//database file
	private final String FILE_PATH = "res/toys.txt"; 

	//initializes toy list
	ArrayList<Toy> toyList; 
	
	//initializes toy type
	Toy toy;
	
	@FXML //search by serial number radio button 
	RadioButton searchBySerialNumber;
	@FXML //search by toy name radio button
	RadioButton searchByToyName;
	@FXML //search by toy type radio button
	RadioButton searchByToyType;
	@FXML //adds all radio buttons to a single group
	ToggleGroup searchTypes;
	
	@FXML //input field on search for serial number
	TextField serialNumberInput;
	@FXML //input field on search for toy name
	TextField toyNameInput;
	@FXML //combobox on search for toy types
	ComboBox<String> toyTypeInput;
	@FXML // all toy types added to toyTypeInput
	String[] toyTypes = {"Figure","Animal","Puzzle","Board Game"};
	
	@FXML // serial number input for add toy
	TextField newSerialNumber;
	@FXML // toy name input for add toy
	TextField newToyName;
	@FXML // toy brand input for add toy
	TextField newToyBrand;
	@FXML // toy price input for add toy
	TextField newToyPrice;
	@FXML // available count for add toy
	TextField newAvailableCount;
	@FXML // appropriate age for add toy
	TextField newAppropriateAge;
	@FXML // figure classification for add toy
	ComboBox<String> newFigureClassification;
	@FXML // all figure classifications for newFigureClassification
	String[] figureClassifications = {"Action","Doll","Historic"};
	@FXML // puzzle type for add toy
	ComboBox<String> newPuzzleType;
	@FXML // all puzzle types for newPuzzleType
	String[] puzzleTypes = {"Mechanical","Cryptic","Logic","Trivia","Riddle"};
	@FXML // animal material for add toy
	TextField newAnimalMaterial;
	@FXML // animal size for add toy
	ComboBox<String> newAnimalSize;
	@FXML // all animal sizes for newAnimal size
	String[] animalSizes = {"Small","Medium","Large"};
	@FXML // board game minimum player count for add toy
	TextField newBoardGameMinCount;
	@FXML // board game maximum player count for add toy
	TextField newBoardGameMaxCount;
	@FXML // board game designers for add toy
	TextField newBoardGameDesigners;
	@FXML // serial number used for remove method
	TextField removeSerialNumber;
	@FXML // initializes the toy ListView
	ListView<String> toyListView = new ListView<>();
	@FXML // initializes the remove ListView
	ListView<String> removeListView = new ListView<>();
	
	//global variables 
	int globalSelectedIndex;
	
	ObservableList<String> globalNoResults = FXCollections.observableArrayList("We couldn't find what you're looking for!");
	
	final static Logger LOGR = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	
	// initializes matching toy array list
	ArrayList<Toy> globalMatchingToys = new ArrayList<>();
	@FXML // error label for search
	Label errorLabel; //for welcome screen
	@FXML // error label for add toys
	Label errorLabel2; //for add toys 
	@FXML // error label for remove toys
	Label errorLabel3; //for remove toys
	
	public Manager() {
 		try {

 		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method initializes the program, loads the data and adds input selections to the ComboBoxes
	 */
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		toyList = new ArrayList<>();
		loadData();
		toyTypeInput.getItems().addAll(toyTypes);
		newFigureClassification.getItems().addAll(figureClassifications);
		newPuzzleType.getItems().addAll(puzzleTypes);
		newAnimalSize.getItems().addAll(animalSizes);
    }
	
	/**
	 * This method saves the information to the database file
	 */
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
	
	/**
	 * This method loads the information from the database file
	 */
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
	 * this method sorts through toyList and pulls toy if inputSN and toySN match
	 * @param serialNumber user's given serial number
	 */
	@FXML
	void searchBySerialNumber(ActionEvent Event) {	
		ObservableList<String> matchingNumbers = FXCollections.observableArrayList();
		ArrayList<Toy> matchingToys = new ArrayList<>();
		
		boolean validator = false; //allows for length and parse long check 
		 
		globalMatchingToys.clear();
		
		try {
			//clearing out the error label if things work
			errorLabel.setText("");
			String serialNumber = serialNumberInput.getText().trim();
			
			//SN validation goes here
			
			if(serialNumber.length() != 10) {
				errorLabel.setText("Invalid input! SN must be 10 digits long!");
			}
			
			else {
				validator = true;
			}
			
			
			//conduct parse long check here 
			
			if(validator == true) {
				
				try {
					Long.parseLong(serialNumber);
				}
				catch(NumberFormatException e){
					errorLabel.setText("Invalid input! All digits must be numbers!");
					validator = false; 
				}
			}
			
			//now we can see if anything is found 
			if (validator == true) {
				for (Toy toy:toyList) {
				String currentToySN = toy.getSerialNumber();
				if (currentToySN.equals(serialNumber)) {
					matchingToys.add(toy); //object list
					globalMatchingToys.add(toy); //global list
					matchingNumbers.add(toy.toString()); //observable list
				}	
				
			}
			
			// checking to see if we actually found anything
			if (matchingToys.size() == 0) {
				// this one opens the rabbit hole of user validation
//				toyListView.setItems(globalNoResults);
				toyListView.getItems().clear();
				errorLabel.setText("We couldn't find what you're looking for!");
			}
			
			else {
				toyListView.setItems(matchingNumbers);
				}
			
			}
			
		
		} //CUT OFF for TRY
		catch (Exception e) {
			errorLabel.setText("There was an error");
		}
	}
	
	/**
	 * this method sorts through toyList and pulls toy if inputName in toyName
	 * @param searchToyName user's given toy name
	 */
	@FXML
	void searchByToyName(ActionEvent Event) {
		ObservableList<String> matchingNames = FXCollections.observableArrayList();
		ArrayList<Toy> matchingToys = new ArrayList<>();
		globalMatchingToys.clear();
		
		try {
			//clearing out the error label if things work
			errorLabel.setText("");
			
			String toyName = toyNameInput.getText().trim().toLowerCase();
			
			//make sure user puts in a toy name
			if (toyName.equals(null) || toyName.isEmpty()) {
				errorLabel.setText("Please enter a name and search again!");
			}
			else {
				
				for (Toy toy:toyList) {
				String currentToyName = toy.getToyName();		
				if (currentToyName.toLowerCase().contains(toyName)) {
					matchingToys.add(toy);
					globalMatchingToys.add(toy); //global list
					matchingNames.add(toy.toString());
				}
			}
//			toyListView.setItems(matchingNames);
			
			// checking to see if we actually found anything
				if (matchingToys.size() == 0) {
					// this one opens the rabbit hole of user validation
//							toyListView.setItems(globalNoResults);
					toyListView.getItems().clear();
					errorLabel.setText("We couldn't find what you're looking for!");
				}
				else {
					toyListView.setItems(matchingNames);
				}
				
			}
			
			
		}
		catch (Exception e) {
			errorLabel.setText("There was an error");
		}
	}
	
	/**
	 * this method sorts through toyList and pulls the toy if inputType and toyType match
	 * @param toyType user's given toy type
	 */
	@FXML
	void searchByToyType(ActionEvent Event) {
		ObservableList<String> matchingTypes = FXCollections.observableArrayList();
		ArrayList<Toy> matchingToys = new ArrayList<>();
		globalMatchingToys.clear();
		
		try {
			//clearing out the error label if things work
			errorLabel.setText("");
			
			String toyType = toyTypeInput.getValue();
			for (Toy toy:toyList) {
				if (toyType.equals("Figure")) {
					if (toy.getSerialNumber().charAt(0) == '0' || toy.getSerialNumber().charAt(0) == '1') {
						
						matchingToys.add(toy);
						globalMatchingToys.add(toy); //global list
						
						matchingTypes.add(toy.toString());
						
					}
				}
				else if (toyType.equals("Animal")) {
					if (toy.getSerialNumber().charAt(0) == '2' || toy.getSerialNumber().charAt(0) == '3') {
						
						matchingToys.add(toy);
						globalMatchingToys.add(toy); //global list
						
						matchingTypes.add(toy.toString());
					}
				}
				else if (toyType.equals("Puzzle")) {
					if (toy.getSerialNumber().charAt(0) == '4' || toy.getSerialNumber().charAt(0) == '5' || toy.getSerialNumber().charAt(0) == '6') {
						
						matchingToys.add(toy);
						globalMatchingToys.add(toy); //global list
						
						matchingTypes.add(toy.toString());
					}
				}
				else if (toyType.equals("Board Game")) {
					if (toy.getSerialNumber().charAt(0) == '7' || toy.getSerialNumber().charAt(0) == '8' || toy.getSerialNumber().charAt(0) == '9') {
						
						matchingToys.add(toy);
						globalMatchingToys.add(toy); //global list
						
						matchingTypes.add(toy.toString());
					}
				}
			}
			toyListView.setItems(matchingTypes);
		}
		catch (Exception e) {
			errorLabel.setText("There was an error");
		}
	}
	
	/**
	 * This method searches for toys based on which radio button is selected
	 * @param Event
	 */
	@FXML
	void search(ActionEvent Event) {
//		System.out.println("Search event is happening"); debug
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
	
	/**
	 * This method will clear the text boxes
	 * @param Event
	 */
	@FXML
	void clear(ActionEvent Event) {
//		System.out.println("clear event is happening"); debug
		
		//clearing error label
		errorLabel.setText("");
		//clearing SN
		serialNumberInput.clear();
		
		//clearing toy name
		toyNameInput.clear();
		
		//clearing combo box selection
		toyTypeInput.getSelectionModel().clearSelection();
		
		//clearing toy list view 
		toyListView.getItems().clear();
	}
	
	/**
	 * This method will purchase an item if it is selected from the ListView
	 * @param Event
	 */
	@FXML 
	void purchase(ActionEvent Event) {
//		System.out.println("purchase exists"); debug
		int selected;
		//selected but to check if something is selected
		String item = toyListView.getSelectionModel().getSelectedItem();
		
// 		check to see if the user actually picked something 
		if(item != null) {		
		
		
		//gettng selected thing
		selected = toyListView.getSelectionModel().getSelectedIndex();
//		System.out.println("The selcted index is: " + selected); debug
		
		//setting it to the global variable
		globalSelectedIndex = selected; //might delete
		
		//have to make another method that compares indexes of the two lists and removes the proper item
		//also have to save it after each run 
		
//		System.out.println("full matched list");
//		System.out.println(globalMatchingToys);
		
		//yanking result's available count 
		int inventory = globalMatchingToys.get(selected).getAvailableCount();
//		System.out.println(inventory);
		
			
			if(inventory > 0) {
			errorLabel.setText("");
			String selectedSerialNumber = globalMatchingToys.get(selected).getSerialNumber();
			for(int rewrite = 0; rewrite < toyList.size(); rewrite++) {
				String activeToy = toyList.get(rewrite).getSerialNumber();
				
				if(selectedSerialNumber.equals(activeToy)) {
					toyList.get(rewrite).setAvailableCount(inventory - 1);
					
					errorLabel.setText("Item purchased successfully!");
					save();
				}
			}
		}
		else {
			//label saying that we're out of an item
			//purchase works on the condition that they picked something 
			errorLabel.setText("Can't purchase this item! It's out of stock!");
			}
			
		}
		// if user hasn't selected an item
		else {
			errorLabel.setText("Please select an item to purchase!");
		}
		
	}

	/**
	 * This method will add a toy that uses the user's given inputs 
	 * @param Event
	 */
	@FXML
	void addToy(ActionEvent Event) throws CustomException{		
		try {
			String serialNumber = newSerialNumber.getText().trim();
			if (serialNumber.length() != 10) { // length of 10
				throw new Exception();
			}
			try {
				long validSN = Long.parseLong(serialNumber); // checks for non-numbers
				try {
					String toyName = newToyName.getText().trim();
					if (toyName.length() == 0) {
						throw new Exception();
					}
					try {
						String toyBrand = newToyBrand.getText().trim();
						if (toyBrand.length() == 0) {
							throw new Exception();
						}
						try {
							double toyPrice = Double.parseDouble(newToyPrice.getText().trim());
							
							if (toyPrice < 0) {
								errorLabel2.setText("Price must be greater than zero");
								throw new CustomException("Toy prices can't be negative!");
								
								//it catches negative prices now, i don't know how that happened
								//custom message won't show up though
								
							}
							
							try {
								int availableCount = Integer.parseInt(newAvailableCount.getText().trim());
								try {
									int appropriateAge = Integer.parseInt(newAppropriateAge.getText().trim()); // works to here
									try {
										if (serialNumber.charAt(0) == '0' || serialNumber.charAt(0) == '1') {
											try {
												String figureClassification = newFigureClassification.getValue();
												if (figureClassification == null) {
													throw new Exception();
												}
												Toy addToy = new Figures(serialNumber,toyName,toyBrand,toyPrice,availableCount,appropriateAge,figureClassification);
												toyList.add(addToy);
												save();
											} catch (Exception e) {
												errorLabel2.setText("Please select a figure classification");
											}
										}
										
										if (serialNumber.charAt(0) == '4' || serialNumber.charAt(0) == '5' || serialNumber.charAt(0) == '6') {
											try {
												String puzzleType = newPuzzleType.getValue();
												if (puzzleType == null) {
													throw new Exception();
												}
												Toy addToy = new Puzzles(serialNumber,toyName,toyBrand,toyPrice,availableCount,appropriateAge,puzzleType);
												toyList.add(addToy);
												save();
											} catch (Exception e) {
												errorLabel2.setText("Please select a puzzle type");
											}
										}
										
										if (serialNumber.charAt(0) == '2' || serialNumber.charAt(0) == '3') {
											try {
												String animalMaterial = newAnimalMaterial.getText().trim();
												try {
													String animalSize = newAnimalSize.getValue();
													if (animalSize == null) {
														throw new Exception();
													}
													Toy addToy = new Animals(serialNumber,toyName,toyBrand,toyPrice,availableCount,appropriateAge,animalMaterial,animalSize);
													toyList.add(addToy);
													save();
												} catch (Exception e) {
													errorLabel2.setText("Please select an animal size");
												}
											} catch (Exception e) {
												errorLabel2.setText("Please enter a valid animal material");
											}
										}
										
										if (serialNumber.charAt(0) == '7' || serialNumber.charAt(0) == '8' || serialNumber.charAt(0) == '9') {
											try {
												int boardGameMinCount = Integer.parseInt(newBoardGameMinCount.getText().trim());
												try {
													int boardGameMaxCount = Integer.parseInt(newBoardGameMaxCount.getText().trim());
													try {
														String boardGameDesigners = newBoardGameDesigners.getText().trim();
														//i added this little block, it didn't help 
//														if(boardGameDesigners == null) {
//															throw new Exception();
//														}
														Toy addToy = new BoardGames(serialNumber,toyName,toyBrand,toyPrice,availableCount,appropriateAge,boardGameMinCount,boardGameMaxCount,boardGameDesigners);
														toyList.add(addToy);
														save();
													} catch (Exception e) {
														errorLabel2.setText("Please enter a valid board game designer");
													}
												} catch (Exception e) {
													errorLabel2.setText("Please enter a valid board game maximum player count");
												}
											} catch (Exception e) {
												errorLabel2.setText("Please enter a valid board game minimum player count");
											}
										}
									} catch (Exception e) {
										// checking for first digit, does this need anything?
									}
								} catch (Exception e) {
									errorLabel2.setText("Please enter a valid appropriate age");
								}
							} catch (Exception e) {
								errorLabel2.setText("Please enter a valid available count");
							}
						} catch (Exception e) {
							errorLabel2.setText("Please enter a valid toy price!");
						}
					} catch (Exception e) {
						errorLabel2.setText("Please enter a valid toy brand");	
					}
				} catch (Exception e) {
					errorLabel2.setText("Please enter a valid toy name");
				}
			} catch (Exception e) {
				errorLabel2.setText("Please enter a serial number with only numbers");
			}	
		} catch (Exception e) {
			errorLabel2.setText("Please enter a serial number with 10 digits");
		}
	}
	
	/**
	 * This method will search for a toy that matches the user's given serial number
	 * @param Event
	 */
	@FXML
	void removeToySearch(ActionEvent Event) {
		
		boolean validator = false; //allows for length and parse long check 
		
		try {
			//clearing out the error label if things work
			errorLabel3.setText("");
			
			ObservableList<String> matchingNumbers = FXCollections.observableArrayList();
			String serialNumber = removeSerialNumber.getText().trim();
			
			//check length 
			if(serialNumber.length() != 10) {
				errorLabel3.setText("Invalid input! SN must be 10 digits long!");
			}
			
			else {
				validator = true;
			}
			
			//check parse long 
			if(validator == true) {
				
				try {
					Long.parseLong(serialNumber);
				}
				catch(NumberFormatException e){
					errorLabel3.setText("Invalid input! All digits must be numbers!");
					validator = false; 
				}
			}
			
			
			//checking for match 
			if (validator == true) {
				
				for (Toy toy:toyList) {
				String currentToySN = toy.getSerialNumber();
				if (currentToySN.equals(serialNumber)) {
					matchingNumbers.add(toy.toString());
				}
			}
				
//			removeListView.setItems(matchingNumbers);
				
			//checking if list length is zero
			if(matchingNumbers.size() == 0) {
				removeListView.getItems().clear();
				errorLabel3.setText("We couldn't find what you're looking for!");
			}
			else {
				removeListView.setItems(matchingNumbers);
			}
		}
			
			
		//CATCH CUTOFF	
		}
		catch (Exception e) {
			errorLabel3.setText("There was an error");
		}
	}
	
	/**
	 * This method will remove a toy that matches the user's given serial number
	 * @param Event
	 */
	@FXML
	void removeToy(ActionEvent Event) {
//		for (Toy toy:toyList) {
//			if (toy.getSerialNumber().equals(removeSerialNumber.getText().trim())) {
//				toyList.remove(toy);
//			}
//		} this one dont work nice :(
		//checking to see if we selected something 
		String item = removeListView.getSelectionModel().getSelectedItem();
		//if statement to check that something was picked
		if(item != null) {
			
			try {
			//clearing out the error label if things work
			errorLabel3.setText("");
			
			
			for (int index = 0; index < toyList.size(); index++) {
				if (toyList.get(index).getSerialNumber().equals(removeSerialNumber.getText().trim())) {
					toyList.remove(index);
				}
			}
			errorLabel3.setText("Item successfully removed!");
			removeListView.getItems().clear();
			save();
		}
		catch (Exception e) {
			errorLabel3.setText("There was an error");
			}
		}
		else {
//			System.out.println("PICK SOMETHING");
			//we need another label bruh 
			errorLabel3.setText("Please select an item to remove!");
		}
		
//		System.out.println("Ttst");
	}
	
}
