package com.example.jessl.alarm.food;

public class Facts {
    private  int mImage;
    private  String mFact;
    private  int mImage1;
    private  String call1;

    public Facts(int mImage,String mFact,int mImage1,String call1){
        this.mImage = mImage;
        this.mImage1 = mImage1;
        this.mFact = mFact;
        this.call1 = call1;
    }

    public int getmImage(){
        return mImage;
    } //shopImage

    public String getmFact(){
        return mFact;
    }//shopName

    public int getmImage1() {return mImage1;}//shopMenu

    public String getmcall1() {return call1;}//callshop
}
