package JavaCoreAdvancedLevel.Task2;

public class MyArray {

    public static void main(String[] args) {
        try {
            // для проверки
//            System.out.println(getSumArr(new String[][] {{"1", "2", "3", "4", "5"}, {"6", "7", "8", "9"}}));
//            System.out.println(getSumArr(new String[][] {{"-1", "Х", "3", "4"}, {"6", "7", "8", "9"}}));
            System.out.println(getSumArr(new String[][] {{"-1", "2", "3", "4"}, {"6", "7", "8", "9"}}));
        } catch (MyArraySizeException e) {
            e.printStackTrace();
        } catch (MyArrayDataException e) {
            e.printStackTrace();
        }
    }

    public static int getSumArr(String[][] arr) throws MyArraySizeException, MyArrayDataException {
        int sum = 0;

        if (arr[0].length + arr[1].length == 8){
            for (int i = 0; i < arr.length; i++){
                for (int j = 0; j < arr[1].length; j++){
                    try {
                        if (Integer.parseInt(arr[i][j]) < 0 || Integer.parseInt(arr[i][j]) > 0) {
                            sum += Integer.parseInt(arr[i][j]);
                        }
                    }
                    catch (Exception e){
                        throw new MyArrayDataException("В ячейке " + i + j + " - NaN");
                    }
                }
            }
        }
        else throw new MyArraySizeException("Массив должен иметь размер 4х4");
        return sum;
    }
}
