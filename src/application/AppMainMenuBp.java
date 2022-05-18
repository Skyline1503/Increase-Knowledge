package application;

import java.io.File;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import export.LinkChallenges;
import export.WriteChallenges;
import javafx.animation.AnimationTimer;
import javafx.animation.RotateTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import json.Json;
import json.Link;
import json.Write;
import modele.CSVDataExport;
import modele.Card;
import modele.Category;
import modele.DataExport;
import modele.PDFDataExport;
import modele.Personne;
import modele.Question;
import modele.Shift;
import view.ViewAddCardAp;
import view.ViewAddChallengeAp;
import view.ViewAddPlayerAp;
import view.ViewAdminAccesAp;
import view.ViewAnswerAp;
import view.ViewBoardGameSp;
import view.ViewCardAdminAp;
import view.ViewChallengeAp;
import view.ViewCreditsBp;
import view.ViewDiceAp;
import view.ViewEndGameAp;
import view.ViewGlobalScoreAp;
import view.ViewMenuAp;
import view.ViewModifyCardAp;
import view.ViewPauseAp;
import view.ViewQuestionChoicesAp;
import view.ViewSettingsAp;

/*
 * 			PRODUCT BY
 * 
 * GODEMONT FLAVIAN 2BI B2 LA209224
 * 					&
 * MAZANO Y GONZALES MATHIAS 2BI B2 LA208312
 *  
 */

public class AppMainMenuBp extends BorderPane {
	private StackPane stackSp;
	private ViewMenuAp vwMenuAp;
	private ViewSettingsAp vwSettingsAp;
	private ViewCreditsBp vwCreditsBp;
	private ViewCardAdminAp vwCardAdminAp;
	private ViewAddPlayerAp vwAddPlayerAp;
	private ViewQuestionChoicesAp vwQuestionChoiceAp;
	private ViewBoardGameSp vwBoardGameSp;
	private ViewDiceAp vwDiceAp;
	private Shift shift;
	private ViewAnswerAp vwAnswerAp;
	private ViewPauseAp vwPauseAp;
	private ViewGlobalScoreAp vwGlobalScoreAp;
	private ViewAdminAccesAp vwAdminAccesAp;
	private ViewEndGameAp vwEndGameAp;
	private ViewAddCardAp vwAddCardAp;
	private ViewModifyCardAp vwModifyCardAp;
	private ViewChallengeAp vwChallengesAp;
	private ViewAddChallengeAp vwAddChallenge;
	private AnimationTimer timer;

	private int nbPlayers=0;
	private int nbTurns;
	private int isGood;
	private int idCate;
	private int nbQuest =0;
	private int nbChall =0;
	private int pos=1;
	private String theGoodIs;
	private String categGood;
	private boolean test2;
	/*
	 * Center the stackpane
	 */
	public AppMainMenuBp()
	{
		this.setCenter(getStackSp());
	}

	/**
	 * Returns the StackPane that contains all the views
	 * 
	 * @return The StackPane that contains all the views.
	 */
	public StackPane getStackSp() {
		if(stackSp==null)
		{
			stackSp = new StackPane();
			// Adding all the views to the stackpane.
			stackSp.getChildren().addAll(getVwMenuAp(),getVwCreditsBp(),getVwCardAdminAp(),getVwAddPlayerAp(),getVwQuestionChoiceAp()
					,getVwBoardGameSp(),getVwDiceAp(),getVwAnswerAp(),getVwPauseAp(),getVwGlobalScoreAp(), getVwSettingsAp()
					, getVwAdminAccesAp(), getVwEndGameAp(),getVwAddCardAp(), getVwModifyCardAp(),getVwChallengesAp(),getVwAddChallenge());
		}
		return stackSp;
	}	

