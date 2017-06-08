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
			// save 버튼

			// 저장해도 되는지 여부에 따라 결정됨.
			if (AdvancedReplacementOperation.getInstance().validityCheck()) {

				// 저장 수행
				AdvancedReplacementOperation.getInstance().saveRMAdetailToServer();
				Communication.getInstance().saveAttachFile(guiAdvancedRepalcementPanel.getTxtRMAnumber().getText(),
						selectedFile);

				// 저장되면 새로운 rma number를 할당받아야함.
				String rmaNumber = Communication.getInstance().getRMAnumberFromServer();
				AdvancedReplacementOperation.getInstance().setRMAnumber(rmaNumber);

				// 이전 RMA 히스토리 갱신.
				Communication.getInstance().showPreviousRMAList(
						guiAdvancedRepalcementPanel.getTxtCompanyName().getSelectedItem().toString());

				// RMA detail 초기화
				guiAdvancedRepalcementPanel.clearRMADetail();
			}

		} else if (actionEvent.getSource() == guiAdvancedRepalcementPanel.getAttachFileBtn()) {

			// 파일 첨부 버튼
			JFileChooser jFileChooser = new JFileChooser();
			int returnVal = jFileChooser.showOpenDialog(null);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				System.out.println("You chose to open this file: " + jFileChooser.getSelectedFile().getName());
				selectedFile = jFileChooser.getSelectedFile();

			}
		}

	}

}
