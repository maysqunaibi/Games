package HuntingGame;

import javafx.scene.layout.BackgroundImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainClass extends Application {
	Text levelText, textscore;
	Scene GameScene, StartScene;
	private Pane root, root2;
	private HashMap<KeyCode, Boolean> keys = new HashMap<>();
	private List<GameObject> bullets = new ArrayList<>();
	private List<GameObject> enemies = new ArrayList<>();
	private List<GameObject> hotChick = new ArrayList<>();
	private IntegerProperty score = new SimpleIntegerProperty();
	private IntegerProperty level = new SimpleIntegerProperty(1);
	private final Integer startTime = 35;
	private Integer seconds = startTime;
	private Button startbutton, Soundbutton, Exitbutton, cont, btnpuse, restartbtn, help;
	private Label timeLabel;
	private HBox hbox;
	private MediaPlayer Gamesound, Popsound, GameOverSo;
	private Media pop, gameover;
	EventHandler eventHandler;
	private AnimationTimer timer;
	private Timeline TimeLine = new Timeline();
	Image Cute_Bird_img = new Image("duck.gif", 90, 90, true, true);
	static Image fireIm = new Image("file:///C:/Users/alsalam/Downloads/fire.jpg", 20, 20, true, true);
	static Image hotChickIm = new Image("egg2.gif", 40, 40, true, true);
	static Image image = new Image("file:///C:/Users/alsalam/Downloads/farm.png");
	static Image BackG_GameScene = new Image("114.jpg", 1050, 590, true, true);
	// static final ImageView BG1_ImV = new ImageView(BackG_GameScene);
	static Image EggsImg = new Image("egg.gif", 40, 40, true, true);
	static Image MonsterImg = new Image("BossBird.gif", 110, 110, true, true);
	static final ImageView imm = new ImageView(MonsterImg);
	static Image PoopImg = new Image("poop.jpg", 30, 30, true, true);
	GameObject player = new Player();
	Player player2 = ((Player) player);
	GameObject monster1 = new monster();
	monster monster2 = ((monster) monster1);

	private Parent creatGamesceneContent() {
		root = new Pane();
		BackgroundImage im = new BackgroundImage(BackG_GameScene, null, null, BackgroundPosition.DEFAULT,
				BackgroundSize.DEFAULT);
		Background value = new Background(im);
		pop = new Media("file:///C:/Users/alsalam/Downloads/pop.mp3");
		Popsound = new MediaPlayer(pop);
		root.setBackground(value);
		player2.setVelocity(new Point2D(1, 0));
		addGameObject(player2, 300, 460);
		monster2.setVelocity(new Point2D(1, 0));
		addGameObject(monster2, 390	, 150);
		// add score //
		textscore = new Text();
		textscore.setLayoutX(440);
		textscore.setLayoutY(100);
		textscore.setFont(Font.font(25));
		textscore.setFill(Color.ORANGE);
		textscore.textProperty().bind(score.asString("Score : [%d]"));

		// Level up //
		levelText = new Text();
		levelText.setLayoutX(540);
		levelText.setLayoutY(50);
		levelText.setFont(Font.font(25));
		levelText.textProperty().bind(level.asString("Level : [%d]"));
		// add time //
		timeLabel = new Label();
		timeLabel.setLayoutX(425);
		timeLabel.setLayoutY(105);
		timeLabel.setFont(Font.font(25));
		timeLabel.setTextFill(Color.RED);
		// continue button
		cont = new Button();
		cont.setLayoutX(480);
		cont.setLayoutY(140);
		cont.setText(" || ");
		// Pause button //
		btnpuse = new Button();
		btnpuse.setLayoutX(630);
		btnpuse.setLayoutY(140);
		btnpuse.setText("Puse");
		// add restart button //
		restartbtn = new Button();
		restartbtn.setLayoutX(570);
		restartbtn.setLayoutY(190);
		restartbtn.setText("Restart");
		root.getChildren().addAll(levelText, textscore, timeLabel, cont, btnpuse, restartbtn);

		return root;
	}

	private Parent creatStartsceneContent() {
		root2 = new Pane();
		// game sound //
		Media bubbles = new Media("file:///C:/Users/alsalam/Downloads/bubbles.mp3");
		Gamesound = new MediaPlayer(bubbles);
		gameover = new Media("file:///C:/Users/alsalam/Downloads/gameover2.mp3");
		GameOverSo = new MediaPlayer(gameover);
		// StartScene //
		// add button sound
		Soundbutton = new Button("Music");
		Soundbutton.setLayoutX(625);
		Soundbutton.setLayoutY(590);
		Soundbutton.setFont(new Font("Arial", 20));
		Soundbutton.setTextFill(Color.DARKRED);
		Label title = new Label("Hunting Chickens");
		title.setLayoutX(120);
		title.setLayoutY(200);
		title.setTextFill(Color.BLACK);
		title.setFont(new Font("Arial", 70));
		// add start button //
		startbutton = new Button("Start");
		startbutton.setLayoutX(400);
		startbutton.setLayoutY(475);
		startbutton.setFont(new Font("Arial", 20));
		startbutton.setTextFill(Color.DARKRED);
		// add Exit button //
		Exitbutton = new Button("  Exit ");
		Exitbutton.setLayoutX(624);
		Exitbutton.setLayoutY(555);
		Exitbutton.setFont(new Font("Arial", 20));
		Exitbutton.setTextFill(Color.DARKRED);
		// help button //
		help = new Button("Help?");
		help.setLayoutX(622);
		help.setLayoutY(515);
		help.setFont(new Font("Arial", 19));
		help.setTextFill(Color.DARKRED);

		// add image //

		ImageView vi = new ImageView(image);
		vi.setFitHeight(650);
		vi.setFitWidth(760);

		root2.getChildren().add(vi);

		root2.getChildren().addAll(startbutton, Exitbutton, Soundbutton, help, title);
		return root2;
	}

	public void update() {

		if (isPressed(KeyCode.UP) && (player2.getView().getTranslateY() >= 0)) {
			player2.animation.play();
			player2.animation.setOffsetY(96);
			player2.moveY(-2);
		} else if (isPressed(KeyCode.DOWN) && (player2.getView().getTranslateY() <= 480)) {
			player2.animation.play();
			player2.animation.setOffsetY(0);
			player2.moveY(2);
		} else if (isPressed(KeyCode.RIGHT) && (player2.getView().getTranslateX() <= 420)) {
			player2.animation.play();
			player2.animation.setOffsetY(64);
			player2.moveX(2);
		} else if (isPressed(KeyCode.LEFT) && (player2.getView().getTranslateX() >= 0)) {
			player2.animation.play();
			player2.animation.setOffsetY(32);
			player2.moveX(-2);
		} else if (isPressed(KeyCode.W)) {
			Fire bullet = new Fire();
			bullet.setVelocity(new Point2D(0, 1).subtract(0, 50));
			addFire(bullet, player2.getView().getTranslateX(), player2.getView().getTranslateY());
		} else if (isPressed(KeyCode.A)) {
			Fire bullet = new Fire();
			bullet.setVelocity(new Point2D(1, 0).subtract(50, 0));
			addFire(bullet, player2.getView().getTranslateX(), player2.getView().getTranslateY());
		} else if (isPressed(KeyCode.D)) {
			Fire bullet = new Fire();
			bullet.setVelocity(new Point2D(0, 1).add(50, 0));
			addFire(bullet, player2.getView().getTranslateX(), player2.getView().getTranslateY());
		} else {
			player2.animation.stop();
		}

	}

	private void onupdate() {
		// killing enemies

		for (GameObject enemy : enemies) {
			for (GameObject fire : bullets) {
				if (fire.isColliding(enemy)) {
					fire.setalive(false);
					enemy.setalive(false);
					addHotChick(new hotChick(), enemy.getView().getTranslateX(), enemy.getView().getTranslateY());
					// add Pop sound //
					
					Popsound.setAutoPlay(true);
					score.set(score.get() + 1);
					root.getChildren().removeAll(fire.getView(), enemy.getView());
				} else if (fire.isColliding(monster2)) {
					monster2.getView().setTranslateX(1 + Math.random() * 490);
					monster2.getView().setTranslateY(1 + Math.random() * 300);
					pop = new Media("file:///C:/Users/alsalam/Downloads/pop.mp3");
					Popsound = new MediaPlayer(pop);
					Popsound.setAutoPlay(true);
					score.set(score.get() + 5);
					root.getChildren().remove(fire.getView());
				}
				// game over state //
				else if (enemy.isColliding(player2) || monster2.isColliding(player2)) {
					player2.setalive(false);
					enemy.setalive(false);
					effect_text("<< GAME OVER >>");
					timer.stop();
					TimeLine.stop();
					// game over sound //
					
					GameOverSo.setAutoPlay(true);
					Gamesound.setAutoPlay(false);
					root.getChildren().removeAll(fire.getView(), enemy.getView(), monster2.getView());
				}

				// win state //
//				else if ((score.intValue()  % 10) == 0) {
//				level = level.integerProperty(score)	
//					
//					//effect_text(("# Level Up #"));
//						timer.stop();
//						TimeLine.stop();
//						root.getChildren().removeAll(bullet.getView(), bullet2.getView(), enemy.getView(),
//								monster2.getView());

			}

		}
		if (monster2.getView().getTranslateX() == player2.getView().getTranslateX()) {
			addPoops(new Poops(), (monster2.getView().getTranslateX() + 30), (monster2.getView().getTranslateY()));
		}
		if (Math.random() < 0.008) {
			addBirds(new Birds(), 0, ((1 + Math.random() * 200)));
			addEggs(new Eggs(), (1 + Math.random() * 400), 1);

		}

		bullets.removeIf(GameObject::isDead);
		enemies.removeIf(GameObject::isDead);
		hotChick.removeIf(GameObject::isDead);
		hotChick.forEach(GameObject::update);
		bullets.forEach(GameObject::update);
		enemies.forEach(GameObject::update);
	}

	private void addGameObject(GameObject object, double x, double y) {
		object.getView().setTranslateX(x);
		object.getView().setTranslateY(y);
		root.getChildren().add(object.getView());
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

	private void addHotChick(GameObject HotChick, double x, double y) {
		hotChick.add(HotChick);
		addGameObject(HotChick, x, y);
	}

	public boolean isPressed(KeyCode key) {
		return keys.getOrDefault(key, false);
	}

	private class Birds extends GameObject {
		Birds() {
			super(new ImageView(Cute_Bird_img));
			this.getView().setEffect(new Reflection());
		}
	}

	private class monster extends GameObject {
		monster() {
			super(imm);
			imm.setEffect(new Reflection());
		}
	}

	private static class Fire extends GameObject {

		Fire() {
			super(new ImageView(fireIm));
		}
	}

	private static class hotChick extends GameObject {

		hotChick() {
			super(new ImageView(hotChickIm));
		}
	}

	private static class Eggs extends GameObject {

		Eggs() {
			super(new ImageView(EggsImg));
			this.setVelocity(new Point2D(0, 4));
		}
	}

	private static class Poops extends GameObject {

		Poops() {
			super(new ImageView(PoopImg));
			this.setVelocity(new Point2D(0, 2));
		}
	}

	private void effect_text(String FText) {
		hbox = new HBox();
		hbox.setLayoutX(170);
		hbox.setLayoutY(100);
		// hbox.setBackground(new Background(new BackgroundFill(Color.WHITE, null,
		// null)));
		root.getChildren().add(hbox);
		for (int i = 0; i < FText.toCharArray().length; i++) {
			char letter = FText.charAt(i);
			Text text = new Text(String.valueOf(letter));
			text.setFont(new Font("Arial", 50));
			text.setOpacity(0);
			text.setFill((Color.DARKRED));
			text.setStroke(Color.BROWN);
			hbox.getChildren().add(text);
			FadeTransition ft = new FadeTransition(Duration.seconds(0.66), text);
			ft.setToValue(1);
			ft.setDelay(Duration.seconds(i * 0.15));
			ft.play();
		}

	}

	void cleanup() {
		// stop animations reset model ect.
		player2.update();
		monster2.update();
		TimeLine.stop();
		timer.stop();
		score.set(0);
		seconds = startTime;
		// TimeLine.setCycleCount(Animation.INDEFINITE);
		// TimeLine.playFromStart();
	}

	void startGame(Stage GameStage) {
		// Game Scene //
		GameScene = new Scene(creatGamesceneContent(), 600, 490);
		// Start Scene //
		StartScene = new Scene(creatStartsceneContent(), 600, 490);
		// Handlers in Game //
		GameScene.setOnKeyPressed(e -> keys.put(e.getCode(), true));
		GameScene.setOnKeyReleased(e -> {
			keys.put(e.getCode(), false);
		});

		eventHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				if (e.getSource().equals(startbutton)) {

					if (TimeLine != null) {
						TimeLine.stop();
					}
					GameStage.setScene(GameScene);
					TimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(1000), t -> {
						seconds--;
						timeLabel.setText("Time : " + seconds.toString());
						if (seconds <= 0) {
							timer.stop();
							TimeLine.stop();
							effect_text("<< Time Out >>");
						}

						for (GameObject en : enemies) {
							if (en instanceof Birds) {
								en.getView().setTranslateX((en.getView().getTranslateX() + 50) % 400);
							}
						}
						for (GameObject hotChick : hotChick) {
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
				}
				if (e.getSource().equals(Exitbutton)) {
					GameStage.close();
				}
				if (e.getSource().equals(Soundbutton)) {
					Gamesound.setAutoPlay(true);
					Gamesound.setVolume(0.2);
				}
				if (e.getSource().equals(btnpuse)) {
					timer.stop();
					TimeLine.stop();
				}
				if (e.getSource().equals(cont)) {
					timer.start();
					TimeLine.play();
				}
				if (e.getSource().equals(restartbtn)) {
					restart(GameStage);
				}
			}
		};

		restartbtn.setOnAction(eventHandler);
		cont.setOnAction(eventHandler);
		btnpuse.setOnAction(eventHandler);
		startbutton.setOnAction(eventHandler);
		Soundbutton.setOnAction(eventHandler);
		Exitbutton.setOnAction(eventHandler);
		GameStage.setScene(StartScene);
		GameStage.setTitle("Hunting Chicken Game");
		GameStage.setResizable(false);
		GameStage.show();
	}

	void restart(Stage stage) {
		cleanup();
		startGame(stage);
	}

	@Override
	public void start(Stage primaryStage) {
		startGame(primaryStage);

	}

	public static void main(String[] args) {
		launch(args);
	}

}
