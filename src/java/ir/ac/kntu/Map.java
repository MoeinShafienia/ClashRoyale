package ir.ac.kntu;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Map {

    private static String[] processMap() {
        String[] gameMap = new String[20];
        try {
            File myObj = new File("C:\\Users\\Moein\\Desktop\\LastProject\\src\\DefaultMap");
            Scanner myReader = new Scanner(myObj);
            int i = 0;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                gameMap[i] = data;
                i++;
            }
            myReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }  
        return gameMap;
    }

    public static char[][] readMap() {
        String[] map = processMap();
        char[][] mainMap = new char[20][20];
        for(int i = 0; i < 20; i++) {
            for(int j = 0; j < 20; j++) {
                mainMap[i][j] = map[i].charAt(j);
            }
        }
        return mainMap;
    }

    public static void printMap() {
        char[][] map = readMap();
        for(int i = 0; i < 20; i++) {
            for(int j = 0; j < 20; j++) {
                System.out.print(map[i][j]);
            }
            System.out.print("\n");
        } 
    }

    public static void drawMap(Pane root, char[][] colors) {
        for (int i = 0; i < 800; i += 40) {
            for (int j = 0; j < 800; j += 40) {
                Rectangle rectangle = new Rectangle(j, i, 40, 40);
                setColor(rectangle, colors, i, j);
                rectangle.setStroke(Color.BLACK);
                root.getChildren().add(rectangle);
            }
        }
    }

    public static void setColor(Rectangle rectangle, char[][] colors, int i,
                            int j) {
        char color = colors[i / 40][j / 40];
        switch (color) {
            case 'r':
                rectangle.setFill(Color.RED);
                break;
            case 'g':
                rectangle.setFill(Color.GREEN);
                break;
            case 'b':
                rectangle.setFill(Color.BLUE);
                break;
            case 'y':
                rectangle.setFill(Color.YELLOW);
                break;
            default:
                rectangle.setFill(Color.WHITE);
        }
    }

    public static char[][] getMap() {
        return readMap();
    }
}
