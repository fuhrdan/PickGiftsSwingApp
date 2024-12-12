import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class PickGiftsSwingApp {

    public static void main(String[] args) {
        // Create the main frame
        JFrame frame = new JFrame("Pick Gifts Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Create the panel to hold input fields and buttons
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1));

        // Input field for gifts array
        JLabel giftsLabel = new JLabel("Enter gifts (comma-separated):");
        JTextField giftsField = new JTextField();
        panel.add(giftsLabel);
        panel.add(giftsField);

        // Input field for k
        JLabel kLabel = new JLabel("Enter value of k:");
        JTextField kField = new JTextField();
        panel.add(kLabel);
        panel.add(kField);

        // Button to calculate result
        JButton calculateButton = new JButton("Calculate");
        JLabel resultLabel = new JLabel("Result: ");
        panel.add(calculateButton);
        panel.add(resultLabel);

        // Add panel to frame
        frame.add(panel);

        // Action listener for the button
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Parse input values
                    String giftsInput = giftsField.getText();
                    int[] gifts = Arrays.stream(giftsInput.split(","))
                            .mapToInt(Integer::parseInt)
                            .toArray();

                    int k = Integer.parseInt(kField.getText());

                    // Calculate the result using the pickGifts method
                    long result = pickGifts(gifts, k);

                    // Display the result
                    resultLabel.setText("Result: " + result);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid input! Please enter valid numbers.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Set frame visibility
        frame.setVisible(true);
    }

    public static long pickGifts(int[] gifts, int k) {
        for (int i = 0; i < k; i++) {
            int left = 0;
            for (int j = 1; j < gifts.length; j++) {
                if (gifts[j] > gifts[left]) {
                    left = j;
                }
            }
            gifts[left] = (int) Math.sqrt(gifts[left]);
        }

        long total = 0;
        for (int gift : gifts) {
            total += gift;
        }

        return total;
    }
}
