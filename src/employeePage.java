import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class employeePage {
    public static void main(String enteredId, String userName) {
        JFrame frame = new JFrame("DBP Library");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500);
        frame.setLayout(new BorderLayout());

        String titleSuffix = "님 근로 Page";

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BorderLayout());
        titlePanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0)); // 여백 추가

        JLabel titleLabel = new JLabel(userName + titleSuffix, SwingConstants.CENTER);
        titleLabel.setFont(new Font("돋움", Font.BOLD, 16));
        titlePanel.add(titleLabel, BorderLayout.CENTER);

        JLabel logoutLabel = new JLabel("<html><u>로그아웃</u></html>");
        logoutLabel.setForeground(Color.BLACK);
        logoutLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        logoutLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        titlePanel.add(logoutLabel, BorderLayout.EAST);

        // 로그아웃 클릭 시 첫 로그인 화면으로 이동
        logoutLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                login.main(null);
            }
        });

        frame.add(titlePanel, BorderLayout.NORTH);

        JTabbedPane tabbedPane = new JTabbedPane();

        // 근무 정보 탭
        JPanel workInfoPanel = new JPanel();
        workInfoPanel.setLayout(null);

        JLabel workDate = new JLabel("근무일자: ");
        workDate.setBounds(13, 10, 60, 30);
        JLabel toDay = new JLabel();
        toDay.setBounds(70, 10, 80, 30);
        workInfoPanel.add(workDate);
        workInfoPanel.add(toDay);

        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        toDay.setText(currentDate.format(formatter));

        JLabel workHours = new JLabel("일일근무시간");
        workHours.setBounds(150, 10, 80, 30);
        workInfoPanel.add(workHours);

        JComboBox<Integer> workTimes = new JComboBox<>(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20});
        workTimes.setBounds(230, 10, 50, 30);
        workInfoPanel.add(workTimes);

        JButton addInfoButton = new JButton("등록");
        addInfoButton.setBounds(300, 10, 60, 30);
        workInfoPanel.add(addInfoButton);

        // 근무 정보 테이블
        String[] columns = {"근무번호", "근무일자", "근무시간", "일일급여"};
        DefaultTableModel workInfoModel = new DefaultTableModel(columns, 0);
        JTable workInfoTable = new JTable(workInfoModel);
        JScrollPane workInfoScrollPane = new JScrollPane(workInfoTable);
        workInfoScrollPane.setBounds(15, 70, 350, 300);
        workInfoPanel.add(workInfoScrollPane);

        // 샘플 데이터 추가
        workInfoModel.addRow(new Object[]{"001", "2023-11-01", "8시간", "80,000원"});
        workInfoModel.addRow(new Object[]{"002", "2023-11-02", "6시간", "60,000원"});

        tabbedPane.addTab("근무정보", workInfoPanel);

        // 월급조회 탭
        JPanel salaryPanel = new JPanel();
        salaryPanel.setLayout(null);

        JComboBox<String> yearCombo = new JComboBox<>(new String[]{"2023", "2024", "2025"});
        yearCombo.setBounds(50, 50, 100, 30);
        JComboBox<String> monthCombo = new JComboBox<>(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"});
        monthCombo.setBounds(200, 50, 100, 30);

        JLabel yearLabel = new JLabel("년");
        yearLabel.setBounds(160, 50, 20, 30);
        JLabel monthLabel = new JLabel("월");
        monthLabel.setBounds(310, 50, 20, 30);

        salaryPanel.add(yearCombo);
        salaryPanel.add(yearLabel);
        salaryPanel.add(monthCombo);
        salaryPanel.add(monthLabel);

        JButton queryButton = new JButton("월급 조회");
        queryButton.setBounds(135, 150, 100, 30);
        salaryPanel.add(queryButton);

        JLabel resultLabel = new JLabel("", SwingConstants.CENTER);
        resultLabel.setFont(new Font("돋움", Font.BOLD, 14));
        resultLabel.setBounds(50, 200, 300, 60);
        salaryPanel.add(resultLabel);

        queryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String year = (String) yearCombo.getSelectedItem();
                String month = (String) monthCombo.getSelectedItem();
                String time = "160";
                String pay = "1,600,000";
                resultLabel.setText("<html>" + year + "년 " + month + "월<br>총 " + time + "시간 근무<br>월급 " + pay + "원</html>");
            }
        });

        tabbedPane.addTab("월급조회", salaryPanel);

        // 연체자조회 탭
        JPanel overduePanel = new JPanel();
        overduePanel.setLayout(new BorderLayout());

        String[] overdueColumns = {"학번/교번", "연체자명", "연체일", "대출불가일"};
        DefaultTableModel overdueModel = new DefaultTableModel(overdueColumns, 0);
        JTable overdueTable = new JTable(overdueModel);
        JScrollPane overdueScrollPane = new JScrollPane(overdueTable);

        overdueModel.addRow(new Object[]{"20231234", "김철수", "2023-11-20", "2023-12-01"});
        overdueModel.addRow(new Object[]{"20231235", "박영희", "2023-11-15", "2023-11-30"});

        overduePanel.add(overdueScrollPane, BorderLayout.CENTER);

        tabbedPane.addTab("연체자조회", overduePanel);

        frame.add(tabbedPane, BorderLayout.CENTER);

        frame.setVisible(true);
    }
}
