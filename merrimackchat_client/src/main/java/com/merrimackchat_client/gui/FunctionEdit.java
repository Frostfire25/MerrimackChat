/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.merrimackchat_client.gui;

/**
 *
 * @author Mark Case
 */
public class FunctionEdit {
    
    myGUI gui = new myGUI(); 
    
//    public FunctionEdit(myGUI gui) {
//        this.gui = gui;
//    }

    public FunctionEdit() {
      
    }
    
    public void undo() {
        gui.um.undo();
    }
    
    public void redo() {
        gui.um.redo();
    }


}
