package es.upm.dit.isst.socialTV.web.controllers;

import java.awt.Graphics2D;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.w3c.tidy.Tidy;

import com.google.appengine.api.images.Image;
import com.itextpdf.awt.PdfGraphics2D;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

import es.upm.dit.isst.socialTV.bs.beans.GraphBean;
import es.upm.dit.isst.socialTV.bs.model.DatoAudiencia;
import es.upm.dit.isst.socialTV.bs.model.DatoAudienciaDAO;
import es.upm.dit.isst.socialTV.bs.model.DatoAudienciaImpl;
import es.upm.dit.isst.socialTV.bs.model.ProgramaTV;
import es.upm.dit.isst.socialTV.bs.model.ProgramaTVDAO;
import es.upm.dit.isst.socialTV.bs.model.ProgramaTVImpl;
import es.upm.dit.isst.socialTV.bs.services.GlobalUtil;

public class PDFServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		
		
		HttpSession session = req.getSession();
		//Separo la URL por /
		String[] params = req.getRequestURL().toString().split("/"); 
		Long num = (long) 0;	
		//Compruebo si el último trozo es una cadena numérica
		try {
			num = Long.parseLong(params[params.length-1]);

		} catch(Exception e){
			return;
		}
		
		ProgramaTVDAO ptv = ProgramaTVImpl.getInstance();
		ProgramaTV prog = ptv.programaPorId(num);
		// Compruebo que existe ese programa
		if( prog!=null){

			DatoAudienciaDAO dao = DatoAudienciaImpl.getInstance();
			// Extraigo la audiencia de dicho programa
			List<DatoAudiencia> list = dao.getAudienceForEpisodeWithId(num);	
			// Hace que no salga el primer valor de tweets, que no es significativo
			if(list.size()>1)list.remove(list.size()-1);
			// Número de monitorizaciones (menos la primera)
			int size = list.size();
			//Array de minutos de medición
			String[] horas = new String[size];
			//Array de medidas
			int[] numTweets = new int[size];
			int index = 0;
			
			// Recorro los resultados para rellenar los arrays
			for(DatoAudiencia dato : list){
				// TODO Ordenar por fecha
				String date = dato.getFecha();
				horas[size -1 - index] =  date.length() > 5? date.substring(date.toString().length() - 5) : date;
				int cuenta = dato.getCount();
				numTweets[size-1-index]= cuenta;
				index++;
			}
			
			//File commons
		
			String header = "<!DOCTYPE html><html><head><meta charset='UTF-8'></head><body>";
			String footer = "</body></html>";
			
			
			//Page 1
			String doc1 = "";
			doc1 += "<h1 style=\"text-align: center; font-size:50px; font-variant:small-caps; \" >Informe de audiencia</h1>";
			doc1 += "<h2 style=\"text-align: center; font-size:40px; font-style: italic; \" >SocialTV</h2><hr>";
			doc1 += "<h3>Nombre: "+prog.getTitulo()+"</h3>";
			doc1 += "<p><b>Episodio: </b>  "+prog.getEpisodeCode()+"</p>";
			doc1 += "<p><b>Hashtag: </b>  "+prog.getHashtag()+"</p>";
			doc1 += "<p><b>Fecha: </b>  "+prog.getFechaInicio()+" :: "+prog.getFechaFin()+"</p>";
			
			//Page 2
			String content = "<h2>Impacto provincial</h2>";
			content += "<div style=\"margin:auto;\"><table width=\"100%\" style=\"width:100%;\"><tr><th>Provincia</th><th>Menciones</th></tr>";
			for (int i = 0; i < GlobalUtil.SPAIN_PROVINCES_ARRAY.length ; i++){
				String province = GlobalUtil.SPAIN_PROVINCES_ARRAY[i];
				content += "<tr><td>"+province+"</td><td>"+prog.getProvince(province)+"</td></tr>";
			}
			content += "</table></div>";
			
			/*
			//Page 3
						
			String file = "<h1>AAAA</h1><h2>AAA</h2><h3>AAA</h3>";
			*/
			
			

			// Generate PDF
			Document document = new Document();
			PdfWriter writer = null;
			ByteArrayOutputStream output = new ByteArrayOutputStream();

			
			try {
				writer = PdfWriter.getInstance(document, output);
			} catch (DocumentException e1) {
				e1.printStackTrace();
			}
			document.open();
			
			
			// Generate Page 1      
		      
			try {
				XMLWorkerHelper.getInstance().parseXHtml(writer, document, this.parseHTML(doc1, output));
			} catch (IOException e) {
				e.printStackTrace();
			} 
			
			output.reset();
			
			// Generate Page 2      

			
		    document.newPage();
		      
			try {
				XMLWorkerHelper.getInstance().parseXHtml(writer, document, this.parseHTML(content, output));
			} catch (IOException e) {
				e.printStackTrace();
			} 
			output.reset();
			
			 document.newPage();
			/*// Generate Page 3     

			
		   
		      
			try {
				XMLWorkerHelper.getInstance().parseXHtml(writer, document, this.parseHTML(file, output));
			} catch (IOException e) {
				e.printStackTrace();
			} 
			output.reset();*/
			PdfContentByte canvas = writer.getDirectContent();
	        CMYKColor magentaColor = new CMYKColor(0.f, 1.f, 0.f, 0.f);
	        canvas.setColorStroke(magentaColor);
	        canvas.moveTo(36, 36);
	        canvas.lineTo(36, 806);
	        canvas.lineTo(559, 36);
	        canvas.lineTo(559, 806);
	        canvas.closePathStroke();
			document.close();

			ServletOutputStream outputStream = resp.getOutputStream();
			resp.setContentType("application/pdf");
			resp.setHeader("Content-Disposition", "inline; filename=Informe.pdf");
			outputStream.write(output.toByteArray());
			outputStream.flush();
			outputStream.close();
			System.out.println();
		
		// Devolvemos la vista de la gráfica
		 
		} else {
			resp.sendRedirect("/");
		}
		
		
	}

	public ByteArrayInputStream parseHTML(String file, ByteArrayOutputStream output){
		ByteArrayInputStream input = new ByteArrayInputStream(file.getBytes());
		
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
		
		return input;
	}
}
