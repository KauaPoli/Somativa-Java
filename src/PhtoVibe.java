import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class PhtoVibe {

    private static JFrame frame;
    private static CardLayout cardLayout;
    private static JPanel cardPanel;
    private static JTextField usernameField;
    private static JPasswordField passwordField;

    // Mapa para armazenar os usuários e senhas cadastrados
    private static Map<String, String> userDatabase = new HashMap<>();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PhtoVibe::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        // Configurar a aparência do sistema operacional
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // Criar a janela principal
        frame = new JFrame("PhotoVibe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Criar um painel de cartão para as páginas
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Adicionar páginas ao painel de cartão
        cardPanel.add(createLoginPage(), "Login");
        cardPanel.add(createRegistrationPage(), "Registration");
        cardPanel.add(createMainPage(), "Main");

        // Exibir a página de login por padrão
        cardLayout.show(cardPanel, "Login");

        // Adicionar o painel de cartão ao frame
        frame.getContentPane().add(cardPanel);

        // Centralizar a janela na tela
        frame.setLocationRelativeTo(null);

        // Exibir a janela
        frame.setVisible(true);
    }

    private static JPanel createLoginPage() {
        JPanel loginPanel = new JPanel(new BorderLayout(10, 10));
        loginPanel.setBorder(new EmptyBorder(50, 100, 50, 100));

        // Adicionar campos de texto para login
        JPanel textFieldPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        JLabel usernameLabel = new JLabel("Usuário:");
        usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Senha:");
        passwordField = new JPasswordField();
        textFieldPanel.add(usernameLabel);
        textFieldPanel.add(usernameField);
        textFieldPanel.add(passwordLabel);
        textFieldPanel.add(passwordField);

        // Adicionar botões de login e registro
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Registrar");
        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);

        // Adicionar ação ao botão de login
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Lógica para autenticação do usuário
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                // Verificar se o usuário existe e a senha está correta
                if (userDatabase.containsKey(username) && userDatabase.get(username).equals(password)) {
                    // Se autenticado com sucesso, mostrar a página principal
                    cardLayout.show(cardPanel, "Main");
                } else {
                    JOptionPane.showMessageDialog(frame, "Usuário ou senha incorretos!");
                }
            }
        });

        // Adicionar ação ao botão de registro
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Mostrar a página de registro
                cardLayout.show(cardPanel, "Registration");
            }
        });

        // Adicionar componentes ao painel de login
        loginPanel.add(textFieldPanel, BorderLayout.CENTER);
        loginPanel.add(buttonPanel, BorderLayout.SOUTH);

        return loginPanel;
    }

    private static JPanel createRegistrationPage() {
        JPanel registrationPanel = new JPanel(new BorderLayout(10, 10));
        registrationPanel.setBorder(new EmptyBorder(50, 100, 50, 100));

        JPanel textFieldPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        JLabel usernameLabel = new JLabel("Novo Usuário:");
        JTextField newUsernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Nova Senha:");
        JPasswordField newPasswordField = new JPasswordField();
        JLabel confirmPasswordLabel = new JLabel("Confirmar Senha:");
        JPasswordField confirmPasswordField = new JPasswordField();
        textFieldPanel.add(usernameLabel);
        textFieldPanel.add(newUsernameField);
        textFieldPanel.add(passwordLabel);
        textFieldPanel.add(newPasswordField);
        textFieldPanel.add(confirmPasswordLabel);
        textFieldPanel.add(confirmPasswordField);

        JButton registerButton = new JButton("Registrar");
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Lógica de registro do usuário
                String username = newUsernameField.getText();
                String password = new String(newPasswordField.getPassword());
                String confirmPassword = new String(confirmPasswordField.getPassword());

                // Verificar se o nome de usuário já existe
                if (userDatabase.containsKey(username)) {
                    JOptionPane.showMessageDialog(frame, "Nome de usuário já existe!");
                } else if (!password.equals(confirmPassword)) {
                    JOptionPane.showMessageDialog(frame, "As senhas não correspondem!");
                } else {
                    // Registrar o novo usuário
                    userDatabase.put(username, password);
                    JOptionPane.showMessageDialog(frame, "Registro bem-sucedido!");
                    // Mostrar a página de login após o registro
                    cardLayout.show(cardPanel, "Login");
                }
            }
        });

        registrationPanel.add(textFieldPanel, BorderLayout.CENTER);
        registrationPanel.add(registerButton, BorderLayout.SOUTH);

        return registrationPanel;
    }

    private static JPanel createMainPage() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(50, 100, 50, 100));

        // Painel com botões para acessar as funcionalidades
        JPanel buttonPanel = new JPanel(new GridLayout(6, 1, 10, 10));

        JButton homeworkButton = new JButton("Lição de Casa");
        JButton liveClassesButton = new JButton("Aulas ao Vivo");
        JButton studyCommunitiesButton = new JButton("Comunidades de Estudo");
        JButton discussionForumButton = new JButton("Fórum de Discussão");
        JButton supportButton = new JButton("Suporte");
        JButton chatButton = new JButton("Chats de Dúvidas");

        buttonPanel.add(homeworkButton);
        buttonPanel.add(liveClassesButton);
        buttonPanel.add(studyCommunitiesButton);
        buttonPanel.add(discussionForumButton);
        buttonPanel.add(supportButton);
        buttonPanel.add(chatButton);

        // Adicionar ação aos botões para trocar de página
        homeworkButton.addActionListener(e -> cardLayout.show(cardPanel, "Homework"));
        liveClassesButton.addActionListener(e -> cardLayout.show(cardPanel, "LiveClasses"));
        studyCommunitiesButton.addActionListener(e -> cardLayout.show(cardPanel, "StudyCommunities"));
        discussionForumButton.addActionListener(e -> cardLayout.show(cardPanel, "DiscussionForum"));
        supportButton.addActionListener(e -> cardLayout.show(cardPanel, "Support"));
        chatButton.addActionListener(e -> cardLayout.show(cardPanel, "Chat"));

        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        // Adicionar as páginas das funcionalidades
        cardPanel.add(createHomeworkPage(), "Homework");
        cardPanel.add(createLiveClassesPage(), "LiveClasses");
        cardPanel.add(createStudyCommunitiesPage(), "StudyCommunities");
        cardPanel.add(createDiscussionForumPage(), "DiscussionForum");
        cardPanel.add(createSupportPage(), "Support");
        cardPanel.add(createChatPage(), "Chat");

        return mainPanel;
    }

    private static JPanel createHomeworkPage() {
        JPanel homeworkPanel = new JPanel(new BorderLayout(10, 10));
        homeworkPanel.setBorder(new EmptyBorder(50, 100, 50, 100));

        JLabel label = new JLabel("Lição de Casa", JLabel.CENTER);
        homeworkPanel.add(label, BorderLayout.NORTH);

        JPanel homeworkListPanel = new JPanel(new GridLayout(4, 1, 10, 10));

        JButton mathButton = new JButton("Matemática");
        JButton portugueseButton = new JButton("Língua Portuguesa");
        JButton geographyButton = new JButton("Geografia");
        JButton historyButton = new JButton("História");

        homeworkListPanel.add(mathButton);
        homeworkListPanel.add(portugueseButton);
        homeworkListPanel.add(geographyButton);
        homeworkListPanel.add(historyButton);

        mathButton.addActionListener(e -> cardLayout.show(cardPanel, "Math"));
        portugueseButton.addActionListener(e -> cardLayout.show(cardPanel, "Portuguese"));
        geographyButton.addActionListener(e -> cardLayout.show(cardPanel, "Geography"));
        historyButton.addActionListener(e -> cardLayout.show(cardPanel, "History"));

        JButton backButton = new JButton("Voltar");
        backButton.addActionListener(e -> cardLayout.show(cardPanel, "Main"));

        homeworkPanel.add(homeworkListPanel, BorderLayout.CENTER);
        homeworkPanel.add(backButton, BorderLayout.SOUTH);

        cardPanel.add(createMathPage(), "Math");
        cardPanel.add(createPortuguesePage(), "Portuguese");
        cardPanel.add(createGeographyPage(), "Geography");
        cardPanel.add(createHistoryPage(), "History");

        return homeworkPanel;
    }

    private static JPanel createMathPage() {
        JPanel mathPanel = new JPanel(new BorderLayout(10, 10));
        mathPanel.setBorder(new EmptyBorder(50, 100, 50, 100));

        JLabel label = new JLabel("Matemática", JLabel.CENTER);
        mathPanel.add(label, BorderLayout.NORTH);

        JPanel mathListPanel = new JPanel(new GridLayout(3, 1, 10, 10));

        JButton grade1Button = new JButton("1º Ano do Ensino Médio");
        JButton grade2Button = new JButton("2º Ano do Ensino Médio");
        JButton grade3Button = new JButton("3º Ano do Ensino Médio");

        mathListPanel.add(grade1Button);
        mathListPanel.add(grade2Button);
        mathListPanel.add(grade3Button);

        mathPanel.add(mathListPanel, BorderLayout.CENTER);

        JButton backButton = new JButton("Voltar");
        backButton.addActionListener(e -> cardLayout.show(cardPanel, "Homework"));

        mathPanel.add(backButton, BorderLayout.SOUTH);

        return mathPanel;
    }

    private static JPanel createPortuguesePage() {
        JPanel portuguesePanel = new JPanel(new BorderLayout(10, 10));
        portuguesePanel.setBorder(new EmptyBorder(50, 100, 50, 100));

        JLabel label = new JLabel("Língua Portuguesa", JLabel.CENTER);
        portuguesePanel.add(label, BorderLayout.NORTH);

        JPanel portugueseListPanel = new JPanel(new GridLayout(3, 1, 10, 10));

        JButton grade1Button = new JButton("1º Ano do Ensino Médio");
        JButton grade2Button = new JButton("2º Ano do Ensino Médio");
        JButton grade3Button = new JButton("3º Ano do Ensino Médio");

        portugueseListPanel.add(grade1Button);
        portugueseListPanel.add(grade2Button);
        portugueseListPanel.add(grade3Button);

        portuguesePanel.add(portugueseListPanel, BorderLayout.CENTER);

        JButton backButton = new JButton("Voltar");
        backButton.addActionListener(e -> cardLayout.show(cardPanel, "Homework"));

        portuguesePanel.add(backButton, BorderLayout.SOUTH);

        return portuguesePanel;
    }

    private static JPanel createGeographyPage() {
        JPanel geographyPanel = new JPanel(new BorderLayout(10, 10));
        geographyPanel.setBorder(new EmptyBorder(50, 100, 50, 100));

        JLabel label = new JLabel("Geografia", JLabel.CENTER);
        geographyPanel.add(label, BorderLayout.NORTH);

        JPanel geographyListPanel = new JPanel(new GridLayout(3, 1, 10, 10));

        JButton grade1Button = new JButton("1º Ano do Ensino Médio");
        JButton grade2Button = new JButton("2º Ano do Ensino Médio");
        JButton grade3Button = new JButton("3º Ano do Ensino Médio");

        geographyListPanel.add(grade1Button);
        geographyListPanel.add(grade2Button);
        geographyListPanel.add(grade3Button);

        geographyPanel.add(geographyListPanel, BorderLayout.CENTER);

        JButton backButton = new JButton("Voltar");
        backButton.addActionListener(e -> cardLayout.show(cardPanel, "Homework"));

        geographyPanel.add(backButton, BorderLayout.SOUTH);

        return geographyPanel;
    }

    private static JPanel createHistoryPage() {
        JPanel historyPanel = new JPanel(new BorderLayout(10, 10));
        historyPanel.setBorder(new EmptyBorder(50, 100, 50, 100));

        JLabel label = new JLabel("História", JLabel.CENTER);
        historyPanel.add(label, BorderLayout.NORTH);

        JPanel historyListPanel = new JPanel(new GridLayout(3, 1, 10, 10));

        JButton grade1Button = new JButton("1º Ano do Ensino Médio");
        JButton grade2Button = new JButton("2º Ano do Ensino Médio");
        JButton grade3Button = new JButton("3º Ano do Ensino Médio");

        historyListPanel.add(grade1Button);
        historyListPanel.add(grade2Button);
        historyListPanel.add(grade3Button);

        historyPanel.add(historyListPanel, BorderLayout.CENTER);

        JButton backButton = new JButton("Voltar");
        backButton.addActionListener(e -> cardLayout.show(cardPanel, "Homework"));

        historyPanel.add(backButton, BorderLayout.SOUTH);

        return historyPanel;
    }

    private static JPanel createLiveClassesPage() {
        JPanel liveClassesPanel = new JPanel(new BorderLayout(10, 10));
        liveClassesPanel.setBorder(new EmptyBorder(50, 100, 50, 100));

        liveClassesPanel.add(new JLabel("Página de Aulas ao Vivo", JLabel.CENTER), BorderLayout.CENTER);

        JButton backButton = new JButton("Voltar");
        backButton.addActionListener(e -> cardLayout.show(cardPanel, "Main"));

        liveClassesPanel.add(backButton, BorderLayout.SOUTH);

        return liveClassesPanel;
    }

    private static JPanel createStudyCommunitiesPage() {
        JPanel studyCommunitiesPanel = new JPanel(new BorderLayout(10, 10));
        studyCommunitiesPanel.setBorder(new EmptyBorder(50, 100, 50, 100));

        studyCommunitiesPanel.add(new JLabel("Página de Comunidades de Estudo", JLabel.CENTER), BorderLayout.CENTER);

        JButton backButton = new JButton("Voltar");
        backButton.addActionListener(e -> cardLayout.show(cardPanel, "Main"));

        studyCommunitiesPanel.add(backButton, BorderLayout.SOUTH);

        return studyCommunitiesPanel;
    }

    private static JPanel createDiscussionForumPage() {
        JPanel discussionForumPanel = new JPanel(new BorderLayout(10, 10));
        discussionForumPanel.setBorder(new EmptyBorder(50, 100, 50, 100));

        discussionForumPanel.add(new JLabel("Página de Fórum de Discussão", JLabel.CENTER), BorderLayout.CENTER);

        JButton backButton = new JButton("Voltar");
        backButton.addActionListener(e -> cardLayout.show(cardPanel, "Main"));

        discussionForumPanel.add(backButton, BorderLayout.SOUTH);

        return discussionForumPanel;
    }

    private static JPanel createSupportPage() {
        JPanel supportPanel = new JPanel(new BorderLayout(10, 10));
        supportPanel.setBorder(new EmptyBorder(50, 100, 50, 100));

        supportPanel.add(new JLabel("Página de Suporte", JLabel.CENTER), BorderLayout.CENTER);

        JButton backButton = new JButton("Voltar");
        backButton.addActionListener(e -> cardLayout.show(cardPanel, "Main"));

        supportPanel.add(backButton, BorderLayout.SOUTH);

        return supportPanel;
    }

    private static JPanel createChatPage() {
        JPanel chatPanel = new JPanel(new BorderLayout(10, 10));
        chatPanel.setBorder(new EmptyBorder(50, 100, 50, 100));

        chatPanel.add(new JLabel("Página de Chats de Dúvidas", JLabel.CENTER), BorderLayout.CENTER);

        JButton backButton = new JButton("Voltar");
        backButton.addActionListener(e -> cardLayout.show(cardPanel, "Main"));

        chatPanel.add(backButton, BorderLayout.SOUTH);

        return chatPanel;
    }
}
