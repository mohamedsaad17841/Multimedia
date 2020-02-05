import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class gui {
    private JButton compress;
    private JPanel addaptiveHuffman;
    private JTextField textField1;
    private JButton decompressionButton;
    private JTextField textField2;

    public gui() {
        compress.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Main.codeSet(5);
                String res = Main.compress("ABCCCAAAA");
                textField1.setText(res);
            }
        });
        decompressionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Main.codeSet(5);
                String res = Main.decompress("000010010101000101110");
                textField2.setText(res);
            }
        });
    }
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("gui");
        frame.setContentPane(new gui().addaptiveHuffman);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
