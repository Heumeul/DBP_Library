import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class mainPage {
    public static void main(int userId, String userName) {
        JFrame frame = new JFrame("DEU Library");
        frame.setSize(400, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JButton myPageButton = new JButton("My Page");
        myPageButton.setBounds(10, 10, 100, 30);
        frame.add(myPageButton);

        // My Page 클릭시 개인 도서 이용 현황 페이지로 이동
        myPageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // 현재 창 닫기
                userPage.main(userId, userName); // userPage 열기
            }
        });

        JLabel logoutLabel = new JLabel("<html><u>로그아웃</u></html>");
        logoutLabel.setBounds(270, 10, 50, 30);
        logoutLabel.setForeground(Color.BLACK); // 파란색 텍스트
        logoutLabel.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 손가락 커서
        frame.add(logoutLabel);

        // 로그아웃 클릭시 로그인 페이지로 이동
        logoutLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                login.main(null);
            }
        });

        JLabel employeeLabel = new JLabel("<html><u>근무자</u></html>");
        employeeLabel.setBounds(330, 10, 50, 30);
        employeeLabel.setForeground(Color.BLUE); // 파란색 텍스트
        employeeLabel.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 손가락 커서
        frame.add(employeeLabel);

        // 근무자 클릭시 근무자 로그인 페이지로 이동
        employeeLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                employeeLogin.main(userName);
            }
        });

        JLabel searchLabel = new JLabel("도서 검색");
        searchLabel.setBounds(160, 50, 200, 30);
        searchLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        frame.add(searchLabel);

        JLabel bookNameLabel = new JLabel("도서명:");
        bookNameLabel.setBounds(15, 100, 50, 30);
        frame.add(bookNameLabel);

        JTextField bookNameField = new JTextField();
        bookNameField.setBounds(60, 100, 100, 30);
        frame.add(bookNameField);

        JLabel authorLabel = new JLabel("저자:");
        authorLabel.setBounds(165, 100, 50, 30);
        frame.add(authorLabel);

        JTextField authorField = new JTextField();
        authorField.setBounds(200, 100, 100, 30);
        frame.add(authorField);

        JButton searchButton = new JButton("검색");
        searchButton.setBounds(313, 100, 60, 30);
        frame.add(searchButton);

        // 테이블 생성
        String[] columnNames = {"도서번호", "분야", "도서명", "저자", "출판사", "도서상태"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);

        JScrollPane tableScrollPane = new JScrollPane(table);
        tableScrollPane.setBounds(13, 150, 360, 200);
        frame.add(tableScrollPane);

        JButton loanButton = new JButton("대출");
        loanButton.setBounds(150, 370, 100, 40);
        frame.add(loanButton);

        frame.setVisible(true);
    }
}
