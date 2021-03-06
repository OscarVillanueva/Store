package sample.controllers;

import javafx.scene.control.TextField;

public class NumberTextField extends TextField {

    public NumberTextField(){
        this.setPromptText("Only Numbers");
    }

    @Override
    public void replaceText(int i,int i1, String string){
        if(string.matches("[0-9]")||string.isEmpty()||string.matches("[0.0-9.0]")||string.matches("[-0.0-9.0]"))
            super.replaceText(i, i1, string);
    }

    @Override
    public void replaceSelection(String string){
        super.replaceSelection(string);
    }
}
