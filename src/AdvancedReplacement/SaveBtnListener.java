package AdvancedReplacement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import AdvancedReplacement.function.Function;
import Communication.Communication;

public class SaveBtnListener implements ActionListener {

	GUIadvancedRepalcementPanel guiAdvancedRepalcementPanel = GUIadvancedRepalcementPanel.getInstance();

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		// TODO Auto-generated method stub

		if (actionEvent.getSource() == guiAdvancedRepalcementPanel.getSaveBtn()) {
			// save 버튼

			// 저장 수행
			Function.getInstance().saveRMADetailDataToServer();

		} else if (actionEvent.getSource() == guiAdvancedRepalcementPanel.getAttachFileBtn()) {

			// 파일 첨부 버튼
			JFileChooser jFileChooser = new JFileChooser();
			int returnVal = jFileChooser.showOpenDialog(null);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				System.out.println("You chose to open this file: " + jFileChooser.getSelectedFile().getName());
				Function.getInstance().selectedFile.add(jFileChooser.getSelectedFile());

				guiAdvancedRepalcementPanel.setAttachFileList(jFileChooser.getSelectedFile().getName());

			}
		}

	}

}
