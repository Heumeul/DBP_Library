import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class userPage {
    public static void main(String userName) {
        JFrame frame = new JFrame("DEU Library");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500);
        frame.setLayout(null);

        String titleSuffix = "님 도서 이용 현황";

        JLabel titleLabel = new JLabel(userName + titleSuffix, SwingConstants.CENTER);
        titleLabel.setFont(new Font("돋움", Font.BOLD, 16));
        titleLabel.setBounds(50, 30, 300, 30);
        frame.add(titleLabel);

        // 뒤로가기 버튼
        JLabel back = new JLabel("<");
        back.setBounds(13, 10, 45, 30);
        back.setCursor(new Cursor(Cursor.HAND_CURSOR));
        frame.add(back);

        // 뒤로가기 버튼 클릭시 mainPage로 이동
        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                mainPage.main(userName);
            }
        });

        JPanel userPanel = new JPanel();
        userPanel.setLayout(new BorderLayout());
        userPanel.setBounds(13, 80, 360, 300); // 위치와 크기 설정 (필수)
        frame.add(userPanel);

        // 테이블 생성
        String[] overdueColumns = {"대출번호", "도서명", "저자", "대출일자", "반납일자"};
        DefaultTableModel overdueModel = new DefaultTableModel(overdueColumns, 0);
        JTable overdueTable = new JTable(overdueModel);
        JScrollPane overdueScrollPane = new JScrollPane(overdueTable); // 스크롤 추가

        // 샘플 데이터 추가
        overdueModel.addRow(new Object[]{"20231234", "강아지똥", "김철수", "2023-12-01", "2023-12-08"});
        overdueModel.addRow(new Object[]{"20231235", "강아지똥2", "박영희", "2023-11-30", "2023-12-07"});

        userPanel.add(overdueScrollPane, BorderLayout.CENTER);

        JButton returnButton = new JButton("반납");
        returnButton.setBounds(13, 400, 360, 30);
        frame.add(returnButton);

        returnButton.addActionListener(e -> {
            int selectedRow = overdueTable.getSelectedRow();
            if (selectedRow != -1) {
                overdueModel.removeRow(selectedRow); // 선택된 행 삭제
                JOptionPane.showMessageDialog(frame, "선택된 도서가 반납되었습니다.");
            } else {
                JOptionPane.showMessageDialog(frame, "반납할 도서를 선택하세요.");
            }
        });

        frame.setVisible(true);
    }
}
