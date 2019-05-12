package HuntingGame;

import javafx.scene.layout.BackgroundImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MaimClass2 extends Application {
	Stage GameStage;
	Text levelText, textscore;
	Scene GameScene, StartScene, HelpScene;
	private Pane rootGame, rootStart, rootHelp;
	private HashMap<KeyCode, Boolean> keys = new HashMap<>();
	private HashMap<Integer, ImageView> life = new HashMap<Integer, ImageView>();
	private List<GameObject> bullets = new ArrayList<>();
	private List<GameObject> enemies = new ArrayList<>();
	private List<GameObject> Dimonds = new ArrayList<>();
	private IntegerProperty SCORE = new SimpleIntegerProperty();
	private IntegerProperty EGGS = new SimpleIntegerProperty();
	private int LIVES = 3;
	private int LEVELS = 1;
	static boolean sound = true;
	private final Integer START_TIME = 500;
	private Integer SECONDS = START_TIME;
	private Button startbutton, Soundbutton, ExitButton, contButton, pauseButton, restartButton, helpButton, backButton,
			levelBtn;
	private Label timeLabel;
	private HBox hbox, boxButtonsGame, HboxLives, LevelUp;
	private VBox boxTimeScore, boxStarts;
	private MediaPlayer Gamesound, shootMediaPlayer, GameOverSo, chickenSo;
	private Media shootSound, gameover, GameSoundMedia, chickenSoMedia;
	private AnimationTimer timer;
	private Timeline TimeLine = new Timeline();
	static Image chickenimage = new Image("File:\\C:\\Users\\alsalam\\Downloads\\chicken.gif", 50, 50, true, true);
	static Image fireIm = new Image("File:\\C:\\Users\\alsalam\\Downloads\\fire.gif", 30, 30, true, true);
	static Image eggsimage = new Image("File:\\C:\\Users\\alsalam\\Downloads\\egg.gif", 30, 30, true, true);
	static Image DimondsImg = new Image("File:\\C:\\Users\\alsalam\\Downloads\\dimond.gif", 30, 30, true, true);
	static Image livesImg = new Image("File:\\C:\\Users\\alsalam\\Downloads\\lives.png", 30, 30, true, true);
	static Image poopIm = new Image("File:\\C:\\Users\\alsalam\\Downloads\\poop.png", 30, 30, true, true);
	static Image MonsterImg = new Image("File:\\C:\\Users\\alsalam\\Downloads\\crazyChicken.gif", 200, 200, true, true);
	static Image imageBackgroundGame = new Image("File:\\C:\\Users\\alsalam\\Downloads\\backgroundGame.png");
	static Image helpim = new Image("File:src//main//resources//pic//help.png");
	static Image imgStart = new Image("File:\\C:\\Users\\alsalam\\Downloads\\backgroundStart.png");

	static final ImageView monster = new ImageView(MonsterImg);
	Player player = new Player();
	monster Boss_Chicken = new monster();

	private Parent creatGamesceneContent() {
		rootGame = new Pane();
		player.setVelocity(new Point2D(1, 0));
		Boss_Chicken.setVelocity(new Point2D(1, 0));
		addGameObject(player, 250, 430);
		addGameObject(Boss_Chicken, 300, 100);
		// add Shoot sound //
		shootSound = new Media("file:///C:/Users/alsalam/Downloads/ringSo.mp3");
		// add Game Over sound //
		gameover = new Media("file:///C:/Users/alsalam/Downloads/gameover2.mp3");
		GameOverSo = new MediaPlayer(gameover);
		// add Chicken sound //
		chickenSoMedia = new Media("file:///C:/Users/alsalam/Downloads/chickenSo.mp3");
		chickenSo = new MediaPlayer(chickenSoMedia);
		chickenSo.setVolume(0.5);
		// lives //
		HboxLives = new HBox();
		for (int i = 1; i <= 3; i++) {
			life.put(i, new ImageView(livesImg));
		}
		for (Integer in : life.keySet()) {
			HboxLives.getChildren().add(life.get(in));
		}
		HboxLives.setLayoutX(10);
		HboxLives.setLayoutY(10);
		HboxLives.setBackground(new Background(new BackgroundFill(Color.DARKSEAGREEN, null, null)));
		// add VBox//
		boxTimeScore = new VBox();
		boxTimeScore.setLayoutX(480);
		boxTimeScore.setLayoutY(10);
		boxTimeScore.setBackground(new Background(new BackgroundFill(Color.DARKSEAGREEN, null, null)));
		// add HBox//
		boxButtonsGame = new HBox();
		boxButtonsGame.setLayoutX(490);
		boxButtonsGame.setLayoutY(90);
		// add score //
		Text textscore = new Text();
		textscore.setFont(new Font("Elephant", 20));
		textscore.setFill(Color.DARKRED);
		textscore.textProperty().bind(SCORE.asString("Score : [%d]"));
		// add time //
		timeLabel = new Label();
		timeLabel.setFont(new Font("Elephant", 20));
		timeLabel.setTextFill(Color.DARKRED);
		// continue button
		contButton = new Button();
		contButton.setText("⏯ ");
		contButton.setFont(new Font("Elephant", 15));
		contButton.setTextFill(Color.DARKRED);
		// Pause button //
		pauseButton = new Button();
		pauseButton.setText("||");
		pauseButton.setFont(new Font("Elephant", 15));
		pauseButton.setTextFill(Color.DARKRED);
		// add restart button //
		restartButton = new Button();
		restartButton.setText("⟲");
		restartButton.setFont(new Font("Elephant", 15));
		restartButton.setTextFill(Color.DARKRED);
		// background //
		rootGame.setBackground(new Background(new BackgroundImage(imageBackgroundGame, null, null, null, null)));
		// Level Up state //
		LevelUp = effect_text(("  # Level Up #  "));
		LevelUp.setBackground(new Background(new BackgroundFill(Color.DARKSEAGREEN, null, null)));
		levelBtn = new Button("Level [ " + LEVELS + " ]");
		levelBtn.setFont(new Font("Elephant", 15));
		levelBtn.setTextFill(Color.DARKRED);
		levelBtn.setLayoutX(260);
		levelBtn.setLayoutY(250);
		levelBtn.setVisible(false);
		LevelUp.setVisible(false);
		// Add children in Game pane //
		boxTimeScore.getChildren().addAll(textscore, timeLabel);
		boxButtonsGame.getChildren().addAll(contButton, pauseButton, restartButton);
		rootGame.getChildren().addAll(boxTimeScore, boxButtonsGame, HboxLives, backButton, levelBtn, LevelUp);

		return rootGame;
	}

	private Parent creatStartsceneContent() {
		rootStart = new Pane();
		// game sound //
		GameSoundMedia = new Media("file:///C:/Users/alsalam/Downloads/122.mp3");
		Gamesound = new MediaPlayer(GameSoundMedia);
		Gamesound.setVolume(1);
		// StartScene //
		// HBox
		boxStarts = new VBox();
		boxStarts.setLayoutX(460);
		boxStarts.setLayoutY(380);
		HBox hbo = effect_text("Chickens Hunting");
		hbo.setBackground(new Background(new BackgroundFill(Color.KHAKI, null, null)));
		// add button sound
		Soundbutton = new Button(" 🕪 ");
		Soundbutton.setLayoutX(550);
		Soundbutton.setLayoutY(10);
		Soundbutton.setFont(new Font("Arial", 18));
		Soundbutton.setTextFill(Color.DARKRED);

		// add start button //
		startbutton = new Button("Start➤");
		startbutton.setFont(new Font("Elephant", 18));
		startbutton.setTextFill(Color.DARKRED);

		// add Exit button //
		ExitButton = new Button("  Exit ");
		ExitButton.setFont(new Font("Elephant", 18));
		ExitButton.setTextFill(Color.DARKRED);

		// help button //
		rootHelp = new Pane();
		helpButton = new Button("Help🔔");
		helpButton.setFont(new Font("Elephant", 18));
		helpButton.setTextFill(Color.DARKRED);
		// add instruction to help scene//
		rootHelp.setBackground(new Background(new BackgroundImage(helpim, null, null, null, null)));
		// back button in help scene//
		backButton = new Button("Home");
		backButton.setLayoutX(525);
		backButton.setLayoutY(450);
		backButton.setFont(new Font("Elephant", 13));
		backButton.setTextFill(Color.BLACK);
		// add image background Start //
		rootStart.setBackground(new Background(new BackgroundImage(imgStart, null, null, null, null)));
		boxStarts.getChildren().addAll(startbutton, helpButton, ExitButton);
		rootStart.getChildren().addAll(boxStarts, hbo, Soundbutton);
		rootHelp.getChildren().addAll(backButton);

		return rootStart;

	}

	public void update() {

		if (isPressed(KeyCode.UP) && (player.getView().getTranslateY() >= 0)) {
			player.animation.play();
			player.animation.setOffsetY(96);
			player.moveY(-2);
		} else if (isPressed(KeyCode.DOWN) && (player.getView().getTranslateY() <= 435)) {
			player.animation.play();
			player.animation.setOffsetY(0);
			player.moveY(2);
		} else if (isPressed(KeyCode.RIGHT) && (player.getView().getTranslateX() <= 500)) {
			player.animation.play();
			player.animation.setOffsetY(64);
			player.moveX(2);
		} else if (isPressed(KeyCode.LEFT) && (player.getView().getTranslateX() >= 0)) {
			player.animation.play();
			player.animation.setOffsetY(32);
			player.moveX(-2);
		} else if (isPressed(KeyCode.W)) {
			Fire bullet = new Fire();
			bullet.setVelocity(new Point2D(0, 1).subtract(0, 50));
			addFire(bullet, player.getView().getTranslateX(), player.getView().getTranslateY());
		} else if (isPressed(KeyCode.A)) {
			Fire bullet = new Fire();
			bullet.setVelocity(new Point2D(1, 0).subtract(50, 0));
			addFire(bullet, player.getView().getTranslateX(), player.getView().getTranslateY());
		} else if (isPressed(KeyCode.D)) {
			Fire bullet = new Fire();
			bullet.setVelocity(new Point2D(0, 1).add(50, 0));
			addFire(bullet, player.getView().getTranslateX(), player.getView().getTranslateY());
		} else {
			player.animation.stop();
		}

	}

	private void onupdate() {
		// killing enemies

		for (GameObject enemy : enemies) {
			for (GameObject fire : bullets) {
				if (fire.isColliding(enemy)) {
					fire.setalive(false);
					enemy.setalive(false);
					if (enemy instanceof Birds) {
						addDimond(new Dimond(), enemy.getView().getTranslateX(), enemy.getView().getTranslateY());
						SCORE.set(SCORE.get() + 1);
						shootMediaPlayer = new MediaPlayer(shootSound);
						shootMediaPlayer.play();
						shootMediaPlayer.setVolume(2);
					} else if (enemy instanceof Eggs) {
						EGGS.set(EGGS.get() + 1);
						shootMediaPlayer = new MediaPlayer(shootSound);
						shootMediaPlayer.play();
					}
					rootGame.getChildren().removeAll(fire.getView(), enemy.getView());
				} else if (fire.isColliding(Boss_Chicken)) {
					Boss_Chicken.getView().setTranslateX(1 + Math.random() * 450);
					Boss_Chicken.getView().setTranslateY(1 + Math.random() * 180);
					chickenSo.setAutoPlay(true);
					SCORE.set(SCORE.get() + 3);
					rootGame.getChildren().remove(fire.getView());
				}
				// lose life state //
				else if (enemy.isColliding(player) || Boss_Chicken.isColliding(player)) {
					player.setalive(false);
					HboxLives.getChildren().remove(life.get(LIVES));
					rootGame.getChildren().removeAll(fire.getView(), enemy.getView());
					// game over state //
				} else if (LIVES == 0) {
					player.setalive(false);
					enemy.setalive(false);
					HBox hbox = effect_text(" GAME OVER ");
					rootGame.getChildren().add(hbox);
					timer.stop();
					TimeLine.stop();
					// game over sound //
					GameOverSo.setAutoPlay(true);
					Gamesound.setAutoPlay(false);
					rootGame.getChildren().removeAll(fire.getView(), enemy.getView(), Boss_Chicken.getView());
				}
				// win state //
				else if (SCORE.get() >= 30) {
					timer.stop();
					TimeLine.stop();
					LevelUp.setVisible(true);
					levelBtn.setVisible(true);
					rootGame.getChildren().removeAll(fire.getView(), enemy.getView());
				}
			}
		}
		if (Boss_Chicken.getView().getTranslateX() == player.getView().getTranslateX()) {
			addPoops(new Poops(), (Boss_Chicken.getView().getTranslateX() + 80),
					(Boss_Chicken.getView().getTranslateY()));
		}
		if (Math.random() < 0.008) {
			addBirds(new Birds(), (1 + Math.random() * 400), 1);
			addEggs(new Eggs(), (1 + Math.random() * 400), 1);
		}
		bullets.removeIf(GameObject::isDead);
		enemies.removeIf(GameObject::isDead);
		Dimonds.removeIf(GameObject::isDead);
		Dimonds.forEach(GameObject::update);
		bullets.forEach(GameObject::update);
		enemies.forEach(GameObject::update);
	}

	private void addGameObject(GameObject object, double x, double y) {
		object.getView().setTranslateX(x);
		object.getView().setTranslateY(y);
		rootGame.getChildren().add(object.getView());
	}

	private void addBirds(GameObject enemy, double x, double y) {
		enemies.add(enemy);
		addGameObject(enemy, x, y);
	}

	private void addFire(GameObject fire, double x, double y) {
		bullets.add(fire);
		addGameObject(fire, x, y);
	}

	private void addEggs(GameObject egg, double x, double y) {
		enemies.add(egg);
		addGameObject(egg, x, y);
	}

	private void addPoops(GameObject poop, double x, double y) {
		enemies.add(poop);
		addGameObject(poop, x, y);
	}

	private void addDimond(GameObject dimond, double x, double y) {
		Dimonds.add(dimond);
		addGameObject(dimond, x, y);
	}

	public boolean isPressed(KeyCode key) {
		return keys.getOrDefault(key, false);
	}

	private class Birds extends GameObject {
		Birds() {
			super(new ImageView(chickenimage));
			this.getView().setEffect(new Reflection());
		}
	}

	private class monster extends GameObject {
		monster() {
			super(monster);
			monster.setEffect(new Reflection());
		}
	}

	private static class Fire extends GameObject {

		Fire() {
			super(new ImageView(fireIm));
		}
	}

	private static class Dimond extends GameObject {

		Dimond() {
			super(new ImageView(DimondsImg));
		}
	}

	private static class Eggs extends GameObject {

		Eggs() {
			super(new ImageView(eggsimage));
			this.setVelocity(new Point2D(0, 4));
		}
	}

	private static class Poops extends GameObject {

		Poops() {
			super(new ImageView(poopIm));
			this.setVelocity(new Point2D(0, 2));
		}
	}

	private HBox effect_text(String FText) {
		hbox = new HBox();
		hbox.setLayoutX(105);
		hbox.setLayoutY(100);
		hbox.setBackground(new Background(new BackgroundFill(Color.BEIGE, null, null)));
		for (int i = 0; i < FText.toCharArray().length; i++) {
			char letter = FText.charAt(i);
			Text text = new Text(String.valueOf(letter));
			text.setFont(new Font("Arial", 55));
			text.setOpacity(0);
			text.setFill((Color.DARKRED));
			text.setStroke(Color.BROWN);
			hbox.getChildren().add(text);
			FadeTransition ft = new FadeTransition(Duration.seconds(0.66), text);
			ft.setToValue(1);
			ft.setDelay(Duration.seconds(i * 0.15));
			ft.play();
		}
		return hbox;
	}

	void cleanup() {
		// stop animations reset model etc. //
		player.update();
		Boss_Chicken.update();
		TimeLine.stop();
		timer.stop();
		SCORE.set(0);
		EGGS.set(0);
		SECONDS = START_TIME;
		LIVES = 3;
	}

	void startGame(Stage GameStage) {
		this.GameStage = GameStage;
		// Game Scene //
		GameScene = new Scene(creatGamesceneContent(), 600, 490);
		// Start Scene //
		// Handlers in Game //
		GameScene.setOnKeyPressed(e -> keys.put(e.getCode(), true));
		GameScene.setOnKeyReleased(e -> {
			keys.put(e.getCode(), false);
		});
		GameStage.setScene(GameScene);
		if (TimeLine != null) {
			TimeLine.stop();
		}
		TimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(1000), t -> {
			SECONDS--;
			timeLabel.setText("Eggs : " + EGGS.getValue() + "\nTime : " + SECONDS.toString());
			if (SECONDS <= 0) {
				timer.stop();
				TimeLine.stop();
				effect_text("<< Time Out >>");
			}
			if (player.isDead() && LIVES > 0) {
				LIVES -= 1;
				player.setalive(true);
			}
			for (GameObject en : enemies) {
				if (en instanceof Birds) {
					en.getView().setTranslateY((en.getView().getTranslateY() + 50) % 400);
				}
			}
			for (GameObject hotChick : Dimonds) {
				hotChick.setVelocity(new Point2D(-1, 3).multiply(7));
			}
		}));
		TimeLine.setCycleCount(Animation.INDEFINITE);
		TimeLine.playFromStart();
		timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				update();
				onupdate();

			}
		};
		timer.start();
		pauseButton.setOnAction(e -> {
			timer.stop();
			TimeLine.stop();
			Gamesound.pause();

		});
		contButton.setOnAction(e -> {
			timer.start();
			TimeLine.play();
			Gamesound.play();
		});
		restartButton.setOnAction(e -> {
			restart(GameStage);
			
		});
		levelBtn.setOnAction(e -> {
			LEVELS++;
			cleanup();
			startGame(this.GameStage);
			LevelUp.setVisible(false);

		});

	}

	void restart(Stage stage) {
		cleanup();
		startGame(stage);
		LEVELS = 1;
	}

	@Override
	public void start(Stage primaryStage) {
		StartScene = new Scene(creatStartsceneContent(), 600, 490);
		Gamesound.setAutoPlay(true);
		startbutton.setOnAction(e -> {
			startGame(primaryStage);
			LEVELS=1;
		});
		HelpScene = new Scene(rootHelp, 600, 490);
		helpButton.setOnAction(e -> primaryStage.setScene(HelpScene));
		backButton.setOnAction(e -> {
			if (primaryStage.getScene().equals(HelpScene)) {
				primaryStage.setScene(StartScene);
			} else {
				primaryStage.setScene(StartScene);
				cleanup();
			}

		});
		Soundbutton.setOnAction(e -> {
			Gamesound.pause();
		});
		ExitButton.setOnAction(e -> primaryStage.close());
		primaryStage.setScene(StartScene);
		primaryStage.setTitle("Hunting Chicken Game");
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
