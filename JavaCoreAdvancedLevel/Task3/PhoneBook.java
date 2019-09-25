package Task3;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneBook {
    Map<String, List<String>> phoneBook;

    public PhoneBook() {
        phoneBook = new HashMap<>();
    }

    public void add(String surname, String... number){
       phoneBook.put(surname, Arrays.asList(number));
    }

    public void get (String surname){
        System.out.println(phoneBook.get(surname));
    }

    // дополнительное задание
    public static void checkPassword(){
        String password = new Scanner(System.in).next();
        Pattern pattern = Pattern.compile("^(?=.*?[0-9])(?=.*?[a-z])(?=.*?[A-Z])\\S{8,}$");
        Matcher matcher = pattern.matcher(password);
        System.out.println(matcher.matches());
    }

    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();
        phoneBook.add("Petr", "8552895222", "877777777");
        phoneBook.add("Vaska", "8999999999");
        phoneBook.get("Petr");

        // дополнительное задание
        checkPassword();
    }
}
