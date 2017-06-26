package AdvancedReplacement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import Communication.Communication;

public class SaveBtnListener implements ActionListener {

	GUIadvancedRepalcementPanel guiAdvancedRepalcementPanel = GUIadvancedRepalcementPanel.getInstance();

	static ArrayList<File> selectedFile = new ArrayList<File>();

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		// TODO Auto-generated method stub

		if (actionEvent.getSource() == guiAdvancedRepalcementPanel.getSaveBtn()) {
			// save 버튼

			// 저장해도 되는지 여부에 따라 결정됨.
			if (AdvancedReplacementOperation.getInstance().validityCheck()) {

				// 저장 수행
				AdvancedReplacementOperation.getInstance().saveRMAdetailToServer();

				for (int i = 0; i < selectedFile.size(); i++) {
					Communication.getInstance().saveAttachFile(guiAdvancedRepalcementPanel.getTxtRMAnumber().getText(),
							selectedFile.get(i));
				}

				// 저장되면 새로운 rma number를 할당받아야함.
				String rmaNumber = Communication.getInstance().getRMAnumberFromServer();
				AdvancedReplacementOperation.getInstance().setRMAnumber(rmaNumber);

				// 이전 RMA 히스토리 갱신.
				Communication.getInstance().showPreviousRMAList(
						guiAdvancedRepalcementPanel.getTxtCompanyName().getSelectedItem().toString(),
						guiAdvancedRepalcementPanel.getTxtSiteName().getSelectedItem().toString());

				// RMA detail 초기화
				guiAdvancedRepalcementPanel.clearRMADetail();
			}

		} else if (actionEvent.getSource() == guiAdvancedRepalcementPanel.getAttachFileBtn()) {

			// 파일 첨부 버튼
			JFileChooser jFileChooser = new JFileChooser();
			int returnVal = jFileChooser.showOpenDialog(null);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				System.out.println("You chose to open this file: " + jFileChooser.getSelectedFile().getName());
				selectedFile.add(jFileChooser.getSelectedFile());

				guiAdvancedRepalcementPanel.setAttachFileList(jFileChooser.getSelectedFile().getName());

			}
		}

	}

}
