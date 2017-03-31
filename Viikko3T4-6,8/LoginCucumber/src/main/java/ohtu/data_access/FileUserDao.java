
package ohtu.data_access;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import ohtu.domain.User;

public class FileUserDao implements UserDao {

    private File f;
    public FileUserDao(String fileName) {
        this.f = new File(fileName);
        try {
            this.f.createNewFile();
        } catch (IOException ioe) {
            System.out.println("Eipä luoda: " + ioe.getMessage());
        }
    }

    public List<User> listAll() {
        List<User> userList = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(f);
            while (scanner.hasNextLine()) {
                String authString = scanner.nextLine();
                String username = authString.split(":")[0];
                String password = authString.split(":")[1];
                userList.add(new User(username, password));
            }
        } catch (FileNotFoundException fnfe) {
            System.out.println("Tiedostoa ei löydy.");
        }
        return userList;
    }

    public User findByName(String name) {
        try {
            Scanner scanner = new Scanner(f);
            while (scanner.hasNextLine()) {
                String authString = scanner.nextLine();
                String username = authString.split(":")[0];
                String password = authString.split(":")[1];
                if (username.equals(name))
                    return new User(username, password);
            }
        } catch (FileNotFoundException fnfe) {
            System.out.println("Tiedostoa ei löydy");
        }
        return null;
    }

    public void add(User user) {
        try (FileWriter fw = new FileWriter(f, true)) {
            fw.write(user.getUsername() + ":" + user.getPassword());
        } catch (IOException ioe) {
            System.out.println("Tiedostoon ei voitu kirjoittaa: " + ioe.getMessage());
        }
    }
}