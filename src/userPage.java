import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

public class userPage {
    public static void main(int userId, String userName) {
        JFrame frame = new JFrame("DEU Library");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500);
        frame.setLayout(null);

        String titleSuffix = "님 도서 이용 현황";

        JLabel titleLabel = new JLabel(userName + titleSuffix, SwingConstants.CENTER);
        titleLabel.setFont(new Font("돋움", Font.BOLD, 16));
        titleLabel.setBounds(50, 30, 300, 30);
        frame.add(titleLabel);

        JLabel back = new JLabel("<이전");
        back.setBounds(13, 10, 60, 30);
        back.setCursor(new Cursor(Cursor.HAND_CURSOR));
        frame.add(back);

        // 뒤로가기 버튼 클릭시 mainPage로 이동
        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                mainPage.main(userId, userName);
            }
        });

        JPanel userPanel = new JPanel();
        userPanel.setLayout(new BorderLayout());
        userPanel.setBounds(13, 80, 360, 300); // 위치와 크기 설정 (필수)
        frame.add(userPanel);

        // 테이블 생성
        String[] overdueColumns = {"대출번호", "도서명", "저자", "대출일자", "연체기한"};
        DefaultTableModel overdueModel = new DefaultTableModel(overdueColumns, 0);
        JTable overdueTable = new JTable(overdueModel);
        JScrollPane overdueScrollPane = new JScrollPane(overdueTable); // 스크롤 추가

        userPanel.add(overdueScrollPane, BorderLayout.CENTER);

        JButton returnButton = new JButton("반납");
        returnButton.setBounds(13, 400, 360, 30);
        frame.add(returnButton);

        try {
            Connect dbConnection = new Connect();
            dbConnection.DB_Connect();

            ResultSet rs;

            if (String.valueOf(userId).length() == 5) {
                rs = dbConnection.getStaffLoanData(userId);
            } else if (String.valueOf(userId).length() == 6) {
                rs = dbConnection.getStudentLoanData(userId);
            } else {
                JOptionPane.showMessageDialog(frame, "잘못된 ID 형식입니다.", "오류", JOptionPane.ERROR_MESSAGE);
                frame.dispose();
                return;
            }

            // ResultSet 데이터를 테이블에 추가
            while (rs != null && rs.next()) {
                overdueModel.addRow(new Object[]{
                        rs.getInt("대출번호"),
                        rs.getString("도서명"),
                        rs.getString("저자"),
                        rs.getDate("대출일자"),
                        rs.getDate("연체기한")
                });
            }
        } catch (SQLException e) {
            System.out.println("Error loading table data: " + e.getMessage());
        }

        Connect dbConnection = new Connect();
        dbConnection.DB_Connect(); // DB 연결 초기화

        returnButton.addActionListener(e -> {
            int selectedRow = overdueTable.getSelectedRow();
            if (selectedRow != -1) {
                overdueModel.removeRow(selectedRow);
                JOptionPane.showMessageDialog(frame, "선택된 도서가 반납되었습니다.");
            } else {
                JOptionPane.showMessageDialog(frame, "반납할 도서를 선택하세요.");
            }
        });

        frame.setVisible(true);
    }
}