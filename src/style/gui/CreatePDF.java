package style.gui;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import style.gui.test.create.CreateNodes;
import style.gui.test.take.TTest;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by Sofia on 2017-05-26.
 */
public class CreatePDF {
    private GridPane grid;
    private ComboBox student, test;
    private Button button;
    private Label headline;

    public CreatePDF(){
        grid = CreateNodes.createGrid();
        grid.setHgap(15);
        grid.setVgap(15);

        headline = CreateNodes.createHeader("Create PDF");
        test = CreateNodes.createComboBox("Select test");
        test.setMaxWidth(350);
        student = CreateNodes.createComboBox("Select student");
        button  = CreateNodes.createButton("Create");


        grid.add(headline,1,1);
        grid.add(test,1,2);
        grid.add(student,1,3);
        grid.add(button,1,4);

        grid.setMaxHeight(300);
        grid.setMaxWidth(400);

    }
    public GridPane getCreatePDF(){
    return grid;
    }

    public class PdfService {
            public  void main(String[] args) {
                Document document = new Document();


                try {
                    PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("testar.pdf"));
                    document.open();
                    document.add(new Paragraph());
                    document.add(new Chunk(""));
                    document.close();
                    writer.close();
                } catch (DocumentException e) {
                    e.printStackTrace();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }
