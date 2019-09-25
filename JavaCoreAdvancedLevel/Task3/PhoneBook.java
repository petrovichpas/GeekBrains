package Task3;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    public static void checkPassword(String password){
        Pattern pattern = Pattern.compile("(?=.*?[0-9]) (?=.*?[a-z]) (?=.*?[A-Z]) \\S{8,}");
        System.out.println(pattern.matcher(password));
    }

    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();
        phoneBook.add("Petr", "8552895222", "877777777");
        phoneBook.add("Vaska", "8999999999");
        phoneBook.get("Petr");
        // дополнительное задание "(?=.*[0-9]) (?=.*[a-z]) (?=.*[A-Z]) \\S{8,}"
    }
}
