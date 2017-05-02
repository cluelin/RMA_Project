package AdvancedReplacement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

import javax.swing.JFileChooser;

import Communication.Communication;

public class SaveBtnListener implements ActionListener {

	GUIadvancedRepalcementPanel guiAdvancedRepalcementPanel = GUIadvancedRepalcementPanel.getInstance();

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		// TODO Auto-generated method stub

		if (actionEvent.getSource() == guiAdvancedRepalcementPanel.getSaveBtn()) {
			// save 버튼

			//저장해도 되는지 여부에 따라 결정됨. 
			if (AdvancedReplacementOperation.getInstance().validityCheck()) {
				
				//저장 수행
				AdvancedReplacementOperation.getInstance().saveRMAdetailToServer();
				
				//저장되면 새로운 rma number를 할당받아야함. 
				String rmaNumber = Communication.getInstance().getRMAnumberFromServer();
				AdvancedReplacementOperation.getInstance().setRMAnumber(rmaNumber);
			}

		} else if (actionEvent.getSource() == guiAdvancedRepalcementPanel.getAttachFileBtn()) {

			// 파일 첨부 버튼0
			JFileChooser jFileChooser = new JFileChooser();
			int returnVal = jFileChooser.showOpenDialog(null);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				System.out.println("You chose to open this file: " + jFileChooser.getSelectedFile().getName());
				File selectedFile = jFileChooser.getSelectedFile();
				// selectedFile.renameTo(new File("copiedFIle." +
				// getFileExtension(selectedFile)));
				copyFile(selectedFile);

			}
		}

	}

	// Copy selected File to Default Location With Same File name.
	private void copyFile(File orignalFile) {

		try {
			FileInputStream fileInputStream = new FileInputStream(orignalFile);
			FileOutputStream fileOutputStream = new FileOutputStream(new File(orignalFile.getName()));

			// using FileChannel improve performance
			FileChannel fileChannelIn = fileInputStream.getChannel();
			FileChannel fileChannelOut = fileOutputStream.getChannel();

			long size = fileChannelIn.size();
			fileChannelIn.transferTo(0, size, fileChannelOut);

			fileChannelOut.close();
			fileChannelIn.close();

			fileOutputStream.close();
			fileInputStream.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
