package com.example.backend.Solution.Triangle;

public class Triangle {
    private int slide1;
    private int slide2;
    private int slide3;

    public Triangle(){
        this(1, 1, 1);
    }

    public Triangle(int slide1, int slide2, int slide3) {
        this.slide1 = slide1;
        this.slide2 = slide2;
        this.slide3 = slide3;
    }

    private boolean checkValid(int slide1, int slide2, int slide3){
        if(slide1 < 1 || slide1 > 100 || slide2 < 1 || slide2 > 100 || slide3 < 1 || slide3 > 100){
            return false;
        }
        return true;
    }

    private boolean checkRightAngle(int slide1, int slide2, int slide3){
        int square1 = slide1 * slide1;
        int square2 = slide2 * slide2;
        int square3 = slide3 * slide3;

        if(square1 + square2 == square3 || square3 + square2 == square1 || square1 + square3 == square2)
            return true;

        return false;
    }

    public String checkType(int slide1, int slide2, int slide3){
        if(!checkValid(slide1, slide2, slide3))
            return "Invalid";

        if(slide1 + slide2 <= slide3 || slide2 + slide3 <= slide1 || slide3 + slide1 <= slide2)
            return "Cannot Form";

        if(slide1 == slide2 && slide2 == slide3)
            return "Equal";
        // 由于所有边都是int 故不设置此分支
//        else if((slide1 == slide2 || slide1 == slide3 || slide2 == slide3) && (checkRightAngle(slide1, slide2, slide3)))
//            return "IsoRt";
        else if(slide1 == slide2 || slide1 == slide3 || slide2 == slide3)
            return "Iso";
        else if(checkRightAngle(slide1, slide2, slide3))
            return "Rt";
        else
            return "Normal";
    }

}
