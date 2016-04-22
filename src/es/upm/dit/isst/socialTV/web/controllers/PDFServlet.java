package es.upm.dit.isst.socialTV.web.controllers;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.w3c.tidy.Tidy;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

public class PDFServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//String file = req.getParameter("file");
		String file = "<!DOCTYPE html><html><head><meta charset='UTF-8'></head><body>Hello!</body></html>";
		ByteArrayInputStream input = new ByteArrayInputStream(file.getBytes());
		ByteArrayOutputStream output = new ByteArrayOutputStream();

		// HTML to XHTML
		Tidy tidy = new Tidy();
		tidy.setShowWarnings(true);
		tidy.setInputEncoding("UTF-8");
		tidy.setOutputEncoding("UTF-8");
		tidy.setXHTML(true);
		tidy.setMakeClean(true);
		tidy.parseDOM(input,output);
		input.reset();

		// XTHML Tags
		tidy.setXmlTags(true);
		input = new ByteArrayInputStream(output.toByteArray());
		output.reset();
		tidy.parse(input, output);
		input = new ByteArrayInputStream(output.toByteArray());
		output.reset();

		// Generate PDF
		Document document = new Document();
		PdfWriter writer = null;

		try {
			writer = PdfWriter.getInstance(document, output);
		} catch (DocumentException e1) {
			e1.printStackTrace();
		}
		document.open();

		try {
			XMLWorkerHelper.getInstance().parseXHtml(writer, document, input);
		} catch (IOException e) {
			e.printStackTrace();
		} 
		document.close();

		ServletOutputStream outputStream = resp.getOutputStream();
		resp.setContentType("application/pdf");
		resp.setHeader("Content-Disposition", "inline; filename=Informe.pdf");
		outputStream.write(output.toByteArray());
		outputStream.flush();
		outputStream.close();
		System.out.println();
	}

}
