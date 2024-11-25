import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class employeeLogin {
    public static void main(String userName) {
        JFrame frame = new JFrame("DEU Library");
        frame.setSize(300, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel titleLabel = new JLabel("근로자 로그인");
        titleLabel.setBounds(45, 70, 200, 30);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 25)); // 폰트 크기 변경
        frame.add(titleLabel);

        JLabel idLabel = new JLabel("근로ID");
        idLabel.setBounds(50, 145, 50, 30);
        idLabel.setFont(new Font("맑은 고딕", Font.BOLD, 10)); // 폰트 크기 변경
        frame.add(idLabel);

        JPasswordField idField = new JPasswordField();
        idField.setBounds(95, 145, 140, 30);
        frame.add(idField);

        JButton loginButton = new JButton("로그인");
        loginButton.setBounds(65, 220, 170, 40);
        loginButton.setFont(new Font("맑은 고딕", Font.BOLD, 10)); // 폰트 크기 변경
        frame.add(loginButton);

        // 로그인 버튼 클릭시 관리자 페이지로 이동
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                employeePage.main(userName);
            }
        });

        // 프레임 표시
        frame.setVisible(true);
    }
}
