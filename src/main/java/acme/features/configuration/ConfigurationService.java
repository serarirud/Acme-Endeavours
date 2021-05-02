package acme.features.configuration;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfigurationService {
	
	@Autowired
	private ConfigurationRepository confRepo;
	
	public boolean spamFilter(String text) {
		final Double umbral = this.confRepo.getConfiguration().getThreshold();
		final List<String> spamWords = Arrays.asList(this.confRepo.getConfiguration().getSpamWords().split(","));
		text = text.trim();
		Integer numWordsText = text.toLowerCase().split(" ").length;
		
		final String textMod = text.toLowerCase().replace(",", " ").replace(".", " ").replace(";", " ")
			.replace(":", " ").replace("(", " ").replace(")", " ").replace("-", " ").replace("_", " ")
			.replace("<", " ").replace(">", " ").replace("¿", " ").replace("?", " ").replace("¡", " ")
			.replace("!", " ");

		Integer contador=0;
		for(int i=0; i<spamWords.size();i++) {
			final String palabra = spamWords.get(i);
			final Integer l = palabra.length();
			final Integer numPalabras = palabra.split(" ").length;
			for(int j=0;j<textMod.length()-l + 1;j++) {
				if(palabra.equals(textMod.substring(j, j+l))) {
					contador++;
					if(numPalabras > 1) {
						numWordsText = numWordsText - numPalabras + 1;
					}
				}
			}
		}
		
		final Double porcentajeSpam = contador*100./numWordsText;
		return porcentajeSpam>=umbral;
	}
	
	
	public Double getThreshold() {
		return this.confRepo.getConfiguration().getThreshold();
	}
	

}
