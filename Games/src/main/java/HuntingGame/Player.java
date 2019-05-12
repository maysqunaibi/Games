package HuntingGame;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Player extends GameObject {
	int count = 3;
	int columns = 3;
	int offsetX = 0;
	int offsetY = 0;
	int width = 32;
	int height = 32;
	SpriteAnimation animation;
	static Image image = new Image("file:///C:/Users/alsalam/Downloads/1.png");
	static final ImageView imageView = new ImageView(image);

	Player() {
		super(imageView);
		imageView.setViewport(new Rectangle2D(offsetX, offsetY, width, height));
		animation = new SpriteAnimation(imageView, Duration.millis(200), count, columns, offsetX, offsetY, width,
				height);

	}

	public void moveX(int x) {
		boolean right = x > 0 ? true : false;
		for (int i = 0; i < Math.abs(x); i++) {
			if (right)
				this.getView().setTranslateX(this.getView().getTranslateX() + 1);
			else
				this.getView().setTranslateX(this.getView().getTranslateX() - 1);
		}
	}

	public void moveY(int y) {
		boolean down = y > 0 ? true : false;
		for (int i = 0; i < Math.abs(y); i++) {
			if (down)
				this.getView().setTranslateY(this.getView().getTranslateY() + 1);
			else
				this.getView().setTranslateY(this.getView().getTranslateY() - 1);
		}
	}

}

