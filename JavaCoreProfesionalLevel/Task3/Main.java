package JavaCoreProfessionalLevel.Task3;

import java.io.*;
import java.util.Scanner;
import java.util.Vector;

public class Main implements Serializable{
    public static void main(String[] args) {
        read50Bytes();
        sequenceExm();
        readPage();
        reverse();
    }

    public static void read50Bytes(){
        try(FileInputStream inputStream = new FileInputStream("src\\JavaCoreProfessionalLevel\\Task3\\files\\Pushkin.txt")) {
            byte[] buf = new byte[50];
            inputStream.read(buf);
            System.out.print(new String(buf, 0, buf.length, "UTF-8") + "\n");
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sequenceExm(){
        Vector files = new Vector();
        for (int i = 1; i < 6; i++) {
            try {
                files.addElement(new FileInputStream("src\\JavaCoreProfessionalLevel\\Task3\\files\\" + i + ".txt"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        try (SequenceInputStream seq = new SequenceInputStream(files.elements());
             FileOutputStream out = new FileOutputStream("src\\JavaCoreProfessionalLevel\\Task3\\files\\out.txt")) {
            int x;
            while ((x = seq.read()) != -1) {
                out.write(x);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readPage(){
        System.out.println("Введи номер страницы:");
        try (RandomAccessFile raf = new RandomAccessFile("src\\JavaCoreProfessionalLevel\\Task3\\files\\BigText.txt", "r")) {
            int page = new Scanner(System.in).nextInt();
            raf.seek(page <= 1 ? 0 : (page - 1) * 1800);
            for (int i = 0; i < 1800; i++) {
                System.out.print((char) raf.read());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void reverse(){
        try (RandomAccessFile raf = new RandomAccessFile("src\\JavaCoreProfessionalLevel\\Task3\\files\\out.txt", "r")) {
            for (long i = raf.length() - 1; i >= 0; i--) {
                raf.seek(i);
                System.out.print((char) raf.read());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
