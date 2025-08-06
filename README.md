# PlayerInventoryAPI
Легкое и простое API, которое упрощает управление инвентарем игрока.

# Подключение
Maven:
```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-shade-plugin</artifactId>
            <version>3.3.0</version>
            <executions>
                <execution>
                    <phase>package</phase>
                    <goals>
                        <goal>shade</goal>
                    </goals>
                </execution>
            </executions>
            <configuration>
                <relocations>
                    <relocation>
                        <pattern>com.github.ConderFix.playerinventoryapi</pattern>
                        <!-- Замените 'com.yourpackae' на пакет вашего плагина! -->
                        <shadedPattern>com.yourpackage.playerinventoryapi</shadedPattern>
                    </relocation>
                </relocations>
            </configuration>
        </plugin>
    </plugins>
</build>

<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>com.github.ConderFix</groupId>
        <artifactId>PlayerInventoryAPI</artifactId>
        <version>fbf37a67</version>
    </dependency>
</dependencies>
```
Gradle:
```groovy
plugins {
    id 'com.gradleup.shadow' version '8.3.0'
}

repositories {
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation 'com.github.ConderFix:PlayerInventoryAPI:fbf37a67'
}

shadowJar {
    archiveClassifier.set('')
    // Замените 'com.yourpackage' на пакет вашего пакета!!!
    relocate 'com.github.ConderFix.PlayerInventoryAPI', 'com.yourpackage.playerinventoryapi'
}

tasks.build {
    dependsOn (shadowJar)
}
```
# Использование
Главный класс: PlayerInventoryAPI. Создайте экземпляр класса и используйте методы класса. Пример использования:

Java:
```java
final PlayerInventory playerInventory = ...;
final PlayerInventoryAPI playerInventoryAPI = ...;

if (playerInventoryAPI.hasMaterial(playerInventory, Material.EMERALD, 20)) {
    playerInventoryAPI.removeMaterial(playerInventory, Material.EMERALD, 20);
    player.sendMessage("С твоего инвенатаря забралось 20 изумрудов");
} else {
    player.sendMessage("У тебя недостаточно изумрудов!");
}
```

Kotlin:
```kotlin
val inventory: PlayerInventory = ...
val api: PlayerInventoryAPI = ...

if (api.hasMaterial(inventory, Material.EMERALD, 20)) {
    api.removeMaterial(inventory, Material.EMERALD, 20)
    player.sendMessage("С твоего инвенатаря забралось 20 изумрудов")
} else {
    player.sendMessage("У тебя недостаточно изумрудов!")
}
```


Этот код проверяет, что если в PlayerInventory (в инвентаре игрока) есть 20 изумрудов, то они пропадают.
