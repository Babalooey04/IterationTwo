import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;

public class displayRight extends JPanel{
	JPanel bottomrightPanel;
    JPanel toprightPanel;

	public displayRight() {

		bottomrightPanel = new JPanel();
        toprightPanel = new JPanel();

        setLayout(new BorderLayout() );
        setBackground(Color.WHITE);

        toprightPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        toprightPanel.setBorder(BorderFactory.createTitledBorder("Recipe Description"));
        toprightPanel.setBackground(Color.WHITE);
        bottomrightPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        bottomrightPanel.setBorder(BorderFactory.createTitledBorder("Recipe Ingredients"));
        bottomrightPanel.setBackground(Color.WHITE);
        bottomrightPanel.setPreferredSize(new Dimension(0,245));

        add(bottomrightPanel, BorderLayout.SOUTH);
        add(toprightPanel, BorderLayout.NORTH);

	}

	public void addRecipes(JList description, JList ingredients) {
		toprightPanel.add(description);
        bottomrightPanel.add(ingredients);
	}
}
