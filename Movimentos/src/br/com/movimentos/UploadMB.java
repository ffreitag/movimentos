package br.com.movimentos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import br.com.movimentos.dao.MovimentosDAO;
import br.com.movimentos.model.Movimentos;
import br.com.movimentos.model.Resultado;

/**
 * Classe responsavel pela carga e importacao dos dados do arquivo texto
 * 
 * @author Fernando Freitag
 * @version 1.0
 * @since 13/12/2015
 */
@ManagedBean
@RequestScoped
public class UploadMB {
	private String destino = "C:\\temp\\";
	UploadedFile file;

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public void executaUpload(FileUploadEvent event) {
		this.file = event.getFile();
		String fileNameUploaded = file.getFileName();
		long fileSizeUploaded = file.getSize();
		String infoAboutFile = "Arquivo recebido: " + fileNameUploaded
				+ "Tamanho do Arquivo:" + fileSizeUploaded;

		try {
			// Copia o arquivo para uma pasta
			copiaArquivo(event.getFile().getFileName(), event.getFile()
					.getInputstream());
			// Importa os dados
			importaDados(event.getFile().getFileName());
		} catch (IOException e) {
			e.printStackTrace();
		}

		FacesMessage message = new FacesMessage("Sucesso! ", infoAboutFile);
		FacesContext.getCurrentInstance().addMessage(null, message);

	}

	public void copiaArquivo(String fileName, InputStream in) {
		try {

			OutputStream out = new FileOutputStream(
					new File(destino + fileName));

			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = in.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}

			in.close();
			out.flush();
			out.close();

			System.out.println("New file created!");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public void importaDados(String filename) throws FileNotFoundException {

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		MovimentosDAO dao = (MovimentosDAO) context.getBean("movimentosDao");

		Scanner scanner = new Scanner(new FileReader(destino + filename))
				.useDelimiter("\\n");

		Scanner cabecalho = new Scanner(scanner.nextLine()).useDelimiter("\\t");
		String cabFilial = cabecalho.next();
		String cabPeriodo = cabecalho.next();
		String cabTotal = cabecalho.next();

		if (cabFilial.equals("Filial") && cabPeriodo.equals("Periodo")
				&& cabTotal.equals("Total")) {

			while (scanner.hasNextLine()) {
				Scanner linha = new Scanner(scanner.nextLine())
						.useDelimiter("\\t");
				String filial = linha.next();
				String periodo = linha.next();
				String total = linha.next().replace(".", "").replace(".", "")
						.replace(",", ".");
				Movimentos movimentacao = new Movimentos(filial, periodo,
						Double.parseDouble(total));
				dao.salvar(movimentacao);

			}

			context.close();
		}
	}
}
