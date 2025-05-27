package dk.sdu.cbse.core;

import dk.sdu.cbse.common.*;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

@Configuration
public class MainConfig {

    public MainConfig() {}


    /** Main.java calls on this config to create an instance of Game,
     * which in turns calls on the Beans here to load the
     *
     * @return Game Instance of Game, containing
     */
    @Bean
    public Game game(){
        return new Game(entityProcessingServiceList(),postEntityProcessingServices(),gamePluginServices());
    }

    @Bean
    public List<IEntityProcessingService> entityProcessingServiceList(){
        return ServiceLoader.load(IEntityProcessingService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    @Bean
    public List<IGamePluginService> gamePluginServices() {
        return ServiceLoader.load(IGamePluginService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    @Bean
    public List<IPostEntityProcessingService> postEntityProcessingServices() {
        return ServiceLoader.load(IPostEntityProcessingService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
