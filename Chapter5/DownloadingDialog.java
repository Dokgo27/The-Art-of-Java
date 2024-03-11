import java.awt.*;
import javax.swing.*;

/* 이 클래스는 메세지가 다운로딩되고 있음을 사용자에게 알리는 
 * 간단한 대화상자를 보여준다.*/
public class DownloadingDialog extends JDialog
{
  // 생성자
  public DownloadingDialog(Frame parent)
  {
    // 상위 클래스 생성자, 대화상자가 모 임을 명세
    super(parent, true);

    // 타이틀 설정
    setTitle("E-mail Client");

    // "X" 가 클릭되더라도 윈도우가 닫히지 않도록 함
    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

    // 다운로드하는 동안 메세지 출력
    JPanel contentPane = new JPanel();
    contentPane.setBorder(
      BorderFactory.createEmptyBorder(5, 5, 5, 5));
    contentPane.add(new JLabel("Downloading messages..."));
    setContentPane(contentPane);

    // 컴포넌트에 맞춰서 대화상자의 크기를 설정.
    pack();

    // 대화상자를 어플리케이션의 중앙에 맞춤.
    setLocationRelativeTo(parent);
  }
}
