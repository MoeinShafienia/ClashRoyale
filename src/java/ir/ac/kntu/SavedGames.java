package ir.ac.kntu;

import java.io.*;
import java.util.ArrayList;

public class SavedGames {

    private Player player1;
    private Player player2;

    private static final String PATH = "C:\\Users\\Moein\\Desktop" +
            "\\LastProject\\src\\ir\\ac" +
            "\\kntu\\savedgames\\savedgames";
    private static int numberOfSavedGames = 0;
    private static ArrayList<SavedGames> savedGamesList = new ArrayList<>();

    private SavedGames(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public static SavedGames load(int numberOfGame){
        try {

            FileInputStream file =
                    new FileInputStream(PATH.concat("\\game" + numberOfGame));
            ObjectInputStream out = new ObjectInputStream(file);


            Player player1 = (Player) out.readObject();
            Player player2 = (Player) out.readObject();


            out.close();
            file.close();

            return new SavedGames(player1,player2);

        } catch(IOException ex) {
            System.out.println("IOException is caught");
        }catch (ClassNotFoundException e){
            System.out.println("ClassNotFoundException is caught");
        }
        return null;
    }

    public static void save(Player player1,Player player2){
        try {

            FileOutputStream file =
                    new FileOutputStream(PATH.concat("game" + (++numberOfSavedGames)));
            ObjectOutputStream out = new ObjectOutputStream(file);


            out.writeObject(player1);
            out.writeObject(player2);

            out.close();
            file.close();

            savedGamesList.add(new SavedGames(player1,player2));
            System.out.println("game saved successfully");

        } catch(IOException ex) {
            System.out.println("IOException is caught");
        }
    }
}
