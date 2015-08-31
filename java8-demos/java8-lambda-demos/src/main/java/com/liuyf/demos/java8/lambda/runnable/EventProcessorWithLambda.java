package com.liuyf.demos.java8.lambda.runnable;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class EventProcessorWithLambda extends JFrame {

	private static final long serialVersionUID = 138718147893866786L;

	public EventProcessorWithLambda() throws HeadlessException {
		
		super("EventProcessorWithLambda");
		this.setLayout(new FlowLayout());
		
		// Prior to Java 8
		JButton showOne = new JButton("ShowOne");
		showOne.setPreferredSize(new Dimension(200, 50));
		showOne.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Event handling without lambda expression is boring");
			}
		});
		this.add(showOne);
		
		// Java 8
		JButton showTwo = new JButton("ShowTwo");
		showTwo.setPreferredSize(new Dimension(200, 50));
		showTwo.addActionListener((e) -> {
			System.out.println("Light, Camera, Action ! Lambda expression rocks");
		});
		this.add(showTwo);
		
		this.pack();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		JFrame frame = new EventProcessorWithLambda();
		frame.setVisible(true);
	}
}
