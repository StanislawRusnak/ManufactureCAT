package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import javafx.collections.ObservableList;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Transform;
import javafx.scene.transform.Translate;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import main.Main;
import procedure.Drill;
import procedure.Grind;
import procedure.Lathe;
import procedure.Other;
import procedure.Procedure;
import procedure.ProcessInfo;

public class ExportImport {
	private MainController main;
	ObservableList<Procedure> prList;

	public void exportExcel() {
		prList = Main.mainController.collection.getProcedureList();
		Workbook workbook = new HSSFWorkbook();
		Sheet spreadsheet = workbook.createSheet("Podsumowanie zabiegów");
		Row row;
		int last;
		Procedure tmp;
		CellStyle style = workbook.createCellStyle(); // tworzenie stylu paskow oddzielajacych
		style.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
		style.setFillPattern(FillPatternType.ALT_BARS);

		Row prInfoRow = spreadsheet.createRow(0); // processInfoRow i wype³nianie go
		ProcessInfo prInfo = main.processInfo;
		prInfoRow.createCell(0).setCellValue("Nazwa czêœci: " + prInfo.getPartName());
		prInfoRow.createCell(1).setCellValue("Seria [szt]: " + prInfo.getPartQuantity());
		prInfoRow.createCell(2).setCellValue("Czas przygotow-zakoñcz. [min]: " + prInfo.getPreparingTime());
		prInfoRow.createCell(3).setCellValue("Utworzony przez: " + prInfo.getOperatorName());
		prInfoRow.createCell(4).setCellValue("Data i godz dodania: " + prInfo.getDate());
		prInfoRow.createCell(5).setCellValue("Pó³fabrykat (œr x d³ [mm]): " + prInfo.getHalfProduct());
		prInfoRow.createCell(6).setCellValue("Koszt pó³fabrykatu [z³/szt]: " + prInfo.getHalfProductCost());

		spreadsheet.createRow(1).setRowStyle(style); // pasek oddzielaj¹cy ze stylem

		for (int i = 0; i < prList.size(); i++) { // wype³nianie tabeli list¹ zabiegow
			row = spreadsheet.createRow(i + 3);
			tmp = prList.get(i);

			if (tmp instanceof Lathe) {
				Lathe lathe = (Lathe) tmp;
				row.createCell(0).setCellValue("Typ: " + lathe.getType());
				row.createCell(1).setCellValue("Obrabiarka: " + lathe.getMachine());
				row.createCell(2).setCellValue("Koszt zabiegu [z³/h]: " + lathe.getCostPerHour());
				row.createCell(3).setCellValue("Œrednica pocz¹tkowa [mm]: " + lathe.getDiameterBefore());
				row.createCell(4).setCellValue("Œrednica koñcowa [mm]: " + lathe.getDiameterAfter());
				row.createCell(5).setCellValue("D³ugoœæ toczenia [mm]: " + lathe.getLatheLength());
				row.createCell(6).setCellValue("Droga ja³owa [mm]: " + lathe.getIdleTrack());
				row.createCell(7).setCellValue("Posuw [mm/obr]: " + lathe.getFeed());
				row.createCell(8).setCellValue("G³êbokoœæ skrawania [mm]: " + lathe.getDepthOfCut());
				row.createCell(9).setCellValue("Prêdkoœæ obr wrzeciona [mm/obr]: " + lathe.getRpm());
				row.createCell(10).setCellValue("Czas pomocniczy [min]: " + lathe.getAdditionalTime());

			} else if (tmp instanceof Drill) {
				Drill drill = (Drill) tmp;
				row.createCell(0).setCellValue("Typ: " + drill.getType());
				row.createCell(1).setCellValue("Obrabiarka: " + drill.getMachine());
				row.createCell(2).setCellValue("Koszt zabiegu [z³/h]: " + drill.getCostPerHour());
				row.createCell(3).setCellValue("Œrednica otworu [mm]: " + drill.getDiameterAfter());
				row.createCell(4).setCellValue("D³ugoœæ otworu [mm]: " + drill.getDrillLength());
				row.createCell(5).setCellValue("Droga ja³owa [mm]: " + drill.getIdlePath());
				row.createCell(6).setCellValue("Posuw [mm/obr]: " + drill.getFeed());
				row.createCell(7).setCellValue("Prêdkoœæ obr wrzeciona [mm/obr]: " + drill.getRpm());
				row.createCell(8).setCellValue("Czas pomocniczy [min]: " + drill.getAdditionalTime());
			} else if (tmp instanceof Grind) {
				Grind grind = (Grind) tmp;
				row.createCell(0).setCellValue("Typ: " + grind.getType());
				row.createCell(1).setCellValue("Obrabiarka: " + grind.getMachine());
				row.createCell(2).setCellValue("Koszt zabiegu [z³/h]: " + grind.getCostPerHour());
				row.createCell(3).setCellValue("Posuw [mm/obr]: " + grind.getFeed());
				row.createCell(4).setCellValue("Prêdkoœæ obr wrzeciona [mm/obr]: " + grind.getRpm());
				row.createCell(5).setCellValue("Naddatek na obróbkê [mm]: " + grind.getSurplus());
				row.createCell(6).setCellValue("D³ugoœæ obróbkowa [mm]: " + grind.getGrindLength());
				row.createCell(7).setCellValue("Szerokoœæ œciernicy [mm]: " + grind.getGrindWidth());
				row.createCell(8).setCellValue("Liczba przejœæ: " + grind.getGrindReps());
				row.createCell(9).setCellValue("Czas pomocniczy [min]: " + grind.getAdditionalTime());

			} else if (tmp instanceof Other) {
				Other other = (Other) tmp;
				row.createCell(0).setCellValue("Typ: " + other.getType());
				row.createCell(1).setCellValue("Obrabiarka: " + other.getMachine());
				row.createCell(2).setCellValue("Koszt zabiegu [z³/h]: " + other.getCostPerHour());
				row.createCell(3).setCellValue("Czas g³ówny zabiegu [min]: " + other.getMainTime());
				row.createCell(4).setCellValue("Czas pomocniczy [min]: " + other.getAdditionalTime());
				row.createCell(5).setCellValue("Inne parametry: " + other.getOtherParameters());

			} else if (tmp instanceof Procedure) {
				row.createCell(0).setCellValue("Typ: " + tmp.getType());
				row.createCell(1).setCellValue("Czas: " + tmp.getTime());
				row.createCell(2).setCellValue("Koszt: " + tmp.getCost());
			}
		}
		last = prList.size() + 4;
		spreadsheet.createRow(last).setRowStyle(style);// tworzenie paska oddzielajacego od podsumowania
		spreadsheet.createRow(last - 1).createCell(0); // tworzenie pustej komórki pod zabiegami w dla metody
														// wczytujacej
		spreadsheet.createRow(last).createCell(0); // tworzenie pustej komórki pod zabiegami w dla metody wczytujacej
													// (bez niej >nullpointer

		Row sumRow = spreadsheet.createRow(last + 1); // wiersz sumy czasów i kosztów obliczone
		sumRow.createCell(0).setCellValue("Czas sumaryczny 1szt [min]: " + main.timeSumField.getText());
		sumRow.createCell(1).setCellValue("Koszt sumaryczny 1szt [z³]: " + main.costSumField.getText());
		sumRow.createCell(2).setCellValue("Czas wykonania serii [min]: " + main.seriesTimeField.getText());
		sumRow.createCell(3).setCellValue("Koszt wykonania serii [z³]: " + main.seriesCostField.getText());

		FileOutputStream fileOut;
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Microsoft Excel (.xls)", "*.xls"));
		fileChooser.setTitle("Zapisz plik");
		fileChooser.setInitialFileName("Podaj nazwê pliku");
		File savedFile = fileChooser.showSaveDialog(new Stage());

		if (savedFile != null) {
			try {
				fileOut = new FileOutputStream(savedFile);
				workbook.write(fileOut);
				fileOut.flush();
				fileOut.close();
				workbook.close();
			} catch (IOException e) {
				System.out.println("Exception appear during xls creating");
				e.printStackTrace();
			}
		}
	}

