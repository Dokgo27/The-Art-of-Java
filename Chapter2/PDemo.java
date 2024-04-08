// Demonstrate the parser.  
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
  
class PDemo1 extends JFrame{
    private JLabel enterlabel;
    private JLabel resultlabel;
    private JTextField expText;
    private JTextField resultText;
    private JButton calButton;

    public PDemo1(){

        setTitle("Calculator"); // 프레임 제목 설정
        setSize(500, 200); // 프레임 크기 설정

        Container container = getContentPane();
        container.setLayout(null);

        enterlabel = new JLabel("Write the expression");
        enterlabel.setBounds(10, 10, 150, 30);
        container.add(enterlabel);

        expText = new JTextField();
        expText.setBounds(10, 40, 300, 30);
        container.add(expText);

        calButton = new JButton("calculation");
        calButton.setBounds(350, 40, 100, 30);
        container.add(calButton);

        resultlabel = new JLabel("result");
        resultlabel.setBounds(10, 80, 100, 30);
        container.add(resultlabel);

        resultText = new JTextField();
        resultText.setBounds(10, 110, 300, 30);
        container.add(resultText);

        Parser2 p = new Parser2();
        p.init();

        calButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 계산 버튼 클릭 시 수식을 계산하여 결과를 표시
                String expr = expText.getText();
                try {
                    resultText.setText("Result: " + p.evaluate(expr));  //evaluate 메서드 호출

                } catch (ParserException2 exc) {
                    resultText.setText(String.valueOf(exc));
                }
            }
        });
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true); //화면에 프레임 출력
    }

    public static void main(String args[])  throws IOException {
      // PDemo1 객체 생성
      PDemo1 fr = new PDemo1();
    }
}