package Task7;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

public class Reflection {

    public static void main(String[] args) throws Exception {
        new Reflection().start();
    }

    private void start() throws Exception {
        String[] fileList = new File("target/classes/Task7").list();
        ArrayList<String> classes = new ArrayList<>();

        for (String o : fileList) {
            if (o.toLowerCase().contains(".class")) {
                String[] mass = o.split("\\.");
                classes.add(mass[0]);
            }
        }

        for (String s : classes) {
            Class clazz = URLClassLoader.newInstance(new URL[]{new File("target/classes/Task7").toURL()}).loadClass("Task7." + s);
            Method[] methods = clazz.getDeclaredMethods();
            ArrayList<Method> list = new ArrayList<>();

            for (int i = 0; i < methods.length; i++) {
                if (methods[i].isAnnotationPresent(Task7.annotations.Test.class)) {
                    list.add(methods[i]);
                }
            }
            list.sort((mt1, mt2) -> mt1.getAnnotation(Task7.annotations.Test.class).priority() - mt2.getAnnotation(Task7.annotations.Test.class).priority());

            for (Method mt : methods) {
                if (mt.isAnnotationPresent(Task7.annotations.BeforeSuite.class)) {
                    list.add(0, mt);
                }
                if(mt.isAnnotationPresent(Task7.annotations.AfterSuite.class)) {
                    list.add(mt);
                }
            }
            for (Method mt : list) {
                if (!list.get(0).isAnnotationPresent(Task7.annotations.BeforeSuite.class)) {
                    throw new RuntimeException("Не обнаружен метод с аннотацией @BeforeSuite");
                }

                if (!list.get(list.size() - 1).isAnnotationPresent(Task7.annotations.AfterSuite.class)){
                    throw new RuntimeException("Не обнаружен метод с аннотацией @AfterSuite");
                }

                switch (mt.getName()) {
                    case "startTest":
                        clazz.getDeclaredMethod(mt.getName()).invoke(clazz.getConstructor().newInstance());
                        break;
                    case "calculate":
                        Method calculate = clazz.getDeclaredMethod(mt.getName(), int.class, int.class, int.class, int.class);
                        calculate.setAccessible(true);
                        System.out.println(calculate.invoke(clazz.getConstructor().newInstance(), 1, 2, 3, 4).equals(2) ?
                                "Test " + mt.getName() + " accept." : "Test " + mt.getName() + " failed.");
                        break;
                    case "checkTwoNumbers":
                        Method checkTwoNumbers = clazz.getDeclaredMethod(mt.getName(), int.class, int.class);
                        checkTwoNumbers.setAccessible(true);
                        System.out.println(checkTwoNumbers.invoke(clazz.getConstructor().newInstance(), 3, 4).equals(false) ?
                                "Test " + mt.getName() + " accept." : "Test " + mt.getName() + " failed.");
                        break;
                    case "printIsPositive":
                        Method printIsPositive = clazz.getDeclaredMethod(mt.getName(), int.class);
                        printIsPositive.setAccessible(true);
                        printIsPositive.invoke(clazz.getConstructor().newInstance(), 84);
                        break;
                    case "isNegative":
                        Method isNegative = clazz.getDeclaredMethod(mt.getName(), int.class);
                        isNegative.setAccessible(true);
                        System.out.println(isNegative.invoke(clazz.getConstructor().newInstance(), 4).equals(false) ?
                        "Test " + mt.getName() + " accept." : "Test " + mt.getName() + " failed.");
                        break;
                    case "printWelocome":
                        Method printWelocome = clazz.getDeclaredMethod(mt.getName(), String.class);
                        printWelocome.setAccessible(true);
                        printWelocome.invoke(clazz.getConstructor().newInstance(), "POG");
                        break;
                    case "isLeapYear":
                        Method isLeapYear = clazz.getDeclaredMethod(mt.getName(), int.class);
                        isLeapYear.setAccessible(true);
                        System.out.println(isLeapYear.invoke(clazz.getConstructor().newInstance(), 1000).equals(false) ?
                        "Test " + mt.getName() + " accept." : "Test " + mt.getName() + " failed.");
                        break;
                    case "endTest":
                        clazz.getDeclaredMethod(mt.getName()).invoke(clazz.getConstructor().newInstance());
                        break;
                }
            }
        }
    }
}
