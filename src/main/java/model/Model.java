package model;

import point.Dot;

import java.util.ArrayList;

public class Model {

    public static ArrayList<Dot> Dots= new ArrayList<>();

    public void setDot(Dot dot){
        Dots.add(dot);
    }

    public ArrayList<Dot> getDots() {
        return Dots;
    }
    public String DrawDot(){
        if(!Dots.isEmpty()){
           Dot dot = Dots.get(Dots.size()-1);

        }
        return "sdf";
    }
}
