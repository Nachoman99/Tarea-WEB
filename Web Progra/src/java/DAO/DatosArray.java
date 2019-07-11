package DAO;

import Archivos.FileManagement;
import java.io.IOException;
import java.util.List;
import modelo.User;

/**
 * Class that uses the singleton to return a single instance of the created User
 *
 * @author Jose Ignacio Zamora
 */
public class DatosArray {

    private static DatosArray instance;

    /**
     * A list of User
     */
    public List<User> users;

    /**
     *
     */
    public FileManagement json = new FileManagement();

    private DatosArray() throws IOException {
        users = json.readAll("jsonFile.json");
    }

    /**
     * Method that returns the instance of the class
     *
     * @return
     * @throws IOException
     */
    public static DatosArray getInstance() throws IOException {
        if (instance == null) {
            instance = new DatosArray();
        }
        return instance;
    }
}
