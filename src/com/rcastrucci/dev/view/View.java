package com.rcastrucci.dev.view;

import javax.swing.JOptionPane;

/**
 * Classe para uso em teste, interação do usuário com as classes.
 * Esta classe utiliza a classe JOptionPane para exibir mensagens, avisos e requisitar informações.
 * 
 * @author 1TDSOS-2021 Grupo F - FIAP
 * @author Renne Castrucci
 * @author Rita de Cassia Fontenele Oliveira
 * @author Gabriel Sever Carvalho
 * @author Guilherme Gonçalves Pires
 * @author Weslley Pablo Araujo Silva
 * 
 * @version 1.0
 */
public class View {
	
	/**
	 * Exibe uma mensagem na tela e apresenta os botões de escolha Yes ou No e retorna a resposta em boolean
	 * @param msg é uma String com o conteúdo da mensagem a ser exibida
	 * @return boolean true se o usuário pressionar o botão Yes ou false se pressionar o botão No
	 * @see mensagem input
	 */
	public static boolean option(String msg) {
		int op = JOptionPane.showConfirmDialog(null, msg, "Confirmação", JOptionPane.YES_NO_OPTION);
		return (op == JOptionPane.YES_OPTION);
	}
	
	/**
	 * Exibe uma mensagem na tela e apresenta os botões de escolha Yes ou No e retorna a resposta em boolean
	 * @param title é um parametro opcional que é o título da janela, o valor pré-definido é "Confirmação"
	 * @param msg é uma String com o conteúdo da mensagem a ser exibida
	 * @return boolean true se o usuário pressionar o botão Yes ou false se pressionar o botão No
	 * @see mensagem input
	 */
	public static boolean option(String title, String msg) {
		int op = JOptionPane.showConfirmDialog(null, msg, title, JOptionPane.YES_NO_OPTION);
		return (op == JOptionPane.YES_OPTION);
	}
	
	/**
	 * Exibe uma mensagem na tela para o usuário
	 * @param msg é uma String com o conteúdo da mensagem a ser exibida
	 * @see input option
	 */
	public static void mensagem(String msg) {
		JOptionPane.showMessageDialog(null, msg, "Mensagem", 1);
	}
	
	/**
	 * Exibe uma mensagem na tela para o usuário e imprime a mesma no console
	 * @param msg é uma String com o conteúdo da mensagem a ser exibida
	 * @param console é im parâmentro do tipo boolean que true exibirá a mensagem no console
	 * @see input option
	 */
	public static void mensagem(String msg, boolean console) {
		JOptionPane.showMessageDialog(null, msg, "Mensagem", 1);
		if (console) System.out.println(msg);
	}
	
	/**
	 * Exibe uma mensagem na tela para o usuário
	 * @param title é um parametro opcional que é o título da janela, o valor pré-definido é "Mensagem"
	 * @param msg é uma String com o conteúdo da mensagem a ser exibida
	 * @see input option
	 */
	public static void mensagem(String title, String msg) {
		JOptionPane.showMessageDialog(null, msg, title, 1);
	}
	
	/**
	 * Exibe uma mensagem requisitando um input do usuário retorna o valor em String
	 * @param msg é uma String com o conteúdo da mensagem a ser exibida
	 * @return String um valor digitado pelo usuário
	 * @see mensagem option
	 */
	public static String input(String msg) {
		return JOptionPane.showInputDialog(msg);
	}
	
	/**
	 * Exibe uma mensagem requisitando um input do usuário com um input já inserido e retorna o valor em String
	 * @param msg é uma String com o conteúdo da mensagem a ser exibida
	 * @return String um valor digitado pelo usuário
	 * @see mensagem option
	 */
	public static String input(String msg, String field) {
		return JOptionPane.showInputDialog(msg, field);
	}
		
	/**
	 * Exibe uma janela de multipla escolha e retorna um objeto até que o usuário confirme a escolha
	 * @param title título da janela
	 * @param msg mensagem ao usuário
	 * @param obj uma lista de opções
	 * @param standardOption é a opção inicial
	 * @return object escolha feita pelo usuário
	 * @see option input
	 */
	public static Object optionList(String title, String msg, Object[] obj, String standardOption) {
		Object answer;
		do {
			answer = JOptionPane.showInputDialog(null, msg, title, JOptionPane.QUESTION_MESSAGE, null, obj, standardOption);
			if (answer == null) {
				System.out.println("É necessário escolher uma opção para prosseguir");
			}
		} while (answer == null);
		return answer;
	}
}
