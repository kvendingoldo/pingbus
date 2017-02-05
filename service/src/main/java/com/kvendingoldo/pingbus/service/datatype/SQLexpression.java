package com.kvendingoldo.pingbus.service.datatype;

public class SQLexpression {

    private String expression;

    public SQLexpression(){
        expression = null;
    }

    public SQLexpression(String expression){
        this.expression = expression;
    }

    public boolean isEmpty(){
        return ("".equals(expression));
    }

    public boolean isCorrect(){
        // pass
        return true;
    }

    public void put(String expression){
        this.expression = expression;
    }

    @Override
    public String toString(){
        return expression;
    }

}
