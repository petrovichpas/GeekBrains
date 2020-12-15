
public class Main {
    public static void main(String[] args) {
        Person person = new Person.PersonBuilder().addFirstName("Valentin").addCountry("RU").addAge(30).build();
        Person person2 = new Person.PersonBuilder().addLastName("Ivanov").addCountry("GB").addAge(33).build();
        System.out.println(person.getCountry());
        System.out.println(person2.getCountry());

        Figures f = new Figures();
        f.draw(new Figures().new Square());
        f.draw(new Figures().new Circle());
        f.draw(new Figures().new Triangle());
    }
}