	public void importExcel() {
		prList = Main.mainController.collection.getProcedureList();
		FileChooser fc = new FileChooser();
		fc.getExtensionFilters().add(new ExtensionFilter("Microsoft Excel (.xls)", "*.xls"));
		File file = fc.showOpenDialog(new Stage());
		prList.clear();
		xlsParser(file); // wczytywanie zabiegów do listy
	}

	public void xlsParser(File xlsFile) {
		try (InputStream input = new FileInputStream(xlsFile)) {
			// wczytywanie processInfo oraz pó³fabrykatu
			Workbook workbook = WorkbookFactory.create(input);
			Sheet prCosting = workbook.getSheetAt(0);
			Row row = prCosting.getRow(0); // pobranie 1 wiersza z pliku excel
			ProcessInfo prInfo = main.processInfo; // przypisanie do zmiennej referencji obiektu processInfo
			Procedure proc = main.halfProduct;
			prInfo.setPartName(trimStr(row.getCell(0).getStringCellValue())); // wype³nianie poszczególnych pól
			prInfo.setPartQuantity(trimInt(row.getCell(1).getStringCellValue()));
			prInfo.setPreparingTime(trimDbl(row.getCell(2).getStringCellValue()));
			prInfo.setOperatorName(trimStr(row.getCell(3).getStringCellValue()));
			prInfo.setDate(trimDate(row.getCell(4).getStringCellValue()));
			prInfo.setHalfProduct(trimStr(row.getCell(5).getStringCellValue()));
			prInfo.setHalfProductCost(trimDbl(row.getCell(6).getStringCellValue()));
			proc.setParameters("Prêt okr¹g³y " + prInfo.getHalfProduct() + " [mm]");
			proc.setCost(prInfo.getHalfProductCost());
			main.collection.addProcedure(proc); // dodanie pó³fabrykatu do listy
			main.getProcessInfo().setText(prInfo.toString()); // wpisanie do pola processInfo informacji

			// wczytywanie listy zabiegów do kolekcji
			int rowNum = 4; // wskazanie wiersza z pierwszym zabiegiem (pó³fabrykat to nie zabieg i by³
							// dodany wczesniej)
			String prType;
			while (!prCosting.getRow(rowNum).getCell(0).getStringCellValue().isEmpty()) {
				row = prCosting.getRow(rowNum);
				prType = trimStr(row.getCell(0).getStringCellValue());

				if (prType.equals(LatheAddPaneController.OUTER_LATHE)
						|| prType.equals(LatheAddPaneController.INNER_LATHE)) { // jesli w wierszu jest toczenie
																				// zewnêtrzne lub wewnêtrzne
					proc = new Lathe(trimDbl(row.getCell(3).getStringCellValue()),
							trimDbl(row.getCell(4).getStringCellValue()), trimDbl(row.getCell(5).getStringCellValue()),
							trimDbl(row.getCell(7).getStringCellValue()), trimDbl(row.getCell(8).getStringCellValue()),
							trimDbl(row.getCell(6).getStringCellValue()), trimDbl(row.getCell(9).getStringCellValue()),
							trimDbl(row.getCell(2).getStringCellValue()), trimStr(row.getCell(0).getStringCellValue()),
							trimStr(row.getCell(1).getStringCellValue()),
							trimDbl(row.getCell(10).getStringCellValue()));
					main.collection.addProcedure(proc);

				} else if (prType.equals(LatheAddPaneController.TRANSVE_LATHE)) { // toczenie poprzeczne
					proc = new Lathe(trimDbl(row.getCell(3).getStringCellValue()),
							trimDbl(row.getCell(5).getStringCellValue()), trimDbl(row.getCell(7).getStringCellValue()),
							trimDbl(row.getCell(8).getStringCellValue()), trimDbl(row.getCell(6).getStringCellValue()),
							trimDbl(row.getCell(9).getStringCellValue()), trimDbl(row.getCell(2).getStringCellValue()),
							LatheAddPaneController.TRANSVE_LATHE, trimStr(row.getCell(1).getStringCellValue()),
							trimDbl(row.getCell(10).getStringCellValue()));
					main.collection.addProcedure(proc);
				} else if (prType.equals(DrillAddPaneController.CLOSE_DRILLING)
						|| prType.equals(DrillAddPaneController.OPEN_DRILLING)
						|| prType.equals(DrillAddPaneController.HOLE_DRILLING)) { // wiercenie nieprzelotowe, przelotowe
																					// lub powiercanie
					proc = new Drill(trimDbl(row.getCell(3).getStringCellValue()),
							trimDbl(row.getCell(4).getStringCellValue()), trimDbl(row.getCell(6).getStringCellValue()),
							trimDbl(row.getCell(5).getStringCellValue()), trimDbl(row.getCell(7).getStringCellValue()),
							trimDbl(row.getCell(2).getStringCellValue()), trimStr(row.getCell(0).getStringCellValue()),
							trimStr(row.getCell(1).getStringCellValue()), trimDbl(row.getCell(8).getStringCellValue()));
					main.collection.addProcedure(proc);

				} else if (prType.equals(GrindAddPaneController.LONGIT_GRIND)) { // szlifowanie wzd³u¿ne
					proc = new Grind(trimDbl(row.getCell(4).getStringCellValue()),
							trimDbl(row.getCell(3).getStringCellValue()), trimDbl(row.getCell(6).getStringCellValue()),
							trimDbl(row.getCell(7).getStringCellValue()), trimInt(row.getCell(8).getStringCellValue()),
							trimDbl(row.getCell(2).getStringCellValue()), GrindAddPaneController.LONGIT_GRIND,
							trimStr(row.getCell(1).getStringCellValue()), trimDbl(row.getCell(9).getStringCellValue()));
					main.collection.addProcedure(proc);
				} else if (prType.equals(GrindAddPaneController.TRANSVE_GRIND)) { // Szlifowanie wg³êbne
					proc = new Grind(trimDbl(row.getCell(5).getStringCellValue()),
							trimDbl(row.getCell(4).getStringCellValue()), trimDbl(row.getCell(3).getStringCellValue()),
							trimDbl(row.getCell(2).getStringCellValue()), GrindAddPaneController.TRANSVE_GRIND,
							trimStr(row.getCell(1).getStringCellValue()), trimDbl(row.getCell(9).getStringCellValue()));
					main.collection.addProcedure(proc);
				} else { // w innym wypadku jest to zabieg typu other (inny)
					proc = new Other(trimDbl(row.getCell(2).getStringCellValue()),
							trimStr(row.getCell(0).getStringCellValue()), trimStr(row.getCell(1).getStringCellValue()),
							trimDbl(row.getCell(4).getStringCellValue()), trimDbl(row.getCell(3).getStringCellValue()),
							trimStr(row.getCell(5).getStringCellValue()));
					main.collection.addProcedure(proc);
				}
				rowNum++;
			}

		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private String trimStr(String str) { // throw sub string
		String[] subStr = str.split(":");
		return subStr[1].trim();
	}

	private String trimDate(String str) { // throw sub string of Date and hour
		String[] subStr = str.split(":");
		String date = subStr[1].trim() + ":" + subStr[2].trim();
		return date;
	}

	private Double trimDbl(String str) { // throw double num from string
		return Double.parseDouble(trimStr(str));
	}

	private Integer trimInt(String str) { // throw integer num from string
		return Integer.parseInt(trimStr(str));
	}

	public void printTable() {
		Node node = main.allWindow;
		Printer printer = Printer.getDefaultPrinter();
		PageLayout pageLayout = printer.createPageLayout(Paper.A4, PageOrientation.LANDSCAPE,
				Printer.MarginType.HARDWARE_MINIMUM);
		PrinterJob job = PrinterJob.createPrinterJob();
		double scaleX = pageLayout.getPrintableWidth() / node.getBoundsInParent().getWidth();
		double scaleY = pageLayout.getPrintableHeight() / node.getBoundsInParent().getHeight();
		Scale scale = new Scale(scaleX, scaleY);
		Transform tr = new Translate(0, -140);
		node.getTransforms().addAll(scale, tr);

		if (job != null && job.showPrintDialog(node.getScene().getWindow())) {
			boolean success = job.printPage(pageLayout, node);
			if (success) {
				job.endJob();
			}
		}
		node.getTransforms().removeAll(scale, tr);
	}

	public void init(MainController mainController) {
		main = mainController;

	}
}
