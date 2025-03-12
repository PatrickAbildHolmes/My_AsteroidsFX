## How to run it
java --module-path mods-mvn --class-path libs/* --module=Core/dk.sdu.cbse.App

Possibly instead;
```bash
mvn exec:exec
```

## How to add more modules
1) Run:
```bash
mvn archetype:generate -DarchetypeGroupId=org.codehaus.mojo.archetypes -DarchetypeArtifactId=pom-root -DarchetypeVersion=RELEASE
```
2) Enter the following in the command line:
    Define value for property ’groupId’: dk.sdu.cbse
    Define value for property ’artifactId’: MODULENAME
    Define value for property ’version’ 1.0-SNAPSHOT:
    Define value for property ’package’ dk.sdu.cbse:
    <<*click enter a few times*>>
3) In line 12, change value from "pom" to "jar"
    <packaging>jar</packaging>
4) add 'src/main/java' directory
5) add 'dk.sdu.cbse' package
6) add module-info.java to java directory
7) Add all `pom.xml` and `module-info.java` to git
8) (from parent directory) Run:
   ```bash
   mvn clean install
    ```
9) Click the 'Load Maven changes' pop-up


## Notes
Modules list:
1) Asteroids
2) Bullet
3) Collision
4) Common
5) CommonAsteroids
6) CommonBullet
7) CommonEnemyShips
8) Core
9) EnemyShip
10) Player


7 Key Considerations for JPMS
• module-info.java: Correctly declare module dependencies and exported
packages.
• requires: Use to declare dependencies.
• exports: Use to make packages accessible.
• --add-modules: Might be needed for specific Java SE modules.
• Circular Dependencies: Avoid them.
• Dependency Management: Use <dependencyManagement> in the parent
POM.
This setup allows you to organize your project and leverage JPMS. Adjust
module names, dependencies, and exported packages as needed.