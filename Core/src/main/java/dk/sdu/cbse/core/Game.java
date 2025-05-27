package dk.sdu.cbse.core;

import dk.sdu.cbse.common.Entity;
import dk.sdu.cbse.common.GameData;
import dk.sdu.cbse.common.GameKeys;
import dk.sdu.cbse.common.World;
import dk.sdu.cbse.common.IEntityProcessingService;
import dk.sdu.cbse.common.IGamePluginService;
import dk.sdu.cbse.common.IPostEntityProcessingService;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

@Component
public class Game extends Application {
    private final World world = new World();
    private final Map<Entity, Polygon> polygons = new ConcurrentHashMap<>();
    private final Pane gameWindow = new Pane();
    private final List<IEntityProcessingService> processingServices;// = context.getBean(List<IEntityProcessingService>);
    private final List<IPostEntityProcessingService> postProcessingServices;
    private final List<IGamePluginService> gamePluginServices;
    private final GameData gameData = new GameData();
    private Text scoreText;

    // Builds the Game by using the associated Beans
    public Game(List<IEntityProcessingService> IEntityProcessingService,
                List<IPostEntityProcessingService> IPostEntityProcessingService,
                List<IGamePluginService> IGamePluginService){
        this.processingServices = IEntityProcessingService;
        this.postProcessingServices = IPostEntityProcessingService;
        this.gamePluginServices = IGamePluginService;
    }

    @Override
    public void start(Stage window) throws Exception {
        scoreText = new Text(10, 20, "Points: 0");
        gameWindow.setPrefSize(gameData.getDisplayWidth(), gameData.getDisplayHeight());
        gameWindow.getChildren().add(scoreText);

        Scene scene = new Scene(gameWindow);
        scene.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.LEFT)) {
                gameData.getKeys().setKey(GameKeys.LEFT, true);
            }
            if (event.getCode().equals(KeyCode.RIGHT)) {
                gameData.getKeys().setKey(GameKeys.RIGHT, true);
            }
            if (event.getCode().equals(KeyCode.UP)) {
                gameData.getKeys().setKey(GameKeys.UP, true);
            }
            if (event.getCode().equals(KeyCode.SPACE)) {
                gameData.getKeys().setKey(GameKeys.SPACE, true);
            }
        });
        scene.setOnKeyReleased(event -> {
            if (event.getCode().equals(KeyCode.LEFT)) {
                gameData.getKeys().setKey(GameKeys.LEFT, false);
            }
            if (event.getCode().equals(KeyCode.RIGHT)) {
                gameData.getKeys().setKey(GameKeys.RIGHT, false);
            }
            if (event.getCode().equals(KeyCode.UP)) {
                gameData.getKeys().setKey(GameKeys.UP, false);
            }
            if (event.getCode().equals(KeyCode.SPACE)) {
                gameData.getKeys().setKey(GameKeys.SPACE, false);
            }
        });

        // Lookup all Game Plugins using ServiceLoader
        for (IGamePluginService iGamePlugin : this.gamePluginServices) {
            iGamePlugin.start(gameData, world);
        }

        for (Entity entity : world.getEntities()) {
            Polygon polygon = new Polygon(entity.getPolygonCoordinates());
            polygons.put(entity, polygon);
            gameWindow.getChildren().add(polygon);
        }
//        render(); // Commented out because it also gets called in Main.java . Game becomes /very fast/
        window.setScene(scene);
        window.setTitle("ASTEROIDS");
        window.show();
    }

    public void render() {
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
                draw();
                gameData.getKeys().update();
            }
        }.start();
    }

    private void update() {
        for (IEntityProcessingService entityProcessorService : this.processingServices) {
            entityProcessorService.process(gameData, world);
        }
        for (IPostEntityProcessingService postEntityProcessorService : this.postProcessingServices) {
            postEntityProcessorService.process(gameData, world);
        }
    }

    private void draw(){
        for (Entity polygonEntity : polygons.keySet()) {
            if(!world.getEntities().contains(polygonEntity)){
                Polygon removedPolygon = polygons.get(polygonEntity);
                polygons.remove(polygonEntity);
                gameWindow.getChildren().remove(removedPolygon);
            }
        }

        for (Entity entity : world.getEntities()) {
            Polygon polygon = polygons.get(entity);
            if (polygon == null) {
                polygon = new Polygon(entity.getPolygonCoordinates());
                polygons.put(entity, polygon);
                gameWindow.getChildren().add(polygon);
            }
            polygon.setTranslateX(entity.getX());
            polygon.setTranslateY(entity.getY());
            polygon.setRotate(entity.getRotation());
        }
        // Update score
        String score = "0";
        try {
            score = String.valueOf(gameData.getCurrentScore());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        scoreText = new Text(10, 20, "Points: "+score);
        gameWindow.getChildren().add(scoreText);
    }

}
