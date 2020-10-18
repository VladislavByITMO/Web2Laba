package model;

import java.util.ArrayList;
import java.util.List;

public class Model {

    List<Dot> dots;


    public List<Dot> getDotsList() {
        return dots;
    }


    public void addDot(Dot dot) {
        this.dots.add( dot);
    }


    public Model() {
        dots = new ArrayList<>();
    }

}
