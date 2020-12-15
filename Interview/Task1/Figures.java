public class Figures {
    public void draw(Figures f){
        System.out.println("Drawing " + f.getClass().getSimpleName());
    }

    public class Square extends Figures {

    }

    public class Circle extends Figures {

    }

    public class Triangle extends Figures {

    }
}
