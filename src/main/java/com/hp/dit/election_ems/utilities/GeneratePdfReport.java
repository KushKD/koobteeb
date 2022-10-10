package com.hp.dit.election_ems.utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.hp.dit.election_ems.entities.TransferRequestEntities;
import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;

import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.sun.java.swing.plaf.windows.WindowsBorders;
import org.slf4j.LoggerFactory;


import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GeneratePdfReport {

    //mvn package
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(GeneratePdfReport.class);

    // ByteArrayInputStream
    public static ByteArrayInputStream generateIdCard(TransferRequestEntities data) throws JsonProcessingException {
        TransferRequestEntities vehicleOwnerEntries = null;
        ObjectMapper objectMapper = new ObjectMapper();

        vehicleOwnerEntries = data;
       // String postJson = objectMapper.writeValueAsString(vehicleOwnerEntries);
        Document document = new Document(PageSize.A7, 5, 5, 5, 5);


        ByteArrayOutputStream out = new ByteArrayOutputStream();


        Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
        Font boldFont3 = new Font(Font.FontFamily.TIMES_ROMAN, 8);

        boldFont3.setColor(BaseColor.BLACK);

        try {

            PdfPTable parent = new PdfPTable(1);
            float[] columnWidthsnestedparent = {100f};
            parent.setWidths(columnWidthsnestedparent);
            parent.setWidthPercentage(100);


            // Create a Simple table Earlier Working
            PdfPTable one = new PdfPTable(1);
            float[] columnWidthsnested = {100f};
            one.setWidths(columnWidthsnested);
            one.getDefaultCell().setBorder(0);


            PdfPTable onexx = new PdfPTable(3);
            float[] columnWidthsnesteds = {20f, 60f, 20f};
            onexx.setWidths(columnWidthsnesteds);
            onexx.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            onexx.getDefaultCell().setPadding(0);

            //Image one
            Image imaged = Image.getInstance(new URL(Utilities.getPhotoUrl("ec.jpg")));
            onexx.addCell(imaged);


            PdfPCell cell = new PdfPCell(new Phrase("Chief Electoral Office, Himachal Pradesh", boldFont3));
            cell.setColspan(1);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            onexx.addCell(cell);

            Image image2 = Image.getInstance(new URL(Utilities.getPhotoUrl("dc_shimla.png")));
            onexx.addCell(image2);

            one.addCell(onexx);




            cell = new PdfPCell(new Phrase("Election Expenditure Monitoring", boldFont));
            cell.setColspan(2);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            one.addCell(cell);

            JsonObject jsonObjecttwo = new JsonObject();
            jsonObjecttwo.addProperty("vehicle_number", vehicleOwnerEntries.getVehicleNumber());
            jsonObjecttwo.addProperty("id", vehicleOwnerEntries.getTransferRequestID());

            //postJson
            BarcodeQRCode barcodeQRCodetwo = new BarcodeQRCode(jsonObjecttwo.toString(), 70, 70, null);
            Image codeQrImagetwo = barcodeQRCodetwo.getImage();


            cell.addElement(codeQrImagetwo);
            one.addCell(cell);



            cell  = new PdfPCell(new Phrase("In case this card is lost/found, kindly inform/return to Chief Electoral Office, Himachal Pradesh.",boldFont3));
            cell.setColspan(2);
            cell.setBorder(0);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            one.addCell(cell);




            parent.addCell(one);



            PdfWriter.getInstance(document, out);
            document.open();

            document.add(parent);

            document.close();

        } catch (DocumentException ex) {  // | MalformedURLException ex

            Logger.getLogger(GeneratePdfReport.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //return out;
        return new ByteArrayInputStream(out.toByteArray());
    }
}
