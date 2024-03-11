// A simple calculator applet. 
import java.awt.*; 
import java.awt.event.*; 
import java.applet.*; 
/* 
  <applet code="Calc" width=200 height=150> 
  </applet> 
*/ 
 
public class Calc extends Applet 
  implements ActionListener { 
 
  TextField expText, resText; 
  Parser p;  
 
  public void init() { 
    Label heading = new 
          Label("Expression Calculator ", Label.CENTER); 
 
    Label explab = new Label("Expression ", Label.CENTER); 
    Label reslab = new Label("Result     ", Label.CENTER); 
    expText = new TextField(24); 
    resText = new TextField(24); 
 
    resText.setEditable(false); // result field for display only 
 
    add(heading); 
    add(explab); 
    add(expText); 
    add(reslab); 
    add(resText); 
 
    /* Register expression text field 
       to receive action events. */ 
    expText.addActionListener(this); 
 
    // create parser 
    p = new Parser();  
  } 
 
  // User pressed Enter. 
  public void actionPerformed(ActionEvent ae) { 
    repaint(); 
  } 
 
  public void paint(Graphics g) { 
    double result = 0.0; 
    String expstr = expText.getText(); 
 
    try { 
      if(expstr.length() != 0)  
        result = p.evaluate(expstr);  
 
// To clear expression after ENTER is pressed 
// use the folloing line: 
//    expText.setText(""); 
 
      resText.setText(Double.toString(result)); 
 
      showStatus(""); // erase any previous error message 
    } catch (ParserException exc) { 
      showStatus(exc.toString()); 
      resText.setText(""); 
    } 
  } 
}
