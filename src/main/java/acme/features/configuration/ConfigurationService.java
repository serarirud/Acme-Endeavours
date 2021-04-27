package acme.features.configuration;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfigurationService {
	
	@Autowired
	private ConfigurationRepository confRepo;
	
	public boolean spamFilter(final String text, final Double umbral) {
		Double contador=0.;
		final List<String> spamWords = Arrays.asList(this.confRepo.getConfiguration().getSpamWords().split(","));
		final Double numWordsText = text.toLowerCase().split(" ").length*1.;
		
		final String textMod =" " +text.toLowerCase().replace(",", " ").replace(".", " ").replace(";", " ")
			.replace(":", " ").replace("(", " ").replace(")", " ").replace("-", " ").replace("_", " ")
			.replace("<", " ").replace(">", " ").replace("¿", " ").replace("?", " ").replace("¡", " ")
			.replace("!", " ").replace("'", " ")+" ";
		
		for(int i=0; i<spamWords.size();i++) {
			final String palabra = " "+spamWords.get(i)+" ";
			final String[] textoSplit = textMod.split(palabra);
			if(textoSplit.length==0) {
				contador+=1.;
				break;
			}else {
				contador+=textoSplit.length-1;
			}
		}
		final Double porcentajeSpam = contador*100/numWordsText;
		
		return porcentajeSpam>=umbral;
	}
	
	
	public Double getThreshold() {
		return this.confRepo.getConfiguration().getThreshold();
	}
	

}
