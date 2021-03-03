package by.bookstore.list;

import java.util.ArrayList;
import java.util.List;

public class Test<T> {

    T type;

    public T test(T t){
        return t;
    }

    public static <E> E abc(E e){
        return e;
    }

    public void sort(List<T> list){

    }




    public static void main(String[] args) {
        Test<Integer> integerTest = new Test<>();
        Integer test = integerTest.test(22);
        integerTest.sort(new ArrayList<>());

        String dasd = Test.abc("dasd");

        Integer abc = Test.abc(23);


        Test<String> stringTest = new Test<>();
        String sdsd = stringTest.test("sdsd");
    }
}
