package AdvancedReplacement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import javax.swing.JFileChooser;

import Communication.Communication;
import Communication.ConnectionSocket;

public class SaveBtnListener implements ActionListener {

	GUIadvancedRepalcementPanel guiAdvancedRepalcementPanel = GUIadvancedRepalcementPanel.getInstance();

	static File selectedFile;

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		// TODO Auto-generated method stub

		if (actionEvent.getSource() == guiAdvancedRepalcementPanel.getSaveBtn()) {
			// save ��ư

			// �����ص� �Ǵ��� ���ο� ���� ������.
			if (AdvancedReplacementOperation.getInstance().validityCheck()) {

				// ���� ����
				AdvancedReplacementOperation.getInstance().saveRMAdetailToServer();
				Communication.getInstance().saveAttachFile(guiAdvancedRepalcementPanel.getTxtRMAnumber().getText(),
						selectedFile);

				// ����Ǹ� ���ο� rma number�� �Ҵ�޾ƾ���.
				String rmaNumber = Communication.getInstance().getRMAnumberFromServer();
				AdvancedReplacementOperation.getInstance().setRMAnumber(rmaNumber);

				// ���� RMA �����丮 ����.
				Communication.getInstance().showPreviousRMAList(
						guiAdvancedRepalcementPanel.getTxtCompanyName().getSelectedItem().toString());

				// RMA detail �ʱ�ȭ
				guiAdvancedRepalcementPanel.clearRMADetail();
			}

		} else if (actionEvent.getSource() == guiAdvancedRepalcementPanel.getAttachFileBtn()) {

			// ���� ÷�� ��ư
			JFileChooser jFileChooser = new JFileChooser();
			int returnVal = jFileChooser.showOpenDialog(null);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				System.out.println("You chose to open this file: " + jFileChooser.getSelectedFile().getName());
				selectedFile = jFileChooser.getSelectedFile();

			}
		}

	}

}
