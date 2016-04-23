package es.upm.dit.isst.socialTV.web.controllers;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.w3c.tidy.Tidy;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

import es.upm.dit.isst.socialTV.bs.model.DatoAudiencia;
import es.upm.dit.isst.socialTV.bs.model.DatoAudienciaDAO;
import es.upm.dit.isst.socialTV.bs.model.DatoAudienciaImpl;
import es.upm.dit.isst.socialTV.bs.model.ProgramaTV;
import es.upm.dit.isst.socialTV.bs.model.ProgramaTVDAO;
import es.upm.dit.isst.socialTV.bs.model.ProgramaTVImpl;
import es.upm.dit.isst.socialTV.bs.services.GlobalUtil;

public class PDFServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

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
			
			String fechaInicio = prog.getFechaInicio();
			String fechaFin = prog.getFechaFin();
			fechaInicio = prog.getFechaInicio().substring(0,fechaInicio.length()-6)+' '+prog.getFechaInicio().substring(fechaInicio.length()-5);
			fechaFin = prog.getFechaFin().substring(0,fechaFin.length()-6)+' '+prog.getFechaFin().substring(fechaFin.length()-5);
			
			//Page 1
			String doc1 = "";
			doc1 += "<h1 style=\"text-align: center; font-size:50px; font-variant:small-caps; \" >Informe de audiencia</h1>";
			doc1 += "<h2 style=\"text-align: center; color:#55ACEE; font-size:40px; font-style: italic; \" >SocialTV</h2><hr>";
			doc1 += "<h3>Nombre:   "+prog.getTitulo()+"</h3>";
			doc1 += "<p><b>Episodio:   </b>  "+prog.getEpisodeCode()+"</p>";
			doc1 += "<p><b>Hashtag:   </b>  "+prog.getHashtag()+"</p>";
			doc1 += "<p><b>Inicio:   </b>  "+fechaInicio + " </p>";
			doc1 += "<p><b>Fin:   </b>   "+fechaFin + "</p>";
			doc1 += "<p><b>Menciones en total:   </b>  "+prog.getCount()+"</p>";

			//Page 2
			
			String doc2 = "<h2>Evolucion temporal</h2>";
			doc2+= "<div style=\"margin:auto;\"><table width=\"100%\" style=\"width:100%;\"><tr><th>Hora</th><th>Menciones</th></tr>";
			for (int i = 1; i < numTweets.length; i++){
				doc2+="<tr><td>"+horas[i]+"</td><td>"+numTweets[i]+"</td></tr>";
			}
			doc2 +=  "</table></div>";
			
			
			//Page 3
			String doc3 = "<h2>Impacto provincial</h2>";
			doc3 += "<div style=\"margin:auto;\"><table width=\"100%\" style=\"width:100%;\"><tr><th>Provincia</th><th>Menciones</th></tr>";
			for (int i = 0; i < GlobalUtil.SPAIN_PROVINCES_ARRAY.length ; i++){
				String province = GlobalUtil.SPAIN_PROVINCES_ARRAY[i];
				doc3 += "<tr><td>"+province+"</td><td>"+prog.getProvince(province)+"</td></tr>";
			}
			doc3 += "</table></div>";
			
			

			

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
			PdfContentByte canvas = writer.getDirectContent();
	        CMYKColor magentaColor = new CMYKColor((float)0.643,(float) 0.227, 0.f,(float) 0.067);
	        CMYKColor black = new CMYKColor(0f,0.f,0.f,1.f); 
	        
	        canvas.setColorStroke(magentaColor);
	        canvas.moveTo(46,100);
	        System.out.println(numTweets.length);
	        int numero = numTweets.length-2;
	        if (numero==0) numero = 1;
	        float xSpacing=(559-46)/(numero);
	        float xCumulative = 46;
	        Font helvetica = new Font(FontFamily.HELVETICA, 12);
	        BaseFont bf_helv = helvetica.getCalculatedBaseFont(false);
	        canvas.setFontAndSize(bf_helv, 12);
	        
	        //Find max value
	        int max = numTweets[1];
	        for (int i = 1; i < numTweets.length; i++){
	        	if(numTweets[i]>max)max=numTweets[i];
	        }
	        for (int i = 1; i < numTweets.length; i++){
				canvas.lineTo(xCumulative, 100+ numTweets[i]*300/max);
				canvas.beginText();
				 canvas.showTextAligned(Element.ALIGN_LEFT, ""+horas[i], xCumulative+4, 70, 90);
				 canvas.endText();
				xCumulative += xSpacing;
			}
	        

	        canvas.lineTo(559, 100);
	        canvas.lineTo(46, 100);
	        canvas.setRGBColorFill(85, 172, 238);
	        canvas.fill();
	        canvas.closePathStroke();
	        
	        //Y-Axis
	        
	        canvas.beginText();
	        canvas.resetCMYKColorFill();
			canvas.showTextAligned(Element.ALIGN_RIGHT, "0", 46, 100, 0);
			canvas.showTextAligned(Element.ALIGN_RIGHT, String.format("%.2f", ((float)max/2)), 46, 250, 0);
			canvas.showTextAligned(Element.ALIGN_RIGHT, ""+max, 46, 400, 0);
			canvas.endText();
			// Chart Area
	        canvas.setColorStroke(black);
	        canvas.moveTo(46, 400);
	        canvas.lineTo(46, 100);
	        canvas.lineTo(559, 100);
	        canvas.lineTo(559, 400);
	        canvas.closePathStroke();
	        
			// Generate Page 2      
			
		    document.newPage();
		      
			try {
				XMLWorkerHelper.getInstance().parseXHtml(writer, document, this.parseHTML(doc2, output));
			} catch (IOException e) {
				e.printStackTrace();
			} 
			output.reset();
			
			 document.newPage();
			// Generate Page 3     

			
		   
		      
			try {
				XMLWorkerHelper.getInstance().parseXHtml(writer, document, this.parseHTML(doc3, output));
			} catch (IOException e) {
				e.printStackTrace();
			} 
			output.reset();
	

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
