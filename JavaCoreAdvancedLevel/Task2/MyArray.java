package JavaCoreAdvancedLevel.Task2;

public class MyArray {

    public static void main(String[] args) {
        try {
            System.out.println(getSumArr(new String[][] {{"-1", "2", "3", "4"}, {"6", "7", "8", "9"}}));
        } catch (MyArraySizeException e) {
            e.printStackTrace();
        } catch (MyArrayDataException e) {
            e.printStackTrace();
        }
    }

    public static int getSumArr(String[][] arr) throws MyArraySizeException, MyArrayDataException {
        int sum = 0;

        if (arr[0].length == 4 && arr[1].length == 4){
            for (int i = 0; i < arr.length; i++){
                for (int j = 0; j < arr[1].length; j++){
                    try {
                        sum += Integer.parseInt(arr[i][j]);
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
