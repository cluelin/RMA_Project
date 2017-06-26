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
			// save ��ư

			// �����ص� �Ǵ��� ���ο� ���� ������.
			if (AdvancedReplacementOperation.getInstance().validityCheck()) {

				// ���� ����
				AdvancedReplacementOperation.getInstance().saveRMAdetailToServer();

				for (int i = 0; i < selectedFile.size(); i++) {
					Communication.getInstance().saveAttachFile(guiAdvancedRepalcementPanel.getTxtRMAnumber().getText(),
							selectedFile.get(i));
				}

				// ����Ǹ� ���ο� rma number�� �Ҵ�޾ƾ���.
				String rmaNumber = Communication.getInstance().getRMAnumberFromServer();
				AdvancedReplacementOperation.getInstance().setRMAnumber(rmaNumber);

				// ���� RMA �����丮 ����.
				Communication.getInstance().showPreviousRMAList(
						guiAdvancedRepalcementPanel.getTxtCompanyName().getSelectedItem().toString(),
						guiAdvancedRepalcementPanel.getTxtSiteName().getSelectedItem().toString());

				// RMA detail �ʱ�ȭ
				guiAdvancedRepalcementPanel.clearRMADetail();
			}

		} else if (actionEvent.getSource() == guiAdvancedRepalcementPanel.getAttachFileBtn()) {

			// ���� ÷�� ��ư
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
