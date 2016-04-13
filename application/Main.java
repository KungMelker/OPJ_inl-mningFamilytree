package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Main extends Application {

	private List<Cat> listOfCats;
	private Cat viewedCat;

	@Override
	public void start(Stage primaryStage) {
		
		
		listOfCats = new ArrayList<Cat>();
		
		BorderPane root = new BorderPane();
		Scene scene = new Scene(root, 900, 800);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();

		// Center Grid
		GridPane centerGrid = new GridPane();
		centerGrid.setPadding(new Insets(10, 10, 10, 10));
		centerGrid.setVgap(5);
		centerGrid.setHgap(5);
		root.setCenter(centerGrid);

		// Right border
		VBox rightVbox = new VBox();
		root.setRight(rightVbox);

		// RadioButtons for gender selection.
		RadioButton rb1sex = new RadioButton("Male");
		RadioButton rb2sex = new RadioButton("Female");
		ToggleGroup toggleGender = new ToggleGroup();
		rb1sex.setToggleGroup(toggleGender);
		rb2sex.setToggleGroup(toggleGender);
		toggleGender.selectToggle(rb2sex);

		// TextFields
		TextField tfName = new TextField();
		tfName.setPromptText("Name of the cat");
		TextField tfBirthDate = new TextField();
		tfBirthDate.setPromptText("YYYY-MM-DD");

		// Labels
		Label lMandInfo = new Label("Mandatory Information:");
		Label lName = new Label("Name:");
		Label lBirthDate = new Label("Birth Date:");
		Label lAddInfo = new Label("Additional Information:");
		Label lMate = new Label("Mate:");
		Label lCats = new Label("List of created cats");
		Label lMaleParent = new Label("Father:");
		Label lFemaleParent = new Label("Mother:");
		Label lChildrens = new Label("Childrens:");
		Label lSibblings = new Label("Sibblings:");

		// Text
		Text tCurrMate = new Text("No Mate");
		tCurrMate.setStroke(Color.RED);
		Text tFather = new Text("No Father");
		tFather.setStroke(Color.LIGHTBLUE);
		Text tMother = new Text("No Mother");
		tMother.setStroke(Color.LIGHTPINK);

		// buttons
		Button bAddCat = new Button("Add Cat");
		Button bDelCat = new Button("Del.Cat");
		Button bAddMate = new Button("Add");
		Button bRemMate = new Button("Remove");
		Button bVieweCat = new Button("Veiwe Cat");
		Button bAddMaleParent = new Button("Add");
		Button bRemMaleParent = new Button("Remove");
		Button bAddFemaleParent = new Button("Add");
		Button bRemFemaleParent = new Button("Remove");
		Button bAddChild = new Button("Add"); // will be added to hbChildrens
		// Button bRemChild = new Button("Remove"); // will be added to
		// hbChildrens in future updates
		Button bAddSibling = new Button("Add"); // will be added to hbSiblings
		// Button bRemSibling = new Button("Remove"); // will be added to
		// hbSibblings in future updates.

		// HBox
		HBox hbChildrens = new HBox();
		hbChildrens.getChildren().addAll(bAddChild);
		HBox hbSiblings = new HBox();
		hbSiblings.getChildren().addAll(bAddSibling);

		// listview
		ListView<Cat> listViewOfCats = new ListView<>();
		ObservableList<Cat> cats = FXCollections.observableArrayList(listOfCats);

		ListView<Cat> listViewOfChildren = new ListView<>();
		ObservableList<Cat> children = null;

		ListView<Cat> listViewOfSibblings = new ListView<>();
		ObservableList<Cat> sibblings = null;

		// Add all context to right VBox
		rightVbox.getChildren().addAll(lCats, listViewOfCats, bVieweCat);

		// GridPane child orientation

		GridPane.setColumnSpan(centerGrid, 4);
		GridPane.setRowSpan(centerGrid, 10);

		GridPane.setConstraints(lMandInfo, 0, 0);// Object, column, row

		GridPane.setConstraints(lName, 0, 1);
		GridPane.setConstraints(tfName, 1, 1);

		GridPane.setConstraints(lBirthDate, 2, 1);
		GridPane.setConstraints(tfBirthDate, 4, 1);

		GridPane.setConstraints(rb1sex, 0, 2);
		GridPane.setConstraints(rb2sex, 1, 2);

		GridPane.setConstraints(lAddInfo, 0, 3);

		GridPane.setConstraints(lMate, 0, 4);
		GridPane.setConstraints(tCurrMate, 1, 4);
		GridPane.setConstraints(bAddMate, 2, 4);
		GridPane.setConstraints(bRemMate, 4, 4);

		GridPane.setConstraints(lMaleParent, 0, 5);
		GridPane.setConstraints(tFather, 1, 5);
		GridPane.setConstraints(bAddMaleParent, 2, 5);
		GridPane.setConstraints(bRemMaleParent, 4, 5);

		GridPane.setConstraints(lFemaleParent, 0, 6);
		GridPane.setConstraints(tMother, 1, 6);
		GridPane.setConstraints(bAddFemaleParent, 2, 6);
		GridPane.setConstraints(bRemFemaleParent, 4, 6);

		GridPane.setConstraints(lChildrens, 0, 7);
		GridPane.setConstraints(listViewOfChildren, 0, 8);
		GridPane.setConstraints(hbChildrens, 0, 9);

		GridPane.setConstraints(lSibblings, 2, 7);
		GridPane.setConstraints(listViewOfSibblings, 2, 8);
		GridPane.setConstraints(hbSiblings, 2, 9);

		GridPane.setConstraints(bAddCat, 1, 10);
		GridPane.setConstraints(bDelCat, 2, 10);

		// Add all context to centerGrid
		centerGrid.getChildren().addAll(rb1sex, rb2sex, bAddCat, bDelCat, lName, tfName, lBirthDate, tfBirthDate,
				lAddInfo, lMandInfo, lMate, tCurrMate, bAddMate, bRemMate, lMaleParent, tFather, bAddMaleParent,
				bRemMaleParent, lFemaleParent, tMother, bAddFemaleParent, bRemFemaleParent, lChildrens,
				listViewOfChildren, lSibblings, listViewOfSibblings, hbChildrens, hbSiblings);

		// Top border - toolbar
		addToolbar(root, cats, listViewOfCats, tfName);

		// Action events
		bAddCat.setOnAction(event -> {

			creatCat(toggleGender, tfName, tfBirthDate, rb2sex, cats, rightVbox, centerGrid, listViewOfCats);

		});

		bDelCat.setOnAction(event -> {

			deliteCat(toggleGender, tfName, rb2sex, rb1sex, cats, root, listViewOfCats);

		});

		bAddMate.setOnAction(event -> {

			addMate(listViewOfCats, tCurrMate, centerGrid);

		});

		bRemMate.setOnAction(event -> {

			removeMate(listViewOfCats, tCurrMate, centerGrid);

		});

		bVieweCat.setOnAction(event -> {

			viewCat(listViewOfCats, tCurrMate, centerGrid, tfName, tfBirthDate, rb1sex, rb2sex, toggleGender, tMother,
					tFather, children, sibblings, listViewOfChildren, listViewOfSibblings);

		});

		bAddMaleParent.setOnAction(event -> {

			addParent(listViewOfCats, tFather, tMother, centerGrid);
		});

		bRemMaleParent.setOnAction(event -> {
			boolean removeMale = true;
			removeParent(removeMale, tFather, tMother, centerGrid);

		});
		bAddFemaleParent.setOnAction(event -> {

			addParent(listViewOfCats, tFather, tMother, centerGrid);
		});

		bRemFemaleParent.setOnAction(event -> {
			boolean removeMale = false;
			removeParent(removeMale, tFather, tMother, centerGrid);

		});

		bAddChild.setOnAction(event -> {

			addChild(listViewOfCats, listViewOfChildren, children, centerGrid);
		});

		bAddSibling.setOnAction(event -> {

			addSibling(listViewOfCats, listViewOfSibblings, sibblings, centerGrid);

		});

	}

	private void addToolbar(BorderPane root, ObservableList<Cat> cats, ListView<Cat> listViewOfCats, TextField tfName) {

		Button bsave = new Button("save", new ImageView("images/22.gif"));
		bsave.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
		Button bopen = new Button("open", new ImageView("images/53.gif"));
		bopen.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);

		ToolBar tbar = new ToolBar(bsave, bopen);

		root.setTop(tbar);

		bsave.setTooltip(new Tooltip("Save your file."));
		bopen.setTooltip(new Tooltip("Open new file."));

		bsave.setOnAction(e -> {

			saveFile();
		});

		bopen.setOnAction(e -> {

			openFile(cats, listViewOfCats, tfName);
		});

	}

	private void creatCat(ToggleGroup toggleGender, TextField tfName, TextField tfBirthDate, RadioButton rb2sex,
			ObservableList<Cat> cats, VBox rightVbox, GridPane centerGrid, ListView<Cat> listViewOfCats) {
		if (!tfName.getText().isEmpty() && !tfBirthDate.getText().isEmpty() && isDateValid(tfBirthDate.getText())) {
			Cat newCat = null;

			if (toggleGender.getSelectedToggle().equals(rb2sex)) {
				newCat = new FemaleCat(tfName.getText(), formaTotDate(tfBirthDate.getText()));
				listOfCats.add(newCat);

			} else {
				newCat = new MaleCat(tfName.getText(), formaTotDate(tfBirthDate.getText()));
				listOfCats.add(newCat);

			}
									
			cats = FXCollections.observableArrayList(listOfCats);
			listViewOfCats.setItems(cats);
			viewedCat = newCat;
		}

	}

	private void deliteCat(ToggleGroup toggleGender, TextField tfName, RadioButton rb2sex, RadioButton rb1sex,
			ObservableList<Cat> cats, BorderPane root, ListView<Cat> listViewOfCats) {

		if (listViewOfCats.getSelectionModel().getSelectedItem() != null) {
			Cat catToBeRemoved = listViewOfCats.getSelectionModel().getSelectedItem();
			listOfCats.remove(catToBeRemoved);
			rb2sex.disarm();
			rb1sex.disarm();
			toggleGender.selectToggle(rb2sex);
			tfName.clear();
			cats = FXCollections.observableArrayList(listOfCats);
			listViewOfCats.setItems(cats);
		}

	}

	private void addMate(ListView<Cat> listViewOfCats, Text tCurrMate, GridPane centerGrid) {
		
	Cat newMate = listViewOfCats.getSelectionModel().getSelectedItem();
		
		if (newMate != null && newMate != viewedCat) {
			
			viewedCat.addMate(newMate);

			tCurrMate.setText(newMate.getName());
			tCurrMate.setStroke(Color.GREEN);
		}

	}

	private void removeMate(ListView<Cat> listViewOfCats, Text tCurrMate, GridPane centerGrid) {

		if (viewedCat.getMate() != null) {
			viewedCat.getMate().setMate(null);
			viewedCat.setMate(null);

			tCurrMate.setText("No Mate");
			tCurrMate.setStroke(Color.RED);
		}

	}

	private void addParent(ListView<Cat> listViewOfCats, Text tFather, Text tMother, GridPane centerGrid) {
		
		Cat newParent = listViewOfCats.getSelectionModel().getSelectedItem();
		if (newParent != null && newParent != viewedCat) {
			
			viewedCat.addParent(newParent);
			if (newParent.getGender().equals(Gender.FEMALE)) {
				tMother.setText(newParent.getName());

			} else if (newParent.getGender().equals(Gender.MALE)) {

				tFather.setText(newParent.getName());
			}

		}
	}

	private void removeParent(boolean removeMale, Text tFather, Text tMother, GridPane centerGrid) {

		if (viewedCat.getFather() != null || viewedCat.getMother() != null) {

			Set<Cat> parents = viewedCat.getParents();

			if (removeMale == false) {
				tMother.setText("No Mother");
				parents.remove(viewedCat.getMother());

			} else if (removeMale == true) {
				tFather.setText("No Father");
				parents.remove(viewedCat.getFather());
			}

		}
	}

	private void addChild(ListView<Cat> listViewOfCats, ListView<Cat> listViewOfChildren, ObservableList<Cat> children,
			GridPane centerGrid) {
		
		Cat newChild = listViewOfCats.getSelectionModel().getSelectedItem();
		if (newChild != null && viewedCat.getMate() != null && newChild != viewedCat) {
			
			viewedCat.addChild(newChild);
			children = FXCollections.observableArrayList(viewedCat.getChildren());
			listViewOfChildren.setItems(children);
		}

	}

	private void addSibling(ListView<Cat> listViewOfCats, ListView<Cat> listViewOfSibblings,
			ObservableList<Cat> sibblings, GridPane centerGrid) {
		
		Cat newSibling = listViewOfCats.getSelectionModel().getSelectedItem();
		
		if (newSibling != null && newSibling != viewedCat) {
			
			viewedCat.addSibling(newSibling);
			sibblings = FXCollections.observableArrayList(viewedCat.getSiblings());
			listViewOfSibblings.setItems(sibblings);
		}

	}

	private void viewCat(ListView<Cat> listViewOfCats, Text tCurrMate, GridPane centerGrid, TextField tfName,
			TextField tfBirthDate, RadioButton rb1sex, RadioButton rb2sex, ToggleGroup toggleGender, Text tMother,
			Text tFather, ObservableList<Cat> children, ObservableList<Cat> sibblings, ListView<Cat> listViewOfChildren,
			ListView<Cat> listViewOfSibblings) {

		if (listViewOfCats.getSelectionModel().getSelectedItem() != null) {
			viewedCat = listViewOfCats.getSelectionModel().getSelectedItem();

			tfName.setText(viewedCat.getName());
			tfBirthDate.setText(viewedCat.getBirthDate().toString());

			// View the sex of the chosen cat.
			if (viewedCat.getClass() == FemaleCat.class) {
				toggleGender.selectToggle(rb2sex);
			} else if (viewedCat.getClass() == MaleCat.class) {
				toggleGender.selectToggle(rb1sex);
			}

			// Check if there are any mate to show and if so they are showed.
			if (viewedCat.getMate() != null) {
				tCurrMate.setText(viewedCat.getMate().getName());
				tCurrMate.setStroke(Color.GREEN);
			} else {
				tCurrMate.setText("No Mate");
				tCurrMate.setStroke(Color.RED);
			}

			// Check if there are a mother and if so she is showed.
			if (viewedCat.getMother() != null) {
				tMother.setText(viewedCat.getMother().getName());

			} else {
				tMother.setText("No Mother");

			}
			tMother.setStroke(Color.LIGHTPINK);

			// Check if there are a father and if so he is showed.
			if (viewedCat.getFather() != null) {
				tFather.setText(viewedCat.getFather().getName());

			} else {
				tFather.setText("No Father");

			}
			tFather.setStroke(Color.LIGHTBLUE);

			// Check if viewed cat has children
			if (viewedCat.getChildren() != null) {
				children = FXCollections.observableArrayList(viewedCat.getChildren());
			} else {
				children = null;
				listViewOfChildren.getSelectionModel().clearSelection();
			}

			listViewOfChildren.setItems(children);

			if (viewedCat.getSiblings() != null) {
				sibblings = FXCollections.observableArrayList(viewedCat.getSiblings());
			} else {
				sibblings = null;
				listViewOfSibblings.getSelectionModel().clearSelection();
			}

			listViewOfSibblings.setItems(sibblings);

		}

	}

	// ************* VG - KOMMENTAR READ/WRITE TO FILE *********************
	// I'm using file chooser to let the user choose the location
	// the file is stored and where to get the file from.
	// that should compensate for that there are no relative path to the
	// file.

	private void openFile(ObservableList<Cat> cats, ListView<Cat> listViewOfCats, TextField tfName) {

		FileChooser filechooser = new FileChooser();
		
		File theFile = filechooser.showOpenDialog(null);

		FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Text Files", "*.txt");
		filechooser.setInitialDirectory(new File("c:\\"));
		filechooser.getExtensionFilters().add(filter);

		if (theFile != null) {

			String contents = "";
			Cat newCat = null;

			try (BufferedReader br = new BufferedReader(new FileReader(theFile))) {

				while (br.ready()) {

					contents = br.readLine();
					String[] tempContArray = new String[3];
					tempContArray = contents.split("%");
					
					
					switch (tempContArray[2]) {
					case "FEMALE":
						
						newCat = new FemaleCat(tempContArray[0], formaTotDate(tempContArray[1]));
						listOfCats.add(newCat);
						break;
						
					case "MALE":
						
						newCat = new MaleCat(tempContArray[0], formaTotDate(tempContArray[1]));
						listOfCats.add(newCat);
						break;	
						

					default:
						break;
					}
					
					cats = FXCollections.observableArrayList(listOfCats);
					listViewOfCats.setItems(cats);

				}

			} catch (ArrayIndexOutOfBoundsException e1) {
				
				tfName.setText("FILE ERROR!!");
				
			} catch (Exception e2) {
				
				e2.printStackTrace();
			}
			

		}

	}

	private void saveFile() {

		FileChooser fileWriter = new FileChooser();
		
		File theFile = fileWriter.showSaveDialog(null);

		FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Text Files", "*.txt");
		fileWriter.getExtensionFilters().add(filter);

		String contents = "";

		for (Cat cat : listOfCats) {

			contents += cat.getName() + "%" + cat.getBirthDate().toString() + "%" + cat.getGender() + "\n";
		}

		if (theFile != null) {

			
			try (BufferedWriter br = new BufferedWriter(new FileWriter(theFile))) {
				contents = contents.replaceAll("\n", System.lineSeparator());
				br.write(contents);

			} catch (Exception e1) {
				
				e1.printStackTrace();
			}

		}

	}

	private static boolean isDateValid(String date) {
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			dateFormat.setLenient(false);
			dateFormat.parse(date);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}

	private LocalDate formaTotDate(String usersDate) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate date = LocalDate.parse(usersDate, formatter);

		return date;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
