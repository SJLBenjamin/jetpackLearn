package com.example.jetpacklearn;

import android.database.Observable;

import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;

/**
 * bean类,将数据源充当被观察者,通过ObservableXXX设置,记住需要设置为public,否则无法访问
 */
public class StudentObservable {
  public ObservableField<String>  sex ;
   public ObservableInt weight ;

    public StudentObservable(ObservableField<String> sex, ObservableInt weight) {
        this.sex = sex;
        this.weight = weight;
    }
}
