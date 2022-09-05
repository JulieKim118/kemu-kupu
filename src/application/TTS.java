package application;

import java.io.IOException;

import enums.Language;

/**
 * Communicates specifically with Festival TTS to speak words.
 *
 * @author Juwon Jung
 * @author Jared Daniel Recomendable
 * @author Julie Kim
 *
 */
public class TTS {

	/**
	 * Tells Festival to speak with the input parameters to consider.
	 *
	 * @param speed    A double that indicates the scaling of time that Festival
	 *                 speaks in. A smaller number indiccates a faster speed of
	 *                 speech.
	 * @param word     A String containing what Festival should say.
	 * @param language A Language enum that indicates what language Festival should
	 *                 speak in.
	 */
	public static void speak(double speed, String word, Language language) {
		String voice;
		if (language.equals(Language.MAORI)) {
			voice = "voice_akl_mi_pk06_cg";
		} else {
			voice = "voice_akl_nz_jdt_diphone";
		}

		String cmd = "echo \"(" + voice + ") (Parameter.set 'Duration_Stretch " + speed + ") (SayText \\\"" + word
				+ "\\\")\" | festival --pipe";
		ProcessBuilder builder = new ProcessBuilder("/bin/bash", "-c", cmd);
		try {
			Process process = builder.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
