package Archivos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import com.google.gson.Gson;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import modelo.User;

/**
 * This class is in charge of managing json files
 *
 * @author Kevin Trejos
 * @version 10/7/2019
 */
public class FileManagement {

    BufferedWriter output = null;
    BufferedReader input = null;
    Gson gson = new Gson();
    File file;

    private void createDocument(String ruta) throws IOException {
        file = new File(ruta);
        output = new BufferedWriter(new FileWriter(file, true));

    }

    /**
     * This method removes the contents of the file
     *
     * @param path path where the file is located
     * @throws IOException
     */
    public void deleteFile(String path) throws IOException {
        output = new BufferedWriter(new FileWriter(path));
        output.write("");
        closeWriter();
    }

    private void closeWriter() throws IOException {
        output.close();
    }

    /**
     * Read the first line of the file
     *
     * @param path path where the file is located
     * @return A User
     * @throws IOException
     */
    public User readFirst(String path) throws IOException {
        input = new BufferedReader(new FileReader(path));
        User user = gson.fromJson(input.readLine(), User.class);
        input.close();
        return user;
    }

    /**
     * Write the User entered in the file
     *
     * @param path path where the file is located
     * @param user the user to write
     * @throws IOException
     */
    public void write(String path, User user) throws IOException {
        createDocument(path);
        output.write(gson.toJson(user));
        output.write("\n");
        closeWriter();
    }

    /**
     * Read all the contents of the file
     *
     * @param path path where the file is located
     * @return An arraylist with the data that the file contains
     * @throws IOException
     */
    public ArrayList<User> readAll(String path) throws IOException {
        ArrayList<User> list = new ArrayList<>();
        User user;
        try {
            input = new BufferedReader(new FileReader(path));
        } catch (FileNotFoundException e) {
            createDocument(path);
            return list;
        }

        while ((user = gson.fromJson(input.readLine(), User.class)) != null) {
            list.add(user);
        }
        input.close();
        return list;
    }
}
