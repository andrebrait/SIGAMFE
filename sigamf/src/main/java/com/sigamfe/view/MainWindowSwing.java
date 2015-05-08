package com.sigamfe.view;

import java.awt.EventQueue;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Component;

import com.sigamfe.configuration.ApplicationConfiguration;
import com.sigamfe.configuration.PersistenceConfiguration;
import com.sigamfe.model.Usuario;
import com.sigamfe.model.enums.IndicadorSN;
import com.sigamfe.model.enums.PermissaoUsuario;
import com.sigamfe.repository.UsuarioRepository;

@Component
public class MainWindowSwing {

	private JFrame login;

	@Autowired
	private UsuarioRepository usuarioRepository;

	private static SpringApplication app;
	private JTextField usernameField;
	private JTextField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		PersistenceConfiguration.setDB_DATABASE("sigamfe");
		PersistenceConfiguration.setDB_HOSTNAME("localhost");
		PersistenceConfiguration.setDB_PORT("52000");

		app = new SpringApplication(ApplicationConfiguration.class);
		app.setWebEnvironment(false);
		app.setShowBanner(false);
		app.setHeadless(false);

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					final MainWindowSwing window = new MainWindowSwing();
					window.login.setVisible(true);
				} catch (final Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	/**
	 * Create the application.
	 */
	@Autowired
	public MainWindowSwing() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		login = new JFrame();
		login.setType(Type.UTILITY);
		login.setTitle("SIGAMFE - Entrar");
		login.setResizable(false);
		login.setAlwaysOnTop(true);
		login.setBounds(100, 100, 347, 177);
		login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		final JLabel usernameLabel = new JLabel("Nome de usuário");

		usernameField = new JTextField();
		usernameField.setToolTipText("Nome do usuário para entrar no sistema");
		usernameField.setColumns(10);
		usernameLabel.setLabelFor(usernameField);

		final JLabel passwordLabel = new JLabel("Senha");
		passwordLabel.setToolTipText("");
		passwordLabel.setLabelFor(passwordField);

		passwordField = new JTextField();
		passwordField.setToolTipText("Senha do usuário");
		passwordField.setColumns(10);

		final JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				app.run();
				JOptionPane.showMessageDialog(login, "Você entrou!", "Login com sucesso", JOptionPane.INFORMATION_MESSAGE);
				final Usuario user = new Usuario();
				user.setCpf(1479524689L);
				user.setLogin("andrebrait");
				user.setSenha("pitchula");
				user.setPermissao(PermissaoUsuario.ADMINISTRADOR);
				user.setAtivo(IndicadorSN.SIM);
				usuarioRepository.save(user);
			}
		});

		final JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				login.dispose();
			}
		});
		final GroupLayout groupLayout = new GroupLayout(login.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout
				.createParallelGroup(Alignment.TRAILING)
				.addGroup(
						groupLayout
								.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										groupLayout.createParallelGroup(Alignment.TRAILING)
												.addComponent(usernameLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(passwordLabel))
								.addGap(18)
								.addGroup(
										groupLayout.createParallelGroup(Alignment.LEADING, false).addComponent(usernameField)
												.addComponent(passwordField, 226, 226, Short.MAX_VALUE)).addGap(7))
										.addGroup(
						groupLayout.createSequentialGroup().addGap(80)
								.addComponent(btnEntrar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addGap(49)
								.addComponent(btnCancelar, GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE).addGap(74)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(
						groupLayout
								.createSequentialGroup()
								.addGap(41)
								.addGroup(
										groupLayout
												.createParallelGroup(Alignment.BASELINE)
												.addComponent(usernameLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(usernameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE))
								.addGap(18)
								.addGroup(
										groupLayout
												.createParallelGroup(Alignment.BASELINE)
												.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(passwordLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addGap(18)
								.addGroup(
										groupLayout.createParallelGroup(Alignment.BASELINE)
												.addComponent(btnEntrar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(btnCancelar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addGap(110)));
		login.getContentPane().setLayout(groupLayout);
	}
}
