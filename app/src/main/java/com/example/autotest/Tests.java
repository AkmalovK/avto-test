package com.example.autotest;

import java.util.ArrayList;

public class Tests {
    private static int counter = -1;
    private static ArrayList<TestModel> array;

    public static  void SetTestModel(ArrayList<TestModel> arr) {
        array = arr;
        counter = -1;
    }
    public static TestModel getArray() {
        counter++;
        if (counter >= array.size())
            return null;
        return array.get(counter);
    }
}
