package com.example.myapplication;

public class Caozuo {
    int time;
    int mome;

    Caozuo(){
        mome=1;
    }

    public String a(String str){
        if(mome==1) return (Integer.parseInt(str)+1)%6+"";
        else return str;
    }

    public String b(String str){
        if(mome==1) return (Integer.parseInt(str)+1)%10+"";
        else return str;
    }

    public void begin(){
        switch (mome){
            case 1:
                mome=2;
                break;
            case 2:
                mome=3;
                break;
            case 3:
                mome=2;
                break;
        }
    }

    public String run(String str){
        int hour,min,second;
        hour=(str.charAt(0)-'0')*10+(str.charAt(1)-'0');
        min=(str.charAt(2)-'0')*10+(str.charAt(3)-'0');
        second=(str.charAt(4)-'0')*10+(str.charAt(5)-'0');
        if(second>0) second--;
        else if(min>0){
            min--;
            second=59;
        }
        else if(hour>0){
            hour--;
            min=59;
            second=59;
        }
        return ""+hour/10+hour%10+min/10+min%10+second/10+second%10;
    }

    public String zero(){
        mome=1;
        return init();
    }

    public String init(){
        return "000000";
    }
}
