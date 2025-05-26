package dk.sdu.cbse.core;

import javafx.application.Application;

import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main extends Application {

    public static void main(String[] args) {
        launch(Main.class);
    }

    @Override
    public void start(Stage window) throws Exception {
        ApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
        Game game = context.getBean(Game.class);
        game.start(window);
        game.render();
    }
}
