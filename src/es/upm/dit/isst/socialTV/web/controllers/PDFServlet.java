package es.upm.dit.isst.socialTV.web.controllers;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
/**
 * Servlet que genera un informe en PDF de la monitorizaci�n especificada
 * @author Sonsoles
 *
 */
public class PDFServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Comprobar permisos
		if (!GlobalUtil.checkLogin(req)) {
			GlobalUtil.redirigirLogin(req, resp, GlobalUtil.LOGIN_ERROR_MESSAGE);
			return;
		}

		//Separo la URL por /
		String[] params = req.getRequestURL().toString().split("/"); 
		Long num = (long) 0;	
		//Compruebo si el �ltimo trozo es una cadena num�rica
		try {
			num = Long.parseLong(params[params.length-1]);

		} catch(Exception e){
			resp.sendRedirect("/");
			return;
		}
		
		ProgramaTVDAO ptv = ProgramaTVImpl.getInstance();
		ProgramaTV prog = ptv.programaPorId(num);
		// Compruebo que existe ese programa
		if( prog!=null){

			DatoAudienciaDAO dao = DatoAudienciaImpl.getInstance();
			// Extraigo la audiencia de dicho programa
			List<DatoAudiencia> list = dao.getAudienceForEpisodeWithId(num);	
			if (list.size() < 2) {
				resp.sendRedirect("/");
				return;
			}
			// Hace que no salga el primer valor de tweets, que no es significativo
//			if(list.size()>1)list.remove(list.size()-1);
			// N�mero de monitorizaciones (menos la primera)
			int size = list.size();
			//Array de minutos de medici�n
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
		
			String header = "<!DOCTYPE html><html><head><meta charset='UTF-8'/></head><body><style> font-family:sans-serif;</style>";
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
			doc1 = header + doc1 + footer;
			//Page 2
			
			String doc2 = "<h2>Evoluci�n temporal</h2>";
			doc2+= "<div style=\"margin:auto;\"><table width=\"100%\" style=\"width:100%;\"><tr><th>Hora</th><th>Menciones</th></tr>";
			for (int i = 0; i < numTweets.length; i++){
				doc2+="<tr><td>"+horas[i]+"</td><td>"+numTweets[i]+"</td></tr>";
			}
			doc2 +=  "</table></div>";
			doc2 = header + doc2 + footer;
			
			//Page 3
			String doc3 = "<h2>Impacto provincial</h2>";
			doc3 += "<div style=\"margin:auto;\"><table width=\"100%\" style=\"width:100%;\"><tr><th>Provincia</th><th>Menciones</th></tr>";
			for (int i = 0; i < GlobalUtil.SPAIN_PROVINCES_ARRAY.length ; i++){
				String province = GlobalUtil.SPAIN_PROVINCES_ARRAY[i];
				doc3 += "<tr><td>"+province+"</td><td>"+prog.getProvince(province)+"</td></tr>";
			}
			doc3 += "</table></div>";		
			doc3 = header + doc3 + footer;
			
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
				XMLWorkerHelper.getInstance().parseXHtml(writer, document,  this.parseHTML(doc1, output), Charset.forName("UTF-8"));
			} catch (IOException e) {
				e.printStackTrace();
			} 
			
			output.reset();
			PdfContentByte canvas = writer.getDirectContent();
	        CMYKColor magentaColor = new CMYKColor((float)0.643,(float) 0.227, 0.f,(float) 0.067);
	        CMYKColor black = new CMYKColor(0f,0.f,0.f,1.f); 
	        CMYKColor grey = new CMYKColor(0f,0.f,0.f,(float) 0.5); 
	        float canvasHeight = 300;
	        float canvasTop = 400;
	        float canvasBottom = 100;
	        float canvasLeft = 46;
	        float canvasRight=559;
	       
	        System.out.println(numTweets.length);
	        int numero = numTweets.length-1;
	        if (numero==0) numero = 1;
	        float xSpacing= (canvasRight-canvasLeft+1)/(numero);
	        Logger.getLogger(PDFServlet.class.getName()).info(""+xSpacing);;
	        
	        float xCumulative =canvasLeft; //MARGIN FOR TEXT
	        canvas.setLineDash(4,4);
	        canvas.setColorStroke(grey);
	        
	        //Find max value and paint x grid
	        int max = numTweets[1];
	        for (int i = 0; i < numTweets.length; i++){
	            canvas.moveTo(xCumulative,canvasBottom);
	        	canvas.lineTo(xCumulative,canvasTop);
	 	        canvas.closePathStroke();
	        	if(numTweets[i]>max)max=numTweets[i];
	        	xCumulative += xSpacing;
	        }
	        int precision = 1;
	        if(max>10) precision = 2;
	        if(max>30) precision = 5;
	        if(max>50) precision = 10;
	        float spacing =300/max;
		
	        //Y-Axis
	        Font helvetica = new Font(FontFamily.HELVETICA, 12);
	        BaseFont bf_helv = helvetica.getCalculatedBaseFont(false);
	        canvas.setFontAndSize(bf_helv, 12); 
	     
	        canvas.resetCMYKColorFill();
			canvas.showTextAligned(Element.ALIGN_RIGHT, "0", 46, 100, 0);
			

			
			 for (int i = 0; i <=max; i= i+precision){
				canvas.beginText();
				canvas.showTextAligned(Element.ALIGN_RIGHT, ""+i, (float)canvasLeft, canvasBottom+spacing*(i), 0);
				canvas.endText();
				if(i!=0){
				canvas.moveTo(canvasLeft,canvasBottom+spacing*(i));
	        	canvas.lineTo(canvasRight,canvasBottom+spacing*(i));
	 	        canvas.closePathStroke();}
	     
		     }
			
	        xCumulative = canvasLeft;
	       
	        canvas.setLineDash(0);
	        canvas.setColorStroke(magentaColor);
	        canvas.moveTo(canvasLeft,canvasBottom);
	        for (int i = 0; i < numTweets.length; i++){
	        	
				canvas.lineTo(xCumulative, canvasBottom+ numTweets[i]*canvasHeight/max);
				canvas.beginText();
				canvas.showTextAligned(Element.ALIGN_LEFT, ""+horas[i], 4 + xCumulative, 70, 90);//Height text :70, Rotation:90
				canvas.endText();
				xCumulative += xSpacing;
			}
	        

	        canvas.lineTo(559, 100);
	        canvas.lineTo(46, 100);
	        canvas.setRGBColorStroke(85, 172, 238);
	        canvas.setRGBColorFill(85, 172, 238);
	        canvas.fill();
	        canvas.closePathStroke();
	        


			
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
				XMLWorkerHelper.getInstance().parseXHtml(writer, document, this.parseHTML(doc2, output), Charset.forName("UTF-8"));
			} catch (IOException e) {
				e.printStackTrace();
			} 
			output.reset();
			
			 document.newPage();
			 
			// Generate Page 3     
		      
			try {
				XMLWorkerHelper.getInstance().parseXHtml(writer, document, this.parseHTML(doc3, output), Charset.forName("UTF-8"));
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
		
		// Devolvemos la vista de la gr�fica
		 
		} else {
			resp.sendRedirect("/");
		}
		
		
	}
	
	
	

	public ByteArrayInputStream parseHTML(String file, ByteArrayOutputStream output){
		ByteArrayInputStream input = new ByteArrayInputStream(file.getBytes(Charset.forName("UTF-8")));
		
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
