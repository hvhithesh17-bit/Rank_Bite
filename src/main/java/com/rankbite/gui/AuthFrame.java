package com.rankbite.gui;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * Dedicated Customer & Owner Authentication Frame
 */
public class AuthFrame extends LoginFrame {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {}
            new AuthFrame().setVisible(true);
        });
    }
}