	/**
	 * This function creates a new instance of the viewMenuAp class and sets the visibility of the view to
	 * true. It also creates event handlers for the buttons in the view
	 * 
	 * @return The viewMenuAp object.
	 */
	public ViewMenuAp getVwMenuAp() {
		if(vwMenuAp == null)
		{
			vwMenuAp = new ViewMenuAp();
			vwMenuAp.setVisible(true);

			// Creating an event handler for the credits button.
			vwMenuAp.getBtnCredits().setOnAction(new EventHandler<ActionEvent>() 
			{
				@Override
				public void handle(ActionEvent event) {
					getVwMenuAp().setVisible(false);
					getVwCreditsBp().setVisible(true);

				}
			});
			// Creating a new event handler for the exit button.
			vwMenuAp.getBtnExit().setOnAction(new EventHandler<ActionEvent>() {
				@Override 
				public void handle(ActionEvent e) {
					// Creating a new Alert object, setting the title, and then showing it.
					Alert alert = 
							new Alert(AlertType.WARNING, 
									"Quit?",
									ButtonType.YES, 
									ButtonType.NO);
					alert.setTitle("VOulez vous quitter");
					Optional<ButtonType> result = alert.showAndWait();

					// Closing the application.
					if (result.get() == ButtonType.YES) {
						javafx.application.Platform.exit();
					}
				}
			});
			// Setting the action for the button to call the method setVisible on the view.
			vwMenuAp.getBtnCard().setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					getVwMenuAp().setVisible(false);
					getVwCardAdminAp().setVisible(true);
				}
			});
			// Creating an event handler for the button "Play" in the menu.
			// When the button is clicked, the menu is hidden and the add player view is shown.
			vwMenuAp.getBtnPlay().setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					getVwAddPlayerAp().getData().clear();
					getVwEndGameAp().getData().clear();
					getVwAddPlayerAp().getPlayer().getLstPlayers().clear();
					getVwAddPlayerAp().getBtnPlay().setDisable(true);
					getVwMenuAp().setVisible(false);
					getVwAddPlayerAp().setVisible(true);
					getVwAddPlayerAp().getBtnAdd().setDisable(false);
				}
			});
			// Setting the onAction event for the button.
			vwMenuAp.getBtnSettings().setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					getVwMenuAp().setVisible(false);
					getVwSettingsAp().setVisible(true);

				}
			});
			// Setting the onAction event for the button.
			vwMenuAp.getBtnAdmin().setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					getVwMenuAp().setVisible(false);
					getVwAdminAccesAp().setVisible(true);
				}
			});
		}
		return vwMenuAp;
	}

	/**
	 * This function creates a new instance of the ViewCreditsBp class, and sets the visibility of the
	 * ViewCreditsBp to false. 
	 * It also sets the visibility of the MenuBp to true.
	 * 
	 * @return A ViewCreditsBp object.
	 */
	public ViewCreditsBp getVwCreditsBp() {
		if(vwCreditsBp == null)
		{
			vwCreditsBp = new ViewCreditsBp();
			vwCreditsBp.setVisible(false);
			// This is the code that will handle the event of the user clicking the back button.
			vwCreditsBp.getBtnBack().setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					getVwCreditsBp().setVisible(false);
					getVwMenuAp().setVisible(true);
				}
			});
		}
		return vwCreditsBp;
	}

	/**
	 * This function creates a new ViewCardAdminAp object and returns it.
	 * 
	 * @return The ViewCardAdminAp object.
	 */
	public ViewCardAdminAp getVwCardAdminAp() {
		if(vwCardAdminAp == null)
		{
			vwCardAdminAp = new ViewCardAdminAp();
			vwCardAdminAp.setVisible(false);

			// Creating an event handler for the back button.
			vwCardAdminAp.getBtnBack().setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					getVwCardAdminAp().setVisible(false);
					getVwMenuAp().setVisible(true);
				}
			});
			// Creating an event handler for the button "Add" in the view "CardAdminAp".
			vwCardAdminAp.getBtnAdd().setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					// Setting the visibility of the view to true, clearing the text fields, and setting the text of the
					// button to "NEXT".
					getVwAddCardAp().setVisible(true);
					getVwAddCardAp().getTxtfAuthor().setDisable(false);
					getVwAddCardAp().getTxtfAuthor().clear();
					getVwAddCardAp().getTxtfChoices1().clear();
					getVwAddCardAp().getTxtfChoices2().clear();
					getVwAddCardAp().getTxtfChoices3().clear();
					getVwAddCardAp().getTxtfInterr().clear();
					getVwAddCardAp().getRbTrue1().setSelected(false);
					getVwAddCardAp().getRbTrue2().setSelected(false);
					getVwAddCardAp().getRbTrue3().setSelected(false);
					getVwAddCardAp().getTxtfAuthor().setDisable(false);
					getVwAddCardAp().getBtnNext().setText("NEXT");
					getVwAddCardAp().getLblCateg().setText(Category.listCateg().get(nbQuest).toString());
				}
			});
			// A Java code that is used to modify a question.
			vwCardAdminAp.getBtnModify().setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					// Setting the author of the question to the textfield.
					getVwModifyCardAp().setVisible(true);
					getVwModifyCardAp().getTxtfAuthor().setDisable(false);
					// Iterating through a map and setting the text of a text field and the selected value of a radio
					// button.
					for(int j=0;j<=5;j++)
					{
						test2 = Category.listCateg().get(nbQuest).toString().equalsIgnoreCase(Link.getDeck().getListCards().get(getVwCardAdminAp().getNumCard()).getListQuestion().get(j).getCategory().toString());
						if(test2)
						{
							vwModifyCardAp.getLblCateg().setText(Category.listCateg().get(nbQuest).toString());
							vwModifyCardAp.getTxtfAuthor().setText(getVwCardAdminAp().getLblAuthors().getText());

							int i=0;
							// Iterating through a map and setting the text of the textfields and the selected value of the
							// radio buttons.
							for (Map.Entry<String, Boolean> entry : Link.getDeck().getListCards().get(getVwCardAdminAp().getNumCard()).getListQuestion().get(j).getChoices().entrySet()) {
								String key = entry.getKey();
								Boolean value = entry.getValue();
								if(i==0)
								{
									getVwModifyCardAp().getTxtfChoices1().setText(key);
									getVwModifyCardAp().getRbTrue1().setSelected(value);
								}
								else if(i==1)
								{
									getVwModifyCardAp().getTxtfChoices2().setText(key);
									getVwModifyCardAp().getRbTrue2().setSelected(value);
								}
								else
								{
									getVwModifyCardAp().getTxtfChoices3().setText(key);
									getVwModifyCardAp().getRbTrue3().setSelected(value);
								}
								i++;
							}
							getVwModifyCardAp().getTxtfInterr().setText(Link.getDeck().getListCards().get(ViewCardAdminAp.getNumCard()).getListQuestion().get(j).getInterrogation().toString());
							getVwModifyCardAp().getBtnNext().setText("NEXT");
							vwModifyCardAp.getLblError().setText("");
							getVwModifyCardAp().getLblCateg().setText(Category.listCateg().get(nbQuest).toString());
						}
					}
				}
				// The above code is setting the action for the buttons in the view.
			});
			vwCardAdminAp.getBtnChallenges().setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					vwCardAdminAp.setVisible(false);
					// Setting the visibility of the view to true.
					getVwChallengesAp().setVisible(true);
				}
			});
		}
		return vwCardAdminAp;
	}

	/**
	 * This function creates a new ViewAddPlayerAp object and returns it.
	 * 
	 * @return The viewAddPlayerAp object.
	 */
	public ViewAddPlayerAp getVwAddPlayerAp() {
		if(vwAddPlayerAp == null)
		{
			vwAddPlayerAp = new ViewAddPlayerAp();
			vwAddPlayerAp.setVisible(false);

			// Creating an event handler for the back button.
			vwAddPlayerAp.getBtnBack().setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					getVwAddPlayerAp().setVisible(false);
					getVwMenuAp().setVisible(true);
				}
			});
			// Adding the event handler for the "Play" button.
			vwAddPlayerAp.getBtnPlay().setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {	
					// Setting the visibility of the dice view to true.
					getVwDiceAp().setDisable(false);
					getVwSettingsAp().getMp().getMediaPlayer().stop();
					getVwAddPlayerAp().setVisible(false);
					getVwBoardGameSp().setVisible(true);
					getVwBoardGameSp().getQuestChoiceVw().setVisible(false);
					getVwBoardGameSp().getQuestChoiceVw().getBtnValidate().setDisable(true);
					getVwBoardGameSp().getHbCenter().getChildren().removeAll(getVwDiceAp(),getVwBoardGameSp().getGridGp());
					getVwBoardGameSp().getHbCenter().getChildren().addAll(getVwDiceAp(),getVwBoardGameSp().getGridGp());
					getVwDiceAp().setVisible(true);

					// Copying the list of players from the view to the model.
					getVwAddPlayerAp().copyList();
					Main.getPrimaryStage().setFullScreen(true);
					setNbPlayers(0);
					getTimer().stop();

					// Removing all the labels from the score view.
					getVwBoardGameSp().getScoreVw().getChildren().removeAll(getVwBoardGameSp().getScoreVw().getLblComputer(),getVwBoardGameSp().getScoreVw().getLblHistory(),
							getVwBoardGameSp().getScoreVw().getLblIdeas(),getVwBoardGameSp().getScoreVw().getLblLiterature(),getVwBoardGameSp().getScoreVw().getLblPlanet(),getVwBoardGameSp().getScoreVw().getLblScience());

					// Adding the player to the board.
					getVwBoardGameSp().getScoreVw().getCrlColorPlay().setFill(Color.web(getVwAddPlayerAp().getPlayer().getLstPlayers().get(getNbPlayers()).getColor().toString()));
					getVwBoardGameSp().getScoreVw().getLblPlayer().setText(getVwAddPlayerAp().getPlayer().getLstPlayers().get(getNbPlayers()).getName().toString());
					getVwBoardGameSp().getScoreVw().getLblScore().setText(""+getVwAddPlayerAp().getPlayer().getLstPlayers().get(getNbPlayers()).getTotalScore());
					getVwBoardGameSp().getScoreVw().getGood(getVwAddPlayerAp().getPlayer().getLstPlayers().get(getNbPlayers()).getLstCateg());

					// Moving the pawn
					for(int i=(getVwAddPlayerAp().getPlayer().getSizePlayer()-1);i>-1;i--)
					{
						getVwBoardGameSp().getGridGp().getChildren().remove(getVwAddPlayerAp().getPlayer().getLstPlayers().get(i).getPawn().getCircle());
						getVwBoardGameSp().getGridGp().add(getVwAddPlayerAp().getPlayer().getLstPlayers().get(i).getPawn().getCircle(), vwDiceAp.getShift(getVwAddPlayerAp().getPlayer().getLstPlayers().get(i).getTotalDice()).getAxeX(), vwDiceAp.getShift(getVwAddPlayerAp().getPlayer().getLstPlayers().get(getNbPlayers()).getTotalDice()).getAxeY());
					}
				}
			});
		}
		return vwAddPlayerAp;
	}

	/**
	 * This function creates a new ViewQuestionChoicesAp object and sets it to the variable
	 * vwQuestionChoice. 
	 * If the variable vwQuestionChoice is null, then it creates a new ViewQuestionChoicesAp object and
	 * sets it to the variable vwQuestionChoice. 
	 * 
	 * @return The ViewQuestionChoicesAp class.
	 */
	public ViewQuestionChoicesAp getVwQuestionChoiceAp() {
		if(vwQuestionChoiceAp == null)
		{
			vwQuestionChoiceAp = new ViewQuestionChoicesAp();
			vwQuestionChoiceAp.setVisible(false);

			// The above code is checking if the chrono is negative. If it is negative, then the answer is too
			// late.
			if((getVwBoardGameSp().getQuestChoiceVw().getChrono() < 0))
			{
				getVwQuestionChoiceAp().getVwAnswer().setVisible(true);
				getVwQuestionChoiceAp().getVwAnswer().getRect().setFill(Color.RED);
				getVwQuestionChoiceAp().getVwAnswer().getLblTheGood().setText("TO LATE");
				getVwQuestionChoiceAp().getBoardGaVw().getQuestChoiceVw().setVisible(false);
				getVwQuestionChoiceAp().getBoardGaVw().getDiceVw().setDisable(true);
			}
		}
		return vwQuestionChoiceAp;
	}

	/**
	 * This function creates a new ViewBoardGameSp object and returns it
	 * 
	 * @return The view of the board game.
	 */
	public ViewBoardGameSp getVwBoardGameSp() {
		if(vwBoardGameSp == null)
		{
			vwBoardGameSp = new ViewBoardGameSp();
			vwBoardGameSp.setVisible(false);		
			// Creating a button that will validate the choice of the player.
			vwBoardGameSp.getQuestChoiceVw().getBtnValidate().setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {

					for(int j=0;j<=5;j++)
					{
						if(vwDiceAp.getBoardgameVw().getListCateg().get(getVwAddPlayerAp().getPlayer().getLstPlayers().get(getNbPlayers()).getTotalDice()).toString().equalsIgnoreCase(Link.getDeck().getListCards().get(getNbTurns()).getListQuestion().get(j).getCategory().toString()))
						{
							setCategGood(vwDiceAp.getBoardgameVw().getListCateg().get(getVwAddPlayerAp().getPlayer().getLstPlayers().get(getNbPlayers()).getTotalDice()).toString());

							int i=1;
							// Displaying the question and the category of the question.
							getVwBoardGameSp().getQuestChoiceVw().getLblQuestion().setText(Link.getDeck().getListCards().get(getNbTurns()).getListQuestion().get(j).getInterrogation().toString());
							getVwBoardGameSp().getQuestChoiceVw().getLblCategory().setText(Link.getDeck().getListCards().get(getNbTurns()).getListQuestion().get(j).getCategory().toString());
							// Creating the choices for the questions.
							for (Map.Entry<String, Boolean> entry : Link.getDeck().getListCards().get(getNbTurns()).getListQuestion().get(j).getChoices().entrySet()) 
							{
								String key = entry.getKey();
								if(entry.getValue()) {
									setIsGood(i);
									setTheGoodIs(entry.getKey());
								}
								i++;
							}
							break;
						}
					} 
					if (vwDiceAp.getBoardgameVw().getListCateg().get(getVwAddPlayerAp().getPlayer().getLstPlayers().get(getNbPlayers()).getTotalDice()).toString().equalsIgnoreCase("CHALLENGES"))
					{
						setIsGood(1);
					}
					// Checking if the player has chosen the good answer. If he has, he gets a good answer and a good
					// score.
					// If he hasn't, he gets a bad answer and a bad score.
					getVwDiceAp().setDisable(false);
					vwBoardGameSp.getQuestChoiceVw().setVisible(false);
					getVwBoardGameSp().getBtnQuit().setDisable(false);
					getVwBoardGameSp().getBtnScore().setDisable(false);
					// Checking if the player has selected the good answer. If he answered above 30 seconds
					// the score is increased by 50 points.
					// Otherwise it increases by 25 points.
					if(getVwBoardGameSp().getQuestChoiceVw().getChoice1().isSelected() && getIsGood() == 1
							|| getVwBoardGameSp().getQuestChoiceVw().getChoice2().isSelected() && getIsGood() == 2
							|| getVwBoardGameSp().getQuestChoiceVw().getChoice3().isSelected() && getIsGood() == 3) {
						getVwAnswerAp().setVisible(true);
						getVwAnswerAp().getRect().setFill(Color.GREEN);
						// Checking if the player has rolled a CHALLENGES or DEPART and if so, it is displaying a message.
						if(vwBoardGameSp.getListCateg().get(getVwAddPlayerAp().getPlayer().getLstPlayers().get(getNbPlayers()).getTotalDice())=="CHALLENGES")
						{
							getVwAnswerAp().getLblTheGood().setText("NICE JOB, you are the king of challenges");
						}
						else {
							getVwAnswerAp().getLblTheGood().setText("GOOD ANSWER");
						}
						// Setting the value of the isGood property of the VwAnswerAp object to true.
						getVwAnswerAp().isGood(true);

						// Incrementing the number of correct answers for the player.
						getVwAddPlayerAp().getPlayer().getLstPlayers().get(getNbPlayers()).augNbCorrect();

						// Playing a sound when the user gets the answer good.
						getVwSettingsAp().getMp().setMedia("/music/true.wav");
						getVwSettingsAp().getMp().setMediaPlayer(getVwSettingsAp().getMp().getMedia());
						getVwSettingsAp().getMp().getMediaPlayer().setVolume(getVwSettingsAp().getSlider().getValue());
						getVwSettingsAp().getMp().getMediaPlayer().setMute(getVwSettingsAp().getCbMute().isSelected());
						getVwSettingsAp().getMp().getMediaPlayer().play();

						getVwAddPlayerAp().getPlayer().getLstPlayers().get(getNbPlayers()).addLstCate(getCategGood());
						if(getVwBoardGameSp().getQuestChoiceVw().getChrono() >=15) {
							if(	getVwAddPlayerAp().getPlayer().getLstPlayers().get(getNbPlayers()).getTotalDice()==0) {
								getVwAddPlayerAp().getPlayer().getLstPlayers().get(getNbPlayers()).setTotalScore(100);
							}
							else {
								getVwAddPlayerAp().getPlayer().getLstPlayers().get(getNbPlayers()).setTotalScore(50);
							}
						}
						else {
							if(	getVwAddPlayerAp().getPlayer().getLstPlayers().get(getNbPlayers()).getTotalDice()==0) {
								getVwAddPlayerAp().getPlayer().getLstPlayers().get(getNbPlayers()).setTotalScore(50);
							}
							else {
								getVwAddPlayerAp().getPlayer().getLstPlayers().get(getNbPlayers()).setTotalScore(25);
							}
						}
					}
					else {
						// Setting the visibility of the view to true.
						getVwAnswerAp().setVisible(true);

						// Incrementing the number of incorrect answers for the player.
						getVwAddPlayerAp().getPlayer().getLstPlayers().get(getNbPlayers()).augNbIncorrect();

						// Playing a sound when the user gets the answer wrong.
						getVwAnswerAp().getRect().setFill(Color.RED);
						getVwSettingsAp().getMp().setMedia("/music/false.wav");
						getVwAnswerAp().isGood(false);
						getVwSettingsAp().getMp().setMediaPlayer(getVwSettingsAp().getMp().getMedia());
						getVwSettingsAp().getMp().getMediaPlayer().setMute(getVwSettingsAp().getCbMute().isSelected());
						getVwSettingsAp().getMp().getMediaPlayer().setVolume(getVwSettingsAp().getSlider().getValue());
						getVwSettingsAp().getMp().getMediaPlayer().play();

						// The above code is setting the width of the label to 500 and setting the wrap text to true.
						getVwAnswerAp().getLblTheGood().setPrefWidth(500);
						getVwAnswerAp().getLblTheGood().setWrapText(true);
						// Checking if the player has rolled a CHALLENGES or DEPART and if so, it will display a message.
						if(vwBoardGameSp.getListCateg().get(getVwAddPlayerAp().getPlayer().getLstPlayers().get(getNbPlayers()).getTotalDice())=="CHALLENGES")
						{
							getVwAnswerAp().getLblTheGood().setText("You can do better, it's disappointing");
						}
						else {
							getVwAnswerAp().getLblTheGood().setText("The Good Answer was : \n"+getTheGoodIs());
						}
					}		
					// check if the list of categories is complete
					if(getVwAddPlayerAp().getPlayer().getLstPlayers().get(getNbPlayers()).getLstCateg().size()>=6)
					{
						getVwAddPlayerAp().getPlayer().getLstPlayers().get(getNbPlayers()).setTotalScore(250);
						Collections.sort(getVwAddPlayerAp().getPlayer().getLstPlayers(),getVwAddPlayerAp().getPlayer().getLstPlayers().get(getNbPlayers()).SCOORE);
						// Adding the players to the end game table.

						for(int i=(getVwAddPlayerAp().getPlayer().getSizePlayer()-1);i>-1;i--)
						{
							// Setting the position of the player in the list of players.
							getVwAddPlayerAp().getPlayer().getLstPlayers().get(i).setPosition(getPos());
							getVwEndGameAp().getData().add(getVwAddPlayerAp().getPlayer().getLstPlayers().get(i));
							setPos(1);
						}

						// Setting the data in the table to the data in the observable list.
						getVwEndGameAp().getTable().setItems(getVwEndGameAp().getData());

						// Setting the visibility of the answer panel to false, the visibility of the board game panel to
						// false, and the visibility of the end game panel to true.
						getVwAnswerAp().setVisible(false);
						getVwBoardGameSp().setVisible(false);
						getVwEndGameAp().setVisible(true);
					}
					// Adding a card to the deck, and then it is going to the next player.
					nbTurns++;
					if(getNbTurns()==Link.getDeck().getListCards().size()) {
						nbTurns=0;
					}
					nbPlayers++;
					if(getNbPlayers()>=getVwAddPlayerAp().getPlayer().getSizePlayer())
					{
						nbPlayers = 0;
					}

					// Removing all the labels from the score view.
					getVwBoardGameSp().getScoreVw().getChildren().removeAll(getVwBoardGameSp().getScoreVw().getLblComputer(),getVwBoardGameSp().getScoreVw().getLblHistory(),
							getVwBoardGameSp().getScoreVw().getLblIdeas(),getVwBoardGameSp().getScoreVw().getLblLiterature(),getVwBoardGameSp().getScoreVw().getLblPlanet(),getVwBoardGameSp().getScoreVw().getLblScience());

					// Adding the player to the board.
					getVwBoardGameSp().getScoreVw().getCrlColorPlay().setFill(Color.web(getVwAddPlayerAp().getPlayer().getLstPlayers().get(getNbPlayers()).getColor().toString()));
					getVwBoardGameSp().getScoreVw().getLblPlayer().setText(getVwAddPlayerAp().getPlayer().getLstPlayers().get(getNbPlayers()).getName().toString());
					getVwBoardGameSp().getScoreVw().getGood(getVwAddPlayerAp().getPlayer().getLstPlayers().get(getNbPlayers()).getLstCateg());
					getVwBoardGameSp().getScoreVw().getLblScore().setText(""+getVwAddPlayerAp().getPlayer().getLstPlayers().get(getNbPlayers()).getTotalScore());
					getTimer().stop();
					getVwBoardGameSp().getQuestChoiceVw().setChrono(30);
				}
			});
			// Creating an event handler for the button "Quit" in the board game view.
			// When the button is clicked, the board game view is set to invisible and the add player view is
			// set to visible.
			vwBoardGameSp.getBtnQuit().setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					// Removing the pawns from the board.
					for(int i=(getVwAddPlayerAp().getPlayer().getSizePlayer()-1);i>-1;i--)
					{
						getVwBoardGameSp().getGridGp().getChildren().remove(getVwAddPlayerAp().getPlayer().getLstPlayers().get(i).getPawn().getCircle());
					}
					Collections.sort(getVwAddPlayerAp().getPlayer().getLstPlayers(),getVwAddPlayerAp().getPlayer().getLstPlayers().get(getNbPlayers()).SCOORE);
					// Adding the players to the end game table.

					for(int i=(getVwAddPlayerAp().getPlayer().getSizePlayer()-1);i>-1;i--)
					{
						// Setting the position of the player in the list of players.
						getVwAddPlayerAp().getPlayer().getLstPlayers().get(i).setPosition(getPos());
						getVwEndGameAp().getData().add(getVwAddPlayerAp().getPlayer().getLstPlayers().get(i));
						setPos(1);
					}

					// Setting the data in the table to the data in the observable list.
					getVwEndGameAp().getTable().setItems(getVwEndGameAp().getData());

					// Setting the visibility of the answer panel to false, the visibility of the board game panel to
					// false, and the visibility of the end game panel to true.
					getVwAnswerAp().setVisible(false);
					getVwBoardGameSp().setVisible(false);
					getVwPauseAp().setVisible(false);
					getVwEndGameAp().setVisible(true);
				}
			});
			// Setting the button to be enabled when the user clicks on the choice 1.
			getVwBoardGameSp().getQuestChoiceVw().getChoice1().setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {	
					getVwBoardGameSp().getQuestChoiceVw().getBtnValidate().setDisable(false);
				}
			});
			// Setting the button to be enabled when the user clicks on the choice 2.
			getVwBoardGameSp().getQuestChoiceVw().getChoice2().setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {	
					getVwBoardGameSp().getQuestChoiceVw().getBtnValidate().setDisable(false);
				}
			});
			// Setting the button to be enabled when the user clicks on the choice 3.
			getVwBoardGameSp().getQuestChoiceVw().getChoice3().setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {	
					getVwBoardGameSp().getQuestChoiceVw().getBtnValidate().setDisable(false);
				}
			});
			// Creating an event handler for the button Score.
			getVwBoardGameSp().getBtnScore().setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// Clearing the data from the table.
					getVwGlobalScoreAp().getData().clear();
					// Sorting the list of players by their score.
					Collections.sort(getVwAddPlayerAp().getPlayer().getLstPlayers(),getVwAddPlayerAp().getPlayer().getLstPlayers().get(getNbPlayers()).SCOORE);
					// Adding the players to the global score list.

					for(int i=(getVwAddPlayerAp().getPlayer().getSizePlayer()-1);i>-1;i--)
					{
						// Setting the position of the player in the list of players.
						getVwAddPlayerAp().getPlayer().getLstPlayers().get(i).setPosition(getPos());
						getVwGlobalScoreAp().getData().add(getVwAddPlayerAp().getPlayer().getLstPlayers().get(i));
						setPos(1);
					}

					// Setting the data in the table and making the table visible.
					getVwGlobalScoreAp().getTable().setItems(getVwGlobalScoreAp().getData());
					getVwGlobalScoreAp().setVisible(true);
				}
			});
		}
		return vwBoardGameSp;
	}

	/**
	 * This function creates a ViewDiceAp object and returns it
	 * 
	 * @return The view of the dice.
	 */
	public ViewDiceAp getVwDiceAp() {
		if(vwDiceAp == null)
		{
			vwDiceAp = new ViewDiceAp();
			vwDiceAp.setVisible(false);

			// The above code is adding a graphic to the button.
			Image imgDie = new Image("img/button/imgDe/Die.png");
			ImageView viewDie = new ImageView(imgDie);
			getVwBoardGameSp().getDiceVw().getBtnThrow().setGraphic(viewDie);
			/*CSS settings to make the button round*/
			getVwBoardGameSp().getDiceVw().getBtnThrow().getStyleClass().add("recDice");

			// The code above is the action of the button "Throw the dice".
			vwDiceAp.getBtnThrow().setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event){
					// Setting the chronometer to 30 seconds.
					getVwBoardGameSp().getQuestChoiceVw().getLblChrono().setText("00:30");
					getTimer().start();

					// Disabling the dice and the quit button.
					vwDiceAp.setDisable(true);
					getVwBoardGameSp().getBtnQuit().setDisable(true);
					getVwBoardGameSp().getBtnScore().setDisable(true);
					vwBoardGameSp.getQuestChoiceVw().setVisible(true);

					// The above code is disabling the buttons and setting the selected value to false.
					vwBoardGameSp.getQuestChoiceVw().getChoice1().setSelected(false);
					vwBoardGameSp.getQuestChoiceVw().getChoice2().setSelected(false);
					vwBoardGameSp.getQuestChoiceVw().getChoice3().setSelected(false);
					vwBoardGameSp.getQuestChoiceVw().getBtnValidate().setDisable(true);

					RotateTransition transition  = new RotateTransition(Duration.seconds(1), vwDiceAp.getBtnThrow());
					transition.setByAngle(360);
					transition.play();

					// Creating a random number between 1 and 6 and then using that number to select an image from the
					// imgDe folder.
					int throwDice = vwDiceAp.getRandomNum().getRand();
					Image imgDie = new Image("img/button/imgDe/"+throwDice+"Die.png");
					// Adding the total dice of the player to the total dice of the player.
					getVwAddPlayerAp().getPlayer().getLstPlayers().get(getNbPlayers()).setTotalDice(throwDice);
					if(getVwAddPlayerAp().getPlayer().getLstPlayers().get(getNbPlayers()).getTotalDice()>=42) {
						getVwAddPlayerAp().getPlayer().getLstPlayers().get(getNbPlayers()).setTotalDice(-42);
					}
					getShift(getVwAddPlayerAp().getPlayer().getLstPlayers().get(getNbPlayers()).getTotalDice());

					// Moving the pawn
					getVwBoardGameSp().getGridGp().getChildren().remove(getVwAddPlayerAp().getPlayer().getLstPlayers().get(nbPlayers).getPawn().getCircle());
					getVwBoardGameSp().getGridGp().add(getVwAddPlayerAp().getPlayer().getLstPlayers().get(nbPlayers).getPawn().getCircle(), vwDiceAp.getShift(getVwAddPlayerAp().getPlayer().getLstPlayers().get(getNbPlayers()).getTotalDice()).getAxeX(), vwDiceAp.getShift(getVwAddPlayerAp().getPlayer().getLstPlayers().get(getNbPlayers()).getTotalDice()).getAxeY());

					// Creating a new ImageView object and setting the graphic of the button to the image of the die.
					ImageView viewDie = new ImageView(imgDie);
					vwDiceAp.getBtnThrow().setGraphic(viewDie);

					// Adding a CSS class to the button.
					vwDiceAp.getBtnThrow().getStyleClass().add("recDice");

					//Image size
					viewDie.setFitHeight(190);
					viewDie.setFitWidth(190);

					for(int j=0;j<=5;j++)
					{
						if(vwDiceAp.getBoardgameVw().getListCateg().get(getVwAddPlayerAp().getPlayer().getLstPlayers().get(getNbPlayers()).getTotalDice()).toString().equalsIgnoreCase(Link.getDeck().getListCards().get(getNbTurns()).getListQuestion().get(j).getCategory().toString()))
						{
							int i=1;
							// Displaying the question and the category of the question.
							getVwBoardGameSp().getQuestChoiceVw().getLblQuestion().setText(Link.getDeck().getListCards().get(getNbTurns()).getListQuestion().get(j).getInterrogation().toString());
							getVwBoardGameSp().getQuestChoiceVw().getLblCategory().setText(Link.getDeck().getListCards().get(getNbTurns()).getListQuestion().get(j).getCategory().toString());
							// Creating the choices for the questions.
							for (Map.Entry<String, Boolean> entry : Link.getDeck().getListCards().get(getNbTurns()).getListQuestion().get(j).getChoices().entrySet()) 
							{
								String key = entry.getKey();
								if(i==1)
								{
									getVwBoardGameSp().getQuestChoiceVw().getChoice1().setText(key);
									getVwBoardGameSp().getQuestChoiceVw().getChoice1().setWrapText(true);
								}
								else if(i==2)
								{
									getVwBoardGameSp().getQuestChoiceVw().getChoice2().setText(key);
									getVwBoardGameSp().getQuestChoiceVw().getChoice2().setWrapText(true);
								}
								else
								{
									getVwBoardGameSp().getQuestChoiceVw().getChoice3().setVisible(true);
									getVwBoardGameSp().getQuestChoiceVw().getChoice3().setText(key);
									getVwBoardGameSp().getQuestChoiceVw().getChoice3().setWrapText(true);
								}
								i++;
							}
							break;
						}
					} 
					if (vwDiceAp.getBoardgameVw().getListCateg().get(getVwAddPlayerAp().getPlayer().getLstPlayers().get(getNbPlayers()).getTotalDice()).toString().equalsIgnoreCase("CHALLENGES"))
					{
						getVwBoardGameSp().getQuestChoiceVw().getLblCategory().setText("CHALLENGE");
						getVwBoardGameSp().getQuestChoiceVw().getLblQuestion().setText(LinkChallenges.getChallenges().getListChallenges().get(getNbChall()));
						getVwBoardGameSp().getQuestChoiceVw().getChoice1().setText("CHALLENGE DONE");
						getVwBoardGameSp().getQuestChoiceVw().getChoice2().setText("CHALLENGE NOT DONE");
						getVwBoardGameSp().getQuestChoiceVw().getChoice3().setVisible(false);
						getVwBoardGameSp().getQuestChoiceVw().getLblChrono().setText("");
						getTimer().stop();
						setNbChall(1);
					}

					// Removing the questChoiceVw from the view and then adding it back to the view.
					getVwBoardGameSp().getChildren().remove(getVwBoardGameSp().getQuestChoiceVw());
					getVwBoardGameSp().getChildren().remove(getVwBoardGameSp().getHbCenter());
					getVwBoardGameSp().getChildren().add(getVwBoardGameSp().getHbCenter());
					getVwBoardGameSp().getChildren().add(getVwBoardGameSp().getQuestChoiceVw());
				}
			});
		}
		return vwDiceAp;
	}

	/**
	 * This function creates a new ViewAnswer object if one does not exist, and returns it
	 * 
	 * @return The ViewAnswer object.
	 */
	public ViewAnswerAp getVwAnswerAp() {
		if(vwAnswerAp == null)
		{
			vwAnswerAp = new ViewAnswerAp();
			vwAnswerAp.setVisible(false);
			// Creating an event handler for the button Quit.
			vwAnswerAp.getBtnQuit().setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					getVwAnswerAp().setVisible(false);
				}
			});
		}
		return vwAnswerAp;
	}

	/**
	 * This function creates a new ViewPauseAp object if it doesn't exist, and returns it. 
	 * If it does exist, it returns the existing object.
	 * 
	 * @return The ViewPauseAp object.
	 */
	public ViewPauseAp getVwPauseAp() {
		if(vwPauseAp == null)
		{
			vwPauseAp = new ViewPauseAp();
			vwPauseAp.setVisible(false);
			// Adding a key listener to the board view.
			getVwBoardGameSp().setOnKeyPressed((EventHandler<? super KeyEvent>) new EventHandler<KeyEvent>() {
				@Override
				public void handle(KeyEvent t) {
					KeyCode key = t.getCode();
					if (key == KeyCode.ESCAPE){
						vwPauseAp.setVisible(true);
						Main.getPrimaryStage().setFullScreen(true);
						vwPauseAp.getCbMute().setSelected(getVwSettingsAp().getCbMute().isSelected());
						getTimer().stop();
					}
				}
			});
			// Creating an event handler for the button that is in the PauseView.
			vwPauseAp.getBtnQuit().setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					getVwPauseAp().setVisible(false);
					if(getVwBoardGameSp().getQuestChoiceVw().getChrono() != 30) {
						getTimer().start();
					}
				}
			});
			// Creating an event handler for the button that is in the PauseView.
			vwPauseAp.getBtnExit().setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					Collections.sort(getVwAddPlayerAp().getPlayer().getLstPlayers(),getVwAddPlayerAp().getPlayer().getLstPlayers().get(getNbPlayers()).SCOORE);
					// Adding the players to the end game table.

					for(int i=(getVwAddPlayerAp().getPlayer().getSizePlayer()-1);i>-1;i--)
					{
						// Setting the position of the player in the list of players.
						getVwAddPlayerAp().getPlayer().getLstPlayers().get(i).setPosition(getPos());
						getVwEndGameAp().getData().add(getVwAddPlayerAp().getPlayer().getLstPlayers().get(i));
						setPos(1);
					}

					// Setting the data in the table to the data in the observable list.
					getVwEndGameAp().getTable().setItems(getVwEndGameAp().getData());

					// Setting the visibility of the answer panel to false, the visibility of the board game panel to
					// false, and the visibility of the end game panel to true.
					getVwAnswerAp().setVisible(false);
					getVwBoardGameSp().setVisible(false);
					getVwPauseAp().setVisible(false);
					getVwEndGameAp().setVisible(true);
				}
			});			
			// Creating an event handler for the button that is in the PauseView.
			vwPauseAp.getBtnMenu().setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					// Setting the visibility of the pause screen to false, the visibility of the board game screen to
					// false, and the visibility of the menu screen to true.
					getVwPauseAp().setVisible(false);
					getVwBoardGameSp().setVisible(false);
					getVwMenuAp().setVisible(true);
					// Moving the pawn
					for(int i=(getVwAddPlayerAp().getPlayer().getSizePlayer()-1);i>-1;i--)
					{
						getVwBoardGameSp().getGridGp().getChildren().remove(getVwAddPlayerAp().getPlayer().getLstPlayers().get(i).getPawn().getCircle());
					}
				}
			});
			// Setting the checkbox in the settings view to the same value as the checkbox in the pause view.
			vwPauseAp.getCbMute().setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					getVwSettingsAp().getCbMute().setSelected(getVwPauseAp().getCbMute().isSelected());
				}
			});
		}
		return vwPauseAp;
	}

	/**
	 * This function creates a new instance of the ViewGlobalScore class if it doesn't already exist. 
	 * If it does exist, it returns the existing instance
	 * 
	 * @return A ViewGlobalScore object.
	 */
	public ViewGlobalScoreAp getVwGlobalScoreAp() {
		if(vwGlobalScoreAp == null)
		{
			vwGlobalScoreAp = new ViewGlobalScoreAp();
			vwGlobalScoreAp.setVisible(false);
			// Creating an event handler for the button Quit.
			vwGlobalScoreAp.getBtnQuit().setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					getVwGlobalScoreAp().setVisible(false);
				}
			});
			// The above code is creating an event handler for the button.
			vwGlobalScoreAp.getBtnPdf().setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// Exporting the data from the view to a PDF file.
					try {
						DataExport d = new PDFDataExport(); 
						FileChooser fc = d.FileChooserOpen();
						File f = d.openFile(fc);
						d.writeData(getVwGlobalScoreAp().getData(), f);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					// Hiding the view. 
					getVwGlobalScoreAp().setVisible(false);
				}

				private void DataExport(ObservableList<Personne> data) {
					// TODO Auto-generated method stub

				}
			});
			// The above code is creating an event handler for the button.
			vwGlobalScoreAp.getBtnCsv().setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// Exporting the data from the table to a CSV file.
					try {
						DataExport d = new CSVDataExport(); 
						FileChooser fc = d.FileChooserOpen();
						File f = d.openFile(fc);
						d.writeData(getVwGlobalScoreAp().getData(), f);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					// Hiding the view.
					getVwGlobalScoreAp().setVisible(false);
				}
			});
		}
		return vwGlobalScoreAp;
	}

	/**
	 * > This function returns the ViewSettingsAp object, and if it doesn't exist, it creates it, sets it
	 * to invisible, and adds an event handler to the back button
	 * 
	 * @return The ViewSettingsAp object.
	 */
	public ViewSettingsAp getVwSettingsAp() {
		if(vwSettingsAp == null)
		{
			vwSettingsAp = new ViewSettingsAp();
			vwSettingsAp.setVisible(false);

			// The above code is an event handler for the back button. When the back button is clicked, the
			// settings view is set to invisible and the menu view is set to visible.
			vwSettingsAp.getBtnBack().setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					getVwSettingsAp().setVisible(false);
					getVwMenuAp().setVisible(true);
				}
			});
			// Exporting the JSON file.
			vwSettingsAp.getBtnExportJSON().setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					Json.ExportJson();
				}
			});
			// Importing a JSON file.
			vwSettingsAp.getBtnImportJSON().setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					Json.ImportJson();
				}
			});
		}
		return vwSettingsAp;
	}

	/**
	 * It creates the viewAdminAccesAp if it doesn't exist yet, and then it sets the buttons' actions
	 * 
	 * @return The viewAdminAccesAp
	 */
	public ViewAdminAccesAp getVwAdminAccesAp() {
		if(vwAdminAccesAp == null)
		{
			vwAdminAccesAp = new ViewAdminAccesAp();
			vwAdminAccesAp.setVisible(false);
			// Setting the action of the button to go back to the menu.
			vwAdminAccesAp.getBtnBack().setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					getVwAdminAccesAp().setVisible(false);
					getVwMenuAp().setVisible(true);
				}
			});
			vwAdminAccesAp.getBtnConnexion().setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					// Checking if the username and password are correct. If they are, it will allow the user to
					// access the admin menu.
					if(getVwAdminAccesAp().getTxtUsers().getText().equals("admin") == true && getVwAdminAccesAp().getTxtPassword().getText().equals("helha") == true)
					{
						getVwAdminAccesAp().getBtnDeconnexion().setDisable(false);
						getVwAdminAccesAp().getBtnConnexion().setDisable(true);
						getVwAdminAccesAp().getTxtUsers().clear();
						getVwAdminAccesAp().getTxtPassword().clear();
						getVwAdminAccesAp().setVisible(false);
						getVwMenuAp().setVisible(true);
						getVwAdminAccesAp().getLblAcces().setText("");
						getVwAdminAccesAp().getLblConnexion().setText("You are logged in as an administrator\n"
								+ "You can modify, add or remove cards\n"
								+ " in the new button which has just appeared \n"
								+ "in the main menu.");
						getVwCardAdminAp().getBtnAdd().setDisable(false);
						getVwCardAdminAp().getBtnDelete().setDisable(false);
						getVwCardAdminAp().getBtnModify().setDisable(false);
						getVwChallengesAp().getBtnAdd().setDisable(false);
						getVwChallengesAp().getBtnDelete().setDisable(false);
					}
					// Checking if the user is an admin or a simple user.
					else if(getVwAdminAccesAp().getTxtUsers().getText().equals("user") == true && getVwAdminAccesAp().getTxtPassword().getText().equals("helha") == true)
					{
						getVwAdminAccesAp().getBtnDeconnexion().setDisable(false);
						getVwAdminAccesAp().getBtnConnexion().setDisable(true);
						getVwAdminAccesAp().getTxtUsers().clear();
						getVwAdminAccesAp().getTxtPassword().clear();
						getVwAdminAccesAp().setVisible(false);
						getVwMenuAp().setVisible(true);
						getVwCardAdminAp().getBtnAdd().setDisable(true);
						getVwCardAdminAp().getBtnDelete().setDisable(true);
						getVwAdminAccesAp().getLblAcces().setText("");
						getVwAdminAccesAp().getLblConnexion().setText("You are logged in as an simple user\n"
								+ "You can modify cards\n"
								+ " in the new button which has just appeared \n"
								+ "in the main menu.");
						getVwCardAdminAp().getBtnAdd().setDisable(false);
						getVwCardAdminAp().getBtnDelete().setDisable(true);
						getVwCardAdminAp().getBtnModify().setDisable(true);
						getVwChallengesAp().getBtnAdd().setDisable(false);
						getVwChallengesAp().getBtnDelete().setDisable(true);
					}
					else
					{
						// Setting the text of the label to "INCORRECT"
						getVwAdminAccesAp().getLblAcces().setText("INCORRECT");
					}
				}
			});
			// A Java code that is used to disconnect the user from the application.
			vwAdminAccesAp.getBtnDeconnexion().setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					// Disabling the disconnect button and enabling the connect button. It is also making the modify
					// button invisible and the card admin button invisible. It is also changing the text of the
					// connection label.
					getVwAdminAccesAp().getBtnDeconnexion().setDisable(true);
					getVwAdminAccesAp().getBtnConnexion().setDisable(false);
					getVwCardAdminAp().getBtnAdd().setDisable(false);
					getVwCardAdminAp().getBtnDelete().setDisable(false);
					getVwAdminAccesAp().getLblConnexion().setText("You are not connected");
					getVwCardAdminAp().getBtnAdd().setDisable(true);
					getVwCardAdminAp().getBtnDelete().setDisable(true);
					getVwCardAdminAp().getBtnModify().setDisable(true);
					getVwChallengesAp().getBtnAdd().setDisable(true);
					getVwChallengesAp().getBtnDelete().setDisable(true);
				}
			});
		}
		return vwAdminAccesAp;
	}

	/**
	 * It creates a new instance of the end game view if it doesn't exist yet, and then it creates an
	 * event handler for the "Quit" button in the end game view
	 * 
	 * @return The view of the end of the game.
	 */
	public ViewEndGameAp getVwEndGameAp() {
		if(vwEndGameAp == null)
		{
			vwEndGameAp = new ViewEndGameAp();
			vwEndGameAp.setVisible(false);
			// Creating an event handler for the button "Quit" in the board game view.
			// When the button is clicked, the board game view is set to invisible and the add player view is
			// set to visible.
			vwEndGameAp.getBtnQuit().setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					// Removing the pawns from the board.
					for(int i=(getVwAddPlayerAp().getPlayer().getSizePlayer()-1);i>-1;i--)
					{
						getVwBoardGameSp().getGridGp().getChildren().remove(getVwAddPlayerAp().getPlayer().getLstPlayers().get(i).getPawn().getCircle());
					}
					// Playing the music in the background.
					getVwSettingsAp().getMp().setMedia("/music/fond.wav");
					getVwSettingsAp().getMp().setMediaPlayer(getVwSettingsAp().getMp().getMedia());
					getVwSettingsAp().getMp().getMediaPlayer().setMute(getVwSettingsAp().getCbMute().isSelected());
					getVwSettingsAp().getMp().getMediaPlayer().setVolume(getVwSettingsAp().getSlider().getValue());
					getVwSettingsAp().getMp().getMediaPlayer().play();
					getVwSettingsAp().getMp().getMediaPlayer().setAutoPlay(true);
					getVwSettingsAp().getMp().getMediaPlayer().setCycleCount(MediaPlayer.INDEFINITE);
					getVwEndGameAp().setVisible(false);
					getVwMenuAp().setVisible(true);				
				}
			});
			// Creating an event handler for the button.
			vwEndGameAp.getBtnCsv().setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event)  {
					// Exporting the list of players to a CSV file.
					try {
						DataExport d = new CSVDataExport(); 
						FileChooser fc = d.FileChooserOpen();
						File f = d.openFile(fc);
						d.writeData(getVwEndGameAp().getData(),f);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			// The above code is exporting the data from the view to a PDF file.
			vwEndGameAp.getBtnPdf().setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event)  {
					// Exporting the data from the view to a PDF file.
					try {
						DataExport d = new PDFDataExport(); 
						FileChooser fc = d.FileChooserOpen();
						File f = d.openFile(fc);
						d.writeData(getVwEndGameAp().getData(), f);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		}
		return vwEndGameAp;
	}

	/**
	 * It creates a new card and adds it to the deck
	 * 
	 * @return The method returns the viewAddCardAp.
	 */
	public ViewAddCardAp getVwAddCardAp() {
		if(vwAddCardAp == null)
		{
			vwAddCardAp = new ViewAddCardAp();
			vwAddCardAp.setVisible(false);
			Card c = new Card();
			// A button that allows the user to quit the pain.
			vwAddCardAp.getBtnQuit().setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					vwAddCardAp.setVisible(false);
					// Clearing the list of questions.
					c.getListQuestion().clear();
					// The above code is calling the jsonCall() method of the Link class.
					Link.jsonCall();
					nbQuest=0;
				}
			}); 
			// The code for the button "Next" of the view "AddCardAp".
			vwAddCardAp.getBtnNext().setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// Checking if the user has filled in all the fields and if the question is not already in the
					// deck.
					if((vwAddCardAp.getRbTrue1().isSelected() || vwAddCardAp.getRbTrue2().isSelected() || vwAddCardAp.getRbTrue3().isSelected())
							&& !vwAddCardAp.getTxtfChoices1().getText().trim().isEmpty() && !vwAddCardAp.getTxtfChoices2().getText().trim().isEmpty() && !vwAddCardAp.getTxtfChoices3().getText().trim().isEmpty() 
							&& !vwAddCardAp.getTxtfInterr().getText().trim().isEmpty() ) {

						// Creating a new question object with the author, category, and question text. It then adds the
						// answers to the question object.
						Question q = new Question(vwAddCardAp.getTxtfAuthor().getText(),Category.getCateList(nbQuest), vwAddCardAp.getTxtfInterr().getText());
						q.addAnswer(vwAddCardAp.getTxtfChoices1().getText(), vwAddCardAp.getRbTrue1().isSelected());
						q.addAnswer(vwAddCardAp.getTxtfChoices2().getText(), vwAddCardAp.getRbTrue2().isSelected());
						q.addAnswer(vwAddCardAp.getTxtfChoices3().getText(), vwAddCardAp.getRbTrue3().isSelected());
						boolean test = false;
						// Checking if the list of questions in the deck contains the question q.
						for (int i = 0; i < Link.getDeck().getListCards().size(); i++) {
							if(Link.getDeck().getListCards().get(i).getListQuestion().contains(q)) {
								test=true;
							}
						}	
						if(c.getListQuestion().contains(q) || test) {
							// The above code is checking if the question is already present in the deck.
							vwAddCardAp.getLblError().setText("The question is already present in the deck");
						}
						else if(vwAddCardAp.getTxtfChoices1().getText().equalsIgnoreCase(vwAddCardAp.getTxtfChoices2().getText()) || 
								vwAddCardAp.getTxtfChoices1().getText().equalsIgnoreCase(vwAddCardAp.getTxtfChoices3().getText()) ||
								vwAddCardAp.getTxtfChoices2().getText().equalsIgnoreCase(vwAddCardAp.getTxtfChoices3().getText()))
						{
							vwAddCardAp.getLblError().setText("2 choix identique!");
						}
						else {
							// Creating a new Question object and adding it to the card object.
							c.addQuestion(q);
							nbQuest++;
							// Checking if the number of questions is less than or equal to 5. If it is, it will set the
							// label to the category of the question.
							if(nbQuest<=5) {
								vwAddCardAp.getLblCateg().setText(Category.listCateg().get(nbQuest).toString());
							}
							if(nbQuest==5) {
								vwAddCardAp.getBtnNext().setText("VALID");						
							}
							// Adding a card to the deck and then writing the deck to the json file.
							if(nbQuest>5) {
								Link.getDeck().addCard(c);
								Write.writeJson(Link.getDeck(), "questions.json");
								Link.jsonCall();
								c.getListQuestion().clear();
								getVwAddCardAp().setVisible(false);
								nbQuest=0;
							}
							// Clearing the text fields and radio buttons and setting the author text field to disabled.
							vwAddCardAp.getTxtfAuthor().setDisable(true);
							vwAddCardAp.getTxtfChoices1().clear();
							vwAddCardAp.getTxtfChoices2().clear();
							vwAddCardAp.getTxtfChoices3().clear();
							vwAddCardAp.getTxtfInterr().clear();
							vwAddCardAp.getRbTrue1().setSelected(false);
							vwAddCardAp.getRbTrue2().setSelected(false);
							vwAddCardAp.getRbTrue3().setSelected(false);
							vwAddCardAp.getLblError().setText("");
						}
					}
					else {
						// The above code is checking if the text field is empty. If it is empty, it will display an error
						// message.
						vwAddCardAp.getLblError().setText("Empty field");
					}
				}
			});
		}
		return vwAddCardAp;
	}

	/**
	 * It's the function that allows the administrator to modify a card
	 * 
	 * @return The method returns the viewModifyCardAp.
	 */
	public ViewModifyCardAp getVwModifyCardAp() {
		if(vwModifyCardAp == null)
		{
			vwModifyCardAp = new ViewModifyCardAp();
			vwModifyCardAp.setVisible(false);
			// Creating a new object of the Card class.
			Card c = new Card();
			// A button that allows the user to quit the pain.
			vwModifyCardAp.getBtnQuit().setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					vwModifyCardAp.setVisible(false);
					// Clearing the list of questions.
					c.getListQuestion().clear();
					// Calling the jsonCall method on the Link class.
					Link.jsonCall();
					nbQuest=0;
				}
			}); 
			// The code for the next button.
			vwModifyCardAp.getBtnNext().setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// Checking if the user has filled in all the fields and if so, it adds the question to the card.
					if((vwModifyCardAp.getRbTrue1().isSelected() || vwModifyCardAp.getRbTrue2().isSelected() || vwModifyCardAp.getRbTrue3().isSelected())
							&& !vwModifyCardAp.getTxtfChoices1().getText().trim().isEmpty() && !vwModifyCardAp.getTxtfChoices2().getText().trim().isEmpty() && !vwModifyCardAp.getTxtfChoices3().getText().trim().isEmpty() 
							&& !vwModifyCardAp.getTxtfInterr().getText().trim().isEmpty() ) {

						// Creating a new question object and adding it to the card object.
						Question q = new Question(vwModifyCardAp.getTxtfAuthor().getText(),Category.getCateList(nbQuest), vwModifyCardAp.getTxtfInterr().getText());
						q.addAnswer(vwModifyCardAp.getTxtfChoices1().getText(), vwModifyCardAp.getRbTrue1().isSelected());
						q.addAnswer(vwModifyCardAp.getTxtfChoices2().getText(), vwModifyCardAp.getRbTrue2().isSelected());
						q.addAnswer(vwModifyCardAp.getTxtfChoices3().getText(), vwModifyCardAp.getRbTrue3().isSelected());
						c.addQuestion(q);

						nbQuest++;


						// Setting the text of the labels and textfields in the view.
						if(nbQuest<=5) {

							for(int j=0;j<=5;j++)
							{
								test2 = Category.listCateg().get(nbQuest).toString().equalsIgnoreCase(Link.getDeck().getListCards().get(getVwCardAdminAp().getNumCard()).getListQuestion().get(j).getCategory().toString());
								if(test2)
								{
									vwModifyCardAp.getLblCateg().setText(Category.listCateg().get(nbQuest).toString());
									vwModifyCardAp.getTxtfAuthor().setText(vwModifyCardAp.getTxtfAuthor().getText());
									vwModifyCardAp.getTxtfAuthor().setDisable(true);

									int i=0;
									// Iterating through a map and setting the text of the textfields and the selected value of the
									// radio buttons.
									for (Map.Entry<String, Boolean> entry : Link.getDeck().getListCards().get(getVwCardAdminAp().getNumCard()).getListQuestion().get(j).getChoices().entrySet()) {
										String key = entry.getKey();
										Boolean value = entry.getValue();
										if(i==0)
										{
											getVwModifyCardAp().getTxtfChoices1().setText(key);
											getVwModifyCardAp().getRbTrue1().setSelected(value);
										}
										else if(i==1)
										{
											getVwModifyCardAp().getTxtfChoices2().setText(key);
											getVwModifyCardAp().getRbTrue2().setSelected(value);
										}
										else
										{
											getVwModifyCardAp().getTxtfChoices3().setText(key);
											getVwModifyCardAp().getRbTrue3().setSelected(value);
										}
										i++;
									}
									getVwModifyCardAp().getTxtfInterr().setText(Link.getDeck().getListCards().get(ViewCardAdminAp.getNumCard()).getListQuestion().get(j).getInterrogation().toString());
									getVwModifyCardAp().getBtnNext().setText("NEXT");
									vwModifyCardAp.getLblError().setText("");
									getVwModifyCardAp().getLblCateg().setText(Category.listCateg().get(nbQuest).toString());
								}
							}
						}
						if(nbQuest==5) {
							// The above code is setting the text of the button to "VALID".
							vwModifyCardAp.getBtnNext().setText("VALID");						
						}
						// Checking if the number of questions is greater than 5. If it is, it replaces the card with the
						// new one, writes the deck to the json file, and clears the list of questions.
						if(nbQuest>5) {
							Link.getDeck().replaceCard(ViewCardAdminAp.getNumCard(), c);
							Write.writeJson(Link.getDeck(), "questions.json");
							Link.jsonCall();
							c.getListQuestion().clear();
							getVwModifyCardAp().setVisible(false);
							nbQuest=0;
						}
					}
					else {
						// The above code is checking if the text field is empty. If it is empty, it will display an error
						// message.
						vwModifyCardAp.getLblError().setText("Empty field");
					}
				}
			});
		}
		return vwModifyCardAp;
	}

	/**
	 * This function is used to create the viewChallengeAp object and set the visibility of the
	 * viewChallengeAp object to false. It also sets the onAction event for the back button and the add
	 * button
	 * 
	 * @return The viewChallengesAp is being returned.
	 */
	public ViewChallengeAp getVwChallengesAp() {
		if(vwChallengesAp == null)
		{
			vwChallengesAp = new ViewChallengeAp();
			vwChallengesAp.setVisible(false);

			// Setting the action of the button to go back to the previous view.
			vwChallengesAp.getBtnBack().setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					getVwChallengesAp().setVisible(false);
					getVwCardAdminAp().setVisible(true);
				}
			}); 
			// Setting the action of the button to go to the add challenges view.
			vwChallengesAp.getBtnAdd().setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					getVwAddChallenge().setVisible(true);
					getVwAddChallenge().getTxtfChallenges().clear();
				}
			}); 
		}
		return vwChallengesAp;
	}

	/**
	 * It creates a new ViewAddChallengeAp object if it doesn't exist yet, and then it sets the action of
	 * the buttons in the view
	 * 
	 * @return The viewAddChallengeAp
	 */
	public ViewAddChallengeAp getVwAddChallenge() {
		if(vwAddChallenge == null)
		{
			vwAddChallenge = new ViewAddChallengeAp();
			vwAddChallenge.setVisible(false);

			// Setting the action of the button to close the window.
			vwAddChallenge.getBtnQuit().setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					getVwAddChallenge().setVisible(false);
				}
			}); 
			// Adding a new challenge to the list of challenges.
			vwAddChallenge.getBtnValidate().setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {

					// The above code is checking if the text field is empty. If it is, it will display an error message.
					if(!vwAddChallenge.getTxtfChallenges().getText().trim().isEmpty()) {
						boolean test = false;
						for (int i = 0; i < LinkChallenges.getChallenges().getListChallenges().size(); i++) {
							if(LinkChallenges.getChallenges().getListChallenges().contains(vwAddChallenge.getTxtfChallenges().getText())) {
								test=true;
							}
						}						
						// Checking if the challenge is already present in the list. If it is, it will display an error
						// message. If it is not, it will add the challenge to the list and write it to the file.
						if(test) {
							vwAddChallenge.getLblError().setText("The challenge is already present in the list");
						}
						else {
							LinkChallenges.getChallenges().addChallenge(getVwAddChallenge().getTxtfChallenges().getText());
							// Writing the challenges to a file.
							try {
								WriteChallenges.write(LinkChallenges.getChallenges().getListChallenges());
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							LinkChallenges.txtCall();

							getVwAddChallenge().setVisible(false);
							vwAddChallenge.getLblError().setText("");
						}
					}
					else
					{
						vwAddChallenge.getLblError().setText("Empty field");
					}
				}
			}); 
		}
		return vwAddChallenge;
	}

	/**
	 * > This function returns the shift.
	 * 
	 * @return The shift variable is being returned.
	 */
	public Shift getShift() {
		return shift;
	}

	/**
	 * It returns the number of questions in the card
	 * 
	 * @return The number of questions in the card.
	 */
	public int getNbQuest() {
		return nbQuest;
	}

	/**
	 * Returns the current position of the player.
	 * 
	 * @return The value of the pos variable.
	 */
	public int getPos() {
		return pos;
	}

	/**
	 * > This function is used to set the position of the player in the list of players
	 * 
	 * @param posAjout the number of positions to move forward or backward
	 */
	public void setPos(int posAjout) {
		if(pos>=getVwAddPlayerAp().getPlayer().getSizePlayer())
		{
			pos=0;
		}
		this.pos = pos + posAjout;
	}

	/**
	 * > This function returns the number of challenges
	 * 
	 * @return The number of challenges
	 */
	public int getNbChall() {
		return nbChall;
	}

	/**
	 * It adds the number of challenges to the number of challenges completed, and if the number
	 * of challenges completed is equal to the number of challenges in the game, it sets the number of
	 * challenges completed to 0
	 * 
	 * @param nbChallAjout the number of challenges to add to the current number of challenges
	 */
	public void setNbChall(int nbChallAjout) {
		nbChall = nbChall + nbChallAjout;
		if(nbChall==LinkChallenges.getChallenges().getListChallenges().size()) {
			nbChall=0;
		}
		this.nbChall = nbChall;
	}

	/**
	 * Returns the number of players in the game
	 * 
	 * @return The number of players.
	 */
	public int getNbPlayers() {
		return nbPlayers;
	}

	/**
	 * Returns the number of players in the game
	 * 
	 * @return The number of players.
	 */
	public void setNbPlayers(int nb) {
		this.nbPlayers = nb;
	}

	/**
	 * Returns the number of turns the player has  play
	 * 
	 * @return The number of turns the player has  play
	 */
	public int getNbTurns() {
		return nbTurns;
	}

	/**
	 * It creates a new Shift object if one does not exist, and returns the existing one if it does.
	 * 
	 * @param dice The number of dice to roll.
	 * @return A new instance of the Shift class.
	 */
	public Shift getShift(int dice) {
		if(shift == null)
		{
			shift = new Shift(dice);
		}
		return shift;
	}

	/**
	 * Returns the value of the isGood field
	 * 
	 * @return The isGood variable.
	 */
	public int getIsGood() {
		return isGood;
	}

	/**
	 * It sets the isGood variable to the value passed in.
	 * 
	 * @param isGood 1 if the comment is good, 0 if it's bad.
	 */
	public void setIsGood(int isGood) {
		this.isGood = isGood;
	}

	/**
	 * It returns the idCate.
	 * 
	 * @return The idCate variable is being returned.
	 */
	public int getIdCate() {
		return idCate;
	}

	/**
	 * It sets the idCate variable to the value of the idCate parameter.
	 * 
	 * @param idCate The id of the category.
	 */
	public void setIdCate(int idCate) {
		this.idCate = idCate;
	}

	/**
	 * It returns the value of theGoodIs.
	 * 
	 * @return The method is returning the value of theGoodIs.
	 */
	public String getTheGoodIs() {
		return theGoodIs;
	}

	/**
	 * It sets the value of theGoodIs to the value of the parameter theGoodIs.
	 * 
	 * @param theGoodIs The name of the parameter.
	 */
	public void setTheGoodIs(String theGoodIs) {
		this.theGoodIs = theGoodIs;
	}

	/**
	 * This function returns the categGood field of the vwGlobalScore object
	 * 
	 * @return The categGood variable is being returned.
	 */
	public String getCategGood() {
		if(vwGlobalScoreAp == null)
		{
			categGood = new String();
		}
		return categGood;
	}

	/**
	 * It sets the value of the categGood field.
	 * 
	 * @param categGood The category of the good.
	 */
	public void setCategGood(String categGood) {
		this.categGood = categGood;
	}

	/**
	 * The getTimer() method creates a new AnimationTimer object if it doesn't exist yet. 
	 * It then returns the AnimationTimer object
	 * 
	 * @return The timer object.
	 */
	public AnimationTimer getTimer() {
		if(timer == null)
		{
			getVwBoardGameSp().getQuestChoiceVw().setChrono(30);
			timer = new AnimationTimer() {
				Long LastUpdate = System.nanoTime();
				@Override
				public void handle(long now) {
					// TODO Auto-generated method stub
					if((now-LastUpdate)>=1000000000) {
						getVwBoardGameSp().getQuestChoiceVw().setChrono(-1);
						getVwBoardGameSp().getQuestChoiceVw().getLblChrono().setText(String.format("00:%02d", getVwBoardGameSp().getQuestChoiceVw().getChrono()));
						LastUpdate = now;
					}
					if(getVwBoardGameSp().getQuestChoiceVw().getChrono() < 0) {
						for(int j=0;j<=5;j++)
						{
							if(vwDiceAp.getBoardgameVw().getListCateg().get(getVwAddPlayerAp().getPlayer().getLstPlayers().get(getNbPlayers()).getTotalDice()).toString().equalsIgnoreCase(Link.getDeck().getListCards().get(getNbTurns()).getListQuestion().get(j).getCategory().toString()))
							{
								int i=1;
								// Displaying the question and the category of the question.
								getVwBoardGameSp().getQuestChoiceVw().getLblQuestion().setText(Link.getDeck().getListCards().get(getNbTurns()).getListQuestion().get(j).getInterrogation().toString());
								getVwBoardGameSp().getQuestChoiceVw().getLblCategory().setText(Link.getDeck().getListCards().get(getNbTurns()).getListQuestion().get(j).getCategory().toString());
								// Creating the choices for the questions.
								for (Map.Entry<String, Boolean> entry : Link.getDeck().getListCards().get(getNbTurns()).getListQuestion().get(j).getChoices().entrySet()) 
								{
									String key = entry.getKey();
									if(entry.getValue()) {
										setIsGood(i);
										setTheGoodIs(entry.getKey());
									}
									i++;
								}
								break;
							}
						} 
						getTimer().stop();
						getVwAnswerAp().setVisible(true);
						getVwBoardGameSp().getQuestChoiceVw().getLblChrono().setText("TO LATE");
						getVwBoardGameSp().getQuestChoiceVw().setChrono(30);
						// Incrementing the number of incorrect answers for the player.
						getVwAddPlayerAp().getPlayer().getLstPlayers().get(getNbPlayers()).augNbIncorrect();

						// Playing a sound when the user gets the answer wrong.
						getVwAnswerAp().getRect().setFill(Color.RED);
						getVwSettingsAp().getMp().setMedia("/music/false.wav");
						getVwAnswerAp().isGood(false);
						getVwSettingsAp().getMp().setMediaPlayer(getVwSettingsAp().getMp().getMedia());
						getVwSettingsAp().getMp().getMediaPlayer().setMute(getVwSettingsAp().getCbMute().isSelected());
						getVwSettingsAp().getMp().getMediaPlayer().setVolume(getVwSettingsAp().getSlider().getValue());
						getVwSettingsAp().getMp().getMediaPlayer().play();

						// The above code is setting the width of the label to 500 and setting the wrap text to true.
						getVwAnswerAp().getLblTheGood().setPrefWidth(500);
						getVwAnswerAp().getLblTheGood().setWrapText(true);
						// Checking if the player has rolled a CHALLENGES or DEPART and if so, it will display a message.
						getVwAnswerAp().getLblTheGood().setText("TO LATE\nThe Good Answer was : \n"+getTheGoodIs());

						nbTurns++;
						if(getNbTurns()==Link.getDeck().getListCards().size()) {
							nbTurns=0;
						}

						nbPlayers++;
						if(getNbPlayers()>=getVwAddPlayerAp().getPlayer().getSizePlayer())
						{
							nbPlayers = 0;
						}
						getVwDiceAp().setDisable(false);
						vwBoardGameSp.getQuestChoiceVw().setVisible(false);
						getVwBoardGameSp().getBtnQuit().setDisable(false);
						getVwBoardGameSp().getBtnScore().setDisable(false);
						// Removing all the labels from the score view.
						getVwBoardGameSp().getScoreVw().getChildren().removeAll(getVwBoardGameSp().getScoreVw().getLblComputer(),getVwBoardGameSp().getScoreVw().getLblHistory(),
								getVwBoardGameSp().getScoreVw().getLblIdeas(),getVwBoardGameSp().getScoreVw().getLblLiterature(),getVwBoardGameSp().getScoreVw().getLblPlanet(),getVwBoardGameSp().getScoreVw().getLblScience());

						// Adding the player to the board.
						getVwBoardGameSp().getScoreVw().getCrlColorPlay().setFill(Color.web(getVwAddPlayerAp().getPlayer().getLstPlayers().get(getNbPlayers()).getColor().toString()));
						getVwBoardGameSp().getScoreVw().getLblPlayer().setText(getVwAddPlayerAp().getPlayer().getLstPlayers().get(getNbPlayers()).getName().toString());
						getVwBoardGameSp().getScoreVw().getGood(getVwAddPlayerAp().getPlayer().getLstPlayers().get(getNbPlayers()).getLstCateg());
						getVwBoardGameSp().getScoreVw().getLblScore().setText(""+getVwAddPlayerAp().getPlayer().getLstPlayers().get(getNbPlayers()).getTotalScore());
					}
				}
			};
		}
		return timer;
	}
}