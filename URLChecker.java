import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.BorderLayout;
import java.net.HttpURLConnection;
import java.net.URL;

public class URLChecker implements ActionListener {
    Integer i = 0;
    private JTextField urlField;
    private JButton actionButton;
    private JLabel resultLabel;
    private JPanel panel;
    private JFrame frame;

    public URLChecker() {

        urlField = new JTextField(20);

        actionButton = new JButton("Check URL");
        actionButton.addActionListener(this);

        resultLabel = new JLabel("Response: ");

        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(urlField);
        panel.add(actionButton);
        panel.add(resultLabel);

        frame = new JFrame();
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("URL Checker");
        frame.pack();
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent clickActionButton) {
        String inputURL = urlField.getText();
        resultLabel.setText(urlResponse(inputURL));
    }
    
    public String urlResponse(String url) {
        try {
            URL usedURL = new URL(url);
            HttpURLConnection http = (HttpURLConnection)usedURL.openConnection();
            String statusCode = String.valueOf(http.getResponseCode());
            if (statusCode.substring(0,1).contains("1") == true) {
                return "Response: Informational " + statusCode;
            }
            if (statusCode.substring(0,1).contains("2") == true) {
                return "Response: Success " + statusCode;
            }
            if (statusCode.substring(0,1).contains("3") == true) {
                return "Response: Redirection " + statusCode;
            }
            if (statusCode.substring(0,1).contains("4") == true) {
                return "Response: Client Error " + statusCode;
            }
            if (statusCode.substring(0,1).contains("5") == true) {
                return "Response: Server Error " + statusCode;
            }
            else {
                return "Error trying to connect, try again.";
            }
        }
        catch(IOException e) {
            return "Error trying to connect, try again.";
        }
    }

    public static void main(String[] args) {
        new URLChecker();
    }
}