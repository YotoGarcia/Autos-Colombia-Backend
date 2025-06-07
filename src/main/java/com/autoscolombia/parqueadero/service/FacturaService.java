package com.autoscolombia.parqueadero.service;

import com.autoscolombia.parqueadero.model.Ingreso;
import com.autoscolombia.parqueadero.model.Pago;
import com.itextpdf.barcodes.Barcode128;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.UnitValue;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
public class FacturaService {

    public byte [] generarFacturaPDF(Pago pago){
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()){
            PdfWriter writer = new PdfWriter(baos);
            PdfDocument pdf = new PdfDocument(writer);
            Document doc = new Document(pdf);

            doc.add(new Paragraph("FACTURA DE PAGO").setBold().setFontSize(18));
            doc.add(new Paragraph("Placa: " + pago.getVehiculo().getPlaca()));
            doc.add(new Paragraph("Celda Asignada: " + pago.getCelda().getNombre()));
            doc.add(new Paragraph("Hora de Ingreso: " + pago.getHoraIngreso()));
            doc.add(new Paragraph("Hora de Salida: " + pago.getHoraSalida()));
            doc.add(new Paragraph("Total Pagado: $" + pago.getValorPagado()));


            Barcode128 barcode = new Barcode128(pdf);
            String data = pago.getVehiculo().getPlaca();
            barcode.setCode(data);
            barcode.setCodeType(Barcode128.CODE128);
            Image barcodeImage = new Image(barcode.createFormXObject(pdf));
            barcodeImage.setWidth(UnitValue.createPercentValue(60));
            doc.add(new Paragraph("Código de barras:"));
            doc.add(barcodeImage);

            doc.add(new Paragraph("\nGracias por su visita."));

            doc.close();
            return baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public byte [] generarFacturaIngresoPDF(Ingreso ingreso){
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()){
            PdfWriter writer = new PdfWriter(baos);
            PdfDocument pdf = new PdfDocument(writer);
            Document doc = new Document(pdf);

            doc.add(new Paragraph("COMPROBANTE DE INGRESO").setBold().setFontSize(18));
            doc.add(new Paragraph("Placa: " + ingreso.getVehiculo().getPlaca()));
            doc.add(new Paragraph("Celda Asignada: " + ingreso.getCelda().getNombre()));
            doc.add(new Paragraph("Hora de Ingreso: " + ingreso.getHoraIngreso()));


            Barcode128 barcode = new Barcode128(pdf);
            String data = ingreso.getVehiculo().getPlaca();
            barcode.setCode(data);
            barcode.setCodeType(Barcode128.CODE128);
            Image barcodeImage = new Image(barcode.createFormXObject(pdf));
            barcodeImage.setWidth(UnitValue.createPercentValue(60));
            doc.add(new Paragraph("Código de barras:"));
            doc.add(barcodeImage);

            doc.add(new Paragraph("\nGracias por su visita."));

            doc.close();
            return baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
