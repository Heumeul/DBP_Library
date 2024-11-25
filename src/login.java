import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class login {
    public static void main(String[] args) {
        JFrame frame = new JFrame("DEU Library");
        frame.setSize(400, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel titleLabel = new JLabel("도서관 로그인");
        titleLabel.setBounds(100, 70, 200, 30);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 30));
        frame.add(titleLabel);

        JRadioButton studentButton = new JRadioButton("학생");
        studentButton.setBounds(110, 130, 60, 30);
        studentButton.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        studentButton.setSelected(true);
        JRadioButton staffButton = new JRadioButton("교직원");
        staffButton.setBounds(200, 130, 80, 30);
        staffButton.setFont(new Font("맑은 고딕", Font.BOLD, 15));

        ButtonGroup roleGroup = new ButtonGroup();
        roleGroup.add(studentButton);
        roleGroup.add(staffButton);

        frame.add(studentButton);
        frame.add(staffButton);

        // 학번 입력 필드(default)
        JLabel idLabel = new JLabel("학번");
        idLabel.setBounds(70, 200, 80, 30);
        idLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        frame.add(idLabel);

        JPasswordField idField=new JPasswordField();
        idField.setBounds(120, 200, 180, 30);
        frame.add(idField);

        JLabel nameLabel = new JLabel("이름");
        nameLabel.setBounds(70, 250, 80, 30);
        nameLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        frame.add(nameLabel);

        JTextField nameField = new JTextField();
        nameField.setBounds(120, 250, 180, 30);
        frame.add(nameField);

        JButton loginButton = new JButton("로그인");
        loginButton.setBounds(100, 320, 200, 40);
        frame.add(loginButton);

        // 학생 라디오 버튼 클릭시
        studentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                idLabel.setText("학번");
                idField.setText(null);
                nameField.setText(null);
            }
        });

        // 교직원 라디오 버튼 클릭시
        staffButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                idLabel.setText("교번");
                idField.setText(null);
                nameField.setText(null);
            }
        });

        // 로그인 버튼 클릭시 개인 페이지로 이동
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName=nameField.getText();
                frame.dispose();
                mainPage.main(userName);
            }
        });

        frame.setVisible(true);
    }
}