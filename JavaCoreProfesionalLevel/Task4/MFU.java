package Task4;

public class MFU {
    public static synchronized void print(String document){
        System.out.println(document + "\n" + "Печать завершена.");
    }

    public synchronized static void scan(){
        System.out.println("Сканирование завершено.");
    }

    public static void main(String[] args) {
        new Thread(()-> print("Бухгалтерский баланс 2018")).start();
        new Thread(()-> print("Отчет о прибылях и убытках 2018")).start();
        new Thread(()-> print("Дальняя бомбардировочная. Голованов А.Е.")).start();
        new Thread(()-> scan()).start();
        new Thread(()-> scan()).start();
        new Thread(()-> scan()).start();
    }
}
