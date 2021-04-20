package acme.features.spamWord;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.spamWord.SpamWord;

@Service
public class SpamWordFilterService {
	
	@Autowired
	private SpamWordRepository spamRepo;
	
	public boolean spamFilter(final String text, final Integer umbral) {
		Double contador=0.;
		final List<SpamWord> words = this.spamRepo.findMany();
		final Double numWordsText = text.toLowerCase().split(" ").length*1.;
		
		final String textMod =" " +text.toLowerCase().replace(",", " ").replace(".", " ").replace(";", " ")
			.replace(":", " ").replace("(", " ").replace(")", " ").replace("-", " ").replace("_", " ")
			.replace("<", " ").replace(">", " ").replace("¿", " ").replace("?", " ").replace("¡", " ")
			.replace("!", " ").replace("'", " ")+" ";
		
		for(int i=0; i<words.size();i++) {
			final String palabra = " "+words.get(i).getWord()+" ";
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
	

}
